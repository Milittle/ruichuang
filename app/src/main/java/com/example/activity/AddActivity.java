package com.example.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bean.ActivityBean;
import com.example.bean.UserBean;
import com.example.dao.ActivityDao;
import com.example.ruichuang.R;
import com.example.tool.ActionBarTool;

import java.util.Calendar;

public class AddActivity extends ActionBarTool implements OnClickListener {
	protected static final String TAG = "AddActivity";
	private static final String MAINTITLE="添加活动";
	private ActivityBean activityBean;
	private EditText mAddTitle;
	private TextView mAddDate;
	private TextView mAddTime;
	private EditText mAddLocation;
	private EditText mAddDep;
	private EditText mAddDetails;
	private Button mLeftArr;
	private Button mAddSubmit;
	private UserBean userBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		init();
	}
	private void init() {
		
		Intent intent = getIntent();
		Bundle bundle=intent.getExtras();
		userBean = (UserBean) bundle.getSerializable(UserBean.sUser);
		
		activityBean=new ActivityBean("","","","","","");
		mAddTitle=(EditText) findViewById(R.id.add_title);
		mAddDate=(TextView) findViewById(R.id.add_date);
		mAddTime=(TextView) findViewById(R.id.add_time);
		mAddLocation=(EditText) findViewById(R.id.add_location);
		mAddDep=(EditText) findViewById(R.id.add_dep);
		mAddDetails=(EditText) findViewById(R.id.add_details);
		mAddSubmit=(Button) findViewById(R.id.add_submit);
		
		mAddSubmit.setOnClickListener(this);
		
		View view = setLayoutActionBar(R.layout.mybar,MAINTITLE);
		if(view!=null){
			mLeftArr=(Button) view.findViewById(R.id.mybar_arrleft);
			mLeftArr.setOnClickListener(this);
		}
		
		mAddDate.setText("请选择日期");
		
		mAddTime.setText("请选择时间");
		
		mAddDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Log.i(TAG, "mAddDate");
				Calendar c=Calendar.getInstance(); 
				new DatePickerDialog(AddActivity.this,new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker arg0, int year, int month, int day) {
						mAddDate.setText(year+"-"+(month+1)+"-"+day);
					}
				},c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		mAddTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Calendar c=Calendar.getInstance();
				new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker arg0, int hour, int minute) {
						mAddTime.setText(hour+":"+minute);
					}
				}, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
			}
		});
		
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.mybar_arrleft:
			this.finish();
			break;
			
		case R.id.add_submit:
			
			if("".equals(mAddTitle.getText().toString())){
				Toast.makeText(this, "标题不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("请选择日期".equals(mAddDate.getText().toString())){
				Toast.makeText(this, "请选择日期", Toast.LENGTH_SHORT).show();
				return;
			}
			if("请选择时间".equals(mAddTime.getText().toString())){
				Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(mAddLocation.getText().toString())){
				Toast.makeText(this, "地点不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(mAddDep.getText().toString())){
				Toast.makeText(this, "主办人不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(mAddDetails.getText().toString())){
				Toast.makeText(this, "详情不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			activityBean.setUserId(userBean.getId());
			activityBean.setTitle(mAddTitle.getText().toString());
			activityBean.setTime(mAddDate.getText().toString()+" "+mAddTime.getText().toString());
			activityBean.setLocation(mAddLocation.getText().toString());
			activityBean.setState("未开始");
			activityBean.setDep(mAddDep.getText().toString());
			activityBean.setDetails(mAddDetails.getText().toString());
			
			new ActivityDao(this).insertActivityInfo(activityBean);
			
			Toast.makeText(this, "添加活动成功", Toast.LENGTH_SHORT).show();
			
			Bundle bundle=new Bundle();
			bundle.putSerializable(UserBean.sUser, userBean);
			Intent intent=new Intent();
			intent.setClass(AddActivity.this,MyActivity.class);
			intent.putExtras(bundle);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			this.finish();
			break;

		default:
			break;
		}
	}

}
