package at.jku.se.diary;

import at.jku.se.diary.model.Map;
import at.jku.se.diary.model.MarkerPoint;
import at.jku.se.diary.model.StructInformation;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    Map map;


    @BeforeEach
    void setUp(){
        this.map = new Map(HelloFX.diary);
    }


    @Test
    void getDataFromApiTest() {
        MarkerPoint rome = new MarkerPoint(0, "Rome", 41.9027835,12.4963655);
        MarkerPoint m = this.map.getDataFromAPI("Rome", 0);
        assertEquals(m.getAddress(), rome.getAddress());
        assertEquals(m.getLatitute(), rome.getLatitute());
        assertEquals(m.getLongitute(), rome.getLongitute());

    }
/*
    @Test
    public void getDataExceptionTest() {
        MarkerPoint m = this.map.getDataFromAPI("");
        assertNull(m.getLongitute());
    }

 */
}
