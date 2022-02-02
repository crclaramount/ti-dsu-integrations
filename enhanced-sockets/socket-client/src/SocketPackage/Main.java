package SocketPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("CLIENT SOCKET MANAGER");
            System.out.print("Enter the port of the server [1000 - 8000]: ");
            String inputMessage = br.readLine();
            int serverPort = Integer.parseInt(inputMessage);
            SimpleClientSocket.send(serverPort);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
