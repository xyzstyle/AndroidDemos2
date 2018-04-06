package xyz.handler7;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch(msg.what){         //判断what的值
                case 0:   //what值为0时
                    mImageView.setImageResource(R.drawable.z1);break;
                case 1:   //what值为1时
                    mImageView.setImageResource(R.drawable.z2);break;
                case 2:   //what值为2时
                    mImageView.setImageResource(R.drawable.z3);break;
                case 3:   //what值为3时
                    mImageView.setImageResource(R.drawable.z4);break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView) findViewById(R.id.myImageView);       //得到ImageView的引用
        PicShowThread myThread = new PicShowThread(mHandler);          		//初始化MyThread线程
        myThread.start();

    }
}
