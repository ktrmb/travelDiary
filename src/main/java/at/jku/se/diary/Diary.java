package at.jku.se.diary;

import javafx.util.Pair;

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
    private boolean currentEntry = false;

    public Diary() throws JAXBException {
        entryList = new ArrayList<>();
        diaryDB = HelloFX.diaryDB;
        diaryFile = HelloFX.diaryFile;
        categories = new ArrayList<>();
        currentEntry = false;

        //Todo delete later
        categories.add("Restaurant");
        categories.add("Kino");
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


    public void addNewEntry(DiaryEntry newEntry) throws JAXBException {
        entryList.add(newEntry);
        diaryDB.writeDiary(this, diaryFile);
    }

    public boolean getCurrentEntry() {
        return currentEntry;
    }

    public void setCurrentEntry(boolean currentEntry) {
        this.currentEntry = currentEntry;
    }


}
