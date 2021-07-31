package com.example.medicalclinic;

import java.util.List;
import java.util.LinkedList;

public class UpcomingAppointments extends AppointmentListPro{
    private List<Appointment> upcoming;

    public UpcomingAppointments(){

    }

    @Override
    public void add(Appointment a) {

    }

    @Override
    public void delete(Appointment a) {

    }

    @Override
    public void list() {

    }

    @Override
    public List<Appointment> getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(List<Appointment> upcoming) {
        this.upcoming = upcoming;
    }
}
