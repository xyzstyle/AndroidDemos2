package xyz.handler6;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread1 extends Thread {
	public Handler mHandler;

	int i;
	public void run() {
		Looper.prepare();
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what) {
				case 100:
					method1();
					break;
				case 200:
					getLooper().quit();
				}
			}

		};
		someOtherFunction();
		Looper.loop();
		someEndFunction();
	}

	private void someOtherFunction() {
		i+=1;
		Log.i("someOtherFunction", String.valueOf(i));
	}

	private void someEndFunction() {
		i+=1;
		Log.i("someEndFunction", String.valueOf(i));

	}

	private void method1() {
		i+=1;
		Log.i("method1", String.valueOf(i));

	}


	public synchronized Handler getHandler(){
		return mHandler;
	}

}
