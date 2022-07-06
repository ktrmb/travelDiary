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
    private String diaryFilePath;

    public Diary() throws JAXBException {
        entryList = new ArrayList<>();
        diaryDB = HelloFX.diaryDB;
        diaryFile = HelloFX.diaryFile;
        categories = new ArrayList<>();
        currentEntry = null;
        diaryFilePath = "diary.xml";
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

    public void createNewEntry(int id, LocalDate date, String title, String address, String diaryText, String pic1, String pic2,
                               String pic3, ArrayList<StructInformation> structInfo) throws JAXBException {
        //ArrayList<StructInformation> structuredInfo = new ArrayList<>();

        if(getCurrentEntry() != null) {
            structInfo = getCurrentEntry().getStructuredInfo();
            setCurrentEntry(null);
        }

        DiaryEntry newEntry = new DiaryEntry(id, date, title, address, diaryText, structInfo);

        String defaultPic = "png";
        if(!pic1.contains(defaultPic)){
            newEntry.setPicture1(newEntry.saveImageToFile(pic1, (String.valueOf(newEntry.getId())+"_1")));
        }
        if(!pic2.contains(defaultPic)){
            newEntry.setPicture2(newEntry.saveImageToFile(pic2, (String.valueOf(newEntry.getId())+"_2")));
        }
        if(!pic3.contains(defaultPic)){
            newEntry.setPicture3(newEntry.saveImageToFile(pic3, (String.valueOf(newEntry.getId())+"_3")));
        }
        addNewEntry(newEntry);
    }

    public File addPic(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
    }

    //Filterhilfsklassen auslagern ------------------------------------------------------
    public boolean filterCategories(DiaryEntry entry, String category){
        if(entry.getStructuredInfo() != null){
            for(StructInformation s : entry.getStructuredInfo()){
                if(s.getCategory().equals(category)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean filterStructInfoText(DiaryEntry entry, String value){
        if(entry.getStructuredInfo() != null){
            for(StructInformation s : entry.getStructuredInfo()){
                if(s.getStructuredText().toLowerCase().contains(value.toLowerCase())){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean filterStars(DiaryEntry entry, String rating){
        if(entry.getStructuredInfo() != null){
            for(StructInformation s : entry.getStructuredInfo()){
                if(String.valueOf(s.getStars()).equals(rating)){
                    return true;
                }
            }
        }
        return false;
    }
    public void setDiaryFilePath (String path) throws JAXBException {
        this.diaryFilePath = path;
        if(this.diaryDB != null){
            diaryDB.writeDiary(this, diaryFile);
        }
    }

    public String getDiaryFilePath () throws JAXBException {
        return diaryFilePath;
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
