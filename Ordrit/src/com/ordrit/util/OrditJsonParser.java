package com.ordrit.util;

import org.json.JSONException;
import org.json.JSONObject;


public class OrditJsonParser {
	
	public static String getTokenStringFromJSON(JSONObject obj) throws JSONException{
		String token= "";
		token= obj.get(OrdritJsonKeys.TAG_TOKEN).toString();
		return token;
	}
	
	/*public static List<Brand> getAllPremiumLogosFromJSONArray(JSONArray brandsArray) throws JSONException{
		List<Brand> brandList = new ArrayList<Brand>();
		for(int i=0; i<brandsArray.length();i++){
			JSONObject brandJson= brandsArray.getJSONObject(i).getJSONObject(YourKeyJsonKeys.TAG_LOGO);
			String categoryType = brandJson.getJSONObject(YourKeyJsonKeys.TAG_BRAND_TYPE).get(YourKeyJsonKeys.TAG_BRAND_CATEGORY_TYPE).toString();
			if(categoryType.equalsIgnoreCase(YourKeyJsonKeys.TAG_CATEGORY_TYPE_PREMIUM)){
			Brand brand = new Brand();
			brand.setActive(brandJson.getBoolean(YourKeyJsonKeys.TAG_BRAND_ACTIVE));
			brand.setCategoryType(brandJson.getJSONObject(YourKeyJsonKeys.TAG_BRAND_TYPE).get(YourKeyJsonKeys.TAG_BRAND_CATEGORY_TYPE).toString());
			brand.setCategoryId(brandJson.getJSONObject(YourKeyJsonKeys.TAG_BRAND_TYPE).get(YourKeyJsonKeys.TAG_BRAND_CATEGORY_ID).toString());
			brand.setLogoUrl(brandJson.getString(YourKeyJsonKeys.TAG_BRAND_LOGO_URL));
			brand.setName(brandJson.getString(YourKeyJsonKeys.TAG_BRAND_NAME) + categoryType);
			brand.setExternalId(brandJson.getString(YourKeyJsonKeys.TAG_BRAND_ID).toString());
			brandList.add(brand);
			}
		}
		
		return brandList;
	
}*/}