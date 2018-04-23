package com.xyz.notification1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
	private TextView tv1, tv2, tv3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		String s1=getIntent().getStringExtra("title");
		String s2=getIntent().getStringExtra("subject");
		String s3=getIntent().getStringExtra("body");
		tv1.setText(s1);
		tv2.setText(s2);
		tv3.setText(s3);
	}
}
