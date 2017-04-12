package com.waterdet.pojo;

public class User {
	private int number;
	private String userID;
	private String password;
	private int grade;
	public String getUserID() {
		return userID;
	}

	public void setUserid(String userID) {
		this.userID = userID;
	}

	public String getPassward() {
		return password;
	}

	public void setPassward(String passward) {
		this.password = passward;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
