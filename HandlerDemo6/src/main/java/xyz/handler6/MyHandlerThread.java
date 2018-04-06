package xyz.handler6;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * My File Created by xyz on 2018/4/2.
 */

public class MyHandlerThread extends HandlerThread {
    Handler mHandler;
    int i;
    public MyHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 100:
                        method1();
                        break;
                    case 200:
                        getLooper().quit();
                }
            }

        };
        someOtherFunction();
    }

    @Override
    public void run() {
        super.run();

        someEndFunction();
    }

    private void someOtherFunction() {
        i+=1;
        Log.i("someOtherFunction", String.valueOf(i));
    }

    private void someEndFunction() {
        i+=1;
        Log.i("someEndFunction", String.valueOf(i));

    }

    private void method1() {
        i+=1;
        Log.i("method1", String.valueOf(i));

    }

    public synchronized Handler getHandler(){
        return mHandler;
    }
}
