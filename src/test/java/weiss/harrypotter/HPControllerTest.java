package weiss.harrypotter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import javax.inject.Named;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HPControllerTest {
    static{
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void requestSpells(){
        //given
        HPService service = mock();
        DefaultListModel listModel = mock();
        HPController controller = new HPController(service, listModel);
        List<Spell> spells = mock();
        Observable<List<Spell>> observable = Observable.just(spells);
        doReturn(observable).when(service).getSpells();
        //when
        controller.requestSpells();
        //then
        verify(service).getSpells();

    }

    @Test
    void setSpells(){
        //given
        List<Spell> spells = new ArrayList<>();
        DefaultListModel listModel = mock();
        HPService service = mock();
        HPController controller = new HPController(service, listModel);
        Spell spell = new Spell();
        spells.add(spell);
        spell.setName("Avada Kevadra");
        //when
        controller.setSpells(spells);
        //then
        verify(listModel).addElement(spell.getName());

    }
    @Test
    void getInfo(){
        //given
        DefaultListModel listModel = mock();
        HPService service = mock();
        HPController controller = new HPController(service, listModel);
        Spell spell = new Spell();
        List<Spell> spells = new ArrayList<>();
        spells.add(spell);
        controller.setSpells(spells);
        spell.setName("Opening Charm");
        spell.setIncantation("Aberto");
        spell.setType("Charm");
        spell.setLight("Blue");
        spell.setCreator("Null");

        //when
        String info = controller.getInfo(0);


        //then
        assertEquals(info, "Name: " + "Opening Charm" + "\t\t   Incantation: " + "Aberto" +
        "\t\t    Type: " + "Charm" + "\t     Light: " + "Blue" + "\t\t\t\t\t\t    Creator: " + "Null");
    }


}