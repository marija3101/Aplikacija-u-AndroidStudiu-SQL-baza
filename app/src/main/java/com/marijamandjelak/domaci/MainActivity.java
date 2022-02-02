package com.marijamandjelak.domaci;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, pol;
    Database db;
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(MainActivity.this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        pol = findViewById(R.id.edit);
        sharedPref = getSharedPreferences("changeData", MODE_PRIVATE);
        String gender = sharedPref.getString("gender", "");

    }

    
    public void dugme(View view) {
        if(pol.getText().toString().equals("zenski")){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("gender","zenski");
            editor.apply();
        } else if(pol.getText().toString().equals("muski")){
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("gender","muski");
            editor.apply();
        }
        if(db.provera(username.getText().toString(), password.getText().toString()) == false) {
            db.insert(username.getText().toString().trim(), password.getText().toString().trim());
        }
        if(username.getText().toString().equals("admin")&&password.getText().toString().equals("admin")) {
            Intent i = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(i);
        } else {
            Intent i = new Intent(this,MainActivity3.class);
            startActivity(i);
        }
    }

}