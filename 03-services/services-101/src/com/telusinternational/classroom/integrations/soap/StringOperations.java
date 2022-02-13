package com.telusinternational.classroom.integrations.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface StringOperations {

	@WebMethod(operationName = "helloToMe")
	@WebResult(name = "helloToMeResponseNode", targetNamespace = "http://www.telusinternational.com")
	String sayHello(@WebParam(name="name") String name);
	
	@WebMethod(operationName = "goodbyeToMe")
	@WebResult(name = "goodbyeToMeResponseNode", targetNamespace = "http://www.telusinternational.com")
	String sayGoodbye(@WebParam(name="name") String name);
	
}
