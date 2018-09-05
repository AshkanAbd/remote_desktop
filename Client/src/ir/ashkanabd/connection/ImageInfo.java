package ir.ashkanabd.connection;

import java.io.Serializable;

public class ImageInfo implements Serializable {
    private int height, width, rgb;

    public ImageInfo(int width, int height, int rgb) {
        this.height = height;
        this.width = width;
        this.rgb = rgb;
    }

    public int getRgb() {
        return rgb;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}