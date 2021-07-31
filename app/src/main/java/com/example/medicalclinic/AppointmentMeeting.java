package com.example.medicalclinic;

public class AppointmentMeeting extends AppointmentInfo{
    String username;
    String name;

    public AppointmentMeeting(String username){
        this.username = username;
        //Get name from database...
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
