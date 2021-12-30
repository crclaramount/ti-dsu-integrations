package com.telusinternational.google.classroom.integrations.example3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadSocket extends Thread {

	private Socket s = null;
	private MultiThreadedSocket handler;
	private String id;
	
	public ThreadSocket(Socket s, MultiThreadedSocket handler, String id) {
		this.s = s;
		this.handler = handler;
		this.id = id;
	}

	private void print(String message) {
		System.out.println("["+id+"] [" + new java.util.Date().toLocaleString() + "] [" + ThreadSocket.class.getSimpleName() + "]: " + message);
	}
	
	@Override
	public void run() {
		try {
			// Now we read the content of the message received as a bytes stream
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String message = (String) dis.readUTF();
			print("Socket.Server[read]");
			print("<" + message + ">");
			this.handler.message = message;
			// If you have any business logic should go here
			print("Socket.Server[business-logic]: Processing Message...");
			
			// If you have any business logic should go here
			
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			String response = "Your message has been processed";
			print("Socket.Server[write]");
			print("<"+response+">");
			dout.writeUTF("["+ this.id + "] [" + ThreadSocket.class.getSimpleName() + "] " + response);
			dout.flush();
			
			print("Socket.Server[close]");
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
