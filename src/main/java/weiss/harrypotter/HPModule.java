package weiss.harrypotter;
import dagger.Module;
import dagger.Provides;
import net.bytebuddy.pool.TypePool;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@Module

public class HPModule {

    @Provides
            public HPService providesHPService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wizard-world-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        HPService service = retrofit.create(HPService.class);
        return service;
    }
    @Provides
    @Named("listModel")
    @Singleton
    public DefaultListModel<String> listModel(){
        return new DefaultListModel<>();

    }


}


