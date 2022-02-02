package com.marijamandjelak.domaci;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity2 extends AppCompatActivity {


    Database db;
    Button insert, view, edit, delete;
    EditText username, password, id;
    NotificationManagerCompat menadzer;
    int brojac = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new Database(MainActivity2.this);
        insert = (Button) findViewById(R.id.insert);
        view = (Button) findViewById(R.id.view);
        edit = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        id = (EditText) findViewById(R.id.id);
        menadzer = NotificationManagerCompat.from(MainActivity2.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean insert = db.insert(username.getText().toString().trim(), password.getText().toString().trim());
                if(insert == true) {
                    Notification notifikacija = new NotificationCompat.Builder(MainActivity2.this,KanaliZaNotifikacije.KANAL_USPESNO)
                            .setContentTitle("Dodavanje")
                            .setContentText("Uspesno ste dodali korisnika u bazu")
                            .setSmallIcon(R.drawable.ic_insert)
                            .build();
                    menadzer.notify(brojac,notifikacija);
                    brojac++;
                }
                else {
                    Notification notifikacija = new NotificationCompat.Builder(MainActivity2.this,KanaliZaNotifikacije.KANAL_NEUSPESNO)
                            .setContentTitle("Dodavanje")
                            .setContentText("Korisnik nije dodat u bazu")
                            .setSmallIcon(R.drawable.ic_insert)
                            .build();
                    menadzer.notify(brojac,notifikacija);
                    brojac++;
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = db.view();
                if(c.getCount() == 0) {
                    show("Error", "Nothing");
                    return;
                }
                StringBuffer b = new StringBuffer();
                while(c.moveToNext()) {
                    b.append("ID: "+ c.getString(0 )+ "\n");
                    b.append("USERNAME: "+ c.getString(1 )+ "\n");
                    b.append("PASSWORD: "+ c.getString(2 )+ "\n");
                }
                show("Korisnici", b.toString());
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean updated = db.update(id.getText().toString(), username.getText().toString(), password.getText().toString());
                if(updated ==true) {
                    Notification notifikacija = new NotificationCompat.Builder(MainActivity2.this,KanaliZaNotifikacije.KANAL_USPESNO)
                            .setContentTitle("Azuriranje")
                            .setContentText("Uspesno ste azurirali korisnika u bazi")
                            .setSmallIcon(R.drawable.ic_edit)
                            .build();
                    menadzer.notify(brojac,notifikacija);
                    brojac++;
                }
                else {
                    Notification notifikacija = new NotificationCompat.Builder(MainActivity2.this,KanaliZaNotifikacije.KANAL_NEUSPESNO)
                            .setContentTitle("Azuriranje")
                            .setContentText("Korisnik nije azuriran u bazi")
                            .setSmallIcon(R.drawable.ic_edit)
                            .build();
                    menadzer.notify(brojac,notifikacija);
                    brojac++;
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new android.app.AlertDialog.Builder(MainActivity2.this)
                        .setTitle("Da li je ste sigurni")
                        .setPositiveButton("DA", new DialogInterface.OnClickListener(

                        ) {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Integer deleted = db.delete(id.getText().toString());
                                if(deleted>0) {
                                    Toast.makeText(MainActivity2.this, "deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity2.this, "not deleted", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("NE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final Snackbar snackbar = Snackbar.make(view,"Odustali ste od brisanja korisnika",Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }).show();
            }


        });
    }


    public void show(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }


    public void next(View view) {
        Intent i = new Intent(MainActivity2.this,MainActivity3.class);
        startActivity(i);
    }
}