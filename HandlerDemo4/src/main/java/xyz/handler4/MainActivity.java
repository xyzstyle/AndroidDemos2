package xyz.handler4;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    static final String TAG ="Handler";
    static final int HANDLER_TEST = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "The main thread\n");
        Log.d(TAG,"main thread id--->"+Thread.currentThread().getId());
        Log.d(TAG,"main thread name--->"+Thread.currentThread().getName());
        new myThread().start();

    }
    Handler h = new Handler(){
        public void handleMessage (Message msg) {
            switch(msg.what) {
                case HANDLER_TEST: Log.d(TAG,"The handler thread");
                    Log.d(TAG,"handler thread id--->"+Thread.currentThread().getId());
                    Log.d(TAG,"handler thread name--->"+Thread.currentThread().getName());
                    break;
            }
        }};

    class myThread extends Thread {
        public void run() {
            Log.d(TAG, "The worker thread\n");
            Log.d(TAG,"worker thread id--->"+Thread.currentThread().getId());
            Log.d(TAG,"worker thread name--->"+Thread.currentThread().getName());
            Message msg = new Message();
            msg.what = HANDLER_TEST;
            h.sendMessage(msg);
        }
    }
}
