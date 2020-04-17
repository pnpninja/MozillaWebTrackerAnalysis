package edu.stonybrook.pragsec.webtracker.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.stonybrook.pragsec.webtracker.dao.WebsiteDetailDAO;
import edu.stonybrook.pragsec.webtracker.database.DBOperator;
import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;

public class ScreenshotPC {
	static WebsiteDetailDAO websiteDetailDao = DBOperator.getInstance();
	List<WebsiteDetail> processList;
	static Logger logger = LoggerFactory.getLogger(ScreenshotPC.class);
	
	public ScreenshotPC() {
		getNext10WebsiteDetails();
	}
	
	public synchronized WebsiteDetail getNextInList() {
		if(processList.isEmpty()) {
			getNext10WebsiteDetails();
			if(processList.isEmpty()) {
				return null;
			}else {
				return processList.remove(0);
			}
		}else {
			return processList.remove(0);
		}
	}
	
	private void getNext10WebsiteDetails() {
		logger.info("Fetching next 10 websites.....");
		processList = websiteDetailDao.getAllWithoutScreenshotWithLimit(10);
	}

}
