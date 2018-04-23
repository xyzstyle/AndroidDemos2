package com.xyz.receiver1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class StaticReceiver extends BroadcastReceiver {

    private static final String TAG = "xyz:StaticReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.d(TAG, "成功接收消息,Action："+intent.getAction());
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}

