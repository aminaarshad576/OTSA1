package com.example.my_app_otsa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VanDetailsActivity extends AppCompatActivity {
    TextView a, b, c, d;
    Button btn_van;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.van_activity);

        a = (TextView) findViewById(R.id.editVan_color);
        b = (TextView) findViewById(R.id.editVan_model);
        c = (TextView) findViewById(R.id.editVan_no);
        d = (TextView) findViewById(R.id.editVan_type);
        btn_van = (Button) findViewById(R.id.van_detail);

        btn_van.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child("Van").child("1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String van_color = dataSnapshot.child("Van Color").getValue().toString();
                        String van_model = dataSnapshot.child("Van Model").getValue().toString();
                        String van_no = dataSnapshot.child("Van No").getValue().toString();
                        String van_type = dataSnapshot.child("Van Type").getValue().toString();
                        a.setText(van_color);
                        b.setText(van_model);
                        c.setText(van_no);
                        d.setText(van_type);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}