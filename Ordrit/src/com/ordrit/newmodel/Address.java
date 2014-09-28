package com.ordrit.newmodel;

import com.google.gson.annotations.Expose;

public class Address {

	@Expose
	private String street_address;
	@Expose
	private String city;
	@Expose
	private String state;
	@Expose
	private String pin_code;
	@Expose
	private String created_on;
	@Expose
	private String id;
	@Expose
	private String url;

	public String getStreet_address() {
	return street_address;
	}

	public void setStreet_address(String street_address) {
	this.street_address = street_address;
	}

	public String getCity() {
	return city;
	}

	public void setCity(String city) {
	this.city = city;
	}

	public String getState() {
	return state;
	}

	public void setState(String state) {
	this.state = state;
	}

	public String getPin_code() {
	return pin_code;
	}

	public void setPin_code(String pin_code) {
	this.pin_code = pin_code;
	}

	public String getCreated_on() {
	return created_on;
	}

	public void setCreated_on(String created_on) {
	this.created_on = created_on;
	}

	public String getId() {
	return id;
	}

	public void setId(String id) {
	this.id = id;
	}

	public String getUrl() {
	return url;
	}

	public void setUrl(String url) {
	this.url = url;
	}

}
