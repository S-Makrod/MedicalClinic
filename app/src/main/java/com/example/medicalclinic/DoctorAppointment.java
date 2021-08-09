package com.example.medicalclinic;

import java.text.ParseException;
import java.util.Date;

public interface DoctorAppointment {

    String converter(Date d) throws ParseException;

    String getDate();

    void setDate(String date);

    AppointmentInfo getPatient();

    void setPatient(AppointmentMeeting a);

}
