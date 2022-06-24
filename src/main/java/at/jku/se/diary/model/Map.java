package at.jku.se.diary.model;

import java.util.ArrayList;

import com.dlsc.gmapsfx.javascript.object.LatLong;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Map {

    Diary diary;
    private GeocodingService geocodingService;

    public Map(Diary diary) {
        this.diary = diary;

    }
/*
    public ArrayList<Marker> getMarker() {
        ArrayList<DiaryEntry> entries = this.diary.getEntryList();
        ArrayList<Marker> markers = new ArrayList<>();

        for (DiaryEntry e : entries) {
            if (e.getAddress() != null) {
                MarkerPoint m = this.getDataFromAPI(e.getAddress());
                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(new LatLong(m.getLatitute(), m.getLongitute()));
                Marker pos = new Marker(markerOptions1);
                markers.add(pos);
            }
        }
        return markers;
    } */


    public MarkerPoint getDataFromAPI(String address) {
        System.out.println("before:" + address);
        address = address.replace(" ", "");
        System.out.println("after:" + address);
        try {
            HttpResponse<JsonNode> apiResponse = Unirest.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyClUxzPhXoJKME9PHBo1wH-HBYej7901dM").asJson();
            JSONObject myObj = apiResponse.getBody().getObject();
            JSONObject results = null;
            try {
                JSONArray resultsArray = myObj.getJSONArray("results");
                results = resultsArray.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(results == null) throw new IllegalArgumentException("Bad Request");
            JSONObject geometry = results.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            MarkerPoint m = new MarkerPoint(address, location.getDouble("lat"), location.getDouble("lng"));
            return m;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

