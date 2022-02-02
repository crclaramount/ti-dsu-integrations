package com.mycompany.app;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private static DataOutputStream dataOutputStream = null;
	private static DataInputStream dataInputStream = null;
	
	
	public static void main(String[] args) {
		try(Socket socket = new Socket("localhost", 5000)){
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			sendFile("C:\\Users\\river\\Documents\\Code\\DSU\\SystemIntegration\\Sockets\\FilesTrasnfer\\FileTransfer\\FileSend\\test2.txt");
			sendFile("C:\\Users\\river\\Documents\\Code\\DSU\\SystemIntegration\\Sockets\\FilesTrasnfer\\FileTransfer\\FileSend\\test3.txt");
			
			dataInputStream.close();
			dataInputStream.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void sendFile(String path) throws Exception{
		int bytes = 0;
		File file = new File(path);
		FileInputStream fileInputStream = new FileInputStream(file);
		
		//send file size
		dataOutputStream.writeLong(file.length());
		//break file into chunks
		byte[] buffer = new byte[4*1024];
		while((bytes = fileInputStream.read(buffer))!=-1) {
			dataOutputStream.write(buffer, 0, bytes);
			dataOutputStream.flush();
		}
		fileInputStream.close();
	}
}
