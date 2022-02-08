package com.telusinternational.classroom.integrations.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class StringOperations {
	private String message = new String("Hello, ");

    public void Hello() {}

    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
