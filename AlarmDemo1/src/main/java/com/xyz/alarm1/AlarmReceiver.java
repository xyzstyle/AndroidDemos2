package com.xyz.alarm1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.d("xyz", "It's time to do something   "+intent.getStringExtra("info"));
		
		Toast.makeText(context, "It's time to do something "+intent.getStringExtra("info"), Toast.LENGTH_LONG).show();

	}

}
