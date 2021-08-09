package com.example.medicalclinic;

public abstract class AppointmentInfo {
    String username;
    String name;

    public abstract String getUsername();
    public abstract void setUsername(String username);
    public abstract String getName();
    public abstract void setName(String name);
}
