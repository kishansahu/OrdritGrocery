package com.ordrit.newmodel;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class NearByStore {
	@Expose
	private String count;
	@Expose
	private String next;
	@Expose
	private String previous;
	@Expose
	private List<Store> results = new ArrayList<Store>();

	public String getCount() {
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

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<Store> getResults() {
		return results;
	}

	public void setResults(List<Store> results) {
		this.results = results;
	}
}
