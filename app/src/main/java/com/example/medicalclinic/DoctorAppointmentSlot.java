package com.example.medicalclinic;

import java.util.Date;

public class DoctorAppointmentSlot implements DoctorAppointment{
    String date;
    AppointmentMeeting patient;

    public DoctorAppointmentSlot(){

    }

    public DoctorAppointmentSlot(String username, Date d) {
        date = converter(d);
        patient = new AppointmentMeeting(username);
    }

    @Override
    public String converter(Date d) {
        return Appointment.dateFormatter.format(d);
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
    public AppointmentInfo getPatient() {
        return patient;
    }

    @Override
    public void setPatient(AppointmentMeeting patient) {
        this.patient = patient;
    }
}
