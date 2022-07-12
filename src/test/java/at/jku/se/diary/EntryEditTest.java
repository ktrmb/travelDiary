package at.jku.se.diary;

import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.EntryEdit;
import at.jku.se.diary.model.StructInformation;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
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
     * Method: deleteEntry()
     */
    @Test
    public void testDeleteEntry() throws Exception {
        setUp();
        diary.addNewEntry(entry1);
        diary.addNewEntry(entry2);
        assertTrue(diary.getEntryList().contains(entry1));
        assertTrue(diary.getEntryList().contains(entry2));

        entryEdit.setEntry(entry1);
        entryEdit.deleteEntry();
        assertFalse(diary.getEntryList().contains(entry1));
    }



    /**
     * Method: deletePic(ImageView pic, String picNumber)
     */
    @Test
    public void testDeletePic() throws Exception {
     /*   ImageView picture = new ImageView();
        picture.setImage(pic);
        ee.deletePic(picture, "1");
        System.out.print(picture.getImage().getUrl());
*/

    }

    /**
     * Method: deletePicFile(String fileName)
     */
    @Test
    public void testDeletePicFile() throws Exception {

    }






}
