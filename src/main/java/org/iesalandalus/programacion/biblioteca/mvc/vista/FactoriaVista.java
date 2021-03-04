package org.iesalandalus.programacion.biblioteca.mvc.vista;

import org.iesalandalus.programacion.biblioteca.mvc.vista.texto.VistaTexto;

public enum FactoriaVista {

	TEXTO {

		public IVista crear() {

			return new VistaTexto();
		}
	};

	public abstract IVista crear();
}
