package org.iesalandalus.programacion.biblioteca.mvc.modelo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ILibros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IPrestamos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.memoria.Alumnos;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.memoria.Libros;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.memoria.Prestamos;

public class Modelo implements IModelo {

	private IAlumnos alumnos;
	private IPrestamos prestamos;
	private ILibros libros;

	public Modelo(IFuenteDatos fuenteDatos) {

		alumnos = fuenteDatos.crearAlumnos();
		libros = fuenteDatos.crearLibros();
		prestamos = fuenteDatos.crearPrestamos();
	}

	@Override
	public void insertar(Alumno alumno) throws OperationNotSupportedException {

		alumnos.insertar(alumno);
	}

	@Override
	public void insertar(Libro libro) throws OperationNotSupportedException {

		libros.insertar(libro);
	}

	@Override
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}

		Alumno alumno = alumnos.buscar(prestamo.getAlumno());

		if (alumno == null) {

			throw new OperationNotSupportedException("ERROR: No existe el alumno del préstamo.");
		}

		Libro libro = libros.buscar(prestamo.getLibro());

		if (libro == null) {

			throw new OperationNotSupportedException("ERROR: No existe el libro del préstamo.");
		}

		prestamos.prestar(new Prestamo(alumno, libro, prestamo.getFechaPrestamo()));
	}

	@Override
	public void devolver(Prestamo prestamo, LocalDate fechaDevolver) throws OperationNotSupportedException {

		prestamo = prestamos.buscar(prestamo);

		if (prestamo == null) {

			throw new OperationNotSupportedException("ERROR: No se puede devolver un préstamo no prestado.");
		}

		prestamos.devolver(prestamo, fechaDevolver);
	}

	@Override
	public Alumno buscar(Alumno alumno) {

		return alumnos.buscar(alumno);
	}

	@Override
	public Libro buscar(Libro libro) {

		return libros.buscar(libro);
	}

	@Override
	public Prestamo buscar(Prestamo prestamo) {

		return prestamos.buscar(prestamo);
	}

	@Override
	public void borrar(Alumno alumno) throws OperationNotSupportedException {

		List<Prestamo> prestamoAlumno = prestamos.get(alumno);

		for (Prestamo prestamo : prestamoAlumno) {

			prestamos.borrar(prestamo);
		}
		alumnos.borrar(alumno);
	}

	@Override
	public void borrar(Libro libro) throws OperationNotSupportedException {

		List<Prestamo> prestamoLibro = prestamos.get(libro);

		for (Prestamo prestamo : prestamoLibro) {

			prestamos.borrar(prestamo);
		}
		libros.borrar(libro);
	}

	@Override
	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {

		prestamos.borrar(prestamo);
	}

	@Override
	public List<Alumno> getAlumnos() {

		return alumnos.get();
	}

	@Override
	public List<Libro> getLibros() {

		return libros.get();
	}

	@Override
	public List<Prestamo> getPrestamos() {

		return prestamos.get();
	}

	@Override
	public List<Prestamo> getPrestamos(Alumno alumno) {

		return prestamos.get(alumno);
	}

	@Override
	public List<Prestamo> getPrestamos(Libro libro) {

		return prestamos.get(libro);
	}

	@Override
	public List<Prestamo> getPrestamos(LocalDate fecha) {

		return prestamos.get(fecha);
	}

	@Override
	public Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate mes) {

		return prestamos.getEstadisticaMensualPorCurso(mes);

	}
}
