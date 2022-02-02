package com.telusinternational.google.classroom.integrations.example3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.telusinternational.google.classroom.integrations.models.Person;

public class MultiThreadedClientSocket {
	
	public static void send(int port) throws IOException, InterruptedException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer pendingResponses = 0;
		String inputName = null;
		do {
			Socket s = new Socket("localhost", port);
			//retrieve input data
			Thread.sleep(1000);
			System.out.println("=========================================");
			System.out.print("Enter your name: ");
			inputName = br.readLine();
			System.out.println("=========================================");
			System.out.print("Enter your age: ");
			int inputAge;
			try{
				inputAge = Integer.parseInt(br.readLine());
			}catch(NumberFormatException e) {
				inputAge = Integer.parseInt("-1");
			}
			//create new object to be sent
			Person person = new Person(inputName, inputAge);
			pendingResponses = pendingResponses + 1;
			ThreadClient tc = new ThreadClient(person, s, pendingResponses);
			tc.start();
		} while (!inputName.equals(""));
		
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
