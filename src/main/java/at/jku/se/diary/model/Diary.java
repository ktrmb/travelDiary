package at.jku.se.diary.model;

import at.jku.se.diary.HelloFX;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

@XmlRootElement(name="myDiary")
public class Diary {
    private ArrayList<DiaryEntry> entryList;
    private DiaryDB diaryDB;
    private File diaryFile;
    private ArrayList<String> categories;
   // private boolean currentEntry = false;
    private DiaryEntry currentEntry;

    public Diary() throws JAXBException {
        entryList = new ArrayList<>();
        diaryDB = HelloFX.diaryDB;
        diaryFile = HelloFX.diaryFile;
        categories = new ArrayList<>();
        currentEntry = null;
    }

    @XmlElement
    public ArrayList<DiaryEntry> getEntryList() {
        return entryList;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setEntryList(ArrayList<DiaryEntry> entryList) {
        this.entryList = entryList;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addNewCategory(String category) throws JAXBException {
        categories.add(category);
        if(this.diaryDB != null){
            diaryDB.writeDiary(this, diaryFile);
        }
    }

    public void addNewEntry(DiaryEntry newEntry) throws JAXBException {
        entryList.add(newEntry);
        if(this.diaryDB != null) {
            diaryDB.writeDiary(this, diaryFile);
        }
    }

    public DiaryEntry getCurrentEntry() {
        return currentEntry;
    }

    public void setCurrentEntry(DiaryEntry currentEntry) {
        this.currentEntry = currentEntry;
    }

    //-------------------ab hier aus Controller ausgelagert:

    public void createNewEntry(LocalDate date, String title, String address, String diaryText, String pic1, String pic2, String pic3) throws JAXBException {
        ArrayList<StructInformation> structuredInfo = new ArrayList<>();

        if(getCurrentEntry() != null) {
            structuredInfo = getCurrentEntry().getStructuredInfo();
            setCurrentEntry(null);
        }
        int id = getEntryList().size() + 1;

        DiaryEntry newEntry = new DiaryEntry(id, date, title, address, diaryText, structuredInfo);

        //Bilder zuerst in ordner "pictures" speichern und dann in das newDiary Objekt speichern
        //Bild1:
        String defaultPic = "Icons/pic.png";
        if(!pic1.contains(defaultPic)){
            String imgName1 = newEntry.saveImageToFile(pic1, (String.valueOf(newEntry.getId())+"_1"));
            newEntry.setPicture1(imgName1);
        }
        //Bild 2:
        if(!pic2.contains(defaultPic)){
            String imgName2 = newEntry.saveImageToFile(pic2, (String.valueOf(newEntry.getId())+"_2"));
            newEntry.setPicture2(imgName2);
        }
        //Bild 3:
        if(!pic3.contains(defaultPic)){
            String imgName3 = newEntry.saveImageToFile(pic3, (String.valueOf(newEntry.getId())+"_3"));
            newEntry.setPicture3(imgName3);
        }

        addNewEntry(newEntry);
    }

    public File addPic(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Bild aus");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }



/*
    public String toString(){
        String output = "";
        for(DiaryEntry e : getEntryList()){
            output += e.toString();
        }
        return output;
    }

    //wieder weglöschen!
    public void outputDiaryIDs(){
        for(DiaryEntry e : getEntryList()){
            System.out.println("ID: " + e.getId() + " Titel: " + e.getTitle());
        }
    }*/
}
