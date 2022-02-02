# ti-dsu-integrations
System Integration Code Samples

## Sockets

**Prerequisites**
- Download and Install Maven 3.x.x
- Ensure MAVEN_HOME environment variable is registered pointing to your Maven Root folder (the folder containing /bin and /conf)
- Download and Install an IDE for Java development compatible with Maven

In order to run the Sockets code, you must understand there are two projects, **/sockets-101** and **/sockets-client-101**.

Project: **01-sockets/sockets-101**
> Should open the port for a Server Socket, this will receive messages sent by the client, and print them.

Main class: com.telusinternational.google.classroom.integrations.SocketManager

**Instructions on how to run it**
```
$.../01-sockets/sockets-101/> mvn install
# Here you should get BUILD SUCCESSFUL and a folder created under the project called /target
$.../01-sockets/sockets-101/> java -jar sockets-101-0.0.1-SNAPSHOT.jar
# This will start the execution of the code
=========SERVER SOCKET MANAGER===========
Enter the port to be opened [1000 - 8000]: <Enter Port>
=========================================
Enter your desired mode of operation:
 [1] Simple Socket (one message received and closes the connection).
 [2] Continuous Socket (one message after another being processed in sequence).
 [3] Concurrent Socket (one message after another processed in parallel threads).
 [4] Concurrent Retained Socket (one message after another processed in parallel threads, kept alive until the client asks to kill the session).
=========================================
Enter your option: <Option 1-4>
=========================================
Option Received: {OPTION}
=========================================
[Feb 2, 2022 8:36:37 AM] [SimpleSocket]: =========================================
[Feb 2, 2022 8:36:37 AM] [SimpleSocket]: Starting Server
[Feb 2, 2022 8:36:37 AM] [SimpleSocket]: =========================================
[Feb 2, 2022 8:36:37 AM] [SimpleSocket]: Socket.Server[bind]
[Feb 2, 2022 8:36:37 AM] [SimpleSocket]: Socket.Server[listen] [port: {PORT}]
```

Project: **01-sockets/sockets-client-101**
> Will open a connection to a Server Socket, provided the port (assumed localhost), and will ask you to enter messages to be sent through the network.
Main class: com.telusinternational.google.classroom.integrations.SocketClientManager

**Instructions on how to run it**
```
$.../01-sockets/sockets-client-101/> mvn install
# Here you should get BUILD SUCCESSFUL and a folder created under the project called /target
$.../01-sockets/sockets-client-101/> java -jar sockets-client-101-0.0.1-SNAPSHOT.jar
# This will start the execution of the code
=========CLIENT SOCKET MANAGER===========
Enter the port of the server [1000 - 8000]: <Enter Port>
=========================================
Enter your desired mode of operation:
 [1] Simple Socket (one message sent and closes the connection).
 [2] Continuous Socket (one message after another being sent in sequence).
 [3] Concurrent Socket (one message after another sent in parallel threads).
=========================================
Enter your option: <Option 1-3>
=========================================
Option Received: {OPTION}
=========================================
=========================================
Enter your message: <Enter a Text and hit Enter>
```

Class Recording link: https://drive.google.com/file/d/1bZUWZGxc1q_ceq8V2G1n7dxgKRaiDSPG/view?usp=sharing
Reference video: https://www.youtube.com/watch?v=BqBKEXLqdvI

---

## Queues

**Prerequisites**
- Download and Install Maven 3.x.x
- Ensure MAVEN_HOME environment variable is registered pointing to your Maven Root folder (the folder containing /bin and /conf)
- Download and Install an IDE for Java development compatible with Maven
- Download WildFly in its most recent version from section 4.1: https://www.wildfly.org/downloads/
- Ensure you follow the steps in this link for setting up WildFly with a queue named TestQ:  http://middlewaremagic.com/jboss/?p=2739


```
# First we need to setup a User and Password which will be used by our Queue consuming Applications
.../WildFly/bin> add-user
What type of user do you wish to add? 
 a) Management User (mgmt-users.properties) 
 b) Application User (application-users.properties)
(a): b
Enter the details of the new user to add.
Using realm 'ApplicationRealm' as discovered from the existing property files.
Username : jmsuser
User 'jmsuser' already exists and is enabled, would you like to... 
 a) Update the existing user password and roles 
 b) Disable the existing user 
 c) Type a new username
(a): a
Password recommendations are listed below. To modify these restrictions edit the add-user.properties configuration file.
 - The password should be different from the username
 - The password should not be one of the following restricted values {root, admin, administrator}
 - The password should contain at least 8 characters, 1 alphabetic character(s), 1 digit(s), 1 non-alphanumeric symbol(s)
Password : 
Re-enter Password : 
What groups do you want this user to belong to? (Please enter a comma separated list, or leave blank for none)[guest]: guest
Updated user 'jmsuser' to file '.../WildFly/standalone/configuration/application-users.properties'
Updated user 'jmsuser' to file '.../WildFly/domain/configuration/application-users.properties'
Updated user 'jmsuser' with groups guest to file '.../WildFly/standalone/configuration/application-roles.properties'
Updated user 'jmsuser' with groups guest to file '.../WildFly/domain/configuration/application-roles.properties'
Is this new user going to be used for one AS process to connect to another AS process? 
e.g. for a slave host controller connecting to the master or for a Remoting connection for server to server EJB calls.
yes/no? yes
To represent the user add the following to the server-identities definition <secret value="am1zdXNlckAxMjM=" />

# Now we will configure a JMS Queue in the Server
# In order to enter the jboss-cli, locate your prompt inside the /bin folder of your WildFly installation, and run the following command
.../WildFly/bin> jboss-cli
[standalone@localhost:9990 /] connect
# To add a JMS queue, use the jms-queue command from the management CLI:
[standalone@localhost:9990 /] /subsystem=messaging-activemq/server=default/jms-queue=TestQ/:add(entries=["java:jboss/exported/jms/queue/TestQ"])
# You can confirm the queue was created properly with the following command
jms-queue read-resource --queue-address=TestQ
[standalone@localhost:9990 /] :reload
```

In order to run the Queues code, you must understand there are two projects, **/queue-sender-101** and **/queue-receiver-101**.

Project: **02-queues/queue-sender-101**
> Should establish a Connection and a Session with the localhost WildFly Server, and open the Queue "TestQ" to deposit messages typed in the console.

Main class: com.telusinternational.google.classroom.integrations.WildFlyJmsQueueSender

**Instructions on how to run it**
```
$.../02-queues/queue-sender-101/> mvn install
# Here you should get BUILD SUCCESSFUL and a folder created under the project called /target
$.../02-queues/queue-sender-101/> java -jar queue-sender-101-0.0.1-SNAPSHOT.jar
# This will start the execution of the code
Feb 02, 2022 7:42:04 AM org.jboss.naming.remote.client.InitialContextFactory <clinit>
INFO: WFNAM00025: org.jboss.naming.remote.client.InitialContextFactory is deprecated; new applications should use org.wildfly.naming.client.WildFlyInitialContextFactory instead
Feb 02, 2022 7:42:04 AM org.wildfly.naming.client.Version <clinit>
INFO: WildFly Naming version 1.0.14.Final
Feb 02, 2022 7:42:04 AM org.wildfly.security.Version <clinit>
INFO: ELY00001: WildFly Elytron version 1.17.1.Final
[Feb 2, 2022 7:42:04 AM] [WildFlyJmsQueueSender]: Queue.Sender[init] Initial Context is Ready! [javax.naming.InitialContext@1554825]
Feb 02, 2022 7:42:04 AM org.xnio.Xnio <clinit>
INFO: XNIO version 3.8.4.Final
Feb 02, 2022 7:42:04 AM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.8.4.Final
Feb 02, 2022 7:42:05 AM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 2.4.0.Final
Feb 02, 2022 7:42:05 AM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 5.0.23.Final
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[Feb 2, 2022 7:42:09 AM] [WildFlyJmsQueueSender]: Queue.Sender[init] Connection established!
[Feb 2, 2022 7:42:09 AM] [WildFlyJmsQueueSender]: Queue.Sender[init] Session established!
[Feb 2, 2022 7:42:09 AM] [WildFlyJmsQueueSender]: Queue.Sender[init] Queue [jms/queue/TestQ] Instance Opened!
[Feb 2, 2022 7:42:09 AM] [WildFlyJmsQueueSender]: Queue.Sender[process] Ready to send Messages!
Enter your message: <Message to be sent>
[Feb 2, 2022 7:47:41 AM] [WildFlyJmsQueueSender]: Message deposited in queue [jms/queue/TestQ]
```

Project: **02-queues/queue-receiver-101**
> Should establish a Connection and a Session with the localhost WildFly Server, and open the Queue "TestQ" to read/pop out messages stored in the server.

Main class: com.telusinternational.google.classroom.integrations.WildFlyJmsQueueReader

**Instructions on how to run it**
```
$.../02-queues/queue-receiver-101/> mvn install
# Here you should get BUILD SUCCESSFUL and a folder created under the project called /target
$.../02-queues/queue-receiver-101/> java -jar queue-receiver-101-0.0.1-SNAPSHOT.jar
# This will start the execution of the code
Feb 02, 2022 7:50:34 AM org.jboss.naming.remote.client.InitialContextFactory <clinit>
INFO: WFNAM00025: org.jboss.naming.remote.client.InitialContextFactory is deprecated; new applications should use org.wildfly.naming.client.WildFlyInitialContextFactory instead
Feb 02, 2022 7:50:34 AM org.wildfly.naming.client.Version <clinit>
INFO: WildFly Naming version 1.0.14.Final
Feb 02, 2022 7:50:34 AM org.wildfly.security.Version <clinit>
INFO: ELY00001: WildFly Elytron version 1.17.1.Final
[Feb 2, 2022 7:50:34 AM] [WildFlyJmsQueueReader]: Queue.Receiver[init] Initial Context is Ready! [javax.naming.InitialContext@1e46e8f]
Feb 02, 2022 7:50:34 AM org.xnio.Xnio <clinit>
INFO: XNIO version 3.8.4.Final
Feb 02, 2022 7:50:34 AM org.xnio.nio.NioXnio <clinit>
INFO: XNIO NIO Implementation Version 3.8.4.Final
Feb 02, 2022 7:50:35 AM org.jboss.threads.Version <clinit>
INFO: JBoss Threads version 2.4.0.Final
Feb 02, 2022 7:50:35 AM org.jboss.remoting3.EndpointImpl <clinit>
INFO: JBoss Remoting version 5.0.23.Final
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[Feb 2, 2022 7:50:38 AM] [WildFlyJmsQueueReader]: Queue.Receiver[init] Connection established!
[Feb 2, 2022 7:50:39 AM] [WildFlyJmsQueueReader]: Queue.Receiver[init] Session established!
[Feb 2, 2022 7:50:39 AM] [WildFlyJmsQueueReader]: Queue.Receiver[init] Queue Instance [jms/queue/TestQ] Opened!
[Feb 2, 2022 7:50:39 AM] [WildFlyJmsQueueReader]: Queue.Receiver[init] Receiver Started!
JMS Ready To Receive Messages (To quit, send a "quit" message from QueueSender.class).
[Feb 2, 2022 7:50:39 AM] [WildFlyJmsQueueReader]: Queue.Receiver[process] Message Received!
[Feb 2, 2022 7:50:39 AM] [WildFlyJmsQueueReader]: Queue.Receiver[process] <<The message deposited in the Queue>>
```