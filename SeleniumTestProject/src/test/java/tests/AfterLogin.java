package tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.AlertPage;
import pages.DropDownPage;
import pages.DroppablePage;
import pages.SignInPage;
import pages.WidgetTabPage;

public class AfterLogin extends BaseClass {
	WebDriver driver;
	SignInPage signInPage;
	DropDownPage dropDownPage;
	WidgetTabPage widgetTabPage;
	AlertPage alertPage;
	DroppablePage droppablePage;

	@BeforeMethod
	public void beforeEveryAfterLoginTest() {
		driver = BaseClass.driver;
		signInPage = new SignInPage(driver);
		signInPage.signinWithCredentials("bob_test", "test123");
		widgetTabPage = new WidgetTabPage(driver);
		dropDownPage = new DropDownPage(driver);
		alertPage = new AlertPage(driver);
	}

	@Test(testName = "Widget Count",
			description = "Check count of widget elements", priority = 3)
	public void checkWidgetcount() {
		int count = widgetTabPage.countOfBoxes();
		System.out.println("The number of elements in Widget tab is: " + count);
		Assert.assertTrue(count > 0, "no widget elements found");
	}

	@Test(description = "Get country name from dropdown", priority = 4)
	public void checkCountryCount() {
		List<String> countries = dropDownPage.getCountryNames();
		Assert.assertTrue(countries.size() > 0);
		System.out.println("The total no. of countries in dropdown are: " + countries.size());
		System.out.println("The Names of Countries are: ");
		for (String countryName : countries) {
			System.out.println(countryName);
		}
	}

	@Test(description = "click on Alert tab", priority = 5)
	public void alert() {
		String alertMessage = alertPage.getAlertMessage();
		System.out.println("The Message in Alert Box is: " +alertMessage);
		Assert.assertTrue(alertMessage!= null, "No Alert Receieved");
	}

	@AfterMethod
	public void afterEveryAfterLoginTest() {
		driver.manage().deleteAllCookies();
		String baseUrl = "http://way2automation.com/way2auto_jquery/index.php";
		driver.get(baseUrl);
	}
}
