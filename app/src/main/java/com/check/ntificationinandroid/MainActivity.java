package com.check.ntificationinandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "My Channel";
    private static final int NOTIFICATION_ID = 100;

    @SuppressLint({"ObsoleteSdkInt", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = getResources().getDrawable(R.drawable.active);
        Bitmap largicon = ((BitmapDrawable) drawable).getBitmap();

        NotificationManager nm = getSystemService(NotificationManager.class);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setLargeIcon(largicon)
                .setSmallIcon(R.drawable.active)
                .setContentTitle("New Message")
                .setContentText("New Message From this App")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "NEW CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            nm.createNotificationChannel(channel);
            notificationBuilder.setChannelId(CHANNEL_ID);
        }

        Notification notification = notificationBuilder.build();
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);
    }
}
