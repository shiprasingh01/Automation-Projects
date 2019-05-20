package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class temp {

	@BeforeMethod
	public void testPageTitle() {
		System.out.println("BeforeMethod");
	}

	@Test(description = "dragging and dropping the box", priority = 1)
	public void doTasks() {
		System.out.println("priority = 1");
	}

	@Test(description = "Opening a new window and fetching the text from it",
			priority = 2)
	public void newWindowMessage() {
		System.out.println("priority = 2");
	}

	@Test(description = "Printing and formatting date from DatePicker box",
			priority = 3)
	public void printDate() {
		System.out.println("priority = 3");
	}

	@Test(description = "performing actions on Autocomplete box", priority = 4)
	public void printTextFromAutocomplete() {
		System.out.println("priority = 4");
	}
	
	@Test(description = "performing random action on Autocomplete box", priority = 5)
	public void printRandomTextFromAutocomplete() {
		System.out.println("priority = 5");
	}

	@AfterMethod
	public void refreshBrowser() {
		System.out.println("AfterMethod");
	}
}
