
package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * this class contains methods to save a backup file at chosen directory
 * @author Team E
 *
 */
public class SelectFileLocationController {
    private final Diary diary = HelloFX.diary;
    private final DiaryDB diaryDB = HelloFX.diaryDB;
    private final File diaryFile = HelloFX.diaryFile;
    private String filePath = "diary.xml";

    @FXML
    private Button btnSaveFileLocation;
    @FXML
    private Button btnSearchLocationFile;
    @FXML
    private Label txtSaveLocation;

    /**
     * before the scene loads, sets file path text
     */
    public void initialize() throws JAXBException {
        txtSaveLocation.setText(diaryDB.readDiary(diaryFile).getDiaryFilePath());
    }

    /**
     * loads Save Dialog Window, copies file at directory
     * @param event button clicked to choose directory
     * @throws IOException
     * @throws JAXBException
     */
    @FXML
    void saveFileLocation(MouseEvent event) throws IOException, JAXBException {
        FileNameExtensionFilter filter = new FileNameExtensionFilter ("XML-File","XML");
        JFileChooser saveas = new JFileChooser();
        saveas.setDialogTitle("Save File at ...");
        saveas.setFileFilter(filter);

        int userSelection = saveas.showSaveDialog(saveas);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            filePath = saveas.getSelectedFile().getAbsolutePath();
            diary.setDiaryFilePath(filePath);
            txtSaveLocation.setText(filePath);
        }

        System.out.println(filePath);
        File destination = new File(filePath);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(diaryFile);
            os = new FileOutputStream(destination);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            assert is != null;
            is.close();
            assert os != null;
            os.close();
        }
    }

    /**
     * switches back to the "JournalList" scene
     * @param event button clicked to switch scene
     */
    @FXML
    void saveSetLocations(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("JournalList", btnSaveFileLocation.getScene());
        s.switchScene();
    }
}