package com.example.medicalclinic;

public class DoctorDB implements Database<DoctorUser>, DatabaseDelete{
    final String child = "doctors";
    @Override
    public boolean search(String username) {
        return false;
    }

    @Override
    public void add(DoctorUser u) {


    }

    @Override
    public void delete(String username) {

    }
}
