package com.contact.entity;

public class Contact {
	
	private Long Id;
	private String email;
	private String contactName;
	private Long userId;

	public Contact(long id, String email, String contactName, long userId) {
		this.contactName = contactName;
		this.email = email;
		this.userId = userId;
		this.Id = id;
	}

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


}
