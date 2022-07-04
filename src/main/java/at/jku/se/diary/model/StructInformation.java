package at.jku.se.diary.model;

/**
 *
 * this class represents the structured information, which is added to a diary entry
 * @author Team E
 *
 */

public class StructInformation {

    private int id;
    private String category;
    private double stars;
    private String structuredText;

    public StructInformation(){
    }

    /**
     *
     * @param id is the identifier for the structured information
     * @param category to tag a structured information for better strucutring
     * @param stars for the rating of the structured information
     * @param structuredText is the free text of a structured information
     */
    public StructInformation(int id, String category, double stars, String structuredText) {
        this.id = id;
        this.category = category;
        this.stars = stars;
        this.structuredText = structuredText;
    }

    /**
     * @return the identifier of the structured information
     */
    public int getId() {
        return id;
    }

    /**
     * @param id sets the identifier of the structured information
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tag of the structured information
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category sets the tag of the structured information
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the rating of the structured information
     */
    public double getStars() {
        return stars;
    }

    /**
     * @param stars sets the rating of the structured information
     */
    public void setStars(double stars) {
        this.stars = stars;
    }

    /**
     * @return the free text of the structured information
     */
    public String getStructuredText() {
        return structuredText;
    }

    /**
     * @param structuredText sets the free tesxt of the structured information
     */
    public void setStructuredText(String structuredText) {
        this.structuredText = structuredText;
    }
}
