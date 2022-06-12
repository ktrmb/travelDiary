package at.jku.se.diary.controller;
import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class SelectFileLocationController extends Component {
    File diary = HelloFX.diaryFile;

    @FXML
    private Button btnSaveFileLocation;

    @FXML
    private Button btnSearchLocationFile;

    @FXML
    private Button btnSearchLocationPhoto;

    @FXML
    void saveFileLocation(MouseEvent event) throws IOException {
        String path;
        String filename;
        String filecontent = diary.toString();

        FileNameExtensionFilter filter = new FileNameExtensionFilter ("XML-File","XML");
        JFileChooser saveas = new JFileChooser();
        saveas.setDialogTitle("Save File at ...");
        saveas.setFileFilter((javax.swing.filechooser.FileFilter) filter);

        int userSelection = saveas.showSaveDialog(saveas);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            FileWriter fw = new FileWriter(saveas.getSelectedFile() + ".xml");
            path = saveas.getSelectedFile().getAbsolutePath();
            filename = saveas.getSelectedFile().getName();
            //lb1.setText(path + " " + filename);

            fw.write(filecontent);
            fw.close();
        }

    }

    @FXML
    void savePhotoLocation(MouseEvent event) {

    }

    @FXML
    void saveSetLocations(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("journalList", btnSaveFileLocation.getScene());
        s.switchScene();
    }

}
