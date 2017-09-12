package com.example.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
	public static final String sId="_id";
	public static final String sUsername="_username";
	public static final String sPassword="_password";
	public static final String sTruename="_truename";
	public static final String sSchool="_school";
	public static final String sProfession="_profession";
	public static final String sSex="_sex";
	public static final String sUser="UserBean";
	
	private int mId;
	private String mUsername;
	private String mPassword;
	private String mTruename;
	private String mSchool;
	private String mProfession;
	private String mSex;
	
	public UserBean(String username, String password, String truename,
			String school, String profession, String sex) {
		mUsername = username;
		mPassword = password;
		mTruename = truename;
		mSchool = school;
		mProfession = profession;
		mSex = sex;
	}
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		mId = id;
	}
	public String getUsername() {
		return mUsername;
	}
	public void setUsername(String username) {
		mUsername = username;
	}
	public String getPassword() {
		return mPassword;
	}
	public void setPassword(String password) {
		mPassword = password;
	}
	public String getTruename() {
		return mTruename;
	}
	public void setTruename(String truename) {
		mTruename = truename;
	}
	public String getSchool() {
		return mSchool;
	}
	public void setSchool(String school) {
		mSchool = school;
	}
	public String getProfession() {
		return mProfession;
	}
	public void setProfession(String profession) {
		mProfession = profession;
	}
	public String getSex() {
		return mSex;
	}
	public void setSex(String sex) {
		mSex = sex;
	}

}
