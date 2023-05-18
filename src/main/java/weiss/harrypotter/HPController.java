package weiss.harrypotter;

import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.util.List;

public class HPController {
    //private final JList<Spell> list;
    private final DefaultListModel<String> listModel;
    HPService service;
    HPFrame frame;
    private final List<Spell> spell;
    private final JLabel label1;


    @Inject
    public HPController(
            HPService service,
            @Named("listModel") DefaultListModel<String> listModel,
            @Named("spell") List<Spell> spell,
            @Named("label1") JLabel label1
    ) {

        this.service = service;
        this.listModel = listModel;
        this.spell = spell;
        this.label1 = label1;

    }


    public DefaultListModel<String> setList() {

        service.getSpells()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        Spells -> {
                            SwingUtilities.invokeLater(() -> {
                                this.setListModel(Spells);
                                //this.setSpells(Spells);

                            });
                        }
                        ,
                        Throwable::printStackTrace
                );
        return this.getListModel();
    }



    public void setListModel(List<Spell> spells) {
        for (int i = 0; i < spells.size(); i++) {
            this.listModel.addElement(spells.get(i).getName());
        }

    }

    public DefaultListModel<String> getListModel() {
        return this.listModel;
    }

    public String getInfo(Object item) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.get(i).equals(item)) {
                //String name = spell.get(i).getLight();
                label1.setText(item.toString());
            }
        }
        return null;
    }


}
