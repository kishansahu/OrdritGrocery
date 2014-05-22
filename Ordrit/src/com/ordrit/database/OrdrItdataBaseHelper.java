package com.ordrit.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OrdrItdataBaseHelper {
	private SQLiteDatabase sqLiteDatabase;
	private OrdrItDataBase ordrItDataBase;

	public OrdrItdataBaseHelper(Context context) {
		ordrItDataBase = new OrdrItDataBase(context);
	}

	public void open() throws SQLException {
		sqLiteDatabase = ordrItDataBase.getWritableDatabase();
	}

	public void close() {
		ordrItDataBase.close();
	}
	
	
	
}
