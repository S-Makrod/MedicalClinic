package com.example.medicalclinic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.util.Log;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    PatientLoginActivity view;

    @Mock
    LoginData model;

    @Test
    public void testPatientUsernameEmpty() {
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("pass1234");
        LoginPresenter presenter = new LoginPresenter();
        model.setPresenter(presenter);
        model = new LoginData("pass1234", "", presenter);
        model.executeLogin("patients", view);
        verify(view).loginFailure(presenter.getMessage(4));
    }

    @Test
    public void testPatientPasswordEmpty() {
        when(view.getUsername()).thenReturn("Mike");
        when(view.getPassword()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("", "Mike", presenter);
        model.executeLogin("patients", view);
        verify(view).loginFailure(presenter.getMessage(4));
    }

    @Test
    public void testPatientUsernameAndPasswordEmpty() {
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("", "", presenter);
        model.executeLogin("patients", view);
        verify(view).loginFailure(presenter.getMessage(4));
    }

    @Test
    public void testDoctorUsernameEmpty() {
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("pass1234");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("pass1234", "", presenter);
        model.executeLogin("doctors", view);
        verify(view).loginFailure(presenter.getMessage(4));
    }

    @Test
    public void testDoctorPasswordEmpty() {
        when(view.getUsername()).thenReturn("Mike");
        when(view.getPassword()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("", "Mike", presenter);
        model.executeLogin("doctors", view);
        verify(view).loginFailure(presenter.getMessage(4));
    }

    @Test
    public void testDoctorUsernameAndPasswordEmpty() {
        when(view.getUsername()).thenReturn("");
        when(view.getPassword()).thenReturn("");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("", "", presenter);
        model.executeLogin("doctors", view);
        verify(view).loginFailure(presenter.getMessage(4));
    }

    @Test
    public void testPatientUsernameDoesNotExist() {
        when(view.getUsername()).thenReturn("JaneDoe");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "JaneDoe", presenter);
        model.setUsernameExists(false);
        model.setDataLoadedSuccessfully(true);
        model.executeLoginAttempt("patients", view);
        verify(view).loginFailure(presenter.getMessage(2));
    }

    @Test
    public void testDoctorUsernameDoesNotExist() {
        when(view.getUsername()).thenReturn("DocJoe");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "DocJoe", presenter);
        model.setUsernameExists(false);
        model.setDataLoadedSuccessfully(true);
        model.executeLoginAttempt("doctor", view);
        verify(view).loginFailure(presenter.getMessage(2));
    }

    @Test
    public void testPatientInvalidPassword() {
        when(view.getUsername()).thenReturn("JaneDoe");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "JaneDoe", presenter);
        model.setUsernameExists(true);
        model.setCorrectPassword(false);
        model.setDataLoadedSuccessfully(true);
        model.executeLoginAttempt("patients", view);
        verify(view).loginFailure(presenter.getMessage(3));
    }

    @Test
    public void testDoctorInvalidPassword() {
        when(view.getUsername()).thenReturn("DoctorMike");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "DoctorMike", presenter);
        model.setUsernameExists(true);
        model.setCorrectPassword(false);
        model.setDataLoadedSuccessfully(true);
        model.executeLoginAttempt("doctors", view);
        verify(view).loginFailure(presenter.getMessage(3));
    }

    @Test
    public void testPatientSuccessfulLogin() {
        when(view.getUsername()).thenReturn("JaneDoe");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "JaneDoe", presenter);
        model.setUsernameExists(true);
        model.setCorrectPassword(true);
        model.setDataLoadedSuccessfully(true);
        model.executeLoginAttempt("patients", view);
        verify(view).loginSuccess(presenter.getMessage(1));
    }

    @Test
    public void testDoctorSuccessfulLogin() {
        when(view.getUsername()).thenReturn("DoctorMike");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "DoctorMike", presenter);
        model.setUsernameExists(true);
        model.setCorrectPassword(true);
        model.setDataLoadedSuccessfully(true);
        model.executeLoginAttempt("doctors", view);
        verify(view).loginSuccess(presenter.getMessage(1));
    }

    @Test
    public void patientUnsuccessfulDataLoad() {
        when(view.getUsername()).thenReturn("JohnDoe");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "JohnDoe", presenter);
        model.setUsernameExists(true);
        model.setCorrectPassword(true);
        model.setDataLoadedSuccessfully(false);
        model.executeLoginAttempt("patients", view);
        verify(view).loginFailure(presenter.getMessage(5));
    }

    @Test
    public void doctorsUnsuccessfulDataLoad() {
        when(view.getUsername()).thenReturn("DoctorMike");
        when(view.getPassword()).thenReturn("password");
        LoginPresenter presenter = new LoginPresenter();
        model = new LoginData("password", "DoctorMike", presenter);
        model.setUsernameExists(true);
        model.setCorrectPassword(true);
        model.setDataLoadedSuccessfully(false);
        model.executeLoginAttempt("doctors", view);
        verify(view).loginFailure(presenter.getMessage(5));
    }

}