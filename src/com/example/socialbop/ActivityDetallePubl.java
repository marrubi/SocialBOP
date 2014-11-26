package com.example.socialbop;

import com.example.socialbop.db.DBOpenHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;
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
		tvNombre.setText(nombre);
		
		TextView tvRaza = (TextView) findViewById(R.id.textView2);
		tvRaza.setText(raza);
		
		TextView tvGenero = (TextView) findViewById(R.id.textView3);
		tvGenero.setText(genero);
		
		TextView tvLugar = (TextView) findViewById(R.id.textView4);
		tvLugar.setText(lugar);
		
		TextView tvFecha = (TextView) findViewById(R.id.textView5);
		tvFecha.setText(fecha);
		
		TextView tvRecompensa = (TextView) findViewById(R.id.textView6);
		tvRecompensa.setText(recompensa);
		
		TextView tvDetalles = (TextView) findViewById(R.id.textView7);
		tvDetalles.setText(detalles);	
		
		ImageView imageview = (ImageView) findViewById(R.id.ivDV1);
		DisplayMetrics metrics = new DisplayMetrics(); 

        getWindowManager().getDefaultDisplay().getMetrics(metrics); 


        
        Bitmap ibitmap = BitmapFactory.decodeByteArray(fotopubl, 0, fotopubl.length);

        float scaleWidth = metrics.scaledDensity;
        float scaleHeight = metrics.scaledDensity;
        
        int width = ibitmap.getWidth();
        int height = ibitmap.getHeight();
        
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(ibitmap, 0, 0, width, height, matrix, true);
        
		imageview.setImageBitmap(resizedBitmap);
		
		helper = new DBOpenHelper(this);
				
		SharedPreferences settings1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
		String userlogeado = settings1.getString("name", "");
		
		Button buttCerrar = (Button) findViewById(R.id.buttoncerrar);
		Button buttContactar = (Button) findViewById(R.id.buttoncontactar);
			
		if(userpubl.equals(userlogeado)){
			buttCerrar.setVisibility(View.VISIBLE);
		}
		else{
			buttContactar.setVisibility(View.VISIBLE);
		}
		
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
		
		new AlertDialog.Builder(this)
	    .setTitle("Cerrar Publicación")
	    .setMessage("¿Seguro que desea cerrar esta publicación?")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
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
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	    .show();
		
	}
	
	
	public void contactarPubl(View view){
		String mail = "";
		new AlertDialog.Builder(this)
	    .setTitle("Contacto")
	    .setMessage("Mail de Contacto: " + mail)
	    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	
	    		finish();
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_info)
	    .show();
		
	}
	
	
}


