package com.example.medicalclinic;

import java.text.ParseException;
import java.util.Date;

public class AppointmentSlot implements Appointment{
    Date d;
    AppointmentInfo a;

    public AppointmentSlot(){

    }

    @Override
    public Date converter(Date d) throws ParseException {
        return null;
    }
}
