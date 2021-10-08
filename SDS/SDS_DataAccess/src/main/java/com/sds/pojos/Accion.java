package com.sds.pojos;

import java.util.Set;

public class Accion {

	public Integer idAccion;
	public String nombreAccion;
	public String descripAccion;
	public int borradoAccion;
	
	private Set<Rol> roles;
	private Set<Funcionalidad> funcionalidades;
	
	public Integer getIdAccion() {
		return idAccion;
	}
	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}
	public String getNombreAccion() {
		return nombreAccion;
	}
	public void setNombreAccion(String nombreAccion) {
		this.nombreAccion = nombreAccion;
	}
	public String getDescripAccion() {
		return descripAccion;
	}
	public void setDescripAccion(String descripAccion) {
		this.descripAccion = descripAccion;
	}
	public int getBorradoAccion() {
		return borradoAccion;
	}
	public void setBorradoAccion(int borradoAccion) {
		this.borradoAccion = borradoAccion;
	}
	
	public Set<Rol> getRoles(){
		return roles;
	}
	
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	public Set<Funcionalidad> getFuncionalidades(){
		return funcionalidades;
	}
	
	public void setFuncionalidades(Set<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	@Override
	public String toString() {
		return "Accion [idAccion=" + idAccion + 
				", nombreAccion=" + nombreAccion + 
				", descripAccion=" + descripAccion +
				", borradoAccion=" + borradoAccion + 
				", roles=" + roles + 
				", funcionalidades=" + funcionalidades
				+ "]";
	}
	
	
	
	
}
