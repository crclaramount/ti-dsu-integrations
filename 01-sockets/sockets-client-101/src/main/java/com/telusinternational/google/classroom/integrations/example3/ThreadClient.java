package com.telusinternational.google.classroom.integrations.example3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.telusinternational.google.classroom.integrations.models.Person;

public class ThreadClient extends Thread {

	private Person person = null;
	private Socket s = null;
	private Integer pendingResponses = 0;
	
	public ThreadClient(Person person, Socket s, Integer pendingResponses) {
		this.person = person;
		this.s = s;
		this.pendingResponses = pendingResponses;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream din = new DataInputStream(s.getInputStream());  
			ObjectOutputStream dout = new ObjectOutputStream(s.getOutputStream());
			dout.writeObject(person);
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
