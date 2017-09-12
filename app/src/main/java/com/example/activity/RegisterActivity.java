package com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bean.UserBean;
import com.example.dao.UserDao;
import com.example.ruichuang.R;
import com.example.tool.ActionBarTool;

public class RegisterActivity extends ActionBarTool implements OnClickListener{

	
	private Button mLeftArr;
	private Button mRegister;
	private EditText mUsername;
	private EditText mPassword1;
	private EditText mPasswrod2;
	private EditText mTruename;
	private EditText mSchool;
	private EditText mProfession;
	private RadioGroup mSex;
	private RadioButton mMale;
	private RadioButton mFmale;
	private UserBean bean;
	private static final String TAG = "注册";
	
	SharedPreferences sf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		init();
		sf=this.getSharedPreferences("login", Context.MODE_PRIVATE);
	}

	private void init() {
		bean=new UserBean("","","","","","");
		View view = setLayoutActionBar(R.layout.mybar,TAG);
		if(view!=null){
			mLeftArr=(Button) view.findViewById(R.id.mybar_arrleft);
			mLeftArr.setOnClickListener(this);
		}
		
		mRegister=(Button) findViewById(R.id.registersumbit);
		mRegister.setOnClickListener(this);
		mUsername=(EditText) findViewById(R.id.r_username);
		mPassword1=(EditText) findViewById(R.id.r_password1);
		mPasswrod2=(EditText) findViewById(R.id.r_password2);
		mTruename=(EditText) findViewById(R.id.r_truename);
		mSchool=(EditText) findViewById(R.id.r_school);
		mProfession=(EditText) findViewById(R.id.r_profession);
		mSex=(RadioGroup) findViewById(R.id.r_sex);
		mMale=(RadioButton) findViewById(R.id.male);
		mFmale=(RadioButton) findViewById(R.id.fmale);
		
		mUsername.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				bean.setUsername(text.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mPassword1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				bean.setPassword(text.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});		
		mTruename.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				bean.setTruename(text.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				
			}
		});
		mSchool.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				bean.setSchool(text.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				
			}
		});
		mProfession.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2, int arg3) {
				bean.setProfession(text.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				String sexTemp="";
				if(mMale.getId()==arg1){
					sexTemp="男";
				}else if(mFmale.getId()==arg1){
					sexTemp="女";
				}
				bean.setSex(sexTemp);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		/*getMenuInflater().inflate(R.menu.login, menu);*/
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.mybar_arrleft:
			this.finish();
			break;
		case R.id.registersumbit:
			if(!bean.getPassword().equals(mPasswrod2.getText().toString())){
				Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(bean.getUsername())){
				Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(bean.getPassword())){
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(mPasswrod2.getText().toString())){
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if("".equals(bean.getTruename())){
				Toast.makeText(this, "真实姓名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if("".equals(bean.getSchool())){
				Toast.makeText(this, "学校不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(bean.getProfession())){
				Toast.makeText(this, "专业不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if("".equals(bean.getSex())){
				bean.setSex("男");
			}
			String username=bean.getUsername();
			String password=bean.getPassword();
			if(new UserDao(this).insertUserInfo(bean).equals(-1L)){
				Toast.makeText(this, "用户名已经存在，请换用户名在进行注册！", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
				mUsername.setText("");
				mPassword1.setText("");
				mPasswrod2.setText("");
				mTruename.setText("");
				mSchool.setText("");
				mProfession.setText("");
				Log.i(TAG, "注册验证"+username+password);
				UserDao user=new UserDao(this);
				
				UserBean checkUser = user.checkUser(username,password);
				if(!(checkUser.getUsername().equals(""))){
					Editor edit = sf.edit();
					edit.putString("username", checkUser.getUsername());
					edit.putString("password", checkUser.getPassword());
					edit.commit();
					Log.i(TAG, "注册成功");
					Bundle bundle=new Bundle();
					bundle.putSerializable(UserBean.sUser, checkUser);
					Intent intentMain=new Intent(RegisterActivity.this,MainActivity.class);
					intentMain.putExtras(bundle);
					this.startActivity(intentMain);
					LoginActivity.loginActivity.finish();
					this.finish();
				}
			}
			break;

		default:
			break;
		}
	}
	
}
