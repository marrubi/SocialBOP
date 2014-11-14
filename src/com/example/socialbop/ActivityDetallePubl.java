package com.example.socialbop;

import com.example.socialbop.db.DBOpenHelper;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
	
public class ActivityDetallePubl extends Activity {

	public DBOpenHelper helper;
	public Long id_publ;
	public String nombre;
	public String raza;
	public String recompensa;
	public String lugar;
	public String fecha;
	public String userpubl;
	public String genero;
	public String detalles;
	public String estadopubl;
	public String estadodown = "down";
	private byte[] fotopubl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle);
		
		Intent intent = getIntent();
		id_publ = intent.getLongExtra("id_publ", 10);
		nombre = intent.getStringExtra("nombre");
		raza = intent.getStringExtra("raza");
		recompensa = intent.getStringExtra("recompensa");
		lugar = intent.getStringExtra("lugar");
		fecha = intent.getStringExtra("fecha");
		userpubl = intent.getStringExtra("userpubl");
		genero = intent.getStringExtra("genero");
		detalles = intent.getStringExtra("detalles");
		estadopubl = intent.getStringExtra("estadopubl");
		fotopubl = intent.getByteArrayExtra("foto");
		
		int resId = getResources().getIdentifier("ic_launcher", "drawable", getPackageName());
		Uri uri = Uri.parse("android.resource://com.example.socialbop/drawable/ic_launcher");
		
		TextView tvNombre = (TextView) findViewById(R.id.textView1);
		tvNombre.setText("Nombre Mascota: " + nombre);
		
		TextView tvRaza = (TextView) findViewById(R.id.textView2);
		tvRaza.setText("Raza: " + raza);
		
		TextView tvGenero = (TextView) findViewById(R.id.textView3);
		tvGenero.setText("Género: " + genero);
		
		TextView tvLugar = (TextView) findViewById(R.id.textView4);
		tvLugar.setText("Lugar de Extravío: " + lugar);
		
		TextView tvFecha = (TextView) findViewById(R.id.textView5);
		tvFecha.setText("Fecha de Extravío: " + fecha);
		
		TextView tvRecompensa = (TextView) findViewById(R.id.textView6);
		tvRecompensa.setText("Recompensa: " + recompensa);
		
		TextView tvDetalles = (TextView) findViewById(R.id.textView7);
		tvDetalles.setText("Detalles: " + detalles);	
		
		ImageView imageview = (ImageView) findViewById(R.id.ivDV1);
		imageview.setImageBitmap(BitmapFactory.decodeByteArray(fotopubl, 0,
                fotopubl.length));
		
		helper = new DBOpenHelper(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getActionBar().setDisplayHomeAsUpEnabled(true);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			finish();
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void cerrarPubl(View view){
		
		Publicacion publ = new Publicacion();
		publ.setNombre(nombre);
		publ.setRaza(raza);
		publ.setGenero(genero);
		publ.setLugar(lugar);
		publ.setFecha(fecha);
		publ.setRecompensa(recompensa);
		publ.setDetalles(detalles);
		publ.setUsuariopubl(userpubl);
		publ.setEstadopubl(estadopubl);
		publ.setFoto(fotopubl);
		
		helper.actualizarPublicacion(publ,id_publ);
		
		setResult(3);
		finish();
	}
}


