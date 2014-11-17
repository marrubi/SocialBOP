package com.example.socialbop;

/*
 * Integrantes:

 * 	-Marco Arratia
 * 	-Matías Astorga 
 * 	-Daniel Abrilot
 */

import com.example.socialbop.db.DBOpenHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends Activity {
	
	public final static String COPIAR_TEXTO = "com.example.socialbop.USER";
	DBOpenHelper helper;
	EditText usuario;
	EditText contrasena;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		helper = new DBOpenHelper(this);
		helper.nuevologin("nombres1","apellidos1",1,"telefono1","correo1","user", "pass");
		
		usuario = (EditText) findViewById(R.id.etA1Usuario);
		contrasena = (EditText) findViewById(R.id.etA1Contrasena);
		
		SharedPreferences prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
		String name = prefs.getString("name", "");
		String pass = prefs.getString("pass", "");
		if(helper.Login(name, pass)){
			Intent intent = new Intent(this, ActivityPublicaciones.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.siguiente:
			SharedPreferences settings = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("name", usuario.getText().toString());
			editor.putString("pass", contrasena.getText().toString());
			editor.commit();
			
			if(helper.Login(usuario.getText().toString(), contrasena.getText().toString())){
				Intent intent = new Intent(this, ActivityPublicaciones.class);
				intent.putExtra(COPIAR_TEXTO, usuario.getText().toString());
				startActivity(intent);
				finish();
			}
			else{
				Toast.makeText(getApplicationContext(),"Login Incorrecto", Toast.LENGTH_SHORT).show();
			}
			return true;
		case R.id.nuevo_usuario:
			Intent intent = new Intent(ActivityLogin.this, ActivityNvoUsuario.class);
			startActivity(intent);
			return true;
		default:
			break;
		}
		if (id == R.id.siguiente) {
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void nuevo_usuario(View v){
		Intent intent = new Intent(ActivityLogin.this, ActivityNvoUsuario.class);
		startActivity(intent);
	}
	
	public void recup_contr(View v){
		Intent intent = new Intent(ActivityLogin.this, ActivityRecPass.class);
		startActivity(intent);
	}
}
