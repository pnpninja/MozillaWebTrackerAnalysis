package edu.stonybrook.pragsec.webtracker.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.stonybrook.pragsec.webtracker.dao.*;


public class DBOperator {
	
	private static DBOperator single_instance = null;
	private static final String PERSISTENCE_UNIT_NAME = "webtracker";
	
	public WebsiteDetailDAO websiteDetailDAO;
	public EntityManagerFactory entityManagerFactory;
	
	private DBOperator() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		websiteDetailDAO = new WebsiteDetailDAOImpl(entityManagerFactory);
	}
	
	public synchronized static WebsiteDetailDAO getInstance() {
		if(single_instance == null)
			single_instance = new DBOperator();
		return single_instance.websiteDetailDAO;
	}

}
