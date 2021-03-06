package pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FramesAndWindowsPage {
	WebDriver driver;

	By windowsTab = By.xpath("//*[@id='toggleNav']/li[4]/a");
	By newWindowTab = By.xpath("//*[@id='wrapper']/div/div[1]/div[1]/ul/li[2]/a");
	By newWindowLink = By.xpath("//div[@class='farme_window']/p/a");
	By newOpenedWindow = By.xpath("/html/body/div[1]/p/a");

	public FramesAndWindowsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnWindowsTab() {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		//driver.switchTo().frame(1);
		driver.findElement(windowsTab).click();
	}

	public int getWindowsCount() {
		this.clickOnWindowsTab();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement openSeperateNewWindowTab = wait.until(ExpectedConditions
				.visibilityOfElementLocated(newWindowTab));
		//driver.findElement(newWindowTab).click();
		openSeperateNewWindowTab.click();
		driver.switchTo().frame(driver.findElements(By.tagName("iframe")).get(1));
		//driver.findElement(newWindowLink).click();
		WebElement openNewSeperateWindowLink = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.linkText("Open New Seprate Window")));
		openNewSeperateWindowLink.click();
		Set<String> windowHandles = driver.getWindowHandles();
		int activeWindowsCount = windowHandles.size();
		return activeWindowsCount;
	}

	public String getNewWindowMessage() {
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterate = windowHandles.iterator();
		String parent_window = iterate.next();
		String child_window = iterate.next();
		driver.switchTo().window(child_window);
		String newWindowText = driver.findElement(newOpenedWindow).getText();
		driver.close();
		driver.switchTo().window(parent_window);
		return newWindowText;
	}
}
