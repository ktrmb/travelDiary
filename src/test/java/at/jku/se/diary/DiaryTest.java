package at.jku.se.diary;


import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryTest{

    /**
     * Creating Test-Class
     */
    Diary diary;
    {
        try {
            diary = new Diary();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    private ArrayList<DiaryEntry> diaryEntryList = new ArrayList<>();
    private ArrayList<StructInformation> structInfoList = new ArrayList<>();
    private ArrayList<String> categoryList = new ArrayList<>();
    private final DiaryEntry entry1 = new DiaryEntry(1, LocalDate.now(),
            "Ausflug Attersee", "Steinbach am Attersee", "Liebes Tagebuch ...", structInfoList);
    private final DiaryEntry entry2 = new DiaryEntry(2, LocalDate.now(),
            "Ausflug Italien", "Rosolina Mare", "Liebes Tagebuch ...", structInfoList);
    private String category1 = "Strand";
    private String category2 = "Hotel";

    @Test
    void setEntryList(){
        diaryEntryList.add(entry1);
        diaryEntryList.add(entry2);
        diary.setEntryList(diaryEntryList);
        assertEquals(diary.getEntryList(), diaryEntryList);
    }
    @Test
    void setCategoriesTest(){
        categoryList.add(category1);
        categoryList.add(category2);
        diary.setCategories(categoryList);
        assertEquals(diary.getCategories(), categoryList);
        try {
            diary.addNewCategory("Meer");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        assertTrue(diary.getCategories().contains("Meer"));
    }
    @Test
    void addNewEntryTest(){
        DiaryEntry newEntry = new DiaryEntry(3, LocalDate.now(),
                "Besichtigung Sagrada Familia", "Barcelona", "Liebes Tagebuch ...", structInfoList);
        try {
            diary.addNewEntry(entry1);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        diary.setEntryList(diaryEntryList);
        try {
            diary.addNewEntry(newEntry);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //hier weiter!

    }
    @Test
    void isCurrentEntryTest() {
        diary.setCurrentEntry(true);
        assertTrue(diary.isCurrentEntry());
        diary.setCurrentEntry(false);
        assertFalse(diary.isCurrentEntry());
    }






}
