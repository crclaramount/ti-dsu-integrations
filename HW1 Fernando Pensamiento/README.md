# DSU_HW1_javaSockets
We work with sockets in java


* [Client side](#Client-Side)
* [Server side](#Server-side)
* [How it works](#How-it-works)

In both application we have to chose if we want to send a xml file or a xml as a string. 

## Client Side 
When we decide to send a string we send the string like a normal socket message, and we wait for the response, if the response is "OK" we strop the program. 
if the response is not "ok" we ask the user again for a string as an XML. 

to send the file we use this method 
```r
 private static boolean sendFile(String path) throws Exception {
        int bytes = 0;
        File file = new File(path);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);

            // send file size
            dataOutputStream.writeLong(file.length());
            // break file into chunks
            byte[] buffer = new byte[4 * 1024];
            while ((bytes = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytes);
                dataOutputStream.flush();
            }
            fileInputStream.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
```

This method was found in this URL https://heptadecane.medium.com/file-transfer-via-java-sockets-e8d4f30703a5 

## Server side 
If the client send an XML as a string, we create a file with that string. 
That's because first I made the code to compare if an XML file was a correct XML and is easier in that way to don't create a lot of extra code. 

```r
private static void receiveFile(String fileName) throws Exception {
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong(); // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
        }
        fileOutputStream.close();
    }
```
This is the method to receive a file in a socket 
This method was found in this URL https://heptadecane.medium.com/file-transfer-via-java-sockets-e8d4f30703a5

And we have another method to compare if a xml is a valid xml with this method. 
```r
	public boolean esXML(String stFile) {
		File file = new File(stFile);
		Document doc = null;

		try {

			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			doc.getDocumentElement().normalize();
			return true;
		} catch (Exception e) {
			System.out.println("Error leyendo el archivo");
			return false; 
		}
	}
```


## How it works
We have to have installed java and can run javac command with the terminal 
the easier way to open a terminal is going to the folder and write cmd on the path ho shows you the current path, when you press enter windows can open a terminal in the current 
folder. 
![image](https://user-images.githubusercontent.com/98837103/152025400-1dd815d7-5716-4014-9f00-33fc2a3ea931.png)


We place in the folder of the server and use the following command 

```r
  javac *.java
```
and then we can run `java Server`
then we can choose between send a xml file or a string as a xml 

next we have to wait for the client 

to run the client is the same, we have to open a terminal in the client folder, then run javac to compile the file and then run the client. 

![image](https://user-images.githubusercontent.com/98837103/152026632-4fecb327-f86f-4de9-86b3-84b72c394d0a.png)


![image](https://user-images.githubusercontent.com/98837103/152026765-6edf445a-b0e5-4b57-bcb9-cd08150f2373.png)


Once you send a correct XML file, both applications stop

