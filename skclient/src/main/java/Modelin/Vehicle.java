package Modelin;

import java.io.Serializable;

public class Vehicle implements Serializable {

    public String Category;
    public String Brand;
    public String Color;
    public double Weight;
    private double LastTime;
    private double LastDistance;

    public String StartEngine() {
        return Brand + "," + Category + " Engine Started!";
    }

    public String StopEngine() {
        return Brand + "," + Category + " Engine Stopped!";
    }

    public String RaceAndGetStats() {
        double Distance = Math.floor(Math.random() * (125 - 15 + 1) + 15);
        double Time = Math.floor(Math.random() * (45 - 10 + 1) + 10);
        this.LastTime = Time;
        this.LastDistance = Distance;
        return Brand + "," + Category + " Race Stats: Distance: " + Distance + "(Km) Time: " + Time + "(Minutes)";
    }

    public double getLastTime() {
        return this.LastTime;
    }

    public double getLastDistance() {
        return this.LastDistance;
    }

    public void ShowDemostration() {

        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("*** D E M O S T R A T I O N ***");

        System.out.println("Brand: " + this.Brand);
        System.out.println("Category: " + this.Category);
        System.out.println("Color: " + this.Color);
        System.out.println("Weight (Kg): " + this.Weight);

        System.out.println(this.StartEngine());
        System.out.println(this.RaceAndGetStats());
        System.out.println("Last Race Time: " + this.getLastTime() + " (Minutes)");
        System.out.println("Last Race Distance: " + this.getLastDistance() + " (Km)");
        System.out.println(this.StopEngine());

        System.out.println("");
        System.out.println("");
        System.out.println("");

    }
}
