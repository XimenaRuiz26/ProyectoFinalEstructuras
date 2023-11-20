package controllers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.Actividad;
import model.Admin;
import model.Cargo;
import model.Personal;
import model.Proceso;
import model.Restaurante;
import model.Tarea;
import structures.Cola;
import structures.Lista;
public class ModelFactoryController {
	Restaurante restaurante;
	

	//------------------------------  Singleton ------------------------------------------------
	// Clase estatica oculta. Tan solo se instanciara el singleton una vez
	private static class SingletonHolder {
		// El constructor de Singleton puede ser llamado desde aquï¿½ al ser protected
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	// Mï¿½todo para obtener la instancia de nuestra clase
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	public ModelFactoryController() {
		//1. inicializar datos y luego guardarlo en archivos
				//iniciarSalvarDatosPrueba();

				//2. Cargar los datos de los archivos
				//cargarDatosDesdeArchivos();
		
				//3. Guardar y Cargar el recurso serializable binario
				//guardarResourceBinario();
				//cargarResourceBinario();

				//4. Guardar y Cargar el recurso serializable XML

//				try {
//					guardarResourceXML();
//					guardarResourceBinario();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		
				// Siempre se debe verificar si la raiz del recurso es null
				if (restaurante== null) {
					System.out.println("es null el restaurante");
					iniciarSalvarDatosPrueba();
					// crearCopias();
					// guardarResourceSerializable();
//					guardarResourceXML();
//					guardarResourceBinario();
		
				}
	}
	
	
    private void iniciarSalvarDatosPrueba() {
		inicializarDatos();
//		try {
//
//			Persistencia.guardarVendedores(getRed().getlistaClientes());
//			Persistencia.guardarAdministrador(getRed().getAdministrador());
//			Persistencia.guardarProductos(getRed().getlistaClientes());
//			
//			Persistencia.guardarRecursoBancoBinario(red);
//			Persistencia.guardarRecursoBancoXML(red);
//
//			//Persistencia.cargarDatosArchivos(getBanco());
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("Se inicializaron los datos");
	}
    
    private void inicializarDatos() {
    	
    	restaurante= new Restaurante("Sr. Don Platano", "Calarcá");
    	
    	Set <Personal> listaPersonal= new HashSet<Personal>();
    	
    	Admin admin= new Admin();
    	admin.setNombre("Pepe");
    	admin.setId("105465465");
    	admin.setUsuario("pepito12");
    	admin.setContrasenia("12345");
    	
    	Personal personal = new Personal();
    	personal.setNombre("Edd");
    	personal.setApellido("Gutierrez");
    	personal.setId("5345465454");
    	personal.setDireccion("cra 30");
    	personal.setEmail("eddg@gmail.com");
    	personal.setUsuario("eddg");
    	personal.setContrasenia("0000");
    	personal.setCargo(Cargo.CAMARERO);
    	
    	Lista <Proceso> listaProcesos = new Lista<Proceso>();
    	
    	Proceso proceso = new Proceso();
    	proceso.setNombre("Gestion pedidos");
    	proceso.setId("1111");
    	proceso.setDescripcion("gestionar los pedidos");
    	
    	Lista <Actividad> listaActividades = new Lista<Actividad>();
    	
    	Cola<Tarea> listaTareas1 = new Cola<Tarea>();
    	
    	Tarea tarea = new Tarea();
    	tarea.setNombre("servicio de entrega");
    	tarea.setDescripcion("Coordinar con el servicio de entrega");
    	tarea.setObligatoria(true);
    	tarea.setDuracionMin(20);
    	
    	Tarea tarea2 = new Tarea();
    	tarea2.setNombre("Detalles seguimientos");
    	tarea2.setDescripcion("Propocionar al cliente detalles acerca del envio");
    	tarea2.setObligatoria(true);
    	tarea2.setDuracionMin(30);
    	
    	listaTareas1.encolar(tarea);
    	listaTareas1.encolar(tarea2);
    	
    	Cola<Tarea> listaTareas2 = new Cola<Tarea>();
    	
    	Tarea tarea3 = new Tarea();
    	tarea3.setNombre("Supervisar");
    	tarea3.setDescripcion("Supervisar el tiempo de preparacion");
    	tarea3.setObligatoria(true);
    	tarea3.setDuracionMin(30);
    	
    	Tarea tarea4 = new Tarea();
    	tarea4.setNombre("Calidad de orden");
    	tarea4.setDescripcion("Verificar la calidad antes de entregar");
    	tarea4.setObligatoria(true);
    	tarea4.setDuracionMin(20);
    	
    	listaTareas2.encolar(tarea3);
    	listaTareas2.encolar(tarea4);
    	
    	Actividad actividad = new Actividad();
    	actividad.setNombre("Entrega al cliente");
    	actividad.setDescripcion("Entregar al cliente el pedido");
    	actividad.setObligatoria(true);
    	actividad.setTareas(listaTareas1);
    	
    	Actividad actividad2 = new Actividad();
    	actividad2.setNombre("Tiempo de preparacion");
    	actividad2.setDescripcion("Verificar el tiempo de preparación del pedido");
    	actividad2.setObligatoria(true);
    	actividad2.setTareas(listaTareas2);
    	
    	listaActividades.agregarInicio(actividad);
    	listaActividades.agregarInicio(actividad2);
    	
    	proceso.setActividades(listaActividades);
    	
    	listaProcesos.agregarInicio(proceso);
    	
    	listaPersonal.add(personal);
    	restaurante.setAdmin(admin);
    	restaurante.setListaProcesos(listaProcesos);
    	restaurante.setListaPersonal(listaPersonal);
    	
    	

    }
	
	public Restaurante getRed() {
		return restaurante;
	}

	public void setRed(Restaurante restaurante) {
		this.restaurante= restaurante;
	}

	public boolean verificarAdmin(String usuarioAdmin, String contraseniaAdmin) {
		return restaurante.verificarAdmin(usuarioAdmin, contraseniaAdmin);
	}

	public boolean verificarPersonal(String usuario, String contrasenia) {
		return restaurante.verificarPersonal(usuario, contrasenia);
	}

	public boolean crearPersonal(String nombre, String apellido, String documento, String direccion,
			String contrasenia, String email, String usuario, String cargo)  {
		return restaurante.crearPersonal(nombre, apellido, documento, direccion, contrasenia, email, usuario, cargo);
	}
	
	public String traerNombre(String correo){
		return restaurante.traerNombre(correo);
	}

	public boolean crearProceso(String nombreP, String idP, String descripcionP) {
		return restaurante.crearProceso(nombreP, idP, descripcionP);
	}

	public ArrayList<Proceso> obtenerProcesos() {
		return restaurante.obtenerProcesos();
	}

	public ArrayList<String> obtenerProcesosA() {
		return restaurante.obtenerProcesosA();
	}

	public ArrayList<String> obtenerActividadesCB(String proceso) {
		return restaurante.obtenerActividadesCB(proceso);
	}

	public boolean crearActividad(String nombreA, String descripcion, String proceso, String preceder,
			String seleccion) {
		return restaurante.crearActividad(nombreA, descripcion, proceso, preceder, seleccion);
	}

	public boolean buscarActividades(String actividad1, String actividad2) {
		return restaurante.buscarActividades(actividad1, actividad2);
	}

	public ArrayList<String> traerInfoActividad(String actividad) {
		return restaurante.traerInfoActividad(actividad);
	}

	public ArrayList<Tarea> obtenerTareas(String actividad) {
		return restaurante.obtenerTareas(actividad);
	}

	public void intercambiarActividades(String actividad1, String actividad2) {
		restaurante.intercambiarActividades(actividad1, actividad2);
	}

	public boolean intercambiarAtributos(String actividad1, String actividad2) {
		return restaurante.intercambiarAtributos(actividad1, actividad2);
	}

	public ArrayList<String> obtenerPosiblesActividades() {
		return restaurante.obtenerPosiblesActividades();
	}

	public ArrayList<String>traerInfoActividad2(String actividad1) {
		return restaurante.traerInfoActividad2(actividad1);
	}

}
