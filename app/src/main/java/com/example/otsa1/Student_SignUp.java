package com.example.otsa1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

public class Student_SignUp extends AppCompatActivity {
    EditText stuUsName_signUp, stuPass_signUp, stuRegNo_signUp, stuAdd_signUp, stuFatNm_signUp, stuPhnNo_signUp;
    Button stuUpload_imageBtn, stuSignUpBtn;
    ImageView stuProPic_signUp;
    public Uri filepath;

    Bitmap bitmap;
    private FirebaseStorage storage;
    private StorageReference uploader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__sign_up);

        stuProPic_signUp = (ImageView) findViewById(R.id.stuProPic);

        stuUpload_imageBtn = (Button) findViewById(R.id.stuUpload_image);
        stuSignUpBtn = (Button) findViewById(R.id.stuSignUpBtn);

        stuUpload_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(Student_SignUp.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                intent.setAction(intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent, 1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        stuSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadToFireBase();
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                stuProPic_signUp.setImageBitmap(bitmap);
            }
            catch (Exception ex)
            {

            }

        }
    }

    private void uploadToFireBase() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image ....");
        pd.show();


        stuUsName_signUp = (EditText)findViewById(R.id.stuUsName);
        stuPass_signUp = (EditText)findViewById(R.id.stuPass);
        stuRegNo_signUp = (EditText)findViewById(R.id.stuRegNo);
        stuAdd_signUp = (EditText)findViewById(R.id.stuAdd);
        stuFatNm_signUp =(EditText)findViewById(R.id.stuFatNm);
        stuPhnNo_signUp =(EditText)findViewById(R.id.stuPhnNo);


        storage = FirebaseStorage.getInstance();
        uploader = storage.getReference("Image1"+new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                FirebaseDatabase db= FirebaseDatabase.getInstance();
                                DatabaseReference root = db.getReference("Users/Student");

                                String name = stuUsName_signUp.getText().toString();
                                String  password= stuPass_signUp.getText().toString();
                                String reg_no = stuRegNo_signUp.getText().toString();
                                String address = stuAdd_signUp.getText().toString();
                                String father_name= stuFatNm_signUp.getText().toString();
                                String phone_no = stuPhnNo_signUp.getText().toString();

                                StudentDataHolder obj= new StudentDataHolder(name,password, address, father_name,phone_no,uri.toString());
                                root.child(reg_no).setValue(obj);

                                stuUsName_signUp.setText("");
                                stuPass_signUp.setText("");
                                stuRegNo_signUp.setText("");
                                stuAdd_signUp.setText("");
                                stuFatNm_signUp.setText("");
                                stuPhnNo_signUp.setText("");
                                stuProPic_signUp.setImageResource(R.drawable.profile);

                                Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Student_SignUp.this, LogInForm.class));

                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage" + (int) progressPercent + "%");
                    }
                });




    }
}













