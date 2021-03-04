package org.iesalandalus.programacion.biblioteca.mvc.vista.texto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.AudioLibro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.LibroEscrito;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}

	public static void mostrarMenu() {

		for (Opcion opcion : Opcion.values()) {

			System.out.println(opcion);
		}
	}

	public static void mostrarCabecera(String mensaje) {

		System.out.printf("%n%s%n", mensaje);
		String formatoStr = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(formatoStr, 0).replace("0", "-"));
	}

	public static int elegirOpcion() {

		int opcionMenu;

		do {

			System.out.print("\nElige una opción: ");
			opcionMenu = Entrada.entero();

		} while (!Opcion.esOrdinalValido(opcionMenu));

		return opcionMenu;
	}

	public static Alumno leerAlumno() {

		Alumno alumno = null;

		String nombreAlumno, correoAlumno;
		Curso cursoAlumno = null;
		int opcionCurso = 0;

		System.out.print("Introduce el nombre del alumno: ");
		nombreAlumno = Entrada.cadena();

		System.out.print("Introduce el correo del alumno: ");
		correoAlumno = Entrada.cadena();

		do {

			System.out.println("Introduce una opcion del 1 al 4 para el curso del alumno \n"
					+ " 1. 1º ESO, 2. 2º ESO, 3. 3º ESO, 4. 4º ESO: ");
			opcionCurso = Entrada.entero();

			switch (opcionCurso) {

			case 1:

				cursoAlumno = cursoAlumno.PRIMERO;
				break;

			case 2:

				cursoAlumno = cursoAlumno.SEGUNDO;
				break;

			case 3:

				cursoAlumno = cursoAlumno.TERCERO;
				break;

			case 4:

				cursoAlumno = cursoAlumno.CUARTO;
				break;

			default:

				System.out.println("Lo siento pero esa opción no esta permitida");
				break;
			}

		} while (opcionCurso != 1 && opcionCurso != 2 && opcionCurso != 3 && opcionCurso != 4);

		alumno = new Alumno(nombreAlumno, correoAlumno, cursoAlumno);

		return alumno;
	}

	public static Alumno leerAlumnoFicticio() {

		Alumno alumnoFicticio;
		String correoAlumno;

		System.out.print("Introduce el correo del alumno: ");
		correoAlumno = Entrada.cadena();

		alumnoFicticio = Alumno.getAlumnoFicticio(correoAlumno);

		return alumnoFicticio;
	}

	public static Libro leerLibro() {

		Libro libro = null;

		String tituloLibro, autorLibro;
		int opcionLibro;
		int numPaginasLibro, duracionLibro;

		System.out.print("Introduce el titulo del libro: ");
		tituloLibro = Entrada.cadena();

		System.out.print("Introduce el autor del libro: ");
		autorLibro = Entrada.cadena();

		do {

			System.out.println(
					"Introduce una opcion 1 o 2 para el tipo de libro \n" + " 1.Libro escrito, 2.Audio Libro: ");
			opcionLibro = Entrada.entero();

			switch (opcionLibro) {

			case 1:

				System.out.print("introduce el número de paginas del libro: ");
				numPaginasLibro = Entrada.entero();

				libro = new LibroEscrito(tituloLibro, autorLibro, numPaginasLibro);
				break;

			case 2:

				System.out.print("introduce la duración del libro: ");
				duracionLibro = Entrada.entero();

				libro = new AudioLibro(tituloLibro, autorLibro, duracionLibro);
				break;

			default:

				System.out.println("Lo siento pero esa opción no esta permitida");
				break;
			}

		} while (opcionLibro != 1 && opcionLibro != 2);

		return libro;
	}

	public static Libro leerLibroFicticio() {

		Libro libroFicticio;
		String tituloLibro, autorLibro;

		System.out.print("Introduce el titulo del libro: ");
		tituloLibro = Entrada.cadena();

		System.out.print("Introduce el autor del libro: ");
		autorLibro = Entrada.cadena();

		libroFicticio = Libro.getLibroFicticio(tituloLibro, autorLibro);

		return libroFicticio;
	}

	public static Prestamo leerPrestamo() {

		Alumno alumno = leerAlumnoFicticio();
		Libro libro = leerLibroFicticio();
		LocalDate fechaPrestamo = null;

		fechaPrestamo = leerFecha("Introduce la fecha del prestamo: ");

		Prestamo prestamo = new Prestamo(alumno, libro, fechaPrestamo);

		return prestamo;
	}

	public static Prestamo leerPrestamoFicticio() {

		Prestamo prestamoFicticio;

		Alumno alumno = leerAlumnoFicticio();
		Libro libro = leerLibroFicticio();

		prestamoFicticio = Prestamo.getPrestamoFicticio(alumno, libro);

		return prestamoFicticio;
	}

	public static LocalDate leerFecha(String mensaje) {

		LocalDate fecha = null;
		String fechaPrestamo;

		try {

			System.out.println(mensaje);
			fechaPrestamo = Entrada.cadena();

			fecha = LocalDate.parse(fechaPrestamo, Prestamo.FORMATO_FECHA);

		} catch (DateTimeParseException e) {

			System.out.println("ERROR: El formato de la fecha no es correcto");
		}

		return fecha;
	}

}
