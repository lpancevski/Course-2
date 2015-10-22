package com.example.course.webservices.entity;

public class ContactDetails {
	
	private Integer contactId;
	private String contactName;
	private String contactSurname;
	private String contactAddress;
	
	public ContactDetails() {}

	public ContactDetails(Integer contactId, String contactName,
			String contactSurname, String contactAddress) {
		super();
		this.contactId = contactId;
		this.contactName = contactName;
		this.contactSurname = contactSurname;
		this.contactAddress = contactAddress;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactSurname() {
		return contactSurname;
	}

	public void setContactSurname(String contactSurname) {
		this.contactSurname = contactSurname;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	
}
