package com.sds.service.proceso.model;

import java.util.List;

import com.sds.model.ObjetivoEntity;
import com.sds.model.ProcesoEntity;

public class Proceso {

	private String usuario;
	private ProcesoEntity proceso;
	private List<ObjetivoEntity> objetivos;
	private List<Integer> niveles;

	public Proceso() {
		super();
	}

	public Proceso(final String usuario, final ProcesoEntity proceso) {
		super();
		this.usuario = usuario;
		this.proceso = proceso;
	}

	public Proceso(final String usuario, final ProcesoEntity proceso, final List<ObjetivoEntity> objetivos,
			final List<Integer> niveles) {
		super();
		this.usuario = usuario;
		this.proceso = proceso;
		this.objetivos = objetivos;
		this.niveles = niveles;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public ProcesoEntity getProceso() {
		return proceso;
	}

	public void setProceso(final ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	public List<ObjetivoEntity> getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(final List<ObjetivoEntity> objetivos) {
		this.objetivos = objetivos;
	}

	public List<Integer> getNiveles() {
		return niveles;
	}

	public void setNivel(final List<Integer> niveles) {
		this.niveles = niveles;
	}

	@Override
	public String toString() {
		return "Proceso [usuario=" + usuario + ", proceso=" + proceso + ", objetivos=" + objetivos + ", niveles="
				+ niveles + "]";
	}

}
