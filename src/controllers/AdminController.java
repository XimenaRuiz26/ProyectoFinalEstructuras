package controllers;

import aplication.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AdminController {
	
	private Aplicacion aplicacion;
	
	@FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private Button btnEntrar;
    
    @FXML
    private ImageView flechaRegresarEvent;
	
    
    ModelFactoryController modelFactoryController;

    @FXML
    void entrarEvent(ActionEvent event) {
    	ingresarAction();
    }
    
    @FXML
    void flechaRegresarEvent(MouseEvent event) {
	 flechaRegresarEvent.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciar();
    	});
    }


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}
	
	private void ingresarAction() {

    	String usuarioAdmin = "";
    	String contraseniaAdmin = "";

    	usuarioAdmin = txtUsuario.getText();
    	contraseniaAdmin = txtContrasenia.getText();

    	if(datosValidos(usuarioAdmin,contraseniaAdmin)){
    		boolean documentoValido = modelFactoryController.verificarAdmin(usuarioAdmin,contraseniaAdmin);
    		if(documentoValido){
    			aplicacion.mostrarVentanaPrincipal(usuarioAdmin);
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
