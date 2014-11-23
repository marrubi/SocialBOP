package com.example.socialbop.db;

import com.example.socialbop.Publicacion;
import com.example.socialbop.Usuario;
import com.example.socialbop.Login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	public static final String DB_NOMBRE = "BOP.db";
	public static final int DB_VERSION = 19;
	public static final String DB_TABLA = "usuario";
	public static final String DB_TABLA2 = "publicacion";
	
	public DBOpenHelper(Context context) {
		super(context, DB_NOMBRE, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	public boolean nuevologin(String nombres, String apellidos, int genero, String telefono, String correo, String user, String pass){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		contentValues.put("nombres", nombres);
		contentValues.put("apellidos", apellidos);
		contentValues.put("genero", genero);
		contentValues.put("telefono", telefono);
		contentValues.put("correo", correo);

		db.insert("usuario", null, contentValues);
		return true;
		   
	}
	
	public long getIDLogin(Login log){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT id_login FROM login WHERE usuario=? AND contrasena=?", new String[]{log.getUsuario(),log.getContrasena()});
		return mCursor.getColumnIndex("id_login");
	}
	//Esta es la funcion que retorna la contraseña, pero el metodo
	//getColumnIndex retorna el indice del campo contrasena y no la contraseña que quiero
	public long getPassMail(String mail){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT usuario,contrasena FROM login,usuario WHERE usuario.id_login_fk = login.id_login and correo = ?", new String[]{mail});
		return mCursor.getColumnIndex("contrasena");
	}
	
	
	public void insertarUsuario(Usuario usuario, long id){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		contentValues.put("nombres", usuario.getNombres());
		contentValues.put("apellidos", usuario.getApellidos());
		contentValues.put("genero", usuario.getGenero());
		contentValues.put("telefono", usuario.getTelefono());
		contentValues.put("correo", usuario.getCorreo());
		contentValues.put("id_login_fk", id);
		db.insert("usuario", null, contentValues);
		
	}
	
	public void insertarLogin(Login log){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		contentValues.put("usuario", log.getUsuario());
		contentValues.put("contrasena", log.getContrasena());
		db.insert("login", null, contentValues);
	}
	
	public void insertarPublicacion(Publicacion publ){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		
		contentValues.put("nombre", publ.getNombre());
		contentValues.put("raza", publ.getRaza());
		contentValues.put("genero", publ.getGenero());
		contentValues.put("lugar", publ.getLugar());
		contentValues.put("fecha", publ.getFecha());
		contentValues.put("recompensa", publ.getRecompensa());
		contentValues.put("detalles", publ.getDetalles());
		contentValues.put("foto", publ.getFoto());
		contentValues.put("usuariopubl", publ.getUsuariopubl());
		contentValues.put("estadopubl", publ.getEstadopubl());
		db.insert("publicacion", null, contentValues);
	}
	
	public void actualizarPublicacion(Publicacion publ,long id){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		System.out.println(id + publ.getId() + publ.getNombre()+ publ.getRaza()+
				publ.getGenero()+publ.getLugar()+publ.getFecha()+publ.getRecompensa()+publ.getDetalles()+
				publ.getUsuariopubl()+publ.getEstadopubl());;
		
		contentValues.put("nombre", publ.getNombre());
		contentValues.put("raza", publ.getRaza());
		contentValues.put("genero", publ.getGenero());
		contentValues.put("lugar", publ.getLugar());
		contentValues.put("fecha", publ.getFecha());
		contentValues.put("recompensa", publ.getRecompensa());
		contentValues.put("detalles", publ.getDetalles());
		contentValues.put("usuariopubl", publ.getUsuariopubl());
		contentValues.put("estadopubl", "down");
		contentValues.put("foto",publ.getFoto());
		
		db.update("publicacion", contentValues, "id_publicacion =?", new String[]{String.valueOf(id)});
		db.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE usuario("
				+ "id_usuario INTEGER PRIMARY KEY autoincrement, "
				+ "nombres TEXT, "
				+ "apellidos TEXT, "
				+ "edad INTEGER, "
				+ "genero TEXT, "
				+ "telefono TEXT, "
				+ "correo TEXT, "
				+ "id_login_fk INTEGER)");
		
		db.execSQL("CREATE TABLE publicacion("
				+ "id_publicacion INTEGER PRIMARY KEY autoincrement, "
				+ "nombre TEXT, "
				+ "raza TEXT, "
				+ "genero TEXT, "
				+ "lugar TEXT, "
				+ "fecha TEXT, "
				+ "recompensa TEXT, "
				+ "usuariopubl TEXT, "
				+ "detalles TEXT,"
				+ "estadopubl TEXT, "
				+ "foto BLOB)");
		
		db.execSQL("CREATE TABLE login("
				+ "id_login INTEGER PRIMARY KEY autoincrement, "
				+ "usuario TEXT NOT NULL UNIQUE, "
				+ "contrasena TEXT NOT NULL UNIQUE)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS usuario");
		db.execSQL("DROP TABLE IF EXISTS publicacion");
		db.execSQL("DROP TABLE IF EXISTS login");
	    onCreate(db);
	}

	
	public boolean Login(String username, String password) throws SQLException{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor mCursor = db.rawQuery("SELECT * FROM login WHERE usuario=? AND contrasena=?", new String[]{username,password});
		if (mCursor != null) {
			if(mCursor.getCount() > 0)
			{
				return true;
			}
		}
		return false;
	}
	
}
