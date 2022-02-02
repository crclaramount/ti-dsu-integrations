package com.mycompany.skserver;
import Modelin.Vehicle;
import java.net.*;
import java.io.*;

public class SkServer{

    private Socket socket = null;
    private ServerSocket server = null;
 
    public void FileStorage(Vehicle v) throws IOException {

        File file = new File("c:\\files\\Vehicle.bin");
        file.getParentFile().mkdirs(); // Will create parent directories if not exists
        file.createNewFile();
        FileOutputStream s = new FileOutputStream(file, false);

        ObjectOutputStream tmp = new ObjectOutputStream(s);
        tmp.writeObject(v);
        tmp.close();
    }

    public SkServer(int port) {

        try 
        {

            server = new ServerSocket(port);
            System.out.println("Server Started!");

            System.out.println("Waiting for Client...");
            socket = server.accept();

            System.out.println("Client Accepted!");

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            Vehicle Obj = (Vehicle) objectInputStream.readObject();

            System.out.println("Object Received!");
            FileStorage(Obj);

            Obj.ShowDemostration();

            System.out.println("Closing connection");
            socket.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
}
