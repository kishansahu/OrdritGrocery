package com.ordrit.model;

public class ItemCategory {

	private String id;
	private String name;
	private String url;
	private ItemSubCategory itemSubCategory;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ItemSubCategory getItemSubCategory() {
		return itemSubCategory;
	}
	public void setItemSubCategory(ItemSubCategory itemSubCategory) {
		this.itemSubCategory = itemSubCategory;
	}
	
	
}
