package com.example.activity;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.bean.UserBean;
import com.example.dao.UserDao;
import com.example.ruichuang.R;
import com.example.tool.ActionBarTool;
import com.example.tool.MyDialog;

public class MainActivity extends ActionBarTool implements OnClickListener {

	private static String TAG="我的";
	
	private static String T="MainActivity";
	
	private int SIGN=0;
	
	
	private Button mName;
	private Button mTrueName;
	private Button mSex;
	private Button mSchool;
	private Button mProfession;
	private TableRow mMyActivity;
	private Button mExit;
	private TextView mNameText;
	private TextView mTrueNameTExt;
	private TextView mSexText;
	private TextView mSchoolText;
	private TextView mProfessionText;
	private TextView mMyactivityText;
	private Button mButtonMyActivity;
	
	UserBean bean;
	
	private Button mLeftArr;
	
	private TextView mMyBarText;
	
	SharedPreferences sf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		sf=getSharedPreferences("login", Context.MODE_PRIVATE);
	}

	private void init() {
		mName=(Button) findViewById(R.id.b_name);
		mTrueName=(Button) findViewById(R.id.b_truename);
		mSex=(Button) findViewById(R.id.b_sex);
		mSchool=(Button) findViewById(R.id.b_school);
		mProfession=(Button) findViewById(R.id.b_profession);
		mMyActivity= (TableRow) findViewById(R.id.b_myactivity);
		mExit=(Button) findViewById(R.id.b_exit);
		mButtonMyActivity = (Button) findViewById(R.id.b_button_myActivity);
		
		mNameText=(TextView) findViewById(R.id.b_nameText);
		mTrueNameTExt=(TextView) findViewById(R.id.b_truenameText);
		mSexText=(TextView) findViewById(R.id.b_sexText);
		mSchoolText=(TextView) findViewById(R.id.b_schoolText);
		mProfessionText=(TextView) findViewById(R.id.b_professionText);
		mMyactivityText=(TextView) findViewById(R.id.b_myactivityText);


		mButtonMyActivity.setOnClickListener(this);
		mMyActivity.setOnClickListener(this);
		View view = setLayoutActionBar(R.layout.mybar,TAG);
		if(view!=null){
			mLeftArr=(Button) view.findViewById(R.id.mybar_arrleft);
			mLeftArr.setOnClickListener(this);
		}
		
		Intent intent = getIntent();
		Bundle bundle=intent.getExtras();
		bean = (UserBean) bundle.getSerializable(UserBean.sUser);
		
		mName.setText(bean.getUsername());
		mTrueName.setText(bean.getTruename());
		mSchool.setText(bean.getSchool());
		mProfession.setText(bean.getProfession());
		mSex.setText(bean.getSex());
		
		mTrueName.setOnClickListener(this);
		mSchool.setOnClickListener(this);
		mProfession.setOnClickListener(this);
		mSex.setOnClickListener(this);
		mExit.setOnClickListener(this);
		
		mNameText.setOnClickListener(this);
		mTrueNameTExt.setOnClickListener(this);
		mSexText.setOnClickListener(this);
		mSchoolText.setOnClickListener(this);
		mProfessionText.setOnClickListener(this);
		mMyactivityText.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.main, menu);*/
		
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.b_myactivityText:
		case R.id.b_myactivity:
		case R.id.b_button_myActivity:
			Bundle bundle=new Bundle();
			bundle.putSerializable(UserBean.sUser, bean);
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, MyActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.mybar_arrleft:
			break;
		case R.id.b_truenameText:
		case R.id.b_truename:
			final MyDialog d=new MyDialog(this);
			d.setText(mTrueName.getText().toString());
			d.setOnPositiveListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					EditText temp=d.getEditText();
					mTrueName.setText(temp.getText().toString());
					bean.setTruename(temp.getText().toString());
					new UserDao(MainActivity.this).updateUserInfo(bean);
					d.dismiss();
				}
			});
			d.setOnNegativeListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					d.dismiss();
				}
			});
			d.show();
			break;
		case R.id.b_exit:
			showDialog(2);
			break;
		case R.id.b_schoolText:
		case R.id.b_school:
			final MyDialog d1=new MyDialog(this);
			d1.setText(mSchool.getText().toString());
			d1.setOnPositiveListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					EditText temp=d1.getEditText();
					mSchool.setText(temp.getText().toString());
					bean.setSchool(temp.getText().toString());
					new UserDao(MainActivity.this).updateUserInfo(bean);
					d1.dismiss();
				}
			});
			d1.setOnNegativeListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					d1.dismiss();
				}
			});
			d1.show();
			break;
		case R.id.b_professionText:
		case R.id.b_profession:
			final MyDialog d11=new MyDialog(this);
			d11.setText(mProfession.getText().toString());
			d11.setOnPositiveListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					EditText temp=d11.getEditText();
					mProfession.setText(temp.getText().toString());
					bean.setProfession(temp.getText().toString());
					new UserDao(MainActivity.this).updateUserInfo(bean);
					d11.dismiss();
				}
			});
			d11.setOnNegativeListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					d11.dismiss();
				}
			});
			d11.show();
			break;
		case R.id.b_sexText:
		case R.id.b_sex:
			showDialog(1);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog=null;
		
		switch (id) {
		case 1:
			Builder builder=new Builder(this);
			
			builder.setTitle("请选择性别");
			
			builder.setSingleChoiceItems(R.array.sex, 0, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int which) {
					Log.i(T, "setSingleChoiceItems"+which);
					SIGN=which;
				}
			});
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int which) {
					//处理确定按钮的事件
					Log.i(T, "setPositiveButton"+which);
					String sex=getResources().getStringArray(R.array.sex)[SIGN];
                    mSex.setText(sex);
                    bean.setSex(sex);
                    new UserDao(MainActivity.this).updateUserInfo(bean);
				}
			});
			
			dialog = builder.create();
			
			break;
		case 2:
			Builder b1=new Builder(this);
			b1.setTitle("确认是否退出");
			b1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Editor edit = sf.edit();
					edit.putString("username", "d1e2f3a4u5l6t7V8a9lue");
					edit.putString("password", "d1e2f3a4u5l6t7V8a9lue");
					edit.commit();
					startActivity(new Intent(MainActivity.this,LoginActivity.class));
					MainActivity.this.finish();
					Log.i(T, "finish");
				}
			});
			b1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					
				}
			});
			dialog=b1.create();
			break;

		default:
			break;
		}
		return dialog;
	}

}
