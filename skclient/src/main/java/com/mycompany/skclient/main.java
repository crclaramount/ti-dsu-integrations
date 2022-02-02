package com.mycompany.skclient;
import Modelin.Vehicle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class main 
{
    public static void main(String[] args) throws IOException 
    {
        // Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("");
        System.out.println("");
        System.out.println("This App will create a Vehicle Object and send it to the a Server via Socket");
        System.out.println("When the Server receives the Vehicle Object, it will execute the Vehicle Object's methods like:");
        System.out.println("- StartEngine()");
        System.out.println("- RaceAndGetStats()");
        System.out.println("- getLastTime()");
        System.out.println("- getLastDistance()");
        System.out.println("- ShowDemostration()");
        System.out.println("- StopEngine()");
        System.out.println("");

        System.out.println("Enter Vehicle Category (Car, Motorcycle, Truck, Bicycle)");
        String Category = reader.readLine();
        System.out.println("");

        System.out.println("Enter Vehicle Color");
        String Color = reader.readLine();
        System.out.println("");

        System.out.println("Enter Vehicle Weight (Kg)");
        String tmp = reader.readLine();

        Double Weight = 0.0;
        try {
            Weight = Double.parseDouble(tmp);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Weight");
            System.out.println("Default Weight: 0.0 Kg");
        }

        System.out.println("");

        System.out.println("Enter Vehicle Brand");
        String Brand = reader.readLine();
        System.out.println("");

        Vehicle Vh = new Vehicle();
        Vh.Category = Category;
        Vh.Color = Color;
        Vh.Weight = Weight;
        Vh.Brand = Brand;

        System.out.println("");
        System.out.println("Binary File Stored at: C:\\files\\Vehicle.bin");

        SkClient cli = new SkClient("localhost", 8085, Vh);
    }       

}
