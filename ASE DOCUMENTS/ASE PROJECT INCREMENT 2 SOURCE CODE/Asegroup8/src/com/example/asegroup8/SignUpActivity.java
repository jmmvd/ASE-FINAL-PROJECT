package com.example.asegroup8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class SignUpActivity extends Activity {
	
	
	private Button submit;
	private EditText FName,LName,Email,City,State,Country,ZipCode,Address,UserName,pwd,cnfpwd;
	
	RegisterAdapter register;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		register=new RegisterAdapter(this); 
		register=register.open();
		
		
		FName=(EditText)findViewById(R.id.et_fname); 
		LName=(EditText)findViewById(R.id.et_lname); 
		Email=(EditText)findViewById(R.id.et_email);
		City=(EditText)findViewById(R.id.et_city);		
		State=(EditText)findViewById(R.id.et_state);
		Country=(EditText)findViewById(R.id.et_country);
		ZipCode=(EditText)findViewById(R.id.et_zip);		
		Address=(EditText)findViewById(R.id.et_address);
		UserName=(EditText)findViewById(R.id.et_cuser);
		pwd=(EditText)findViewById(R.id.et_cpwd);
		cnfpwd=(EditText)findViewById(R.id.et_cnfpwd);		
		
		submit =(Button)findViewById(R.id.btnregsub);
		
		
submit.setOnClickListener(new View.OnClickListener() {			
			
	public void onClick(View v) {
		String firstname=FName.getText().toString(); 
		String lastname=LName.getText().toString(); 
		String email=Email.getText().toString();
		String city=City.getText().toString();
		String state=State.getText().toString();
		String country=Country.getText().toString();
		String zipcode=ZipCode.getText().toString();
		String address=Address.getText().toString();
		String username=UserName.getText().toString();
		String password=pwd.getText().toString();
		String cnfpassword=cnfpwd.getText().toString();
						
				 
		if(firstname.equals("")||lastname.equals("")||email.equals("")||city.equals("")||state.equals("")||country.equals("")||zipcode.equals("")||address.equals("")||username.equals("")||password.equals("")||cnfpassword.equals("")) 
		{ 
			Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show(); 
				return; 
		} 
				

		else 
		{ 			
			if(pwd.getText().toString().equals(cnfpwd.getText().toString()))
			{
				register.insertEntry(firstname,lastname,email,address,city,state,country,zipcode,username,password,cnfpassword);
				
				//FName.setText("");LName.setText("");Email.setText("");City.setText("");State.setText("");Country.setText("");
				//ZipCode.setText("");Address.setText("");UserName.setText("");pwd.setText("");cnfpwd.setText("");
			
				//Toast.makeText(getApplicationContext(), "Registered Successfully ", Toast.LENGTH_LONG).show();	
				
				Intent in = new Intent(SignUpActivity.this,UStudyHomeActivity.class);
		         startActivity(in);
				
			}
			
			else
			{
				Toast.makeText(getApplicationContext(), "Please Confirm the Right Password ", Toast.LENGTH_LONG).show();
				
			}
		} 	
		 
				
		}
		});


		
	}
	protected void onDestroy() { 
		// TODO Auto-generated method stub 
		super.onDestroy();

		register.close(); 
		} 
		}

		