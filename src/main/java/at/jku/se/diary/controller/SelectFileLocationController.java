package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import at.jku.se.diary.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import java.io.*;

public class SelectFileLocationController {
    private Diary diary = HelloFX.diary;
    private String filePath = "diary.xml";
    private DiaryDB diaryDB = HelloFX.diaryDB;
    private File diaryFile = HelloFX.diaryFile;

    @FXML
    private Button btnSaveFileLocation;

    @FXML
    private Button btnSearchLocationFile;

    @FXML
    private Label txtSaveLocation;

    @FXML
    void saveFileLocation(MouseEvent event) throws IOException, JAXBException {
        String path;
        String filecontent = "";
        File diaryFile = new File("diary.xml");
        /*//xml to string
        try (InputStream in = new FileInputStream("diary.xml");
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
        }*/

        Reader fileReader = new FileReader(diaryFile);
        BufferedReader bufReader = new BufferedReader(fileReader);

        StringBuilder sb = new StringBuilder();
        String line = bufReader.readLine();
        while( line != null){
            sb.append(line).append("\n");
            line = bufReader.readLine();
        }
        filecontent = sb.toString();

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

            fw.write(filecontent);
            fw.close();
            filePath = path;
            txtSaveLocation.setText(path);
        }
    }

    public void initialize() throws JAXBException {
        filePath = diaryDB.readDiary(diaryFile).getDiaryFilePath();
        txtSaveLocation.setText(filePath);
    }

    @FXML
    void saveSetLocations(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("JournalList", btnSaveFileLocation.getScene());
        s.switchScene();
    }

}
