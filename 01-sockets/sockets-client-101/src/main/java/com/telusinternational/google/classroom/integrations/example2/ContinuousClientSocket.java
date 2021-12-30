package com.telusinternational.google.classroom.integrations.example2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.telusinternational.google.classroom.integrations.example1.SimpleClientSocket;
import com.telusinternational.google.classroom.integrations.example3.MultiThreadedClientSocket;

public class ContinuousClientSocket {

	public static void send(int port) throws UnknownHostException, IOException {
		String inputMessage = "",receivedMessage = "";
		while(!inputMessage.equals("TERMINATE")){
			Socket s = new Socket("localhost",port);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			DataInputStream din = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			System.out.println("=========================================");
			
			System.out.print("Enter your message: ");
			inputMessage = br.readLine();
			dout.writeUTF(inputMessage);
			dout.flush();
			receivedMessage = din.readUTF();
			System.out.println("Server says: " + receivedMessage);
			System.out.println("=========================================");
			din.close();
			dout.close();
			s.close();
		}
	}
	
	public static void main(String[] args) {
		int serverPort = 6000;
		if(args.length > 0) {
			serverPort = Integer.parseInt(args[0]);
		}
		System.out.println("=========================================");
		System.out.println("Starting Client");
		System.out.println("=========================================");
		try {
			ContinuousClientSocket.send(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("=========================================");
		System.out.println("Terminating Client");
		System.out.println("=========================================");
	}
	
}
