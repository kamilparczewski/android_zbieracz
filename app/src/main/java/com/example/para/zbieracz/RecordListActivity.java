package com.example.para.zbieracz;

import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.AlphabeticIndex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Locale;

public class RecordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    String currentLocale;
    List<Reports> reportsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_list);
        currentLocale = getIntent().getExtras().getString("locale");

        setLanguage(currentLocale);
        reportsList = Reports.listAll(Reports.class);
        listView = (ListView) findViewById(R.id.list_records);
        ArrayAdapter<Reports> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reportsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        Reports reports = reportsList.get(position);

        Intent intent= new Intent(RecordListActivity.this, EditActivity.class);
        intent.putExtra("report", reports);
        intent.putExtra("locale", currentLocale );
        startActivity(intent);


    }

    public void setLanguage(String locale){
        Locale mLocale = new Locale(locale);
        Locale.setDefault(mLocale);
        Configuration config = new Configuration();
        config.locale = mLocale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_record_list);
        currentLocale = locale;

    }
    public void onClickAdd(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("locale", currentLocale );
        startActivity(intent);
    }

}
