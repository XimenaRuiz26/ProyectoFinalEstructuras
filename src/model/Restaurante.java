package model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import structures.Lista;
import structures.Nodo;

public class Restaurante {
	
	private String nombre; 
	private String direccion; 
	private Lista<Proceso> listaProcesos;
	private Admin admin;
	private Set<Personal> listaPersonal;
	
	public Restaurante(String nombre, String direccion) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		listaProcesos = new Lista<Proceso>();
		admin= new Admin();
		listaPersonal = new HashSet<Personal>();
	}
	
	public Restaurante(){
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Lista<Proceso> getListaProcesos() {
		return listaProcesos;
	}

	public void setListaProcesos(Lista<Proceso> listaProcesos) {
		this.listaProcesos = listaProcesos;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public Set<Personal> getListaPersonal() {
		return listaPersonal;
	}

	public void setListaPersonal(Set<Personal> listaPersonal) {
		this.listaPersonal = listaPersonal;
	}

	public boolean verificarAdmin(String usuarioAdmin, String contraseniaAdmin) {
		if(admin.getUsuario().equals(usuarioAdmin)&& admin.getContrasenia().equals(contraseniaAdmin)){
			return true;
		}
		return false;
	}
	
	public boolean verificarPersonal(String usuario, String contrasenia) {
		Iterator<Personal> iterator = listaPersonal.iterator();
        while (iterator.hasNext()) {
            Personal aux = iterator.next();
            if (aux.getUsuario().equals(usuario) && aux.getContrasenia().equals(contrasenia)) {
                return true;
            }
        }
		return false;
	}

	public Boolean crearPersonal(String nombre2, String apellido, String documento, String direccion2,
			String contrasenia, String email, String usuario, String cargo) {
		Personal personal = new Personal();
		personal.setNombre(nombre2);
		personal.setApellido(apellido);
		personal.setId(documento);
		personal.setDireccion(direccion);
		personal.setContrasenia(contrasenia);
		personal.setEmail(email);
		personal.setUsuario(usuario);
		personal.setCargo(verificarCargo(cargo));

		if(verificarPersonal(email)== true){
			return false;
		}else{
			listaPersonal.add(personal);
			return true;
		}
	}
	
	public boolean verificarPersonal(String email) {
		Iterator<Personal> iterator = listaPersonal.iterator();
        while (iterator.hasNext()) {
            Personal aux = iterator.next();
            if (aux.getEmail().equals(email)) {
                return true;
            }
        }
		return false;
	}

	private Cargo verificarCargo(String cargo) {
		if(cargo.equals("CAMARERO")){
			return Cargo.CAMARERO;
		}
		else if(cargo.equals("COCINERO")){
			return Cargo.COCINERO;
		}
		else if(cargo.equals("CAJERO")){
			return Cargo.CAJERO;
		}
		return null;
	}

	public String traerNombre(String correo) {
		String nombre= "";
		Iterator<Personal> iterator = listaPersonal.iterator();
        while (iterator.hasNext()) {
            Personal aux = iterator.next();
            if (aux.getEmail().equals(correo)) {
                nombre= aux.getNombre();
            }
        }
		return nombre;
	}

	public boolean crearProceso(String nombreP, String idP, String descripcionP) {
		Proceso proceso = new Proceso();
		proceso.setNombre(nombreP);
		proceso.setId(idP);
		proceso.setDescripcion(descripcionP);
		
		if(verificarProceso(idP)== true){
			return false;
		}else{
			listaProcesos.agregarInicio(proceso);
			return true;
		}
	}

	private boolean verificarProceso(String idP) {
	    Nodo<Proceso> actual = listaProcesos.getNodoPrimero();
	    while (actual != null) {
	        Proceso proceso = actual.getValorNodo(); 
	        String id = proceso.getId();

	        if (id.equals(idP)) {
	            return true;
	        }
	        actual = actual.getSiguienteNodo();
	    }
	    return false;
	}

	public ArrayList<Proceso> obtenerProcesos() {
		ArrayList<Proceso> listaProcesos2 = new ArrayList <Proceso>();
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
			listaProcesos2.add(proceso);
			actual = actual.getSiguienteNodo();
		}

		return listaProcesos2;
	}

	public ArrayList<String> obtenerProcesosA() {
		ArrayList<String> listaProcesos2 = new ArrayList <String>();
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
			listaProcesos2.add(proceso.getNombre());
			actual = actual.getSiguienteNodo();
		}

		return listaProcesos2;
	}

	public ArrayList<String> obtenerActividadesCB(String proceso2) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
			if(proceso.getNombre().equals(proceso2)){
				return proceso.obtenerActividades();
			}
			actual = actual.getSiguienteNodo();
		}

		return null;
	}

	public boolean crearActividad(String nombreA, String descripcion, String proceso2, String preceder, String seleccion) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
			if(proceso.getNombre().equals(proceso2)){
				return proceso.crearActividad(nombreA, descripcion, preceder, seleccion);
			}
			actual = actual.getSiguienteNodo();
		}

		return false;
	}

	public boolean buscarActividades(String actividad1, String actividad2) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
				if(proceso.buscarActividades(actividad1, actividad2)){
					return true;
				}
				actual = actual.getSiguienteNodo();
			}
		return false;
	}

	public ArrayList<String> traerInfoActividad(String actividad) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
				if(proceso!=null){
					return proceso.traerInfoActividad(actividad);
				}
				actual = actual.getSiguienteNodo();
			}
		return null;
	}

	public ArrayList<Tarea> obtenerTareas(String actividad) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
				if(proceso!=null){
					return proceso.traerTareas(actividad);
				}
				actual = actual.getSiguienteNodo();
			}
		return null;
	}

	public void intercambiarActividades(String actividad1, String actividad2) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
				if(proceso!=null){
					proceso.intercambiarActividades(actividad1, actividad2);
				}
				actual = actual.getSiguienteNodo();
			}
	}

	public boolean intercambiarAtributos(String actividad1, String actividad2) {
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
				if(proceso!=null){
					return proceso.intercambiarAtributis(actividad1, actividad2);
				}
				actual = actual.getSiguienteNodo();
			}
		return false;
	}

	public ArrayList<String> obtenerPosiblesActividades() {
	    ArrayList<String> listaActividades = new ArrayList<String>();
	    Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

	    while (actual != null) {
	        Proceso proceso = actual.getValorNodo();
	        if (proceso != null) {
	            listaActividades.addAll(proceso.traerActividades());
	        }
	        actual = actual.getSiguienteNodo();
	    }
	    return listaActividades;
	}

	public ArrayList<String> traerInfoActividad2(String actividad1) {
		 ArrayList<String> info = new ArrayList<String>(5);
		Nodo<Proceso> actual = listaProcesos.getNodoPrimero();

		while (actual != null) {
			Proceso proceso = (Proceso) actual.getValorNodo();
				if(proceso!=null){
					info.add(proceso.getNombre());
					info.addAll(proceso.traerInfoActividad2(actividad1));
				}
				actual = actual.getSiguienteNodo();
			}
		return info;
	}

}
