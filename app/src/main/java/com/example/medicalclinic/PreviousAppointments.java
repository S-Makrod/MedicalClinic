package com.example.medicalclinic;

import java.util.List;
import java.util.LinkedList;
//TODO FOR SAAD
public class PreviousAppointments implements AppointmentList{
    List<Appointment> previous;

    public PreviousAppointments(){

    }

    @Override
    public void add(Appointment a) {

    }

    @Override
    public void list() {

    }

    public List<Appointment> getPrevious() {
        return previous;
    }

    public void setPrevious(List<Appointment> previous) {
        this.previous = previous;
    }
}
