package at.jku.se.diary;

import at.jku.se.diary.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {

    Map map;
    DiaryEntry entry1;
    Diary diary;
    DiaryDB diaryDB;
    public static File diaryFile;

    /**
     * creating a new Diary and a new map before each test
     */
    @BeforeEach
    void setUp() throws JAXBException {
        diary = new Diary();
        diaryFile = new File("diaryTest.xml");
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


/*
    @Test
    void getMarkerTest() {
        Marker m = map.getMarker().get(0);
        DiaryEntry e = diary.getEntryList().get(0);

        MarkerPoint m1 = map.getDataFromAPI(e.getAddress(), e.getId());
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(new LatLong(m1.getLatitute(), m1.getLongitute()));
        Marker pos = new Marker(markerOptions1);

        assertEquals(m.getJSObject(), pos.getJSObject());
    }
    */
}
