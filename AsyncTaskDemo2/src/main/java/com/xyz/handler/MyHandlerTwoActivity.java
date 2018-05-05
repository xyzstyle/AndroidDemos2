package com.xyz.handler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyHandlerTwoActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListener();
    }


    private void setListener() {
        findViewById(R.id.start_dialog).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DownTask downTask = new DownTask(createMyDialog());
                downTask.execute(1000,100);
            }
        });

    }

    ProgressDialog progressDialog = null;
    private ProgressDialog createMyDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("后台处理");
        progressDialog.setMessage("请稍等，正在处理...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        return progressDialog;
    }
}