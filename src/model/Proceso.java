package model;

import java.util.ArrayList;
import java.util.Iterator;

import structures.Cola;
import structures.Lista;
import structures.Nodo;

public class Proceso {
	private String id; 
	private String nombre; 
	private String descripcion; 
	private Lista<Actividad> actividades;
	
	public Proceso(String id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		actividades = new Lista<Actividad>();
		this.descripcion = descripcion;
		
	}
	
	public Proceso(){
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

	public Lista<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(Lista<Actividad> actividades) {
		this.actividades = actividades;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

//	public ArrayList<String> obtenerActividades() {
//		ArrayList<String> listaActividades = new ArrayList <String>();
//		Nodo<Actividad> actual = actividades.getNodoPrimero();
//		while (actual != null) {
//			Actividad actividad = (Actividad) actual.getValorNodo();
//			listaActividades.add(actividad.getNombre());
//			actual = actual.getSiguienteNodo();
//		}
//		return listaActividades;
//	}
	
	public ArrayList<String> obtenerActividades() {
		actividades.imprimirLista();
		ArrayList<String> listaActividades = new ArrayList<String>();
		if (actividades != null) {
			Nodo<Actividad> actual = actividades.getNodoPrimero();
			while (actual != null) {
				Actividad actividad = actual.getValorNodo();
				listaActividades.add(actividad.getNombre());
				actual = actual.getSiguienteNodo();
			}
		} else {
			System.out.println("La lista de actividades es null.");
		}
		return listaActividades;
	}

	public boolean crearActividad(String nombreA, String descripcion2, String preceder, String seleccion) {
		Actividad actividad = new Actividad();
		actividad.setNombre(nombreA);
		actividad.setDescripcion(descripcion2);
		actividad.setObligatoria(verificarObligatoria(seleccion));
		if (actividades == null) {
			actividades = new Lista<Actividad>();
		}
		if (verificarActividad(nombreA) == true) {
			return false;
		} else {
			insertarAntesDe(preceder, actividad);
			imprimirLista();
			return true;
		}
	}

	public void imprimirLista() {

		Nodo<Actividad> aux = actividades.getNodoPrimero();

		while (aux != null) {
			System.out.print(aux.getValorNodo().getNombre() + "\t");
			aux = aux.getSiguienteNodo();
		}

		System.out.println();
	}

	public void insertarAntesDe(String actividadExistente, Actividad nuevaActividad) {
		Nodo<Actividad> cabeza = actividades.getNodoPrimero();
		Nodo<Actividad> nuevo = new Nodo<>(nuevaActividad);

		if (cabeza == null) {
			actividades.setNodoPrimero(nuevo);
		} else if (cabeza.getValorNodo().getNombre().equals(actividadExistente)) {
			nuevo.setSiguienteNodo(cabeza);
			actividades.setNodoPrimero(nuevo);
		} else {
			Nodo<Actividad> previo = null;
			Nodo<Actividad> actual = cabeza;

			while (actual != null && !actual.getValorNodo().getNombre().equals(actividadExistente)) {
				previo = actual;
				actual = actual.getSiguienteNodo();
			}

			if (actual != null) {
				nuevo.setSiguienteNodo(actual);
				previo.setSiguienteNodo(nuevo);
			} else {
				System.out.println("La actividad " + actividadExistente + " no existe en la lista.");
			}
		}
	}

	private boolean verificarActividad(String nombreA) {
		if (nombreA == null || actividades.estaVacia()) {
			return false;
		} else {
			Iterator<Actividad> iterator = actividades.iterator();
			while (iterator.hasNext()) {
				Actividad aux = iterator.next();
				if (aux.getNombre().equals(nombreA)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean verificarObligatoria(String seleccion) {
		if (seleccion.equals("Si")) {
			return true;
		} else if (seleccion.equals("No")) {
			return false;
		}
		return false;
	}

	public boolean buscarActividades(String actividad1, String actividad2) {
		Nodo<Actividad> actual = actividades.getNodoPrimero();

		while (actual != null) {
			Actividad actividad = (Actividad) actual.getValorNodo();
			if (actividad.getNombre().equals(actividad1) || actividad.getNombre().equals(actividad2)) {
				return true;
			}
			actual = actual.getSiguienteNodo();
		}

		return false;
	}

	public ArrayList<String> traerInfoActividad(String actividad2) {
		ArrayList<String> info = new ArrayList<String>(3);
		Nodo<Actividad> actual = actividades.getNodoPrimero();
		while (actual != null) {
			Actividad actividad = (Actividad) actual.getValorNodo();
			if (actividad.getNombre().equals(actividad2)) {
				info.add(actividad.getDescripcion());
				info.add(actividad.calcularMin());
				info.add(actividad.calcularMax());
			}
			actual = actual.getSiguienteNodo();
		}

		return info;
	}

	public ArrayList<Tarea> traerTareas(String actividad2) {
		ArrayList<Tarea> listaTareas = new ArrayList<Tarea>();
		Nodo<Actividad> actual = actividades.getNodoPrimero();
		while (actual != null) {
			Actividad actividad = (Actividad) actual.getValorNodo();
			if (actividad.getNombre().equals(actividad2)) {
				return actividad.traerTareas();
			}
			actual = actual.getSiguienteNodo();
		}

		return listaTareas;
	}

	public void intercambiarActividades(String nombreActividad1, String nombreActividad2) {
		Nodo<Actividad> actual = actividades.getNodoPrimero();
		Actividad actividad1 = null;
		Actividad actividad2 = null;

		while (actual != null && (actividad1 == null || actividad2 == null)) {
			Actividad actividad = actual.getValorNodo();
			if (actividad.getNombre().equals(nombreActividad1)) {
				actividad1 = actividad;
			} else if (actividad.getNombre().equals(nombreActividad2)) {
				actividad2 = actividad;
			}
			actual = actual.getSiguienteNodo();
		}

		if (actividad1 != null && actividad2 != null) {

			Cola<Tarea> tempTareas = actividad1.getTareas();
			actividad1.setTareas(actividad2.getTareas());
			actividad2.setTareas(tempTareas);
		}
	}

	public boolean intercambiarAtributis(String actividad11, String actividad22) {
		actividades.imprimirLista();
		Nodo<Actividad> actual = actividades.getNodoPrimero();
		Actividad actividad1 = null;
		Actividad actividad2 = null;

		while (actual != null && (actividad1 == null || actividad2 == null)) {
			Actividad actividad = actual.getValorNodo();
			if (actividad.getNombre().equals(actividad11)) {
				actividad1 = actividad;
			} else if (actividad.getNombre().equals(actividad22)) {
				actividad2 = actividad;
			}
			actual = actual.getSiguienteNodo();
		}
		if (actividad1 != null && actividad2 != null) {
			String tempNombre = actividad1.getNombre();
			String tempDescripcion = actividad1.getDescripcion();
			boolean tempObligatoria = actividad1.isObligatoria();
			actividad1.setNombre(actividad2.getNombre());
			actividad1.setDescripcion(actividad2.getDescripcion());
			actividad1.setObligatoria(actividad2.isObligatoria());
			actividad2.setNombre(tempNombre);
			actividad2.setDescripcion(tempDescripcion);
			actividad2.setObligatoria(tempObligatoria);
			actividades.imprimirLista();
			return true;
		}
		return false;

	}

	public ArrayList<String> traerActividades() {
	    ArrayList<String> nombresActividades = new ArrayList<String>();
	    Nodo<Actividad> actual = actividades.getNodoPrimero();

	    while (actual != null) {
	        Actividad actividad = actual.getValorNodo();
	        if (actividad != null) {
	            nombresActividades.add(actividad.getNombre());
	        }
	        actual = actual.getSiguienteNodo();
	    }

	    return nombresActividades;
	}

	public ArrayList<String> traerInfoActividad2(String actividad1) {
		ArrayList<String> info = new ArrayList<String>(4);
		Nodo<Actividad> actual = actividades.getNodoPrimero();
		while (actual != null) {
			Actividad actividad = (Actividad) actual.getValorNodo();
			if (actividad.getNombre().equals(actividad1)) {
				info.add(actividad.getDescripcion());
				info.add(actividad.calcularMin());
				info.add(actividad.calcularMax());
				if(actividad.isObligatoria()==true){
					info.add("Si");
				}else{
					info.add("No");
				}
			}
			actual = actual.getSiguienteNodo();
		}

		return info;
	}

}
