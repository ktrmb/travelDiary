package at.jku.se.diary;

import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.EntryEdit;
import at.jku.se.diary.model.StructInformation;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * this class is for testing the EntryEdit class
 * @author Team E
 *
 */
public class EntryEditTest {

    private Diary diary;
    private ArrayList<DiaryEntry> diaryEntryList;
    private ArrayList<StructInformation> structInfoList;
    private ArrayList<String> categoryList;
    private DiaryEntry entry1;
    private DiaryEntry entry2;
    private String category1;
    private String category2;
    private EntryEdit entryEdit;
    private EntryEdit entryEdit1;

    /**
     * creating some test-data
     */
    void setUp(){
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
        entryEdit = new EntryEdit(diary);
    }

    /**
     * to test the setter and getter
     */
    @Test
    public void setAndGetEntryTest(){
        setUp();
        assertEquals(entryEdit.getEntry(), null);
        entryEdit.setEntry(entry1);
        assertEquals(entryEdit.getEntry(), entry1);
        entryEdit.setEntry(entry2);
        assertEquals(entryEdit.getEntry(), entry2);
    }

    /**
     * to test whether a diaryEntry can be deleted correctly
     * Important note: to run this test a jpg-picture with the name "picDelete1" has to be
     * manually saved into the "pictures" folder. If this picture disappears after
     * running the test, the test has been passed.
     */
    @Test
    public void deleteEntryTest() throws Exception {
        setUp();

        diary.addNewEntry(entry1);
        diary.addNewEntry(entry2);
        assertTrue(diary.getEntryList().contains(entry1));
        assertTrue(diary.getEntryList().contains(entry2));

        entryEdit.setEntry(entry1);
        entryEdit.deleteEntry();
        assertFalse(diary.getEntryList().contains(entry1));
        assertTrue(diary.getEntryList().contains(entry2));

        diary.addNewEntry(entry1);
        entry1.setPicture1("picDelete1.jpg");
        entryEdit.setEntry(entry1);
        assertTrue(diary.getEntryList().contains(entry1));
        entryEdit.deleteEntry();
        assertFalse(diary.getEntryList().contains(entry1));

    }

    /**
     * testing the default constructor without Parameter
     */
    @Test
    public void defaultConstructorTest() throws Exception {
        entryEdit1 = new EntryEdit();
        assertEquals(entryEdit1.getEntry(), null);
    }
}
