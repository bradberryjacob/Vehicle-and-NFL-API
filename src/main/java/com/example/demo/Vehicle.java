package com.example.demo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="vehiclesss")
public class Vehicle implements Serializable {

    @Id
    private int id;

    private String makeModel;
    private int year;
    private double price;

    public Vehicle(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRetailPrice() {
        return price;
    }

    public void setRetailPrice(double retailPrice) {
        this.price = retailPrice;
    }

    public Vehicle(int id, String makeModel, int year, double retailPrice) {
        this.id = id;
        this.makeModel = makeModel;
        this.year = year;
        this.price = retailPrice;
    }
}
