package ir.ashkanabd.Request;

import ir.ashkanabd.connection.ImageInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SendScreen {

    private Robot robot;
    private Rectangle rectangle;
    private BufferedImage preImage;

    public SendScreen() throws Exception {
        robot = new Robot();
        rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        preImage = null;
    }

    public List<ImageInfo> recordScreen() {
        return compareImage(robot.createScreenCapture(rectangle), preImage);
    }

    private List<ImageInfo> compareImage(BufferedImage newImage, BufferedImage preImage) {
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(newImage , 0,0,480,320 ,null );
        g2d.dispose();
        List<ImageInfo> difference = new ArrayList<>();
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int rgb = newImage.getRGB(i, j);
                if (preImage == null) {
                    difference.add(new ImageInfo(i, j, rgb));
                } else if (rgb != preImage.getRGB(i, j)) {
                    difference.add(new ImageInfo(i, j, rgb));
                }
            }
        }
        this.preImage = newImage;
        return difference;
    }

    public BufferedImage getScreen() {
        return (preImage = robot.createScreenCapture(rectangle));
    }
}
