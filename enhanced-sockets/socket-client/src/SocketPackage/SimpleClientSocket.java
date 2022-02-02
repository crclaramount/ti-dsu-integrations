package SocketPackage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClientSocket {

    public static void send(int port) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", port);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter student name: ");
        String name = br.readLine();
        System.out.println("Enter student lastname: ");
        String lastname = br.readLine();
        System.out.println("Enter student age: ");
        String inputMessage = br.readLine();
        int age = Integer.parseInt(inputMessage);

        Student newStudent = new Student(name, lastname, age);
        objectOutputStream.writeObject(newStudent);
        objectOutputStream.flush();
        System.out.println("STUDENT SENT");

        Student returnMessage = (Student) objectInputStream.readObject();
        System.out.println("Return Message is=" + returnMessage.toString());
        socket.close();
    }

}
