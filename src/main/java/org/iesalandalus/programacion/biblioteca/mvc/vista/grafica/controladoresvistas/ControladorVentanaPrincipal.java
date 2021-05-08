package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladoresvistas;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.AudioLibro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.LibroEscrito;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorVentanaPrincipal {

	private IControlador controladorMVC;

	@FXML private MenuItem bSalir;
	@FXML private MenuItem bAcercaDe;

	@FXML private TableView<Alumno> tvAlumnos;
    @FXML private TableColumn<Alumno, String> tcANombre;
    @FXML private TableColumn<Alumno, String> tcACorreo;
    @FXML private TableColumn<Alumno, String> tcACurso;

    @FXML private TableView<Prestamo> tvPrestamoAlumno;
    @FXML private TableColumn<Prestamo, String> tcPaLibro;
    @FXML private TableColumn<Prestamo, String> tcPaFechaPrestamo;
    @FXML private TableColumn<Prestamo, String> tcPaFechaDevolucion;
    @FXML private TableColumn<Prestamo, String> tcPaPuntos;

    @FXML private TableView<Libro> tvLibros;
    @FXML private TableColumn<Libro, String> tcLTitulo;
    @FXML private TableColumn<Libro, String> tcLAutor;
    @FXML private TableColumn<Libro, String> tcLNumPaginasDuracion;

    @FXML private TableView<Prestamo> tvLibrosPrestados;
    @FXML private TableColumn<Prestamo, String> tcLpAlumno;
    @FXML private TableColumn<Prestamo, String> tcLpFechaPrestamo;
    @FXML private TableColumn<Prestamo, String> tcLpFechaDevolucion;
    @FXML private TableColumn<Prestamo, String> tcLpPuntos;

    @FXML private TableView<Prestamo> tvPrestamos;
    @FXML private TableColumn<Prestamo, String> tcPAlumno;
    @FXML private TableColumn<Prestamo, String> tcPLibro;
    @FXML private TableColumn<Prestamo, String> tcPFechaPrestamo;
    @FXML private TableColumn<Prestamo, String> tcPFechaDevolucion;
    @FXML private TableColumn<Prestamo, String> tcPPuntos;

	public void setControladorMVC(IControlador controladorMVC) {

		this.controladorMVC = controladorMVC;
	}
	
	
	@FXML
	private void acercaDe(ActionEvent event) throws IOException {

		VBox contenido = FXMLLoader.load(LocalizadorRecursos.class.getResource("vistas/AcercaDe.fxml"));
		Dialogos.mostrarDialogoInformacionPersonalizado("Biblioteca Yebra", contenido);
	}

	@FXML
	private void salir(ActionEvent event) {

		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que desea salir de la aplicación?", null)) {

			controladorMVC.terminar();
			System.exit(0);
		}
	}

}
