package org.iesalandalus.programacion.biblioteca.mvc.vista.texto;

import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.apache.commons.math3.exception.NullArgumentException;
import org.iesalandalus.programacion.biblioteca.mvc.controlador.IControlador;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.vista.IVista;
import org.iesalandalus.programacion.utilidades.Entrada;

public class VistaTexto implements IVista {

	private IControlador controlador;

	public VistaTexto() {

		Opcion.setVista(this);
	}

	@Override
	public void setControlador(IControlador controlador) {

		this.controlador = controlador;
	}

	@Override
	public void comenzar() {

		int ordinalOpcion;
		Opcion opcion;

		Consola.mostrarCabecera("Gestión de la biblioteca del IES Al-Ándalus");

		do {

			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
			System.out.println("\n");

		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	@Override
	public void terminar() {

		controlador.terminar();
	}

	public void insertarAlumno() {

		Consola.mostrarCabecera("Insertar alumno");

		try {

			controlador.insertar(Consola.leerAlumno());
			System.out.println("El alumno está insertado correctamente.");

		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {

			System.out.println(e.getMessage());
		}
	}

	public void buscarAlumno() {

		String mensaje = "";
		Alumno alumno;

		Consola.mostrarCabecera("Buscar alumno");

		try {

			alumno = controlador.buscar(Consola.leerAlumnoFicticio());

			if (alumno == null) {

				mensaje = "No existe dicho alumno";

			} else {

				mensaje = alumno.toString();
			}

			System.out.println(mensaje);

		} catch (IllegalArgumentException | NullPointerException e) {

			System.out.println(e.getMessage());
		}

	}

	public void borrarAlumno() {

		Consola.mostrarCabecera("Borrar alumno");

		try {

			controlador.borrar(Consola.leerAlumnoFicticio());
			System.out.println("El alumno está borrado correctamente.");

		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

			System.out.println(e.getMessage());
		}
	}

	public void listarAlumnos() {

		Consola.mostrarCabecera("Lista de alumnos");

		List<Alumno> alumnos = controlador.getAlumnos();

		if (!alumnos.isEmpty()) {

			for (Alumno alumno : alumnos) {

				if (alumno != null) {

					System.out.println(alumno);
				}
			}

		} else {

			System.out.println("No hay alumnos que mostrar.");
		}
	}

	public void insertarLibro() {

		Consola.mostrarCabecera("Insertar libro");

		try {

			controlador.insertar(Consola.leerLibro());
			System.out.println("el Libro está insertado correctamente.");

		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

			System.out.println(e.getMessage());
		}
	}

	public void buscarLibro() {

		String mensaje = "";
		Libro libro;

		Consola.mostrarCabecera("Buscar libro");

		try {

			libro = controlador.buscar(Consola.leerLibroFicticio());

			if (libro == null) {

				mensaje = "No existe dicho libro";

			} else {

				mensaje = libro.toString();
			}

			System.out.println(mensaje);

		} catch (IllegalArgumentException | NullPointerException e) {

			System.out.println(e.getMessage());
		}
	}

	public void borrarLibro() {

		Consola.mostrarCabecera("Borrar libro");

		try {

			controlador.borrar(Consola.leerLibroFicticio());
			System.out.println("El libro está borrado correctamente.");

		} catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {

			System.out.println(e.getMessage());
		}
	}

	public void listarLibros() {

		Consola.mostrarCabecera("Lista de libros");

		List<Libro> libros = controlador.getLibros();

		if (!libros.isEmpty()) {

			for (Libro libro : libros) {

				if (libro != null) {

					System.out.println(libro);
				}
			}

		} else {

			System.out.println("No hay libros que mostrar");
		}
	}

	public void prestarLibro() {

		Consola.mostrarCabecera("Prestar libro");

		try {

			controlador.prestar(Consola.leerPrestamo());
			;
			System.out.println("el Libro ha sido prestado correctamente.");

		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {

			System.out.println(e.getMessage());
		}
	}

	public void devolverLibro() {

		Consola.mostrarCabecera("Devolver libro");

		try {

			controlador.devolver(Consola.leerPrestamoFicticio(),
					Consola.leerFecha("Introduce la fecha del prestamo: "));
			System.out.println("el Libro ha sido devuelto correctamente.");

		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {

			System.out.println(e.getMessage());
		}
	}

	public void buscarPrestamo() {

		String mensaje = "";
		Prestamo prestamo = null;

		Consola.mostrarCabecera("Buscar prestamo");

		try {

			prestamo = controlador.buscar(Consola.leerPrestamoFicticio());

			if (prestamo == null) {

				mensaje = "No existe dicho prestamo";

			} else {

				mensaje = prestamo.toString();
			}

			System.out.println(mensaje);

		} catch (IllegalArgumentException | NullPointerException e) {

			System.out.println(e.getMessage());
		}
	}

	public void borrarPrestamo() {

		Consola.mostrarCabecera("Borrar prestamo");

		try {

			controlador.borrar(Consola.leerPrestamoFicticio());
			System.out.println("El prestamo está borrado correctamente.");

		} catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

			System.out.println(e.getMessage());
		}
	}

	public void listarPrestamos() {

		Consola.mostrarCabecera("Lista de prestamos");

		List<Prestamo> prestamos = controlador.getPrestamos();

		if (!prestamos.isEmpty()) {

			for (Prestamo prestamo : prestamos) {

				if (prestamo != null) {

					System.out.println(prestamo);
				}
			}

		} else {

			System.out.println("No hay prestamos que mostrar");
		}
	}

	public void listarPrestamosAlumno() {

		Consola.mostrarCabecera("Lista de prestamos por alumno");

		try {

			List<Prestamo> prestamosAlumno = controlador.getPrestamos(Consola.leerAlumnoFicticio());

			if (!prestamosAlumno.isEmpty()) {

				for (Prestamo prestamo : prestamosAlumno) {

					if (prestamo != null) {

						System.out.println(prestamo);
					}
				}

			} else {

				System.out.println("No hay prestamos que mostrar para dicho alumno");
			}

		} catch (NullPointerException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}

	}

	public void listarPrestamosLibro() {

		Consola.mostrarCabecera("Lista de prestamos por libro");

		try {

			List<Prestamo> prestamoLibro = controlador.getPrestamos(Consola.leerLibroFicticio());

			if (!prestamoLibro.isEmpty()) {

				for (Prestamo prestamo : prestamoLibro) {

					if (prestamo != null) {

						System.out.println(prestamo);
					}
				}

			} else {

				System.out.println("No hay prestamos que mostrar para dicho libro");
			}

		} catch (NullPointerException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}

	}

	public void listarPrestamosFecha() {

		Consola.mostrarCabecera("Lista de prestamos por libro");

		try {

			List<Prestamo> prestamoFecha = controlador
					.getPrestamos(Consola.leerFecha("Introduce la fecha del prestamo: "));

			if (!prestamoFecha.isEmpty()) {

				for (Prestamo prestamo : prestamoFecha) {

					if (prestamo != null) {

						System.out.println(prestamo);
					}
				}

			} else {

				System.out.println("No hay prestamos que mostrar para dicha fecha");
			}

		} catch (NullPointerException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

	public void mostrarEstadisticaMensualPorCurso() {

		String listadosPuntos = "(";

		Consola.mostrarCabecera("Estadística mensual por curso");

		try {

			Map<Curso, Integer> estadisticaMensualPorCurso = controlador
					.getEstadisticaMensualPorCurso(Consola.leerFecha("Introduzca la fecha: "));

			for (Curso curso : Curso.values()) {

				listadosPuntos += curso.toString() + "=" + estadisticaMensualPorCurso.get(curso) + ", ";
			}

			listadosPuntos = listadosPuntos.substring(0, listadosPuntos.length() - 2) + ")";

			System.out.println(listadosPuntos);

		} catch (NullPointerException | IllegalArgumentException e) {

			System.out.println(e.getMessage());
		}
	}

}
