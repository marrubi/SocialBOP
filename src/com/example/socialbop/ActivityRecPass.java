package com.example.socialbop;

import com.example.socialbop.db.DBOpenHelper;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class ActivityRecPass extends Activity {

	
	EditText email;
	DBOpenHelper correo;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recpass);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity6, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case android.R.id.home:
				finish();
				break;
			case R.id.action_enviar:
				try{
					//aqui retorno lo de getPassMail en Long c pero me retorno un 1, es decir al email que puse me mando
					//su password es:1
					email = (EditText)findViewById(R.id.etA1Usuario);
					correo = new DBOpenHelper(this);
					long c = correo.getPassMail(email.getText().toString());
					GmailSender sender = new GmailSender("soportebop@gmail.com", "movilutembop");
					sender.sendMail("Recuperación de password", 
									"Su password es:"+c, 
									"soportebop@gmail.com", 
									email.getText().toString());
				}catch(Exception e){
					Log.e("SendMail", e.getMessage(), e); 
				}
				
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
