package com.example.otsa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Thread td;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        td = new Thread(){
            public void run()
            {
                try {
                    sleep(2000);
                }
                catch (Exception exc)
                {
                    exc.printStackTrace();
                }
                finally {

                    Intent itn =new Intent(MainActivity.this,LogInForm.class);
                    startActivity(itn);
                }
            }
        };
        td.start();

    }
}