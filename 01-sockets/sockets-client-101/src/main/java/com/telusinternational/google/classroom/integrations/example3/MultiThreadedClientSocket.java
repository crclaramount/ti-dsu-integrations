package com.telusinternational.google.classroom.integrations.example3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.telusinternational.google.classroom.integrations.example1.SimpleClientSocket;
import com.telusinternational.google.classroom.integrations.example2.ContinuousClientSocket;

public class MultiThreadedClientSocket {
	
	public static void send(int port) throws IOException, InterruptedException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer pendingResponses = 0;
		String inputMessage = "";
		
		while(!inputMessage.equals("TERMINATE")){
			Socket s = new Socket("localhost", port);
			System.out.println("[MAIN] =========================================");
			System.out.print("[MAIN] Enter your message: ");
			inputMessage = br.readLine();
			pendingResponses = pendingResponses + 1;
			ThreadClient tc = new ThreadClient(inputMessage, s, pendingResponses);
			tc.start();
		}
		System.out.println("[MAIN] Terminating...");
		br.close();
		System.out.println("[MAIN] Closing connections");
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
			MultiThreadedClientSocket.send(serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=========================================");
		System.out.println("Terminating Client");
		System.out.println("=========================================");
	}
	
}
