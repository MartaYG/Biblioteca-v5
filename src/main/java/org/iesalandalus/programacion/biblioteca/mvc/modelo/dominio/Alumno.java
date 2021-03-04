package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.util.Objects;

public class Alumno {

	private static final String ER_NOMBRE = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([\\s]+[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)+$";
	private static final String ER_CORREO = "^.+[@][a-zA-Z]+\\.[a-zA-Z]+$";

	private String nombre;
	private String correo;

	private Curso curso;

	public Alumno(String nombre, String correo, Curso curso) {

		setNombre(nombre);
		setCorreo(correo);
		setCurso(curso);
	}

	public Alumno(Alumno alumno) {

		if (alumno == null) {

			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		}

		setNombre(alumno.getNombre());
		setCorreo(alumno.getCorreo());
		setCurso(alumno.getCurso());
	}

	public static Alumno getAlumnoFicticio(String correo) {

		return new Alumno("José Ramón Jiménez Reyes", correo, Curso.PRIMERO);
	}

	public String getNombre() {

		return nombre;
	}

	private void setNombre(String nombre) {

		if (nombre == null) {

			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");

		} else if (!nombre.matches(ER_NOMBRE)) {

			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		}

		this.nombre = formateaNombre(nombre);
	}

	private String formateaNombre(String nombre) {

		String formatearNombre = nombre;

		formatearNombre = formatearNombre.toLowerCase().replaceAll(" +", " ").trim();

		String[] palabra = formatearNombre.split(" ");
		String devolverCadena = "";

		for (int i = 0; i < palabra.length; i++) {

			String modificando = palabra[i];
			modificando = modificando.substring(0, 1).toUpperCase() + modificando.substring(1, modificando.length());
			palabra[i] = modificando;
			devolverCadena += modificando + " ";
		}

		devolverCadena = devolverCadena.trim();

		return devolverCadena;
	}

	public String getCorreo() {

		return correo;
	}

	private void setCorreo(String correo) {

		if (correo == null) {

			throw new NullPointerException("ERROR: El correo no puede ser nulo.");

		} else if (!correo.matches(ER_CORREO)) {

			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		}

		this.correo = correo;
	}

	private String getIniciales() {

		String iniciales = "";
		String[] palabra = getNombre().split(" ");

		for (int i = 0; i < palabra.length; i++) {

			iniciales += palabra[i].substring(0, 1);
		}
		return iniciales;
	}

	public Curso getCurso() {

		return curso;
	}

	public void setCurso(Curso curso) {

		if (curso == null) {

			throw new NullPointerException("ERROR: El curso no puede ser nulo.");
		}

		this.curso = curso;
	}

	@Override
	public int hashCode() {

		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			
			return true;
		}

		if (!(obj instanceof Alumno)) {
			
			return false;
		}

		Alumno other = (Alumno) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {

		return String.format("nombre=%s (%s), correo=%s, curso=%s", formateaNombre(nombre), getIniciales(), correo,
				curso);
	}

}
