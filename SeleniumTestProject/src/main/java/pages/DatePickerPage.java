package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePickerPage {
	WebDriver driver;
	By datePickerBox = By.xpath("//*[@id='wrapper']/div[2]/div[2]/div[2]/ul/li[3]/a/figure/img");
	By dateBox = By.xpath("//*[@id='datepicker']");
	By currentMonth = By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]");
	By currentYear = By.xpath("//*[@id='ui-datepicker-div']/div/div/span[2]");
	By monthChangeButton = By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span");
	By dateBlock = By.xpath("//*[@id='ui-datepicker-div']/table/tbody");
	By monthYear = By.xpath("//*[@id='ui-datepicker-div']/div/div");

	public DatePickerPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickDatePicker() {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		//driver.switchTo().frame(1);
		driver.findElement(datePickerBox).click();
	}
	
	public String getSelectedDate(String monthYear, String date) {
		String selectedDate = "";
		//open date picker page
		this.clickDatePicker();
		//click date picker field
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(0));
		WebElement dateTextBox = driver.findElement(dateBox);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		dateTextBox = wait.until(ExpectedConditions
				.visibilityOf(dateTextBox));
		dateTextBox.click();
		//get current month and year
		while (true) {
			String currentMonthYear = this.getDisplayedMonthYear();
			if (currentMonthYear.equals(monthYear)) {
				this.selectDate(date);
				break;
			} else {
				this.clickNextMonthButton();
			}
		}
		selectedDate = dateTextBox.getAttribute("value");
		return selectedDate;
	}
	
	private String getDisplayedMonthYear() {
		WebElement monthYearElement = driver.findElement(monthYear);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		monthYearElement = wait.until(ExpectedConditions
				.visibilityOf(monthYearElement));
		if(monthYearElement != null) {
			return monthYearElement.getText();
		}
		return "";
	}
	
	private void clickNextMonthButton() {
		WebElement nextButton = driver.findElement(monthChangeButton);
		nextButton.click();
	}
	
	private void selectDate(String date) {
		WebElement dateTable = driver.findElement(dateBlock);
		List<WebElement> dates = dateTable.findElements(By.tagName("a"));
		for(WebElement date1 : dates) {
			String calDate = date1.getText();
			if(calDate.equals(date)) {
				date1.click();
				break;
			}
		}
	}
}