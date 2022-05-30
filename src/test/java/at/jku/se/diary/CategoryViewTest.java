package at.jku.se.diary;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;

import java.io.File;
import java.io.IOException;
import java.net.URL;


@ExtendWith(ApplicationExtension.class)
public class CategoryViewTest {

    @Start
    private void start(Stage stage) throws IOException {
        HelloFX diary = new HelloFX();
        URL url = new File("src/main/java/at/jku/se/diary/CategoryList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void containsButtons(FxRobot robot) {
        FxAssert.verifyThat("#BtnOk", LabeledMatchers.hasText("Ok"));
        FxAssert.verifyThat("#BtnAdd", LabeledMatchers.hasText("Add"));
    }


}
