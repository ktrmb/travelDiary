/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.jku.se.diary;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author reinhold
 */
class DiaryEntryTest {
    /**
     * Creating a new TestObject
     */
    private ArrayList<StructInformation> arrayListInfos = new ArrayList<>();
    private final DiaryEntry entry = new DiaryEntry(1, LocalDate.now(),
            "Ausflug Attersee", "Steinbach am Attersee", "Liebes Tagebuch ...", arrayListInfos);

    /**
     * Test of setTitle method, of class DiaryEntry.
     */
    @Test
    void setTitle() {
        entry.setTitle("Going San Francisco");
        assertEquals(entry.getTitle(), "Going San Francisco");
    }
    @Test
    void setDate() {
        entry.setDate(LocalDate.of(2022, 5, 4));
        assertEquals(entry.getDate(), LocalDate.of(2022, 5, 4));
    }

    @Test
    void setAddress(){
        entry.setAddress("Weyregg am Attersee");
        assertEquals(entry.getAddress(), "Weyregg am Attersee");
    }
    @Test
    void setDiaryText(){
        entry.setDiaryText("Liebes Tagebuch, es war sehr schön");
        assertEquals(entry.getDiaryText(), "Liebes Tagebuch, es war sehr schön");
    }
    @Test
    void setStructuredInfo(){
        StructInformation structInfo1 = new StructInformation(1, "See", 4.00, "sehr schön");
        StructInformation structInfo2 = new StructInformation(2, "Essen", 4.00, "sehr gut");
        ArrayList<StructInformation> arrayListStructInfos = new ArrayList<>();
        arrayListStructInfos.add(structInfo1);
        arrayListStructInfos.add(structInfo2);
        entry.setStructuredInfo(arrayListStructInfos);
        assertEquals(entry.getStructuredInfo(), arrayListStructInfos);
    }
}
