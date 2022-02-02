package SocketPackage;

import java.io.*;

public class Student implements Serializable {

    private String name;
    private String lastname;
    private int age;


    public Student() {
        this.name = "";
        this.lastname = "";
        this.age = 0;
    }

    public Student(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public void saveToFile(String path) throws IOException {
        String absolutePath = new File("").getAbsolutePath();
        File file = new File(absolutePath + path);
        file.createNewFile();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(this);
    }

    public static Student getFromFile(String path) throws IOException, ClassNotFoundException {
        String absolutePath = new File("").getAbsolutePath();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(absolutePath + path));
        return (Student) objectInputStream.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student {" +
                "name = '" + name + '\'' +
                ", lastname = '" + lastname + '\'' +
                ", age = " + age +
                '}';
    }
}
