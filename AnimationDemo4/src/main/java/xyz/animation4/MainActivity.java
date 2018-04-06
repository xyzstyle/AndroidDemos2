package xyz.animation4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView myImageView;
    AnimationSet animationSet1;
    AnimationSet animationSet2;
    Animation myAnimation3;
    Animation myAnimation4;
    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) { // 判断what的值
                case 0: // what值为0时
                    myImageView.setImageResource(R.drawable.z1);
                    myImageView.startAnimation(animationSet1);
                    break;
                case 1: // what值为1时
                    myImageView.setImageResource(R.drawable.z2);
                    myImageView.startAnimation(animationSet2);

                    break;
                case 2: // what值为2时
                    myImageView.setImageResource(R.drawable.z3);
                    myImageView.startAnimation(myAnimation3);// 启动动画
                    break;
                case 3: // what值为3时
                    myImageView.setImageResource(R.drawable.z4);
                    myImageView.startAnimation(myAnimation4);// 启动动画
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // 得到ImageView的引用
        myImageView = (ImageView) findViewById(R.id.myImageView);

        // 动画初始化
        prepareAnimationForPic1();
        prepareAnimationForPic2();
        prepareAnimationForPic3();
        prepareAnimationForPic4();
        myImageView.setImageResource(R.drawable.z1);
        myImageView.startAnimation(animationSet1);

    }

    private void prepareAnimationForPic1() {
        // 创建一个AnimationSet对象
        animationSet1 = new AnimationSet(true);
        // 创建一个AlphaAnimation对象
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        // 设置动画执行的时间（单位：毫秒）
        alphaAnimation.setDuration(1500);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1, 0);
        alphaAnimation1.setStartOffset(4500);
        alphaAnimation1.setDuration(1500);
        // 将AlphaAnimation对象添加到AnimationSet当中
        animationSet1.addAnimation(alphaAnimation);
        animationSet1.addAnimation(alphaAnimation1);
        animationSet1.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                myHandler.sendEmptyMessage(1);
            }
        });

    }

    private void prepareAnimationForPic2() {

        animationSet2 = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        // 设置动画执行的时间（单位：毫秒）
        alphaAnimation.setDuration(2000);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF,
                0.2f);
        rotateAnimation.setDuration(2000);
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(1, 1);
        alphaAnimation1.setDuration(3000);
        alphaAnimation1.setStartOffset(2000);

        // rotateAnimation.setStartOffset(301);
        animationSet2.addAnimation(alphaAnimation);
        animationSet2.addAnimation(rotateAnimation);

        animationSet2.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {

                myHandler.sendEmptyMessage(2);
            }
        });

    }

    private void prepareAnimationForPic3() {
        // 由xml文件生成Animation对象
        myAnimation3 = AnimationUtils.loadAnimation(this, R.anim.anim1);
        myAnimation3.setAnimationListener(new AnimationListener() {

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                myHandler.sendEmptyMessage(3);
            }
        });
    }

    private void prepareAnimationForPic4() {
        // 由xml文件生成Animation对象
        myAnimation4 = AnimationUtils.loadAnimation(this, R.anim.anim2);
        myAnimation4.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {

            }

            public void onAnimationRepeat(Animation animation) {

            }

            public void onAnimationEnd(Animation animation) {
                myHandler.sendEmptyMessage(0);
            }
        });
    }
}
