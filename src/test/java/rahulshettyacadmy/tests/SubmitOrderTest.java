package rahulshettyacadmy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import rahulshettyacadmy.TestComponents.BaseTest;
import rahulshettyacadmy.pageobjects.CartPage;
import rahulshettyacadmy.pageobjects.CheckoutPage;
import rahulshettyacadmy.pageobjects.LandingPage;
import rahulshettyacadmy.pageobjects.OrderPage;
import rahulshettyacadmy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "PurchaseOrder")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

//LoginContext application

		//LandingPage landingPage =lanchApplication();// lect 167 not required because of Base test Before method
		                             // annotation so next line lading page obect give error so
		                             //we create ladingpage object on top of the Base test file.
//LoginContext application 
		landingPage.loginAppliction(input.get("email"), input.get("pass"));
//Add product in cart // lect163
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		productCatalogue.goToCartPage();
//Cart Added product  //lect164
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
//		Assert.assertTrue(match);
//Select dynamic drop down value
		CheckoutPage checkoutPage = cartPage.gotoCheckout();
//		checkoutPage.selectCountry("India");
		// ConfirmationPage confirmationPage = checkoutPage.submitOrder();// avoiding to create seprate object for confirmationPage 
// Message validation
		// String ConfirmMesaage = confirmationPage.getConformationMessage();
		// Assert.assertEquals(ConfirmMesaage, "THANKYOU FOR THE ORDER.");
		// Assert.assertTrue(ConfirmMesaage.equalsIgnoreCase("THANKYOU FOR THE
		// ORDER."));
		// driver.close(); // This is use in Base test Aftermethod annotetion

	}

	@Test(dependsOnMethods = { "submitOrder" })

	public void OrderHistoryTest() {
		landingPage.loginAppliction("tdata2548@gmail.com", "Tdata2548");
		OrderPage orderpage = new OrderPage(driver);
		orderpage.goToOrdersPage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(productName));
	}

	
	
	// Lect174  json data provider
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//rahulshettyacadmy//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	// Lect172
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"tdata2548@gmail.com", "Tdata2548","ADIDAS ORIGINAL"},{"anshika@gmail.com", "Iamking@000","ZARA COAT 3"}};
//	}

	// Lect 173
//	@DataProvider
//	public Object[][] getData() throws IOException {

//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("emai", "tdata2548@gmail.com");
//		map.put("pass", "Tdata2548");
//		map.put("productName", "ADIDAS ORIGINAL");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("emai", "anshika@gmail.com");
//		map1.put("pass", "Iamking@000");
//		map1.put("productName", "ZARA COAT 3");

//		return new Object[][] { {map}, { map1 } };

//	}

}
