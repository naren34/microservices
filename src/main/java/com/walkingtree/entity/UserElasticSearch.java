package com.walkingtree.entity;

import javax.persistence.Column;

import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName="user",type="users")

public class UserElasticSearch {
	//@Id
	
	private String id;
	
	@Column(unique=true,nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	private String fName;
	
	private String LName;
	
	private int phoneNumber;
	
	private String email;

	
	public String getId() {
		return id;
	}

	/*public void setId(String id) {
		this.id = id;
	}*/

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return LName;
	}

	public void setLName(String lName) {
		LName = lName;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
