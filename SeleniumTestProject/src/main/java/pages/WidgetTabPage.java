package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WidgetTabPage {

	WebDriver driver;
	// By widgetBlock = By.xpath("//*[@id='wrapper']");
	By widgetTab = By.xpath("//section[@id=\'wrapper\']/nav/div/ul/li[3]/a");
	By list = By.xpath("//*[@id=\'toggleNav\']/li[3]/ul/li");
	By focusToBlock = By.xpath("//*[@id='wrapper']/header/div");

	public WidgetTabPage(WebDriver driver) {
		this.driver = driver;
	}

	public int countOfBoxes() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));		
		WebElement widget = driver.findElement(widgetTab);
		Actions action = new Actions(driver);
		action.moveToElement(widget).perform();
		List<WebElement> listElements = driver.findElements(list);
		int count = listElements.size();
		driver.findElement(focusToBlock).click();
		return count;
	}
}
