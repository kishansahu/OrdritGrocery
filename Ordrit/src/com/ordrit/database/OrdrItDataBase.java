package com.ordrit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrdrItDataBase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_NAME = "OrdrIt.db";
	
	// Table Store start
	
	private static final String TABLE_STORE="Store";
	
	private static final String COLUMN_STORE_ID = "storeId";
	private static final String COLUMN_STORE_NAME="storeName";
	private static final String COLUMN_LOCATION_LATLONG="locationLatLong";
	private static final String COLUMN_ID="id";
	private static final String COLUMN_URL="Url";
	private static final String COLUMN_PHONE_NUMBER_1="phoneNumber1";
	private static final String COLUMN_PHONE_NUMBER_2="phoneNumber2";
	private static final String COLUMN_USER_ID="userId";
	private static final String COLUMN__ADDRESS_ID= "addressId";
	
	private static final String CREATE_KEY_TABLE_STORE = "CREATE TABLE " + TABLE_STORE + "("
			+ COLUMN_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_STORE_NAME + " TEXT," 
			+ COLUMN_LOCATION_LATLONG + " TEXT,"
			+ COLUMN_ID + " TEXT,"
			+ COLUMN_URL + " TEXT," 
			+ COLUMN_PHONE_NUMBER_1 + " TEXT," 
			+ COLUMN_PHONE_NUMBER_2 + " TEXT," 
			+ COLUMN_USER_ID + " TEXT," 
			+ COLUMN__ADDRESS_ID + " TEXT" + ")";
	
	// Table Store end
	
	public OrdrItDataBase(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_KEY_TABLE_STORE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORE);
        onCreate(db);
	}


}
