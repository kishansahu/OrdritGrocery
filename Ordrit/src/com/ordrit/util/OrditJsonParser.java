package com.ordrit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ordrit.model.Address;
import com.ordrit.model.Item;
import com.ordrit.model.ItemCategory;
import com.ordrit.model.ItemSubCategory;
import com.ordrit.model.States;
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

	public static List<States> getStateFromJSONArray(String jsonString)throws JSONException {
		JSONArray jsonArray= new JSONArray(jsonString);
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
	
	public static List<Item> getItemsUnderSubCategory(String storeId, String itemSubCategoryId) throws JSONException{
		String str="{\"results\":[{\"id\":28,\"price\":\"24.00\",\"store\":{\"estimated_delivery_time\":45,\"location\":\"POINT (77.2799241000000023 28.3809262999999987)\",\"merchant\":\"http://staging.ankursethi.in/merchants/1\",\"created_on\":\"2014-04-22T11:43:06.247Z\",\"closes_at\":\"11:30:00\",\"phone_number_2\":\"\",\"opens_at\":\"09:00:00\",\"phone_number_1\":\"9958746143\",\"url\":\"http://staging.ankursethi.in/stores/1\",\"id\":1,\"address\":\"http://staging.ankursethi.in/store_addresses/1\",\"name\":\"Sunil Store 1\",\"sub_category\":null,\"minimum_order\":\"200.00\"},\"merchant\":{\"id\":1,\"stores\":[\"http://staging.ankursethi.in/stores/1\",\"http://staging.ankursethi.in/stores/2\",\"http://staging.ankursethi.in/stores/3\"],\"user\":\"http://staging.ankursethi.in/users/1\",\"url\":\"http://staging.ankursethi.in/merchants/1\",\"created_on\":\"2014-04-21T08:54:11.769Z\",\"name\":\"bowser@ordrit.in\"},\"created_on\":\"2014-04-22T19:35:39.241Z\",\"price_units\":\"KG\",\"description\":\"White Chawli\",\"name\":\"White Chawli\",\"image\":{\"id\":29,\"image\":\"inventory_item_images/2014/04/22/White_Chawli.jpeg\",\"sub_category\":\"http://staging.ankursethi.in/item_sub_categories/14\",\"url\":\"http://staging.ankursethi.in/product_images/29\",\"created_on\":\"2014-04-22T19:35:21.903Z\",\"name\":\"White Chawli\"},\"sub_category\":{\"id\":14,\"category\":\"http://staging.ankursethi.in/item_categories/5\",\"url\":\"http://staging.ankursethi.in/item_sub_categories/14\",\"created_on\":\"2014-04-21T15:39:49.139Z\",\"name\":\"Dal & Pulses\"},\"url\":\"http://staging.ankursethi.in/inventory_items/28\"},{\"id\":27,\"price\":\"61.00\",\"store\":{\"estimated_delivery_time\":45,\"location\":\"POINT (77.2799241000000023 28.3809262999999987)\",\"merchant\":\"http://staging.ankursethi.in/merchants/1\",\"created_on\":\"2014-04-22T11:43:06.247Z\",\"closes_at\":\"11:30:00\",\"phone_number_2\":\"\",\"opens_at\":\"09:00:00\",\"phone_number_1\":\"9958746143\",\"url\":\"http://staging.ankursethi.in/stores/1\",\"id\":1,\"address\":\"http://staging.ankursethi.in/store_addresses/1\",\"name\":\"Sunil Store 1\",\"sub_category\":null,\"minimum_order\":\"200.00\"},\"merchant\":{\"id\":1,\"stores\":[\"http://staging.ankursethi.in/stores/1\",\"http://staging.ankursethi.in/stores/2\",\"http://staging.ankursethi.in/stores/3\"],\"user\":\"http://staging.ankursethi.in/users/1\",\"url\":\"http://staging.ankursethi.in/merchants/1\",\"created_on\":\"2014-04-21T08:54:11.769Z\",\"name\":\"bowser@ordrit.in\"},\"created_on\":\"2014-04-22T19:30:39.887Z\",\"price_units\":\"KG\",\"description\":\"White urad Dal whole\",\"name\":\"White urad Dal whole\",\"image\":{\"id\":28,\"image\":\"inventory_item_images/2014/04/22/White_urad_Dal_whole.jpeg\",\"sub_category\":\"http://staging.ankursethi.in/item_sub_categories/14\",\"url\":\"http://staging.ankursethi.in/product_images/28\",\"created_on\":\"2014-04-22T19:30:20.850Z\",\"name\":\"White urad Dal whole\"},\"sub_category\":{\"id\":14,\"category\":\"http://staging.ankursethi.in/item_categories/5\",\"url\":\"http://staging.ankursethi.in/item_sub_categories/14\",\"created_on\":\"2014-04-21T15:39:49.139Z\",\"name\":\"Dal & Pulses\"},\"url\":\"http://staging.ankursethi.in/inventory_items/27\"}]}";
		List<Item> itemList= new ArrayList<Item>();
		JSONObject jsonObj = new JSONObject(str);
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
	
}