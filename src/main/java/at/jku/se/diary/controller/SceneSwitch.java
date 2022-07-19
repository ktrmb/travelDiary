package at.jku.se.diary.controller;

import at.jku.se.diary.model.DiaryEntry;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.File;
import java.io.IOException;
import java.net.URL;
/**
 *
 * this class contains all navigation methods, switches between scenes
 * @author Team E
 *
 */
public class SceneSwitch {
    private final String newScene;
    private final Scene scene;
    private final String pathToViews = "src/main/java/at/jku/se/diary/view/";

    /**
     * @param newScene fxml file name of scene to switch to
     * @param scene current scene
     */
    public SceneSwitch(String newScene, Scene scene) {
        this.newScene = newScene;
        this.scene = scene;
    }

    /**
     * classic switch between scenes
     * @throws IOException
     */
    public void switchScene()throws IOException {
        URL url = new File(pathToViews + newScene + ".fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        scene.setRoot(root);
    }

    /**
     * special scene switch to pass variables between controllers
     * @param entry gets passed from EntryEditController to StructuredInformationController
     * @throws IOException
     */
    public void switchSceneStructInfoController(DiaryEntry entry)throws IOException {
        URL url = new File(pathToViews + newScene + ".fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        StructuredInfoController controller = loader.getController();
        controller.setEntryEdit(entry);
        scene.setRoot(root);
    }

    /**
     * special scene switch to pass variables between controllers
     * @param entry gets passed from JournalListController to EntryEditController
     * @throws IOException
     */
    public void switchSceneEntryEditController(DiaryEntry entry)throws IOException {
        URL url = new File(pathToViews + newScene + ".fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        EntryEditController controller = loader.getController();
        controller.setSelectedEntry(entry);
        scene.setRoot(root);
    }
}