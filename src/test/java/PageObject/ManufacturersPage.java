package PageObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import TestBase.SetupClass;

public class ManufacturersPage extends SetupClass {

	public ManufacturersPage(WebDriver driver) {
		super();
	}
	
	@Test
	public void clickAddNewManufacture () throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[2]/a")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[contains(@href , '/Admin/Manufacturer/List')]")).click();
		
		
		WebElement createManufacturerLink = wait.until(
		    ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href , '/Admin/Manufacturer/Create')]"))
		);
		createManufacturerLink.click();

		

		
		
	}
	
	@Test
	public void fillAddNewManufactureDetails() throws InterruptedException, IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement AdvanceOption = wait.until(
		    ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='onoffswitch-switch']"))
		);
		AdvanceOption.click();
		
        
        
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/utilities/Inputs.xlsx");
		
		XSSFWorkbook workbook =new XSSFWorkbook(file);
		
		XSSFSheet sheet =workbook.getSheet("manufacturers");
		
		XSSFRow row =sheet.getRow(1);
		
		String name =row.getCell(1).toString();
		
		driver.findElement(By.cssSelector("#Name")).sendKeys(name);
		
		
		Thread.sleep(5000);
		
		WebElement iframe = driver.findElement(By.id("Description_ifr"));
        driver.switchTo().frame(iframe);

        WebElement inputElement = driver.findElement(By.xpath("//*[@id=\'tinymce\']"));
        String valueToEnter = row.getCell(2).toString();
        inputElement.sendKeys(valueToEnter);

        driver.switchTo().defaultContent();
        
        Thread.sleep(1000);
        
        WebElement clickuploadfile =driver.findElement(By.xpath("//*[@name='qqfile']"));
        String imgpath = row.getCell(3).toString();
        clickuploadfile.click();
        Thread.sleep(1000);
        clickuploadfile.sendKeys(imgpath);
        
        Thread.sleep(10000);
        
        
       
	}
	
	
}
