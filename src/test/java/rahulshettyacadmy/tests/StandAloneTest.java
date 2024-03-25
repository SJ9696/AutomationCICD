package rahulshettyacadmy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

//import com.beust.jcommander.internal.Console;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//LoginContext application
        
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.manage().window().maximize();
        String productName = "ADIDAS ORIGINAL";
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("tdata2548@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='enter your passsword']")).sendKeys("Tdata2548");
		driver.findElement(By.id("login")).click();
		
//Add product in cart
		// If any error in this code check property of this project > java compiler
		// check java version should be 1.8
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));// use explicit wait except use
																					// thread.sleep
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(s -> s.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Use Explicit wait for particuler web element its give message for add product
		// confirmation

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));// Explicit
																											// wait for
																											// until
																											// display
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));//
		// wait process
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

//Cart Added product

		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.close();
//Select dynamic drop down value
//		driver.findElement(By.cssSelector("[placeholder='Select Country']")).click();
//		Actions a = new Actions(driver);
//		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));// Explicit
//																										// wait
//		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
//		driver.findElement(By.cssSelector(".action__submit")).click();
		//a.moveToElement(move).build().perform();
		//move.click();
		//driver.findElement(By.className("icon icon-arrow-right-circle")).click();

// Message validation
//		String ConfirmMesaage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		//Assert.assertEquals(ConfirmMesaage, "THANKYOU FOR THE ORDER.");
//		ConfirmMesaage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
//		driver.close();

	}

}
