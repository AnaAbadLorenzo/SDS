package com.sds.service.proceso.model;

import java.util.List;

import com.sds.model.ObjetivoEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.RespuestaPosibleEntity;

public class Proceso {

	private String usuario;
	private ProcesoEntity proceso;
	private List<ProcedimientoEntity> procedimientos;
	private List<ObjetivoEntity> objetivos;
	private List<Integer> niveles;
	private List<RespuestaPosibleEntity> respuestasPosibles;

	public Proceso() {
		super();
	}

	public Proceso(final String usuario, final ProcesoEntity proceso) {
		super();
		this.usuario = usuario;
		this.proceso = proceso;
	}

	public Proceso(final String usuario, final ProcesoEntity proceso, final List<ProcedimientoEntity> procedimientos,
			final List<ObjetivoEntity> objetivos, final List<Integer> niveles,
			final List<RespuestaPosibleEntity> respuestasPosibles) {
		super();
		this.usuario = usuario;
		this.proceso = proceso;
		this.procedimientos = procedimientos;
		this.objetivos = objetivos;
		this.niveles = niveles;
		this.respuestasPosibles = respuestasPosibles;
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

	public List<ProcedimientoEntity> getProcedimientos() {
		return procedimientos;
	}

	public void setProcedimientos(final List<ProcedimientoEntity> procedimientos) {
		this.procedimientos = procedimientos;
	}

	public List<RespuestaPosibleEntity> getRespuestasPosibles() {
		return respuestasPosibles;
	}

	public void setRespuestasPosibles(final List<RespuestaPosibleEntity> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}

	public void setNiveles(final List<Integer> niveles) {
		this.niveles = niveles;
	}

	@Override
	public String toString() {
		return "Proceso [usuario=" + usuario + ", proceso=" + proceso + ", procedimiento=" + procedimientos
				+ ", objetivos=" + objetivos + ", niveles=" + niveles + ", respuestasPosibles=" + respuestasPosibles
				+ "]";
	}

}
