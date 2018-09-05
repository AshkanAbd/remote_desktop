package ir.ashkanabd.connection;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Scanner;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private Socket client;
    private InputStream inStream;
    private OutputStream outStream;
    private OnReceive onReceive;
    private PrintWriter pw;
    public static String mouseType = "m";
    public static String keyType = "k";

    public Server(int port) throws Exception {
        serverSocket = new ServerSocket(port);
    }

    public void acceptClient() throws Exception {
        client = serverSocket.accept();
        client.setKeepAlive(true);
        outStream = client.getOutputStream();
        inStream = client.getInputStream();
        pw = new PrintWriter(outStream, true);
    }

    public void sendKey(Object obj, String type) throws Exception {
        Object objects[] = new Object[]{obj, type};
        send(objects);
    }

    public void sendMouse(Object obj, double sceneX, double sceneY, String type) throws Exception {
        Object objects[] = new Object[]{obj, type, sceneX, sceneY};
        send(objects);
    }

    private void send(Object obj) throws Exception {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArr);
        oos.writeObject(obj);
        oos.flush();
        byte buff[] = byteArr.toByteArray();
        pw.println(Base64.getEncoder().encodeToString(buff));
    }

    public void receiveDifference() throws Exception {
        Scanner scn = new Scanner(inStream);
        ObjectInputStream ois;
        ByteArrayInputStream byteArr;
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            byte buf[] = Base64.getDecoder().decode(line);
            byteArr = new ByteArrayInputStream(buf);
            ois = new ObjectInputStream(byteArr);
            onReceive.onReceive(ois.readObject());
        }
    }

    public void receive() throws Exception {
        Scanner scn = new Scanner(inStream);
        while (scn.hasNextLine()) {
            String line = scn.nextLine();
            byte buff[] = Base64.getDecoder().decode(line);
            onReceive.onReceive(ImageIO.read(
                    new ByteArrayInputStream(buff)));
        }
    }

    public void setOnReceive(OnReceive onReceive) {
        this.onReceive = onReceive;
    }
}
