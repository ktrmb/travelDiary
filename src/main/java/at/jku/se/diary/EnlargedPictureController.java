package at.jku.se.diary;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

public class EnlargedPictureController {
    private DiaryEntry entry;
    private Diary diary = HelloFX.diary;
    private Stage stage;
    private String picture;

    @FXML
    private ImageView enlargedPicture;


    public void setSelectedEntry (String picture) {
        this.picture = picture;
    }

    public void initialize() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setEntry(picture);
            }
        });
    }
    public void setEntry (String picture) {
        Image image1 = new Image("file:src/pictures/"+picture);
        this.enlargedPicture.setImage(image1);

    }

}
