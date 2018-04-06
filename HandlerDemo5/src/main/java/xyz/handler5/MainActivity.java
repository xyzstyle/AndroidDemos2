package xyz.handler5;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "handler";

    private MyHandler mMyHandler;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tv);

        mMyHandler = new MyHandler();
        SendThread t1 = new SendThread(1);
        t1.start();
        SendThread t2 = new SendThread(2);
        t2.start();

        // * Causes the Runnable r to be added to the message queue.
        // * The runnable will be run on the thread to which this handler is
        // * attached.
        // mHandler.post(r);

    }


    private class SendThread extends Thread {
        int what;

        public SendThread(int what) {
            this.what = what;
        }

        public void run() {
            long id = Thread.currentThread().getId();
            Log.d(TAG, "SendThread id : " + id+"   what:"+what);
            Message message = new Message();
            message.what = what;
            mMyHandler.sendMessage(message);

            mMyHandler.post(new MyRunnable(id));
        }
    }

    private class MyHandler extends Handler {

        public MyHandler() {
            super();
        }

        @Override
        public void handleMessage(Message msg) {


            long id = Thread.currentThread().getId();
            Log.d(TAG, "handle Message thread id : " + id);
            switch (msg.what) {
                case 1:
                    Log.d(TAG, "handle:SendThread 1");
                    mTextView.setText("SendThread 1");
                    break;
                case 2:
                    Log.d(TAG, "handle:SendThread 2");
                    mTextView.setText("SendThread 2");
                    break;
            }
        }
    }

    private class MyRunnable implements Runnable{
        private long threadID;
        MyRunnable(long threadID) {
            this.threadID=threadID;
        }
        @Override
        public void run() {
            mTextView.setText("Runnable"+threadID);
            long id = Thread.currentThread().getId();
            Log.d(TAG, "runnable id = " + id);
        }
    }
}
