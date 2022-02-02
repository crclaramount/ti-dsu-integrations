# ti-dsu-integrations
## Socket Transfer File
Here we have a Client and Server class which the server are using the port #5000 in order to open a channel of communication. 

Next we can see the path were the file will be storage and the file name is testForFileReceiver and testForFileReceiver2. They are two file for the test. 

The client open the port 5000 and is the one that sends the file named test2.txt and test3.txt which are located in the folder named FileSend. 

The client has a loop in order to send the file for the problem of file size. Breaking the file into multiple chunks and then sending them through the socket within a looping statement and receiving in a similar manner.

```JAVA
 wile(stream.read(buffer)!=-1){ ... }

```

First we run Server and then Client. 
