package weiss.harrypotter;

import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class HPFrame extends JFrame {
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JScrollPane scrollPane;

    //private List<String> model = new ArrayList<>();
    private final JList<String> list;
    public HPFrame() throws IOException {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
        setSize(1200, 1000);
        setTitle("Spell Info");

        list = new JList<>(listModel);
        list.setBounds(10,10,200,400);
        //mainPanel.setSize(500,500);
        scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.WEST);


        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("clicked");

            }
        });





        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wizard-world-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        HPService service = retrofit.create(HPService.class);




        service.getSpells()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        Spells -> {
                            SwingUtilities.invokeLater(() -> {
                                for (Spell spell : Spells) {
                                    listModel.addElement(spell.getName());
                                }
                            });
                        }
                        ,
                        Throwable::printStackTrace

                );

    }

}
