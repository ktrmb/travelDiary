package at.jku.se.diary.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.SwingUtilities;

/**
 *
 * this class enables to enlarge images
 * @author Team E
 *
 */
public class EnlargedPictureController {
    private String picture;
    @FXML
    private ImageView enlargedPicture;
    @FXML
    private Button btnRotate;

    /**
     * to set the selected picture
     * @param picture which was selected
     */
    public void setSelectedEntry (String picture) {
        this.picture = picture;
    }

    /**
     * loads the picture
     */
    public void initialize() {
        SwingUtilities.invokeLater(() -> setEntry(picture));
    }

    /**
     * to show the selected picture on the imageView
     * @param picture which was selected
     */
    public void setEntry (String picture) {
        this.enlargedPicture.setImage(new Image("file:src/pictures/"+picture));
    }

    /**
     * to rotate the picture in the imageView
     * @param event clicked to rotate the picture
     */
    @FXML
    void rotatePic(ActionEvent event){
        enlargedPicture.setRotate(enlargedPicture.getRotate()+90);
    }
}
