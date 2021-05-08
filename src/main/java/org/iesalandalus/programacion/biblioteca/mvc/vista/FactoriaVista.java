package org.iesalandalus.programacion.biblioteca.mvc.vista;

import org.iesalandalus.programacion.biblioteca.mvc.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.biblioteca.mvc.vista.texto.VistaTexto;

public enum FactoriaVista {

	TEXTO {

		public IVista crear() {

			return new VistaTexto();
		}
	},

	GRAFICA {

		public IVista crear() {

			return new VistaGrafica();
		}
	};

	public abstract IVista crear();
}
