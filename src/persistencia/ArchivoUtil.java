package persistencia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Actividad;
import model.Proceso;
import model.Tarea;
import structures.Cola;
import structures.Lista;
import structures.ListaDoble;

public class ArchivoUtil {
	public static final String RUTA_IMPORTACION = "src/importacion/Datos.xlsx";
	public static void exportarArchivoExcel(Lista<Proceso> procesos) throws IOException {
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("DATOS");

	    // Define header cells
	    CellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerCellStyle.setFont(headerFont);

	    // Create header row
	    Row headerRow = sheet.createRow(0);
	    Cell processHeaderCell = headerRow.createCell(0);
	    processHeaderCell.setCellValue("Procesos");
	    processHeaderCell.setCellStyle(headerCellStyle);
	    Cell activityHeaderCell = headerRow.createCell(1);
	    activityHeaderCell.setCellValue("Actividades");
	    activityHeaderCell.setCellStyle(headerCellStyle);
	    Cell taskHeaderCell = headerRow.createCell(2);
	    taskHeaderCell.setCellValue("Tareas");
	    taskHeaderCell.setCellStyle(headerCellStyle);
	    
	    int rowCount = 1;
	    for (Proceso proceso : procesos) {
	        Row row = sheet.createRow(rowCount++);
	        row.createCell(0).setCellValue(proceso.getNombre());

	        for (Actividad actividad : proceso.getActividades()) {
	            row = sheet.createRow(rowCount++);
	            row.createCell(1).setCellValue(actividad.getNombre());

	            for (Tarea tarea : actividad.traerTareas()) {
	                row = sheet.createRow(rowCount++);
	                row.createCell(2).setCellValue(tarea.getNombre());
	            }
	        }
	    }
	    
	    for (int i = 0; i < 3; i++) {
        sheet.autoSizeColumn(i);
	    }
	    
	    FileOutputStream fileOut = new FileOutputStream("Exportacion de datos.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();
	    workbook.close();
	}
	
	public static Lista<Proceso> leerProcesosArchivoExcel() throws IOException{
		FileInputStream file = new FileInputStream(new File(RUTA_IMPORTACION));

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(1);

        Lista<Proceso> procesos = new Lista<Proceso>();
        ListaDoble<Actividad> actividades = new ListaDoble<Actividad>();
        Cola<Tarea> cola = new Cola<Tarea>();
        Proceso procesoActual = null;
        Actividad actividadActual = null;
        for (Row row : sheet) {
            String id = row.getCell(1).getStringCellValue();
            String nombre = row.getCell(2).getStringCellValue();
            String descripcion = row.getCell(3).getStringCellValue();
            String nombreA = row.getCell(5).getStringCellValue();
            String descripcionA = row.getCell(6).getStringCellValue();
            String obligatoriaA = row.getCell(7).getStringCellValue();
            String nombreT = row.getCell(9).getStringCellValue();
            String descripcionT = row.getCell(10).getStringCellValue();
            String obligatoriaT = row.getCell(11).getStringCellValue();
            String duracionT = row.getCell(12).getStringCellValue();
            boolean obliA= false;
            if(obligatoriaA.equals("Si")){
            	obliA=true;
            }
            boolean obliT= false;
            if(obligatoriaT.equals("Si")){
            	obliT=true;
            }

            if (procesoActual == null || !procesoActual.getNombre().equals(nombre)) {
                procesoActual = new Proceso(id, nombre, descripcion);
                if (actividadActual == null || !actividadActual.getNombre().equals(nombreA)) {
                	actividadActual = new Actividad(nombreA, descripcionA,obliA );
                	actividades.agregarInicio(actividadActual);
                	Tarea tarea = new Tarea(nombreT, descripcionT, obliT, Integer.parseInt(duracionT));
                	cola.encolar(tarea);
                	actividadActual.setTareas(cola);
                	procesoActual.setActividades(actividades);
                }
                procesos.agregarInicio(procesoActual);
            }
        }
        file.close();
        workbook.close();
		return procesos;
    
	}

}
