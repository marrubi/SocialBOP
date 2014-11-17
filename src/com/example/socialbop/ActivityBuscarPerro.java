package com.example.socialbop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityBuscarPerro extends Activity {
	
	RadioGroup RGSexo;
	RadioButton rbA5F,rbA5M;
	Spinner spinner3;
	String generopet,razapet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buscarperro);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		RGSexo = (RadioGroup) findViewById(R.id.RGSexo2);
		rbA5F = (RadioButton) findViewById(R.id.rbA5F);
		rbA5M = (RadioButton) findViewById(R.id.rbA5M);
		spinner3 = (Spinner) findViewById(R.id.spinner3);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity4, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_buscar:
			
			switch (RGSexo.getCheckedRadioButtonId()){
			case R.id.rbA5F:{
				generopet = "Macho";
			}
			break;

			case R.id.rbA5M:{
				generopet = "Hembra";
			}
			break;
			}
			
			razapet = String.valueOf(spinner3.getSelectedItem());
			
			//Guardar registros razapet y generopet, para leerlos a la vuelta en activity5.
			Intent i = getIntent();
			i.putExtra("genero", generopet);
			i.putExtra("raza", razapet);
			setResult(2, i);
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
