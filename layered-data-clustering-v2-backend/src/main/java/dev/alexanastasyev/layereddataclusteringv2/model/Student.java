package dev.alexanastasyev.layereddataclusteringv2.model;

public class Student {
    private final int number;
    private final String name;

    public Student(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
