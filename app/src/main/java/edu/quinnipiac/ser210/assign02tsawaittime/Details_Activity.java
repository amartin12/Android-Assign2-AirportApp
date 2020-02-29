package edu.quinnipiac.ser210.assign02tsawaittime;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 29 February 2020

This is the Details Activity class which is accessed after selecting an airport on the Main Activty page.
Here the user will see the details from the Airport they selected.
 */

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.TextView;

public class Details_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String airportDetails = (String) getIntent().getExtras().get("AIRPORT_DETAILS");

        TextView textView = (TextView) findViewById(R.id.details);

        textView.setText(airportDetails);


        Log.d("DEBUG ","In Detail Activ");
        //Log.d("DEBUG ", airportDetails);



    }

}
