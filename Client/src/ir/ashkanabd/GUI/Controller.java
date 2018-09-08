package ir.ashkanabd.GUI;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;

public class Controller {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imageView;

    public void initialize() {
        imageView.fitHeightProperty().bind(anchor.heightProperty());
        imageView.fitWidthProperty().bind(anchor.widthProperty());
    }

    void onReceive(Object obj) {
        BufferedImage img = (BufferedImage) obj;
        imageView.setImage(SwingFXUtils.toFXImage(img, null));
    }
}
