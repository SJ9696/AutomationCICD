	package rahulshettyacadmy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	// Section 19 Lect 159 About constructor and page object and Page factoryfor
	// login page
	// IMPNote -PageObject not hold any data it's should only focus element and
	// actions only

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {

		// This is constructor - It is run before run to Landing page class
		// Constructor and Class must have same name
		// Note -please create object Lading page in main run file

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// PageFactory
	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector(".mb-3");
	By addToCartBy =By.cssSelector(".card-body button:last-of-type");
	By toastMessage  = By.cssSelector("#toast-container");

	// method

	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;

	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCartBy).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisppear(spinner);
		
	}

}
