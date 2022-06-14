package at.jku.se.diary.model;

import at.jku.se.diary.HelloFX;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
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


/*    //wieder weglöschen!
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