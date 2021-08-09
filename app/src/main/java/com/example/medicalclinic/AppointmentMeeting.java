package com.example.medicalclinic;

public class AppointmentMeeting extends AppointmentInfo{
    String username;
    String name;

    public AppointmentMeeting() {

    }

    public AppointmentMeeting(String username){
        this.username = username;
        //Get name from database...
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

}
