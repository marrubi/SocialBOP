package com.example.socialbop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Activity2 extends Activity {
	public static Activity fa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity2);
		fa = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_buscar) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void ir_menu_principal(View v){
		Intent i = new Intent(Activity2.this, Activity5.class);
		finish();
		startActivity(i);
	}
	
	public void nueva_publicacion(View v){
		Intent i = new Intent(Activity2.this, Activity7.class);
		startActivity(i);
	}
}
