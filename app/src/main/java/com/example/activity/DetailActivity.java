package com.example.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bean.ActivityBean;
import com.example.dao.ActivityDao;
import com.example.ruichuang.R;
import com.example.tool.ActionBarTool;

public class DetailActivity extends ActionBarTool implements OnClickListener {

	private static final String TAG = "活动详情";
	private TextView mTitle;
	private TextView mLocation;
	private TextView mTime;
	private TextView mContent;
	private Button mState;
	
	private Button mLeftArr;
	private ActivityBean activityBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		init();
		
	}

	private void init() {
		mTitle=(TextView) findViewById(R.id.d_title);
		mLocation=(TextView) findViewById(R.id.d_location);
		mTime=(TextView) findViewById(R.id.d_time);
		mContent=(TextView) findViewById(R.id.d_content);
		mState=(Button) findViewById(R.id.d_changestate);
		mState.setOnClickListener(this);
		
		mState.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View view,
					ContextMenuInfo menuInfo) {
				if(activityBean.getState().equals("未开始")){
					menu.add(0, 1, 0, "正在进行");
					menu.add(0, 2, 0, "结束");
				}
				if(activityBean.getState().equals("正在进行")){
					menu.add(0, 2, 0, "结束");
				}
				if(activityBean.getState().equals("结束")){
					Toast.makeText(DetailActivity.this, "当前状态不可修改", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		Intent intent=this.getIntent();
		
		Bundle bundle = intent.getExtras();
		
		if(bundle!=null){
			activityBean= (ActivityBean) bundle.getSerializable("bean");
			String title=activityBean.getTitle();
			String location=activityBean.getLocation();
			String time=activityBean.getTime();
			String content=activityBean.getDetails();
			String state=activityBean.getState();
			
			mTitle.setText(title);
			mLocation.setText(location);
			mTime.setText(time);
			mContent.setText(content);
			mContent.setMovementMethod(new ScrollingMovementMethod());
			mState.setText("当前状态："+state+"  点击修改状态");
		}
		
		View view = setLayoutActionBar(R.layout.mybar,TAG);
		if(view!=null){
			mLeftArr=(Button) view.findViewById(R.id.mybar_arrleft);
			mLeftArr.setOnClickListener(this);
		}
		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 0:
			changeState("未开始");
			break;
		case 1:
			changeState("正在进行");
			break;
		case 2:
			changeState("结束");
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.detail, menu);*/
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.mybar_arrleft:
			this.finish();
			break;
		case R.id.d_changestate:
			mState.showContextMenu();
			break;

		default:
			break;
		}
	}
	
	public void changeState(String state){
		activityBean.setState(state);
		new ActivityDao(this).updateActivityInfo(activityBean);
		mState.setText("当前状态："+activityBean.getState()+"  点击修改状态");
		Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
	}

}
