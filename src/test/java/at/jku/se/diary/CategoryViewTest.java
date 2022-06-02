package at.jku.se.diary;


import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import javafx.scene.control.TextField;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.assertj.core.util.IterableUtil.iterator;
import static org.testfx.assertions.api.Assertions.assertThat;


public class CategoryViewTest extends ApplicationTest{

    final String categoryTextField = "#newCategory";
    final String addButton = "#BtnAdd";
    final String listView = "#lVcategoryList";
    final String okButton = "#BtnOk";
    final String deleteButton = "#delete";


    @Start
    public void start(Stage stage) throws IOException {
        HelloFX diary = new HelloFX();
        URL url = new File("src/main/java/at/jku/se/diary/CategoryList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
/*
    @Test
    public void testUserInput () {

        TextField textfield = ApplicationTest.

        clickOn("#inputField");
        write("This is a test!");
        clickOn("#applyButton");
        assertThat(label.getText(), is("This is a test!"));
    }

*/


}
