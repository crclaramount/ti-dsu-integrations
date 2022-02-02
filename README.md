# ti-dsu-integrations
System Integration Code Samples

## Enhanced Socket Server

The enhanced socket server code is in the directory:

```
enhanced-sockets\socket-server\src\SocketPackage
```

To run the server you have to put this in the command line:

```
cd enhanced-sockets\socket-server\out\artifacts\socket_server_jar
java -jar socket-server.jar
```

The object that you send to the server and get as response is in:

```
enhanced-sockets\socket-server\src\SocketPackage\Student.java
```

The server code is in: 

```
enhanced-sockets\socket-server\src\SocketPackage\SimpleSocket.java
```

```java
public static Student handleConnections(int port) throws IOException, ClassNotFoundException {

    Main.print(socketName, "Socket.Server[bind]");

    ServerSocket serverSocket = new ServerSocket(port);
    Main.print(socketName, "Socket.Server[listen] [port: " + port + "]");

    // This line of code will stay locked until a request is received
    Socket socket = serverSocket.accept();
    // If we reach this line of code, someone established a session with us
    Main.print(socketName, "Socket.Server[accept] Session Connection Received!");

    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

    // Receiving student
    Student newStudent = (Student) objectInputStream.readObject();
    Main.print(socketName, "Socket.Server[read]: " + newStudent.toString() + "");

    // If you have any business logic should go here
    Main.print(socketName, "Socket.Server[business-logic]: Processing Message...");

    Main.print(socketName, "Socket.Server[write]");
    Main.print(socketName, "<" + newStudent.getName() + ">");

    objectOutputStream.writeObject(newStudent);
    objectOutputStream.flush();

    Main.print(socketName, "Socket.Server[close]");
    objectInputStream.close();
    socket.close();
    serverSocket.close();
    return newStudent;
}
```

## Enhanced Socket Client

The enhanced socket client code is in the directory:

```
enhanced-sockets\socket-client\src\SocketPackage
```

To run the client you have to put this in the command line:

```
cd enhanced-sockets\socket-client\out\artifacts\socket_client_jar
java -jar socket-client.jar
```

The client code is in: 

```
enhanced-sockets\socket-client\src\SocketPackage\SimpleClientSocket.java
```

```java
public static void send(int port) throws IOException, ClassNotFoundException {
    Socket socket = new Socket("localhost", port);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    System.out.println("Enter student name: ");
    String name = br.readLine();
    System.out.println("Enter student lastname: ");
    String lastname = br.readLine();
    System.out.println("Enter student age: ");
    String inputMessage = br.readLine();
    int age = Integer.parseInt(inputMessage);

    Student newStudent = new Student(name, lastname, age);
    objectOutputStream.writeObject(newStudent);
    objectOutputStream.flush();
    System.out.println("STUDENT SENT");

    Student returnMessage = (Student) objectInputStream.readObject();
    System.out.println("Return Message is=" + returnMessage.toString());
    socket.close();
}
```