package com.ordrit.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.model.Address;
import com.ordrit.model.Store;
import com.ordrit.model.User;

public class OrditJsonParser {

	public static String getTokenStringFromJSON(JSONObject obj)
			throws JSONException {
		String token = "";
		token = obj.get(OrdritJsonKeys.TAG_TOKEN).toString();
		return token;
	}

	/*
	 * public static List<Brand> getAllPremiumLogosFromJSONArray(JSONArray
	 * brandsArray) throws JSONException{ List<Brand> brandList = new
	 * ArrayList<Brand>(); for(int i=0; i<brandsArray.length();i++){ JSONObject
	 * brandJson=
	 * brandsArray.getJSONObject(i).getJSONObject(YourKeyJsonKeys.TAG_LOGO);
	 * String categoryType =
	 * brandJson.getJSONObject(YourKeyJsonKeys.TAG_BRAND_TYPE
	 * ).get(YourKeyJsonKeys.TAG_BRAND_CATEGORY_TYPE).toString();
	 * if(categoryType
	 * .equalsIgnoreCase(YourKeyJsonKeys.TAG_CATEGORY_TYPE_PREMIUM)){ Brand
	 * brand = new Brand();
	 * brand.setActive(brandJson.getBoolean(YourKeyJsonKeys.TAG_BRAND_ACTIVE));
	 * brand
	 * .setCategoryType(brandJson.getJSONObject(YourKeyJsonKeys.TAG_BRAND_TYPE
	 * ).get(YourKeyJsonKeys.TAG_BRAND_CATEGORY_TYPE).toString());
	 * brand.setCategoryId
	 * (brandJson.getJSONObject(YourKeyJsonKeys.TAG_BRAND_TYPE
	 * ).get(YourKeyJsonKeys.TAG_BRAND_CATEGORY_ID).toString());
	 * brand.setLogoUrl
	 * (brandJson.getString(YourKeyJsonKeys.TAG_BRAND_LOGO_URL));
	 * brand.setName(brandJson.getString(YourKeyJsonKeys.TAG_BRAND_NAME) +
	 * categoryType);
	 * brand.setExternalId(brandJson.getString(YourKeyJsonKeys.TAG_BRAND_ID
	 * ).toString()); brandList.add(brand); } }
	 * 
	 * return brandList;
	 * 
	 * }
	 */
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
			merchant.setUserId(storeJsonObj.getJSONObject(
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
		return null;
	}

}