package edu.stonybrook.pragsec.webtracker.dao;

import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;


public interface WebsiteDetailDAO {
	WebsiteDetail getByURL(String url);
	WebsiteDetail getById(Long id);
	Long persist(WebsiteDetail websiteDetail);
}
