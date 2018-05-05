package com.xyz.asynctask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xyz.asynctask.R;

public class MainActivity extends Activity {
	private Button myBtn=null;
	private TextView myText=null;
	private ProgressBar myBar=null;
    private DownTask downTask;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myText=(TextView)findViewById(R.id.myText);
        myBar=(ProgressBar)findViewById(R.id.myBar);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				downTask=new DownTask(myText,myBar);
				downTask.execute(300,100);
			}
		});
        findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                downTask.cancel(true);
            }
        });
    }
}
