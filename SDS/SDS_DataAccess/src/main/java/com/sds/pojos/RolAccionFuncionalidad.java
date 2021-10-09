package com.sds.pojos;

public class RolAccionFuncionalidad extends GenericPojo{
	
	private static final long serialVersionUID = 1L;
	public Integer idRol;
	public Integer idAccion;
	public Integer idFuncionalidad;
	
	
	public Integer getIdRol() {
		return idRol;
	}
	
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}
	
	public Integer getIdAccion() {
		return idAccion;
	}
	
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}
	
	public Integer getIdFuncionalidad() {
		return idFuncionalidad;
	}
	
	public void setIdFuncionalidad(Integer idFuncionalidad) {
		this.idFuncionalidad= idFuncionalidad;
	}

	@Override
	public String toString() {
		return "RolAccionFuncionalidad [idRol=" + idRol + 
				", idAccion=" + idAccion + 
				", idFuncionalidad=" + 
				idFuncionalidad + "]";
	}
	
	
	


}
