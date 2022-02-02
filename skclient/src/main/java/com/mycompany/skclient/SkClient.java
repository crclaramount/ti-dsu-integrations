package com.mycompany.skclient;
import java.net.*;
import java.io.*;

import java.io.Serializable;

import java.net.*;
import java.io.*;



public class SkClient {

    private Socket socket;
    private OutputStream output = null;

    public SkClient(String address, int port, Object Obj) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            output = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(output);
            objectOutputStream.writeObject(Obj);
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            output.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
