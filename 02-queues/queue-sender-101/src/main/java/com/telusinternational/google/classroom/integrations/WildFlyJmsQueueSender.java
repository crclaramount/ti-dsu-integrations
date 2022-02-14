package com.telusinternational.google.classroom.integrations;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.Properties;
 
//jms stuff
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class WildFlyJmsQueueSender {
  public final static String JMS_CONNECTION_FACTORY_JNDI="jms/RemoteConnectionFactory";
  public final static String JMS_QUEUE_JNDI="jms/queue/TestQ";
  public final static String JMS_USERNAME="pepe";       //  The role for this user is "guest" in ApplicationRealm
  public final static String JMS_PASSWORD="pepe";  
  public final static String WILDFLY_REMOTING_URL="http-remoting://localhost:8080";
 
  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueSender qsender;
  private Queue queue;
  private TextMessage msg;
 
  public static void main(String[] args) throws Exception {
    InitialContext ic = getInitialContext();
    WildFlyJmsQueueSender queueSender = new WildFlyJmsQueueSender();
    queueSender.init(ic, JMS_QUEUE_JNDI);
    readAndSend(queueSender);
    queueSender.close();
  }
 
  public void init(Context ctx, String queueName) throws NamingException, JMSException {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_CONNECTION_FACTORY_JNDI);
     
    //  If you won't pass jms credential here then you will get 
    // [javax.jms.JMSSecurityException: HQ119031: Unable to validate user: null]    
    qcon = qconFactory.createQueueConnection(this.JMS_USERNAME, this.JMS_PASSWORD);   
    print("Queue.Sender[init] Connection established!");
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    print("Queue.Sender[init] Session established!");
    queue = (Queue) ctx.lookup(queueName);
    print("Queue.Sender[init] Queue [" + JMS_QUEUE_JNDI + "] Instance Opened!");
    qsender = qsession.createSender(queue);
    msg = qsession.createTextMessage();
    qcon.start();
  }
 
  public void send(String message,int counter) throws JMSException {
    msg.setText(message);
    msg.setIntProperty("counter", counter);
    qsender.send(msg);
  }
 
  public void close() throws JMSException {
    qsender.close();
    print("Queue.Sender[terminate] Queue Closed!");
    qsession.close();
    print("Queue.Sender[terminate] Session Closed!");
    qcon.close();
    print("Queue.Sender[terminate] Connection Closed!");
  }
 
  private static void readAndSend(WildFlyJmsQueueSender wildFlyJmsQueueSender) throws IOException, JMSException {
	String message = "";
	int i = 0;
	while(!message.equals("TERMINATE")) {
		print("Queue.Sender[process] Ready to send Messages!");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your message: ");
		message = br.readLine();
		//print("Queue.Sender[process] <" + message + ">");
		wildFlyJmsQueueSender.send(message,i++);
		print("Message deposited in queue [" + JMS_QUEUE_JNDI + "]");
	}
	print("Queue.Sender[process] Terminate Signal Received!");
  }
  
  private static void print(String message) {
	  System.out.println("[" + new java.util.Date().toLocaleString() + "] [" + WildFlyJmsQueueSender.class.getSimpleName() + "]: " + message);
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
           print("Queue.Sender[init] Initial Context is Ready! [" + context + "]");
      } catch (Exception e) {
           e.printStackTrace();
      }
    return context;
  }
}