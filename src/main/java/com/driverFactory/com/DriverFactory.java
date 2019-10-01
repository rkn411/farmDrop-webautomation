package com.driverFactory.com;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.ErrorHandler;

public class DriverFactory {
	public static long DEFAULT_WAIT = 20;
	protected static WebDriver driver = null;
	static String currentPath = System.getProperty("user.dir");
	static Properties prop = new Properties();
	static DesiredCapabilities capability = null;

	/**
	 * By default to web driver will be chrome Override it by passing
	 * -Dbrowser=firefox to the command line arguments
	 * 
	 * @param capabilities
	 * @return webdriver
	 */
	private static WebDriver chooseDriver(DesiredCapabilities capabilities) {
		String preferredDriver = System.getProperty("browser", "chrome");
		boolean headless = System.getProperty("headless", "false").equals("true");
		switch (preferredDriver.toLowerCase()) {
		case "firfox":
			FirefoxOptions options = new FirefoxOptions();
			if (headless) {
				options.addArguments("-headless", "-safe-mode");
			}
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			try {
				driver = new FirefoxDriver(capabilities);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
			return driver;
		default:
			final ChromeOptions chromeOptions = new ChromeOptions();
			if (headless) {
				chromeOptions.addArguments("--headless");
			}
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			try {
				driver = new ChromeDriver(capabilities);
				ErrorHandler handler = new ErrorHandler();
				handler.setIncludeServerErrors(false);
				// driver.setErrorHandler(handler);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
			return driver;
		}
	}

	/**
	 * Method to go with default driver
	 * 
	 * @return
	 */

	public static WebDriver getDefaultDriver() {
		if (driver != null) {
			return driver;
		}
		String enviroment = "desktop";
		switch (enviroment) {
		case "desktop":
			DesiredCapabilities capabilities = null;
			capabilities = DesiredCapabilities.firefox();
			capabilities.setJavascriptEnabled(true);
			capabilities.setCapability("takesScreenshot", true);
			driver = chooseDriver(capabilities);
			driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("\nException : Invalid platform " + enviroment);
			System.exit(0);
		}

		return driver;
	}

	/**
	 * Method to close driver
	 */

	public static void closeDriver() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (NoSuchMethodError nsme) {
			} catch (NoSuchSessionException nsse) {
			} catch (SessionNotCreatedException snce) {
			}
			driver = null;
		}
	}
}
