package at.jku.se.diary;

import java.util.ArrayList;

public class Diary {
    private ArrayList<DiaryEntry> entryList;

    public Diary(){
        entryList = new ArrayList<>();
    }

    public void addNewEntry(DiaryEntry newEntry){
        entryList.add(newEntry);
    }

}
