package com.example.asegroup8;

import android.content.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log; 


public class DatabaseConnection extends SQLiteOpenHelper {
	
	public DatabaseConnection(Context context, String name,CursorFactory factory, int version) 
	{ 
	super(context, name, factory, version); 
	
	}

	public void onCreate(SQLiteDatabase _db) {
		
		_db.execSQL(RegisterAdapter.DATABASE_CREATE);
	}

	
	public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
		
		Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data"); 
		_db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE"); 
		onCreate(_db);
	} 
}
