package weiss.harrypotter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import javax.inject.Named;
import javax.swing.*;

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
        //List<Spell> spells = new List<>();
    }


}