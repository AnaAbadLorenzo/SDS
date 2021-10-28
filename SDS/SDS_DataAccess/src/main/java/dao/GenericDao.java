package dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import pojos.GenericPojo;

public interface GenericDao {

	public abstract void insertar(final GenericPojo pojo);

	public abstract void modificar(final GenericPojo pojo);

	public abstract void eliminar(final GenericPojo pojo);

	public abstract List buscarPorCriteria(final Class pojoClass, final DetachedCriteria criteria);

	public abstract List buscarTodos(final Class class1);
}
