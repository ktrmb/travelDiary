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

class DiaryEntryTest {

    private DiaryEntry entry1;
    private DiaryEntry entry2;
    private ArrayList<StructInformation> arrayListInfos = new ArrayList<>();

    @BeforeEach
    void setUp(){
        entry1 = new DiaryEntry();
        //String address, String diaryText, ArrayList<StructInformation> infos
        entry2 = new DiaryEntry(0, LocalDate.now(), "Urlaub", "Schweiz", "Es war schön", arrayListInfos);
    }

    @Test
    void setId(){
        entry1.setId(1);
        assertEquals(entry1.getId(), 1);
    }

    @Test
    void setTitle() {
        entry1.setTitle("Going San Francisco");
        assertEquals(entry1.getTitle(), "Going San Francisco");
    }

    @Test
    void setDate() {
        entry1.setDate(LocalDate.of(2022, 5, 4));
        assertEquals(entry1.getDate(), LocalDate.of(2022, 5, 4));
    }

    @Test
    void setAddress(){
        entry1.setAddress("Weyregg am Attersee");
        assertEquals(entry1.getAddress(), "Weyregg am Attersee");
    }
    @Test
    void setDiaryText(){
        entry1.setDiaryText("Liebes Tagebuch, es war sehr schön");
        assertEquals(entry1.getDiaryText(), "Liebes Tagebuch, es war sehr schön");
    }
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

    @Test
    void setPicture1(){
        entry1.setPicture1("defaultPic.png");
        assertEquals(entry1.getPicture1(), "defaultPic.png");
    }
    @Test
    void setPicture2(){
        entry1.setPicture2("defaultPic.png");
        assertEquals(entry1.getPicture2(), "defaultPic.png");
    }
    @Test
    void setPicture3(){
        entry1.setPicture3("defaultPic.png");
        assertEquals(entry1.getPicture3(), "defaultPic.png");
    }
/*    @Test
    void saveImageToFileTest(){
        String imgFile = "C:\\Users\\magda\\Pictures\\Fotobuch\\map.JPG";
        String imageName = entry1.saveImageToFile(imgFile, "1");
        assertEquals(imageName, "image1.jpg");
    }*/

}
