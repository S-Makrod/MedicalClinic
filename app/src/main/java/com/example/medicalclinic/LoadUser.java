package com.example.medicalclinic;

public interface LoadUser<G> {
    G get(String username);
}