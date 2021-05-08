package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladoresvistas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorRealizarPrestamo {
	
	private IControlador controladorMVC;
	private ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();
	private ObservableList<Alumno> alumnos = FXCollections.observableArrayList();
	private ObservableList<Libro> libros = FXCollections.observableArrayList();

    @FXML private ListView<Alumno> lvAlumno;
    @FXML private ListView<Libro> lvLibro;
    @FXML private DatePicker dpFechaPrestamo;
    @FXML private Button bAceptar;
    @FXML private Button bCancelar;
    
	private class CeldaAlumno extends ListCell<Alumno> {

		@Override
		public void updateItem(Alumno alumno, boolean vacia) {

			super.updateItem(alumno, vacia);
			
			if (vacia) {
				
				setText("");
				
			} else {

				setText(alumno.getCorreo());
			}
		}
	}

	private class CeldaLibro extends ListCell<Libro> {

		@Override
		public void updateItem(Libro libro, boolean vacia) {

			super.updateItem(libro, vacia);
			
			if (vacia) {
				
				setText("");
				
			} else {

				setText(libro.getTitulo() + ", " + libro.getAutor());
			}
		}
	}

	@FXML
	private void initialize() {

		lvAlumno.setItems(alumnos);
		lvAlumno.setCellFactory(lv -> new CeldaAlumno());
		lvLibro.setItems(libros);
		lvLibro.setCellFactory(lv -> new CeldaLibro());
	}

	public void setControladorMVC(IControlador controladorMVC) {

		this.controladorMVC = controladorMVC;
	}

	public void setAlumnos(ObservableList<Alumno> alumnos) {

		this.alumnos.setAll(alumnos);
	}

	public void setLibros(ObservableList<Libro> libros) {

		this.libros.setAll(libros);
	}

	public void setPrestamos(ObservableList<Prestamo> prestamos) {

		this.prestamos.setAll(prestamos);
	}

	private Prestamo getPrestamo() {

		Alumno alumno = lvAlumno.getSelectionModel().getSelectedItem();
		Libro libro = lvLibro.getSelectionModel().getSelectedItem();
		LocalDate fechaPrestamo = dpFechaPrestamo.getValue();

		return new Prestamo(alumno, libro, fechaPrestamo);
	}

	@FXML
	void aceptar(ActionEvent event) {

		Prestamo prestamo = null;
		Stage ventana = null;

		try {

			prestamo = getPrestamo();
			controladorMVC.prestar(prestamo);
			prestamos.setAll(controladorMVC.getPrestamos());
			ventana = ((Stage) bAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Añadir Préstamo", "Préstamo realizado Correctamente", ventana);

			inicializa();
			ventana.close();

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("Realizar Préstamo", e.getMessage());
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
    	
    	lvAlumno.getSelectionModel().clearSelection();
    	lvLibro.getSelectionModel().clearSelection();
    	dpFechaPrestamo.setValue(null);
    }
    
    public void seleccionarAlumno(Alumno alumno) {
    
    	lvAlumno.getSelectionModel().select(alumno);
    }
    
  public void seleccionarLibro(Libro libro) {
	  
	  lvLibro.getSelectionModel().select(libro);
  }
}
