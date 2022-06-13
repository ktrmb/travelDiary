package at.jku.se.diary;

import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryDBTest {
    /**
     * Create test-data
     */
    public Diary diary;
    public File diaryFile;
    public DiaryDB diaryDB;
    private ArrayList<StructInformation> arrayListInfos = new ArrayList<>();
    private final DiaryEntry newEntry = new DiaryEntry(1, LocalDate.now(),
            "Ausflug Attersee", "Steinbach am Attersee", "Liebes Tagebuch ...", arrayListInfos);


/*    @BeforeEach
    void setUpTestDiary(){
        diaryDB = new DiaryDB();
        diaryFile = new File("diaryTEST.xml");
        try {
            diary = new Diary();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    void readDiaryTest(){
        diaryDB = new DiaryDB();
        diaryFile = new File("diaryTEST.xml");
        try {
            diary = new Diary();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            diary = diaryDB.readDiary(diaryFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String title = diary.getEntryList().get(0).getTitle();
        assertEquals(title, "Strandbesuch");

    }
/*    @Test
    void writeDiaryTest(){
        try {
            diary.addNewEntry(newEntry);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            diaryDB.writeDiary(diary, diaryFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            diary = diaryDB.readDiary(diaryFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        String title = diary.getEntryList().get(1).getTitle();
        assertEquals(title, "Ausflug Attersee");

    }*/

}
