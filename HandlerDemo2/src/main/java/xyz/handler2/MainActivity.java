package xyz.handler2;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog = null;
    ProgressThread progressThread = null;

    private Handler mHandler = new Handler(){
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 300:
                    if(msg.arg2 == 0 || msg.arg1 == msg.arg2){

                        progressDialog.dismiss();
                    }else{
                        progressDialog.setProgress(msg.arg1*100/msg.arg2);
                    }
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListener();
    }


    private void setListener(){
        Button b = (Button)findViewById(R.id.start_dialog);
        Button.OnClickListener l = new Button.OnClickListener(){

            public void onClick(View v) {
                createDialog();
                progressDialog.show();
                progressThread = new ProgressThread(mHandler);
                progressThread.start();
            }
        };
        b.setOnClickListener(l);
    }

    private void createDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("后台处理");
        progressDialog.setMessage("请稍等，正在处理...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
    }
}
