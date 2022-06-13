package at.jku.se.diary;

import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StructuredInfoTest {

    StructInformation structInformation = new StructInformation();

    @Test
    void setId() {
        this.structInformation.setId(1);
        Assertions.assertEquals(this.structInformation.getId(), 1);
    }



    @Test
    void setStructInformation() {
        this.structInformation.setStructuredText("waren Pizza essen");
        Assertions.assertEquals(this.structInformation.getStructuredText(), "waren Pizza essen");
    }

    @Test
    void setStars() {
        this.structInformation.setStars(4);
        Assertions.assertEquals(this.structInformation.getStars(), 4);
    }

    @Test
    void setCategory() {
        this.structInformation.setCategory("Restaurant");
        Assertions.assertEquals(this.structInformation.getCategory(), "Restaurant");
    }
}
