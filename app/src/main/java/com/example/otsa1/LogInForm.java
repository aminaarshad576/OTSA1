package com.example.otsa1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInForm extends AppCompatActivity
{
 EditText logRegNo, logpass;
 Button logBtn, stuLogin, admLogin, driLogin, parLogin;
 TextView signupLink;
 String usertype;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_form);

        logRegNo = (EditText)findViewById(R.id.logRegNo);
        logpass = (EditText)findViewById(R.id.logpass);

        logBtn = (Button)findViewById(R.id.logBtn);

        signupLink = (TextView)findViewById(R.id.signupLink);

                signupLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LogInForm.this, SignUp_options.class));
                    }
                });

        stuLogin = (Button)findViewById(R.id.stuLogin);
        admLogin =  (Button)findViewById(R.id.admLogin);
        parLogin = (Button)findViewById(R.id.parLogin);
        driLogin = (Button)findViewById(R.id.driLogin);



         stuLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 usertype="Student";
                 stuLogin.setTextColor(Color.parseColor("#D16400"));
                 admLogin.setTextColor(Color.parseColor("#FFFFFF"));
                 parLogin.setTextColor(Color.parseColor("#FFFFFF"));
                 driLogin.setTextColor(Color.parseColor("#FFFFFF"));

             }
         });

        admLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertype="Admin";
                admLogin.setTextColor(Color.parseColor("#D16400"));
                stuLogin.setTextColor(Color.parseColor("#FFFFFF"));
                parLogin.setTextColor(Color.parseColor("#FFFFFF"));
                driLogin.setTextColor(Color.parseColor("#FFFFFF"));

            }
        });

        driLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertype="Driver";
                driLogin.setTextColor(Color.parseColor("#D16400"));
                stuLogin.setTextColor(Color.parseColor("#FFFFFF"));
                admLogin.setTextColor(Color.parseColor("#FFFFFF"));
                parLogin.setTextColor(Color.parseColor("#FFFFFF"));

            }
        });

        parLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usertype="Parent";
                parLogin.setTextColor(Color.parseColor("#D16400"));
                stuLogin.setTextColor(Color.parseColor("#FFFFFF"));
                admLogin.setTextColor(Color.parseColor("#FFFFFF"));
                driLogin.setTextColor(Color.parseColor("#FFFFFF"));

            }
        });



        logBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                FirebaseDatabase db= FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference("Users/");

                String logReg = logRegNo.getText().toString();
                String password = logpass.getText().toString();

                root.child(usertype).child(logReg).addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot)
                    {
                        if (snapshot.exists())
                        {
                            String comparePassword = snapshot.child("password").getValue(String.class);

                            if (comparePassword.equals(password) && usertype=="Student")
                            {
                                startActivity(new Intent(LogInForm.this, Parent_HomePage.class));
                            }
                            else if (comparePassword.equals(password) && usertype=="Admin")
                            {
                                startActivity(new Intent(LogInForm.this,Parent_HomePage.class));
                            }
                            else if (comparePassword.equals(password) && usertype=="Driver")
                            {
                                startActivity(new Intent(LogInForm.this, Parent_HomePage.class));
                            }
                           else if (comparePassword.equals(password) && usertype=="Parent")
                            {
                                startActivity(new Intent(LogInForm.this, Parent_HomePage.class));
                            }
                            else
                                {
                                Toast.makeText(LogInForm.this, "Invalid password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(LogInForm.this, "User Not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error)
                    {
                        Toast.makeText(LogInForm.this, "Invalid UserName or Password", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}