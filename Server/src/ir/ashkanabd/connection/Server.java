package ir.ashkanabd.connection;

import ir.ashkanabd.Request.SendScreen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

public class Server {
    private Socket client;
    private InputStream inStream;
    private OutputStream outStream;
    private PrintWriter pw;
    private OnReceive onReceive;

    public static String keyType = "k";
    public static String mouseType = "m";

    public Server(String ip, int port) throws Exception {
        client = new Socket(ip, port);
        client.setKeepAlive(true);
        outStream = client.getOutputStream();
        inStream = client.getInputStream();
        pw = new PrintWriter(outStream,true);
    }

    public void sendImage(List<ImageInfo> difference) throws Exception{
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(byteArr);
        oos.writeObject(difference);
        pw.println(Base64.getEncoder().encodeToString(byteArr.toByteArray()));
//        pw.flush();
    }

    public void sendImage(BufferedImage image) throws Exception {
        ByteArrayOutputStream byteArr = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArr);
        String line = Base64.getEncoder().encodeToString(byteArr.toByteArray());
        pw.println(line);
    }

    public void receive() throws Exception {
        Scanner scn = new Scanner(inStream);
        ByteArrayInputStream byteArr;
        ObjectInputStream ois;
        while (scn.hasNextLine()) {
            byte buff[] = Base64.getDecoder().decode(scn.nextLine());
            byteArr = new ByteArrayInputStream(buff);
            ois = new ObjectInputStream(byteArr);
            onReceive.onReceive(ois.readObject());
        }
    }

    public void setOnReceive(OnReceive onReceive) {
        this.onReceive = onReceive;
    }
}
