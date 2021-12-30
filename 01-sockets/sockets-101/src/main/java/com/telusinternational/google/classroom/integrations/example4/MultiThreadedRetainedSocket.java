package com.telusinternational.google.classroom.integrations.example4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.telusinternational.google.classroom.integrations.example4.ThreadSocket;

/***
 * This class will serve as a simple example on how to build a Socket connection in the Server Side
 * This socket will have the ability to answer multiple sessions in parallel and not be deadlocked on one only
 * @author CCLARAMOUNT
 * Date: 2021-12-23 // DSU - System Integrations Classroom
 */
public class MultiThreadedRetainedSocket {
	
	private static void print(String message) {
		System.out.println("["+String.format("%04d" , 0)+"] [" + new java.util.Date().toLocaleString() + "] [" + 
				MultiThreadedRetainedSocket.class.getSimpleName() + "]: " + message);
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
		
		String message = "";
		int threadCount = 1;
		
		while(!message.equals("TERMINATE")) {
			// This line of code will stay locked until a request is received
			Socket s = ss.accept();
			// If we reach this line of code, someone established a session with us
			print("Socket.Server[accept] Session Connection Received!");
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			message = (String) dis.readUTF();
			
			ThreadSocket ts = new ThreadSocket(s, String.format("%04d" , threadCount++));
			ts.start();
		}
		ss.close();
	}
	
	public static void execute(int port) {

		print("=========================================");
		print("Starting Server");
		print("=========================================");
		try {
			MultiThreadedRetainedSocket.handleConnections(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		print("=========================================");
		print("Terminating Server");
		print("=========================================");
	}
}
