package at.jku.se.diary.controller;

import at.jku.se.diary.HelloFX;
import at.jku.se.diary.model.DiaryEntry;
import at.jku.se.diary.model.Map;
import com.dlsc.gmapsfx.GoogleMapView;
import com.dlsc.gmapsfx.javascript.event.UIEventType;
import com.dlsc.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import netscape.javascript.JSObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 *
 * this class creates a Map and handles all functions regarding marker and map
 * @author Team E
 *
 */
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

    Map location;

    /**
     * @param event button clicked, loads "JournalList" scene
     * @throws IOException
     */
    @FXML
    void showJournalList(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("JournalList", btnNewEntry.getScene());
        s.switchScene();
    }

    /**
     * @param event button clicked, loads "NewEntry" scene
     * @throws IOException
     */
    @FXML
    void showNewEntryPage(MouseEvent event) throws IOException {
        SceneSwitch s = new SceneSwitch("DiaryEntryView", btnNewEntry.getScene());
        s.switchScene();
    }

    /**
     * creates a Map
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      location = new Map(HelloFX.diary);

        mapView.addMapInitializedListener(() -> configureMap());
    }


    private void configureMap() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(41.890251, 12.492373))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(9);
        map = mapView.createMap(mapOptions, false);
        map.setZoom(3);

        ArrayList<Marker> markers = location.getMarker();
        map.addMarkers((Collection<Marker>) markers);

        for (Marker m : markers) {
            map.addUIEventHandler(m, UIEventType.click, (JSObject obj) -> {
                LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
                System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
                DiaryEntry entry = location.getEntryFromLatLng(ll.getLatitude(), ll.getLongitude());
                this.showSelectedEntry(entry);
            });
        }

    }

    /**
     * switches to the Edit Entry Page with the entry of which marker was clicked
     * @param entry diary entry
     */
    void showSelectedEntry(DiaryEntry entry) {
        try {
            Scene scene = btnJournalList.getScene();
            URL url = new File("src/main/java/at/jku/se/diary/view/EntryEdit.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            EntryEditController eController = loader.getController();
            eController.setSelectedEntry(entry);

            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
