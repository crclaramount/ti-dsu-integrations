package com.telusinternational.google.classroom.integrations;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WildFlyJmsQueueReader implements MessageListener {
	public final static String JMS_CONNECTION_FACTORY_JNDI="jms/RemoteConnectionFactory";
	  public final static String JMS_QUEUE_JNDI="jms/queue/TestQ";
	  public final static String JMS_USERNAME="pepe";       //  The role for this user is "guest" in ApplicationRealm
	  public final static String JMS_PASSWORD="pepe";  
	  public final static String WILDFLY_REMOTING_URL="http-remoting://localhost:8080";
	 
	 
	  private QueueConnectionFactory qconFactory;
	  private QueueConnection qcon;
	  private QueueSession qsession;
	  private QueueReceiver qReceiver;
	  private Queue queue;
	  private TextMessage msg;
	  private boolean quit = false;
	 
	  public static void main(String[] args) throws Exception {
	    InitialContext ic = getInitialContext();
	    WildFlyJmsQueueReader wildflyJmsQueueReceive = new WildFlyJmsQueueReader();
	    wildflyJmsQueueReceive.init(ic, JMS_QUEUE_JNDI);
	    System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message from QueueSender.class).");
	    // Waiting until a "quit" message has been received.
	    synchronized(wildflyJmsQueueReceive) {
	         while (! wildflyJmsQueueReceive.quit) {
	             try {
	                   wildflyJmsQueueReceive.wait();
	             }
	             catch (InterruptedException ie) {
	                   ie.printStackTrace();
	             }
	         }
	     }
	     wildflyJmsQueueReceive.close();
	  }
	   
	  public void init(Context ctx, String queueName) throws NamingException, JMSException {
	    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_CONNECTION_FACTORY_JNDI);
	 
	    //  If you won't pass jms credential here then you will get 
	    // [javax.jms.JMSSecurityException: HQ119031: Unable to validate user: null]    
	    qcon = qconFactory.createQueueConnection(this.JMS_USERNAME, this.JMS_PASSWORD);   
	    print("Queue.Receiver[init] Connection established!");
	    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	    print("Queue.Receiver[init] Session established!");
	    queue = (Queue) ctx.lookup(queueName);
	    print("Queue.Receiver[init] Queue Instance [" + JMS_QUEUE_JNDI + "] Opened!");
	    qReceiver = qsession.createReceiver(queue);
	    qReceiver.setMessageListener(this);
	    print("Queue.Receiver[init] Receiver Started!");
	    qcon.start();
	  }
	 
	  public void onMessage(Message msg) {
	     try {
	    	 print("Queue.Receiver[process] Message Received!");
	    	 String msgText;
	    	 if (msg instanceof TextMessage) {
	    		 msgText = ((TextMessage)msg).getText();
	    	 } else {
	    		 msgText = msg.toString();
	    	 }
	    	 print("Queue.Receiver[process] <" + msgText + ">");
	    	 if (msgText.equalsIgnoreCase("TERMINATE")) {
	    		 print("Queue.Receiver[process] Terminate Signal Received!");
	    		 synchronized(this) {
	    			 quit = true;
	    			 this.notifyAll(); // Notify main thread to quit
	    		 }
	    	 }
	     } catch (JMSException jmse) {
	    	 jmse.printStackTrace();
	     }
	  }
	  
	  private static void print(String message) {
		  System.out.println("[" + new java.util.Date().toLocaleString() + "] [" + WildFlyJmsQueueReader.class.getSimpleName() + "]: " + message);
	  }
	 
	  public void close() throws JMSException {
	    qReceiver.close();
	    print("Queue.Receiver[terminate] Queue Closed!");
	    qsession.close();
	    print("Queue.Receiver[terminate] Session Closed!");
	    qcon.close();
	    print("Queue.Receiver[terminate] Connection Closed!");
	  }
	   
	  private static InitialContext getInitialContext() throws NamingException {
	     InitialContext context=null;
	     try {
	           Properties props = new Properties();
	           props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	           props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);   // NOTICE: "http-remoting" and port "8080"
	           props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
	           props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
	           //props.put("jboss.naming.client.ejb.context", true);
	           context = new InitialContext(props);
	           print("Queue.Receiver[init] Initial Context is Ready! [" + context + "]");
	      } catch (Exception e) {
	           e.printStackTrace();
	      }
	    return context;
	  }
}
