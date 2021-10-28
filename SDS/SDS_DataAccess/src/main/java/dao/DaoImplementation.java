package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import exception.DataBaseException;
import pojos.GenericPojo;
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
			final List result = criteria.getExecutableCriteria(session).list();
			return result;
		} catch (final Exception e) {
			e.printStackTrace();
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
