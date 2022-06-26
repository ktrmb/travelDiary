package at.jku.se.diary.controller;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class SelectFileLocationController extends Component {
    Diary diary = HelloFX.diary;

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
        String filecontent = "";
        //xml to string
        try (InputStream in = new FileInputStream(diary.getDiaryFilePath());
             BufferedReader r = new BufferedReader(
                     new InputStreamReader(in, StandardCharsets.UTF_8))) {
            String str = null;
            StringBuilder sb = new StringBuilder(8192);
            while ((str = r.readLine()) != null) {
                sb.append(str);
            }
            filecontent = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //new file + content
        FileNameExtensionFilter filter = new FileNameExtensionFilter ("XML-File","XML");
        JFileChooser saveas = new JFileChooser();
        saveas.setDialogTitle("Save File at ...");
        saveas.setFileFilter((javax.swing.filechooser.FileFilter) filter);

        int userSelection = saveas.showSaveDialog(saveas);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            FileWriter fw = new FileWriter(saveas.getSelectedFile() + ".xml");
            path = saveas.getSelectedFile().getAbsolutePath();
            diary.setDiaryFilePath(path);
            filename = saveas.getSelectedFile().getName();
            //lb1.setText(path + " " + filename);

            fw.write(filecontent);
            fw.close();
        }
    }




    @FXML
    void savePhotoLocation(MouseEvent event) {
        //no need for method
    }

    @FXML
    void saveSetLocations(MouseEvent event) throws IOException {
        System.out.println(diary.getDiaryFilePath());
        SceneSwitch s = new SceneSwitch("journalList", btnSaveFileLocation.getScene());
        s.switchScene();
    }

}
