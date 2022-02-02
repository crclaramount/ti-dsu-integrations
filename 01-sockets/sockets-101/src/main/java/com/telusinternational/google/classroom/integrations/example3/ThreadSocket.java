package com.telusinternational.google.classroom.integrations.example3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.telusinternational.google.classroom.integrations.models.Person;
import com.telusinternational.google.classroom.integrations.util.InputProcessing;

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
		System.out.println("["+id+"] [" + new java.util.Date() + "] [" + ThreadSocket.class.getSimpleName() + "]: " + message);
	}
	
	@Override
	public void run() {
		try {
			// Now we read the content of the message received as a bytes stream
			ObjectInputStream dis = new ObjectInputStream(s.getInputStream());
			Person person = InputProcessing.readObject(dis);
			print("Socket.Server[read]");
			print("<" + person.getClass().getSimpleName() + ">");
			// If you have any business logic should go here
			print("Socket.Server[business-logic]: Processing Message...");
			
			// If you have any business logic should go here
			if(person != null && !person.getName().isEmpty()) person.greet();
			InputProcessing.writeToFile(person);
			if(person.getName() == "") {
				this.handler.message = "TERMINATE";
			}
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
