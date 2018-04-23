package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	public WebDriver driver;

	public MainPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//button[@data-role='stop']")
	private WebElement btStop;

	@FindBy(xpath = "//*[@class=\"icon-arrow-right icon-white\"]")
	private WebElement lkShowMenu;

	@FindBy(xpath = "//a[@uid='productbacklog']")
	private WebElement lkBacklog;

	@FindBy(linkText = "Тестирование")
	private WebElement lkTesting;

	@FindBy(xpath = "//a[@uid='testing-list']")
	private WebElement lkTestScenarios;

	public void dissmissInstructions() {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(btStop));
			btStop.click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public void goToBacklog() {
		try {
			lkBacklog.click();
			lkShowMenu.click();
		} catch (Exception e) {
			return;
		}
	}

	public void goToTestScenarios() {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(lkTesting));
			lkTesting.click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(lkTestScenarios));
			lkTestScenarios.click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}
