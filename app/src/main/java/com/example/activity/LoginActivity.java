package com.example.activity;

import com.example.bean.UserBean;
import com.example.dao.UserDao;
import com.example.ruichuang.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	static Activity loginActivity;
	private Button mRegisterButton;
	private Button mLoginButton;
	private EditText mUsername;
	private EditText mPassword;
	SharedPreferences sf;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ActionBar actionBar=getActionBar();
		actionBar.hide();
		init();
		sf=getSharedPreferences("login",Context.MODE_PRIVATE);
		String username = sf.getString("username", "d1e2f3a4u5l6t7V8a9lue");
		String password = sf.getString("password", "d1e2f3a4u5l6t7V8a9lue");
		if(!username.equals("d1e2f3a4u5l6t7V8a9lue")&&!password.equals("d1e2f3a4u5l6t7V8a9lue")){
			UserDao user=new UserDao(this);
			UserBean checkUser = user.checkUser(username,password);
			if(!(checkUser.getUsername().equals(""))){
				Bundle bundle=new Bundle();
				bundle.putSerializable(UserBean.sUser, checkUser);
				Intent intentMain=new Intent(LoginActivity.this,MainActivity.class);
				intentMain.putExtras(bundle);
				this.startActivity(intentMain);
				this.finish();
			}
		}
	}

	private void init() {
		loginActivity=this;
		mRegisterButton=(Button) findViewById(R.id.register_button);
		mLoginButton=(Button) findViewById(R.id.login_button);
		
		mUsername=(EditText) findViewById(R.id.username);
		mPassword=(EditText) findViewById(R.id.password);
		
		mRegisterButton.setOnClickListener(this);
		mLoginButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.register_button:
			Intent intentRegister=new Intent(LoginActivity.this,RegisterActivity.class);
			this.startActivity(intentRegister);
			break;

		case R.id.login_button:
			String username=mUsername.getText().toString();
			String password=mPassword.getText().toString();
			if(username==null||"".equals(username)){
				Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(password==null||"".equals(password)){
				Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			UserDao user=new UserDao(this);
			UserBean checkUser = user.checkUser(username,password);
			if(!(checkUser.getUsername().equals(""))){
				Bundle bundle=new Bundle();
				bundle.putSerializable(UserBean.sUser, checkUser);
				Intent intentMain=new Intent(LoginActivity.this,MainActivity.class);
				intentMain.putExtras(bundle);
				Editor edit = sf.edit();
				edit.putString("username", username);
				edit.putString("password", password);
				edit.commit();
				this.startActivity(intentMain);
				this.finish();
			}else{
				Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
			}
		default:
			break;
		}
	}

}
