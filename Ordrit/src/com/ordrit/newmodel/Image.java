package com.ordrit.newmodel;

import com.google.gson.annotations.Expose;

public class Image {
	@Expose
	private String name;
	@Expose
	private String image;
	@Expose
	private String sub_category;
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

	public String getImage() {
	return image;
	}

	public void setImage(String image) {
	this.image = image;
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
