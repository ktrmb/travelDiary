package at.jku.se.diary.model;

import java.util.ArrayList;
import com.dlsc.gmapsfx.javascript.object.Marker;
import com.dlsc.gmapsfx.javascript.object.MarkerOptions;
import com.dlsc.gmapsfx.service.geocoding.GeocoderStatus;
import com.dlsc.gmapsfx.service.geocoding.GeocodingResult;
import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import com.dlsc.gmapsfx.javascript.object.LatLong;
import javafx.scene.control.Alert;

public class Map {

    Diary diary;
    private GeocodingService geocodingService;

    public Map(Diary diary) {
        this.diary = diary;
    }

    public ArrayList<Marker> getMarker() {
        ArrayList<DiaryEntry> entries = this.diary.getEntryList();
        ArrayList<Marker> markers = new ArrayList<>();

        for(DiaryEntry e: entries)  {
            if(e.getAddress() != null) {
                double[] x = this.getLatLong(e.getAddress());

                MarkerOptions markerOptions1 = new MarkerOptions();
                markerOptions1.position(new LatLong(x[0], x[1]));
                Marker pos = new Marker(markerOptions1);

                markers.add(pos);
            }
        }
        return markers;
    }

    public double[] getLatLong(String location) {
        double[] coordinates = new double[0];

        geocodingService.geocode(location, (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                return;
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            System.out.print(latLong);
            coordinates[0] = latLong.getLatitude();
            coordinates[1] = latLong.getLongitude();
        });

        return coordinates;
    }
}
