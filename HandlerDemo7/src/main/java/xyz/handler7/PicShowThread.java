package xyz.handler7;



import android.os.Handler;

public class PicShowThread extends Thread {

	private Handler handler;
	PicShowThread(Handler handler) {
		this.handler=handler;
	}

	int what = 0;   //发送消息的what值


	@Override
	public void run() {

		super.run();
		while(true){//循环
			handler.sendEmptyMessage((what++)%4);  //发送消息
			try{
				Thread.sleep(5000);//睡眠五秒钟
			} catch(Exception e){   //捕获异常
				e.printStackTrace();//打印异常信息
			}
		}
	}
}
