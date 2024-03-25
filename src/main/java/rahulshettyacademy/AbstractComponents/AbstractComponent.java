package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacadmy.pageobjects.CartPage;
import rahulshettyacadmy.pageobjects.OrderPage;

public class AbstractComponent {
	// Note - This file use for common components all over the project.
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));// use explicit wait except use
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	
	public void waitForWebElementToAppear(WebElement findBy) { // webElement use for PageFactory
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));// use explicit wait except use
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage =new CartPage(driver); //  create object of cart page instaed of submit order test file
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage =new OrderPage(driver); //create one new page for this class object
		return orderPage;
	}

	public void waitForElementToDisppear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));//
		// use explicit wait except use
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
