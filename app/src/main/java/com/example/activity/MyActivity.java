package com.example.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.bean.ActivityBean;
import com.example.bean.UserBean;
import com.example.dao.ActivityDao;
import com.example.ruichuang.R;
import com.example.tool.ActionBarTool;

import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends ActionBarTool implements OnItemClickListener, OnClickListener {

	private static final String TAG = "我的活动列表";
	private static int pos=-1;
	private Button mLeftArr;
	private Button mRightArr;
	private ListView mlv;
	private SimpleAdapter mLvAdapter;
	private MyAdapter mMyAdapter;
    static List<ActivityBean> mActivitys=new ArrayList<ActivityBean>();
	
	private List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
	private UserBean bean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		init();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		data.clear();
		super.onResume();
		mActivitys=new ActivityDao(this).getList(bean.getId());
		for(int i=0;i<mActivitys.size();i++){
			Map<String,Object> mLvData=new HashMap<String,Object>();
			ActivityBean bean=mActivitys.get(i);
			String title=bean.getTitle();
			String location=bean.getLocation();
			String time=bean.getTime();
			String dep=bean.getDep();
			String state=bean.getState();
			
			mLvData.put("title", title);
			mLvData.put("location",location);
			mLvData.put("time",time);
			mLvData.put("dep",dep);
			mLvData.put("state",state);
			
			data.add(mLvData);
		}
		Log.i(TAG, ""+mActivitys.size());
		mMyAdapter.notifyDataSetChanged();
	}

	private void init() {
		
		Intent intent = getIntent();
		Bundle bundle=intent.getExtras();
		bean = (UserBean) bundle.getSerializable(UserBean.sUser);
		
		mActivitys=new ActivityDao(this).getList(bean.getId());
		
		
		mlv=(ListView) findViewById(R.id.activity_list);
		
		this.registerForContextMenu(mlv);
		
		/*mLvAdapter=new SimpleAdapter(
				MyActivity.this,
				data,
				R.layout.myactivity,
				new String[]{"title","location","time","dep","state"},
				new int[]{R.id.tv_title,R.id.tv_location,R.id.tv_time,R.id.tv_dep,R.id.tv_state}
				);*/
		
		
		mMyAdapter=new MyAdapter(MyActivity.this,data);
		
		
		mlv.setAdapter(mMyAdapter);
		
		mlv.setOnItemClickListener(this);
		
		View view = setLayoutActionBar(R.layout.mybar,TAG);
		if(view!=null){
			mLeftArr=(Button) view.findViewById(R.id.mybar_arrleft);
			mRightArr=(Button) view.findViewById(R.id.mybar_arrright);
			mRightArr.setBackgroundResource(R.drawable.add);
			mRightArr.setOnClickListener(this);
			mLeftArr.setOnClickListener(this);
		}
		
		
	}
	
	private class MyAdapter extends BaseAdapter{

		private List<Map<String,Object>> data;
		private Context context;
		
		public MyAdapter(Context context,List<Map<String,Object>> data){
			this.context=context;
			this.data=data;
		}
		
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if(view==null){
				view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.myactivity, null);
			}
			
			TextView tv_title=(TextView) view.findViewById(R.id.tv_title);
			TextView tv_location=(TextView) view.findViewById(R.id.tv_location);
			TextView tv_time=(TextView) view.findViewById(R.id.tv_time);
			TextView tv_dep=(TextView) view.findViewById(R.id.tv_dep);
			TextView tv_state=(TextView) view.findViewById(R.id.tv_state);
			
			tv_title.setText(data.get(position).get("title").toString());
			tv_location.setText(data.get(position).get("location").toString());
			tv_time.setText(data.get(position).get("time").toString());
			tv_dep.setText(data.get(position).get("dep").toString());
			tv_state.setText(data.get(position).get("state").toString());
			
			
			return view;
		}
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater mi=this.getMenuInflater();
		mi.inflate(R.menu.mymenu, menu);
		pos=((AdapterContextMenuInfo)menuInfo).position;
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.context_delete:
			if(pos!=-1){
				ActivityBean temp=mActivitys.get(pos);
				ActivityDao activityDao=new ActivityDao(this);
				activityDao.deleteActivityInfo(temp.getId());
				mActivitys.clear();
				mActivitys.addAll(activityDao.getList(bean.getId()));
				data.clear();
				for(int i=0;i<mActivitys.size();i++){
					Map<String,Object> mLvData=new HashMap<String,Object>();
					ActivityBean bean=mActivitys.get(i);
					String title=bean.getTitle();
					String location=bean.getLocation();
					String time=bean.getTime();
					String dep=bean.getDep();
					String state=bean.getState();
					
					mLvData.put("title", title);
					mLvData.put("location",location);
					mLvData.put("time",time);
					mLvData.put("dep",dep);
					mLvData.put("state",state);
					
					data.add(mLvData);
				}
				mMyAdapter.notifyDataSetChanged();
				Log.i(TAG, "onContextItemSelected"+mActivitys.size());
			}
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.my, menu);*/
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
		ActivityBean activityBean = mActivitys.get(position);
		
		Intent intent=new Intent(MyActivity.this,DetailActivity.class);
		
		Bundle bundle=new Bundle();
		
		bundle.putSerializable("bean", activityBean);
		intent.putExtras(bundle);
		this.startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.mybar_arrleft:
			this.finish();
			break;
		case R.id.mybar_arrright:
			Bundle bundle=new Bundle();
			bundle.putSerializable(UserBean.sUser, bean);
			Intent intent=new Intent();
			intent.setClass(MyActivity.this,AddActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

}
