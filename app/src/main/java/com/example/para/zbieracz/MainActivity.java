package com.example.para.zbieracz;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.example.para.zbieracz.database.DataSource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final String TAG = MainActivity.class.getSimpleName();
    String LOCALE_ENGLISH = "en";
    String LOCALE_POLISH = "pl";
    String currentLocale;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    TextView dateTextView;
    Locale mLocale;
    EditText firstNameEditText, lastNameEditText;
    Button buttonNext, buttonPickDate, buttonPolish, buttonEnglish;

    Reports reports;

    Date date;
    String firstName= "", lastName= "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocale = "pl";

        setViews();

        reports = new Reports();




    }

    private void setViews() {
        firstNameEditText = (EditText) findViewById(R.id.firstname);
        lastNameEditText = (EditText) findViewById(R.id.lastname);
        firstNameEditText.setText(firstName);
        lastNameEditText.setText(lastName);
        firstNameEditText.addTextChangedListener(new TextWatcher() {
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

        lastNameEditText.addTextChangedListener(new TextWatcher() {
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


        dateTextView = (TextView) findViewById(R.id.text_view_date_picked);

        if (date != null)
        dateTextView.setText(dateFormat.format(date));

        buttonNext = (Button) findViewById(R.id.btnNext);
        buttonEnglish = (Button) findViewById(R.id.btnEnglish);
        buttonPolish = (Button) findViewById(R.id.btnPolish);
        buttonPickDate = (Button) findViewById(R.id.button_pick_date);

        buttonNext.setOnClickListener(this);
        buttonEnglish.setOnClickListener(this);
        buttonPolish.setOnClickListener(this);
        buttonPickDate.setOnClickListener(this);
    }


    public void setLanguage(String locale){
        mLocale = new Locale(locale);
        Locale.setDefault(mLocale);
        Configuration config = new Configuration();
        config.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        MainActivity.this.setContentView(R.layout.activity_main);
        setViews();
        currentLocale = locale;

    }


    public void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month ,day);
                date = newDate.getTime();
                dateTextView.setText(dateFormat.format(date));


            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();



    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnEnglish:
                setLanguage(LOCALE_ENGLISH);
                break;

            case R.id.btnPolish:
                setLanguage(LOCALE_POLISH
                );
                break;

            case R.id.btnNext:
                reports.setFirstname(firstName);
                reports.setLastname(lastName);
                reports.setDate(date);
                Log.d(TAG, "onClick: "+ reports);
                Intent intent= new Intent(MainActivity.this, PhotoActivity.class);
                intent.putExtra("report", reports);
                intent.putExtra("locale", currentLocale );
                startActivity(intent);
                break;

            case R.id.button_pick_date:
                    showDatePickerDialog();
                break;
        }

    }
}
