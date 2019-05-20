package base;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utils.DriverUtil;

public class BaseClass {
	DriverUtil driverUtil = new DriverUtil();
	public static WebDriver driver;

	@BeforeSuite
	public void initialiseSuite() {
		String baseUrl = "http://way2automation.com/way2auto_jquery/index.php";
		driver = driverUtil.getChromeDriver(baseUrl);
	}

	@AfterSuite
	public void tearDown() {
		driverUtil.destroyDriver();
	}
}
