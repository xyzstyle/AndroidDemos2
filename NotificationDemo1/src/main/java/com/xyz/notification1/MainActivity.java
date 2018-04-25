package com.xyz.notification1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed1, ed2, ed3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        findViewById(R.id.button).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String title = ed1.getText().toString().trim();
        String subject = ed2.getText().toString().trim();
        String body = ed3.getText().toString().trim();

        Intent intent=new Intent( MainActivity.this, NotificationActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("subject", subject);
        intent.putExtra("body", body);
        //startActivity(intent);
        PendingIntent pending = PendingIntent.getActivity(
                getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
        mBuilder.setTicker(title)
                .setContentTitle(subject)
                .setContentInfo(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pending);
        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(22, notification);
    }
}
