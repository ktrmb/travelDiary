package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.Map;
import at.jku.se.diary.model.MarkerPoint;
import at.jku.se.diary.model.SceneSwitch;
import com.dlsc.gmapsfx.GoogleMapView;

import com.dlsc.gmapsfx.javascript.object.*;

import com.dlsc.gmapsfx.service.geocoding.GeocoderStatus;
import com.dlsc.gmapsfx.service.geocoding.GeocodingResult;
import com.dlsc.gmapsfx.service.geocoding.GeocodingService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

    @FXML
    private ImageView btnJournalList;

    @FXML
    private ImageView btnMap;

    @FXML
    private ImageView btnNewEntry;

    @FXML
    private GoogleMapView mapView =  new GoogleMapView("en-US", "AIzaSyClUxzPhXoJKME9PHBo1wH-HBYej7901dM");

    private GoogleMap map;

    Map location;


    @FXML
    void showMapPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("JournalList", btnNewEntry.getScene());
        s.switchScene();
    }

    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("DiaryEntryView", btnNewEntry.getScene());
        s.switchScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      location = new Map(HelloFX.diary);

        mapView.addMapInitializedListener(() -> configureMap());

    }


    public void configureMap() {
        MapOptions mapOptions = new MapOptions();


        mapOptions.center(new LatLong(41.890251, 12.492373))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(9);
        map = mapView.createMap(mapOptions, false);

        map.setZoom(3);


        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(new LatLong(41.890251, 12.492373));
        Marker m3 = new Marker(markerOptions3);
        map.addMarker( m3 );

        for(DiaryEntry m: HelloFX.diary.getEntryList()) {
            MarkerPoint mp = location.getDataFromAPI(m.getAddress());
            map.addMarker( new Marker(new MarkerOptions().position(new LatLong(mp.getLatitute(), mp.getLongitute()))));
        }
    }


}
