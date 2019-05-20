package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertPage {
	WebDriver driver;

	By alertTab = By.xpath("//nav[@class=\'block\']/div/ul/li[7]/a");
	By simpleAlertTab = By.xpath("*[@id='wrapper']/div/div[1]/div[1]/ul/li[1]/a");
	//By alertBox = By.xpath("//html/body/button[contains(text(),\'alert\')]");
	By alertBox = By.tagName("button");

	public AlertPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickAlertTab() {
		driver.findElement(alertTab).click();

	}

	public String getAlertMessage() {
		driver.switchTo().frame(1);
		this.clickAlertTab();
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(0));		
		driver.findElement(alertBox).click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		return alertText;
		

	}
}
