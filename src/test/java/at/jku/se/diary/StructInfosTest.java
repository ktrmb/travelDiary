package at.jku.se.diary;


import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StructInfosTest {

    StructInformation structInformation = new StructInformation();

    @Test
    void setStructInformation() {
        structInformation.setStructuredText("waren Pizza essen");
        assertEquals(structInformation.getStructuredText(), "waren Pizza essen");
    }

    @Test
    void setStars() {
        structInformation.setStars(4);
        assertEquals(structInformation.getStars(), 4);
    }

    @Test
    void setCategory() {
        structInformation.setCategory("Restaurant");
        assertEquals(structInformation.getCategory(), "Restaurant");
    }
}
