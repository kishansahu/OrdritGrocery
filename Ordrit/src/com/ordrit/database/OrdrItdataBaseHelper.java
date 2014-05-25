package com.ordrit.database;

import com.ordrit.model.Store;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OrdrItdataBaseHelper {
	private SQLiteDatabase sqLiteDatabase;
	private OrdrItDataBase ordrItDataBase;

	public OrdrItdataBaseHelper(Context context) {
		ordrItDataBase = new OrdrItDataBase(context);
	}

	private void open() throws SQLException {
		sqLiteDatabase = ordrItDataBase.getWritableDatabase();
	}

	private void close() {
		ordrItDataBase.close();
	}
	
	public Store getStore() {
		open();
		Store store=new Store();
		
		//get user table data
		
		
		close();
		return store;
		
	}
	
}
