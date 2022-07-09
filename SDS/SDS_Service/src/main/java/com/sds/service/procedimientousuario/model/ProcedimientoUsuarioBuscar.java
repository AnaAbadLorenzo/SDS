package com.sds.service.procedimientousuario.model;

import java.util.Date;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.UsuarioEntity;

public class ProcedimientoUsuarioBuscar {

	private Date fechaProcedimientoUsuario;
	private UsuarioEntity usuario;
	private ProcedimientoEntity procedimiento;
	private int inicio;
	private int tamanhoPagina;

	public ProcedimientoUsuarioBuscar() {
		super();
	}

	public ProcedimientoUsuarioBuscar(final Date fechaProcedimientoUsuario, final UsuarioEntity usuario,
			final ProcedimientoEntity procedimiento, final int inicio, final int tamanhoPagina) {
		super();
		this.fechaProcedimientoUsuario = fechaProcedimientoUsuario;
		this.usuario = usuario;
		this.procedimiento = procedimiento;
		this.inicio = inicio;
		this.tamanhoPagina = tamanhoPagina;
	}

	public Date getFechaProcedimientoUsuario() {
		return fechaProcedimientoUsuario;
	}

	public void setFechaProcedimientoUsuario(final Date fechaProcedimientoUsuario) {
		this.fechaProcedimientoUsuario = fechaProcedimientoUsuario;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(final UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public ProcedimientoEntity getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoEntity procedimiento) {
		this.procedimiento = procedimiento;
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
		return "ProcedimientoUsuarioBuscar [fechaProcedimientoUsuario=" + fechaProcedimientoUsuario + ", usuarioEntity="
				+ usuario + ", procedimiento=" + procedimiento + ", inicio=" + inicio + ", tamanhoPagina="
				+ tamanhoPagina + "]";
	}

}
