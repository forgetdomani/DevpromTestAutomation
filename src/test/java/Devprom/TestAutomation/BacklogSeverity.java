package Devprom.TestAutomation;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.LoginPage;
import pages.MainPage;
import pages.TestScenariosPage;
import testData.TestScenario;
import testData.UserData;

public class BacklogSeverity {
	private static WebDriver driver;
	private static LoginPage loginPage;
	private static MainPage mainPage;
	private static TestScenariosPage testScenariosPage;

	@BeforeClass
	public static void setup() {

		System.setProperty("webdriver.gecko.driver", "c:\\gecodriver\\geckodriver.exe");

		driver = new FirefoxDriver();
		loginPage = new LoginPage(driver);
		mainPage = new MainPage(driver);
		testScenariosPage = new TestScenariosPage(driver);
		driver.manage().window().maximize();
		driver.navigate().to("https://nkosaretskaya.myalm.ru/");
	}

	@Test
	public void BacklogPriorityTest() {
		UserData user = new UserData("forgetdomani", "forgetdomani");
		ArrayList<TestScenario> scenarios = new ArrayList<TestScenario>();
		scenarios.add(new TestScenario());
		scenarios.add(new TestScenario());
		scenarios.add(new TestScenario());
		String severity = "Обязательно";

		loginPage.login(user);
		mainPage.dissmissInstructions();
		mainPage.goToBacklog();
		mainPage.goToTestScenarios();
		for (TestScenario scenario : scenarios) {
			testScenariosPage.createScenario(scenario);
		}
		mainPage.goToTestScenarios();
		testScenariosPage.selectScenarios(scenarios);
		testScenariosPage.setSeverity(severity);

		for (TestScenario scenario : scenarios) {
			String scSeverity = testScenariosPage.getScenarioSeverity(scenario);
			Assert.assertTrue(scSeverity.equals(severity));
		}

	}

	@AfterClass
	public static void tearDown() {
		driver.close();
	}
}
