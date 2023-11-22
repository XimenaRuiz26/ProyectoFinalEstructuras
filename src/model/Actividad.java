package model;

import java.util.ArrayList;

import structures.Cola;
import structures.Nodo;

public class Actividad {
	private String nombre; 
	private String descripcion; 
	private boolean obligatoria; 
	private Cola<Tarea> tareas;
	
	public Actividad(String nombre, String descripcion, boolean obligatoria) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.obligatoria = obligatoria;
		tareas= new Cola<Tarea>();
	}
	
	public Actividad(){
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

	public Cola<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Cola<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	public String calcularMin() {
	    int tiempoMinimo = 0;
	    String tiempoMin = "";

	    // Verificar si la cola de tareas no está inicializada (es null)
	    if (tareas == null) {
	        tiempoMin = "0"; // Si la cola no está inicializada, se establece tiempoMin a "0"
	    } else {
	        // Verificar si la cola de tareas no está vacía
	        if (!tareas.estaVacia()) {
	            Nodo<Tarea> actual = tareas.getPrimero(); // Obtener el primer nodo de la cola
	            
	            while (actual != null) {
	                tiempoMinimo += actual.getValorNodo().getDuracionMin();
	                actual = actual.getSiguienteNodo(); // Avanzar al siguiente nodo
	            }
	            tiempoMin = Integer.toString(tiempoMinimo);
	        } else {
	            tiempoMin = "0"; // Si la cola está vacía, el tiempo mínimo es cero
	        }
	    }
	    
	    return tiempoMin;
	}
	
	public String calcularMax() {
	    int tiempoMinimo = 0;
	    String tiempoMin = "";

	    // Verificar si la cola de tareas no está inicializada (es null)
	    if (tareas == null) {
	        tiempoMin = "0"; // Si la cola no está inicializada, se establece tiempoMin a "0"
	    } else {
	        // Verificar si la cola de tareas no está vacía
	        if (!tareas.estaVacia()) {
	            Nodo<Tarea> actual = tareas.getPrimero(); // Obtener el primer nodo de la cola
	            
	            while (actual != null) {
	                tiempoMinimo += actual.getValorNodo().getDuracionMin()+10;
	                actual = actual.getSiguienteNodo(); // Avanzar al siguiente nodo
	            }
	            tiempoMin = Integer.toString(tiempoMinimo);
	        } else {
	            tiempoMin = "0"; // Si la cola está vacía, el tiempo mínimo es cero
	        }
	    }
	    
	    return tiempoMin;
	}

	public ArrayList<Tarea> traerTareas() {
		ArrayList<Tarea> listaTareas = new ArrayList<Tarea>();
		if (!tareas.estaVacia()) {
	        Nodo<Tarea> actual = tareas.getPrimero(); 
	        while (actual != null) {
	            listaTareas.add(actual.getValorNodo());
	            actual = actual.getSiguienteNodo(); 
	        }
	    }else{
	    	System.out.println("No hay tareas");
	    }
	    return listaTareas;
	}

	public boolean crearTarea(String nombreP, String descripcion2, String seleccion, String duracionMin) {
		Tarea tarea = new Tarea();
		tarea.setNombre(nombreP);
		tarea.setDescripcion(descripcion2);
		tarea.setDuracionMin(Integer.parseInt(duracionMin));
		tarea.setObligatoria(verificarObligatoria(seleccion));
		if (tareas == null) {
			tareas = new Cola<Tarea>();
		}
		if (verificarTarea(nombreP) == true) {
			return false;
		} else {
			tareas.encolar(tarea);
			tareas.imprimir();
			return true;
		}
	}
	
	private boolean verificarTarea(String nombreA) {
	    if (nombreA == null || tareas.estaVacia()) {
	        return false;
	    }

	    Nodo<Tarea> actual = tareas.getPrimero();
	    while (actual != null) {
	        Tarea tarea = actual.getValorNodo();
	        if (tarea != null && tarea.getNombre().equals(nombreA)) {
	            return true;
	        }
	        actual = actual.getSiguienteNodo();
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

	public boolean crearTareaPosicion(String nombreP, String descripcion2, String seleccion, String duracionMin,
			String posicion) {
		Tarea tarea = new Tarea();
		tarea.setNombre(nombreP);
		tarea.setDescripcion(descripcion2);
		tarea.setDuracionMin(Integer.parseInt(duracionMin));
		tarea.setObligatoria(verificarObligatoria(seleccion));
		if (tareas == null) {
			tareas = new Cola<Tarea>();
		}
		if (verificarTarea(nombreP) == true) {
			return false;
		} else {
			tareas.agregarEnPosicion(tarea, Integer.parseInt(posicion));
			tareas.imprimir();
			return true;
		}
	}
	
	
	
	
}
