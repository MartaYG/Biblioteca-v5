package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.AudioLibro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.LibroEscrito;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ILibros;

public class Libros implements ILibros {

	private List<Libro> coleccionLibros;

	public Libros() {

		coleccionLibros = new ArrayList<>();
	}

	@Override
	public List<Libro> get() {

		List<Libro> copiaLibros = copiaProfundaLibros();
		copiaLibros.sort(Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor));
		
		return copiaLibros;
	}

	private List<Libro> copiaProfundaLibros() {

		List<Libro> copiaLibros = new ArrayList<>();

		for (Libro libro : coleccionLibros) {

			if (libro instanceof LibroEscrito) {

				copiaLibros.add(new LibroEscrito((LibroEscrito) libro));

			} else if (libro instanceof AudioLibro) {

				copiaLibros.add(new AudioLibro((AudioLibro) libro));
			}
		}

		return copiaLibros;
	}

	@Override
	public int getTamano() {

		return coleccionLibros.size();
	}

	@Override
	public void insertar(Libro libro) throws OperationNotSupportedException {

		if (libro == null) {

			throw new NullPointerException("ERROR: No se puede insertar un libro nulo.");
		}

		if (coleccionLibros.contains(libro)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un libro con ese título y autor.");

		} else {

			if (libro instanceof LibroEscrito) {

				coleccionLibros.add(new LibroEscrito((LibroEscrito) libro));

			} else if (libro instanceof AudioLibro) {

				coleccionLibros.add(new AudioLibro((AudioLibro) libro));
			}
		}
	}

	@Override
	public Libro buscar(Libro libro) {

		int indiceLibro;

		if (libro == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un libro nulo.");
		}

		indiceLibro = coleccionLibros.indexOf(libro);
		
		Libro libroBuscado=null;

		if (indiceLibro == -1) {

			libroBuscado = null;

		} else {

			if (libro instanceof LibroEscrito) {

				libroBuscado = new LibroEscrito((LibroEscrito) libro);

			} else if (libro instanceof AudioLibro) {

				libroBuscado = new AudioLibro((AudioLibro) libro);
			}
		}

		return libroBuscado;
	}

	@Override
	public void borrar(Libro libro) throws OperationNotSupportedException {

		if (libro == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un libro nulo.");
		}

		if (!coleccionLibros.contains(libro)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún libro con ese título y autor.");

		} else {

			coleccionLibros.remove(libro);
		}
	}
}