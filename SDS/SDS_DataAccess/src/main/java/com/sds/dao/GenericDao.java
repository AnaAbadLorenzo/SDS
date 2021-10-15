package com.sds.dao;

import java.io.Serializable;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.sds.pojos.GenericPojo;

@Repository
public interface GenericDao {
	
	public abstract void insertar(GenericPojo pojo);
	public abstract void modificar(GenericPojo pojo);
	public abstract void eliminar(GenericPojo pojo);
	public abstract List buscarCriteria(DetachedCriteria criteria);
}
