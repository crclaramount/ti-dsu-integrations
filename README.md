# ti-dsu-integrations
System Integration Code Samples

## Sockets

Prerequisites: 
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