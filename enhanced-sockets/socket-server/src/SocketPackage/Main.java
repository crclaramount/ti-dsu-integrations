package SocketPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {


    private static DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void print(String socket, String message) {
        System.out.println("[" + dateFormat.format(new Date()) + "] [" + socket + "]: " + message);
    }

    public static void main(String[] args) {
        Student mainStudent = new Student();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int option = 0;
        try {
            System.out.println("SOCKET SERVER");
            System.out.print("Enter the port to be opened [1000 - 8000]: ");
            String inputMessage = br.readLine();
            int port = Integer.parseInt(inputMessage);
            do {
                System.out.println("MENU");
                System.out.println("Enter your desired mode of operation: ");
                System.out.println("\t[1] Get Message (SimpleSocket).");
                System.out.println("\t[2] New Student.");
                System.out.println("\t[3] Load Student.");
                System.out.println("\t[4] Save Student.");
                System.out.println("\t[5] Show Student.");
                System.out.println("\t[0] Exit.");
                System.out.print("Enter your option: ");
                inputMessage = br.readLine();
                option = Integer.parseInt(inputMessage);
                switch (option) {
                    case 1: {
                        mainStudent = SimpleSocket.execute(port);
                    }
                    break;
                    case 2: {
                        System.out.println("Enter student name: ");
                        String name = br.readLine();
                        System.out.println("Enter student lastname: ");
                        String lastname = br.readLine();
                        System.out.println("Enter student age: ");
                        inputMessage = br.readLine();
                        int age = Integer.parseInt(inputMessage);
                        updateStudent(mainStudent, name.trim(), lastname.trim(), age);
                    }
                    break;
                    case 3: {
                        mainStudent = Student.getFromFile("\\students\\first.bin");
                        System.out.println("Student loaded!");
                    }
                    break;
                    case 4: {
                        mainStudent.saveToFile("\\students\\first.bin");
                        System.out.println("Student saved!");
                    }
                    break;
                    case 5: {
                        System.out.println(mainStudent.toString());
                    }
                    break;
                }
            } while (option != 0);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static void updateStudent(Student student, String name, String lastname, int age) {
        student.setName(name);
        student.setLastname(lastname);
        student.setAge(age);
    }
}
