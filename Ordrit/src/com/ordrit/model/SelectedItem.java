package com.ordrit.model;

import com.ordrit.newmodel.SubCategoryItem;

public class SelectedItem {
	private SubCategoryItem item;
	private String quantity;
	
	
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public SubCategoryItem getItem() {
		return item;
	}

	public void setItem(SubCategoryItem item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "SelectedItem [item=" + item + ", quantity=" + quantity + "]";
	}
}
