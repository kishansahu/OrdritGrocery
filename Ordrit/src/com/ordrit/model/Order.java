package com.ordrit.model;

import java.io.Serializable;
import java.util.List;

import com.ordrit.newmodel.SubCategoryItem;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String creationDate;
	private List<SubCategoryItem> itemsInOrder;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public List<SubCategoryItem> getItemsInOrder() {
		return itemsInOrder;
	}
	public void setItemsInOrder(List<SubCategoryItem> itemsInOrder) {
		this.itemsInOrder = itemsInOrder;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
