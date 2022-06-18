package at.jku.se.diary.controller;

import at.jku.se.diary.model.SceneSwitch;
import com.dlsc.gmapsfx.GoogleMapView;

import com.dlsc.gmapsfx.javascript.object.*;

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
    private GoogleMapView mapView =  new GoogleMapView("en-US", "asdfasdf-HBYej7901dM");

    private GoogleMap map;


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
        mapView.addMapInitializedListener(() -> configureMap());


    }


    public void configureMap() {
        MapOptions mapOptions = new MapOptions();


        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(9);
        map = mapView.createMap(mapOptions, false);

        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(new LatLong(47.6197, -122.3231));
        Marker m1 = new Marker(markerOptions1);
        map.addMarker( m1 );

    }
}
