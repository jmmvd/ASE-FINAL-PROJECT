package com.example.asegroup8;

import android.content.ContentValues; 
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 


public class RegisterAdapter {
	
	static final String DATABASE_NAME = "register.db"; 
	static final int DATABASE_VERSION = 1; 
	public static final int NAME_COLUMN = 1;
	
	
	static final String DATABASE_CREATE = "create table "+"REGISTER"+ 
	"( " +"ID"+" integer primary key autoincrement,"+ "FIRSTNAME text,LASTNAME text,EMAIL text,ADDRESS text,CITY text,STATE text,COUNTRY text,ZIPCODE text,USERNAME text,PASSWORD text,CNFPASSWORD text); "; 
	
	private final Context context;
	public  SQLiteDatabase db;	
	private DatabaseConnection dbcon; 
	
	public  RegisterAdapter(Context _context) 
	{ 
	context = _context; 
	dbcon = new DatabaseConnection(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}
	
	public  RegisterAdapter open() throws SQLException 
	{ 
	db = dbcon.getWritableDatabase(); 
	return this; 
	} 

	public void close() 
	{ 
	db.close(); 
	} 
	
	public  SQLiteDatabase getDatabaseInstance() 
	{ 
	return db; 
	} 
	
	
    public void insertEntry(String firstname,String lastname,String email,String address,String city,String state,String country,String zipcode,String userName,String password,String confirmPassword) 
    { 
    	ContentValues newValues = new ContentValues(); 
    	//Assign values for each row.    	
    	newValues.put("FIRSTNAME", firstname);
    	newValues.put("LASTNAME",lastname);
    	newValues.put("EMAIL",email);
    	newValues.put("ADDRESS",address);
    	newValues.put("CITY",city);
    	newValues.put("STATE",state);
    	newValues.put("COUNTRY",country);
    	newValues.put("ZIPCODE",zipcode);
    	newValues.put("USERNAME",userName);
    	newValues.put("PASSWORD",password);
    	newValues.put("CNFPASSWORD",confirmPassword);
    	
    	db.insert("REGISTER", null, newValues);
    	if(password == confirmPassword )
    	{
    	//Insert the row into your table 
    	//db.insert("REGISTER", null, newValues);
    	
    	}
    	   	
    } 
    
    public int deleteEntry(String UserName) 
    { 
    	//String id=String.valueOf(ID); 
    	String where="USERNAME=?"; 
    	int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ; 
    	//Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show(); 
    	return numberOFEntriesDeleted; 
    } 

    public String getSinlgeEntry(String userName) 
    { 
    	Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null); 
    	if(cursor.getCount()<1) // UserName Not Exist 
    	{ 
    		cursor.close(); 
    		return "NOT EXIST"; 
    	} 
    	cursor.moveToFirst(); 
    	String password= cursor.getString(cursor.getColumnIndex("PASSWORD")); 
    	cursor.close(); 
    	return password; 
    } 

    public void  updateEntry(String userName,String password) 
    { 
    	//Define the updated row content. 
    	ContentValues updatedValues = new ContentValues(); 
    	//Assign values for each row. 
    	updatedValues.put("USERNAME", userName); 
    	updatedValues.put("PASSWORD",password);

    	String where="USERNAME = ?"; 
    	db.update("LOGIN",updatedValues, where, new String[]{userName}); 
    }

}
