package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DroppablePage {
	WebDriver driver;

	By dropImage = By.xpath("//*[@id=\'wrapper\']/div/div[2]/div[1]/ul/li[2]/a/h2");
	//By dropBlock = By.xpath("//*[@id=\'example-1-tab-1\']/div");
	By draggable = By.xpath("//*[@id='draggable']");
	By droppable = By.xpath("//*[@id='droppable']");
	By droppedMessage = By.xpath("//*[@id='droppable']/p");

	public DroppablePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnDroppable() {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		//driver.switchTo().frame(1);	
		//JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(dropImage);
		//je.executeScript("arguments[0].scrollIntoView(true);", element);
		element.click();
	}

	public String getDragAndDropMessage() {
		this.clickOnDroppable();
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(0));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement drag = wait.until(ExpectedConditions
				.visibilityOfElementLocated(draggable));
		WebElement drop = wait.until(ExpectedConditions
				.visibilityOfElementLocated(droppable));
		Actions action = new Actions(driver);
		action.dragAndDrop(drag, drop).perform();
		String Message = driver.findElement(droppedMessage).getText();
		return Message;
	}

}
