package pages;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.TestScenario;

public class TestScenariosPage {
	public WebDriver driver;

	public TestScenariosPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(id = "create-scenario")
	private WebElement btCreateScenario;

	@FindBy(id = "WikiPageCaption")
	private WebElement tbScenarioCaption;

	@FindBy(id = "WikiPageSubmitBtn")
	private WebElement btSaveScenario;

	@FindBy(className = "icon-pencil")
	private WebElement lsBulkModify;

	public void createScenario(TestScenario scenario) {
		try {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(btCreateScenario));
			btCreateScenario.click();
			new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(tbScenarioCaption));
			tbScenarioCaption.sendKeys(scenario.getName());
			btSaveScenario.click();
			new WebDriverWait(driver, 3);
		} catch (Exception e) {
			return;
		}
	}

	public void selectScenarios(ArrayList<TestScenario> scenarios) {
		for (TestScenario scenario : scenarios) {
			selectScenario(scenario);
		}
	}

	private void selectScenario(TestScenario scenario) {
		try {
			WebElement scRow = driver
					.findElement(By.xpath("//*[@id='caption' and text()='" + scenario.getName() + "']"))
					.findElement(By.xpath(".."));
			WebElement chb = scRow.findElement(By.tagName("input"));
			chb.click();
		} catch (Exception e) {
			return;
		}
	}

	public void setSeverity(String severity) {
		try {
			lsBulkModify.click();
			driver.findElement(By.id("bulk-modify-actions")).findElement(By.linkText("Важность")).click();
			new WebDriverWait(driver, 1000).ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.presenceOfElementLocated(By.id("Importance")));
	new WebDriverWait(driver, 1000).until(
			ExpectedConditions.visibilityOf(driver.findElement(By.id("Importance"))));
	
			Select listbox = new Select(driver.findElement(By.id("Importance")));
			listbox.selectByVisibleText(severity);
			driver.findElement(By.id("SubmitBtn")).click();
		} catch (Exception e) {
			return;
		}

	}

	public String getScenarioSeverity(TestScenario scenario) {
		try {
			Actions act = new Actions(driver);
			act.doubleClick(driver.findElement(By.xpath("//*[@id='caption' and text()='" + scenario.getName() + "']")))
					.perform();
			new WebDriverWait(driver, 1000).ignoring(NoSuchElementException.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='tab-additional']")));
			new WebDriverWait(driver, 1000).until(
					ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[href*='tab-additional']"))));
			new WebDriverWait(driver, 1000).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.cssSelector("a[href*='tab-additional']"))));
			driver.findElement(By.cssSelector("a[href*='tab-additional']")).click();
			Select listbox = new Select(driver.findElement(By.id("WikiPageImportance")));
			WebElement option = listbox.getFirstSelectedOption();
			String scSeverity = option.getText();
			new WebDriverWait(driver, 1000).ignoring(NoSuchElementException.class)
			.until(ExpectedConditions.presenceOfElementLocated(By.id("WikiPageCancelBtn")));
	new WebDriverWait(driver, 1000).until(
			ExpectedConditions.visibilityOf(driver.findElement(By.id("WikiPageCancelBtn"))));
	new WebDriverWait(driver, 1000).until(ExpectedConditions
			.elementToBeClickable(driver.findElement(By.id("WikiPageCancelBtn"))));
			driver.findElement(By.id("WikiPageCancelBtn")).click();
			return scSeverity;
		} catch (Exception e) {
			return "";
		}
	}
}
