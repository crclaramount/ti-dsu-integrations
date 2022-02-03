# ti-dsu-integrations
System Integration Code Samples- Socket Object Integration

- Open the server jar file that is located at ...\ti-dsu-integrations\01-sockets\sockets-101\target and run the command  `java -jar sockets-101-0.0.1-SNAPSHOT.jar` on the terminal.
- Open the client jar file that is located at ...\ti-dsu-integrations\01-sockets\sockets-client-101\target and run the command  `java -jar sockets-client-101-0.0.1-SNAPSHOT.jar` on the terminal.
- Once you have the server and client running, select a port and a mode of transfering to establish a connection.
- On the client side is going to ask you to input your name and age to create an Object Person of a custom class:

```java
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
		```
    
- The server will receive this object, deserialize it and store it in the target folder with the `writeToFile(Object o)` function with a random 5 digit number name: ex: 48614.bin 
  
  ```java
  public static void writeToFile(Object o) throws IOException {
		//generate a random 5 digit number to create a random name
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(100000);
		//String root = "C:\\Users\\oscar.umana\\Documents\\Objects\\";
		String name = String.format("%05d", num); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(name + ".bin"));
		objectOutputStream.writeObject(o);
		objectOutputStream.close();
	}
	```
- Finally it will call of method of the Person class called greet().
