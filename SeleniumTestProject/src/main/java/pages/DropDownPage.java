package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDownPage {
	WebDriver driver;

	By dynamicTab = By.xpath("//*[@id=\'toggleNav\']/li[5]/a");
	By dropDown = By.xpath("//*[@id=\'toggleNav\']/li[5]/ul/li[2]/a");
	By countryList = By.tagName("select");

	public DropDownPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getCountryNames() {
		driver.switchTo().frame(1);
		WebElement dynamicElement = driver.findElement(dynamicTab);
		Actions action = new Actions(driver);
		action.moveToElement(dynamicElement).perform();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dropDown)));
		driver.findElement(dropDown).click();
		// driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(1));
		List<WebElement> countries = driver.findElement(countryList).findElements(By.tagName("option"));
		List<String> countryNames = new ArrayList<String>();
		for (WebElement country : countries) {
			countryNames.add(country.getAttribute("value"));
		}
		return countryNames;
	}

}
