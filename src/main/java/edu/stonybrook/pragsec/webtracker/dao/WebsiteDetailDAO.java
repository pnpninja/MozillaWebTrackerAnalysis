package edu.stonybrook.pragsec.webtracker.dao;

import java.util.List;

import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;


public interface WebsiteDetailDAO {
	WebsiteDetail getByURL(String url);
	WebsiteDetail getById(Long id);
	List<WebsiteDetail> getAllWithoutScreenshot();
	Long persist(WebsiteDetail websiteDetail);
	void update(WebsiteDetail websiteDetail);
}
