package com.telusinternational.google.classroom.integrations;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.telusinternational.google.classroom.integrations.example1.SimpleSocket;
import com.telusinternational.google.classroom.integrations.example2.ContinuousSocket;
import com.telusinternational.google.classroom.integrations.example3.MultiThreadedSocket;
import com.telusinternational.google.classroom.integrations.example4.MultiThreadedRetainedSocket;

public class SocketManager {

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Incorrect number of parameters received. Expected Scenario [0]: SimpleSocket, "
					+ "[1]: ContinuousSocket, [2]: MultiThreadSocket");
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("=========SERVER SOCKET MANAGER===========");
			System.out.print("Enter the port to be opened [1000 - 8000]: ");
			String inputMessage = br.readLine();
			int port = Integer.parseInt(inputMessage);
			System.out.println("=========================================");
			System.out.println("Enter your desired mode of operation: ");
			System.out.println(" [1] Simple Socket (one message received and closes the connection).");
			System.out.println(" [2] Continuous Socket (one message after another being processed in sequence).");
			System.out.println(" [3] Concurrent Socket (one message after another processed in parallel threads).");
			System.out.println(" [4] Concurrent Retained Socket (one message after another processed in parallel threads, kept alive until the client asks to kill the session).");
			System.out.println("=========================================");
			System.out.print("Enter your option: ");
			inputMessage = br.readLine();
			int option = Integer.parseInt(inputMessage);
			System.out.println("=========================================");
			System.out.print("Option Received: " + inputMessage + " ");
			switch(option) {
				case 1:
					System.out.println("(Simple Socket)");
					System.out.println("=========================================");
					SimpleSocket.execute(port);
					break;
				case 2:
					System.out.println("(Continuous Socket)");
					System.out.println("=========================================");
					ContinuousSocket.execute(port);
					break;
				case 3:
					System.out.println("(Concurrent Socket)");
					System.out.println("=========================================");
					MultiThreadedSocket.execute(port);
					break;
				case 4:
					System.out.println("(Concurrent Retained Socket)");
					System.out.println("=========================================");
					MultiThreadedRetainedSocket.execute(port);
					break;
				default:
					System.out.println("- No configuration found for option " + option);
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
