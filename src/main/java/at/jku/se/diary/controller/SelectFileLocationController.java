
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
import java.nio.charset.StandardCharsets;

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



/*
package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import at.jku.se.diary.model.SceneSwitch;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
        */
/*//*
/xml to string
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
        }

        try {
            BufferedReader in
                    = new BufferedReader(new FileReader("diary.xml"));
            StringBuffer output = new StringBuffer();
            String st;
            while ((st=in.readLine()) != null) {
                output.append(st);
            }
            filecontent = output.toString();
            System.out.println(filecontent);
            in.close();
        }
        catch (Exception fx) {
            System.out.println("Exception " + fx.toString());
        }*//*


        try{
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            InputSource is = new InputSource("diary.xml");
            Document document = docBuilderFactory.newDocumentBuilder().parse(is);
            StringWriter sw = new StringWriter();
            Transformer serializer = TransformerFactory.newInstance().newTransformer();
            serializer.transform(new DOMSource(document), new StreamResult(sw));
            filecontent = sw.toString();
        } catch (Exception e) {
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
*/
