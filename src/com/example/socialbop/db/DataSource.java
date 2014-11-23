package com.example.socialbop.db;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.socialbop.Login;
import com.example.socialbop.Publicacion;

public class DataSource {
	private static final String LOGTAG = "DB";
	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	public DataSource(Context context){
		dbhelper = new DBOpenHelper(context);
	}
	
	public void openDB(){
		Log.i(LOGTAG, "DB open");
		database = dbhelper.getWritableDatabase();
	}
	
	public void closeDB(){
		Log.i(LOGTAG, "DB close");
		dbhelper.close();
	}
	
	public List<Publicacion> obtenerPublicaciones(){
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		String query = "SELECT * FROM publicacion where estadopubl=?";
		String estado = "up";
		Cursor cursor = database.rawQuery(query, new String[]{estado});
		if (cursor.getCount() > 0){
			
			while(cursor.moveToNext()){
				Publicacion publ = new Publicacion();
				publ.setId(cursor.getLong(cursor.getColumnIndex("id_publicacion")));
				publ.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
				publ.setRaza(cursor.getString(cursor.getColumnIndex("raza")));
				publ.setGenero(cursor.getString(cursor.getColumnIndex("genero")));
				publ.setLugar(cursor.getString(cursor.getColumnIndex("lugar")));
				publ.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
				publ.setRecompensa(cursor.getString(cursor.getColumnIndex("recompensa")));
				publ.setDetalles(cursor.getString(cursor.getColumnIndex("detalles")));
				publ.setUsuariopubl(cursor.getString(cursor.getColumnIndex("usuariopubl")));
				publ.setFoto(cursor.getBlob(cursor.getColumnIndex("foto")));
				publ.setEstadopubl(cursor.getString(cursor.getColumnIndex("estadopubl")));
				publicaciones.add(publ);
				
			}
			
		}
		return publicaciones;
	}
	
	public List<Publicacion> obtenerPublicacionesdebuscar(String query, String busqueda1, String busqueda2, String busqueda3){
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		Cursor cursor = database.rawQuery(query, new String[]{busqueda1,busqueda2,busqueda3});
		if (cursor.getCount() > 0){		
			while(cursor.moveToNext()){
				Publicacion publ = new Publicacion();
				publ.setId(cursor.getLong(cursor.getColumnIndex("id_publicacion")));
				publ.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
				publ.setRaza(cursor.getString(cursor.getColumnIndex("raza")));
				publ.setGenero(cursor.getString(cursor.getColumnIndex("genero")));
				publ.setLugar(cursor.getString(cursor.getColumnIndex("lugar")));
				publ.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
				publ.setRecompensa(cursor.getString(cursor.getColumnIndex("recompensa")));
				publ.setDetalles(cursor.getString(cursor.getColumnIndex("detalles")));
				publ.setUsuariopubl(cursor.getString(cursor.getColumnIndex("usuariopubl")));
				publ.setEstadopubl(cursor.getString(cursor.getColumnIndex("estadopubl")));
				publ.setFoto(cursor.getBlob(cursor.getColumnIndex("foto")));
				publicaciones.add(publ);				
			}
		}
		return publicaciones;
	}
	
	
	public List<Publicacion> obtenerPublicacionesdebuscar2(String query, String busqueda1, String busqueda2){
		List<Publicacion> publicaciones = new ArrayList<Publicacion>();
		Cursor cursor = database.rawQuery(query, new String[]{busqueda1,busqueda2});
		if (cursor.getCount() > 0){		
			while(cursor.moveToNext()){
				Publicacion publ = new Publicacion();
				publ.setId(cursor.getLong(cursor.getColumnIndex("id_publicacion")));
				publ.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
				publ.setRaza(cursor.getString(cursor.getColumnIndex("raza")));
				publ.setGenero(cursor.getString(cursor.getColumnIndex("genero")));
				publ.setLugar(cursor.getString(cursor.getColumnIndex("lugar")));
				publ.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
				publ.setRecompensa(cursor.getString(cursor.getColumnIndex("recompensa")));
				publ.setDetalles(cursor.getString(cursor.getColumnIndex("detalles")));
				publ.setUsuariopubl(cursor.getString(cursor.getColumnIndex("usuariopubl")));
				publ.setEstadopubl(cursor.getString(cursor.getColumnIndex("estadopubl")));
				publ.setFoto(cursor.getBlob(cursor.getColumnIndex("foto")));
				publicaciones.add(publ);				
			}
		}
		return publicaciones;
	}
	
}
