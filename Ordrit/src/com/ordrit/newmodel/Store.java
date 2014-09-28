package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class Store {

	@Expose
	private String name;
	@Expose
	private Merchant merchant;
	@Expose
	private Address address;
	@Expose
	private String location;
	@Expose
	private String opens_at;
	@Expose
	private String closes_at;
	@Expose
	private String minimum_order;
	@Expose
	private String estimated_delivery_time;
	@Expose
	private String sub_category;
	@Expose
	private String created_on;
	@Expose
	private String id;
	@Expose
	private String url;
	@Expose
	private String phone_number_1;
	@Expose
	private String phone_number_2;

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public Merchant getMerchant() {
	return merchant;
	}

	public void setMerchant(Merchant merchant) {
	this.merchant = merchant;
	}

	public Address getAddress() {
	return address;
	}

	public void setAddress(Address address) {
	this.address = address;
	}

	public String getLocation() {
	return location;
	}

	public void setLocation(String location) {
	this.location = location;
	}

	public String getOpens_at() {
	return opens_at;
	}

	public void setOpens_at(String opens_at) {
	this.opens_at = opens_at;
	}

	public String getCloses_at() {
	return closes_at;
	}

	public void setCloses_at(String closes_at) {
	this.closes_at = closes_at;
	}

	public String getMinimum_order() {
	return minimum_order;
	}

	public void setMinimum_order(String minimum_order) {
	this.minimum_order = minimum_order;
	}

	public String getEstimated_delivery_time() {
	return estimated_delivery_time;
	}

	public void setEstimated_delivery_time(String estimated_delivery_time) {
	this.estimated_delivery_time = estimated_delivery_time;
	}

	public String getSub_category() {
	return sub_category;
	}

	public void setSub_category(String sub_category) {
	this.sub_category = sub_category;
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

	public String getPhone_number_1() {
	return phone_number_1;
	}

	public void setPhone_number_1(String phone_number_1) {
	this.phone_number_1 = phone_number_1;
	}

	public String getPhone_number_2() {
	return phone_number_2;
	}

	public void setPhone_number_2(String phone_number_2) {
	this.phone_number_2 = phone_number_2;
	}
	@Expose
	private List<Category> sub_categories = new ArrayList<Category>();

	public List<Category> getSub_categories() {
		return sub_categories;
	}

	public void setSub_categories(List<Category> sub_categories) {
		this.sub_categories = sub_categories;
	}

	@Override
	public String toString() {
		return "Store [name=" + name + ", merchant=" + merchant + ", address="
				+ address + ", location=" + location + ", opens_at=" + opens_at
				+ ", closes_at=" + closes_at + ", minimum_order="
				+ minimum_order + ", estimated_delivery_time="
				+ estimated_delivery_time + ", sub_category=" + sub_category
				+ ", created_on=" + created_on + ", id=" + id + ", url=" + url
				+ ", phone_number_1=" + phone_number_1 + ", phone_number_2="
				+ phone_number_2 + ", sub_categories=" + sub_categories + "]";
	}
}
