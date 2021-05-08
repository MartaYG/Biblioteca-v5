package org.iesalandalus.programacion.biblioteca.mvc.vista.grafica;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.IVista;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.controladoresvistas.ControladorVentanaPrincipal;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.biblioteca.mvc.vista.texto.Consola;
import org.iesalandalus.programacion.biblioteca.mvc.vista.texto.Opcion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VistaGrafica extends Application implements IVista {

	private static IControlador controladorMVC = null;

	@Override
	public void setControlador(IControlador controlador) {

		controladorMVC = controlador;
	}

	@Override
	public void comenzar() {

		launch(this.getClass());
	}

	@Override
	public void terminar() {

		controladorMVC.terminar();
	}

	@Override
	public void start(Stage escenarioPrincipal) {

		try {

			FXMLLoader cargarVentanaPrincipal = new FXMLLoader(
					LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml"));
			VBox raiz = cargarVentanaPrincipal.load();

			ControladorVentanaPrincipal controladorVentanaPrincipal = cargarVentanaPrincipal.getController();
			controladorVentanaPrincipal.setControladorMVC(controladorMVC);

			Scene escena = new Scene(raiz);
			escenarioPrincipal.setOnCloseRequest(salidaEscena -> confirmarSalida(escenarioPrincipal, salidaEscena));
			escenarioPrincipal.setTitle("Biblioteca Yebra");
			escenarioPrincipal.setScene(escena);
			escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {

		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro que desea salir de la aplicación?",
				escenarioPrincipal)) {

			controladorMVC.terminar();
			escenarioPrincipal.close();

		} else {

			e.consume();
		}
	}
}