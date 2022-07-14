/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * this class is for testing the DiaryEntry class
 * @author Team E
 *
 */
class DiaryEntryTest {

    private DiaryEntry entry1;
    private DiaryEntry entry2;
    private ArrayList<StructInformation> arrayListInfos = new ArrayList<>();
    public String defaultPicName = "defaultPic.png";

    /**
     * to set up test data before each test
     */
    @BeforeEach
    void setUp(){
        entry1 = new DiaryEntry();
        entry2 = new DiaryEntry(0, LocalDate.now(), "Urlaub", "Schweiz", "Es war schön", arrayListInfos);
    }

    /**
     * to test if an id is set right
     */
    @Test
    void setId(){
        entry1.setId(1);
        assertEquals(entry1.getId(), 1);
        assertEquals(entry2.getId(), 0);
        entry2.setId(3);
        assertEquals(entry2.getId(), 3);
    }

    /**
     * to test if a title is set right
     */
    @Test
    void setTitle() {
        assertEquals(entry1.getTitle(), null);
        entry1.setTitle("Going San Francisco");
        assertEquals(entry1.getTitle(), "Going San Francisco");
    }

    /**
     * to test if a date is set right
     */
    @Test
    void setDate() {
        entry1.setDate(LocalDate.of(2022, 5, 4));
        assertEquals(entry1.getDate(), LocalDate.of(2022, 5, 4));
        entry1.setDate(null);
        assertEquals(entry1.getDate(), LocalDate.now());
    }

    /**
     * to test if an address is set right
     */
    @Test
    void setAddress(){
        entry1.setAddress("Weyregg am Attersee");
        assertEquals(entry1.getAddress(), "Weyregg am Attersee");
    }

    /**
     * to test if a diary text is set right
     */
    @Test
    void setDiaryText(){
        entry1.setDiaryText("Liebes Tagebuch, es war sehr schön");
        assertEquals(entry1.getDiaryText(), "Liebes Tagebuch, es war sehr schön");
    }

    /**
     * to thest if the structured information is set right
     */
    @Test
    void setStructuredInfo(){
        StructInformation structInfo1 = new StructInformation(1, "See", 4, "sehr schön");
        StructInformation structInfo2 = new StructInformation(2, "Essen", 4, "sehr gut");

        arrayListInfos = new ArrayList<>();
        arrayListInfos.add(structInfo1);
        arrayListInfos.add(structInfo2);
        entry1.setStructuredInfo(arrayListInfos);
        assertEquals(entry1.getStructuredInfo(), arrayListInfos);
    }

    /**
     * to test if the name of picture 1 is set right
     */
    @Test
    void setPicture1(){
        entry1.setPicture1(defaultPicName);
        assertEquals(entry1.getPicture1(), defaultPicName);
    }

    /**
     * to test if the name of picture 2 is set right
     */
    @Test
    void setPicture2(){
        entry1.setPicture2(defaultPicName);
        assertEquals(entry1.getPicture2(), defaultPicName);
    }

    /**
     * to test if the name of picture 3 is set right
     */
    @Test
    void setPicture3(){
        entry1.setPicture3(defaultPicName);
        assertEquals(entry1.getPicture3(), defaultPicName);
    }

}
