package at.jku.se.diary;

import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.EntryEdit;
import at.jku.se.diary.model.StructInformation;
import javafx.scene.image.Image;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EntryEditTest {

    private ArrayList<StructInformation> arrayListInfo = new ArrayList<>();
    private final DiaryEntry entry = new DiaryEntry(1, LocalDate.now(), "Spanien-Reise", "Malaga",
            "Liebes Tagebuch, ...", arrayListInfo);
    private Image defaultPic = new Image("file:src/pictures/defaultPic.png");
    private Image pic = new Image("file:src/pictures/image_test.jpg");
    private EntryEdit ee = new EntryEdit();

    /**
     * Method: setEntry(DiaryEntry entry)
     */
    @Test
    public void testSetEntry() {
        ee.setEntry(entry);
        assertEquals(ee.getEntry(), entry);
    }

    /**
     * Method: deleteEntry()
     */
    @Test
    public void testDeleteEntry() throws Exception {
        ee.deleteEntry();
        assertNull(ee.getEntry());
    }

    /**
     * Method: editPic(ImageView pic)
     */
/*    @Test
    public void testEditPic() throws Exception {
        ee.editPic();
    }*/

    /**
     * Method: deletePic(ImageView pic, String picNumber)
     */
    @Test
    public void testDeletePic() throws Exception {
        //Image View stört beim Testen
    }

    /**
     * Method: deletePicFile(String fileName)
     */
    @Test
    public void testDeletePicFile() throws Exception {
        //zusammenhängend mit testDeletePic
    }

    /**
     * Method: addPic()
     */
    @Test
    public void testAddPic() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: saveImageToFile(String fileImg, String id)
     */
    @Test
    public void testSaveImageToFile() throws Exception {
//TODO: Test goes here...
    }


}
