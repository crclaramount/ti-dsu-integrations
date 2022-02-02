package com.telusinternational.google.classroom.integrations.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;

import com.telusinternational.google.classroom.integrations.models.Person;

public class InputProcessing{
	
	public static void writeToFile(Object o) throws IOException {
		//generate a random 5 digit number to create a random name
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		//String root = "C:\\Users\\oscar.umana\\Documents\\Objects\\";
		String name = String.format("%05d", num); 
		//ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(root + name + ".bin"));
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(name + ".bin"));
		objectOutputStream.writeObject(o);
		objectOutputStream.close();
	}
	
	public static Person readFile(String name) throws IOException, ClassNotFoundException {
		//String root = "C:\\Users\\oscar.umana\\Documents\\Objects\\";
		//ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(root + name + ".bin"));
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(name + ".bin"));
		Person person = (Person)objectInputStream.readObject();
		objectInputStream.close();
		return person;
	}
	
	public static Person readObject(ObjectInputStream dis) throws IOException {
		Person person = null;
		Object aux;
		
		try{
			aux = dis.readObject();
			if(aux instanceof Person)
				person = (Person)aux;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return person;
	}
}
