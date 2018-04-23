package com.xyz.receiver1;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DynamicReceiver mDynamicReceiver;
    //private SysMsgReceiver mSysMsgReceiver;

    private static final String TAG = "xyz:MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.d(TAG, "注册广播事件");
        // 注册自定义动态广播消息
        IntentFilter dynamicFilter = new IntentFilter();
        mDynamicReceiver = new DynamicReceiver();
        dynamicFilter.addAction("com.xyz.dynamic");
        registerReceiver(mDynamicReceiver, dynamicFilter);

        // 注册系统动态广播消息
        IntentFilter sysFilter;
        sysFilter = new IntentFilter();
        sysFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(mSystemReceiver, sysFilter);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_static:
                Log.d(TAG, "发送自定义静态注册广播消息");
                Intent intent = new Intent();
                intent.setAction("com.xyz.static");
                intent.putExtra("msg", "接收静态注册广播成功！");
                sendBroadcast(intent);
                break;

            case R.id.send_dynamic:
                Log.d(TAG, "发送自定义动态注册广播消息");
                Intent intent1 = new Intent();
                intent1.setAction("com.xyz.dynamic");
                intent1.putExtra("msg", "接收动态注册广播成功！");
                sendBroadcast(intent1);
                break;
            case R.id.send_system:
                Log.d(TAG, "发送系统动态注册广播消息");
                Intent airplaneIntent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                airplaneIntent.putExtra("state", !isAirplaneModeOn(getApplicationContext()));
                sendBroadcast(airplaneIntent);
                // sendBroadcast(intent2); //出错，只能系统进程发出
                break;
            default:
                break;
        }

    }

    private class DynamicReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "接收自定义动态注册广播消息");
            if (intent.getAction().equals("com.xyz.dynamic")) {
                String msg = intent.getStringExtra("msg");
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private BroadcastReceiver mSystemReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "接收系统动态注册广播消息");
            if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
                String msg = intent.getAction();
                Log.d(TAG, "onReceive: "+msg);
                Toast.makeText(context, "airplane is "+isAirplaneModeOn(getApplicationContext()), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }

    @Override
    protected void onDestroy() {

        unregisterReceiver(mDynamicReceiver);
        unregisterReceiver(mSystemReceiver);
//        unregisterReceiver(mSysMsgReceiver);
        super.onDestroy();
    }

}
