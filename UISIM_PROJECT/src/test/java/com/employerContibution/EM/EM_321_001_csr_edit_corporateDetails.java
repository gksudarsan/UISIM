package com.employerContibution.EM;



import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
	
	
	@Test(priority=1, description = "EM.321.001 - Verify CSR is able to Edit Corporate Officer/Owner Details.",groups = {"Regression"})
	public void EM_321_02_001() throws Exception
	{
		
		employerManagementLocators eml = new employerManagementLocators();
		 test = report.createTest("EM.321.001 - Verify CSR is able to Edit Corporate Officer/Owner Details.");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 AddCorporatePage addCorporatePage = PageFactory.initElements(driver, AddCorporatePage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 sleep(3000);
		 commonFuntions.clickMenu("Menu");	
		 commonFuntions.ScrollMenu("Account Maintenance");
		 commonFuntions.clickMenu("Account Maintenance");
		 commonFuntions.screenShot("Menu","Pass","Maintain Business Ownership");
		 commonFuntions.clickMenu("Maintain Business Ownership");	
		 sleep(2000);		 
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);		 
		 commonFuntions.screenShot("Error_Required","Pass","Error message shown as expected");
		 commonFuntions.errorLabel("Required");
		 commonFuntions.enterTextbox("Employer Registration Number","6543210" );
		 
		 commonFuntions.clickButtonContains("Continue");
		 Thread.sleep(2000);
		 //commonFuntions.errorContent("The Employer Registration Number(ERN) provided does not exist in the system.");
		 commonFuntions.errorContent("Employer Account Number (EAN) is invalid. Cannot perform Partner/Corporate maintenance.");
		 commonFuntions.screenShot("Error_Required1","Pass","Error message shown as expected");
		 
		 
		// Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'CORP' AND EAN LIKE '9%'","EAN");
		 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ORGANIZATION_TYPE = 'CORP'","EAN");
			String ernValue = databaseResults.get("EAN");
		 System.out.println(ernValue);
		 commonFuntions.enterTextbox("Employer Registration Number",ernValue );
		 commonFuntions.screenShot("Menu","Pass","ERN Screen");
		 commonFuntions.clickButtonContains("Continue");
	     sleep(2000);
	     commonFuntions.screenShot("CorporateOwner","Pass","Add Corporate/Owner details");
	     commonFuntions.clickOnLink("Add Corporate/Owner Details");
	     sleep(2000);
	     commonFuntions.selectRadio("Individual");
	     
	     sleep(3000);
	     String ssn=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
			commonFuntions.enterTextboxContains("First Name","First Name"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));;
			commonFuntions.enterTextboxContains("Last Name","Last Name"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));;
	     
			commonFuntions.enterTextboxContains("Address Line 1","$%^^&^%$");
			commonFuntions.enterTextboxContains("Address Line 2","AddressLine2"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		     
			sleep(2000);
			 commonFuntions.errorLabelContains("only Alphabets, Numbers"," Residential Address");
			commonFuntions.screenShot("ErrroValidation3","Pass","Error Message shown as expected");
	     
	     commonFuntions.enterTextboxContains("Address Line 1","AddressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     
	     commonFuntions.enterTextboxContains("Zip Code","1340");
	     sleep(2000);
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.errorLabelContains("Zip Code must have 5 numbers only"," Residential Address");
	     
	     commonFuntions.screenShot("ErrorValidation4","Pass","Error Messsage shown as expected");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     
	     commonFuntions.selectDropdownUsingSearch("Source", " NYS-100 (paper) ");
			Thread.sleep(2000);
			commonFuntions.selectDropdownUsingSearch("Source Type", " NYS-100 ");
			sleep(3000);
			System.out.println(ssn);
			commonFuntions.clickButtonContains("Submit");
		     sleep(2000);
		     commonFuntions.screenShot("ErrorValidation","Pass","Erro Message shown as expected");
		     commonFuntions.enterTextboxContains("SSN", "23432");
		  
		     sleep(2000);
		     commonFuntions.errorLabelContains("Please enter a valid SSN","SSN");
		     
		     commonFuntions.screenShot("ErrorValidation2","Pass","Error Message shown as expected");
			commonFuntions.enterTextboxContains("SSN", ssn);
			
			commonFuntions.screenShot("Submit","Pass","Submitting");
			commonFuntions.clickButtonContains("Submit");
		     sleep(2000);
		     String ssnValue = StringUtils.left(ssn, 3)+"-"+StringUtils.right(StringUtils.left(ssn, 5),2)+"-"+StringUtils.right(ssn, 4);
		     commonFuntions.screenShot("AfterSubmit","Pass","After Submitting");
	     commonFuntions.selectTable(ssnValue, 9, 1, "Corporate/Owner Details");
			sleep(4000);
			commonFuntions.enterTextboxContains("Address Line 1","UPDATED ADDRESS LINE 1");
			sleep(2000);
			commonFuntions.screenShot("Edit","Pass","Editing Address line 1");
			commonFuntions.selectDropdownUsingSearch("Source", " NYS-100 (paper) ");
			Thread.sleep(2000);
			commonFuntions.selectDropdownUsingSearch("Source Type", " NYS-100 ");
			sleep(3000);
			commonFuntions.clickButtonContains("Submit");
		     sleep(2000);
		     commonFuntions.screenShot("AfterEdit","Pass","After edit submitting");
		     sleep(3000);
			 String address = commonFuntions.retrieveValueFromTable(ssnValue,4,1,"Individual as Corporate Officer ");	
			 test.info("Expected is UPDATED ADDRESS LINE 1 and Actual is "+address);
			    Assert.assertEquals(address.contains("UPDATED ADDRESS LINE 1"), true);
			   
			    
		
		
	
	  
	     
	   
	     
	   	   
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