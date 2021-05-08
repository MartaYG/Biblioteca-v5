package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladoresvistas;

import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControladorRealizarDevolucion {

	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private IControlador controladorMVC;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;
	private Prestamo prestamo;

    @FXML  private Label lAlumno;
    @FXML private Label lLibro;
    @FXML private Label lFechaPrestamo;
    @FXML private DatePicker dpFechaDevolucion;
    @FXML private Button bAceptar;
    @FXML private Button bCancelar;
    
	public void setControladorMVC(IControlador controladorMVC) {

		this.controladorMVC = controladorMVC;
	}

	public void setPrestamo(Prestamo prestamo) {

		this.prestamo = prestamo;
	}

	public void setControladorVentanaPrincipal(ControladorVentanaPrincipal controladorVentanaPrincipal) {

		this.controladorVentanaPrincipal = controladorVentanaPrincipal;
	}

	@FXML
	void aceptar(ActionEvent event) {

		Stage ventana = null;

		try {
			
			controladorMVC.devolver(prestamo, dpFechaDevolucion.getValue());
			controladorVentanaPrincipal.actualizarPrestamos();

			ventana = ((Stage) bAceptar.getScene().getWindow());
			Dialogos.mostrarDialogoInformacion("Realizar Devolución", "Devolución realizada Correctamente", ventana);

			inicializa();
			ventana.close();

		} catch (Exception e) {

			Dialogos.mostrarDialogoError("Realizar Devolución", e.getMessage());
		}
	}

	@FXML
	void cancelar(ActionEvent event) {

		inicializa();
		((Stage) bCancelar.getScene().getWindow()).close();
	}

	public void inicializa() {

		lAlumno.setText(prestamo.getAlumno().getCorreo());
		lLibro.setText(prestamo.getLibro().getTitulo() + ", " + prestamo.getLibro().getAutor());
		lFechaPrestamo.setText(FORMATO_FECHA.format(prestamo.getFechaPrestamo()));
		dpFechaDevolucion.setValue(null);
	}
}
