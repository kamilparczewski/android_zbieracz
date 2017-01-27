package com.example.para.zbieracz;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SummaryActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = SummaryActivity.class.getSimpleName();
    EditText lastnameEditText, firstnameEditText, dateEditText;
    ImageView photoImageView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentLocale;
    Button buttonSave;

    Reports report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        report = (Reports) getIntent().getExtras().get("report");
        Log.d(TAG, "onCreate: "+ report);
        currentLocale = getIntent().getExtras().getString("locale");

        setLanguage(currentLocale);



    }

    public void setLanguage(String locale){
        Locale mLocale = new Locale(locale);
        Locale.setDefault(mLocale);
        Configuration config = new Configuration();
        config.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_summary);
        setViews();
        currentLocale = locale;

    }

    private void setViews() {

        firstnameEditText = (EditText) findViewById(R.id.firstname);
        lastnameEditText = (EditText) findViewById(R.id.lastname);
        dateEditText = (EditText) findViewById(R.id.date);
        photoImageView = (ImageView) findViewById(R.id.imageView2);
        buttonSave = (Button) findViewById(R.id.btnSave);


        firstnameEditText.setText(report.getFirstname());

        lastnameEditText.setText(report.getLastname());
        dateEditText.setText(dateFormat.format(report.getDate()));

        File file = new File(report.getImageUrl());
        Picasso.with(this).load(file).into(photoImageView);

        buttonSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        report.save();
        Intent intent = new Intent(this, RecordListActivity.class);
        intent.putExtra("locale", currentLocale );
        finish();
        startActivity(intent);


    }
}
