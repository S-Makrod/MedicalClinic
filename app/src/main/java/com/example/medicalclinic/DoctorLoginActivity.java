package com.example.medicalclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//View Classs for Doctor Login
public class DoctorLoginActivity extends AppCompatActivity implements Contract.View {
    private TextView doctorUserName,doctorPassword;
    private Contract.Presenter presenter;
    private Contract.Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        doctorUserName=(TextView)findViewById(R.id.doctorUserNameTextBox);
        doctorPassword=(TextView)findViewById(R.id.doctorPasswordTextBox);
    }


    public void doctorSignUpButtonOnClick(View view) {
        Intent intent=new Intent(this,DoctorSelectSpecializationsActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginButtonOnClick(android.view.View view) {
        presenter = new LoginPresenter();
        model = new LoginData(doctorPassword.getText().toString(), doctorUserName.getText().toString(), presenter);
        model.executeLogin("doctors", this);
    }

    @Override
    public void loginSuccess(String message) {
        Toast.makeText(DoctorLoginActivity.this, message, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(DoctorLoginActivity.this, DoctorMainActivity.class);
        intent.putExtra("DOC_USERNAME_INTENT", model.getUsername());
        startActivity(intent);
    }

    @Override
    public void loginFailure(String message) {
        Toast.makeText(DoctorLoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}