package com.sds.service.procedimientousuarioproceso.model;

import java.util.Date;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.UsuarioEntity;

public class ProcedimientoUsuarioProcesoBuscar {

	private ProcedimientoEntity procedimiento;
	private UsuarioEntity usuario;
	private ProcesoEntity proceso;
	private Date fechaProcedimientoUsuarioProceso;
	private int inicio;
	private int tamanhoPagina;

	public ProcedimientoUsuarioProcesoBuscar() {
		super();
	}

	public ProcedimientoUsuarioProcesoBuscar(final ProcedimientoEntity procedimiento, final UsuarioEntity usuario,
			final ProcesoEntity proceso, final Date fechaProcedimientoUsuarioProceso, final int inicio,
			final int tamanhoPagina) {
		super();
		this.procedimiento = procedimiento;
		this.usuario = usuario;
		this.proceso = proceso;
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public ProcedimientoEntity getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoEntity procedimiento) {
		this.procedimiento = procedimiento;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(final UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public ProcesoEntity getProceso() {
		return proceso;
	}

	public void setProceso(final ProcesoEntity proceso) {
		this.proceso = proceso;
	}

	public Date getFechaProcedimientoUsuarioProceso() {
		return fechaProcedimientoUsuarioProceso;
	}

	public void setFechaProcedimientoUsuarioProceso(final Date fechaProcedimientoUsuarioProceso) {
		this.fechaProcedimientoUsuarioProceso = fechaProcedimientoUsuarioProceso;
	}

	public int getInicio() {
		return inicio;
	}

	public void setInicio(final int inicio) {
		this.inicio = inicio;
	}

	public int getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(final int tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioProcesoBuscar [procedimiento=" + procedimiento + ", usuario=" + usuario
				+ ", proceso=" + proceso + ", fechaProcedimientoUsuarioProceso=" + fechaProcedimientoUsuarioProceso
				+ ", inicio=" + inicio + ", tamanhoPagina=" + tamanhoPagina + "]";
	}

}
