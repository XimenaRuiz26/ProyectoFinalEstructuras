package model;

public class Personal {
	private String nombre;
	private String apellido; 
	private String id; 
	private String direccion; 
	private String email;
	private String usuario; 
	private String contrasenia; 
	private Cargo cargo;
	
	public Personal(String nombre, String apellido, String id, String direccion, String email, String usuario,
			String contrasenia, Cargo cargo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.direccion = direccion;
		this.email = email;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.cargo = cargo;
	}
	
	public Personal(){
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
	

}
