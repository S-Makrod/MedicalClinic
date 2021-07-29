package com.example.medicalclinic;

public class AppointmentMeeting extends AppointmentInfo{
    String username;
    String name;

    public AppointmentMeeting(){}

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }
}
