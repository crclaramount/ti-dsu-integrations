# Socket Exercise

Before to execute the program we need to create a folder with the name **files** in the next path:
**ServerTelus/target**.

Steps to use the program

Execute the server and client

```
cd ServerTelus/target

java -jar ServerTelus-1.0-SNAPSHOT.jar
```

We have an instruction that says that we need to put a number of port between 1000 and 8000, if you don't introduce anything and press enter the default port is going to be 4500.
Now we need to be the same with the the Client

cd ServerTelus/target

java -jar ServerTelus-1.0-SNAPSHOT.jar

```
cd ClientTelus/target

java -jar ClientTelus-1.0-SNAPSHOT.jar

```

We need to put the number of the port that wrote in the server or only enter to use the default number port, then the program request the path of the file that you want to transfer to the server.
You can write the path or drag and drop the file to the terminal. if when you drag the file and the path show Quotes we need to delete the Quotes and let only the path

## Example

Wrong Way

<a style="color:red">"C:\Users\Diego Reyes\Desktop\ti-dsu-integrations\SocketHW-1\README.md"</a>

Correct Way

<a style="color:green">C:\Users\Diego Reyes\Desktop\ti-dsu-integrations\SocketHW-1\README.md</a>

How we can see in the example we need to write the path without quotes.
Once we put the path file press enter and wait to the program transfer the file to the folder that created.
If you want to see the file we need to go to the path of **ServerTelus/target/files**
