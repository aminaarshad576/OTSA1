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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class Driver_SignUp extends AppCompatActivity {
    EditText driUsName_signUp, driPass_signUp, driPhnNo_signUp, driCnic_signUp, driAddrr_signUp;
    Button driUpload_imageBtn, driSignUpBtn;
    ImageView driProPic_signUp;
    Uri filepath;

    Bitmap bitmap;
    private FirebaseStorage storage;
    private StorageReference uploader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__sign_up);

        driProPic_signUp=(ImageView)findViewById(R.id.driProPic);

        driUpload_imageBtn= (Button)findViewById(R.id.driUpload_imagebtn);
        driSignUpBtn=(Button)findViewById(R.id.driSignBtn);

        driUpload_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(Driver_SignUp.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
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

        driSignUpBtn.setOnClickListener(new View.OnClickListener() {
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
                driProPic_signUp.setImageBitmap(bitmap);
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


        driUsName_signUp = (EditText)findViewById(R.id.driUsName);
        driPass_signUp = (EditText)findViewById(R.id.driPass);
        driPhnNo_signUp =(EditText)findViewById(R.id.driPhon);
        driCnic_signUp = (EditText)findViewById(R.id.driCnic);
        driAddrr_signUp =(EditText)findViewById(R.id.driAddrr);


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
                                DatabaseReference root = db.getReference("Users/Driver");

                                String name = driUsName_signUp.getText().toString();
                                String  password= driPass_signUp.getText().toString();
                                String phone_no = driPhnNo_signUp.getText().toString();
                                String dri_cnic = driCnic_signUp.getText().toString();
                                String address= driAddrr_signUp.getText().toString();


                                DriverDataHolder obj = new DriverDataHolder(name,password,phone_no,dri_cnic, address,uri.toString());
                                root.child(dri_cnic).setValue(obj);

                                driUsName_signUp.setText("");
                                driPass_signUp.setText("");
                                driPhnNo_signUp.setText("");
                                driCnic_signUp.setText("");
                                driAddrr_signUp.setText("");
                                driProPic_signUp.setImageResource(R.drawable.profile);

                                Toast.makeText(getApplicationContext(), "Account Created Successfully", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Driver_SignUp.this, LogInForm.class));

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