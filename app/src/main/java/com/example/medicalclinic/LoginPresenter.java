package com.example.medicalclinic;

public class LoginPresenter implements Contract.Presenter{
    LoginPresenter(){

    }

    @Override
    public String getMessage(int n) {
        if(n == 1){
            return "Login Successful!";
        } else if(n == 2){
            return "Username does not exist!";
        } else if(n == 3){
            return "Invalid password!";
        } else if(n == 4){
            return "Please Enter a Username and Password!";
        } else {
            return "Error Data Could Not Load!";
        }
    }
}
