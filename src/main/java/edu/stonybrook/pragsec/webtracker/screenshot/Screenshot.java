package edu.stonybrook.pragsec.webtracker.screenshot;

import edu.stonybrook.pragsec.webtracker.utils.URLUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;


//browser.contentblocking.category
public class Screenshot {
	static Logger logger = LoggerFactory.getLogger(Screenshot.class);
	
	public static void main(String[] args) throws InterruptedException {
		takeScreenshotNormal("http://automationtesting.in", false);
	}
	
	public static void takeScreenshotNormal(String url, boolean trackingProtectionEnabled) throws InterruptedException {
		String imageName = URLUtils.getValidUrl(url);
		if(imageName == null) {
			return;
		}
		
		ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("default");
		profile.setPreference("browser.contentblocking.category", trackingProtectionEnabled ? "strict" : "standard" );
		FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver(options);
		
		driver.get(url);
		Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS,500,true).withName(imageName+"_tracking_"+(trackingProtectionEnabled ? "enabled" : "disabled")).save();
		
		Thread.sleep(5000);
		driver.quit();
	}
	
}
