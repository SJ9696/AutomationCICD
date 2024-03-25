package rahulshettyacadmy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import junit.framework.Assert;
import rahulshettyacadmy.TestComponents.BaseTest;
import rahulshettyacadmy.TestComponents.Retry;
import rahulshettyacadmy.pageobjects.CartPage;
import rahulshettyacadmy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		landingPage.loginAppliction("tdata2548@gmail.com", "Tda2548");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		landingPage.loginAppliction("anshika@gmail.com", "Iamking@000");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		// Cart Added product //lect164
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(productName);
//		Assert.assertTrue(match);

	}

}
