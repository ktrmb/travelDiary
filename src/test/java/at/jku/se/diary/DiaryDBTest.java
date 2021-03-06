package at.jku.se.diary;

import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * this class is for testing the DiaryDB class
 * @author Team E
 *
 */
public class DiaryDBTest {

    private Diary diary;
    private File diaryFile;
    private DiaryDB diaryDB;
    private ArrayList<StructInformation> arrayListInfos;
    private DiaryEntry newEntry;


    /**
     * to set up test data before each test
     * @throws JAXBException
     */
    @BeforeEach
    void setUp() throws JAXBException {
        diary = new Diary();
        diaryFile = new File("diaryTest.xml");
        diaryDB = new DiaryDB();
    }

    /**
     * to test if the data from a xml file can be read
     * @throws JAXBException
     */
    @Test
    void readDiaryTest() throws JAXBException {
       assertTrue(diary.getEntryList().isEmpty());
       diary = diaryDB.readDiary(diaryFile);
       assertFalse(diary.getEntryList().isEmpty());
       String title = diary.getEntryList().get(0).getTitle();
       assertEquals(title, "Ausflug Attersee");
    }

    /**
     * to test if data can be written in the xml file
     * @throws JAXBException
     */
    @Test
    void writeDiaryTest() throws JAXBException {
        diaryDB.writeDiary(diary, diaryFile);
        diaryDB.readDiary(diaryFile);
        assertTrue(diary.getEntryList().isEmpty());

        arrayListInfos = new ArrayList<>();
        newEntry = new DiaryEntry(1, LocalDate.now(), "Ausflug Attersee", "Steinbach am Attersee",
                "Liebes Tagebuch ...", arrayListInfos);
        diary.addNewEntry(newEntry);
        diaryDB.writeDiary(diary, diaryFile);
        diaryDB.readDiary(diaryFile);
        assertFalse(diary.getEntryList().isEmpty());
    }

}
