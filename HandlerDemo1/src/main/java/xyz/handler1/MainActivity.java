package xyz.handler1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Handler mHandler= new Handler(){
        public void handleMessage (Message msg) {
            switch(msg.what) {
                case 100:
                    Log.d("xyz","The handler thread");
                    System.out.println("handler thread id--->"+Thread.currentThread().getId());
                    System.out.println("handler thread name--->"+Thread.currentThread().getName());
                    TextView tv=(TextView)findViewById(R.id.tv);
                    Date date=new Date(System.currentTimeMillis());
                    DateFormat dateFormat=DateFormat.getDateTimeInstance();
                    tv.setText(dateFormat.format(date));
                    sendMessageDelayed(obtainMessage(100),1000);
                    break;
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //makeClock();
        if(mHandler!=null){
            Message msg=mHandler.obtainMessage(100);
            if(msg!=null){
                mHandler.sendMessage(msg);
            }
        }
        setContentView(R.layout.activity_main);
    }
    /*private void makeClock(){
    	 mHandler= new Handler(){
    	    	public void handleMessage (Message msg) {
    	    		switch(msg.what) {

    	    		case 100: Log.d("","The handler thread");
    	    		TextView tv=(TextView)findViewById(R.id.tv);
    	    		Date date=new Date(System.currentTimeMillis());
    	    		tv.setText(date.toLocaleString());
    	    		sendMessageDelayed(obtainMessage(100),1000);
    	    		System.out.println("handler thread id--->"+Thread.currentThread().getId());
    	            System.out.println("handler thread name--->"+Thread.currentThread().getName());
    	    	    break;
    	    	    }
    	    		}
    	    	};
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("xyz","The Activity onResume");
        if(mHandler != null){
            Message msg = mHandler.obtainMessage(100);
            if(msg != null)
                mHandler.sendMessageDelayed(msg, 1000);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("xyz","The Activity onPause");
        if(mHandler != null)
            mHandler.removeMessages(100);
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("xyz","The Activity stop");
        super.onStop();
    }
}
