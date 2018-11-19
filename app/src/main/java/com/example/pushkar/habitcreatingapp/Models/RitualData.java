package com.example.pushkar.habitcreatingapp.Models;

public class RitualData {
    int hour;
    int min;
    String name;
    String day;
    Boolean done;

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }


    public RitualData() {

    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }
}
