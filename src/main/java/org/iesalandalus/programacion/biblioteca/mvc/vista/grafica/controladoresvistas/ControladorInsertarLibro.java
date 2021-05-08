package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladoresvistas;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.AudioLibro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.LibroEscrito;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class ControladorInsertarLibro {

	private static final String ER_OBLIGATORIO = ".+";
	private static final String ER_NUMPAGINASDURACION = "\\d+";

	private IControlador controladorMVC;
	private ObservableList<Libro> libros;
	
    @FXML private TextField tfTitulo;
    @FXML private TextField tfAutor;
    @FXML private RadioButton rbLibroEscrito;
    @FXML private TextField tfNumeroPaginas;
    @FXML private RadioButton rbAudioLibro;
    @FXML private TextField tfDuracion;
    @FXML private Button bAceptar;
    @FXML private Button bCancelar;
    
	private ToggleGroup tgLEscritoAudio = new ToggleGroup();

	@FXML
	private void initialize() {

		tfTitulo.textProperty().addListener((ob, va, vn) -> compruebaCampoTexto(ER_OBLIGATORIO, tfTitulo));
		tfAutor.textProperty().addListener((ob, va, vn) -> compruebaCampoTexto(ER_OBLIGATORIO, tfAutor));
		rbLibroEscrito.setToggleGroup(tgLEscritoAudio);
		rbAudioLibro.setToggleGroup(tgLEscritoAudio);
		tgLEscritoAudio.selectedToggleProperty().addListener((ob, va, vn) -> habilitaSeleccion());
		tfNumeroPaginas.setDisable(true);
		tfDuracion.setDisable(true);
	}

	public void setControladorMVC(IControlador controladorMVC) {

		this.controladorMVC = controladorMVC;
	}

	public void setLibros(ObservableList<Libro> libros) {

		this.libros = libros;
	}

	private Libro getLibro() throws OperationNotSupportedException {

		int numPaginas;
		int duracion;

		String titulo = tfTitulo.getText();
		String autor = tfAutor.getText();

		RadioButton seleccion = (RadioButton) tgLEscritoAudio.getSelectedToggle();

		Libro libro = null;

		if (seleccion == rbLibroEscrito) {

			try {

				numPaginas = Integer.parseInt(tfNumeroPaginas.getText());

			} catch (NumberFormatException e) {

				throw new OperationNotSupportedException("El número de páginas debe ser entero.");
			}

			libro = new LibroEscrito(titulo, autor, numPaginas);

		} else if (seleccion == rbAudioLibro) {

			try {

				duracion = Integer.parseInt(tfDuracion.getText());

			} catch (NumberFormatException e) {

				throw new OperationNotSupportedException("El número de duración debe ser entero.");
			}

			libro = new AudioLibro(titulo, autor, duracion);
		}

		return libro;
	}

	@FXML
	void aceptar(ActionEvent event) {

		Libro libro = null;
		Stage ventana = null;

		try {

			libro = getLibro();
			controladorMVC.insertar(libro);
			libros.setAll(controladorMVC.getLibros());
			ventana = ((Stage) bAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Libro", "Libro insertado Correctamente", ventana);

			inicializa();
			ventana.close();

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("Añadir Libro", e.getMessage());
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

	private void habilitaSeleccion() {

		RadioButton seleccion = (RadioButton) tgLEscritoAudio.getSelectedToggle();

		if (seleccion == rbLibroEscrito) {

			tfNumeroPaginas.setDisable(false);
			tfDuracion.setDisable(true);

		} else if (seleccion == rbAudioLibro) {

			tfNumeroPaginas.setDisable(true);
			tfDuracion.setDisable(false);
		}
	}

	public void inicializa() {

		tfTitulo.setText("");
		tfAutor.setText("");
		rbLibroEscrito.setSelected(false);
		rbAudioLibro.setSelected(false);
		tfNumeroPaginas.setText("");
		tfDuracion.setText("");
	}
}
