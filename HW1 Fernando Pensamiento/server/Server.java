
/*
    Code made by 
    https://heptadecane.medium.com/file-transfer-via-java-sockets-e8d4f30703a5
*/
import java.io.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) {
        socketClient(5000);
    }

    private static void socketClient(int port) {

        LectorXML lXMl = new LectorXML();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("listening to port: " + port);
            System.out.println("Select an option: ");
            System.out.println("1) Receive a XML file ");
            System.out.println("2) receive a text as a XML ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String decition = br.readLine();



            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

        
            String newFile = "test.xml";
            boolean recFile = decition.equals("1");
            do {
                if (recFile) {
                    receiveFile(newFile);
                } else {
                    String xmlText = dataInputStream.readUTF();
                    createFile(xmlText, newFile);
                }
                if (lXMl.esXML(newFile)) {
                    dataOutputStream.writeUTF("ok");
                    dataOutputStream.flush();
                    break;
                }
                dataOutputStream.writeUTF("The file is not a xml file");
                dataOutputStream.flush();
            } while (true);
            dataInputStream.close();
            dataOutputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFile(String xmlText, String stFile) {
        try{
            // Create new file
            File file = new File(stFile);

            // If file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }else{
                
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Write in file
            bw.write(xmlText);

            // Close connection
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong(); // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
        }
        fileOutputStream.close();
    }
}