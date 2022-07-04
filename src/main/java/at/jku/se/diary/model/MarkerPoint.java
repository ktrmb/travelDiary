package at.jku.se.diary.model;

/**
 *
 * this class represents a markerpoint, which holds important information about the location of a diary entry
 * @author Team E
 *
 */

public class MarkerPoint {
    private double longitute;
    private double latitute;
    String address;
    int id;

    public MarkerPoint() {
    }
    /**
     *
     * @param diaryEntryId is the identifier of the matching diary entry
     * @param address is the location of the markerpoint
     * @param latitute is the latitute of the location
     * @param longitute is the longitute of the location
     */
    public MarkerPoint(Integer diaryEntryId, String address, double latitute, double longitute) {
        this.id = diaryEntryId;
        this.address = address;
        this.latitute = latitute;
        this.longitute = longitute;
    }

    /**
     * @return the longitute of the location
     */
    public double getLongitute() {
        return this.longitute;
    }

    /**
     * @return the address/location of the markerpoint
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * @return the latitute of the location
     */
    public double getLatitute() {
        return this.latitute;
    }

    /**
     * @return the identifier of the markerpoint and matching diary entry
     */
    public int getId() {
        return id;
    }


}
