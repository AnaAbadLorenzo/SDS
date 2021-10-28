package pojos;

import java.util.Date;

public class Persona extends GenericPojo {

	private static final long serialVersionUID = 1L;

	private String dniP;
	private String nombreP;
	private String apellidosP;
	private Date fechaNacP;
	private String direccionP;
	private String telefonoP;
	private String emailP;
	private Integer borradoP;

	private Empresa empresa;
	private Usuario usuario;

	public Persona() {
		super();
	}

	public Persona(final String dniP, final String nombreP, final String apellidosP, final Date fechaNacP,
			final String direccionP, final String telefonoP, final String emailP, final Integer borradoP,
			final Empresa empresa, final Usuario usuario) {
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

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(final Empresa empresa) {
		this.empresa = empresa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(final Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Persona [dniP=" + dniP + ", nombreP=" + nombreP + ", apellidosP=" + apellidosP + ", fechaNacP="
				+ fechaNacP + ", direccionP=" + direccionP + ", telefonoP=" + telefonoP + ", emailP=" + emailP
				+ ", borradoP=" + borradoP + "]";
	}

}
