package com.telusinternational.google.classroom.integrations.example2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.telusinternational.google.classroom.integrations.models.Person;

public class ContinuousClientSocket {

	public static void send(int port) throws UnknownHostException, IOException {
		//String inputMessage = "",receivedMessage = "";
		String inputName = null;
		do {
			Socket s = new Socket("localhost",port);
			DataInputStream din = new DataInputStream(s.getInputStream());
			ObjectOutputStream dout = new ObjectOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			//retrieve input data
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
			dout.writeObject(person);
			dout.flush();
			//server response
			String receivedMessage = din.readUTF();
			System.out.println("Server says: " + receivedMessage);
			System.out.println("=========================================");
			din.close();
			dout.close();
			s.close();
		} while (!inputName.equals(""));
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
