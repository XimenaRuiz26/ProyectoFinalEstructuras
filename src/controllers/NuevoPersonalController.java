package controllers;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import aplication.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class NuevoPersonalController {
	
	private Aplicacion aplicacion;

	ModelFactoryController modelFactoryController;
	
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private ComboBox<String> comboBoxCargos;

    @FXML
    private Button btnRegistrarse;

    @FXML
    void RegistrarseEvent(ActionEvent event) {
    	try {
			registrarseAction();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public void setAplicacion(Aplicacion aplicacion) {
		this.aplicacion = aplicacion;

	}

	private void registrarseAction() throws IOException {
		String nombre = txtNombre.getText();
		String apellido = txtApellido.getText();
		String documento = txtDocumento.getText();
		String direccion = txtDireccion.getText();
		String usuario = txtUsuario.getText();
		String contrasenia = txtContrasenia.getText();
		String email = txtEmail.getText();
		String cargo = comboBoxCargos.getSelectionModel().getSelectedItem();

		if (datosValidos(nombre, apellido, documento, direccion, contrasenia, email, usuario) == true) {
			if (modelFactoryController.crearPersonal(nombre, apellido, documento, direccion, contrasenia, email,
					usuario, cargo)) {
				mostrarMensaje("Notificacion registro", "Personal registrado",
						"¡Se ha registrado con exito en Sr.Don Platano, bienvenido al equipo!", AlertType.INFORMATION);
				enviarCorreo(email);
				aplicacion.mostrarVentanaLoginP();
			} else {

				mostrarMensaje("Notificación registro", "Personal no registrado",
						"Ya se ha registrado un personal con el correo: " + email + ".", AlertType.ERROR);
			}
		} else {
			mostrarMensaje("Notificación registro", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
	}

	private void enviarCorreo(String destinatario) {
		String nombre = modelFactoryController.traerNombre(txtEmail.getText());
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Cambia esto al
															// servidor SMTP que
															// desees utilizar
		properties.put("mail.smtp.port", "587"); // Cambia esto al puerto SMTP
													// adecuado
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true"); // Habilita
																// STARTTLS para
																// la seguridad

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sr.donplatano7@gmail.com", "fonx zidg vnvo zewr");
			}
		});

		try {
			// Crear un objeto de mensaje
			Message mensaje = new MimeMessage(session);

			// Configurar el remitente y los destinatarios
			mensaje.setFrom(new InternetAddress(destinatario));
			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			mensaje.setSubject("Registro exitoso - Sr. Don Platano");
			mensaje.setText("¡Felicidades!" +nombre+ "\n"
					+ "Te has registrado con exito en Sr. Don Platano, ahora puedes disfrutar y deleitarte con nuestras deliciosas preparaciones");

			// Enviar el mensaje
			Transport.send(mensaje);

			System.out.println("Correo electrónico enviado con éxito.");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
		}
	}

	private boolean datosValidos(String nombre, String apellido, String documento, String direccion, String contrasenia,
			String email, String usuario) {
		if (nombre.equals("")) {
			return false;
		}
		if (apellido.equals("")) {
			return false;
		}
		if (documento.equals("")) {
			return false;
		}
		if (direccion.equals("")) {
			return false;
		}
		if (contrasenia.equals("")) {
			return false;
		}
		if (email.equals("")) {
			return false;
		}
		if (usuario.equals("")) {
			return false;
		}

		return true;

	}

	@FXML
	void initialize() {

		modelFactoryController = ModelFactoryController.getInstance();
		
		comboBoxCargos.getItems().addAll("COCINERO", "CAJERO", "MESERO");
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

}
