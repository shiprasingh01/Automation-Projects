package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverUtil {

	private static WebDriver driver; // private static

	public WebDriver getChromeDriver(String baseUrl) {

		String driverpath = "C:\\Automation\\Selenium\\Executables\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverpath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		return driver;
	}

	public void destroyDriver() {
		driver.close();
		driver.quit();
	}
}
