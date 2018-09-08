package ir.ashkanabd.Request;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;

public class SendScreen {

    private Robot robot;
    private Rectangle rectangle;

    public SendScreen() throws Exception {
        robot = new Robot();
        rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }

    public BufferedImage getScreen() {
        BufferedImage img = robot.createScreenCapture(rectangle);
        int newW = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
        int newH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
        Image tmp = img.getScaledInstance(newW,newH,Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW,newH,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp,0,0,null);
        g2d.dispose();
        return dimg;
    }
}
