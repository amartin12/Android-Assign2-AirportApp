package edu.quinnipiac.ser210.assign02tsawaittime;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 27 February 2020

This is the Help Activity class which sets the view of the help from the actionbar
 */


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }
}
