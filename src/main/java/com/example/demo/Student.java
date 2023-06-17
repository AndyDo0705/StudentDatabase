package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int uid;
    private String name;
    private double weight;
    public String hairColor;
    private double gpa;
    private int height;

    public Student() {
    }

    public Student(String name, double weight, String hairColor, double gpa, int height) {
        this.name = name;
        this.weight = weight;
        this.hairColor = hairColor;
        this.gpa = gpa;
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }
}
