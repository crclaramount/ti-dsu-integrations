package com.telusinternational.google.classroom.integrations.models;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * test class to send objects through sockets
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	
	public Person(String name, int age){
		setName(name);
		try {
			setAge(age);
		}catch(IllegalArgumentException e) {
			setAge(-1);
		}
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) throws IllegalArgumentException{
		this.age = age;
	}
	
	public void greet() {
		System.out.println(String.format("Hi my name is %s and I'm %s years old", this.getName(), this.getAge()));
	}
	
	
}