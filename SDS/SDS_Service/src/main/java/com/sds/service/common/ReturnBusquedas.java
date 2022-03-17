
package com.sds.service.common;

import java.util.ArrayList;
import java.util.List;

public class ReturnBusquedas<T> {

	private List<T> listaBusquedas = new ArrayList<T>();
	private List<String> datosBusqueda = new ArrayList<>();
	private int tamanhoTotal;
	private int numResultados;

	public ReturnBusquedas() {
		super();
	}

	public ReturnBusquedas(final List<T> listaBusquedas, final int tamanhoTotal, final int numResultados) {
		super();
		this.listaBusquedas = listaBusquedas;
		this.tamanhoTotal = tamanhoTotal;
		this.numResultados = numResultados;
	}

	public ReturnBusquedas(final List<T> listaBusquedas, final List<String> datosBusqueda, final int tamanhoTotal,
			final int numResultados) {
		super();
		this.listaBusquedas = listaBusquedas;
		this.datosBusqueda = datosBusqueda;
		this.tamanhoTotal = tamanhoTotal;
		this.numResultados = numResultados;
	}

	public List<T> getListaBusquedas() {
		return listaBusquedas;
	}

	public void setListaBusquedas(final List<T> listaBusquedas) {
		this.listaBusquedas = listaBusquedas;
	}

	public List<String> getDatosBusqueda() {
		return datosBusqueda;
	}

	public void setDatosBusqueda(final List<String> datosBusqueda) {
		this.datosBusqueda = datosBusqueda;
	}

	public int getTamanhoTotal() {
		return tamanhoTotal;
	}

	public void setTamanhoTotal(final int tamanhoTotal) {
		this.tamanhoTotal = tamanhoTotal;
	}

	public int getNumResultados() {
		return numResultados;
	}

	public void setNumResultados(final int numResultados) {
		this.numResultados = numResultados;
	}

	@Override
	public String toString() {
		return "ReturnBusquedas [listaBusquedas=" + listaBusquedas + ", tamanhoTotal=" + tamanhoTotal
				+ ", numResultados=" + numResultados + "]";
	}

}
