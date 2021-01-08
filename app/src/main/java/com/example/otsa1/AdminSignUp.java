package com.example.otsa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminSignUp extends AppCompatActivity {

    EditText AdmUsName_signUp, AdmPass_signUp , AdmEmail_signUp , AdmPhnNo_signUp , AdmCnic_signUp , AdmDesig_signUp ;
    Button admSignBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        AdmUsName_signUp = findViewById(R.id.AdmUsName);
        AdmPass_signUp = findViewById(R.id.AdmPass);
        AdmEmail_signUp =findViewById(R.id.AdmEmail);
        AdmPhnNo_signUp = findViewById(R.id.AdmPhnNo);
        AdmCnic_signUp = findViewById(R.id.AdmCnic);
        AdmDesig_signUp = findViewById(R.id.AdmDesig);

        admSignBtn = (Button)findViewById(R.id.admSignBtn);

        admSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFireBase();
            }
        });
    }

    private void uploadToFireBase() {



        FirebaseDatabase db= FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference("Users/Admin");

        String adm_name = AdmUsName_signUp.getText().toString();
        String adm_password = AdmPass_signUp.getText().toString();
        String adm_email = AdmEmail_signUp.getText().toString();
        String adm_phone_no = AdmPhnNo_signUp.getText().toString();
        String adm_cnic = AdmCnic_signUp.getText().toString();
        String adm_designation = AdmDesig_signUp.getText().toString();

        AdminDataHolder adm_obj = new AdminDataHolder(adm_name,adm_password,adm_email,adm_phone_no, adm_cnic, adm_designation);
        root.child(adm_cnic).setValue(adm_obj);

        AdmUsName_signUp.setText("");
        AdmPass_signUp.setText("");
        AdmEmail_signUp.setText("");
        AdmPhnNo_signUp.setText("");
        AdmCnic_signUp.setText("");
        AdmDesig_signUp.setText("");

        Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(AdminSignUp.this, LogInForm.class));
    }
}