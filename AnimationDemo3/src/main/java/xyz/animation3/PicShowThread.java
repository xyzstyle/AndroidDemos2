package xyz.animation3;

/**
 * My File Created by xyz on 2018/4/3.
 */


import android.os.Handler;

public class PicShowThread extends Thread {

    Handler mHandler;
    int what = 0; // 发送消息的what值

    public PicShowThread(Handler mHandler) {
        // 构造器
        this.mHandler = mHandler; // 得到activity的引用
    }

    @Override
    public void run() {
        super.run();
        while (true) {// 循环
            // sendEmptyMessageDelayed((what++)%4, 5000);
            mHandler.sendEmptyMessage((what++) % 4); // 发送消息
            try {
                Thread.sleep(6000);// 睡眠五秒钟
            } catch (Exception e) { // 捕获异常
                e.printStackTrace();// 打印异常信息
            }
        }
    }
}
