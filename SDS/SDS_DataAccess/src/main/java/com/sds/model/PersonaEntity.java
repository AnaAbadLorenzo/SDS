package com.sds.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "persona")
public class PersonaEntity {

	@Id
	@Column(name = "dni_persona")
	private String dniP;

	@Column(name = "nombre_persona")
	private String nombreP;

	@Column(name = "apellidos_persona")
	private String apellidosP;

	@Column(name = "fecha_nac_persona")
	private Date fechaNacP;

	@Column(name = "direccion_persona")
	private String direccionP;

	@Column(name = "telefono_persona")
	private String telefonoP;

	@Column(name = "email_persona")
	private String emailP;

	@Column(name = "borrado_persona")
	private Integer borradoP;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
	private EmpresaEntity empresa;

	@OneToOne
	@JoinColumn(name = "dni_persona", referencedColumnName = "dni_usuario", updatable = false)
	private UsuarioEntity usuario;

	public PersonaEntity() {
		super();
	}

	public PersonaEntity(final String dniP, final String nombreP, final String apellidosP, final Date fechaNacP,
			final String direccionP, final String telefonoP, final String emailP, final Integer borradoP,
			final EmpresaEntity empresa, final UsuarioEntity usuario) {
		super();
		this.dniP = dniP;
		this.nombreP = nombreP;
		this.apellidosP = apellidosP;
		this.fechaNacP = fechaNacP;
		this.direccionP = direccionP;
		this.telefonoP = telefonoP;
		this.emailP = emailP;
		this.borradoP = borradoP;
		this.empresa = empresa;
		this.usuario = usuario;
	}

	public String getDniP() {
		return dniP;
	}

	public void setDniP(final String dniP) {
		this.dniP = dniP;
	}

	public String getNombreP() {
		return nombreP;
	}

	public void setNombreP(final String nombreP) {
		this.nombreP = nombreP;
	}

	public String getApellidosP() {
		return apellidosP;
	}

	public void setApellidosP(final String apellidosP) {
		this.apellidosP = apellidosP;
	}

	public Date getFechaNacP() {
		return fechaNacP;
	}

	public void setFechaNacP(final Date fechaNacP) {
		this.fechaNacP = fechaNacP;
	}

	public String getDireccionP() {
		return direccionP;
	}

	public void setDireccionP(final String direccionP) {
		this.direccionP = direccionP;
	}

	public String getTelefonoP() {
		return telefonoP;
	}

	public void setTelefonoP(final String telefonoP) {
		this.telefonoP = telefonoP;
	}

	public String getEmailP() {
		return emailP;
	}

	public void setEmailP(final String emailP) {
		this.emailP = emailP;
	}

	public Integer getBorradoP() {
		return borradoP;
	}

	public void setBorradoP(final Integer borradoP) {
		this.borradoP = borradoP;
	}

	public EmpresaEntity getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final EmpresaEntity empresa) {
		this.empresa = empresa;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(final UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "PersonaEntity [dniP=" + dniP + ", nombreP=" + nombreP + ", apellidosP=" + apellidosP + ", fechaNacP="
				+ fechaNacP + ", direccionP=" + direccionP + ", telefonoP=" + telefonoP + ", emailP=" + emailP
				+ ", borradoP=" + borradoP + "]";
	}

}
