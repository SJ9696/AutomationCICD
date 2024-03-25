package rahulshettyacadmy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacadmy.pageobjects.LandingPage;

public class BaseTest {

	// Sect 20 lect 166

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializDriver() throws IOException {

		// properties class read global properties so we create this class (Lect 166)

		Properties prop = new Properties(); // this is a object of GlobalData.properties class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//rahulshettyacadmy//resources//GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):
			prop.getProperty("browser");// IMPNote-lect182-This above two line use for testrun by cmd
		                              //maven commond for direct diffrent browser from system browser
		
//		String browserName = prop.getProperty("browser"); //This is old code line for local prop browser
		System.out.println(browserName);
		if (browserName.contains("chrome")) {
			
			ChromeOptions options= new ChromeOptions();
		    WebDriverManager.chromedriver().setup();
		    
		    if(browserName.contains("headless")) {
		    	options.addArguments("headless");
		    }
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox code
			driver = new FirefoxDriver();

		}

		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return driver;

	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json  convert to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);

		// String convert to HashMap -so install new dependancy "Jackson Databind"

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
	
	public String getScreenshot (String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage lanchApplication() throws IOException {

		driver = initializDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
