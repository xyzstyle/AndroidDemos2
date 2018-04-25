package xyz.picker1;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private  TabLayout tabLayout;
    private  List<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateAndTimeDialog();

    }



    private void dateAndTimeDialog() {
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.date_time, null, false);
        views= new ArrayList<>();
        views.add(new DatePicker(MainActivity.this));
        views.add(new TimePicker(MainActivity.this));
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter());
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Dialog dialog = new AlertDialog.Builder(this)
                .setTitle("qi")
                .setView(view)
                .setPositiveButton("abc", null).create();
        dialog.show();
    }

    private class MyPagerAdapter extends PagerAdapter {
        String[] titles= {"日期", "时间"};

        MyPagerAdapter() {

        }
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
