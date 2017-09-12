package com.example.tool;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ruichuang.R;

public class ActionBarTool extends Activity {
	public View setLayoutActionBar(int layoutId,String TAG){
		ActionBar actionBar=this.getActionBar();
		if(actionBar!=null){
			actionBar.setTitle("");
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowCustomEnabled(true);
			LayoutInflater li=(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			View view = li.inflate(layoutId, new LinearLayout(this),false);
			
			TextView temp=(TextView) view.findViewById(R.id.mybar_text);
			
			temp.setText(TAG);
			
			LayoutParams layout=new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
			
			actionBar.setCustomView(view,layout);
			return view;
		}
		return null;
	}

}
