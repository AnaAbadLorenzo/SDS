package pojos;

public class Usuario extends GenericPojo {

	private static final long serialVersionUID = 1L;

	private String dniUsuario;
	private String usuario;
	private String passwdUsuario;
	private Integer borradoUsuario;

	private Rol rol;
	private Persona persona;

	public Usuario() {
		super();
	}

	public Usuario(final String dniUsuario, final String usuario, final String passwdUsuario,
			final Integer borradoUsuario, final Rol rol, final Persona persona) {
		super();
		this.dniUsuario = dniUsuario;
		this.usuario = usuario;
		this.passwdUsuario = passwdUsuario;
		this.borradoUsuario = borradoUsuario;
		this.rol = rol;
		this.persona = persona;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public void setDniUsuario(final String dniUsuario) {
		this.dniUsuario = dniUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(final String usuario) {
		this.usuario = usuario;
	}

	public String getPasswdUsuario() {
		return passwdUsuario;
	}

	public void setPasswdUsuario(final String passwdUsuario) {
		this.passwdUsuario = passwdUsuario;
	}

	public int getBorradoUsuario() {
		return borradoUsuario;
	}

	public void setBorradoUsuario(final Integer borradoUsuario) {
		this.borradoUsuario = borradoUsuario;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(final Rol rol) {
		this.rol = rol;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(final Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Usuario [dniUsuario=" + dniUsuario + ", usuario=" + usuario + ", passwdUsuario=" + passwdUsuario
				+ ", borradoUsuario=" + borradoUsuario + "]";
	}

}