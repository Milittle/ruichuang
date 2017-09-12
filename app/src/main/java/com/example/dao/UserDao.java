package com.example.dao;

import com.example.bean.UserBean;
import com.example.dbhelper.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDao {
	private static final String sDB_NAME="ruichuang.db";
	private static final int sDB_VERSION=3;
	private static final String TAG = "UserDao";
	private SQLiteDatabase mSqlLiteDb;
	private SQLiteHelper mSqliteHel;
	
	public UserDao(Context context){
		mSqliteHel=new SQLiteHelper(context,sDB_NAME,null,sDB_VERSION);
		mSqlLiteDb=mSqliteHel.getWritableDatabase();
	}
	
	public void close(){
		mSqlLiteDb.close();
		mSqliteHel.close();
	}
	
	public Long insertUserInfo(UserBean bean){
		if(checkUser(bean.getUsername()).getUsername().equals("truecheck")){
			Log.e(TAG, "insertUserInfo");
			return -1L;
		}
		else{
			ContentValues values=new ContentValues();
			values.put(UserBean.sUsername, bean.getUsername());
			values.put(UserBean.sPassword, bean.getPassword());
			values.put(UserBean.sTruename, bean.getTruename());
			values.put(UserBean.sSchool, bean.getSchool());
			values.put(UserBean.sProfession, bean.getProfession());
			values.put(UserBean.sSex, bean.getSex());
			Long uid=mSqlLiteDb.insert(SQLiteHelper.sUSERTB_NAME, UserBean.sId, values);
			
			Log.e(TAG, "insertUserInfo");
			return uid;
		}
	}
	
	public UserBean checkUser(String ...arg){
		UserBean bean=new UserBean("","","","","","");
		if(arg.length==1){
			Log.e(TAG, "checkUser1");
			String sql="select * from users where "+UserBean.sUsername+"='"+arg[0]+"'";
			Cursor cursor = mSqlLiteDb.rawQuery(sql, null);
			if(cursor.moveToNext()){
				bean.setUsername("truecheck");
			}
			else{
				bean.setUsername("falsecheck");
			}
		}
		if(arg.length==2){
			Log.e(TAG, "checkUser2");
			String sql="select * from users where "+UserBean.sUsername+"='"+arg[0]
					+"' and "+UserBean.sPassword+"='"+arg[1]+"'";
			Cursor cursor = mSqlLiteDb.rawQuery(sql, null);
			if(cursor.moveToNext()){
				Log.e(TAG, "checkUser2");
				bean.setId(Integer.parseInt(cursor.getString(0)));
				bean.setUsername(cursor.getString(1));
				bean.setPassword(cursor.getString(2));
				bean.setTruename(cursor.getString(3));
				bean.setSchool(cursor.getString(4));
				bean.setProfession(cursor.getString(5));
				bean.setSex(cursor.getString(6));
			}
		}
		return bean;
	}

	public int updateUserInfo(UserBean bean){
		Log.i(TAG, "updateUserInfo"+bean.getTruename());
		ContentValues values=new ContentValues();
		values.put(UserBean.sTruename, bean.getTruename());
		values.put(UserBean.sSchool, bean.getSchool());
		values.put(UserBean.sProfession, bean.getProfession());
		values.put(UserBean.sSex, bean.getSex());
		int id=mSqlLiteDb.update(SQLiteHelper.sUSERTB_NAME, values, UserBean.sId+"="+bean.getId(),null);
		return id;
	}
}
