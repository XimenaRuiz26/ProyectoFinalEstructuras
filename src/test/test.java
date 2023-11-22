package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Actividad;
import model.Personal;
import model.Proceso;
import model.Restaurante;

public class test {
	private Restaurante restaurante;

	@Before
	public void setUp() {
		restaurante = new Restaurante("Mi Restaurante", "Calle Principal");
	}

	@Test
	public void testVerificarAdmin() {
		restaurante.getAdmin().setUsuario("admin");
		restaurante.getAdmin().setContrasenia("admin123");
		assertTrue(restaurante.verificarAdmin("admin", "admin123"));
		assertFalse(restaurante.verificarAdmin("admin", "wrongpass"));
	}

	@Test
	public void testCrearPersonal() {
		assertTrue(restaurante.crearPersonal("Juan", "Perez", "12345", "Calle 123", "pass123", "juan@example.com",
				"juanito", "CAMARERO"));
		assertFalse(restaurante.crearPersonal("Juan", "Perez", "12345", "Calle 123", "pass123", "juan@example.com",
				"juanito", "CAMARERO"));
	}

	@Test
	public void testVerificarPersonal() {
		Personal personal = new Personal();
		personal.setEmail("test@example.com");
		restaurante.getListaPersonal().add(personal);
		assertTrue(restaurante.verificarPersonal("test@example.com"));
		assertFalse(restaurante.verificarPersonal("nonexistent@example.com"));
	}

	@Test
	public void testTraerNombre() {
		Personal personal = new Personal();
		personal.setEmail("test@example.com");
		personal.setNombre("Test");
		restaurante.getListaPersonal().add(personal);
		assertEquals("Test", restaurante.traerNombre("test@example.com"));
	}


	@Test
	public void testCrearProceso() {

		String nombreP = "ProcesoPrueba";
		String idP = "IDProcesoPrueba";
		String descripcionP = "Descripción del proceso prueba";

		boolean resultado = restaurante.crearProceso(nombreP, idP, descripcionP);
		Assert.assertTrue(resultado);

		assertEquals(1, restaurante.getListaProcesos().getTamanio());
	}

	@Test
	public void testVerificarProceso() {
		String idP = "IDProcesoPrueba";
		Proceso proceso = new Proceso();
		proceso.setId(idP);

		Assert.assertFalse(restaurante.verificarProceso(idP));
		Assert.assertTrue(restaurante.crearProceso(idP, idP, "Descripción del proceso prueba"));
		Assert.assertTrue(restaurante.verificarProceso(idP));
	}

	@Test
	public void testObtenerProcesos() {

		Proceso proceso1 = new Proceso();
		proceso1.setId("IDProceso1");
		Proceso proceso2 = new Proceso();
		proceso2.setId("IDProceso2");

		restaurante.crearProceso("Proceso1", "IDProceso1", "Descripción del proceso 1");
		restaurante.crearProceso("Proceso2", "IDProceso2", "Descripción del proceso 2");

		ArrayList<Proceso> listaProcesos = restaurante.obtenerProcesos();
		Assert.assertEquals(2, listaProcesos.size());
		Assert.assertTrue(listaProcesos.contains(proceso1));
		Assert.assertTrue(listaProcesos.contains(proceso2));
	}

	@Test
	public void testObtenerProcesosA() {
		Proceso proceso1 = new Proceso();
		proceso1.setId("IDProceso1");
		Proceso proceso2 = new Proceso();
		proceso2.setId("IDProceso2");

		restaurante.crearProceso("Proceso1", "IDProceso1", "Descripción del proceso 1");
		restaurante.crearProceso("Proceso2", "IDProceso2", "Descripción del proceso 2");

		ArrayList<String> listaProcesosA = restaurante.obtenerProcesosA();
		Assert.assertEquals(2, listaProcesosA.size());
		Assert.assertTrue(listaProcesosA.contains("Proceso1"));
		Assert.assertTrue(listaProcesosA.contains("Proceso2"));
	}
	
	@Test
	public void testCrearActividad() {
	    Proceso proceso = new Proceso("NombreProceso", "IDProceso", "DescripcionProceso");
	    boolean resultadoEsperado = true;
	    boolean resultadoObtenido = restaurante.crearActividad("NombreActividad", "DescripcionActividad", proceso.getNombre(), "Precedente", "Seleccion");
	    Assert.assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void testBuscarActividades() {
	    Actividad actividad = new Actividad("NombreActividad", "DescripcionActividad", true);
	    boolean resultadoEsperado = true;
	    boolean resultadoObtenido = restaurante.buscarActividades(actividad.getNombre(), actividad.getDescripcion());
	    Assert.assertEquals(resultadoEsperado, resultadoObtenido);
	}
	
	 @Test
	    public void testIntercambiarActividades() {
		 Proceso proceso = new Proceso("NombreProceso", "IDProceso", "DescripcionProceso");
	        proceso.crearActividad("Actividad 1", "Descripción 1", null, null);
	        proceso.crearActividad("Actividad 2", "Descripción 2", null, null);
	        proceso.intercambiarActividades("Actividad 1", "Actividad 2");
	        assertEquals("Actividad 2", proceso.getActividades().getNodoPrimero().getValorNodo().getNombre());
	    }

}
