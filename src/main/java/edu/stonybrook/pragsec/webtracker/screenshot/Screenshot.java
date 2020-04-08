package edu.stonybrook.pragsec.webtracker.screenshot;


import edu.stonybrook.pragsec.webtracker.dao.WebsiteDetailDAO;
import edu.stonybrook.pragsec.webtracker.database.DBOperator;
import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;
import edu.stonybrook.pragsec.webtracker.utils.URLUtils;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;


//browser.contentblocking.category
public class Screenshot {
	static Logger logger = LoggerFactory.getLogger(Screenshot.class);
	
	public static void main(String[] args) throws InterruptedException {

		WebsiteDetailDAO websiteDetailDao = DBOperator.getInstance();
		//takeScreenshotNormal("http://automationtesting.in", false);
		//WebsiteDetail temp = new WebsiteDetail();
		//temp.setURL("http://automationtesting.in");
		//websiteDetailDao.persist(temp);
		//takeScreenshotNormal("http://www.w3schools.com/js/default.asp", true);
		List<WebsiteDetail> t = websiteDetailDao.getAllWithoutScreenshot();
		for(WebsiteDetail website : t) {
			try {
				takeScreenshotNormal(website, false);
				takeScreenshotNormal(website, true);
				website.setScreenshot_taken(true);
				websiteDetailDao.update(website);
			}catch (Exception e) {
				logger.error("Unable to process URL - "+website.getURL()+". Reason - "+e.getMessage(),e);
			}
			
		}
		//System.out.println(t.size());
	}
	
	public static void takeScreenshotNormal(WebsiteDetail wd, boolean trackingProtectionEnabled) throws InterruptedException {
		String imageName = wd.getURL();
		if(imageName == null) {
			return;
		}
		//ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("privacy.trackingprotection.enabled", trackingProtectionEnabled ? true : false );
		profile.setPreference("browser.contentblocking.category", trackingProtectionEnabled ? "strict" : "standard" );
		FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver(options);
		try {
			driver.get("https://"+wd.getURL());
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS,500,true).withName(imageName+"_tracking_"+(trackingProtectionEnabled ? "enabled" : "disabled")).save();
			if(trackingProtectionEnabled) {
				wd.setTracking_enabled_screenshot_location("/Users/prateek/Workspace/webtracker/screenshots/"+imageName+"_tracking_"+(trackingProtectionEnabled ? "enabled" : "disabled")+".png");
			}else {
				wd.setTracking_disabled_screenshot_location("/Users/prateek/Workspace/webtracker/screenshots/"+imageName+"_tracking_"+(trackingProtectionEnabled ? "enabled" : "disabled")+".png");
			}
			
			Thread.sleep(5000);
			driver.quit();
		}catch(Exception e) {
			driver.quit();
			throw e;
		}
		
		
	}
	
}
