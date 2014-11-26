package com.example.socialbop;

import java.util.List;

import com.example.socialbop.db.DataSource;
import com.example.socialbop.R;
import com.example.socialbop.lib.PublicacionAdapter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityPublicaciones extends ListActivity {

	public final static String COPIAR_TEXTO = "com.example.socialbop.USER";
	private static final int AGREGAR_PUBLICACION_ACTIVITY = 1001;
	private static final int BUSCAR_PUBLICACION_ACTIVITY = 1003;
	private static final int CERRAR_PUBLICACION = 1004;

	
	List<Publicacion> publicaciones;
	DataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publicaciones);
		
		datasource = new DataSource(this);
		datasource.openDB();
		publicaciones = datasource.obtenerPublicaciones();
		
		actualizarList();
	}

	public void actualizarList(){
		PublicacionAdapter adapter = new PublicacionAdapter(this, R.layout.detallepublicacion_item, publicaciones);
		setListAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity5, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
			case R.id.action_nuevo:
				Intent intent3 = getIntent();
				String nombreuser = intent3.getStringExtra(ActivityLogin.COPIAR_TEXTO);
				Intent intent = new Intent(this, ActivityNvaPublicacion.class);
				SharedPreferences settings1 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
				String name = settings1.getString("name", "");
				intent.putExtra(COPIAR_TEXTO, name);
				startActivityForResult(intent, AGREGAR_PUBLICACION_ACTIVITY);
				break;
			case R.id.action_buscar:
				Intent intent2 = new Intent(this, ActivityBuscarPerro.class);
				startActivityForResult(intent2, BUSCAR_PUBLICACION_ACTIVITY);
				break;
			case R.id.action_mis_publicaciones:
				SharedPreferences settings2 = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
				String usuariopubli = settings2.getString("name", "");
				String query = "SELECT * FROM publicacion WHERE usuariopubl=? AND estadopubl=?";
				String busqueda1 = usuariopubli;
				String busqueda2 = "up";
				datasource.openDB();
				publicaciones = datasource.obtenerPublicacionesdebuscar2(query,busqueda1,busqueda2);
				datasource.closeDB();
				actualizarList();
				break;
			case R.id.action_cerrar_sesion:
				SharedPreferences settings = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("name", "");
				editor.putString("pass", "");
				editor.commit();
				Intent intent1 = new Intent(ActivityPublicaciones.this,ActivityLogin.class);
				startActivity(intent1);
				finish();
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Publicacion publicacion = publicaciones.get((l.getCount()-1) - position);
		
		Intent intent = new Intent(this, ActivityDetallePubl.class);
		
		intent.putExtra("id_publ", publicacion.getId());
		intent.putExtra("nombre", publicacion.getNombre());
		intent.putExtra("raza", publicacion.getRaza());
		intent.putExtra("recompensa", publicacion.getRecompensa());
		intent.putExtra("lugar", publicacion.getLugar());
		intent.putExtra("fecha", publicacion.getFecha());
		intent.putExtra("userpubl", publicacion.getUsuariopubl());
		intent.putExtra("genero", publicacion.getGenero());
		intent.putExtra("detalles", publicacion.getDetalles());
		intent.putExtra("estadopubl", publicacion.getEstadopubl());
		intent.putExtra("foto", publicacion.getFoto());
		startActivityForResult(intent, CERRAR_PUBLICACION);
		
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == AGREGAR_PUBLICACION_ACTIVITY && resultCode == 1) {
			datasource.openDB();
			publicaciones = datasource.obtenerPublicaciones();
			datasource.closeDB();
			actualizarList();
		}
		if (requestCode == BUSCAR_PUBLICACION_ACTIVITY && resultCode == 2) {
			String razapet = "";
			String generopet = "";
			if(data.getExtras().containsKey("genero")){
				generopet = data.getStringExtra("genero").toString();
			}
			if(data.getExtras().containsKey("raza")){
				razapet = data.getStringExtra("raza").toString();
			}
			
			
			if(generopet.equals("cualquiera") && razapet.equals("Cualquier Raza")){
				datasource.openDB();
				publicaciones = datasource.obtenerPublicaciones();
				datasource.closeDB();
				actualizarList();
			}
			else if(generopet.equals("cualquiera")){
				String query = "SELECT * FROM publicacion WHERE raza =? AND estadopubl=?";
				String busqueda1 = razapet;
				String busqueda2 = "up";
				datasource.openDB();
				publicaciones = datasource.obtenerPublicacionesdebuscar2(query,busqueda1,busqueda2);
				datasource.closeDB();
				actualizarList();
			}
			else if(razapet.equals("Cualquier Raza")){
				String query = "SELECT * FROM publicacion WHERE genero =? AND estadopubl=?";
				String busqueda1 = generopet;
				String busqueda2 = "up";
				datasource.openDB();
				publicaciones = datasource.obtenerPublicacionesdebuscar2(query,busqueda1,busqueda2);
				datasource.closeDB();
				actualizarList();
			}
			else{
				String query = "SELECT * FROM publicacion WHERE raza =? AND genero=? AND estadopubl=?";
				String busqueda1 = razapet;
				String busqueda2 = generopet;
				String busqueda3 = "up";
				datasource.openDB();
				publicaciones = datasource.obtenerPublicacionesdebuscar(query,busqueda1,busqueda2,busqueda3);
				datasource.closeDB();
				actualizarList();
			}
			
		}
		if (requestCode == CERRAR_PUBLICACION && resultCode == 3){ 
			datasource.openDB();
			publicaciones = datasource.obtenerPublicaciones();
			datasource.closeDB();
			actualizarList();
		}
	}
	
	protected void onResume(){
		super.onResume();
		datasource.openDB();
		
	}

	protected void onPause(){
		super.onPause();
		datasource.closeDB();
	}
}
