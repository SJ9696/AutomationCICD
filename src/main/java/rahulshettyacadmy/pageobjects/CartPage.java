package rahulshettyacadmy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	// PageFactory
		@FindBy(css = ".cartSection h3")
		List<WebElement> productTitales;
		
		@FindBy(css = ".totalRow button")
		WebElement checkoutEle;
		
		public Boolean VerifyProductDisplay(String productName) {
			Boolean match = productTitales.stream()
					.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
			return match;
			
		}
		
		public CheckoutPage gotoCheckout() {
			checkoutEle.click();
			return new CheckoutPage(driver);
			
		}

}
