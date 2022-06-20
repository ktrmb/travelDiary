package at.jku.se.diary.model;
import at.jku.se.diary.HelloFX;
import at.jku.se.diary.controller.EntryEditController;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EntryEdit {
    private final Diary diary;
    private DiaryEntry entry;
    private Stage stage;

    public EntryEdit () {
        this.diary = HelloFX.diary;
    }

    public void setEntry(DiaryEntry entry) {
        this.entry = entry;
    }

    public DiaryEntry getEntry () {
        return entry;
    }

    public void deleteEntry() throws JAXBException, IOException {
        ArrayList<String> pictureNames = new ArrayList<>();
        pictureNames.add(entry.getPicture1());
        pictureNames.add(entry.getPicture2());
        pictureNames.add(entry.getPicture3());
        for(String pic : pictureNames){
            if(!pic.contains("default")){
                deletePicFile("src/pictures/"+pic);
            }
        }
        diary.getEntryList().remove(entry);
        HelloFX.diaryDB.writeDiary(diary, HelloFX.diaryFile);
    }

    public void editPic(ImageView pic) {
        File selectedFile = addPic();
        if(selectedFile!=null){
            Image image = new Image(String.valueOf(selectedFile));
            pic.setImage(image);
        }
    }

    public void deletePic(ImageView pic, String picNumber) {
        String fileName = "src/pictures/image" + entry.getId() + "_" + picNumber + ".jpg"; //pathToPic
        deletePicFile(fileName);
        pic.setImage(new Image("file:src/pictures/defaultPic.png")); //defaultPicPath
        entry.setPicture1("defaultPic.png"); //defaultPicName
    }

    public void deletePicFile(String fileName) {
        File newFile = new File(fileName);
        String pathString = newFile.getAbsolutePath();
        Path path = Paths.get(pathString);
        try{
            Files.delete(path);
        }catch(IOException e){
            e.printStackTrace();
        }
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
