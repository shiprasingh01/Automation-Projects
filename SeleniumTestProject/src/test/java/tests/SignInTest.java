package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.SignInPage;

public class SignInTest extends BaseClass {
	WebDriver driver;
	SignInPage signInPage;

	@BeforeMethod
	public void beforeEverySigninTest() {
		this.driver = BaseClass.driver;
		signInPage = new SignInPage(driver);
	}

	@Test(description = "Capturing Validation Message with blank credentials", priority = 0)
	public void testWithoutLoginMessage() {
		signInPage.signinWithCredentials("", "");
		String requiredMsg = signInPage.getAlertMessage();
		System.out.println("The validation message for blank credentials is :" + requiredMsg);
		Assert.assertTrue(requiredMsg != null);
	}

	@Test(description = "Login with invalid id and pwd", priority = 1)
	public void testLoginMsg() {
		signInPage.signinWithCredentials("bob_test", "test345");
		String msgAfterLogin = signInPage.getInvalidLoginMsg();
		String expectedMsg = "Invalid username password.";
		System.out.println("The validation message for invalid login is :" + msgAfterLogin);
		Assert.assertTrue(msgAfterLogin.contains(expectedMsg));
	}

	@Test(description = "Login with valid credentials", priority = 2)
	public void testPageTitle() {
		signInPage.signinWithCredentials("bob_test", "test123");
		String title = signInPage.getTitle();
		System.out.println("The Title of the Page is: " + title);
		Assert.assertTrue(title.contains("Welcome to the Test"));
	}

	@AfterMethod
	public void afterEverySigninTest() {
		driver.manage().deleteAllCookies();
		String baseUrl = "http://way2automation.com/way2auto_jquery/index.php";
		driver.get(baseUrl);
	}
}
