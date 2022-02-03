package com.classroom.telus.international.dsu.serverxml;

import java.net.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author geovanni.santos01
 */
public class Server {

    private Socket socket = null;
    private ServerSocket server = null;
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("=========Server Socket===========");
            System.out.println("Enter the port to be opened [1000 - 8000]: ");
            String inputMessage = br.readLine();
            int port = Integer.parseInt(inputMessage);

            Server server = new Server(port);

        } catch (Exception e) {
            System.out.println(e.toString());

        }
    }
/**
 * this Method create and deployed the Server Socket
 * @param port the listener port
 * 
 */
    public Server(int port) {

        try {

            //Start Server and Waits for a connection
            server = new ServerSocket(port);
            System.out.println("========== Server started on port:" + port + " ==========");
            System.out.println(" Waiting for a Client... ");
            socket = server.accept();
            System.out.println("===== Client accepted ======");

            //takes input/out from the client socket
            dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String xmlDocument = "test.xml";
            String message = "";
            String xmlOption = "";

            while (!message.equals("Stop")) {
                try {
                    //first we recive de option 1 receive a xmlfile, 2 receive a text as xml file, other to exit
                    xmlOption = dataInputStream.readUTF();
                    System.out.println("From client:\t" + xmlOption);

                    if (xmlOption.equals("1")) {
                        System.out.println("===== Receiving a File======");
                        receiveFile("test.xml");
                       
                        if (xmlCheck("test.xml")) {
                            dataOutputStream.writeUTF("Ok,File received");
                            dataOutputStream.flush();
                        } else {
                            dataOutputStream.writeUTF("Error: The file is not a xml file\n\nPlease try again with an valid Option");
                            dataOutputStream.flush();
                        }

                    } else if (xmlOption.equals("2")) {
                        System.out.println("======Receiving a text as a XML=======");
                        message = dataInputStream.readUTF();
               
                        xmlTexttoFile(message, "text.xml");

                        if (xmlCheck("text.xml")) {
                            dataOutputStream.writeUTF("Ok,File received");
                            dataOutputStream.flush();
                        } else {
                            dataOutputStream.writeUTF("Error: The file is not a xml file\n\nPlease try again with an valid Option");
                            dataOutputStream.flush();
                        }

                    } else {
                        System.out.println("===== Exiting =====");
                        message = "Stop";
                        

                    }

                } catch (Exception i) {
                    System.out.println(i.toString());
                }
            }
            System.out.println("===== Closing conecction ====== ");

            //close connection
            socket.close();
            dataInputStream.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    /**
     * This method is used for to receive a xml file
     * @param fileName this must to be the full path of the file
     * @throws Exception 
     */
    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }

    /**
     * This method creates an xml file which contains all the received text
     * @param xmlText text that goes inside the file
     * @param nameFile the name of the file
     */
    private static void xmlTexttoFile(String xmlText, String nameFile) {
        try {
            // Create new file
            File file = new File(nameFile);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            } else {
                System.out.println("Eror: file already exist");
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(xmlText);
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method check if a file is xml
     * @param args this must to be xml file
     * @return 
     */
    private boolean xmlCheck(String args) {
            File file = new File(args);
            Document doc = null;

            try {

                doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                return false;

            }

            return true;
       
    }

}
