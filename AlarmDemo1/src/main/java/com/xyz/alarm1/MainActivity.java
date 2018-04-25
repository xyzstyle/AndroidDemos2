package com.xyz.alarm1;

import java.util.Calendar;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.set_one_button).setOnClickListener(this);
		findViewById(R.id.set_repeat_button).setOnClickListener(this);
	

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_one_button:
			myAlarmOne();
			break;
		case R.id.set_repeat_button:
			myAlarmTwo();
			break;
		default:
			break;
		}

	}

	private void myAlarmOne() {
		// 进行闹铃注册
		Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
		intent.putExtra("info", " only once");
		PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0,
				intent, 0);
		// 过10s 执行这个闹铃
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		calendar.add(Calendar.SECOND, 10);

		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
	}

	private void salf() {

	}

	
	
	private int mMinute = 51;
	private int mHour = 10;
	private long myInterval;
	private void myAlarmTwo() {
		myInterval = 10 * 1000;
		Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
		intent.putExtra("info", "from repeating");
		PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0,
				intent, 0);

		long firstTime = SystemClock.elapsedRealtime(); // 开机之后到现在的运行时间(包括睡眠时间)
		long systemTime = System.currentTimeMillis();

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		// 这里需要设置时区，否则会有8个小时的时差
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		calendar.set(Calendar.MINUTE, mMinute);
		calendar.set(Calendar.HOUR_OF_DAY, mHour);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		// 选择的定时时间
		long selectTime = calendar.getTimeInMillis();
		// 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
		if (systemTime > selectTime) {
			Toast.makeText(MainActivity.this, "设置的时间小于当前时间", Toast.LENGTH_SHORT)
					.show();
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			selectTime = calendar.getTimeInMillis();
		}
		// 计算现在时间到设定时间的时间差
		long time = selectTime - systemTime;
		firstTime += time;
		// 进行闹铃注册
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
				myInterval, sender);
		Log.i("xyz", "time ==== " + time + ", selectTime ===== " + selectTime
				+ ", systemTime ==== " + systemTime + ", firstTime === "
				+ firstTime);
		Toast.makeText(MainActivity.this, "设置重复闹铃成功! ", Toast.LENGTH_LONG)
				.show();
	}

}
