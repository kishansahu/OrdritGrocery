package com.ordrit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.model.Address;
import com.ordrit.model.City;
import com.ordrit.model.Item;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.model.State;
import com.ordrit.model.Store;
import com.ordrit.model.User;

public class OrditJsonParser {

	public static String getTokenStringFromJSON(String jSONOString)
			throws JSONException {
		JSONObject obj=new JSONObject(jSONOString);
		String token = "";
		token = obj.get(OrdritJsonKeys.TAG_TOKEN).toString();
		return token;
	}

	public static Map getItemCategoryMap(String jsonString) throws JSONException{
		
		Map<String, ItemCategory> itemCategoryMap = new TreeMap<String, ItemCategory>();
	//	String str = "[{\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Atta & Flours\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:33.718Z\", \"id\": 13, \"url\": \"http://staging.ankursethi.in/item_sub_categories/13\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}, {\"name\": \"Dal & Pulses\", \"category\": {\"name\": \"Grocery & Staples\", \"created_on\": \"2014-04-21T15:12:04.419Z\", \"id\": 5, \"url\": \"http://staging.ankursethi.in/item_categories/5\"}, \"created_on\": \"2014-04-21T15:39:49.139Z\", \"id\": 14, \"url\": \"http://staging.ankursethi.in/item_sub_categories/14\"}]";
		try {
			JSONArray jsonArray = new JSONArray(jsonString);
			for(int i = 0; i < jsonArray.length(); i++){ 
				JSONObject subCategoryJSONObj = jsonArray.getJSONObject(i);
			   JSONObject categoryObj= subCategoryJSONObj.getJSONObject(OrdritJsonKeys.TAG_CATEGORY);
			   ItemCategory itemCategory=null;
			   String categoryId= categoryObj.getString(OrdritJsonKeys.TAG_ID);
			   if(!itemCategoryMap.containsKey(categoryId)){
			    itemCategory= new ItemCategory();
			    itemCategory.setId(categoryId);
			    itemCategory.setName(categoryObj.getString(OrdritJsonKeys.TAG_NAME));
			    itemCategory.setUrl((categoryObj.getString(OrdritJsonKeys.TAG_URL)));
			    itemCategory.setItemSubCategory(new ArrayList<ItemSubCategory>());
			   }else{
			    itemCategory= itemCategoryMap.get(categoryId);
			   }
			   ItemSubCategory itemSubCategory= new ItemSubCategory(); 
			   itemSubCategory.setId(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_ID));
			   itemSubCategory.setName(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_NAME));
			   itemSubCategory.setUrl(subCategoryJSONObj.getString(OrdritJsonKeys.TAG_URL));
			   itemCategory.getItemSubCategory().add(itemSubCategory);
			   
			   itemCategoryMap.put(itemCategory.getId(), itemCategory);}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itemCategoryMap;
	}
	
	public static List<Store> getAllStoresFromJSON(String jsonString)
			throws JSONException {
		JSONObject obj=new JSONObject(jsonString);
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
			State state = new State();
			state.setUrl(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_STATE));
			merchantAddress.setState(state);
			City city= new City();
			city.setUrl(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_CITY));
			merchantAddress.setCity(city);
			merchantAddress.setPincode(storeJsonObj.getJSONObject(
					OrdritJsonKeys.TAG_ADDRESS).getString(
					OrdritJsonKeys.TAG_PINCODE));
			store.setAddress(merchantAddress);
			storeList.add(store);
		}
		return storeList;
	}
	public static User getUserFromJSON(String jsonString) throws JSONException{
		JSONObject obj=new JSONObject(jsonString);
		
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
	public static Address getMerchantAddressFromJSON(String jsonString) throws JSONException{
		JSONObject jsonObject=new JSONObject(jsonString);
		
		Address merchantAddress = new Address();

		merchantAddress.setStreetAddress(jsonObject.getString(
				OrdritJsonKeys.TAG_STREET_ADDRESS));
		State state = new State();
		state.setUrl(jsonObject.getString(
				OrdritJsonKeys.TAG_STATE));
		merchantAddress.setState(state);
		City city= new City();
		city.setUrl(jsonObject.getString(
				OrdritJsonKeys.TAG_CITY));
		merchantAddress.setCity(city);
		merchantAddress.setPincode(jsonObject.getString(
				OrdritJsonKeys.TAG_PINCODE));
		/*merchantAddress.setUrl(jsonObject.getString(
				OrdritJsonKeys.TAG_URL));*/
		return merchantAddress;
	}

	public static List<State> getStateFromJSONArray(String jsonString)throws JSONException {
		JSONArray jsonArray= new JSONArray(jsonString);
		List<State> states = new ArrayList<State>();
		for (int i = 0; i < jsonArray.length(); i++) {
			states.add(getStateFromJSON(jsonArray.getJSONObject(i)));
		}

		return states;
	}
	public static List<City> getCityFromJSONArray(String jsonString)throws JSONException {
		JSONArray jsonArray= new JSONArray(jsonString);
		List<City> cities = new ArrayList<City>();
		for (int i = 0; i < jsonArray.length(); i++) {
			cities.add(getCityFromJSON(jsonArray.getJSONObject(i)));
		}

		return cities;
	}
	public static State getStateFromJSON(JSONObject jsonObject) throws JSONException{
		State states=new State();
		states.setName(jsonObject.getString(OrdritJsonKeys.TAG_NAME));
		states.setUrl(jsonObject.getString(OrdritJsonKeys.TAG_URL));

		return states;
	}
	public static City getCityFromJSON(JSONObject jsonObject) throws JSONException{
		City city=new City();
		city.setName(jsonObject.getString(OrdritJsonKeys.TAG_NAME));
		city.setUrl(jsonObject.getString(OrdritJsonKeys.TAG_URL));

		return city;
	}
	public static List<Item> getItemsUnderSubCategory(String storeId, String itemSubCategoryId,String jSONString) throws JSONException{
	
		List<Item> itemList= new ArrayList<Item>();
		JSONObject jsonObj = new JSONObject(jSONString);
		JSONArray jsonArray = jsonObj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject itemJsonObj = jsonArray.getJSONObject(i);
			String currentObjsubCategoryId= itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_SUB_CATEGORY).getString(OrdritJsonKeys.TAG_ID);
			String currentObjStoreId= itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_STORE).getString(OrdritJsonKeys.TAG_ID);
			if(storeId.equalsIgnoreCase(currentObjStoreId) && itemSubCategoryId.equalsIgnoreCase(currentObjsubCategoryId)){
				Item item= new Item();
				item.setId(itemJsonObj.getString(OrdritJsonKeys.TAG_ID));
				item.setPricePerUnit(itemJsonObj.getString(OrdritJsonKeys.TAG_PRICE));
				item.setUnitName(itemJsonObj.getString(OrdritJsonKeys.TAG_PRICE_UNITS));
				item.setName(itemJsonObj.getString(OrdritJsonKeys.TAG_NAME));
				item.setImageURL(OrdritConstants.SERVER_BASE_URL
						+ itemJsonObj.getJSONObject(OrdritJsonKeys.TAG_IMAGE)
								.getString(OrdritJsonKeys.TAG_IMAGE));
				itemList.add(item);
			}
		}
		return itemList;
	}
	
	public static User updateUserWithAddress(User user, JSONObject jsonObj) throws JSONException{
		jsonObj= new JSONObject("{\"count\": 2, \"next\": null, \"previous\": null, \"results\": [{\"user\": \"http://staging.ankursethi.in/users/3\", \"street_address\": \"56 ,easyriderPlaza kasol \", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": {\"name\": \"Haryana\", \"created_on\": \"2014-04-22T11:31:07.235Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/states/1\"}, \"pin_code\": \"12005\", \"created_on\": \"2014-05-05T17:37:13.609Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/user_addresses/1\"}, {\"user\": \"http://staging.ankursethi.in/users/8\", \"street_address\": \"B23, Baker Street\", \"city\": \"http://staging.ankursethi.in/cities/1\", \"state\": {\"name\": \"Haryana\", \"created_on\": \"2014-04-22T11:31:07.235Z\", \"id\": 1, \"url\": \"http://staging.ankursethi.in/states/1\"}, \"pin_code\": \"110034\", \"created_on\": \"2014-06-20T11:07:01.998Z\", \"id\": 2, \"url\": \"http://staging.ankursethi.in/user_addresses/2\"}]}");
		JSONArray jsonArray= jsonObj.getJSONArray(OrdritJsonKeys.TAG_RESULTS);
		for(int i=0; i<= jsonArray.length();i++){
			JSONObject itemJsonObj = jsonArray.getJSONObject(i);
			if(itemJsonObj.getString(OrdritJsonKeys.TAG_USER).equalsIgnoreCase(OrdritConstants.SERVER_BASE_URL+OrdritJsonKeys.TAG_USERS+"/"+user.getId())){
				
				Address userAddress = new Address();
				userAddress.setId(itemJsonObj.getString(
						OrdritJsonKeys.TAG_ID));
				userAddress.setStreetAddress(itemJsonObj.getString(
						OrdritJsonKeys.TAG_STREET_ADDRESS));
				userAddress.setPincode(itemJsonObj.getString(
						OrdritJsonKeys.TAG_PINCODE));
				
				City city= new City();
				city.setUrl(itemJsonObj.getString(
						OrdritJsonKeys.TAG_CITY));
				userAddress.setCity(city);
				State state = new State();
				state.setName(itemJsonObj.getJSONObject(
						OrdritJsonKeys.TAG_STATE).getString(
						OrdritJsonKeys.TAG_NAME));
				state.setUrl(itemJsonObj.getJSONObject(
						OrdritJsonKeys.TAG_STATE).getString(
						OrdritJsonKeys.TAG_URL));
				userAddress.setState(state);
				user.setAddress(userAddress);
			}	
			
		}
				
		return user;
	}
}