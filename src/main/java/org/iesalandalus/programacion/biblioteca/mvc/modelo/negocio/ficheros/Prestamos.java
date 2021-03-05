package org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Curso;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Libro;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.dominio.Prestamo;
import org.iesalandalus.programacion.biblioteca.mvc.modelo.negocio.IPrestamos;

public class Prestamos implements IPrestamos {

	List<Prestamo> coleccionPrestamos;
	private static final String NOMBRE_FICHERO_PRESTAMOS="datos/prestamos.dat";

	public Prestamos() {

		coleccionPrestamos = new ArrayList<>();
	}
	
	@Override
	public void comenzar() {
		
		leer();
	}
	
	private void leer() {
		
		File ficheroPrestamos = new File(NOMBRE_FICHERO_PRESTAMOS);
		
		try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroPrestamos))){
			
			Prestamo prestamo = null;
			
			do {
				
				prestamo =(Prestamo) entrada.readObject();
				prestar(prestamo);
						
			}while(prestamo !=null);
			
		} catch (FileNotFoundException e) {
			
			System.out.println("No puedo abrir el fichero de prestamos.");
			
		} catch(EOFException e) {
			
			System.out.println("Fichero prestamos leído satisfactoriamente.");
			
		} catch (IOException e) {
			
			System.out.println("Error inesperado de Entrada/Salida.");
			
		} catch (ClassNotFoundException e) {
			
			System.out.println("No puedo encontrar la clase que tengo que leer.");
			
		} catch (OperationNotSupportedException e) {
			
			System.out.println(e.getMessage());
		} 
	}
	
	@Override
	public void terminar() {
		
		escribir();
	}
	
	private void escribir() {
		
		File ficheroPrestamos = new File(NOMBRE_FICHERO_PRESTAMOS);
		
		try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroPrestamos))){
			
			for(Prestamo prestamo : coleccionPrestamos) {
				
				salida.writeObject(prestamo);
			}
			
			System.out.println("Fichero prestamos escrito satisfactoriamente.");

		} catch (FileNotFoundException e) {
			
			System.out.println("No puedo crear el fichero de prestamos.");
			
		} catch (IOException e) {
			
			System.out.println("Error inesperado de Entrada/Salida.");
		}
	}

	@Override
	public List<Prestamo> get() {

		List<Prestamo> copiaPrestamos = copiaProfundaPrestamos();

		Comparator<Alumno> compararAlumno = Comparator.comparing(Alumno::getNombre);
		Comparator<Libro> compararLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);

		Comparator<Prestamo> compararPrestamo = Comparator.comparing(Prestamo::getFechaPrestamo)
				.thenComparing(Prestamo::getAlumno, compararAlumno).thenComparing(Prestamo::getLibro, compararLibro);

		copiaPrestamos.sort(compararPrestamo);

		return copiaPrestamos;
	}

	private List<Prestamo> copiaProfundaPrestamos() {

		List<Prestamo> copiaPrestamos = new ArrayList<>();

		for (Prestamo prestamo : coleccionPrestamos) {

			copiaPrestamos.add(new Prestamo(prestamo));
		}

		return copiaPrestamos;
	}

	@Override
	public int getTamano() {

		return coleccionPrestamos.size();
	}

	@Override
	public List<Prestamo> get(Alumno alumno) {

		if (alumno == null) {

			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}

		List<Prestamo> prestamoAlumno = new ArrayList<>();

		for (Prestamo prestamo : coleccionPrestamos) {

			if (prestamo.getAlumno().equals(alumno)) {

				prestamoAlumno.add(new Prestamo(prestamo));
			}
		}

		Comparator<Libro> compararLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);

		Comparator<Prestamo> compararPrestamo = Comparator.comparing(Prestamo::getFechaPrestamo)
				.thenComparing(Prestamo::getLibro, compararLibro);

		prestamoAlumno.sort(compararPrestamo);

		return prestamoAlumno;
	}

	@Override
	public List<Prestamo> get(Libro libro) {

		if (libro == null) {

			throw new NullPointerException("ERROR: El libro no puede ser nulo.");
		}

		List<Prestamo> prestamoLibro = new ArrayList<>();

		for (Prestamo prestamo : coleccionPrestamos) {

			if (prestamo.getLibro().equals(libro)) {

				prestamoLibro.add(new Prestamo(prestamo));
			}
		}

		Comparator<Alumno> compararAlumno = Comparator.comparing(Alumno::getNombre);

		Comparator<Prestamo> compararPrestamo = Comparator.comparing(Prestamo::getFechaPrestamo)
				.thenComparing(Prestamo::getAlumno, compararAlumno);

		prestamoLibro.sort(compararPrestamo);

		return prestamoLibro;
	}

	@Override
	public List<Prestamo> get(LocalDate fecha) {

		if (fecha == null) {

			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}

		List<Prestamo> prestamoFecha = new ArrayList<>();

		for (Prestamo prestamo : coleccionPrestamos) {

			if (mismoMes(prestamo.getFechaPrestamo(), fecha)) {

				prestamoFecha.add(new Prestamo(prestamo));
			}
		}

		Comparator<Alumno> compararAlumno = Comparator.comparing(Alumno::getNombre);
		Comparator<Libro> compararLibro = Comparator.comparing(Libro::getTitulo).thenComparing(Libro::getAutor);

		Comparator<Prestamo> compararPrestamo = Comparator.comparing(Prestamo::getFechaPrestamo)
				.thenComparing(Prestamo::getAlumno, compararAlumno).thenComparing(Prestamo::getLibro, compararLibro);

		prestamoFecha.sort(compararPrestamo);

		return prestamoFecha;
	}

	@Override
	public Map<Curso, Integer> getEstadisticaMensualPorCurso(LocalDate mes) {

		Curso curso;

		Map<Curso, Integer> estadisticaMensualPorCurso = inicializarEstadisticas();
		List<Prestamo> prestamoMes = get(mes);

		for (Prestamo prestamo : prestamoMes) {

			curso = prestamo.getAlumno().getCurso();

			estadisticaMensualPorCurso.put(curso, estadisticaMensualPorCurso.get(curso) + prestamo.getPuntos());

		}

		return estadisticaMensualPorCurso;
	}

	private Map<Curso, Integer> inicializarEstadisticas() {

		Map<Curso, Integer> estadisticaMensualPorCurso = new EnumMap<>(Curso.class);

		for (Curso curso : Curso.values()) {

			estadisticaMensualPorCurso.put(curso, 0);
		}

		return estadisticaMensualPorCurso;
	}

	private boolean mismoMes(LocalDate primeraFecha, LocalDate segundaFecha) {

		return (primeraFecha.getYear() == segundaFecha.getYear() && primeraFecha.getMonth() == segundaFecha.getMonth());
	}

	@Override
	public void prestar(Prestamo prestamo) throws OperationNotSupportedException {

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede prestar un préstamo nulo.");
		}

		if (coleccionPrestamos.contains(prestamo)) {

			throw new OperationNotSupportedException("ERROR: Ya existe un préstamo igual.");

		} else {

			coleccionPrestamos.add(new Prestamo(prestamo));
		}
	}

	@Override
	public void devolver(Prestamo prestamo, LocalDate fechaDevolver) throws OperationNotSupportedException {

		int indicePrestamo;

		if (prestamo == null) {

			throw new NullPointerException("ERROR: No se puede devolver un préstamo nulo.");
		}

		indicePrestamo = coleccionPrestamos.indexOf(prestamo);

		if (indicePrestamo == -1) {

			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");

		} else {

			coleccionPrestamos.get(indicePrestamo).devolver(fechaDevolver);
		}
	}

	@Override
	public Prestamo buscar(Prestamo prestamo) {

		int indicePrestamo;

		if (prestamo == null) {

			throw new IllegalArgumentException("ERROR: No se puede buscar un préstamo nulo.");
		}

		indicePrestamo = coleccionPrestamos.indexOf(prestamo);

		if (indicePrestamo == -1) {

			prestamo = null;

		} else {

			prestamo = new Prestamo(coleccionPrestamos.get(indicePrestamo));
		}

		return prestamo;
	}

	@Override
	public void borrar(Prestamo prestamo) throws OperationNotSupportedException {

		if (prestamo == null) {

			throw new IllegalArgumentException("ERROR: No se puede borrar un préstamo nulo.");
		}

		if (!coleccionPrestamos.contains(prestamo)) {

			throw new OperationNotSupportedException("ERROR: No existe ningún préstamo igual.");

		} else {

			coleccionPrestamos.remove(prestamo);
		}
	}
}
