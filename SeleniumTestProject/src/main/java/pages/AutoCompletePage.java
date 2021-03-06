package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutoCompletePage {

	WebDriver driver;
	By autoCompTab = By.xpath("//*[@id='wrapper']/div[2]/div[2]/div[2]/ul/li[2]/a/figure/img");
	By tagBox = By.xpath("//*[@id='tags']");
	By autoList = By.xpath("//ul[@id='ui-id-1']");

	public AutoCompletePage(WebDriver driver) {
		this.driver = driver;
	}

	private void clickOnAutocompletetTab() {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		//driver.switchTo().frame(1);
		driver.findElement(autoCompTab).click();
	}

	public String getTextFromAutoBox() {
		return this.readTextFromAutoBox("j", "Java");
	}
	
	public String getRandomTextFromAutoBox() {
		return this.readTextFromAutoBox(this.getRandomChar(), this.getRandomChar());
	}
	
	private String readTextFromAutoBox(String value, String matchString) {
		this.clickOnAutocompletetTab();
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(0));
		WebElement box = driver.findElement(tagBox);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		box = wait.until(ExpectedConditions
				.visibilityOf(box));
		box.sendKeys(value);
		WebElement list = driver.findElement(autoList);
		list = wait.until(ExpectedConditions
				.visibilityOf(list));
		List<WebElement> listOfNames = list.findElements(By.tagName("li"));
		String name = "";
		for (WebElement listName : listOfNames) {
			name = listName.getText();
			if (name.startsWith(matchString)) {
				listName.click();
				break;
			}
		}
		return name;
	}
	
	private String getRandomChar() {
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		return String.valueOf(c);
	}
}
