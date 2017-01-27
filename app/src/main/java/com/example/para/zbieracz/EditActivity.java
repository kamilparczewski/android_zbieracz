package com.example.para.zbieracz;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = EditActivity.class.getSimpleName();
    EditText lastnameEditText, firstnameEditText, dateEditText;
    ImageView photoImageView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentLocale;
    Button buttonSave, buttonDelete;
    Reports report;
    String firstName= "", lastName= "",lastName2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
        this.setContentView(R.layout.activity_edit);
        setViews();
        currentLocale = locale;

    }

    private void setViews() {

        firstnameEditText = (EditText) findViewById(R.id.firstname);
        lastnameEditText = (EditText) findViewById(R.id.lastname);
        dateEditText = (EditText) findViewById(R.id.date);
        photoImageView = (ImageView) findViewById(R.id.imageView2);
        buttonSave = (Button) findViewById(R.id.btnSave);
        buttonDelete = (Button) findViewById(R.id.btnDelete);


        firstnameEditText.setText(report.getFirstname());

        lastnameEditText.setText(report.getLastname());

        firstName = report.getFirstname();
        lastName = report.getLastname();
        lastName2 = report.getLastname();

        dateEditText.setText(dateFormat.format(report.getDate()));

        File file = new File(report.getImageUrl());
        Picasso.with(this).load(file).into(photoImageView);


        firstnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                firstName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        lastnameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                lastName = charSequence.toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buttonSave.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSave:
                List<Reports> raps = Reports.find(Reports.class, "lastName = ?", lastName2);
                Reports record = raps.get(0);
                record.delete();
                report.setFirstname(firstName);
                report.setLastname(lastName);
                report.save();
                Intent intent = new Intent(this, RecordListActivity.class);
                intent.putExtra("locale", currentLocale );
                startActivity(intent);
                break;

            case R.id.btnDelete:
                List<Reports> notes = Reports.find(Reports.class, "lastName = ?", lastName2);
                Reports report = notes.get(0);
                report.delete();
                intent = new Intent(this, RecordListActivity.class);
                intent.putExtra("locale", currentLocale );
                startActivity(intent);
                break;
        }

    }
}
