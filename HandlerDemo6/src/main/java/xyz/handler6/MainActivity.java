package xyz.handler6;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private LooperThread1 mLooperThread;
    private Handler mCalledHandler;
    private Button issueButton,finishButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        looperTest();

    }
    private void init(){
        issueButton=(Button)findViewById(R.id.issue);
        finishButton=(Button)findViewById(R.id.finish);
        issueButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mCalledHandler!=null)
                    mCalledHandler.sendEmptyMessage(100);
            }

        });
        finishButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mCalledHandler!=null)
                    mCalledHandler.sendEmptyMessage(200);
            }
        });


    }
    private void looperTest(){
        mLooperThread=new LooperThread1();
        mLooperThread.start();
        do mCalledHandler=mLooperThread.getHandler();
        while(mCalledHandler==null);

    }
}
