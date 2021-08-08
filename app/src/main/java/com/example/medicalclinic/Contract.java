package com.example.medicalclinic;

public interface Contract {
    public interface Model{
        void executeLogin(String db, Contract.View view);
        String getPassword();
        String getUsername();
    }

    public interface Presenter{
        String getMessage(int n);
    }

    public interface View{
        void LoginButtonOnClick(android.view.View view);
        void loginSuccess(String message);
        void loginFailure(String message);
    }
}
