package com.telusinternational.google.classroom.integrations.example3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadClient extends Thread {

	private String message = null;
	private Socket s = null;
	private Integer pendingResponses = 0;
	
	public ThreadClient(String message, Socket s, Integer pendingResponses) {
		this.message = message;
		this.s = s;
		this.pendingResponses = pendingResponses;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream din = new DataInputStream(s.getInputStream());  
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			dout.writeUTF(message);
			dout.flush();
			String receivedMessage = din.readUTF();
			System.out.println("[THREAD] Server says: " + receivedMessage);
			System.out.println("[THREAD] =========================================");
			din.close();
			dout.close();
			pendingResponses = pendingResponses - 1;
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
