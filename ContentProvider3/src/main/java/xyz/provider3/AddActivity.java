package xyz.provider3;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * My File Created by xyz on 2018/10/26.
 */

public class AddActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_name = (EditText) findViewById(R.id.et_name);
        et_num = (EditText) findViewById(R.id.et_num);
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(v);
            }
        });
    }

    public void add(View view) {
        String name = et_name.getText().toString();
        String num = et_num.getText().toString();
        if (name == null || num == null) {
            Toast.makeText(this, "姓名和号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("number", num);
            getContentResolver().insert(Uri.parse("content://com.cj.mycontentprovider/contact"), contentValues);
            //setResult(Activity.RESULT_OK);
            finish();
        }

    }


}
