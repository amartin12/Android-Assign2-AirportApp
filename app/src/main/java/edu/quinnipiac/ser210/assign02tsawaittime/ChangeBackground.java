package edu.quinnipiac.ser210.assign02tsawaittime;

/*
Authors: Alexandra Martin, Megan Forster
Professor Ruby
SER 210 Android Development
Due: 27 February 2020

This is the ChangeBackground Activity class which holds method to help change background color the app
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ChangeBackground extends AppCompatActivity {

    View backcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        //Getting the view of the background color
        backcolor = this.getWindow().getDecorView();
        backcolor.setBackgroundResource(R.color.background);

    }
    //Methods to change the background color with buttons corresponding to the color
    public void setLightBlue(View view){
        backcolor.setBackgroundResource(R.color.lightBlue);
    }
    public void setLightOrange(View view){
        backcolor.setBackgroundResource(R.color.lightOrange);
    }
    public void setLightPurple(View view){
        backcolor.setBackgroundResource(R.color.lightPurple);
    }
}
