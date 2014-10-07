package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class SubCategoryData {
/*	@Expose
	private String count;
	@Expose
	private String next;
	@Expose
	private String previous;*/
	@Expose
	private List<SubCategoryItem> results = new ArrayList<SubCategoryItem>();

	/*public String getCount() {
	return count;
	}

	public void setCount(String count) {
	this.count = count;
	}

	public String getNext() {
	return next;
	}

	public void setNext(String next) {
	this.next = next;
	}

	public Object getPrevious() {
	return previous;
	}

	public void setPrevious(String previous) {
	this.previous = previous;
	}*/

	public List<SubCategoryItem> getResults() {
	return results;
	}

	public void setResults(List<SubCategoryItem> results) {
	this.results = results;
	}

}
