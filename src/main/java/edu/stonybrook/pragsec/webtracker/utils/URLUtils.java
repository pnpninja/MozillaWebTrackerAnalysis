package edu.stonybrook.pragsec.webtracker.utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.stonybrook.pragsec.webtracker.screenshot.Screenshot;

public class URLUtils {
	
	static Logger logger = LoggerFactory.getLogger(URLUtils.class);
	
	public static String getValidUrl(String url) {
		logger.info("Validating URL - "+url);
		try {
			URL urlObj = new URL(url);
			logger.info("URL - "+url+" - GOOD");
			return urlObj.getHost();
		}catch(MalformedURLException e) {
			logger.error("URL - "+url+" - BAD. Message - "+e.getMessage());
			return null;
		}
	}

}
