package com.marijamandjelak.domaci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity3 extends AppCompatActivity {
    EditText datum;
    SharedPreferences sharedPref;
    RelativeLayout background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        sharedPref = getSharedPreferences("changeData", MODE_PRIVATE);
        String gender = sharedPref.getString("gender", "");
        datum = (EditText) findViewById(R.id.datum);


        background = (RelativeLayout) findViewById(R.id.pozadina);
        if(gender.equals("zenski")){
            background.setBackgroundColor(getResources().getColor(R.color.novaboja2));
            datum.setText("Vas kolokvijum je zakazan za 11.01.2022.");
            datum.setTextColor(getResources().getColor(R.color.novaboja3));
        } else if(gender.equals("muski")){
            background.setBackgroundColor(getResources().getColor(R.color.novaboja3));
            datum.setText("Vas kolokvijum je zakazan za 13.01.2022.");
            datum.setTextColor(getResources().getColor(R.color.novaboja2));
        }
    }




}