package at.jku.se.diary;

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

    public Diary() throws JAXBException {
        entryList = new ArrayList<>();
        diaryDB = HelloFX.diaryDB;
        diaryFile = HelloFX.diaryFile;
    }
    @XmlElement
    public ArrayList<DiaryEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(ArrayList<DiaryEntry> entryList) {
        this.entryList = entryList;
    }

    public void addNewEntry(DiaryEntry newEntry) throws JAXBException {
        entryList.add(newEntry);
        diaryDB.writeDiary(this, diaryFile);

    }

}
