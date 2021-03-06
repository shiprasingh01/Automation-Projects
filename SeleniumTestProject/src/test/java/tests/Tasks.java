package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.AutoCompletePage;
import pages.DatePickerPage;
import pages.DroppablePage;
import pages.FramesAndWindowsPage;
import pages.SignInPage;

public class Tasks extends BaseClass {
	WebDriver driver;
	SignInPage signInPage;
	DroppablePage droppablePage;
	FramesAndWindowsPage framesAndWindowsPage;
	DatePickerPage datePickerPage;
	AutoCompletePage autoCompletePage;
	String expMonthYear = "June 2021";
	String expDate = "20";

	@BeforeMethod
	public void beforeEveryTaskTest() {
		driver = BaseClass.driver;
		signInPage = new SignInPage(driver);
		signInPage.signinWithCredentials("bob_test", "test123");
		Assert.assertTrue(signInPage.waitForLogin());
		droppablePage = new DroppablePage(driver);
		framesAndWindowsPage = new FramesAndWindowsPage(driver);
		datePickerPage = new DatePickerPage(driver);
		autoCompletePage = new AutoCompletePage(driver);
	}

	@Test(description = "dragging and dropping the box", priority = 6)
	public void doTasks() {
		String droppedMessage = droppablePage.getDragAndDropMessage();
		Assert.assertTrue(droppedMessage != null);
		System.out.println("The Message received after dragging the box is :" + droppedMessage);
	}

	@Test(description = "Opening a new window and fetching the text from it",
			priority = 7)
	public void newWindowMessage() {
		int count = framesAndWindowsPage.getWindowsCount();
		String newWindowText = framesAndWindowsPage.getNewWindowMessage();
		Assert.assertTrue(count > 0, "No child window opened");
		Assert.assertTrue(newWindowText != null);
		System.out.println("The no of current windows opened is :" + count);
		System.out.println("The text from the new Window is :" + newWindowText);
	}

	@Test(description = "Printing and formatting date from DatePicker box",
			priority = 8)
	public void printDate() {
		String selectedDate = datePickerPage.getSelectedDate(expMonthYear, expDate);
		Assert.assertTrue(selectedDate != null);
		System.out.println(" Selected date is: " + selectedDate);
	}

	@Test(description = "performing actions on Autocomplete box", priority = 9)
	public void printTextFromAutocomplete() {
		String text = autoCompletePage.getTextFromAutoBox();
		Assert.assertTrue(text != null);
		System.out.println("The text received from autocomplete is: " + text);
	}
	
	@Test(description = "performing random action on Autocomplete box", priority = 10)
	public void printRandomTextFromAutocomplete() {
		String text = autoCompletePage.getRandomTextFromAutoBox();
		Assert.assertTrue(false);
		System.out.println("The random text received from autocomplete is: " + text);
	}

	@AfterMethod
	public void afterEveryTaskTest() {
		driver.manage().deleteAllCookies();
		String baseUrl = "http://way2automation.com/way2auto_jquery/index.php";
		driver.get(baseUrl);
	}

}
