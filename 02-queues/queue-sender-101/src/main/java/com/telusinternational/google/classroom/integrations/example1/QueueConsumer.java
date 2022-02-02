package com.telusinternational.google.classroom.integrations.example1;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueConsumer implements MessageListener, ExceptionListener {

	private void print(String message) {
		System.out.println("["+String.format("%04d" , 0)+"] [" + new java.util.Date().toLocaleString() + "] [" + 
				this.getClass().getSimpleName() + "]: " + message);
	}
	
	/**
    This method is called asynchronously by JMS when a message arrives
    at the queue. Client applications must not throw any exceptions in
    the onMessage method.
    @param message A JMS message.
	  */
	 public void onMessage(Message message) {
	     TextMessage msg = (TextMessage) message;
	     try {
	         print("Queue.Consumer[message.received]: " + msg.getText());
	     } catch (JMSException ex) {
	         ex.printStackTrace();
	     }
	 }
	                                                                        
	 /**
	    This method is called asynchronously by JMS when some error occurs.
	    When using an asynchronous message listener it is recommended to use
	    an exception listener also since JMS have no way to report errors
	    otherwise.
	    @param exception A JMS exception.
	  */
	 public void onException(JMSException exception) {
		 print("Queue.Consumer[message.error]: " + exception);
	 }
	
}
