package at.jku.se.diary;


import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * this class is for testing the Diary class
 * @author Team E
 *
 */
public class DiaryTest{

    private Diary diary;
    private ArrayList<DiaryEntry> diaryEntryList;
    private ArrayList<StructInformation> structInfoList;
    private ArrayList<String> categoryList;
    private DiaryEntry entry1;
    private DiaryEntry entry2;
    private String category1;
    private String category2;

    /**
     * creating a new Diary with entries and categories before each test
     */
    @BeforeEach
    public void setUp(){
        try {
            diary = new Diary();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        diaryEntryList = new ArrayList<>();
        structInfoList = new ArrayList<>();
        categoryList = new ArrayList<>();
        entry1 = new DiaryEntry(1, LocalDate.now(),
                "Ausflug Attersee", "Steinbach am Attersee", "Liebes Tagebuch ...", structInfoList);
        entry2 = new DiaryEntry(2, LocalDate.now(),
                "Ausflug Italien", "Rosolina Mare", "Liebes Tagebuch ...", structInfoList);
        category1 = "Strand";
        category2 = "Hotel";
    }

    /**
     * test for setting a diaryEntryList
     */
    @Test
    void setEntryListTest(){
        diaryEntryList.add(entry1);
        diaryEntryList.add(entry2);
        diary.setEntryList(diaryEntryList);
        assertEquals(diary.getEntryList(), diaryEntryList);
    }

    /**
     * test for adding a category to the categoryList
     */
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
        assertTrue(diary.getCategories().contains("Strand"));
        assertTrue(diary.getCategories().contains("Hotel"));
    }

    /**
     * test checks if the diaryEntryList isn??t empty anymore after adding a new entry
     */
    @Test
    void addNewEntryTest(){
        assertTrue(diaryEntryList.isEmpty());

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
        assertFalse(diaryEntryList.isEmpty());
    }


    /**
     * test checks if the currentEnty is set when adding new structured Info
     */
    @Test
    void setCurrentEntry() {
        DiaryEntry currentEntry = new DiaryEntry(1, LocalDate.now(), "Urlaub Rom",
                "Italien", "Liebes Tagebuch ...", structInfoList );
        diary.setCurrentEntry(currentEntry);
        assertEquals(diary.getCurrentEntry(), currentEntry);

    }

    /**
     * test if a new entry is created right
     * @throws JAXBException
     */
    @Test
    void createNewEntry() throws JAXBException {
        assertTrue(diary.getEntryList().isEmpty());
        String path = "Icons/pic.png";
        String pic1 = path;
        String pic2 = path;
        String pic3 = path;
        diary.setCurrentEntry(entry1);
        diary.createNewEntry(1, LocalDate.now(), "TestTitle", "TestAdresse",
                "TestText", pic1, pic2, pic3, structInfoList);
        assertFalse(diary.getEntryList().isEmpty());
        for(DiaryEntry e : diary.getEntryList()){
            assertEquals(e.getTitle(),"TestTitle");
            assertEquals(e.getDate(), LocalDate.now());
            assertEquals(e.getAddress(), "TestAdresse");
            assertEquals(e.getDiaryText(), "TestText");
        }
    }

    /**
     * test for the filter-methods
     */
    @Test
    void filterTest(){
        StructInformation info1 = new StructInformation(1, "Hotel", 4, "Text1");
        StructInformation info2 = new StructInformation(2, "Strand", 5, "Text2");
        entry1.getStructuredInfo().add(info1);
        entry1.getStructuredInfo().add(info2);

        assertTrue(diary.filterCategories(entry1, "Hotel"));
        assertTrue(diary.filterCategories(entry1, "Strand"));
        assertFalse(diary.filterCategories(entry1, "Restaurant"));
        assertTrue(diary.filterStars(entry1, "4.0"));
        assertFalse(diary.filterStars(entry1, "3.0"));
        assertTrue(diary.filterStars(entry1, "5.0"));
        assertTrue(diary.filterStructInfoText(entry1, "Tex"));
        assertFalse(diary.filterStructInfoText(entry1, "Text123"));
        assertTrue(diary.filterStructInfoText(entry1, "text2"));

    }

    /**
     * test if an id is created right
     */
    @Test
    void createIDTest(){
        assertEquals(diary.createID(), 1);
        diary.getEntryList().add(entry1);
        assertEquals(diary.createID(), 2);
        diary.getEntryList().add(entry2);
        assertEquals(diary.createID(), 3);
        diary.getEntryList().remove(entry1);
        assertEquals(diary.createID(), 3);
    }
}
