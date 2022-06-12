package at.jku.se.diary.model;
import at.jku.se.diary.HelloFX;
import at.jku.se.diary.controller.EntryEditController;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class EntryEdit {
    private Diary diary;
    private DiaryEntry entry;
    private Stage stage;
    private EntryEditController c;

    public EntryEdit () throws MalformedURLException {
        this.diary = HelloFX.diary;
        URL url = new File("src/main/java/at/jku/se/diary/view/EntryEdit.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        c = loader.getController();
    }

    public void setEntry(DiaryEntry entry) {
        this.entry = entry;
    }
//save geht noch nicht rest sollte passen
    public void saveEntry() throws JAXBException {
        int oldId = entry.getId();
        String defaultWord = "default";

        DiaryEntry newEntry = new DiaryEntry(oldId, c.txtDate.getValue(),
                c.txtTitel.getText(), c.txtAdress.getText(), c.txtText.getHtmlText(), entry.getStructuredInfo());

        if(!c.pic1.getImage().getUrl().contains(defaultWord)){
            String imgName1 = saveImageToFile(c.pic1.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_1"));
            newEntry.setPicture1(imgName1);
        }

        if(!c.pic2.getImage().getUrl().contains(defaultWord)){
            String imgName2 = saveImageToFile(c.pic2.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_2"));
            newEntry.setPicture2(imgName2);
        }

        if(!c.pic3.getImage().getUrl().contains(defaultWord)){
            String imgName3 = saveImageToFile(c.pic3.getImage().getUrl(), (String.valueOf(newEntry.getId())+"_3"));
            newEntry.setPicture3(imgName3);
        }

        diary.getEntryList().remove(entry);
        diary.addNewEntry(newEntry);
        HelloFX.diaryDB.writeDiary(diary, HelloFX.diaryFile);
    }

    public void editPic(ImageView pic) {
        File selectedFile = addPic();
        Image image = new Image(String.valueOf(selectedFile));
        pic.setImage(image);
    }

    public void deletePic(ImageView pic) {

    }

    public File addPic(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Bild aus");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        System.out.println(selectedFile);
        return selectedFile;
    }

    public String saveImageToFile(String fileImg, String id){
        Image image = new Image(fileImg);
        String imageName = "image" + id + ".jpg";
        File imageFile = new File("src\\pictures\\"+imageName);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

        try{
            ImageIO.write(bufferedImage, "jpg", imageFile);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return imageName;
    }
}
