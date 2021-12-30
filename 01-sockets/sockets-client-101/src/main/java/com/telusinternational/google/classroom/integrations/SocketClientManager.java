package com.telusinternational.google.classroom.integrations;

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
import com.telusinternational.google.classroom.integrations.example3.MultiThreadedClientSocket;

public class SocketClientManager {
	
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("=========CLIENT SOCKET MANAGER===========");
			System.out.print("Enter the port of the server [1000 - 8000]: ");
			String inputMessage = br.readLine();
			int serverPort = Integer.parseInt(inputMessage);
			System.out.println("=========================================");
			System.out.println("Enter your desired mode of operation: ");
			System.out.println(" [1] Simple Socket (one message sent and closes the connection).");
			System.out.println(" [2] Continuous Socket (one message after another being sent in sequence).");
			System.out.println(" [3] Concurrent Socket (one message after another sent in parallel threads).");
			System.out.println("=========================================");
			System.out.print("Enter your option: ");
		
			inputMessage = br.readLine();
			int option = Integer.parseInt(inputMessage);
			System.out.println("=========================================");
			System.out.print("Option Received: " + inputMessage + " ");
			
			switch (option) {
				case 1:
					System.out.println("(Simple Socket)");
					System.out.println("=========================================");
					SimpleClientSocket.send(serverPort);
					break;
				case 2:
					System.out.println("(Continuous Socket)");
					System.out.println("=========================================");
					ContinuousClientSocket.send(serverPort);
					break;
				case 3:
					System.out.println("(Concurrent Socket)");
					System.out.println("=========================================");
					MultiThreadedClientSocket.send(serverPort);
					break;
				default:
					System.out.println("- No configuration found for option " + option);
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
