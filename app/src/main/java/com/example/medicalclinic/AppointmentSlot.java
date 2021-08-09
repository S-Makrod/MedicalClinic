package com.example.medicalclinic;

import java.util.Date;

public class AppointmentSlot implements Appointment{
    String date;
    AppointmentMeeting doctor;

    public AppointmentSlot(){

    }

    public AppointmentSlot(String username, Date d) {
        date = converter(d);
        doctor = new AppointmentMeeting(username);
    }

    @Override
    public String converter(Date d) {
        return dateFormatter.format(d);
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public AppointmentInfo getDoctor() {
        return doctor;
    }

    @Override
    public void setDoctor(AppointmentMeeting a) {
        this.doctor = a;
    }
}
