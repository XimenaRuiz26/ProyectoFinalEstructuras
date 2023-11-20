package controllers;
import aplication.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicioController {
	
    @FXML
    private Button btnAdmin;
    
	private Aplicacion aplicacion;
	
	@FXML
    private Button btnPersonal;


	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

	@FXML
	void entrarAdminEvent(ActionEvent event) {
		aplicacion.mostrarVentanaLoginA();
	}

	
	@FXML
    void personalEvent(ActionEvent event) {
		aplicacion.mostrarVentanaLoginP();
                       
    }
}
