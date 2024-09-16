package TestCases;


import java.io.IOException;

import org.testng.annotations.Test;

import PageObject.ManufacturersPage;
import TestBase.SetupClass;


public class AddManufacture_TestCase extends SetupClass{
	
	@Test
	 void Addnewbtnformanufacture() throws InterruptedException, IOException {

		ManufacturersPage clickaddnew =new ManufacturersPage(driver);
		clickaddnew.clickAddNewManufacture();
		
		ManufacturersPage Adddetails =new ManufacturersPage(driver);
		Adddetails.fillAddNewManufactureDetails();
		
	}

	
	

}
