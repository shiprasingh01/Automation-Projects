package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {

	WebDriver driver;

	By signInButtonLocator = By.xpath("(//*[@id='load_form']/div/div[1]/p/a)[2]");
	// By signInButtonLocator = By.className("fancybox");
	By userNameLocator = By.xpath("(//*[@id=\'load_form\']/fieldset[1]/input)[2]");
	// By userNameLocator = By.name("username");
	By passwordLocator = By.xpath("(//*[@id=\'load_form\']/fieldset[2]/input)[2]");
	// By passwordLocator = By.name("password");
	By submitButtonLocator = By.xpath("(//input[@class=\'button\' and @value=\'Submit\'])[2]");
	// By submitButtonLocator = By.className("button");
	// By loginMsg = By.xpath("(//form/p)[2]");
	By msgAlert = By.xpath("(//form[@id='load_form'])[2]/p[@id='alert1']");

	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnSignButton() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement signInButtonElement = wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
		if (signInButtonElement != null) {
			signInButtonElement.click();
		}
	}

	public void enterUsername(String userName) {
		WebElement userNameElement = driver.findElement(userNameLocator);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		userNameElement = wait.until(ExpectedConditions.visibilityOf(userNameElement));
		if (userNameElement != null) {
			userNameElement.sendKeys(userName);
			;
		}
	}

	public void enterPassword(String pwd) {
		WebElement passwordeElement = driver.findElement(passwordLocator);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		passwordeElement = wait.until(ExpectedConditions.visibilityOf(passwordeElement));
		if (passwordeElement != null) {
			passwordeElement.sendKeys(pwd);
			;
		}
	}

	public void clickSubmitButton() {
		WebElement submitButtonElement = driver.findElement(submitButtonLocator);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButtonElement));
		if (submitButtonElement != null) {
			submitButtonElement.click();
		}
	}

	public boolean waitForLogin() {
		WebElement submitButtonElement = driver.findElement(submitButtonLocator);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.invisibilityOf(submitButtonElement));
	}

	public void signinWithCredentials(String userId, String pword) {
		this.clickOnSignButton();
		this.enterUsername(userId);
		this.enterPassword(pword);
		this.clickSubmitButton();
	}

	public String getAlertMessage() {
		WebElement userNameElement = driver.findElement(userNameLocator);
		String validationMessage = userNameElement.getAttribute("validationMessage");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(userNameElement));
		return validationMessage;
	}

	public String getInvalidLoginMsg() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(msgAlert));
		String msg = driver.findElement(msgAlert).getAttribute("innerHTML");
		return msg;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	
}
