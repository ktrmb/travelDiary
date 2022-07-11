package at.jku.se.diary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;



public class EnlargedPictureController {
    private String picture;

    @FXML
    private ImageView enlargedPicture;

    @FXML
    private Button btnRotate;

    public void setSelectedEntry (String picture) {
        this.picture = picture;
    }

    public void initialize() {
        SwingUtilities.invokeLater(() -> setEntry(picture));
    }
    public void setEntry (String picture) {
        this.enlargedPicture.setImage(new Image("file:src/pictures/"+picture));
    }

    @FXML
    void rotatePic(ActionEvent event){
        enlargedPicture.setRotate(enlargedPicture.getRotate()+90);
    }
}
