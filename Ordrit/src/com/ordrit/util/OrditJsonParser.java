package com.ordrit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.model.Address;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.model.States;
import com.ordrit.model.Store;
import com.ordrit.model.User;

public class OrditJsonParser {

	public static String getTokenStringFromJSON(JSONObject obj)
			throws JSONException {
		String token = "";
		token = obj.get(OrdritJsonKeys.TAG_TOKEN).toString();
		return token;
	}

	public static Map getItemCategoryMap() throws JSONException{
		
		Map<String, ItemCategory> itemCategoryMap = new TreeMap<String, ItemCategory>();
		String str= "[{\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}]";
		JSONArray jsonArray = new JSONArray(str);
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject subCategoryJSONObj = jsonArray.getJSONObject(i);
			JSONObject categoryObj= subCategoryJSONObj.getJSONObject(OrdritJsonKeys.TAG_CATEGORY);
			ItemCategory itemCategory=null;
			String categoryId=	categoryObj.getString(OrdritJsonKeys.TAG_ID);
			if(!itemCategoryMap.containsKey(categoryId)){
				itemCategory= new ItemCategory();
				itemCategory.setId(categoryId);
				itemCategory.setName(categoryObj.getString(OrdritJsonKeys.TAG_NAME));
				itemCategory.setUrl((categoryObj.getString(OrdritJsonKeys.TAG_URL)));
			}else{
				itemCategory= itemCategoryMap.get(categoryId);
			}
			ItemSubCategory itemSubCategory= new ItemSubCategory(); 
			itemSubCategory.setId(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_ID));
			itemSubCategory.setName(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_NAME));
			itemSubCategory.setUrl(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_URL));
			itemCategory.setItemSubCategory(itemSubCategory);
			itemCategoryMap.put(itemCategory.getId(), itemCategory);	
		}
		return itemCategoryMap;
	}
	
	public static List<Store> getAllStoresFromJSON(JSONObject obj)
			throws JSONException {
		JSONArray storesArray = obj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		List<Store> storeList = new ArrayList<Store>();
		for (int i = 0; i < storesArray.length(); i++) {
			Store store = new Store();
			JSONObject storeJsonObj = storesArray.getJSONObject(i);
			store.setStoreName(storeJsonObj.getString(OrdritJsonKeys.TAG_NAME));
			store.setLocationLatLong(storeJsonObj
					.getString(OrdritJsonKeys.TAG_LOCATION));
			store.setId(storeJsonObj.getString(OrdritJsonKeys.TAG_ID));
			store.setUrl(storeJsonObj.getString(OrdritJsonKeys.TAG_URL));
			store.setPhoneNumber1(storeJsonObj
					.getString(OrdritJsonKeys.TAG_PHONENUMBER_1));
			store.setPhoneNumber2(storeJsonObj
					.getString(OrdritJsonKeys.TAG_PHONENUMBER_2));
			User merchant = new User();
			merchant.setEmailId(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_MERCHANT).getString(
					OrdritJsonKeys.TAG_NAME));
			merchant.setId(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_MERCHANT).getString(
					OrdritJsonKeys.TAG_ID));
			store.setUser(merchant);
			Address merchantAddress = new Address();
			merchantAddress.setId(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_ID));
			merchantAddress.setStreetAddress(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_STREET_ADDRESS));
			merchantAddress.setState(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_STATE));
			merchantAddress.setCity(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_CITY));
			merchantAddress.setPincode(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_PINCODE));
			store.setAddress(merchantAddress);
			storeList.add(store);
		}
		return storeList;
	}
	public static User getUserFromJSON(JSONObject obj) throws JSONException{
		User user = new User();
		user.setEmailId(obj.getString(OrdritJsonKeys.USER_EMAIL));
		user.setFirstName(obj.getString(OrdritJsonKeys.USER_FIRSTNAME));
		user.setLastName(obj.getString(OrdritJsonKeys.USER_LASTNAME));
		user.setRole(obj.getString(OrdritJsonKeys.USER_ROLE));
		user.setJoinDate(obj.getString(OrdritJsonKeys.USER_DATE_JOINED));
		user.setLastLoginDate(obj.getString(OrdritJsonKeys.USER_LAST_LOGIN));
		user.setPhoneNumber(obj.getString(OrdritJsonKeys.USER_PHONE_NUMBER));
		user.setGcmRegistrationId(obj.getString(OrdritJsonKeys.USER_GCM_REGISTRATION_ID));
		user.setId(obj.getString(OrdritJsonKeys.USER_ID));
		user.setUrl(obj.getString(OrdritJsonKeys.USER_URL));
		return user;
	}
	public static Address getMerchantAddressFromJSON(JSONObject jsonObject) throws JSONException{
		Address merchantAddress = new Address();

		merchantAddress.setStreetAddress(jsonObject.getString(
				OrdritJsonKeys.TAG_STREET_ADDRESS));
		merchantAddress.setState(jsonObject.getString(
				OrdritJsonKeys.TAG_STATE));
		merchantAddress.setCity(jsonObject.getString(
				OrdritJsonKeys.TAG_CITY));
		merchantAddress.setPincode(jsonObject.getString(
				OrdritJsonKeys.TAG_PINCODE));
		merchantAddress.setUrl(jsonObject.getString(
				OrdritJsonKeys.TAG_URL));
		return merchantAddress;
	}

	public static List<States> getStateFromJSONArray(JSONArray jsonArray)throws JSONException {
		List<States> states = new ArrayList<States>();
		for (int i = 0; i < jsonArray.length(); i++) {
			states.add(getStateFromJSON(jsonArray.getJSONObject(i)));
		}

		return states;
	}
	
	public static States getStateFromJSON(JSONObject jsonObject) throws JSONException{
		States states=new States();
		states.setName(jsonObject.getString(OrdritJsonKeys.TAG_NAME));
		states.setUrl(jsonObject.getString(OrdritJsonKeys.TAG_URL));

		return states;
	}
}