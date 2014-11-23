package com.example.socialbop;

public class Usuario {
	private int id;
	private String nombres;
	private String apellidos;
	private int genero;
	private String telefono;
	private String correo;
	private int edad;
	private int id_login_fk;
	
	public int getId_login_fk() {
		return id_login_fk;
	}
	public void setId_login_fk(int id_login_fk) {
		this.id_login_fk = id_login_fk;
	}
	
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
