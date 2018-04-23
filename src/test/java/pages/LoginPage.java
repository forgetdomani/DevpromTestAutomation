package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testData.UserData;

public class LoginPage {
	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(id = "login")
	private WebElement tbUsername;

	@FindBy(id = "pass")
	private WebElement tbPassword;

	@FindBy(tagName = "button")
	private WebElement btLogin;

	public void login(UserData user) {
		try {
			tbUsername.sendKeys(user.getUserName());
			tbPassword.sendKeys(user.getPassword());
			btLogin.click();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

	}

}
