package com.example.medicalclinic;

public abstract class User {
    String name, password, gender, username;

    public abstract String getName();

    public abstract void setName(String name);

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract String getGender();

    public abstract void setGender(String gender);

    public abstract String getUsername();

    public abstract void setUsername(String username);

    public abstract void update_appointments();
}
