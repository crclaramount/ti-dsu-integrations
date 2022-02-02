
import java.io.*;
import java.net.*;

public class ClientTelus {
    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputMessage;
        do {
            System.out.println("=========SERVER SOCKET MANAGER===========");
            System.out.print("Enter the port to be opened [1000 - 8000]: ");
            inputMessage = br.readLine();
            if (inputMessage.length() == 0)
                break;
        } while (Integer.parseInt(inputMessage) < 1000 || Integer.parseInt(inputMessage) > 8000);
        int port = (inputMessage.length() == 0) ? 4500 : Integer.parseInt(inputMessage);
        try (Socket socket = new Socket("localhost", port)) {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Put the path of the file you want transfer: ");
            String path = br.readLine();
            sendFile(path);

            dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        dataOutputStream.writeUTF(file.getName());
        // send file size
        dataOutputStream.writeLong(file.length());
        // break file into chunks
        byte[] buffer = new byte[4 * 1024];
        while ((bytes = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytes);
            dataOutputStream.flush();
        }
        fileInputStream.close();
    }
}