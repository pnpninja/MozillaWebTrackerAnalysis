package edu.stonybrook.pragsec.webtracker.logfetcher;

import edu.stonybrook.pragsec.webtracker.utils.URLUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.logging.Level;

public class LogFetcher {
    static Logger logger = LoggerFactory.getLogger(edu.stonybrook.pragsec.webtracker.screenshot.LogFetcher.class);


    public static void main(String args[]) throws InterruptedException {
        logger.info("Running for Chrome Browser");
        fetchConsoleLogsChrome("https://www.buzzfeed.com", false);
        logger.info("Running for firefox Browser");
        fetchConsoleLogsFirefox("https://www.buzzfeed.com", false);
    }

    private static void fetchConsoleLogsChrome(String url, boolean trackingProtectionEnabled) throws InterruptedException{
        String validUrl = URLUtils.getValidUrl(url);

        if(validUrl == null)
            return;

        System.setProperty("webdriver.chrome.driver", "/home/ppatni/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver(getDesiredCapabilites("chrome"));

        driver.get(url);
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        Thread.sleep(5000);
        for(LogEntry entry : logEntries) {
            logger.info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }

        driver.quit();
    }

    private static void fetchConsoleLogsFirefox(String url, boolean trackingProtectionEnabled) throws InterruptedException{

        //Error in geckodriver (open issue) - https://stackoverflow.com/questions/59026421/python-selenium-unable-to-get-browser-console-logs

        String validUrl = URLUtils.getValidUrl(url);

        if(validUrl == null)
            return;

        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("default");
        profile.setPreference("browser.contentblocking.category", trackingProtectionEnabled ? "strict" : "standard" );
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        WebDriverManager.firefoxdriver().setup();
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        options.merge(getDesiredCapabilites("firefox"));
        WebDriver driver = new FirefoxDriver(options);

        driver.get(url);
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        Thread.sleep(5000);
        for(LogEntry entry : logEntries) {
            logger.info(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }

        driver.quit();


    }

    private static DesiredCapabilities getDesiredCapabilites(String browser) {
        DesiredCapabilities caps = browser.equals("chrome") ? DesiredCapabilities.chrome() : DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        return caps;
    }
}
