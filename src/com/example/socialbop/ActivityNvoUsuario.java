package com.example.socialbop;

import com.example.socialbop.db.DBOpenHelper;
import com.example.socialbop.db.DataSource;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ActivityNvoUsuario extends Activity {
	
	DBOpenHelper helper;
	DataSource ds;
	EditText etNombres, etApellidos, etEdad, etTelefono, etCorreo, etUsuario, etContrasena, etConfContrasena;
	RadioButton rbF, rbM;
	String nombres, apellidos, telefono, correo, usuario, contraseña, confcontraseña, edadstring;
	int genero = 0;
	int edad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ds = new DataSource(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nvousuario);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		etNombres = (EditText) findViewById(R.id.etA3Nombres);
		etApellidos = (EditText) findViewById(R.id.etA3Apellidos);
		etEdad = (EditText) findViewById(R.id.etA3Edad);
		etTelefono = (EditText) findViewById(R.id.etA3Telefono);
		etCorreo = (EditText) findViewById(R.id.etA3Correo);
		etUsuario = (EditText) findViewById(R.id.etA3Usuario);
		etContrasena = (EditText) findViewById(R.id.etA3Contrasena);
		etConfContrasena = (EditText) findViewById(R.id.etA3ConfContrasena); 
		rbF = (RadioButton) findViewById(R.id.rbA3F);
		rbM = (RadioButton) findViewById(R.id.rbA3M);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity3, menu);
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
			case R.id.action_nuevo_usuario:
				if(validarCampos()){
					helper = new DBOpenHelper(this);
					Usuario us = new Usuario();
					Login log = new Login();
					us.setNombres(nombres);
					us.setApellidos(apellidos);
					us.setGenero(genero);
					us.setEdad(edad);
					us.setTelefono(telefono);
					us.setCorreo(correo);
					log.setUsuario(usuario);
					log.setContrasena(contraseña);
					
					helper.insertarLogin(log);
					
					int idlogin = helper.getIDLogin(log.getUsuario(), log.getContrasena());
					
					if(idlogin != 0){
						helper.insertarUsuario(us, idlogin);
						Toast.makeText(this, "Usuario Agregado", Toast.LENGTH_SHORT).show();
						finish();
					}
					else{
						Toast.makeText(this, "No se puede ingresar el usuario a la bd", Toast.LENGTH_SHORT).show();
					}
				}
				
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public boolean validarCampos(){
		nombres = etNombres.getText().toString();
		apellidos = etApellidos.getText().toString();
		telefono = etTelefono.getText().toString();
		edadstring = etEdad.getText().toString();
		correo = etCorreo.getText().toString();
		usuario = etUsuario.getText().toString();
		contraseña = etContrasena.getText().toString();
		confcontraseña = etConfContrasena.getText().toString();
		
		if (nombres.length() == 0) {
			Toast.makeText(this, "Debe ingresar nombre del contacto.", Toast.LENGTH_SHORT).show();
			return false;
			
		}
		if (apellidos.length() == 0) {
			Toast.makeText(this, "Debe ingresar apellido", Toast.LENGTH_SHORT).show();
			return false;
		}
		if (telefono.length() == 0) {
			Toast.makeText(this, "Debe ingresar teléfono", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(edadstring.length() == 0){
			Toast.makeText(this, "Debe ingresar edad", Toast.LENGTH_SHORT).show();
			return false;
		}
		else{
			edad = Integer.parseInt(etEdad.getText().toString());
			if(edad < 18 || edad > 100){
				Toast.makeText(this, "Edad no debe ser menor a 18 años ni mayor a 100 años", Toast.LENGTH_SHORT).show();
				return false;
			}
		}
		
		if (!rbF.isChecked() && !rbM.isChecked()) {
			Toast.makeText(this, "Debe seleccionar género", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(correo.length() == 0){
			Toast.makeText(this, "Debe ingresar correo", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(usuario.length() == 0){
			Toast.makeText(this, "Debe ingresar usuario", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(contraseña.length() == 0){
			Toast.makeText(this, "Debe ingresar contraseña", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(confcontraseña.length() == 0){
			Toast.makeText(this, "Debe ingresar confirmación de contraseña", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(confcontraseña.length() == 0){
			Toast.makeText(this, "Debe ingresar confirmación de contraseña", Toast.LENGTH_SHORT).show();
			return false;
		}
		if(!confcontraseña.equals(contraseña)){
			Toast.makeText(this, "Campos contraseña y confirmación de contraseña deben ser iguales", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	public void isClicked(View v){
		switch(v.getId()) {
	        case R.id.rbA3F:
	            if (rbF.isChecked()){
	            	genero = 1;
	            }  
	            break;
	        case R.id.rbA3M:
	            if (rbM.isChecked()){
	            	genero = 2;
	            }  
	            break;
		}
	}
}
