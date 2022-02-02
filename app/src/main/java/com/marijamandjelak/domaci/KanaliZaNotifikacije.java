package com.marijamandjelak.domaci;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;

public class KanaliZaNotifikacije extends Application {
    public static final String KANAL_USPESNO = "kanal_u";
    public static final String KANAL_NEUSPESNO = "kanal_n";

    @Override
    public void onCreate() {
        super.onCreate();
        kreiranje();
    }

    public void kreiranje(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel kanal_u= new NotificationChannel(KANAL_USPESNO,"kanal_u", NotificationManager.IMPORTANCE_LOW);
            kanal_u.enableVibration(false);
            kanal_u.setDescription("Uspesne notifikacije");
            NotificationChannel kanal_n = new NotificationChannel(KANAL_NEUSPESNO,"kanal_n", NotificationManager.IMPORTANCE_HIGH);
            kanal_n.enableVibration(true);
            kanal_n.setDescription("Neuspesne notifikacije");

            NotificationManager menadzer = getSystemService(NotificationManager.class);
            menadzer.createNotificationChannel(kanal_u);
            menadzer.createNotificationChannel(kanal_n);

        }
    }
}
