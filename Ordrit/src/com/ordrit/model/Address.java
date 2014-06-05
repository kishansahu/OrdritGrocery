package com.ordrit.model;

public class Address {

	private String id;
	private String streetAddress;
	private String city;
	private String State;
	private String pincode;
	private String url;

	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetAddress=" + streetAddress
				+ ", city=" + city + ", State=" + State + ", pincode="
				+ pincode + ", url=" + url + "]";
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
