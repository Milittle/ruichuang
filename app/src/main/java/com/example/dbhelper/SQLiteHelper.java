package com.example.dbhelper;

import com.example.bean.ActivityBean;
import com.example.bean.UserBean;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final String sUSERTB_NAME="users";
	public static final String sACTIVITY_NAME="activitys";
	private static final String sTAB = "SQLiteHelper";
	
	public SQLiteHelper(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e(sTAB, "onCreate");
		db.execSQL("CREATE TABLE IF NOT EXISTS "+
				sUSERTB_NAME+"("+
				UserBean.sId+" integer primary key autoincrement,"+
				UserBean.sUsername+" varchar(50),"+
				UserBean.sPassword+" varchar(50),"+
				UserBean.sTruename+" varchar(50),"+
				UserBean.sSchool+" varchar(50),"+
				UserBean.sProfession+" varchar(50),"+
				UserBean.sSex+" varchar(50))");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS "+
				sACTIVITY_NAME+"("+
				ActivityBean.sID+" integer primary key autoincrement,"+
				ActivityBean.sUSERID+" integer,"+
				ActivityBean.sTITLE+" varchar(20),"+
				ActivityBean.sTime+" varchar(30),"+
				ActivityBean.sLOCATION+" varchar(50),"+
				ActivityBean.sDEP+" varchar(20),"+
				ActivityBean.sSTATE+" varchar(10),"+
				ActivityBean.sDETAILS+" varchar(100))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 2:
			db.execSQL("CREATE TABLE IF NOT EXISTS "+
					sUSERTB_NAME+"("+
					UserBean.sId+" integer primary key autoincrement,"+
					UserBean.sUsername+" varchar(50),"+
					UserBean.sPassword+" varchar(50),"+
					UserBean.sTruename+" varchar(50),"+
					UserBean.sSchool+" varchar(50),"+
					UserBean.sProfession+" varchar(50),"+
					UserBean.sSex+" varchar(50))");
			
			db.execSQL("CREATE TABLE IF NOT EXISTS "+
					sACTIVITY_NAME+"("+
					ActivityBean.sID+" integer primary key autoincrement,"+
					ActivityBean.sUSERID+" integer,"+
					ActivityBean.sTITLE+" varchar(20),"+
					ActivityBean.sTime+" varchar(30),"+
					ActivityBean.sLOCATION+" varchar(50),"+
					ActivityBean.sDEP+" varchar(20),"+
					ActivityBean.sSTATE+" varchar(10),"+
					ActivityBean.sDETAILS+" varchar(100))");
			break;

		default:
			break;
		}
	}
	

}
