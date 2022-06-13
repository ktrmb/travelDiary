package at.jku.se.diary.model;

public class StructInformation {

    private int id;
    private String category;
    private double stars;
    private String structuredText;

    public StructInformation(){
    }

    public StructInformation(int id, String category, double stars, String structuredText) {
        this.id = id;
        this.category = category;
        this.stars = stars;
        this.structuredText = structuredText;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getStructuredText() {
        return structuredText;
    }

    public void setStructuredText(String structuredText) {
        this.structuredText = structuredText;
    }
}
