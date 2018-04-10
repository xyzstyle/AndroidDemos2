package com.xyz.sqlite;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MyView extends TableLayout {
	
	MyAdapter myAdapter;
	Context context;
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public void setAdapter(MyAdapter myAdapter) {
		this.myAdapter = myAdapter;
		notifyUpdate();
	}
	public void notifyUpdate() {
		this.removeAllViews();
		if (!(myAdapter == null)) {

			TableRow top = new TableRow(context);
			TextView topTitle = new TextView(context);
			topTitle.setText("My  Records");
			topTitle.setBackground(getResources().getDrawable(R.drawable.bb));
			topTitle.setGravity(1);
			TableRow.LayoutParams p = new TableRow.LayoutParams();
			p.setMargins(3, 3, 3, 3);
			p.weight = 1;
			p.span = 2;
			top.addView(topTitle, p);
			this.addView(top);
			for(int i=0;i<myAdapter.getItemsCount();i++){

				this.addView(myAdapter.getView(i));

			}

		}
	}

}
