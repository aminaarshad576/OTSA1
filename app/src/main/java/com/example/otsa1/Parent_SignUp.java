package com.example.otsa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Parent_SignUp extends AppCompatActivity {

    EditText parUsName_signUp, parPass_signUp, parPhnNo_signUp, parCnic_signUp, parChildReg_signUp;
    Button parSignBtn;
    RadioButton selectPar_signUp, selectGuard_signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent__sign_up);

        parUsName_signUp = (EditText)findViewById(R.id.parUsName);
        parPass_signUp = (EditText)findViewById(R.id.parPass);
        parPhnNo_signUp = (EditText)findViewById(R.id.parPhnNo);
        parCnic_signUp = (EditText)findViewById(R.id.parCnic);
        parChildReg_signUp = (EditText)findViewById(R.id.parChildReg);

        parSignBtn = (Button)findViewById(R.id.parSignBtn);

        selectPar_signUp = (RadioButton)findViewById(R.id.selectPar);
        selectGuard_signUp = (RadioButton)findViewById(R.id.selectGuard);


        parSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadToFireBase();
            }
        });



    }

    private void uploadToFireBase() {



        FirebaseDatabase db= FirebaseDatabase.getInstance();
        DatabaseReference root = db.getReference("Users/Parent");

        String par_name = parUsName_signUp.getText().toString();
        String par_password = parPass_signUp.getText().toString();
        String par_phone_no = parPhnNo_signUp.getText().toString();
        String par_cnic = parCnic_signUp.getText().toString();
        String parChildReg= parChildReg_signUp.getText().toString();
       String family_member="";


           if (selectPar_signUp.isChecked()) {
               family_member = "Parent";
           }

           else if (selectGuard_signUp.isChecked()) {
               family_member = "Guardian";
           }

        ParentDataHolder par_obj = new ParentDataHolder(par_name, par_password, par_phone_no, par_cnic, parChildReg, family_member);
        root.child(par_cnic).setValue(par_obj);

        parUsName_signUp.setText("");
        parPass_signUp.setText("");
        parPhnNo_signUp.setText("");
        parCnic_signUp.setText("");
        parChildReg_signUp.setText("");


        Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(Parent_SignUp.this,LogInForm.class));
    }

}