package xyz.listview1;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyListView4 extends ListActivity {

    private List<Map<String, Object>> mData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("title", "G1");
        map.put("info", "google 1");
        map.put("img", R.drawable.i1);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G2");
        map.put("info", "google 2");
        map.put("img", R.drawable.i2);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G3");
        map.put("info", "google 3");
        map.put("img", R.drawable.i3);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G4");
        map.put("info", "google 4");
        map.put("img", R.drawable.i1);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G5");
        map.put("info", "google 5");
        map.put("img", R.drawable.i2);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G6");
        map.put("info", "google 6");
        map.put("img", R.drawable.i3);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G7");
        map.put("info", "google 7");
        map.put("img", R.drawable.i1);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G8");
        map.put("info", "google 8");
        map.put("img", R.drawable.i2);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G9");
        map.put("info", "google 9");
        map.put("img", R.drawable.i3);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G10");
        map.put("info", "google 10");
        map.put("img", R.drawable.i1);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G11");
        map.put("info", "google 11");
        map.put("img", R.drawable.i2);
        list.add(map);

        map = new HashMap<>();
        map.put("title", "G12");
        map.put("info", "google 12");
        map.put("img", R.drawable.i3);
        list.add(map);

        return list;
    }

    // ListView 中某项被选中后的逻辑
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Log.v("MyListView4-click", (String) mData.get(position).get("title"));
    }

    /**
     * ListView中点击按键弹出对话框
     */


    private final class ViewHolder {
        ImageView img;
        TextView title;
        TextView info;
        Button viewBtn;
    }

    public void showInfo(int position) {
        int pos = position + 1;
        new AlertDialog.Builder(MyListView4.this)
                .setTitle("我的ListView的第" + pos + "项")
                .setMessage("介绍...")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }


    private class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;


        MyAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //调用一次getView获取ListView中的一个item相对应的view

            ViewHolder holder;
            if (convertView == null) {

                holder = new ViewHolder();
                //convertView是根据vList2实例化后的一个View对象，每一个显示的item对应一个convertView
                convertView = mInflater.inflate(R.layout.vlist2,null);
                Log.v("tag", "position " + position + " convertView is null, " + "new: " + convertView);
                //holder存放实例化后的convertView对象中的子对象，每一个convertView
                holder.img = (ImageView) convertView.findViewById(R.id.img);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.info = (TextView) convertView.findViewById(R.id.info);
                holder.viewBtn = (Button) convertView.findViewById(R.id.view_btn);
                //把holder放到相对应的convertView中，每一个convertView中都存放一个holder
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();
                Log.v("tag", "position " + position + " convertView is not null, " + convertView);
            }

            //根据位置position信息重新设置convertView中的子对象的显示内容，显示内容来自mData
            holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("title"));
            holder.info.setText((String) mData.get(position).get("info"));

            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showInfo(position);
                }
            });


            return convertView;
        }


    }
}
