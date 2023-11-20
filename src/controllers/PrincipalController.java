package controllers;

import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;

import aplication.Aplicacion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Proceso;
import model.Tarea;

public class PrincipalController {
	
	private Aplicacion aplicacion;

	ModelFactoryController modelFactoryController;

	private String usuarioAdmin;

	// ---------TAB PROCESO------------

	@FXML
	private TextField txtNombreProceso;

	@FXML
	private TextField txtIdProcesos;

	@FXML
	private TextField txtDescripcionProcesos;

	@FXML
	private TableView<Proceso> tableProcesos;

	@FXML
	private TableColumn<Proceso, String> columnProceso;

	@FXML
	private Button btnCrearProceso;

	@FXML
	private Label labelNombreP;

	@FXML
	private Label labelIdP;

	@FXML
	private Label labelDescripcionP;

	@FXML
	private Label labelDuracionMinP;

	@FXML
	private Label labelDuracionMaxP;

	@FXML
	private ImageView flechaRegresar;

	Proceso procesoSeleccionado;

	ObservableList<Proceso> listaProcesosData = FXCollections.observableArrayList();

	private ObservableList<Proceso> getListaProcesosData() {
		listaProcesosData.addAll(modelFactoryController.obtenerProcesos());
		return listaProcesosData;
	}

	@FXML
	void crearProcesoEvent(ActionEvent event) {
		crearProcesoAction();
	}

	@FXML
	void flechaRegresarEvent(MouseEvent event) {
		flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
			aplicacion.mostrarVentanaIniciar();
		});
	}

	private void crearProcesoAction() {
		String nombreP = txtNombreProceso.getText();
		String idP = txtIdProcesos.getText();
		String descripcionP = txtDescripcionProcesos.getText();

		if (datosValidosP(nombreP, idP, descripcionP)) {
			if (modelFactoryController.crearProceso(nombreP, idP, descripcionP)) {
				mostrarMensaje("Notificacion creación", "Proceso creado", "Se ha creado con exito el proceso",
						AlertType.INFORMATION);
				listaProcesosData.setAll(modelFactoryController.obtenerProcesos());
				listaProcesosAData.setAll(modelFactoryController.obtenerProcesosA());
				tableProcesos.refresh();
				txtNombreProceso.setText("");
				txtIdProcesos.setText("");
				txtDescripcionProcesos.setText("");
			} else {
				mostrarMensaje("Notificacion creación", "Proceso no creado",
						"Ya existe un proceso con el id " + idP + " No se puede crear", AlertType.INFORMATION);
				tableProcesos.refresh();
				txtNombreProceso.setText("");
				txtIdProcesos.setText("");
				txtDescripcionProcesos.setText("");
			}
		} else {
			mostrarMensaje("Notificación creación", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
	}

	private boolean datosValidosP(String nombre, String id, String descripcion) {
		if (nombre.equals("")) {
			return false;
		}
		if (id.equals("")) {
			return false;
		}
		if (descripcion.equals("")) {
			return false;
		}
		return true;

	}

	// -----------------------------

	// --------------TAB ACTIVIDADES--------------------------

	@FXML
	private ComboBox<String> comboBoxProcesosA;

	@FXML
	private ComboBox<String> comboBoxActividades;

	@FXML
	private TableView<String> tableActividades;

	@FXML
	private TableColumn<String, String> columnActividad;

	@FXML
	private TextField txtNombreA;

	@FXML
	private TextField txtDescripcionA;

	@FXML
	private RadioButton rBtnSi;

	@FXML
	private RadioButton rBtnNo;

	@FXML
	private Button btnCrearActividad;

	ObservableList<String> listaProcesosAData = FXCollections.observableArrayList();

	private ToggleGroup grupoOpciones = new ToggleGroup();

	private ObservableList<String> getListaProcesosAData() {
		listaProcesosAData.addAll(modelFactoryController.obtenerProcesosA());
		return listaProcesosAData;
	}

	ObservableList<String> listActividadesCBData = FXCollections.observableArrayList();

	private ObservableList<String> getListaActividadesCBData(String proceso) {
		// ArrayList<String> actividades = new ArrayList<String>();
		// actividades.addAll(modelFactoryController.obtenerActividadesCB(proceso));
		//
		listActividadesCBData.clear();
		listActividadesCBData.addAll(modelFactoryController.obtenerActividadesCB(proceso));

		return listActividadesCBData;
	}

	@FXML
	void crearActividadEvent(ActionEvent event) {
		crearActividadAction();
	}

	private void crearActividadAction() {
		String nombreA = txtNombreA.getText();
		String descripcion = txtDescripcionA.getText();
		String proceso = comboBoxProcesosA.getSelectionModel().getSelectedItem();
		String preceder = comboBoxActividades.getSelectionModel().getSelectedItem();
		RadioButton radioButtonSeleccionado = (RadioButton) grupoOpciones.getSelectedToggle();
		String seleccion = radioButtonSeleccionado.getText();

		if (datosValidosA(nombreA, descripcion, proceso, seleccion)) {
			if (preceder == null) {
				modelFactoryController.crearActividad(nombreA, descripcion, proceso, preceder, seleccion);
				mostrarMensaje("Notificacion creación", "Actividad creada", "Se ha creado con exito la actividad",
						AlertType.INFORMATION);
				limpiarCamposA();
				obtenerPosiblesActividades();
			} else {
				if (modelFactoryController.crearActividad(nombreA, descripcion, proceso, preceder, seleccion)) {
					mostrarMensaje("Notificacion creación", "Actividad creada", "Se ha creado con exito la actividad",
							AlertType.INFORMATION);

					limpiarCamposA();
					obtenerPosiblesActividades();

				} else {
					mostrarMensaje("Notificacion creación", "Actividad no creada",
							"Ya existe una actividad con el nombre " + nombreA + " No se puede crear",
							AlertType.INFORMATION);
					limpiarCamposA();
				}
			}
		} else {
			mostrarMensaje("Notificación creación", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
	}

	private void limpiarCamposA() {
		tableActividades.refresh();
		txtNombreA.setText("");
		txtDescripcionA.setText("");
		comboBoxProcesosA.setValue(null);
		comboBoxActividades.setValue(null);
		rBtnNo.setSelected(false);
		rBtnSi.setSelected(false);
	}

	private void filtrarActividades(ActionEvent event) {
		String proceso = comboBoxProcesosA.getSelectionModel().getSelectedItem();
		getListaActividadesCBData(proceso);
		comboBoxActividades.setItems(getListaActividadesCBData(proceso));
		tableActividades.getItems().clear();
		tableActividades.setItems(getListaActividadesCBData(proceso));
	}

	private boolean datosValidosA(String nombre, String descripcion, String proceso, String seleccion) {
		if (nombre.equals("")) {
			return false;
		}
		if (proceso.equals("")) {
			return false;
		}
		if (descripcion.equals("")) {
			return false;
		}
		if (seleccion.equals("")) {
			return false;
		}
		if (grupoOpciones.getSelectedToggle() == null) {
			return false;
		}
		return true;

	}

	// -----------------------------------

	// ---------TAP INTERCAMBIO-----------

	@FXML
	private TextField txtActividad1;

	@FXML
	private TextField txtActividad2;

	@FXML
	private Button btnIntercambiar;

	@FXML
	private Label labelNombre1;

	@FXML
	private Label labelDescripcion1;

	@FXML
	private Label labelDuracionMi1;

	@FXML
	private Label labelDuracionMa1;

	@FXML
	private Label labelNombre2;

	@FXML
	private Label labelDescripcion2;

	@FXML
	private Label labelDuracionMi2;

	@FXML
	private Label labelDuracionMa2;

	@FXML
	private TableView<Tarea> tableTareas1;

	@FXML
	private TableColumn<Tarea, String> columnTareas1;

	@FXML
	private TableView<Tarea> tableTareas2;

	@FXML
	private TableColumn<Tarea, String> columnTarea2;

	@FXML
	private Button btnBuscarActividades;
	
	ObservableList<Tarea> listaTareasData = FXCollections.observableArrayList();
	
	private ObservableList<Tarea> getListaTareasData(String actividad) {
		listaTareasData.clear();
		listaTareasData.addAll(modelFactoryController.obtenerTareas(actividad));

		return listaTareasData;
	}
	
	
	ObservableList<Tarea> listaTareas2Data = FXCollections.observableArrayList();
	
	private ObservableList<Tarea> getListaTareas2Data(String actividad) {
		listaTareas2Data.clear();
		listaTareas2Data.addAll(modelFactoryController.obtenerTareas(actividad));

		return listaTareas2Data;
	}
	


	@FXML
	void buscarActividadesEvent(ActionEvent event) {
		buscarAction();
	}
	
	private void buscarAction(){
		String actividad1 = txtActividad1.getText();
		String actividad2 = txtActividad2.getText();
		
		if(actividad1!= null && actividad2!= null){
			if(modelFactoryController.buscarActividades(actividad1, actividad2)){
				mostrarMensaje("Notificacion busqueda", "Actividades encontradas", "Las actividades sí de encuentran en el registro",
						AlertType.INFORMATION);
				mostrarInformacionActividades(actividad1, actividad2);
				tableTareas1.getItems().clear();
				tableTareas1.setItems(getListaTareasData(actividad1));
				tableTareas2.getItems().clear();
				tableTareas2.setItems(getListaTareas2Data(actividad2));
				
			}else{
				mostrarMensaje("Notificacion busqueda", "Actividades no encontradas", "Las actividades no se encuentran en el registro",
						AlertType.INFORMATION);
			}
		}else{
			mostrarMensaje("Notificación busqueda", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
	}

	private void mostrarInformacionActividades(String actividad1, String actividad2) {
		ArrayList<String> infoActividad1= new ArrayList<String>(3);
		ArrayList<String> infoActividad2= new ArrayList<String>(3);
		
		infoActividad1.addAll(modelFactoryController.traerInfoActividad(actividad1));
		infoActividad2.addAll(modelFactoryController.traerInfoActividad(actividad2));
		
		labelNombre1.setText(actividad1);
		labelDescripcion1.setText(infoActividad1.get(0));
		labelDuracionMi1.setText(infoActividad1.get(1)+" min");
		labelDuracionMa1.setText(infoActividad1.get(2)+" min");
		
		labelNombre2.setText(actividad2);
		labelDescripcion2.setText(infoActividad2.get(0));
		labelDuracionMi2.setText(infoActividad2.get(1)+" min");
		labelDuracionMa2.setText(infoActividad2.get(2)+" min");
	}

	@FXML
	void intercambiarEvent(ActionEvent event) {
		intercambiarAction();
	}
	
	private void intercambiarAction(){
		String actividad1 = txtActividad1.getText();
		String actividad2 = txtActividad2.getText();
		if(actividad1!= null && actividad2!= null){
			modelFactoryController.intercambiarActividades(actividad1, actividad2);
			tableTareas1.refresh();
			tableTareas1.getItems().clear();
			tableTareas1.setItems(getListaTareasData(actividad1));
			tableTareas2.refresh();
			tableTareas2.getItems().clear();
			tableTareas2.setItems(getListaTareas2Data(actividad2));
			if(modelFactoryController.intercambiarAtributos(actividad1, actividad2)){
				mostrarInformacionActividades(actividad2, actividad1);
				txtActividad1.setText(actividad2);
				txtActividad2.setText(actividad1);
				System.out.println("Se intercambio");
			}

		}else{
			mostrarMensaje("Notificación intercambio", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
	}
	
	// ------------------------------------------
	
	//-----------TAB BUSQUEDA--------------
	
	@FXML
    private TextField txtBuscarActividades;

    @FXML
    private Label labelNombreA;

    @FXML
    private Label labelDescripcionA;

    @FXML
    private Label labelDuracionMiA;

    @FXML
    private Label labelDuracionMaA;

    @FXML
    private Label labelObligatoriaA;

    @FXML
    private Label labelProcesoA;

    @FXML
    private TableView<Tarea> tableTareasA;

    @FXML
    private TableColumn<Tarea, String> columnTareasA;

    @FXML
    private Label labelNombreTareaA;

    @FXML
    private Label labelDescripcionTareaA;

    @FXML
    private Label labelDuracionMiTareaA;

    @FXML
    private Label labelDuracionMaTareaA;

    @FXML
    private Label labelObligatoriaTareaA;

    @FXML
    private Label labelProcesoTareaA;
    
    private Tarea tareaSeleccionada;
    
	@FXML
	void buscarEvent(KeyEvent event) {
		txtBuscarActividades.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
			if (event.getCode() == KeyCode.ENTER) {
				buscarActividadAction();
			}
		});

	}
	
	private void buscarActividadAction(){
		String actividad= txtBuscarActividades.getText();
		if(actividad!=null){
			mostrarInformacionActividad(actividad);
			tableTareasA.getItems().clear();
			tableTareasA.setItems(getListaTareasData(actividad));
		}
	}
	
	private void mostrarInformacionActividad(String actividad1) {
		ArrayList<String> infoActividad= new ArrayList<String>(6);
		
		infoActividad.addAll(modelFactoryController.traerInfoActividad2(actividad1));
		
		labelNombreA.setText(actividad1);
		labelDescripcionA.setText(infoActividad.get(1));
		labelDuracionMiA.setText(infoActividad.get(2)+" min");
		labelDuracionMaA.setText(infoActividad.get(3)+" min");
		labelObligatoriaA.setText(infoActividad.get(4));
		labelProcesoA.setText(infoActividad.get(0));

	}
    
	private void obtenerPosiblesActividades(){
		ArrayList<String> possibleWords= modelFactoryController.obtenerPosiblesActividades();

    	TextFields.bindAutoCompletion(txtBuscarActividades, possibleWords);

	}
    //--------------------------------------
    
    public void setAplicacion(Aplicacion aplicacion, String usuarioAdmin) {
		this.aplicacion = aplicacion;
		this.usuarioAdmin = usuarioAdmin;
		tableProcesos.getItems().clear();
		tableProcesos.setItems(getListaProcesosData());
		tableActividades.getItems().clear();
		obtenerPosiblesActividades();

	}

	@FXML
	void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		tableProcesos.setItems(getListaProcesosData());
		comboBoxProcesosA.setItems(getListaProcesosAData());
		comboBoxProcesosA.setOnAction(this::filtrarActividades);
		rBtnSi.setToggleGroup(grupoOpciones);
		rBtnNo.setToggleGroup(grupoOpciones);

		this.columnProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));

		tableProcesos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			procesoSeleccionado = newSelection;

			mostrarInformacionProceso(procesoSeleccionado);
		});

		columnActividad.setCellValueFactory(cellData -> {
			String actividad = cellData.getValue(); // Obtiene el valor de la
													// celda (en este caso, un
													// String)
			return new SimpleStringProperty(actividad); // Devuelve un
														// SimpleStringProperty
														// basado en el valor
														// obtenido
		});
		
		this.columnTareas1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnTarea2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnTareasA.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		tableTareasA.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			tareaSeleccionada = newSelection;

			mostrarInformacionTarea(tareaSeleccionada);
		});
	}
	
	private void mostrarInformacionTarea(Tarea tareaSeleccionada2) {
		labelNombreTareaA.setText(tareaSeleccionada2.getNombre());
		labelDescripcionTareaA.setText(tareaSeleccionada2.getDescripcion());
		labelProcesoTareaA.setText(labelProcesoA.getText());
		labelDuracionMaTareaA.setText(Integer.toString(tareaSeleccionada2.getDuracionMin()+10)+" min");
		labelDuracionMiTareaA.setText(Integer.toString(tareaSeleccionada2.getDuracionMin())+" min");
		if(tareaSeleccionada2.isObligatoria()==true){
			labelObligatoriaTareaA.setText("Si");
		}else{
			labelObligatoriaTareaA.setText("No");
		}
	}

	private void mostrarInformacionProceso(Proceso procesoSeleccionado2) {
		labelNombreP.setText(procesoSeleccionado2.getNombre());
		labelIdP.setText(procesoSeleccionado2.getId());
		labelDescripcionP.setText(procesoSeleccionado2.getDescripcion());

	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

}
