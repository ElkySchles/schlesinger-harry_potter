package weiss.harrypotter;

import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HPServiceTest {
    @Test
    public void getSpells(){

        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wizard-world-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        HPService service = retrofit.create(HPService.class);
        //when
        List<Spell> list = service.getSpells().blockingFirst();
        //then
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertNotNull(list.get(0).getName());
    }

}