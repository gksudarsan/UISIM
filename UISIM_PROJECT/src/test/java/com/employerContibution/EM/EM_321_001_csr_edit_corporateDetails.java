package com.employerContibution.EM;



import java.util.Random;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddCorporatePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_321_001_csr_edit_corporateDetails extends TestBase
{
	String EAN = prop.getProperty("EAN");
	
	@Test(priority=1, description = "EM.321.001 - Verify CSR is able to Edit Corporate Officer/Owner Details.",groups = {"Regression"})
	public void EM_321_02_001() throws Exception
	{
		System.out.println(EAN);
		employerManagementLocators eml = new employerManagementLocators();
		 test = report.createTest("EM.321.001 - Verify CSR is able to Edit Corporate Officer/Owner Details.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Maintain Business Ownership");
		 commonFuntions.clickMenu("Maintain Business Ownership");			 
		 commonFuntions.ScrollMenu("Add Corporate Officer/Owner Details");
		 commonFuntions.screenShot("Add Corporate Officer/Owner Details","Pass","Add Corporate Officer/Owner Details");
		 commonFuntions.clickMenu("Add Corporate Officer/Owner Details");	
		eml.EAN().sendKeys(EAN);
		eml.continueButton().click();
	
	  
	     
	   
	     
	   	   
	}
	public void populateFields(String ssnValue) throws Exception {
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		Thread.sleep(2000);
		commonFuntions.selectRadio("Individual");
	     Random random = new Random();	     
	     commonFuntions.enterTextbox("SSN ",ssnValue );
	     commonFuntions.enterTextboxContains("First Name", "AutomationFirstName"+random.nextInt(10000));	    
	     commonFuntions.enterTextboxContains("Last Name", "AutomationLastName"+random.nextInt(10000));
	     commonFuntions.selectDropdown("Title", "Board Chairman");
	     commonFuntions.screenShot("Populate","Pass","populate corporate officer/ownerdetaisl");
	     commonFuntions.enterTextboxContains("Address Line 1", "Added address line 1");
	     commonFuntions.enterTextboxContains("Address Line 2", "Added address line 2");
	     commonFuntions.enterTextboxContains("City", "Added City");	    
	     commonFuntions.enterTextboxContains("Zip", String.valueOf((long) (Math.random()*Math.pow(10,6))));
	     commonFuntions.enterTextboxContains("Contact Number", String.valueOf((long) (Math.random()*Math.pow(10,11))));
	     commonFuntions.screenShot("Submit","Pass","Submit corporate officer/ownerdetaisl");
	     
	}
	public void verifyFields(String ssnValue) {
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
	
	
	}
}