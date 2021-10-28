package pojos;

import java.util.Set;

public class Empresa extends GenericPojo {

	private static final long serialVersionUID = 1L;

	private Integer idEmpresa;
	private String cifEmpresa;
	private String nombreEmpresa;
	private String direccionEmpresa;
	private String telefonoEmpresa;
	private String borradoEmpresa;

	private Set<Persona> personas;

	public Empresa() {
		super();
	}

	public Empresa(final Integer idEmpresa, final String cifEmpresa, final String nombreEmpresa,
			final String direccionEmpresa, final String telefonoEmpresa, final String borradoEmpresa) {
		super();
		this.idEmpresa = idEmpresa;
		this.cifEmpresa = cifEmpresa;
		this.nombreEmpresa = nombreEmpresa;
		this.direccionEmpresa = direccionEmpresa;
		this.telefonoEmpresa = telefonoEmpresa;
		this.borradoEmpresa = borradoEmpresa;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(final Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getCifEmpresa() {
		return cifEmpresa;
	}

	public void setCifEmpresa(final String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(final String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getDireccionEmpresa() {
		return direccionEmpresa;
	}

	public void setDireccionEmpresa(final String direccionEmpresa) {
		this.direccionEmpresa = direccionEmpresa;
	}

	public String getBorradoEmpresa() {
		return borradoEmpresa;
	}

	public void setBorradoEmpresa(final String borradoEmpresa) {
		this.borradoEmpresa = borradoEmpresa;
	}

	public String getTelefonoEmpresa() {
		return telefonoEmpresa;
	}

	public void setTelefonoEmpresa(final String telefonoEmpresa) {
		this.telefonoEmpresa = telefonoEmpresa;
	}

	public Set<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(final Set<Persona> personas) {
		this.personas = personas;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", cifEmpresa=" + cifEmpresa + ", nombreEmpresa=" + nombreEmpresa
				+ ", direccionEmpresa=" + direccionEmpresa + ", telefonoEmpresa=" + telefonoEmpresa
				+ ", borradoEmpresa=" + borradoEmpresa + "]";
	}

}
