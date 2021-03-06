package at.jku.se.diary;

import at.jku.se.diary.model.Map;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.Diary;
import at.jku.se.diary.model.DiaryDB;
import at.jku.se.diary.model.MarkerPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * this class is for testing the Map and Markerpoint classes
 * @author Team E
 *
 */
public class MapTest {

    Map map;
    DiaryEntry entry1;
    Diary diary;
    DiaryDB diaryDB;

    /**
     * creating a new Diary and a new map before each test
     */
    @BeforeEach
    void setUp() throws JAXBException {
        diary = new Diary();
        File diaryFile = new File("diaryTest.xml");
        diaryDB = new DiaryDB();
        diary = diaryDB.readDiary(diaryFile);
        this.map = new Map(diary);
        entry1 = diary.getEntryList().get(0);
    }

    /**
     * test checks if the longitude and latitude of an address is correct
     */
    @Test
    void getDataFromApiTest() {
        MarkerPoint rome = new MarkerPoint(0, "Rome", 41.9027835,12.4963655);
        MarkerPoint m = this.map.getDataFromAPI("Rome", 0);
        assertEquals(m.getAddress(), rome.getAddress());
        assertEquals(m.getLatitute(), rome.getLatitute());
        assertEquals(m.getLongitute(), rome.getLongitute());

    }

    /**
     * test checks if the correct entry returns, when function is called with longitude and latitude of the address
     */
    @Test
    void getEntryfromLatLngTest() {
        MarkerPoint m = this.map.getDataFromAPI(entry1.getAddress(),entry1.getId());
        DiaryEntry result = map.getEntryFromLatLng(m.getLatitute() , m.getLongitute() );
        assertEquals(result.getTitle(), entry1.getTitle());
    }

    /**
     * test checks if null returns, when function is called with longitude and latitude of the address,
     * but the id does not match of markerpoint and entry
     */
    @Test
    void getNullWhenIdDoesNotMatch() {
        MarkerPoint m = this.map.getDataFromAPI(entry1.getAddress(),5);
        DiaryEntry result = map.getEntryFromLatLng(m.getLatitute() , m.getLongitute() );
        assertNull(result);
    }

    /**
     * Test checks if no entry returns, when wrong longitute and latitute
     */
    @Test
    void getNullfromLatLngTest() {
        DiaryEntry result = map.getEntryFromLatLng(42.1324 , 12.4342 );
        assertEquals(result, null);
    }


    /**
     * Test checks if no entry returns, when only wrong longitute
     */
    @Test
    void getNullfromLatTest() {
        DiaryEntry result = map.getEntryFromLatLng(42.1324 , 19.4342 );
        assertEquals(result, null);
    }

    /**
     * test checks if function throws error, when entry has no location
     */
    @Test
    public void exceptionWhenNoLocationTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.map.getDataFromAPI("", 0);
        });

        String expectedMessage = "Location not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
