package com.ordrit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrdrItDataBase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_NAME = "OrdrIt.db";
	
	// Table Store start
	
	private static final String TABLE_STORE="Store";
	
	private static final String COLUMN_STORE_STORE_ID = "storeId";
	private static final String COLUMN_STORE_NAME="storeName";
	private static final String COLUMN_STORE_LOCATION_LATLONG="locationLatLong";
	private static final String COLUMN_STORE_ID="id";
	private static final String COLUMN_STORE_URL="Url";
	private static final String COLUMN_STORE_PHONE_NUMBER_1="phoneNumber1";
	private static final String COLUMN_STORE_PHONE_NUMBER_2="phoneNumber2";
	private static final String COLUMN_STORE_USER_ID="userId";
	private static final String COLUMN_STORE__ADDRESS_ID= "addressId";
	
	private static final String CREATE_TABLE_STORE = "CREATE TABLE " + TABLE_STORE + "("
			+ COLUMN_STORE_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_STORE_NAME + " TEXT," 
			+ COLUMN_STORE_LOCATION_LATLONG + " TEXT,"
			+ COLUMN_STORE_ID + " TEXT,"
			+ COLUMN_STORE_URL + " TEXT," 
			+ COLUMN_STORE_PHONE_NUMBER_1 + " TEXT," 
			+ COLUMN_STORE_PHONE_NUMBER_2 + " TEXT," 
			+ COLUMN_STORE_USER_ID + " TEXT," 
			+ COLUMN_STORE__ADDRESS_ID + " TEXT" + ")";
	
	// Table Store end
	
	// Table User start
	private static final String TABLE_USER="USER";
	private static final String COLUMN_USER_USER_ID = "userId";
	private static final String COLUMN_USER_EMAILID="emailId";
	private static final String COLUMN_USER_FIRST_NAME="firstName";
	private static final String COLUMN_USER_LAST_NAME="lastName";
	private static final String COLUMN_USER_ROLE="role";
	private static final String COLUMN_USER_JOIN_DATE="joinDate";
	private static final String COLUMN_USER_LAST_LOGIN_DATE="lastLoginDate";
	private static final String COLUMN_USER_ID="id";
	private static final String COLUMN_USER_PHONE_NUMBER="phoneNumber";
	private static final String COLUMN_USER_GCM_REGISTRATION_ID="gcmRegistrationId";
	private static final String COLUMN_USER_URL="url";
	
	private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "("
			+ COLUMN_USER_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_USER_EMAILID + " TEXT," 
			+ COLUMN_USER_FIRST_NAME + " TEXT,"
			+ COLUMN_USER_LAST_NAME + " TEXT,"
			+ COLUMN_USER_ROLE + " TEXT," 
			+ COLUMN_USER_JOIN_DATE + " TEXT," 
			+ COLUMN_USER_LAST_LOGIN_DATE + " TEXT," 
			+ COLUMN_USER_ID + " TEXT,"
			+ COLUMN_USER_PHONE_NUMBER + " TEXT,"
			+ COLUMN_USER_GCM_REGISTRATION_ID + " TEXT,"
			+ COLUMN_USER_URL + " TEXT" + ")";
	// End User table
	
	// start Address table
	private static final String TABLE_ADDRESS="Address";
	private static final  String COLUMN_ADDRESS_ADDRESS_ID="addressId";
	private static final  String COLUMN_ADDRESS_ID="id";
	private static final  String COLUMN_ADDRESS_STREET_ADDRESS="streetAddress";
	private static final  String COLUMN_ADDRESS_CITY="city";
	private static final  String COLUMN_ADDRESS_STATE="State";
	private static final  String COLUMN_ADDRESS_PINCODE="pincode";
	private static final  String COLUMN_ADDRESS_URL="url";
	
	private static final String CREATE_TABLE_ADDRESS_ = "CREATE TABLE " + TABLE_ADDRESS + "("
			+ COLUMN_ADDRESS_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_ADDRESS_ID + " TEXT," 
			+ COLUMN_ADDRESS_STREET_ADDRESS + " TEXT,"
			+ COLUMN_ADDRESS_CITY + " TEXT,"
			+ COLUMN_ADDRESS_STATE + " TEXT," 
			+ COLUMN_ADDRESS_PINCODE + " TEXT,"
			+ COLUMN_ADDRESS_URL + " TEXT" + ")";
	
	// End Address table
	
	public OrdrItDataBase(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_STORE);
		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_ADDRESS_);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
		onCreate(db);
	}


}
