package com.telusinternational.google.classroom.integrations.example4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadSocket extends Thread {

	private Socket s = null;
	private String id;
	
	public ThreadSocket(Socket s, String id) {
		this.s = s;
		this.id = id;
	}
	
	private void print(String message) {
		System.out.println("[" + id + "] [" + new java.util.Date().toLocaleString() + "] [" + ThreadSocket.class.getSimpleName() + "]: " + message);
	}
	
	@Override
	public void run() {
		try {
			// Now we read the content of the message received as a bytes stream
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int attempts = 100;
			while(attempts > 0) {
				String message = (String) dis.readUTF();
				print("Socket.Server[read]");
				print("<" + message + ">");
				
				if(message.equals("TERMINATE"))
					attempts = 1;
				// If you have any business logic should go here
				print("Socket.Server[business-logic]: Processing Message...");
				
				// If you have any business logic should go here
				
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				String response = "Your message has been processed";
				print("Socket.Server[write]");
				print("<"+response+">");
				dout.writeUTF("["+ this.id + "] [" + ThreadSocket.class.getSimpleName() + "] " + response);
				dout.flush();
				attempts--;
			}
			print("Socket.Server[close]");
			dis.close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
