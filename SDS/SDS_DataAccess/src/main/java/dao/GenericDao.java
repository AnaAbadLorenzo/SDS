package dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import pojos.GenericPojo;

@Repository
public interface GenericDao {

	public abstract void insertar(final GenericPojo pojo);

	public abstract void modificar(final GenericPojo pojo);

	public abstract void eliminar(final GenericPojo pojo);

	public abstract List buscarPorCriteria(final Class pojoClass, final DetachedCriteria criteria);
}
