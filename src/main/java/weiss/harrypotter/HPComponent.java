package weiss.harrypotter;

import dagger.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Component(modules =  {HPModule.class})


public interface HPComponent {
    HPFrame providesHPFrame();
}

