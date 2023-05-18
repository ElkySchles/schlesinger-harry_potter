package weiss.harrypotter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class HPFrame extends JFrame {
    final DefaultListModel<String> listModel;
    private final JScrollPane scrollPane;

    private final JList<String> list;
    HPController controller;
    private final JLabel label1;
    JLabel[] labels;

    private final List<Spell> spell;
    HPService service;

    @Inject
    public HPFrame(HPController controller,
                @Named("listModel") DefaultListModel<String> listModel,
                   @Named("spell") List<Spell> spell,
                   @Named("label1") JLabel label1

    ) {
        this.controller = controller;
        this.listModel = listModel;
        this.spell = spell;
        this.label1 = label1;



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
        setSize(1200, 1000);
        setTitle("Spell Info");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //JPanel middlePanel = new JPanel();
        //middlePanel.setLayout(new BorderLayout());
        //label1 = new JLabel("Hello");


        label1 = new JLabel();
        listModel = controller.setList();
        list = new JList<>(listModel);
        list.setBounds(10, 10, 200, 400);
        mainPanel.setSize(500,500);
        scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.WEST);

        mainPanel.add(label1, SwingConstants.CENTER);


        DefaultListModel<String> finalListModel = listModel;
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JList target = (JList) e.getSource();
                Object item = target.getModel().getElementAt(target.getSelectedIndex());
                //for (int i = 0; i < finalListModel.getSize(); i++) {
                    //if (finalListModel.get(i).equals(item)) {
                        controller.getInfo(item);
                    //}
                //}
                requestFocus();

            }
        });
        /*Retrofit retrofit = new Retrofit.Builder()
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

                                for (int i = 0; i < Spells.size(); i++) {
                                    listModel.addElement(Spells.get(i).getName());
                                }
                            });
                        }
                        ,
                        Throwable::printStackTrace

                );*/


    }
}

