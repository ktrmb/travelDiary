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
import java.io.*;
import java.net.URL;
/**
 *
 * this is the main class
 * @author Team E
 *
 */
public class HelloFX extends Application {
    public static Diary diary;
    public static DiaryDB diaryDB;
    public static File diaryFile;

    /**
     * starts the application and sets height and width and sets stage to journalList
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException{
        Scene journalList = new Scene(loadFXML(), 640, 480);
        stage.setScene(journalList);
        stage.show();
    }

    /**
     * stops the application and writes all important diary elements to XML file
     * @throws JAXBException
     */
    @Override
    public void stop() throws JAXBException {
        diaryDB.writeDiary(diary, diaryFile);
    }

    private static Parent loadFXML() throws IOException{
        URL url = new File("src/main/java/at/jku/se/diary/view/" + "JournalList" + ".fxml").toURI().toURL();
        return FXMLLoader.load(url);
    }

    /**
     * reads the xml file, which contains all safed Diary Entries
     */
    public static void main(String[] args) {
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

















