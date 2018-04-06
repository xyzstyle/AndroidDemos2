package xyz.handler3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyView myView;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new MyView(this);
        setContentView(myView);

        mHandler = new Handler();

        mHandler.post(new Runnable() {

            @Override
            public void run() {

                myView.invalidate();

                mHandler.postDelayed(this, 5);

            }

        });


    }



    private class MyView extends View {

        private float x = 0f;

        public MyView(Context context) {

            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);

            x += 1;

            Paint mPaint = new Paint();

            mPaint.setColor(Color.BLUE);

            canvas.drawRect(x, 80, x + 40, 96, mPaint);

            canvas.drawRect(x, 108, x + 40, 124, mPaint);

            canvas.drawCircle(x + 20, 102, 12, mPaint);

            canvas.drawRect(x + 20, 100, x + 40, 104, mPaint);

            mPaint.setColor(Color.RED);

            canvas.drawRect(x, 160, x + 40, 176, mPaint);

            canvas.drawRect(x, 188, x + 40, 204, mPaint);

            canvas.drawCircle(x + 20, 182, 12, mPaint);

            canvas.drawRect(x + 20, 180, x + 40, 184, mPaint);

            mPaint.setColor(Color.BLACK);

            canvas.drawRect(x, 240, x + 40, 256, mPaint);

            canvas.drawRect(x, 268, x + 40, 284, mPaint);

            canvas.drawCircle(x + 20, 262, 12, mPaint);

            canvas.drawRect(x + 20, 260, x + 40, 264, mPaint);

            mPaint.setColor(Color.GREEN);

            canvas.drawRect(x, 320, x + 40, 336, mPaint);

            canvas.drawRect(x, 348, x + 40, 364, mPaint);

            canvas.drawCircle(x + 20, 342, 12, mPaint);

            canvas.drawRect(x + 20, 340, x + 40, 344, mPaint);

        }

    }
}
