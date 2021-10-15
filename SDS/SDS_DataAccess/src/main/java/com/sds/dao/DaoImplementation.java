package com.sds.dao;


import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;

import java.util.ArrayList;
import java.util.List;

import com.sds.pojos.GenericPojo;
import com.sds.utilidades.HibernateUtilities;

public class DaoImplementation implements GenericDao{
	private Session session;
	private Transaction transaction;
	
	public void insertar(GenericPojo pojo)throws HibernateException {
		
		try {
			iniciaOperacion();
			session.save(pojo);
			transaction.commit();
		}catch(HibernateException exception) {
			transaction.rollback();
			throw new HibernateException("Ha ocurrido un error durante la inserccion", exception);
		}finally {
			session.close();
		}
	}
	
	public void modificar(GenericPojo pojo) {
		try {
			iniciaOperacion();
			session.update(pojo);
			transaction.commit();
		}catch(HibernateException exception) {
			transaction.rollback();
			throw new HibernateException("Ha ocurrido un error durante la modificacion", exception);
		}finally {
			session.close();
		}
	}
	
	public void eliminar (GenericPojo pojo) {
		
		try {
			iniciaOperacion();
			session.delete(pojo);
			transaction.commit();
		}catch(HibernateException exception) {
			transaction.rollback();
			throw new HibernateException("Ha ocurrido un error en la eliminacion: ", exception);
		}finally {
			session.close();
		}
		
	}
	
	public List buscarCriteria(DetachedCriteria criteria){
		List lista = new ArrayList<>();
		lista = criteria.getExecutableCriteria(session).list();
		
		return lista;
	}
	
	private void iniciaOperacion() throws HibernateException 
    { 
        session = HibernateUtilities.getSessionFactory().openSession(); 
        transaction = session.beginTransaction(); 
    }  

}
