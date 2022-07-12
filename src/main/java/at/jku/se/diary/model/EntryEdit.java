package at.jku.se.diary.model;

import at.jku.se.diary.HelloFX;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * this class contains part of the functions regarding editing entries
 * @author Team E
 *
 */
public class EntryEdit {
    private final Diary diary;
    private DiaryEntry entry;

    public EntryEdit () {
        this.diary = HelloFX.diary;
    }

    public EntryEdit (Diary diary) {
        this.diary = diary;
    }

    /**
     * @param entry currently edited entry
     */
    public void setEntry(DiaryEntry entry) {
        this.entry = entry;
    }

    /**
     * @return entry
     */
    public DiaryEntry getEntry () {
        return entry;
    }

    /**
     * Deletes selected entry
     * @throws JAXBException
     */
    public void deleteEntry() throws JAXBException {
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
        if(HelloFX.diaryDB != null){
            HelloFX.diaryDB.writeDiary(diary, HelloFX.diaryFile);
        }
    }

    /**
     * Method to delete pictures
     * @param pic selected to delete
     * @param picNumber for search in files (part of filename)
     */
    public void deletePic(ImageView pic, String picNumber) {
        String fileName = "src/pictures/image" + entry.getId() + "_" + picNumber + ".jpg"; //pathToPic
        deletePicFile(fileName);
        pic.setImage(new Image("file:src/pictures/defaultPic.png")); //defaultPicPath
        if(Objects.equals(picNumber, "1")){
            entry.setPicture1("defaultPic.png");
        }else if(Objects.equals(picNumber, "2")){
            entry.setPicture2("defaultPic.png");
        }else if(Objects.equals(picNumber, "3")){
            entry.setPicture3("defaultPic.png");
        }
    }

    /**
     * Actually deletes the selected picture (Helper-Method)
     * @param fileName directory to pic file
     */
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
