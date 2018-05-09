package xyz.demos2;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

import xyz.time.DateAndTimePicker;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DateAndTimePicker picker = new DateAndTimePicker(MainActivity.this, null);
        picker.setOnMyTimeChangedListener(new DateAndTimePicker.OnMyTimeChangedListener() {
            @Override
            public void onSelected(Calendar calendar) {
                TextView timeTv = (TextView) findViewById(R.id.tv_time);
                timeTv.setText(calendar.get(Calendar.DAY_OF_MONTH)+"");
            }

            @Override
            public void onCanceled() {

            }
        });
        picker.show();
    }
}
