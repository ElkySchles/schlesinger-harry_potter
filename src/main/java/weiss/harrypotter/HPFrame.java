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

    JLabel[][] labels = new JLabel[5][2];
    String[] names = new String[]{"Name", "Incantation", "type", "light", "creator"};
    //HPService service;

    @Inject
    public HPFrame(HPController controller,
                @Named("listModel") DefaultListModel<String> listModel/*,
                   @Named("label1") JLabel label1
                   */
    ) {
        this.controller = controller;
        this.listModel = listModel;
        //this.label1 = label1;

        //Setting the main GUI



        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
        setSize(1200, 1000);
        setTitle("Spell Info");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("src/main/java/weiss/harrypotter/HarryPotter.png");
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);


        //mainPanel.add(thumb,BorderLayout.NORTH);
        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        otherPanel.add(thumb);
        mainPanel.add(otherPanel, BorderLayout.NORTH);




        /*JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,2));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(100,150,100,150));
        mainPanel.add(centerPanel,BorderLayout.CENTER);*/

        //Setting the area in which to display the text
        label1 = new JLabel();
        JLabel finalLabel1 = label1;
        mainPanel.add(label1, BorderLayout.CENTER);
        /*for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                labels[i][j] = new JLabel();
                centerPanel.add(labels[i][j]);
                labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }

         */

        //Setting the list
        controller.requestSpells();
        list = new JList<>(listModel);
        list.setBounds(10, 10, 200, 400);
        mainPanel.setSize(500,500);
        scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.WEST);



        //Calling the information when something on the list is clicked
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                JList target = (JList) e.getSource();
                Object item = target.getModel().getElementAt(target.getSelectedIndex());
                finalLabel1.setText(controller.getInfo(item));
                requestFocus();

            }
        });


    }
}

