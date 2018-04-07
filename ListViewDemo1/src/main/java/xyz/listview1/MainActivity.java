package xyz.listview1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button myListViewBtn;
    private Button mySimpleCursorAdapterBtn;
    private Button simpleAdapterBtn;
    private Button myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setListeners();
    }


    private void findViews() {
        myListViewBtn = (Button) findViewById(R.id.mylistview_btn);
        myListViewBtn.setTag(1);
        mySimpleCursorAdapterBtn = (Button) findViewById(R.id.simpleCursorAdapter_btn);
        mySimpleCursorAdapterBtn.setTag(2);
        simpleAdapterBtn = (Button) findViewById(R.id.simpleAdapter_btn);
        simpleAdapterBtn.setTag(3);
        myAdapter = (Button) findViewById(R.id.myAdapter_btn);
        myAdapter.setTag(4);
    }

    private void setListeners() {
        MyListeners myListeners = new MyListeners();
        myListViewBtn.setOnClickListener(myListeners);
        mySimpleCursorAdapterBtn.setOnClickListener(myListeners);
        simpleAdapterBtn.setOnClickListener(myListeners);
        myAdapter.setOnClickListener(myListeners);
    }

    private class MyListeners implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            int tag = (Integer) v.getTag();
            Intent intent;
            switch (tag) {
                case 1:
                    Log.d("MyListView", "myListViewBtnListener");
                    intent = new Intent(MainActivity.this, MyListView1.class);
                    startActivity(intent);
                    break;
                case 2:
                    Log.d("MyListView2", "simpleCursorAdapter");
                    intent = new Intent(MainActivity.this, MyListView2.class);
                    startActivity(intent);
                    break;
                case 3:
                    Log.d("MyListView3", "simpleAdapter");
                    intent = new Intent(MainActivity.this, MyListView3.class);
                    startActivity(intent);
                    break;
                case 4:
                    Log.d("MyListView4", "myAdapter");
                    intent = new Intent(MainActivity.this, MyListView4.class);
                    startActivity(intent);
                    break;
            }
        }

    }




}
