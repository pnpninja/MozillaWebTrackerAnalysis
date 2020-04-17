package edu.stonybrook.pragsec.webtracker.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;

public class WebsiteDetailDAOImpl implements WebsiteDetailDAO {

	private EntityManagerFactory entityManagerFactory;
	
	public WebsiteDetailDAOImpl(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	public WebsiteDetail getByURL(String url) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        WebsiteDetail websiteDetail = null;
        
        try {
        	transaction.begin();
            Query query = entityManager.createQuery("from WebsiteDetail wd WHERE wd.URL = :url");
            query.setParameter("url", url);
            websiteDetail = (WebsiteDetail) query.getSingleResult();
        }finally {
        	transaction.commit();
            entityManager.close();
		}
     
        return websiteDetail;
	}

	public WebsiteDetail getById(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        WebsiteDetail websiteDetail = entityManager.find(WebsiteDetail.class, id);

        transaction.commit();
        entityManager.close();

        return websiteDetail;
	}

	public Long persist(WebsiteDetail websiteDetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(websiteDetail);

        transaction.commit();
        entityManager.close();

        return websiteDetail.getId();
	}

	@Override
	public List<WebsiteDetail> getAllWithoutScreenshot() {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<WebsiteDetail> websiteDetail = new ArrayList<WebsiteDetail>();
        
        try {
        	transaction.begin();
            Query query = entityManager.createQuery("from WebsiteDetail wd WHERE wd.screenshot_taken = false");
            websiteDetail = (List<WebsiteDetail>)query.getResultList();
        }finally {
        	transaction.commit();
            entityManager.close();
		}
     
        return websiteDetail;
	}

	@Override
	public void update(WebsiteDetail websiteDetail) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.merge(websiteDetail);

        transaction.commit();
        entityManager.close();

        return;
		
	}

	@Override     
	public List<WebsiteDetail> getAllWithoutScreenshotWithLimit(int limit) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        List<WebsiteDetail> websiteDetail = new ArrayList<WebsiteDetail>();
        
        try {
        	transaction.begin();
            Query query = entityManager.createQuery("from WebsiteDetail wd WHERE wd.screenshot_taken = false AND wd.screenshot_processed = false");
            websiteDetail = (List<WebsiteDetail>)query.setMaxResults(limit).getResultList();
        }finally {
        	transaction.commit();
            entityManager.close();
		}
     
        return websiteDetail;
	}

}
