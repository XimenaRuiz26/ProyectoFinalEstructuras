package controllers;

import aplication.Aplicacion;
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
import javafx.scene.input.MouseEvent;
import model.Proceso;
import model.Tarea;

public class PrincipalPersonalController {
	
	private Aplicacion aplicacion;

	private String usuarioP;

	@FXML
	private TableView<Tarea> tableTareas;

	@FXML
	private TableColumn<Tarea, String> columnTarea;

	@FXML
	private Button btnCrearTarea;

	@FXML
	private Label labelDescripcionP1;

	@FXML
	private Label labelDuracionMinP1;

	@FXML
	private Label labelDuracionMaxP1;

	@FXML
	private ComboBox<String> comboBoxProceso;

	@FXML
	private ComboBox<String> comboBoxActividades;

	@FXML
	private TextField txtNombreTarea;

	@FXML
	private TextField txtDuracionMin;

	@FXML
	private TextField txtDescripcionTarea;

	@FXML
	private RadioButton rBtnSi;

	@FXML
	private RadioButton rBtnNo;

	private ToggleGroup grupoOpciones = new ToggleGroup();

	ObservableList<Proceso> listaProcesosData = FXCollections.observableArrayList();

	private ObservableList<Proceso> getListaProcesosData() {
		listaProcesosData.addAll(modelFactoryController.obtenerProcesos());
		return listaProcesosData;
	}

	ObservableList<String> listActividadesCBData = FXCollections.observableArrayList();

	private ObservableList<String> getListaActividadesCBData(String proceso) {
		listActividadesCBData.clear();
		listActividadesCBData.addAll(modelFactoryController.obtenerActividadesCB(proceso));

		return listActividadesCBData;
	}
	
	ObservableList<Tarea> listaTareasData = FXCollections.observableArrayList();

	private ObservableList<Tarea> getListaTareasData(String actividad) {
		listaTareasData.clear();
		listaTareasData.addAll(modelFactoryController.obtenerTareas(actividad));

		return listaTareasData;
	}

	private void filtrarActividades(ActionEvent event) {
		String proceso = comboBoxProceso.getSelectionModel().getSelectedItem();
		getListaActividadesCBData(proceso);
		comboBoxActividades.setItems(getListaActividadesCBData(proceso));
	}
	
	private void filtrarTareas(ActionEvent event){
		String actividad = comboBoxActividades.getSelectionModel().getSelectedItem();
		tableTareas.getItems().clear();
		tableTareas.setItems(getListaTareasData(actividad));
	}
	

	@FXML
	void crearTareaEvent(ActionEvent event) {
		crearTareaAction();
	}

	private void crearTareaAction() {

		String nombreP = txtNombreTarea.getText();
		String descripcion = txtDescripcionTarea.getText();
		String duracionMin = txtDuracionMin.getText();
		String proceso = comboBoxProceso.getSelectionModel().getSelectedItem();
		String actividad = comboBoxActividades.getSelectionModel().getSelectedItem();

		RadioButton radioButtonSeleccionado = (RadioButton) grupoOpciones.getSelectedToggle();
		String seleccion = radioButtonSeleccionado.getText();

		if (datosValidosTarea(nombreP, descripcion, proceso, actividad, duracionMin)) {
			if (modelFactoryController.crearTarea(nombreP, descripcion, proceso, actividad, seleccion, duracionMin)) {
				mostrarMensaje("Notificacion creación", "Tarea creada", "Se ha creado con exito la Tarea",
						AlertType.INFORMATION);
				limpiarCamposA();
				
			} else {
				mostrarMensaje("Notificacion creación", "Tarea no creada",
						"Ya existe una tarea con el nombre " + nombreP + " No se puede crear", AlertType.INFORMATION);
				limpiarCamposA();
			}
		} else {
			mostrarMensaje("Notificación creación", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
	}

	private void limpiarCamposA() {
		txtNombreTarea.setText("");
		txtDescripcionTarea.setText("");
		txtDuracionMin.setText("");
		comboBoxActividades.setValue(null);
		comboBoxProceso.setValue(null);
		rBtnNo.setSelected(false);
		rBtnSi.setSelected(false);
	}

	private boolean datosValidosTarea(String nombre, String descripcion, String proceso, String actividad, String duracionMin) {
		if (nombre.equals("")) {
			return false;
		}
		if (proceso.equals("")) {
			return false;
		}
		if (descripcion.equals("")) {
			return false;
		}
		if (actividad.equals("")) {
			return false;
		}
		if (duracionMin.equals("")) {
			return false;
		}
		if (grupoOpciones.getSelectedToggle() == null) {
			return false;
		}
		return true;

	}
	
	//---------------------------------------
	
	//------TAB MOSTRAR------------------

	@FXML
	private Label labelDescripcionP;

	@FXML
	private Label labelDuracionMinP;

	@FXML
	private Label labelDuracionMaxP;

	@FXML
	private Label labelNombreT;

	@FXML
	private Label labeObligatoriaT;

	@FXML
	private Label labelDescripcionT;

	@FXML
	private Label labelDuracionMinT;

	@FXML
	private Label labelDuracionMaxT;

	@FXML
	private ComboBox<String> comboBoxProcesosT;

	@FXML
	private ComboBox<String> comboBoxActividadesT;

	@FXML
	private TableView<Tarea> tableTareasMostrar;

	@FXML
	private TableColumn<Tarea, String> columnTareaMostrar;
	
	private Tarea tareaSeleccionada;
	
	private void filtrarActividadesT(ActionEvent event) {
		String proceso = comboBoxProcesosT.getSelectionModel().getSelectedItem();
		getListaActividadesCBData(proceso);
		comboBoxActividadesT.setItems(getListaActividadesCBData(proceso));
	}
	
	private void filtrarTareasT(ActionEvent event){
		String actividad = comboBoxActividadesT.getSelectionModel().getSelectedItem();
		tableTareasMostrar.getItems().clear();
		tableTareasMostrar.setItems(getListaTareasData(actividad));
	}
	
	//---------------------------------

	@FXML
	private ImageView flechaRegresar;

	ModelFactoryController modelFactoryController;

	@FXML
	void flechaRegresarEvent(MouseEvent event) {
		flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
			aplicacion.mostrarVentanaIniciar();
		});
	}

	public void setAplicacion(Aplicacion aplicacion, String usuarioP) {
		this.aplicacion = aplicacion;
		this.usuarioP = usuarioP;
	}

	@FXML
	void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		ObservableList<Proceso> listaProcesos = getListaProcesosData();
		ObservableList<String> nombresProcesos = FXCollections.observableArrayList();
		for (Proceso proceso : listaProcesos) {
		    nombresProcesos.add(proceso.getNombre());
		}
		comboBoxProceso.setItems(nombresProcesos);
		comboBoxProceso.setOnAction(this::filtrarActividades);
		comboBoxActividades.setOnAction(this::filtrarTareas);
		comboBoxProcesosT.setItems(nombresProcesos);
		comboBoxProcesosT.setOnAction(this::filtrarActividadesT);
		comboBoxActividadesT.setOnAction(this::filtrarTareasT);
		
		
		rBtnSi.setToggleGroup(grupoOpciones);
		rBtnNo.setToggleGroup(grupoOpciones);
		this.columnTarea.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnTareaMostrar.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tableTareasMostrar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

			tareaSeleccionada = newSelection;

			mostrarInformacionTarea(tareaSeleccionada);
		});
	}

	
	private void mostrarInformacionTarea(Tarea tareaSeleccionada2) {
		labelNombreT.setText(tareaSeleccionada2.getNombre());
		labelDescripcionT.setText(tareaSeleccionada2.getDescripcion());
		labelDuracionMaxT.setText(Integer.toString(tareaSeleccionada2.getDuracionMin()+10)+" min");
		labelDuracionMinT.setText(Integer.toString(tareaSeleccionada2.getDuracionMin())+" min");
		if(tareaSeleccionada2.isObligatoria()==true){
			labeObligatoriaT.setText("Si");
		}else{
			labeObligatoriaT.setText("No");
		}
	}

	
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

}
