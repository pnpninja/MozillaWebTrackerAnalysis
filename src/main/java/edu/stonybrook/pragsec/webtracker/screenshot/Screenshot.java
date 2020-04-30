package edu.stonybrook.pragsec.webtracker.screenshot;

import edu.stonybrook.pragsec.webtracker.dao.WebsiteDetailDAO;
import edu.stonybrook.pragsec.webtracker.database.DBOperator;
import edu.stonybrook.pragsec.webtracker.models.WebsiteDetail;
import edu.stonybrook.pragsec.webtracker.utils.ScreenshotPC;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

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

		final WebsiteDetailDAO websiteDetailDao = DBOperator.getInstance();
		// takeScreenshotNormal("http://automationtesting.in", false);
		// WebsiteDetail temp = new WebsiteDetail();
		// temp.setURL("http://automationtesting.in");
		// websiteDetailDao.persist(temp);
		// takeScreenshotNormal("http://www.w3schools.com/js/default.asp", true);
//		List<WebsiteDetail> t = websiteDetailDao.getAllWithoutScreenshot();
//		for(WebsiteDetail website : t) {
//			try {
//				takeScreenshotNormal(website, false);
//				takeScreenshotNormal(website, true);
//				website.setScreenshot_taken(true);
//				websiteDetailDao.update(website);
//			}catch (Exception e) {
//				logger.error("Unable to process URL - "+website.getURL()+". Reason - "+e.getMessage(),e);
//			}
//			
//		}

		final ScreenshotPC pc = new ScreenshotPC();
		while(true) {
			WebsiteDetail website = pc.getNextInList();
			if(website == null) {
				break;
			}else {
				try {
					takeScreenshotNormal(website, false);
					takeScreenshotNormal(website, true);
					website.setScreenshot_taken(true);
					website.setScreenshot_processed(true);
					websiteDetailDao.update(website);
				}catch (Exception e) {
					logger.error("Screenshot taking for - " + website.getURL() + " has timed out", e);
					website.setScreenshot_taken(false);
					website.setScreenshot_processed(true);
					websiteDetailDao.update(website);
				}
			}
		}
//		Thread t1 = new Thread(new Runnable() {		
//			@Override
//			public void run() {
//				while(true) {
//					WebsiteDetail website = pc.getNextInList();
//					try {
//						takeScreenshotNormal(website, false);
//						takeScreenshotNormal(website, true);
//						website.setScreenshot_taken(true);
//						websiteDetailDao.update(website);
//					}catch (Exception e) {
//						logger.error("Unable to process URL - "+website.getURL()+". Reason - "+e.getMessage(),e);
//					}
//				}				
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {		
//			@Override
//			public void run() {
//				while(true) {
//					WebsiteDetail website = pc.getNextInList();
//					try {
//						takeScreenshotNormal(website, false);
//						takeScreenshotNormal(website, true);
//						website.setScreenshot_taken(true);
//						websiteDetailDao.update(website);
//					}catch (Exception e) {
//						logger.error("Unable to process URL - "+website.getURL()+". Reason - "+e.getMessage(),e);
//					}
//				}
//			}
//		});
//		Thread t3 = new Thread(new Runnable() {		
//			@Override
//			public void run() {
//				while(true) {
//					WebsiteDetail website = pc.getNextInList();
//					try {
//						takeScreenshotNormal(website, false);
//						takeScreenshotNormal(website, true);
//						website.setScreenshot_taken(true);
//						websiteDetailDao.update(website);
//					}catch (Exception e) {
//						logger.error("Unable to process URL - "+website.getURL()+". Reason - "+e.getMessage(),e);
//					}
//				}
//			}
//		});
//		Thread t4 = new Thread(new Runnable() {		
//			@Override
//			public void run() {
//				while(true) {
//					WebsiteDetail website = pc.getNextInList();
//					try {
//						takeScreenshotNormal(website, false);
//						takeScreenshotNormal(website, true);
//						website.setScreenshot_taken(true);
//						websiteDetailDao.update(website);
//					}catch (Exception e) {
//						logger.error("Unable to process URL - "+website.getURL()+". Reason - "+e.getMessage(),e);
//					}
//				}
//			}
//		});
//		t1.start(); 
//        t2.start();
//        t3.start(); 
//        t4.start();
//        t1.join(); 
//        t2.join();
//        t3.join(); 
//        t4.join();
		// System.out.println(t.size());

//		Thread t1 = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (true) {
//					final WebsiteDetail website = pc.getNextInList();
//					ExecutorService executor = Executors.newFixedThreadPool(1);
//					Future<?> future = executor.submit(new Runnable() {
//						@Override
//						public void run() {
//							try {
//								takeScreenshotNormal(website, false);
//								takeScreenshotNormal(website, true);
//								website.setScreenshot_taken(true);
//								website.setScreenshot_processed(true);
//								websiteDetailDao.update(website);
//							} catch (InterruptedException e) {
//								logger.error("Screenshot taking for - " + website.getURL() + " has timed out", e);
//								website.setScreenshot_taken(false);
//								website.setScreenshot_processed(true);
//								websiteDetailDao.update(website);
//							}
//						}
//					});
//					executor.shutdown();
//					try {
//						if (!executor.awaitTermination(150, TimeUnit.SECONDS)) {
//							logger.info("Too much time taken for processing screnshot");
//							executor.shutdownNow();
//						}
//					} catch (InterruptedException e) {
//						logger.error("DEEP ERROR - Location 1");
//						logger.error(e.getMessage());
//
//					}
//				}
//
//			}
//		});
//		Thread t2 = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (true) {
//					final WebsiteDetail website = pc.getNextInList();
//					ExecutorService executor = Executors.newFixedThreadPool(1);
//					Future<?> future = executor.submit(new Runnable() {
//						@Override
//						public void run() {
//							try {
//								takeScreenshotNormal(website, false);
//								takeScreenshotNormal(website, true);
//								website.setScreenshot_taken(true);
//								website.setScreenshot_processed(true);
//								websiteDetailDao.update(website);
//							} catch (InterruptedException e) {
//								logger.error("Screenshot taking for - " + website.getURL() + " has timed out", e);
//								website.setScreenshot_taken(false);
//								website.setScreenshot_processed(true);
//								websiteDetailDao.update(website);
//							}
//						}
//					});
//					executor.shutdown();
//					try {
//						if (!executor.awaitTermination(150, TimeUnit.SECONDS)) {
//							logger.info("Too much time taken for processing screnshot");
//							executor.shutdownNow();
//						}
//					} catch (InterruptedException e) {
//						logger.error("DEEP ERROR - Location 1");
//						logger.error(e.getMessage());
//
//					}
//				}
//
//			}
//		});
//		t1.start();
//		t2.start();
//
//		t1.join();
//		t2.join();
	}

	public static void takeScreenshotNormal(WebsiteDetail wd, boolean trackingProtectionEnabled)
			throws InterruptedException {
		String imageName = wd.getURL();
		if (imageName == null) {
			return;
		}
		// ProfilesIni profileIni = new ProfilesIni();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("privacy.trackingprotection.enabled", trackingProtectionEnabled ? true : false);
		profile.setPreference("browser.contentblocking.category", trackingProtectionEnabled ? "strict" : "standard");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver(options);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		try {
			driver.get("http://" + wd.getURL());
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, 100, true)
					.withName(imageName + "_tracking_" + (trackingProtectionEnabled ? "enabled" : "disabled")).save();
			if (trackingProtectionEnabled) {
				wd.setTracking_enabled_screenshot_location("/Users/prateek/Workspace/webtracker/screenshots/"
						+ imageName + "_tracking_" + (trackingProtectionEnabled ? "enabled" : "disabled") + ".png");
			} else {
				wd.setTracking_disabled_screenshot_location("/Users/prateek/Workspace/webtracker/screenshots/"
						+ imageName + "_tracking_" + (trackingProtectionEnabled ? "enabled" : "disabled") + ".png");
			}

			Thread.sleep(5000);
			driver.quit();
		} catch (Exception e) {
			driver.quit();
			throw e;
		}

	}

}
