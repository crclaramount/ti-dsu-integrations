package com.telusinternational.classroom.integrations.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface = "com.telusinternational.classroom.integrations.soap.StringOperations")
public class StringOperationsImpl implements StringOperations {
	private String messageHello = new String("Hello, ");
	private String messageGoodbye = new String("Goodbye, ");

    @Override
    public String sayHello(String name) {
        return messageHello + name + ".";
    }

	@Override
	public String sayGoodbye(String name) {
		return messageGoodbye + name + ".";
	}
}
