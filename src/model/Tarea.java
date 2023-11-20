package model;

public class Tarea {
	
	private String nombre; 
	private String descripcion; 
	private boolean obligatoria; 
	private int duracionMin;
	
	
	public Tarea(String nombre, String descripcion, boolean obligatoria, int duracionMin) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.obligatoria = obligatoria;
		this.duracionMin = duracionMin;
	}
	
	public Tarea(){
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}
	public int getDuracionMin() {
		return duracionMin;
	}
	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	} 
	
	

}
