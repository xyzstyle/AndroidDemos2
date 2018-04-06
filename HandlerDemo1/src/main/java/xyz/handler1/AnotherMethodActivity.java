package xyz.handler1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.util.Date;

/**
 * My File Created by xyz on 2018/4/1.
 */

public class AnotherMethodActivity extends AppCompatActivity {

    MyHandler mHandler = new MyHandler(AnotherMethodActivity.this);

    private static class MyHandler extends Handler {
        WeakReference<Activity> mActivity;

        MyHandler(AnotherMethodActivity activity) {
            mActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            AnotherMethodActivity theActivity = (AnotherMethodActivity) mActivity.get();
            Log.d("xyz", "The handler thread");
            System.out.println("handler thread id--->"
                    + Thread.currentThread().getId());
            System.out.println("handler thread name--->"
                    + Thread.currentThread().getName());
            TextView tv = (TextView) theActivity.findViewById(R.id.tv);
            if (msg.what == 100) {
                Date date = new Date(System.currentTimeMillis());
                DateFormat dateFormat = DateFormat.getDateTimeInstance();
                String dateString = dateFormat.format(date);
                tv.setText(dateString);
                theActivity.mHandler.sendMessageDelayed(obtainMessage(100),
                        1000);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (mHandler != null) {
            Message msg = mHandler.obtainMessage(100);
            if (msg != null) {
                mHandler.sendMessage(msg);
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("xyz", "The Activity onResume");
        if (mHandler != null) {
            Message msg = mHandler.obtainMessage(100);
            if (msg != null)
                mHandler.sendMessageDelayed(msg, 1000);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("xyz", "The Activity onPause");
        if (mHandler != null)
            mHandler.removeMessages(100);
        super.onPause();
    }
}
