package com.ordrit.newmodel;



import java.io.Serializable;

import com.google.gson.annotations.Expose;


public class SubCategoryItem implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Expose
private String name;
@Expose
private String description;
@Expose
private String price;
@Expose
private String price_units;
@Expose
private Store store;
@Expose
private Image image;
@Expose
private Merchant merchant;
@Expose
private SubCategory sub_category;
@Expose
private String created_on;
@Expose
private Integer id;
@Expose
private String url;

private String itemQuantity;

public String getItemQuantity() {
	return itemQuantity;
}
public void setItemQuantity(String itemQuantity) {
	this.itemQuantity = itemQuantity;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public String getPrice_units() {
return price_units;
}

public void setPrice_units(String price_units) {
this.price_units = price_units;
}

public Store getStore() {
return store;
}

public void setStore(Store store) {
this.store = store;
}

public Image getImage() {
return image;
}

public void setImage(Image image) {
this.image = image;
}

public Merchant getMerchant() {
return merchant;
}

public void setMerchant(Merchant merchant) {
this.merchant = merchant;
}

public SubCategory getSub_category() {
return sub_category;
}

public void setSub_category(SubCategory sub_category) {
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