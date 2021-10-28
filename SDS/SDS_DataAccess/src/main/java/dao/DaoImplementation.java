package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import exception.DataBaseException;
import pojos.GenericPojo;
import utilidades.Collections;
import utilidades.HibernateUtilities;

public class DaoImplementation implements GenericDao {

	private Session session;
	private Transaction transaction;

	@Override
	public void insertar(final GenericPojo pojo) throws HibernateException {

		try {
			iniciaOperacion();
			session.save(pojo);
			transaction.commit();
		} catch (final HibernateException exception) {
			transaction.rollback();
			throw new HibernateException("Ha ocurrido un error durante la inserccion", exception);
		} finally {
			session.close();
		}
	}

	@Override
	public void modificar(final GenericPojo pojo) {
		try {
			iniciaOperacion();
			session.update(pojo);
			transaction.commit();
		} catch (final HibernateException exception) {
			transaction.rollback();
			throw new HibernateException("Ha ocurrido un error durante la modificacion", exception);
		} finally {
			session.close();
		}
	}

	@Override
	public void eliminar(final GenericPojo pojo) {

		try {
			iniciaOperacion();
			session.delete(pojo);
			transaction.commit();
		} catch (final HibernateException exception) {
			transaction.rollback();
			throw new HibernateException("Ha ocurrido un error en la eliminacion: ", exception);
		} finally {
			session.close();
		}
	}

	@Override
	public List buscarPorCriteria(final Class pojoClass, final DetachedCriteria criteria) {
		try {
			iniciaOperacion();
			final List result = Collections.castList(pojoClass, criteria.getExecutableCriteria(session).list());
			return result;
		} catch (final Exception e) {
			e.printStackTrace();
			throw new DataBaseException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List buscarTodos(final Class pojoClass) {
		try {
			iniciaOperacion();
			final String tableName = session.getSessionFactory().getClassMetadata(pojoClass).getEntityName();
			final String hql = (new StringBuilder("select x from ")).append(tableName).append(" x").toString();
			final List result = buscarPorHql(pojoClass, hql);
			return (result != null ? result : new ArrayList());
		} catch (final Exception e) {
			throw new DataBaseException(e);
		} finally {
			// sesion.close();
		}
	}

	private List buscarPorHql(final Class pojoClass, final String hql) {
		final List result = Collections.castList(pojoClass, session.createQuery(hql).list());
		if (result.size() == 0)
			return null;
		try {
			return result;
		} catch (final Exception e) {
			throw new DataBaseException(e);
		} finally {
			session.close();
		}
	}

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtilities.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

}
