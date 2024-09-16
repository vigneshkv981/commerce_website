package TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetupClass {
	
	public static WebDriver driver;
	
	@BeforeClass()
	@Parameters("browser")
	
	public void setup(@Optional("chrome") String browser) throws InterruptedException {
		
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.acceptInsecureCerts();
		dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
//		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		ChromeOptions options = new ChromeOptions();
		
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-notifications");
		options.merge(dc);
		
		if(browser.equals("chrome")) {
			
			
			WebDriverManager.chromedriver().setup();
		
			
		driver =new ChromeDriver(options);
			
		}else {
			WebDriverManager.edgedriver().setup();
			driver =new EdgeDriver();
		}
		
		driver.manage().deleteAllCookies();

		Thread.sleep(1000);
		
     	driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");
		
		driver.manage().window().maximize();
		
		
		driver.findElement(By.xpath("//button[@type='submit' and (contains(@class, 'button-1') or contains(@class, 'login-button'))]")).click();
		Thread.sleep(3000);
		
	}
	
	
	@AfterMethod()
	public void browsershutdown(){

		driver.quit();
	}
	
	
	
	}


