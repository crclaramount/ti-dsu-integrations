package com.classroom.telus.international.dsu.clientxml;

import java.net.*;
import java.io.*;

/**
 *
 * @author geovanni.santos01
 */
public class Client {

    //initialize socket and input/output streams
    private Socket socket = null;
    private DataInputStream dataInputStream = null;
    private static DataOutputStream dataOutputStream = null;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("=========Client Socket===========");
            System.out.println("Enter the port to be opened [1000 - 8000]: ");
            String inputMessage = br.readLine();
            int port = Integer.parseInt(inputMessage);

            Client client = new Client("localhost", port);

        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }

    /**
     * constructor to put IP address and port
     * @param address this is an ip address of the server,  "localhost" could be right
     * @param port the port where a server is listening
     */
    public Client(String address, int port) {

        //stablish a connection
        try {
            socket = new Socket(address, port);
            System.out.println("===== Connected successfully =====");

            //takes input from terminal
            dataInputStream = new DataInputStream(socket.getInputStream());
            //sends output to the socket
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String message = "";
            String xmlOption = "";
            String serverAnsw = "";

            while (!message.equals("Stop")) {
                try {
                    // Menu for choose if a xml file is send or a text with xml format                    
                    System.out.println("Select an option\n1-Send a XML file\n2-Send a text as a XML\nOther to exit");
                    xmlOption = br.readLine();
                    dataOutputStream.writeUTF(xmlOption);

                    if (xmlOption.equals("1")) {
                        System.out.println("=====Send a File Option======");
                        System.out.println("Insert the path of the XML file:");
                        message = br.readLine();
                        sendFile(message);
                        serverAnsw = dataInputStream.readUTF();
                        System.out.println("Server response:\t" + serverAnsw);

                    } else if (xmlOption.equals("2")) {
                        System.out.println("======Send a text as a XML=======");
                        System.out.println("Write a message to Send:");
                        message = br.readLine();
                        dataOutputStream.writeUTF(message);
                        serverAnsw = dataInputStream.readUTF();
                        System.out.println("Server response:\t" + serverAnsw);

                    } else {
                        message = "Stop";
                        System.out.println("====== Saliendo  ===========");

                    }

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }

            }

            dataInputStream.close();
            dataOutputStream.close();
            socket.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    
    /**
     * this method send a xml file to the server
     * @param path the FULL path of the file
     * @throws Exception 
     */
    private static void sendFile(String path) throws Exception{
        int bytes = 0;
        File file = new File(path);
        
        try{
        FileInputStream fileInputStream = new FileInputStream(file);
        
        // send file size
        dataOutputStream.writeLong(file.length());  
        // break file into chunks
        byte[] buffer = new byte[4*1024];
        while ((bytes=fileInputStream.read(buffer))!=-1){
            dataOutputStream.write(buffer,0,bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
}
