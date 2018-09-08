package ir.ashkanabd.connection;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class MouseInfo implements Serializable {

    private double sceneX, sceneY;
    private String eventType;
    private MouseButton button;

    public MouseInfo(MouseEvent mouseEvent) {
        sceneX = mouseEvent.getSceneX();
        sceneY = mouseEvent.getSceneY();
        eventType = mouseEvent.getEventType().getName();
        button = mouseEvent.getButton();
    }

    public double getSceneY() {
        return sceneY;
    }

    public double getSceneX() {
        return sceneX;
    }

    public String getEventType() {
        return eventType;
    }

    public MouseButton getButton() {
        return button;
    }
}
