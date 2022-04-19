package at.jku.se.diary;

import java.util.ArrayList;

public class Diary {
    private ArrayList<DiaryEntry> entryList;

    public Diary(){
        entryList = new ArrayList<>();
    }

    public ArrayList<DiaryEntry> getEntryList() {
        return entryList;
    }

    public void setEntryList(ArrayList<DiaryEntry> entryList) {
        this.entryList = entryList;
    }

    public void addNewEntry(DiaryEntry newEntry){
        entryList.add(newEntry);
    }

}
