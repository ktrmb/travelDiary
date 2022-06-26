package at.jku.se.diary.model;

public class MarkerPoint {
    private double longitute;
    private double latitute;
    String address;
    int id;

    public MarkerPoint() {
    }

    public MarkerPoint(Integer diaryEntryId, String address, double latitute, double longitute) {
        this.id = diaryEntryId;
        this.address = address;
        this.latitute = latitute;
        this.longitute = longitute;
    }

    public double getLongitute() {
        return this.longitute;
    }

    public String getAddress() {
        return this.address;
    }

    public double getLatitute() {
        return this.latitute;
    }

    public int getId() {
        return id;
    }


}
