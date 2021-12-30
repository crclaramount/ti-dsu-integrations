package com.telusinternational.google.classroom.integrations.example1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.telusinternational.google.classroom.integrations.example2.ContinuousClientSocket;
import com.telusinternational.google.classroom.integrations.example3.MultiThreadedClientSocket;

public class SimpleClientSocket {
	
	public static void send(int port) throws UnknownHostException, IOException {
		Socket s = new Socket("localhost",port);
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("=========================================");
		System.out.print("Enter your message: ");
		String inputMessage = br.readLine();
		dout.writeUTF(inputMessage);
		dout.flush();
		String receivedMessage = din.readUTF();
		System.out.println("Server says: " + receivedMessage);
		System.out.println("=========================================");
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
			SimpleClientSocket.send(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("=========================================");
		System.out.println("Terminating Client");
		System.out.println("=========================================");
	}
	
}
