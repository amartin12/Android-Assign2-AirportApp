package edu.quinnipiac.ser210.assign02tsawaittime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ChangeBackground extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.background);

    }

    public void setLightBlue(View view){
        view.setBackgroundResource(R.color.lightBlue);
    }
    public void setLightOrange(View view){
        view.setBackgroundResource(R.color.lightOrange);
    }
    public void setLightPurple(View view){
        view.setBackgroundResource(R.color.lightPurple);
    }
}
