package ir.ashkanabd.GUI;

import ir.ashkanabd.connection.ImageInfo;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.image.BufferedImage;
import java.util.List;

public class Controller {

    @FXML
    private AnchorPane anchor;
    @FXML
    private ImageView imageView;
    private BufferedImage img = null;

    public void initialize() {
        imageView.fitHeightProperty().bind(anchor.heightProperty());
        imageView.fitWidthProperty().bind(anchor.widthProperty());
    }

    void onReceive(Object obj) {
//        BufferedImage img = (BufferedImage) obj;
        List<ImageInfo> difference = (List<ImageInfo>) obj;
        if (img == null){
            img = new BufferedImage(480,320,BufferedImage.TYPE_INT_RGB);
        }
        for (ImageInfo info : difference){
            img.setRGB(info.getWidth(),info.getHeight(),info.getRgb());
        }
        imageView.setImage(SwingFXUtils.toFXImage(img, null));
    }
}
