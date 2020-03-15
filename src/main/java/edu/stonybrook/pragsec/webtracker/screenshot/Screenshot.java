package edu.stonybrook.pragsec.webtracker.screenshot;

import edu.stonybrook.pragsec.webtracker.database.comms.ApplicationDBDetails;
import edu.stonybrook.pragsec.webtracker.database.comms.SingletonDB;
import edu.stonybrook.pragsec.webtracker.utils.URLUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;


//browser.contentblocking.category
@ComponentScan("edu.stonybrook.pragsec.webtracker")
public class Screenshot {
	static Logger logger = LoggerFactory.getLogger(Screenshot.class);
	
	public static void main(String[] args) throws InterruptedException {
		//ApplicationContext ctx = new AnnotationConfigApplicationContext(SingletonDB.class);
		//ApplicationDBDetails details = ctx.getBean(ApplicationDBDetails.class);
		//System.out.println(details.getName());
		takeScreenshotNormal("http://automationtesting.in", false);
		//takeScreenshotNormal("http://www.w3schools.com/js/default.asp", true);
	}
	
	public static void takeScreenshotNormal(String url, boolean trackingProtectionEnabled) throws InterruptedException {
		String imageName = URLUtils.getValidUrl(url);
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
		
		driver.get(url);
		Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS,500,true).withName(imageName+"_tracking_"+(trackingProtectionEnabled ? "enabled" : "disabled")).save();
		
		Thread.sleep(5000);
		driver.quit();
	}
	
}
