package controllers;

import aplication.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class PersonalController {
	
	private Aplicacion aplicacion;
	
	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtContrasenia;

	@FXML
	private Button btnEntrar;

	@FXML
	private Label registrateAqui;
	
	@FXML
    private ImageView flechaRegresarEvent;
	
	ModelFactoryController modelFactoryController;
	
	 @FXML
	    void flechaRegresarEvent(MouseEvent event) {
		 flechaRegresarEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
	    		aplicacion.mostrarVentanaIniciar();
	    	});
	    }


	@FXML
	void entrarEvent(ActionEvent event) {
		ingresarAction();
	}

	@FXML
	void registrateEvent(MouseEvent event) {
		registrateAqui.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaNuevoPersonal();
    	});
	}

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}
	
	private void ingresarAction() {

    	String usuario = "";
    	String contrasenia = "";

    	usuario = txtUsuario.getText();
    	contrasenia = txtContrasenia.getText();

    	if(datosValidos(usuario,contrasenia)){

    		boolean documentoValido = modelFactoryController.verificarPersonal(usuario,contrasenia);
    		if(documentoValido){
    			aplicacion.mostrarVentanaPrincipalPersonal(usuario);
    		}else{
    			mostrarMensaje("Notificacion Inicio sesion", "Usuario no existe", "Los datos ingresados no corresponde a un usuario valido", AlertType.INFORMATION);

    		}
    	}else{
			mostrarMensaje("Notificacion Inicio sesion", "Datos Incompletos", "Debe ingresar los datos correctamente, despues de 3 intentos se bloqueara el usuario", AlertType.WARNING);

    	}

	}
	
	private boolean datosValidos(String usuario, String contrasenia) {
		if(usuario.equals("") || contrasenia.equals("")){
			return false;
		}
		return true;
	}
	
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

    @FXML
    void initialize() {
    	modelFactoryController = ModelFactoryController.getInstance();

    }

}
