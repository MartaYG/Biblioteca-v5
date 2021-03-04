package org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio;

import java.util.Objects;

public abstract class Libro {

	protected String titulo;
	protected String autor;

	public Libro(String titulo, String autor) {

		setTitulo(titulo);
		setAutor(autor);
	}

	public Libro(Libro libro) {

		if (libro == null) {

			throw new NullPointerException("ERROR: No es posible copiar un libro nulo.");
		}

		setTitulo(libro.getTitulo());
		setAutor(libro.getAutor());
	}

	public static Libro getLibroFicticio(String titulo, String autor) {

		return new LibroEscrito(titulo, autor, 471);
	}

	public abstract float getPuntos();

	public String getTitulo() {

		return titulo;
	}

	protected void setTitulo(String titulo) {

		if (titulo == null) {

			throw new NullPointerException("ERROR: El título no puede ser nulo.");

		} else if (titulo.trim().equals("")) {

			throw new IllegalArgumentException("ERROR: El título no puede estar vacío.");
		}

		this.titulo = titulo;
	}

	public String getAutor() {

		return autor;
	}

	protected void setAutor(String autor) {

		if (autor == null) {

			throw new NullPointerException("ERROR: El autor no puede ser nulo.");

		} else if (autor.trim().equals("")) {

			throw new IllegalArgumentException("ERROR: El autor no puede estar vacío.");
		}

		this.autor = autor;
	}

	@Override
	public int hashCode() {

		return Objects.hash(titulo, autor);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (!(obj instanceof Libro)) {

			return false;
		}

		Libro other = (Libro) obj;
		return Objects.equals(titulo, other.titulo) && Objects.equals(autor, other.autor);
	}

	@Override
	public String toString() {

		return String.format("título=%s, autor=%s", titulo, autor);
	}
}
