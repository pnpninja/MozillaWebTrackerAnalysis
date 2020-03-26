package edu.stonybrook.pragsec.webtracker.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;

public class WebsiteDetailDAOImplTest {
	
	private static final String PERSISTENCE_UNIT_NAME = "webtracker";
	private WebsiteDetailDAO websiteDetailDAO;
	private EntityManagerFactory entityManagerFactory;
	
	@BeforeEach
	public void init() {
		System.out.println("HERE!!!");
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		websiteDetailDAO = new WebsiteDetailDAOImpl(entityManagerFactory);
	}
	
	@Test
	public void testPersist() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		websiteDetailDAO = new WebsiteDetailDAOImpl(entityManagerFactory);
		WebsiteDetail websiteDetail = new WebsiteDetail();
		websiteDetail.setURL("www.google.com");
		Long wdId = websiteDetailDAO.persist(websiteDetail);
		Assert.notNull(wdId);
		
	}
	
	@Test
	public void testFetch() {
		WebsiteDetail websiteDetail = websiteDetailDAO.getByURL("www.google.com");
		Assert.isTrue(websiteDetail.getURL().equals("www.google.com"));
	}

}
