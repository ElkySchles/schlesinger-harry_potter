package weiss.harrypotter;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HPComponent component = DaggerHPComponent
                .builder()
                .build();
        HPFrame frame = component.providesHPFrame();
        frame.setVisible(true);
    }
}
