package com.example.medicalclinic;

import java.util.List;
import java.util.LinkedList;

public class UpcomingAppointments extends AppointmentListPro{
    private List<Appointment> upcoming;

    public UpcomingAppointments(){

        upcoming = new LinkedList<>();
    }

    @Override
    public void add(Appointment a) {
        upcoming.add(a);
    }

    @Override
    public void delete(Appointment a) {
        upcoming.remove(a);
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
