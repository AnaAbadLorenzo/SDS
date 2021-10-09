package com.sds.utilidades;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory; 
import org.hibernate.cfg.Configuration;

public class HibernateUtilities {  
    private static final SessionFactory sessionFactory;   

    static { 
        try { 
            sessionFactory = new Configuration().configure().buildSessionFactory();            
        } catch (HibernateException he) { 
           System.err.println("Error en la inicializacion de la sesion: " + he); 
           throw new ExceptionInInitializerError(he); 
        } 
    }  

    public static SessionFactory getSessionFactory() { 
        return sessionFactory; 
    } 
}