package weiss.harrypotter;

import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.util.List;

public class HPController {

    private final DefaultListModel<String> listModel;
    HPService service;
    private List<Spell> allInfo;


    @Inject
    public HPController(
            HPService service,
            @Named("listModel") DefaultListModel<String> listModel
    ) {

        this.service = service;
        this.listModel = listModel;

    }


    public void requestSpells() {

        service.getSpells()
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
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

    public String getInfo(int i) {

                String name = allInfo.get(i).getName();
                String incantation = allInfo.get(i).getIncantation();
                String type = allInfo.get(i).getType();
                String light = allInfo.get(i).getLight();
                String creator = allInfo.get(i).getCreator();

        return "\n\t Name: " + name + "\t\t   Incantation: " + incantation +
                "\t\t    Type: " + type + "\t     Light: " + light + "\t\t\t\t\t\t    Creator: " + creator;
    }


}
