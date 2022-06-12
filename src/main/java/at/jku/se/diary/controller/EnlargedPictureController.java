package at.jku.se.diary.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.*;

public class EnlargedPictureController {
    private String picture;

    @FXML
    private ImageView enlargedPicture;

    public void setSelectedEntry (String picture) {
        this.picture = picture;
    }

    public void initialize() {
        SwingUtilities.invokeLater(() -> setEntry(picture));
    }
    public void setEntry (String picture) {
        Image image1 = new Image("file:src/pictures/"+picture);
        this.enlargedPicture.setImage(image1);

    }

}
