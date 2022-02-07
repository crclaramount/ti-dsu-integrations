package com.telusinternational.google.classroom.integrations;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.telusinternational.google.classroom.integrations.example1.QueueConsumer;

import java.util.Properties;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.QueueSession;
import javax.jms.QueueReceiver;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueProcessor {

	private void print(String message) {
		System.out.println("["+String.format("%04d" , 0)+"] [" + new java.util.Date().toLocaleString() + "] [" + 
				this.getClass().getSimpleName() + "]: " + message);
	}
	
	public void openAndReceive(String queueName) throws Exception {
        // get the initial context
		Properties props = new Properties();
	    props.put( Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	    //props.put( Context.URL_PKG_PREFIXES, "org.jboss.as.naming.interfaces:org.jboss.ejb.client.naming");
	    props.put( Context.PROVIDER_URL, "remote+http://127.0.0.1:8080");
	    //props.put("java.naming.provider.url", "http://127.0.0.1:8080");
        InitialContext ctx = new InitialContext(props);
                                                                           
        // lookup the queue object
        Queue queue = (Queue) ctx.lookup(queueName);
        print("Queue.Server[lookup.queue] [queue: "+queueName+"]");
        
        // lookup the queue connection factory
        QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.
            lookup("ConnectionFactory");
        print("Queue.Server[lookup.connectionFactory]");
        
        // create a queue connection
        QueueConnection queueConn = connFactory.createQueueConnection();
        print("Queue.Server[create.connection]");

        // create a queue session
        QueueSession queueSession = queueConn.createQueueSession(false,
            Session.AUTO_ACKNOWLEDGE);
        print("Queue.Server[create.session]");

        // create a queue receiver
        QueueReceiver queueReceiver = queueSession.createReceiver(queue);
        print("Queue.Server[init.consumer]");

        // set an asynchronous message listener
        QueueConsumer asyncReceiver = new QueueConsumer();
        queueReceiver.setMessageListener(asyncReceiver);
                                                                           
        // set an asynchronous exception listener on the connection
        queueConn.setExceptionListener(asyncReceiver);
        
        // start the connection
        queueConn.start();

        // wait for messages
        System.out.print("waiting for messages");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.print(".");
        }
        System.out.println();
                                                                           
        // close the queue connection
        queueConn.close();
    }
	
}
