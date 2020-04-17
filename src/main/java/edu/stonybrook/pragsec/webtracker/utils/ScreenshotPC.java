package edu.stonybrook.pragsec.webtracker.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.stonybrook.pragsec.webtracker.dao.WebsiteDetailDAO;
import edu.stonybrook.pragsec.webtracker.database.DBOperator;
import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;
import edu.stonybrook.pragsec.webtracker.screenshot.Screenshot;

public class ScreenshotPC {
	WebsiteDetailDAO websiteDetailDao = DBOperator.getInstance();
	List<WebsiteDetail> processList;
	static Logger logger = LoggerFactory.getLogger(ScreenshotPC.class);
	
	public ScreenshotPC() {
		getNext10WebsiteDetails();
	}
	
	public synchronized WebsiteDetail getNextInList() {
		if(this.processList.isEmpty()) {
			getNext10WebsiteDetails();
			if(this.processList.isEmpty()) {
				return null;
			}else {
				return this.processList.remove(0);
			}
		}else {
			return this.processList.remove(0);
		}
	}
	
	private void getNext10WebsiteDetails() {
		logger.info("Fetching next 10 websites.....");
		this.processList = websiteDetailDao.getAllWithoutScreenshotWithLimit(10);
	}

}
