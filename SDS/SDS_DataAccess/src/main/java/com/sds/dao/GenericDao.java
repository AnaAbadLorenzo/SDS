package com.sds.dao;

import java.io.Serializable;

import com.sds.pojos.GenericPojo;

public interface GenericDao {
	
	public abstract void insertar(GenericPojo pojo);
	public abstract void modificar(GenericPojo pojo);
	public abstract void eliminar(GenericPojo pojo);
}
