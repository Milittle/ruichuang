package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bean.ActivityBean;
import com.example.bean.UserBean;
import com.example.dbhelper.SQLiteHelper;

public class ActivityDao {
	private static final String sDB_NAME="ruichuang.db";
	private static final int sDB_VERSION=3;
	private static final String TAG = "ActivityDao";
	private SQLiteDatabase mSqlLiteDb;
	private SQLiteHelper mSqliteHel;
	
	public ActivityDao(Context context){
		mSqliteHel=new SQLiteHelper(context,sDB_NAME,null,sDB_VERSION);
		mSqlLiteDb=mSqliteHel.getWritableDatabase();
	}
	
	public void close(){
		mSqlLiteDb.close();
		mSqliteHel.close();
	}
	
	public List<ActivityBean> getList(int userId){
		List<ActivityBean> list=new ArrayList<ActivityBean>();
		
		String sql="select * from "+SQLiteHelper.sACTIVITY_NAME+" where "+ActivityBean.sUSERID+"="+userId;
		
		Cursor cursor = mSqlLiteDb.rawQuery(sql, null);
		
		while(cursor.moveToNext()){
			ActivityBean bean=new ActivityBean("","","","","","");
			bean.setId(cursor.getInt(0));
			bean.setUserId(cursor.getInt(1));
			bean.setTitle(cursor.getString(2));
			bean.setTime(cursor.getString(3));
			bean.setLocation(cursor.getString(4));
			bean.setDep(cursor.getString(5));
			bean.setState(cursor.getString(6));
			bean.setDetails(cursor.getString(7));
			
			list.add(bean);
		}
		
		return list;
	}
	
	
	public Long insertActivityInfo(ActivityBean bean){
		ContentValues values=new ContentValues();
		values.put(ActivityBean.sUSERID, bean.getUserId());
		values.put(ActivityBean.sTITLE, bean.getTitle());
		values.put(ActivityBean.sTime,bean.getTime());
		values.put(ActivityBean.sLOCATION, bean.getLocation());
		values.put(ActivityBean.sDEP, bean.getDep());
		values.put(ActivityBean.sSTATE, bean.getState());
		values.put(ActivityBean.sDETAILS, bean.getDetails());
		Long uid=mSqlLiteDb.insert(SQLiteHelper.sACTIVITY_NAME, ActivityBean.sID, values);
		Log.e(TAG, "insertActivityInfo");
		return uid;
	}
	
	public int updateActivityInfo(ActivityBean bean){
		Log.i(TAG, "updateActivityInfo"+bean.getTitle());
		ContentValues values=new ContentValues();
		values.put(ActivityBean.sSTATE, bean.getState());
		int id=mSqlLiteDb.update(SQLiteHelper.sACTIVITY_NAME, values, ActivityBean.sID+"="+bean.getId(),null);
		return id;
	}
	
	public void deleteActivityInfo(int id){
		Log.i(TAG, "deleteActivityInfo"+id);
		ContentValues values=new ContentValues();
		mSqlLiteDb.delete(SQLiteHelper.sACTIVITY_NAME,ActivityBean.sID+"="+id,null);
	}

}
