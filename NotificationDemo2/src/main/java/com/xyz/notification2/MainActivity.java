package com.xyz.notification2;


import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private int mId = 1;
    private int numMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.new_noti_btn).setOnClickListener(this);
        findViewById(R.id.update_noti_btn).setOnClickListener(this);

		/*
         *
		 * NotificationCompat.Builder mBuilder1 = new
		 * NotificationCompat.Builder(
		 * this).setSmallIcon(R.drawable.ic_launcher)
		 * .setContentTitle("Event tracker") .setContentText("Events received");
		 * NotificationCompat.InboxStyle inboxStyle = new
		 * NotificationCompat.InboxStyle(); String[] events = new String[6]; //
		 * Sets a title for the Inbox in expanded layout
		 * inboxStyle.setBigContentTitle("Event tracker details:");
		 *
		 * // Moves events into the expanded layout for (int i = 0; i <
		 * events.length; i++) {
		 *
		 * inboxStyle.addLine(events[i]); } // Moves the expanded layout object
		 * into the notification object. mBuilder1.setStyle(inboxStyle);
		 * NotificationManager mNotificationManager = (NotificationManager)
		 * getSystemService(Context.NOTIFICATION_SERVICE); // mId allows you to
		 * update the notification later on. mNotificationManager.notify(mId,
		 * mBuilder1.build());
		 */

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_noti_btn:
                newNotification();
                break;
            case R.id.update_noti_btn:
                updateNotification();
                break;
            default:
                break;
        }

    }

    private void newNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My notification")
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_MAX);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        Notification mNotification = mBuilder.build();
        Log.d("xyz", "flags：" + mNotification.flags);
        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        Log.d("xyz", "flags：" + mNotification.flags);
        mNotificationManager.notify(mId, mNotification);
    }

    private void updateNotification() {

        // Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(
                this).setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(android.R.drawable.stat_notify_error);

        // Start of a loop that processes data and then notifies the user

        if (numMessages > 0) {
            mNotifyBuilder.setContentText("error number is " + (numMessages))
                    .setNumber(numMessages);
        }
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra("info", "my message" + numMessages);
        PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 1,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotifyBuilder.setContentIntent(pi);

        Notification mNotification = mNotifyBuilder.build();
        // Because the ID remains unchanged, the existing notification is updated.
        numMessages++;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mNotification);

    }
}

