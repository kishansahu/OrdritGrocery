package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class StoreDetailList {

	@Expose
	private List<StoreDetail> sub_categories = new ArrayList<StoreDetail>();

	public List<StoreDetail> getSub_categories() {
		return sub_categories;
	}

	public void setSub_categories(List<StoreDetail> sub_categories) {
		this.sub_categories = sub_categories;
	}

	@Override
	public String toString() {
		return "StoreDetailList [sub_categories=" + sub_categories + "]";
	}

	

}
