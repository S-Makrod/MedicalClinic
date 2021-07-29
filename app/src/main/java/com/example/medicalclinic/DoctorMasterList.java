package com.example.medicalclinic;

import java.util.List;

public class DoctorMasterList implements MasterList<DoctorUser>, MasterListDelete<DoctorUser>{
    List<DoctorUser> d;

    @Override
    public void add(DoctorUser u) {

    }

    @Override
    public void delete(DoctorUser u) {

    }

    @Override
    public DoctorUser get(String username) {
        return null;
    }
}
