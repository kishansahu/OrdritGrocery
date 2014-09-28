package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Merchant {

	@Expose
	private String name;
	@Expose
	private String user;
	@Expose
	private List<String> stores = new ArrayList<String>();
	@Expose
	private String created_on;
	@Expose
	private Integer id;
	@Expose
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<String> getStores() {
		return stores;
	}

	public void setStores(List<String> stores) {
		this.stores = stores;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
