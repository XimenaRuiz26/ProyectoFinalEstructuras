package model;

public class Admin {
	private String id; 
	private String nombre; 
	private String usuario; 
	private String contrasenia;
	
	public Admin(String id, String nombre, String usuario, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	} 
	
	public Admin(){
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	

}
