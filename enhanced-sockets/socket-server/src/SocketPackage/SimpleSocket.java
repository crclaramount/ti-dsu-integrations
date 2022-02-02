package SocketPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocket {

    private static String socketName = SimpleSocket.class.getSimpleName();

    /**
     * This method is meant to receive a port number, open the port in the server and listen for incoming connections
     *
     * @param port The port number from the server which we desire to open
     * @throws IOException
     */
    public static Student handleConnections(int port) throws IOException, ClassNotFoundException {

        Main.print(socketName, "Socket.Server[bind]");

        ServerSocket serverSocket = new ServerSocket(port);
        Main.print(socketName, "Socket.Server[listen] [port: " + port + "]");

        // This line of code will stay locked until a request is received
        Socket socket = serverSocket.accept();
        // If we reach this line of code, someone established a session with us
        Main.print(socketName, "Socket.Server[accept] Session Connection Received!");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        // Receiving student
        Student newStudent = (Student) objectInputStream.readObject();
        Main.print(socketName, "Socket.Server[read]: " + newStudent.toString() + "");

        // If you have any business logic should go here
        Main.print(socketName, "Socket.Server[business-logic]: Processing Message...");

        Main.print(socketName, "Socket.Server[write]");
        Main.print(socketName, "<" + newStudent.getName() + ">");

        objectOutputStream.writeObject(newStudent);
        objectOutputStream.flush();

        Main.print(socketName, "Socket.Server[close]");
        objectInputStream.close();
        socket.close();
        serverSocket.close();
        return newStudent;
    }

    public static Student execute(int port) {
        Main.print(socketName, "Starting Server");
        try {
            Student student = SimpleSocket.handleConnections(port);
            Main.print(socketName, "Terminating Server");
            return student;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Student();
    }
}
