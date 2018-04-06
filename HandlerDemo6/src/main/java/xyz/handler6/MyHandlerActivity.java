package xyz.handler6;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * My File Created by xyz on 2018/4/2.
 */

public class MyHandlerActivity extends AppCompatActivity {
    MyHandlerThread looperThread;
    Button startButton,endButton;
    Handler mCalledHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_thread);

        startButton=(Button)findViewById(R.id.startButton);
        endButton=(Button)findViewById(R.id.endButton);

        startButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                if(mCalledHandler!=null){
                    mCalledHandler.sendEmptyMessage(100);
                }
            }

        });
        endButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                if(mCalledHandler!=null){
                    mCalledHandler.sendEmptyMessage(200);
                }
            }

        });

        looperThread=new MyHandlerThread("handler");
        looperThread.start();
        do mCalledHandler=looperThread.getHandler();
        while (mCalledHandler==null);
    }


}
