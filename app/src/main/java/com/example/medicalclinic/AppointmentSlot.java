package com.example.medicalclinic;

import java.text.ParseException;
import java.util.Date;

public class AppointmentSlot implements Appointment{
    String date;
    AppointmentInfo a;

    public AppointmentSlot(){

    }

    public AppointmentSlot(String username, Date d) {
        date = converter(d);
        a = new AppointmentMeeting(username);
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
    public AppointmentInfo getA() {
        return a;
    }

    @Override
    public void setA(AppointmentInfo a) {
        this.a = a;
    }
}
