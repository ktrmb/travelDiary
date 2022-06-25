package at.jku.se.diary.model;

public class MarkerPoint {
    private double longitute;
    private double latitute;
    String address;

    public MarkerPoint(String address, double latitute, double longitute) {
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
}
