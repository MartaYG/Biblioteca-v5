package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladoresvistas;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorInsertarAlumno {

	private IControlador controladorMVC;
	private ObservableList<Alumno> alumnos;

	private static final String ER_NOMBRE = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([\\s]+[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)+$";
	private static final String ER_CORREO = "^.+[@][a-zA-Z]+\\.[a-zA-Z]+$";
	
    @FXML private TextField tfNombre;
    @FXML private TextField tfCorreo;
    @FXML private ComboBox<Curso> cbCurso;
    @FXML private Button bAceptar;
    @FXML private Button bCancelar;

	@FXML
	private void initialize() {

		cbCurso.setItems(FXCollections.observableArrayList(Curso.values()));
		tfNombre.textProperty().addListener((ob, va, vn) -> compruebaCampoTexto(ER_NOMBRE, tfNombre));
		tfCorreo.textProperty().addListener((ob, va, vn) -> compruebaCampoTexto(ER_CORREO, tfCorreo));
	}

	public void setControladorMVC(IControlador controladorMVC) {

		this.controladorMVC = controladorMVC;
	}

	public void setAlumnos(ObservableList<Alumno> alumnos) {

		this.alumnos = alumnos;
	}

	private Alumno getAlumno() {

		String nombre = tfNombre.getText();
		String correo = tfCorreo.getText();
		Curso curso = cbCurso.getValue();

		return new Alumno(nombre, correo, curso);
	}

	@FXML
	void aceptar(ActionEvent event) {

		Alumno alumno = null;
		Stage ventana = null;

		try {

			alumno = getAlumno();
			controladorMVC.insertar(alumno);
			alumnos.setAll(controladorMVC.getAlumnos());
			ventana = ((Stage) bAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Alumno", "Alumno insertado Correctamente", ventana);

			inicializa();
			ventana.close();

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("Añadir Alumno", e.getMessage());
		}
	}

	@FXML
	void cancelar(ActionEvent event) {

		inicializa();
		((Stage) bCancelar.getScene().getWindow()).close();
	}

	private void compruebaCampoTexto(String er, TextField campoTexto) {

		String texto = campoTexto.getText();

		if (texto.matches(er)) {

			campoTexto.setStyle("-fx-border-color: green; -fx-border-radius: 3;");
		}

		else {

			campoTexto.setStyle("-fx-border-color: red; -fx-border-radius: 3;");
		}
	}

	public void inicializa() {

		tfNombre.setText("");
		compruebaCampoTexto(ER_NOMBRE, tfNombre);
		tfCorreo.setText("");
		compruebaCampoTexto(ER_CORREO, tfCorreo);
		cbCurso.getSelectionModel().clearSelection();
	}
}
