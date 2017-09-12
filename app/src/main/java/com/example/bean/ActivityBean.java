package com.example.bean;

import java.io.Serializable;
import java.util.Date;

public class ActivityBean implements Serializable {
	public static String sID="_id";
	public static String sUSERID="_userid";
	public static String sTITLE="_title";
	public static String sLOCATION="_location";
	public static String sTime="_time";
	public static String sDEP="_dep";
	public static String sSTATE="_state";
	public static String sDETAILS="_details";
	public static String sActivity="ActivityBean";
	
	
	
	private int id;
	private int userId;
	private String title;
	private String location;
	private String time;
	private String dep;
	private String state;
	private String details;
	
	public ActivityBean(){}
	
	
	
	public ActivityBean(String title,String location, String time, String dep, String state,String details) {
		super();
		this.title=title;
		this.location = location;
		this.time = time;
		this.dep = dep;
		this.state = state;
		this.details=details;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}



	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getDep() {
		return dep;
	}
	
	public void setDep(String dep) {
		this.dep = dep;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}



	public String getDetails() {
		return details;
	}



	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
