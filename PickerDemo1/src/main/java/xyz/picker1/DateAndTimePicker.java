package xyz.picker1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * My File Created by xyz on 2018/4/25.
 */

public class DateAndTimePicker {
    private Activity activity;
    private ViewPager viewPager;
    private  TabLayout tabLayout;
    private View[] views;
    public DateAndTimePicker(Activity activity){
        this.activity=activity;
    }

    private void init() {
        LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.date_time, null, false);
        views= new View[2];
        views[0]=new DatePicker(activity);
        views[1]=new TimePicker(activity);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter());
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Dialog dialog = new AlertDialog.Builder(activity)
                .setTitle("请选择时间日期")
                .setView(view)
                .setPositiveButton(android.R.string.ok, null).create();
        dialog.show();
    }
    private class MyPagerAdapter extends PagerAdapter {
        String[] titles= {"日期", "时间"};

        MyPagerAdapter() {

        }
        @Override
        public int getCount() {
            return views.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(views[position]);
            return views[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }


}