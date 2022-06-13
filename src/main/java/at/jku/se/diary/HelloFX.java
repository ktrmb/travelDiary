/*
 * Source Code must not be used without permission of
 * Reinhold Plösch (reinhold.ploesch@gmail.com)
 * (c) Reinhold Plösch, 2020
 */
package at.jku.se.diary;

import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HelloFX extends Application {
    public static Diary diary;
    public static DiaryDB diaryDB;
    public static File diaryFile;

    @Override
    public void start(Stage stage) throws IOException{
        Scene journalList = new Scene(loadFXML("JournalList"), 640, 480);
        stage.setScene(journalList);
        stage.show();
    }

    private static Parent loadFXML(String diaryEntryView) throws IOException{
        URL url = new File("src/main/java/at/jku/se/diary/view/" + diaryEntryView + ".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        return root;
    }

    public static void main(String[] args) {
        //Beim Starten des Programms wird neues Diary-Objekt erzeugt,
        // dass unten dann mit den bereits vorhandenen Daten(der XML) befüllt wird
        diaryDB = new DiaryDB();
        diaryFile = new File("diary.xml");
        try {
            diary = new Diary();
            diary = diaryDB.readDiary(diaryFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        launch();
    }
}

















