package com.example.willproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {

    private Button registerBtn,gotoLoginBtn;
    ImageView profileIv;
    private DataBaseHelper openHelper;
    private SQLiteDatabase db;
    DataBaseHelper dataBaseHelper;
    private EditText regName,regPhone,regGmail,regPassword;
    public ImageView imageViewcamera;


    private Toolbar tool;
//    //PERMISSIONS
//
//    private static final int CAMERA_REQUEST_CODE = 100;
//    private static final int STORAGE_REQUEST_CODE = 101;

//    //Image Pick Constants
//    private static final int IMAGE_PICK_CAMERA_CODE = 102;
//    private static final int IMAGE_PICK_GALLERY_CODE = 103;
//
//    //Array of permissions
//    private String[] cameraPermissions;
//    private String[] storagePermissions;
//    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openHelper = new DataBaseHelper(this);

         tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        ActionBar actionBar = getSupportActionBar();

        // This will display an Up icon (<-), we will replace it with hamburger later
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.tap);



        registerBtn = findViewById(R.id.btnRegLogin);
        gotoLoginBtn = findViewById(R.id.btnGotoLogin);
        regName = findViewById(R.id.etRegName);
        regPhone = findViewById(R.id.etRegPhone);
        regGmail = findViewById(R.id.etRegGmail);
        regPassword = findViewById(R.id.etRegPassword);
        imageViewcamera = (ImageView) findViewById(R.id.imageViewcamera);





        imageViewcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{
                                    Manifest.permission.CAMERA
                            },
                            100);
                }

                byte[] UserImage = imageViewToByte(imageViewcamera);



                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");


                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image from");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent,intent});

                startActivityForResult(chooserIntent, 100);
                AddProfile(UserImage);

            }

            private  void AddProfile(byte[] UserImage){
                openHelper.insertImage(UserImage);
            }
            private byte[] imageViewToByte(ImageView imageViewcamera)
            {
                Bitmap bitmap = ((BitmapDrawable) imageViewcamera.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray = stream.toByteArray();
                return byteArray;
            }
        });




        //------------------------------------------

//        profileIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imagePickDialog();
//
//            }
//        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = regName.getText().toString().trim();
                String fPhone = regPhone.getText().toString().trim();
                String fGmail = regGmail.getText().toString().trim();
                String fPassword = regPassword.getText().toString().trim();
//                String Image = profileIv.toString().trim();

                if (fname.isEmpty() || fPassword.isEmpty() || fGmail.isEmpty() || fPhone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(fname,fPhone,fGmail,fPassword);
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                }
            }

        });

        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
        {
            //get image capture
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //set captured image to imageView
            imageViewcamera.setImageBitmap(captureImage);

            Uri selectedImage = data.getData();
            imageViewcamera.setImageURI(selectedImage);
        }
    }
    public void insertData(String fname,String fPhone,String fGmail,String fPassword){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_2,fname);
        contentValues.put(DataBaseHelper.COL_3,fPhone);
        contentValues.put(DataBaseHelper.COL_4,fGmail);
        contentValues.put(DataBaseHelper.COL_5,fPassword);
//        contentValues.put(DataBaseHelper.C_IMAGE,Image);

        long id = db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);
    }
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return super.onSupportNavigateUp();
//    }
//    private boolean checkStoragePermission(){
//        //Check if enable or not
//        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//        return result;
//    }
//
//    private void requestStoragePermission(){
//        //request permission
//        ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_REQUEST_CODE);
//
//    }
//
//    private boolean checkCameraPermission() {
//        boolean result = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
//        boolean result1 = ContextCompat.checkSelfPermission(this,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//
//        return result && result1;
//
//    }
//
//    private void requestCameraPermission(){
//        //request permission
//        ActivityCompat.requestPermissions(this, cameraPermissions, CAMERA_REQUEST_CODE);
//
//    }
//    private void imagePickDialog() {
//        String[] options = {"Camera", "Gallery"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add Image From");
//        builder.setItems(options, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                if (which==0){
//                    if (!checkCameraPermission()){
//                        requestCameraPermission();
//                    }else {
//                        pickFromCamera();
//                    }
//                }
//                else if (which == 1){
//                    if (!checkStoragePermission()){
//                        requestStoragePermission();
//                    }else {
//                        pickFromGallery();
//                    }
//                }
//            }
//        });
//
//        builder.create().show();
//    }
//
//    private void pickFromGallery() {
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
//        galleryIntent.setType("image/*"); //Only Images
//        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
//    }
//
//    private void pickFromCamera() {
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, "Image title");
//        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");
//
//        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case CAMERA_REQUEST_CODE: {
//                if (grantResults.length>0) {
//                    //if allowed returns true if not returns false
//                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//
//                    if (cameraAccepted && storageAccepted){
//                        pickFromCamera();
//                    }
//                    else{
//                        Toast.makeText(this,"Camera & Storage permissions are required", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//            break;
//            case STORAGE_REQUEST_CODE: {
//                if (grantResults.length>0) {
//                    //if allowed returns true if not returns false
//
//                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                    if (storageAccepted) {
//                        pickFromGallery();
//                    }
//                    else {
//                        Toast.makeText(this,"Storage permission is required", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//            break;
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        //Image picked will receive here
//        if (resultCode == RESULT_OK) {
//            //Image is picked
//            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
//                //image is picked from gallery
//
//                //crop image
//                CropImage.activity(data.getData())
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAspectRatio(1,1)
//                        .start(this);
//            }
//            else if (requestCode == IMAGE_PICK_CAMERA_CODE) {
//                //image is picked from Camera
//
//                //crop image
//                CropImage.activity(imageUri)
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAspectRatio(1,1)
//                        .start(this);
//            }
//            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//                //cropped image received
//                CropImage.ActivityResult result = CropImage.getActivityResult(data);
//                if (resultCode == RESULT_OK){
//                    Uri resultUri= result.getUri();
//                    imageUri = resultUri;
//                    //set image
//                    profileIv.setImageURI(resultUri );
//                }
//                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
//                    //Error
//                    Exception error = result.getError();
//                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
//
//                }
//            }
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

}
