package weiss.harrypotter;

import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.util.List;

public class HPController {

    private final DefaultListModel<String> listModel;
    HPService service;
    private List<Spell> spells;


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

                            });
                        }
                        ,
                        Throwable::printStackTrace
                );
    }



    public void setSpells(List<Spell> spells) {
        this.spells = spells;
        for (Spell spell : spells) {
            this.listModel.addElement(spell.getName());
        }

    }

    public String getInfo(int i) {

                String name = spells.get(i).getName();
                String incantation = spells.get(i).getIncantation();
                String type = spells.get(i).getType();
                String light = spells.get(i).getLight();
                String creator = spells.get(i).getCreator();

        return "Name: " + name + "\t\t   Incantation: " + incantation +
                "\t\t    Type: " + type + "\t     Light: " + light + "\t\t\t\t\t\t    Creator: " + creator;
    }


}
