package com.example.pethoalpar.httprequestexample;

/**
 * Created by pethoalpar on 15/03/2017.
 */
public class Car {
    private String model;
    private String fuel;
    private int manufactureYear;

    public Car(String model, String fuel, int manufactureYear) {
        this.model = model;
        this.fuel = fuel;
        this.manufactureYear = manufactureYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }
}
