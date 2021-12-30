package com.telusinternational.google.classroom.integrations.example3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * This class will serve as a simple example on how to build a Socket connection in the Server Side
 * This socket will have the ability to answer multiple sessions in parallel and not be deadlocked on one only
 * @author CCLARAMOUNT
 * Date: 2021-12-23 // DSU - System Integrations Classroom
 */
public class MultiThreadedSocket {
	
	public String message;
	
	private static void print(String message) {
		System.out.println("["+String.format("%04d" , 0)+"] [" + new java.util.Date().toLocaleString() + "] [" + 
				MultiThreadedSocket.class.getSimpleName() + "]: " + message);
	}
	
	/**
	 * This method is meant to receive a port number, open the port in the server and listen for incoming connections
	 * @param port The port number from the server which we desire to open
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void handleConnections(int port) throws IOException, InterruptedException {
		
		print("Socket.Server[bind]");
		
		ServerSocket ss = new ServerSocket(port);
		print("Socket.Server[listen] [port: "+port+"]");
		
		long threadCount = 1;
		
		String message = "";
		while(!message.equals("TERMINATE")) {
			// This line of code will stay locked until a request is received
			Socket s = ss.accept();
			// If we reach this line of code, someone established a session with us
			print("Socket.Server[accept] Session Connection Received!");
			
			DataInputStream dis = new DataInputStream(s.getInputStream());
			
			ThreadSocket ts = new ThreadSocket(s, this, String.format("%04d" , threadCount++));
			ts.start();
		}
		ss.close();
	}
	
	public static void execute(int port) {

		print("=========================================");
		print("Starting Server");
		print("=========================================");
		try {
			new MultiThreadedSocket().handleConnections(port);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		print("=========================================");
		print("Terminating Server");
		print("=========================================");
	}
}
