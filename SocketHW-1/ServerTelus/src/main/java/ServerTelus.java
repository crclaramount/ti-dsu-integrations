import java.io.*;
import java.net.*;
import java.util.Random;

public class ServerTelus {
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;

    public static void main(String[] args) throws IOException {
        final String dir = System.getProperty("user.dir");
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
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("listening to port:" + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket + " connected.");
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            InputStream in = clientSocket.getInputStream();
            DataInputStream clientData = new DataInputStream(in);
            String fileName = clientData.readUTF();
            String ext = getExtension(fileName);
            System.out.println(ext);
            receiveFile(dir + "\\files\\" + getSaltString() + "." + ext);

            clientSocket.close();
            dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    public static String getExtension(String file) {
        String extension = "";

        int i = file.lastIndexOf('.');
        int p = Math.max(file.lastIndexOf('/'), file.lastIndexOf('\\'));

        if (i > p) {
            extension = file.substring(i + 1);
        }

        return extension;
    }

    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
