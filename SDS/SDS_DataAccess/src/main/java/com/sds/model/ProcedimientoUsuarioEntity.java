package com.sds.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "procedimientousuario")
public class ProcedimientoUsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_procedimiento_usuario")
	private Integer idProcedimientoUsuario;

	@Column(name = "puntuacion_procedimiento_usuario")
	private Integer puntuacionProcedimientoUsuario;

	@Column(name = "fecha_procedimiento_usuario")
	private Date fechaProcedimientoUsuario;

	@Column(name = "borrado_procedimiento_usuario")
	private Integer borradoProcedimientoUsuario;

	@OneToMany(cascade = CascadeType.ALL)
	private final Set<UsuarioEntity> usuarios = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	private ProcedimientoEntity procedimiento;

	public ProcedimientoUsuarioEntity() {
		super();
	}

	public ProcedimientoUsuarioEntity(final Integer idProcedimientoUsuario,
			final Integer puntuacionProcedimientoUsuario, final Date fechaProcedimientoUsuario,
			final Integer borradoProcedimientoUsuario, final ProcedimientoEntity procedimiento) {
		super();
		this.idProcedimientoUsuario = idProcedimientoUsuario;
		this.puntuacionProcedimientoUsuario = puntuacionProcedimientoUsuario;
		this.fechaProcedimientoUsuario = fechaProcedimientoUsuario;
		this.borradoProcedimientoUsuario = borradoProcedimientoUsuario;
		this.procedimiento = procedimiento;
	}

	public Integer getIdProcedimientoUsuario() {
		return idProcedimientoUsuario;
	}

	public void setIdProcedimientoUsuario(final Integer idProcedimientoUsuario) {
		this.idProcedimientoUsuario = idProcedimientoUsuario;
	}

	public Integer getPuntuacionProcedimientoUsuario() {
		return puntuacionProcedimientoUsuario;
	}

	public void setPuntuacionProcedimientoUsuario(final Integer puntuacionProcedimientoUsuario) {
		this.puntuacionProcedimientoUsuario = puntuacionProcedimientoUsuario;
	}

	public Date getFechaProcedimientoUsuario() {
		return fechaProcedimientoUsuario;
	}

	public void setFechaProcedimientoUsuario(final Date fechaProcedimientoUsuario) {
		this.fechaProcedimientoUsuario = fechaProcedimientoUsuario;
	}

	public Integer getBorradoProcedimientoUsuario() {
		return borradoProcedimientoUsuario;
	}

	public void setBorradoProcedimientoUsuario(final Integer borradoProcedimientoUsuario) {
		this.borradoProcedimientoUsuario = borradoProcedimientoUsuario;
	}

	public ProcedimientoEntity getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(final ProcedimientoEntity procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Set<UsuarioEntity> getUsuarios() {
		return usuarios;
	}

	@Override
	public String toString() {
		return "ProcedimientoUsuarioEntity [idProcedimientoUsuario=" + idProcedimientoUsuario
				+ ", puntuacionProcedimientoUsuario=" + puntuacionProcedimientoUsuario + ", fechaProcedimientoUsuario="
				+ fechaProcedimientoUsuario + ", borradoProcedimientoUsuario=" + borradoProcedimientoUsuario
				+ ", usuarios=" + usuarios + ", procedimiento=" + procedimiento + "]";
	}
}
