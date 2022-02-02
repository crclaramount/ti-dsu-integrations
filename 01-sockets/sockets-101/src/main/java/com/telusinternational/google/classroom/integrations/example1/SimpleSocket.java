package com.telusinternational.google.classroom.integrations.example1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.telusinternational.google.classroom.integrations.models.Person;
import com.telusinternational.google.classroom.integrations.util.InputProcessing;

/***
 * This class will serve as a simple example on how to build a Socket connection in the Server Side
 * @author CCLARAMOUNT
 * Date: 2021-12-23 // DSU - System Integrations Classroom
 */
public class SimpleSocket {
	
	private static void print(String message) {
		System.out.println("[" + new java.util.Date() + "] [" + SimpleSocket.class.getSimpleName() + "]: " + message);
	}
	
	/**
	 * This method is meant to receive a port number, open the port in the server and listen for incoming connections
	 * @param port The port number from the server which we desire to open
	 * @throws IOException
	 */
	public static void handleConnections(int port) throws IOException {
		
		print("Socket.Server[bind]");
		ServerSocket ss = new ServerSocket(port);
		print("Socket.Server[listen] [port: "+port+"]");
		
		// This line of code will stay locked until a request is received
		Socket s = ss.accept();
		
		// If we reach this line of code, someone established a session with us
		print("Socket.Server[accept] Session Connection Received!");
		
		// Now we read the content of the message received as a bytes stream
		ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
		Person person = InputProcessing.readObject(dis);
		
		print("Socket.Server[read]: <" + person.getClass().getSimpleName() + ">");
		// If you have any business logic should go here
		if(person != null) person.greet();
		InputProcessing.writeToFile(person);
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		String response = "Your object has been processed";
		print("Socket.Server[write]");
		print("<"+response+">");
		dout.writeUTF("[" + SimpleSocket.class.getSimpleName() + "] " + response);
		dout.flush();
		
		print("Socket.Server[close]");
		dis.close();
		s.close();
		ss.close();
	}
	
	public static void execute(int port) {
		
		print("=========================================");
		print("Starting Server");
		print("=========================================");
		try {
			SimpleSocket.handleConnections(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		print("=========================================");
		print("Terminating Server");
		print("=========================================");
	}
}
