package com.myblog.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBUtil {
	private static Configuration cfg = new Configuration().configure();
	private static ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
			cfg.getProperties()).build();
	private static SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	
	public Session getCurrentSession(){
		Session session = sessionFactory.getCurrentSession();
		return session;
	}
	
	public void closeSession(Session session){
		session.close();
	}
	
	public void closeSessionFactory(){
		sessionFactory.close();
	}
}
