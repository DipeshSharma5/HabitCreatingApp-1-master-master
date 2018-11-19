package com.example.pushkar.habitcreatingapp.Models;

public class HabitData {

    int habitId;
    String habitName;       //name of the habit
    String habitDescription;//description about habit
    int habitCounter;       //counts the number of time a particular habit has been performed
    String time;            //morning / afternoon / evening / night
    int preferredMin;       //minute selected by the user
    int preferredHour;      //hour selected by the user
    int category;           //in which category does a particular habit comes
    boolean performed;      //whether the habit has been performed or not in a particular day
    int performingMin;      //minute of performing the habit
    int performingHour;     //hour of performing the habit


    public HabitData(int habitId, String habitName,
                     String time, int preferredMin,
                     int preferredHour, int category) {

        this.habitId = habitId;
        this.habitName = habitName;
        this.habitCounter = 0;
        this.time = time;
        this.preferredMin = preferredMin;
        this.preferredHour = preferredHour;
        this.category = category;
        this.performed = false;
    }

    public int getHabitId() {
        return habitId;
    }

    public int getPreferredMin() {
        return preferredMin;
    }

    public int getPreferredHour() {
        return preferredHour;
    }

    public int getPerformingMin() {
        return performingMin;
    }

    public int getPerformingHour() {
        return performingHour;
    }

    public boolean isPerformed() {
        return performed;
    }

    public String getHabitName() {
        return habitName;
    }

    public String getHabitDescription() {
        return habitDescription;
    }

    public int getHabitCounter() {
        return habitCounter;
    }

    public String getTime() {
        return time;
    }

    public int getCategory() {
        return category;
    }
}
