package edu.stonybrook.pragsec.webtracker.screenshot;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.MalformedURLException;
import java.net.URL;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import edu.stonybrook.pragsec.webtracker.utils.URLUtils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Screenshot {
	static Logger logger = LoggerFactory.getLogger(Screenshot.class);
	
	public static void main(String[] args) throws InterruptedException {
		takeScreenshotNormal("http://automationtesting.in", true);
	}
	
	public static void takeScreenshotNormal(String url, boolean trackingProtectionEnabled) throws InterruptedException {
		if(!URLUtils.isValidUrl(url)) {
			return;
		}
		
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		
		driver.get(url);
		Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS,500,true).withName("Expected").save();
		
		Thread.sleep(5000);
		driver.quit();
	}
	
	
}
