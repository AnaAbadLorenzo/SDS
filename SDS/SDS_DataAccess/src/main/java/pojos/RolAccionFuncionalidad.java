package pojos;

public class RolAccionFuncionalidad extends GenericPojo {

	private static final long serialVersionUID = 1L;

	private Integer idRol;
	private Integer idAccion;
	private Integer idFuncionalidad;

	public RolAccionFuncionalidad() {
		super();
	}

	public RolAccionFuncionalidad(final Integer idRol, final Integer idAccion, final Integer idFuncionalidad) {
		super();
		this.idRol = idRol;
		this.idAccion = idAccion;
		this.idFuncionalidad = idFuncionalidad;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(final Integer idRol) {
		this.idRol = idRol;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(final Integer idAccion) {
		this.idAccion = idAccion;
	}

	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}

	public void setIdFuncionalidad(final Integer idFuncionalidad) {
		this.idFuncionalidad = idFuncionalidad;
	}

	@Override
	public String toString() {
		return "RolAccionFuncionalidad [idRol=" + idRol + ", idAccion=" + idAccion + ", idFuncionalidad="
				+ idFuncionalidad + "]";
	}

}
