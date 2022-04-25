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
    private ArrayList<ShortDiaryEntry> shortEntryList;
    private DiaryDB diaryDB;
    private File diaryFile;

    public Diary() throws JAXBException {
        entryList = new ArrayList<>();
        diaryDB = HelloFX.diaryDB;
        diaryFile = HelloFX.diaryFile;
    }
    @XmlElement
    public ArrayList<DiaryEntry> getEntryList() {
        return entryList;
    }

    public ArrayList<ShortDiaryEntry> getShortEntryList() {return shortEntryList;}

    public void setEntryList(ArrayList<DiaryEntry> entryList) {
        this.entryList = entryList;
    }

    public void addNewEntry(DiaryEntry newEntry) throws JAXBException {
        entryList.add(newEntry);
        diaryDB.writeDiary(this, diaryFile);

        ShortDiaryEntry sEntry = new ShortDiaryEntry(newEntry.getTitle(), newEntry.getDate().toString());
        shortEntryList.add(sEntry);

    }


}
