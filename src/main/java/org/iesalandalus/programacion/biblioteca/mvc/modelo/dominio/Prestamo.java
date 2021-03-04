package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Prestamo {

	private static final int MAX_DIAS_PRESTAMO = 20;
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private LocalDate fechaPrestamo;
	private LocalDate fechaDevolucion;
	private Alumno alumno;
	private Libro libro;

	public Prestamo(Alumno alumno, Libro libro, LocalDate fechaPrestamo) {

		setAlumno(alumno);
		setLibro(libro);
		setFechaPrestamo(fechaPrestamo);
	}

	public Prestamo(Prestamo prestamo) {

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No es posible copiar un préstamo nulo.");
		}

		this.alumno = prestamo.getAlumno();
		this.libro = prestamo.getLibro();
		this.fechaPrestamo = prestamo.getFechaPrestamo();
		this.fechaDevolucion = prestamo.getFechaDevolucion();
	}

	public static Prestamo getPrestamoFicticio(Alumno alumno, Libro libro) {

		return new Prestamo(alumno, libro, LocalDate.now());
	}

	public void devolver(LocalDate fecha) {

		if (fechaDevolucion != null) {

			throw new IllegalArgumentException("ERROR: La devolución ya estaba registrada.");
		}

		setFechaDevolucion(fecha);
	}

	public int getPuntos() {

		float puntosGanados = 0f;
		int diasPrestados = 0;

		if (fechaDevolucion != null) {

			diasPrestados = (int) ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);

			if (diasPrestados <= MAX_DIAS_PRESTAMO) {

				puntosGanados =libro.getPuntos()/diasPrestados;
				
			}else {
				
				puntosGanados=0;
			}
		}

		return Math.round(puntosGanados);
	}

	public Alumno getAlumno() {

		return new Alumno(alumno);
	}

	private void setAlumno(Alumno alumno) {

		if (alumno == null) {

			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}

		this.alumno = alumno;
	}

	public Libro getLibro() {

		Libro libro = null;

		if (this.libro instanceof LibroEscrito) {

			libro = new LibroEscrito((LibroEscrito)this.libro);

		} else if (this.libro instanceof AudioLibro) {

			libro = new AudioLibro((AudioLibro)this.libro);
		}

		return libro;
	}

	private void setLibro(Libro libro) {

		if (libro == null) {

			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}
		
		if (libro instanceof LibroEscrito) {

			this.libro = new LibroEscrito((LibroEscrito)libro);

		} else if (libro instanceof AudioLibro) {

			this.libro = new AudioLibro((AudioLibro)libro);
		}
	}

	public LocalDate getFechaPrestamo() {

		return fechaPrestamo;
	}

	private void setFechaPrestamo(LocalDate fechaPrestamo) {

		if (fechaPrestamo == null) {

			throw new NullPointerException("ERROR: La fecha de préstamo no puede ser nula.");

		} else if (fechaPrestamo.isAfter(LocalDate.now())) {

			throw new IllegalArgumentException("ERROR: La fecha de préstamo no puede ser futura.");
		}

		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {

		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {

		if (fechaDevolucion == null) {

			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");

		} else if (fechaDevolucion.isAfter(LocalDate.now())) {

			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");

		} else if (fechaDevolucion.isBefore(fechaPrestamo) || fechaDevolucion.isEqual(fechaPrestamo)) {

			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de préstamo.");
		}

		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public int hashCode() {

		return Objects.hash(alumno, libro);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (!(obj instanceof Prestamo)) {

			return false;
		}

		Prestamo other = (Prestamo) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(libro, other.libro);
	}

	@Override
	public String toString() {

		if (fechaDevolucion == null) {

			return String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, puntos=%d", alumno, libro,
					fechaPrestamo.format(FORMATO_FECHA), getPuntos());

		} else {
			return String.format("alumno=(%s), libro=(%s), fecha de préstamo=%s, fecha de devolución=%s, puntos=%d",
					alumno, libro, fechaPrestamo.format(FORMATO_FECHA), fechaDevolucion.format(FORMATO_FECHA),
					getPuntos());
		}
	}
}
