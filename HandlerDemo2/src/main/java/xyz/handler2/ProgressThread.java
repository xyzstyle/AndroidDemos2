package xyz.handler2;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class ProgressThread extends Thread {
    private Handler mCallInHandler;


    ProgressThread(Handler handler) {
        mCallInHandler = handler;
    }

    @Override
    public void run() {
        int total = 100;
        int current = 0;

        while (current <= total) {
            try {
                sleep(2000); //可以在此处完成具体的任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = mCallInHandler.obtainMessage(300);
            msg.arg2 = total;
            msg.arg1 = current;
            mCallInHandler.sendMessage(msg);
            current += 10;
        }
    }
}
