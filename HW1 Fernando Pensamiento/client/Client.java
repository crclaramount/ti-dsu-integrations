
/**
 * Code made by 
 * https://heptadecane.medium.com/file-transfer-via-java-sockets-e8d4f30703a5
 */

import java.io.*;
import java.net.Socket;
import java.io.BufferedReader;

public class Client {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (Socket socket = new Socket("localhost", 5000)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Select an option: ");
            System.out.println("1) Send a XML file ");
            System.out.println("2) Send text as a XML ");
            String decition = br.readLine();

            if (decition.equals("1")) {
                String receivedMessage = "";
                do {
                    System.out.print("Insert name of the xml file: ");
                    String fileName = br.readLine();
                   // dataOutputStream.writeUTF(fileName);

                    if (!sendFile(fileName)) {
                        continue;
                    }
                    receivedMessage = dataInputStream.readUTF();
                    System.out.println("Server says: " + receivedMessage);
                } while (!receivedMessage.equalsIgnoreCase("ok"));
            } else {
                String receivedMessage = "";
                do {
                    System.out.print("Insert the xml as a string: ");
                    String xmlString = br.readLine();
                    dataOutputStream.writeUTF(xmlString);
                    receivedMessage = dataInputStream.readUTF();
                    System.out.println("Server says: " + receivedMessage);
                } while (!receivedMessage.equalsIgnoreCase("ok"));
            }

            dataInputStream.close();
            dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            // send file size
            dataOutputStream.writeLong(file.length());
            // break file into chunks
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();
            }
            fileInputStream.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}