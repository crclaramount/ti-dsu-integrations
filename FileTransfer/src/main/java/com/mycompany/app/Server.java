package com.mycompany.app;
import java.io.*;
import java.net.*;

public class Server {
	private static DataOutputStream dataOutputStream = null;
	private static DataInputStream dataInputStream = null;
	
	public static void main(String[] args) {
		try( ServerSocket serverSocket = new ServerSocket(5000)) {
			System.out.println("listening to port:5000");
			Socket clientSocket = serverSocket.accept();
			System.out.println(clientSocket+"connected.");
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			
			receiveFile("C:\\Users\\river\\Documents\\Code\\DSU\\SystemIntegration\\Sockets\\FilesTrasnfer\\FileTransfer\\FileReceiver\\testForFileReceiver.txt");
			receiveFile("C:\\Users\\river\\Documents\\Code\\DSU\\SystemIntegration\\Sockets\\FilesTrasnfer\\FileTransfer\\FileReceiver\\testForFileReceiver2.txt");
			
			dataInputStream.close();
			dataOutputStream.close();
						
			clientSocket.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	private static void receiveFile(String fileName) throws Exception{
		int bytes = 0;
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		
		long size = dataInputStream.readLong(); //read file size
		
		byte[] buffer = new byte[4*1024];
		while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
			fileOutputStream.write(buffer, 0, bytes);
			size -= bytes;
		}
		fileOutputStream.close();
	}
}
