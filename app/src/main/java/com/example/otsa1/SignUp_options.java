package com.example.otsa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUp_options extends AppCompatActivity {
 Button signBtn1_stu, signBtn2_dri, signBtn3_adm, signBtn4_par;
 TextView loginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_options);

        signBtn1_stu = (Button)findViewById(R.id.signBtn1);
        signBtn2_dri = (Button)findViewById(R.id.signBtn2);
        signBtn3_adm = (Button)findViewById(R.id.signBtn3);
        signBtn4_par = (Button)findViewById(R.id.signBtn4);

        loginLink = (TextView)findViewById(R.id.loginLink);

        signBtn1_stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_options.this, Student_SignUp.class));
            }
        });

        signBtn2_dri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_options.this, Driver_SignUp.class));
            }
        });

        signBtn3_adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_options.this, AdminSignUp.class));
            }
        });

        signBtn4_par.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_options.this, Parent_SignUp.class));
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_options.this, LogInForm.class));
            }
        });
    }
}