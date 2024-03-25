package rahulshettyacadmy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	// Section 19 Lect 159 About constructor and page object and Page factory for
	// login page
	//IMPNote -PageObject not hold any data it's should only focus element and actions only 

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		// send driver from child page 'LandingPage' to Parent 'AbstractComponent' page
		super(driver);
		// This is constructor - It is run before run to Landing page class
		// Constructor and Class must have same name
		// Note -please create object Lading page in main run file
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(css = "input[placeholder='enter your passsword']")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement Login;
	
	@FindBy(css = "[class*=flyInOut")
	WebElement errorMessage;
	
	// method
	public void loginAppliction(String email, String password) { 
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		Login.click();
		
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		
	}

}
