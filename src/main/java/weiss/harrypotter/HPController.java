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
    private List<Spell> allInfo;
    //private  JLabel label1;
    String name;
    String incantation;
    String type;
    String light;
    String creator;


    @Inject
    public HPController(
            HPService service,
            @Named("listModel") DefaultListModel<String> listModel
            //@Named("label1") JLabel label1
    ) {

        this.service = service;
        this.listModel = listModel;
        //this.label1 = label1;

    }


    public void requestSpells() {

        service.getSpells()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        spells -> {
                            SwingUtilities.invokeLater(() -> {
                                this.setSpells(spells);
                                allInfo = spells;
                            });
                        }
                        ,
                        Throwable::printStackTrace
                );
    }



    public void setSpells(List<Spell> spells) {
        for (Spell spell : spells) {
            this.listModel.addElement(spell.getName());
        }

    }

    public String getInfo(Object item) {
        for (int i = 0; i < listModel.getSize(); i++) {
            if (listModel.get(i).equals(item)) {
                name = allInfo.get(i).getName();
                incantation = allInfo.get(i).getIncantation();
                type = allInfo.get(i).getType();
                light = allInfo.get(i).getLight();
                creator = allInfo.get(i).getCreator();
            }
        }
        return "Name " + name + "     Incantation: " + incantation +
                "    type: " + type + "     light: " + light + "    creator " + creator;
    }


}
