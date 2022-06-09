package at.jku.se.diary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SceneSwitch {
    private String newScene; //Main, NewEntry, JournalList, Map, ...
    private Scene scene;
    //Funktioniert noch nicht
    //Construktor noch ändern ig...
    public SceneSwitch(String newScene, Scene scene) {
        this.newScene = newScene;
        this.scene = scene;
    }

    public void switchScene (String newScene) throws IOException {
        switch (newScene) {
            case "journalList":
                sceneJournalList();
                break;
            case "newEntry":
                sceneNewEntry();
                break;
            case "entryView":
                sceneEntryView();
                break;
            case "fileLocation":
                sceneFileLocation();
                break;
            default:
                System.out.println("Scene not available!");
        }
    }

    public void sceneJournalList() {

    }

    public void sceneNewEntry() {

    }

    public void sceneEntryView() {

    }

    public void sceneFileLocation() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/SelectFileLocation.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }
}
