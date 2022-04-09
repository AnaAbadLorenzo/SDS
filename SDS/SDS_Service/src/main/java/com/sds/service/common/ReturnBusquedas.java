
package com.sds.service.common;

import java.util.ArrayList;
import java.util.List;

public class ReturnBusquedas<T> {

	private List<T> listaBusquedas = new ArrayList<T>();
	private List<String> datosBusqueda = new ArrayList<>();
	private int tamanhoTotal;
	private int numResultados;
	private int inicio;

	public ReturnBusquedas() {
		super();
	}

	public ReturnBusquedas(final List<T> listaBusquedas, final int tamanhoTotal, final int numResultados,
			final int inicio) {
		super();
		this.listaBusquedas = listaBusquedas;
		this.tamanhoTotal = tamanhoTotal;
		this.numResultados = numResultados;
		this.inicio = inicio;
	}

	public ReturnBusquedas(final List<T> listaBusquedas, final List<String> datosBusqueda, final int tamanhoTotal,
			final int numResultados, final int inicio) {
		super();
		this.listaBusquedas = listaBusquedas;
		this.datosBusqueda = datosBusqueda;
		this.tamanhoTotal = tamanhoTotal;
		this.numResultados = numResultados;
		this.inicio = inicio;
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

	public int getInicio() {
		return inicio;
	}

	public void setInicio(final int inicio) {
		this.inicio = inicio;
	}

	@Override
	public String toString() {
		return "ReturnBusquedas [listaBusquedas=" + listaBusquedas + ", datosBusqueda=" + datosBusqueda
				+ ", tamanhoTotal=" + tamanhoTotal + ", numResultados=" + numResultados + ", inicio=" + inicio + "]";
	}

}
