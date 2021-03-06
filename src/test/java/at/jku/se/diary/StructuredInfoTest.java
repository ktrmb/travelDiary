package at.jku.se.diary;

import at.jku.se.diary.model.StructInformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * this class is for testing the Structured Info class
 * @author Team E
 *
 */
public class StructuredInfoTest {

    String category = "Restaurant";
    StructInformation structInformation;
    StructInformation structInformation1;

    /**
     * Creating two new structured infos before each test
     */
    @BeforeEach
    void setUp(){
        structInformation = new StructInformation();
        structInformation1 = new StructInformation(0, "Kino", 4.0, "Film war gut");
    }

    /**
     * Test for setting the structured information id
     */
    @Test
    void setId() {
        this.structInformation.setId(1);
        Assertions.assertEquals(this.structInformation.getId(), 1);
    }


    /**
     * Test checks if the text of a structured Info is set
     */
    @Test
    void setStructInformation() {
        this.structInformation.setStructuredText("waren Pizza essen");
        Assertions.assertEquals(this.structInformation.getStructuredText(), "waren Pizza essen");
    }

    /**
     * Test checks if the rating of a structured Info is set
     */
    @Test
    void setStars() {
        this.structInformation.setStars(4);
        Assertions.assertEquals(this.structInformation.getStars(), 4);
    }

    /**
     * Test checks if the category of a structured Info is set
     */
    @Test
    void setCategory() {
        this.structInformation.setCategory(category);
        Assertions.assertEquals(this.structInformation.getCategory(), category);
    }
}
