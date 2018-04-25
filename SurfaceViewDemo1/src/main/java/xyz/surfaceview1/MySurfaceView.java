package xyz.surfaceview1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * My File Created by xyz on 2018/4/23.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder sfh;
    //声明一个画笔
    private Paint paint;
    //文本的坐标
    private int textX = 10, textY = 10;
    //线程消亡的标识位
    private boolean flag;
    //声明一个画布
    private Canvas canvas;
    //声明屏幕的宽高

    /**
     * SurfaceView初始化函数
     */
    public MySurfaceView(Context context) {
        super(context);
        //实例SurfaceHolder
        sfh = this.getHolder();
        //为SurfaceView添加状态监听
        sfh.addCallback(this);
        //实例一个画笔
        paint = new Paint();
        //设置画笔颜色为白色
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        //设置焦点
        setFocusable(true);
    }

    /**
     * SurfaceView视图创建，响应此函数
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        flag = true;
        //实例线程
        Thread th = new Thread(this);
        //启动线程
        th.start();
    }

    /**
     * 游戏绘图
     */
    private void myDraw() {
        try {
            canvas = sfh.lockCanvas();
            if (canvas != null) {
                canvas.drawRGB(192,192, 192);

                canvas.drawText("Game", textX, textY, paint);
            }
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * 触屏事件监听
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int) event.getX();
        textY = (int) event.getY();
        return true;
    }

    /**
     * 按键事件监听
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            myDraw();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * SurfaceView视图状态发生改变，响应此函数
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * SurfaceView视图消亡时，响应此函数
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }
}
