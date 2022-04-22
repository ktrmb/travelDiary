/*
 * Source Code must not be used without permission of
 * Reinhold Plösch (reinhold.ploesch@gmail.com)
 * (c) Reinhold Plösch, 2020
 */
package at.jku.se.diary;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) throws IOException{
       /* String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");*/

        Scene scene = new Scene(loadFXML("DiaryEntryView"), 640, 480);
        //Scene journalList = new Scene(loadFXML("JournalList"), 640, 480);
        stage.setScene(scene);
        //stage.setScene(journalList);
        stage.show();
    }

    private static Parent loadFXML(String diaryEntryView) throws IOException{
        URL url = new File("src/main/java/at/jku/se/diary/" + diaryEntryView + ".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return root;
    }

    public static void main(String[] args) {
        launch();
    }
}
