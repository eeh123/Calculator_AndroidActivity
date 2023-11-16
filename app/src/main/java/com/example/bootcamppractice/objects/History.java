package com.example.bootcamppractice.objects;

public class History {
    private double value1, value2;
    private String operator;
    public History(double value1, double value2, String operator) {
        this.value1 = value1;
        this.value2 = value2;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return (String.valueOf(value1) + " " + operator + " " + String.valueOf(value2));
    }
}
