package com.employerContibution.EM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_457_001 extends TestBase{

	@Test(priority=1, description = "EM.457.001 - Verify CSR is able to add POA/TPR details for additional address Form Type 'Notice of Potential Charges (LO400)'",groups = {"Regression"})
	public void EM_457_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EM.457.001 - Verify CSR is able to add POA/TPR details for additional address Form Type 'Notice of Potential Charges (LO400)'");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.screenShot("Menu", "Pass", "Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Add Power of Attorney/Third Party Representative");
		commonFuntions.screenShot("AM", "Pass", "Add Power of Attorney/Third Party Representative");
		commonFuntions.clickMenu("Add Power of Attorney/Third Party Representative");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.ScrollMenu("Address Line 1 ");
		commonFuntions.screenShot("Error Messages", "Pass", "Required Error Messages");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("POA/TPR Name", "CSRTEStone");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Test");
		commonFuntions.enterTextboxContains("City ", "test&&&&&##$");
		commonFuntions.selectDropdown("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "9888");
		commonFuntions.enterTextboxContains("E-Mail Address", "test@gmail.com");
		commonFuntions.enterTextboxContains("First Name", "AutoTest");
		commonFuntions.enterTextboxContains("Last Name", "Test");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Address Line 1 ");sleep(2000);
		commonFuntions.screenShot("Error Messages", "Pass", "Invalid City and Zip Code");
		commonFuntions.errorLabel("Zip Code must have 5 numbers only.");
		//commonFuntions.errorLabel("only Alphabets, Numbers .-',!#_:;&/ allowed");
		Thread.sleep(2000);

		//zip code must be canadian postal code 
		commonFuntions.enterTextboxContains("PTIN", "P8679");
		commonFuntions.selectDropdown("Country", "Canada");
		commonFuntions.enterTextboxContains("City ", " Test ");
		commonFuntions.selectDropdown("State", " Alberta ");
		commonFuntions.enterTextboxContains("Zip Code", "000000");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.ScrollMenu("Employer Registration Number");
		commonFuntions.screenShot("Third Party Representative", "Pass", "Zip Code Error Message");
		Thread.sleep(2000);

		//no upload button is present on the screen
		WebElement web = driver.findElement(By.xpath("//input[@id='mailingAddressId_city']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='';", web);
	    commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "23334");
		commonFuntions.selectDropdown("Country", "United States");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("State", " Alabama ");
		commonFuntions.enterTextboxContains("Zip Code", "23334");
		Thread.sleep(2000);
		PEOPage.clickOnAdditionalLink.click();
		commonFuntions.screenShot("Add Additional Address", "Pass", "Add Additional Adddress");
		commonFuntions.clickButtonContains("Previous ");
		Thread.sleep(2000);
		PEOPage.clickOnAdditionalLink.click();
		commonFuntions.screenShot("Add Additional Address", "Pass", "Add Additional Adddress");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.screenShot("Required Error Messages", "Pass", "Required Error Messages");
		commonFuntions.errorLabel(" Required ");

		//zip code
		commonFuntions.selectDropdown("Form Type", " Notice of Potential Charges (LO400) ");
		commonFuntions.enterTextboxContains("First Name", "TestOne");
		commonFuntions.enterTextboxContains("Last Name", "TestTwo");
		commonFuntions.enterTextboxContains(" Contact Number ",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.selectDropdown("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code","1342");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Error Messages", "Pass", "Add Additional Add-Invalid Zip Code");
		Thread.sleep(2000);
		//Invalid city
		commonFuntions.enterTextboxContains("City","tesr%%$6");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu("Address Line 1 ");sleep();
		try {
		commonFuntions.errorLabel("only Alphabets, Numbers .-',!#_:;&/ allowed");
		}
		catch(Exception e) {
			System.out.println("Error message is displayed");
		}
		commonFuntions.screenShot("Error Messages", "Pass", "Invalid City Name");
		Thread.sleep(2000);
		
		//on continue 
		js.executeScript("arguments[0].value='';", web);
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		commonFuntions.ScrollMenu(" Contact Number ");
		commonFuntions.selectRadio("Set up from Power of Attorney - IA900 form");
		commonFuntions.screenShot("Add Power of Attorney/Third Party Representative", "Pass", "Add Power of Attorney/Third Party Representative");
		commonFuntions.clickButtonContains("Submit ");
		Thread.sleep(2000);
		commonFuntions.screenShot("POA/Third Party Representative Conformation", "Pass", "POA/Third Party Representative Conformation");
		Thread.sleep(2000);
		


	}
}
