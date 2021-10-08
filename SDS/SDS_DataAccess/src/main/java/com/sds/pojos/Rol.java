package com.sds.pojos;

import java.util.Set;

public class Rol {

	public Integer idRol;
	public String rolName;
	public String rolDescription;
	
	private Set<Accion> acciones;
	private Set<Funcionalidad> funcionalidades;
	private Set<Usuario> usuarios;
	
	public Rol() {
		
	}
	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	
	public String getRolDescription() {
		return rolDescription;
	}
	public void setRolDescription(String rolDescription) {
		this.rolDescription = rolDescription;
	}
	
	public Set<Accion> getAcciones(){
		return acciones;
	}
	
	public void setAcciones(Set<Accion> acciones) {
		this.acciones = acciones;
	}
	
	public Set<Funcionalidad> getFuncionalidades(){
		return funcionalidades;
	}
	
	public void setFuncionalidades(Set<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}
	
	public Set<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + 
				", rolName=" + rolName +
				", acciones=" + acciones + 
				", funcionalidades=" + funcionalidades + 
				", usuarios=" + usuarios + "]";
	}
	
}
