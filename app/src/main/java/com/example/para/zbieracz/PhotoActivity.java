package com.example.para.zbieracz;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_TAKE_PHOTO = 2;
    public static final String TAG = PhotoActivity.class.getSimpleName();
    String currentLocale;
    Reports reports;
    Button addPhotoButton, btnNext;
    ImageView photoImageView;
    String mCurrentPhotoPath= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Bundle extras = getIntent().getExtras();
        currentLocale = (String) extras.get("locale");
        reports = (Reports) extras.get("report");

        setLanguage(currentLocale);



    }


    public void setLanguage(String locale){
        Locale mLocale = new Locale(locale);
        Locale.setDefault(mLocale);
        Configuration config = new Configuration();
        config.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_photo);
        setViews();
        currentLocale = locale;

    }

    private void setViews() {

        photoImageView = (ImageView) findViewById(R.id.imageview_photo);
        addPhotoButton = (Button) findViewById(R.id.button_add_photo);
        btnNext = (Button) findViewById(R.id.btnNext);

        addPhotoButton.setOnClickListener(this);
        btnNext.setOnClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {


            Uri imageUri = data.getData();

            data.getData();

            try {
                saveImageFromUri(imageUri);


            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File(mCurrentPhotoPath);
            Log.d(TAG, mCurrentPhotoPath);
            Picasso.with(this).load(file).into(photoImageView);
            reports.setImageUrl(mCurrentPhotoPath);




    }


    }

    public void saveImageFromUri(Uri imageUri) throws IOException {
        String path = Environment.getExternalStorageDirectory().getPath();
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        FileOutputStream outputStream;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHH:mm");

        String filename = "IMG_" + dateFormat.format(new Date());
        File file = new File(path, filename+ ".jpg");


        outputStream = new FileOutputStream(file);

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        mCurrentPhotoPath = file.getAbsolutePath();

        outputStream.flush();
        outputStream.close();









    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button_add_photo:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

                break;

            case R.id.btnNext:

                Intent intent = new Intent(this, SummaryActivity.class);
                intent.putExtra("report",reports);
                intent.putExtra("locale", currentLocale);
                startActivity(intent);



                break;

        }
    }
}
