package at.jku.se.diary.model;

import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *
 * this class contains important functions to get the lat and lng for a location, but it also works the other way
 * @author Team E
 *
 */

public class Map {

    Diary diary;
    private ArrayList<MarkerPoint> markerPointArrayList = new ArrayList<>();

    /**
     * @param diary to get the correct diary entries for the map
     */
    public Map(Diary diary) {
        this.diary = diary;
    }

    /**
     * @return a list of markers, which are elements of the GoogleMapView and contain the position of the marker
     */
    public ArrayList<Marker> getMarker() {
        ArrayList<DiaryEntry> entries = this.diary.getEntryList();
        ArrayList<Marker> markers = new ArrayList<>();
        for (DiaryEntry e : entries) {
            if (e.getAddress() != null) {
                MarkerPoint m = this.getDataFromAPI(e.getAddress(), e.getId());
                Marker pos = new Marker(new MarkerOptions().position(new LatLong(m.getLatitute(),
                        m.getLongitute())));
                markers.add(pos);
            }
        }
        return markers;
    }

    /**
     * @param address to get the latitute and longitute for the address
     * @param index set the index of the markerpoint to the id of the diary entry
     * @return a markerpoint, with the correct latitute and longitute of the location
     * and has also the id of the matching diary entry
     */
    public MarkerPoint getDataFromAPI(String address, Integer index) {
        address = address.replace(" ", "");
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("https://maps.googleapis.com/maps/api/" + "" +
                    "geocode/json?address=" + address + "&key=AIzaSyClUxzPhXoJKME9PHBo1wH-HBYej7901dM").asJson();
            JSONObject myObj = apiResponse.getBody().getObject();
            JSONObject results = null;
            JSONArray resultsArray = myObj.getJSONArray("results");
            results = resultsArray.getJSONObject(0);

            JSONObject geometry = results.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            MarkerPoint m = new MarkerPoint(index, address,
                    location.getDouble("lat"), location.getDouble("lng"));
            markerPointArrayList.add(m);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param lat to get the matching markerpoint
     * @param lng  to get the matching markerpoint
     * @return a diary entry, which is matching with the location of lat and lng. If no Entry matches,
     * the method will return null
     */
    public DiaryEntry getEntryFromLatLng(double lat, double lng) {
        MarkerPoint markerp;
        for(MarkerPoint mp : markerPointArrayList) {
            if(mp.getLatitute() == lat && mp.getLongitute() == lng){
                markerp = mp;
                for(DiaryEntry e: diary.getEntryList() ) {
                    if(e.getId() == markerp.getId()) {
                        return e;
                    }
                }
            }
        }
        return null;
    }


}

