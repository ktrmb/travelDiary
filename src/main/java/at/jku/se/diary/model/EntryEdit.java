package at.jku.se.diary.model;

import at.jku.se.diary.HelloFX;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
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
}
