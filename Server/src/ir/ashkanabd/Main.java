package ir.ashkanabd;

import ir.ashkanabd.Request.GetInput;
import ir.ashkanabd.Request.SendScreen;
import ir.ashkanabd.connection.Server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;

public class Main {

    private static Main main;
    private Server server;
    private GetInput getInput;
    private Thread socketThread, sendThread;
    private SendScreen sendScreen;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.start();
    }

    void start() throws Exception {
        server = new Server("127.0.0.1", 52000);
        getInput = new GetInput();
        server.setOnReceive(getInput::onReceive);
        sendScreen = new SendScreen();
        socketThread = new Thread(() -> {
            try {
                server.receive();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Receive");
        socketThread.start();
        sendThread = new Thread(() -> {
            try {
                while (true) {
//                    Thread.sleep(5);
                    server.sendImage(sendScreen.getScreen());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Send");
        sendThread.start();
    }
}
