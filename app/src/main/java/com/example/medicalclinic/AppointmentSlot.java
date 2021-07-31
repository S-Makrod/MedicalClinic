package com.example.medicalclinic;

import java.text.ParseException;
import java.util.Date;

public class AppointmentSlot implements Appointment{
    String date;
    AppointmentInfo a;

    public AppointmentSlot(String username, Date d) throws ParseException {
        date = converter(d);
        a = new AppointmentMeeting(username);
    }

    @Override
    public String converter(Date d) throws ParseException {
        return null;
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
