package at.jku.se.diary.model;

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

    public SceneSwitch(String newScene, Scene scene) {
        this.newScene = newScene;
        this.scene = scene;
    }

    public void switchScene () throws IOException {
        switch (newScene) {
            case "journalList":
                sceneJournalList();
                break;
            case "newEntry":
                sceneNewEntry();
                break;
            case "entryEdit":
                sceneEntryEdit();
                break;
            case "fileLocation":
                sceneFileLocation();
                break;
            case "structInfo":
                sceneStructInfo();
            case "category":
                sceneCategoryList();
            case "map":
                sceneMap();
            default:
                System.out.println("Scene not available!" + " " + newScene);
        }
    }

    public void sceneJournalList() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/JournalList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneNewEntry() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/DiaryEntryView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneEntryEdit() {

    }

    public void sceneFileLocation() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/SelectFileLocation.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneStructInfo() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/StructInformationView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneCategoryList() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/CategoryList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    public void sceneMap() throws IOException {
        URL url = new File("src/main/java/at/jku/se/diary/view/MapView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }
}
