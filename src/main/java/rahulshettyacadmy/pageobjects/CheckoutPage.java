package rahulshettyacadmy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;	

	
	// This is constructor use for -that give life of driver 
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Initalize element by using page factory 
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = "//button[contains(@class,'ta-item')][2]") //button[contains(@class,'ta-item')][2]
	WebElement selectcountry;
	
	@FindBy(css = ".action__submit")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		System.out.println(countryName);
		//waitForElementToAppear(By.cssSelector(".ta-results"));// Explict  wait
		selectcountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		submit.click();
		return new ConfirmationPage(driver);
		
	}

}
