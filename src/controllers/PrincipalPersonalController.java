package controllers;

import aplication.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Proceso;

public class PrincipalPersonalController {
	
	private Aplicacion aplicacion;
	
	private String usuarioP;
	
	@FXML
    private TableView<?> tableTareas;

    @FXML
    private TableColumn<?, ?> columnTarea;

    @FXML
    private Button btnCrearTarea;

    @FXML
    private Label labelDescripcionP1;

    @FXML
    private Label labelDuracionMinP1;

    @FXML
    private Label labelDuracionMaxP1;

    @FXML
    private ComboBox<Proceso> comboBoxProceso;

    @FXML
    private ComboBox<?> comboBoxActividades;

    @FXML
    private TextField txtNombreTarea;

    @FXML
    private TextField txtDuracionMin;

    @FXML
    private TextField txtDescripcionTarea;

    @FXML
    private TextField txtDuracionMax;

	@FXML
	private RadioButton rBtnSi;

	@FXML
	private RadioButton rBtnNo;
	
	ObservableList<Proceso> listaProcesosData = FXCollections.observableArrayList();

	private ObservableList<Proceso> getListaProcesosData() {
		listaProcesosData.addAll(modelFactoryController.obtenerProcesos());
		return listaProcesosData;
	}
	
	@FXML
	void crearTareaEvent(ActionEvent event) {
		
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
	private ComboBox<?> comboBoxProcesosT;

	@FXML
	private ComboBox<?> comboBoxActividadesT;

	@FXML
	private TableView<?> tableTareasMostrar;

	@FXML
	private TableColumn<?, ?> columnTareaMostrar;

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
		comboBoxProceso.setItems(getListaProcesosData());

	}

}
