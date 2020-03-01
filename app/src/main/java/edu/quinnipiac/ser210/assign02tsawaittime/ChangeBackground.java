package edu.quinnipiac.ser210.assign02tsawaittime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ChangeBackground extends AppCompatActivity {

    View backcolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        backcolor = this.getWindow().getDecorView();
        backcolor.setBackgroundResource(R.color.background);

    }
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
