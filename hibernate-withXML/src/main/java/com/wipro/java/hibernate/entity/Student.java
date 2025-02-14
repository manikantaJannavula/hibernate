package com.wipro.java.hibernate.entity;

public class Student {

	private int sId;
	private String sName;
	private String sRollNo;
	private String sClass;

	// default constructor
	public Student() {
	}

	// Parameterized Constructor
	public Student(String sName, String sRollNo, String sClass) {
		super();
		this.sName = sName;
		this.sRollNo = sRollNo;
		this.sClass = sClass;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsRollNo() {
		return sRollNo;
	}

	public void setsRollNo(String sRollNo) {
		this.sRollNo = sRollNo;
	}

	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	

	@Override
	public String toString() {
		return "Student [sId=" + sId + ", sName=" + sName + ", sRollNo=" + sRollNo + ", sClass=" + sClass + "]";
	}

	
}
