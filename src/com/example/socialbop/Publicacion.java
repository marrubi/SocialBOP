package com.example.socialbop;

public class Publicacion {
	long id;
	String nombre;
	String raza;
    String genero;
	String lugar;
	String fecha;
	String recompensa;
	String detalles;
	String usuariopubl;
	String estadopubl;
	byte[] foto;
	
	public String getEstadopubl() {
		return estadopubl;
	}
	public void setEstadopubl(String estadopubl) {
		this.estadopubl = estadopubl;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public String getUsuariopubl() {
		return usuariopubl;
	}
	public void setUsuariopubl(String usuariopubl) {
		this.usuariopubl = usuariopubl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRecompensa() {
		return recompensa;
	}
	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
}
