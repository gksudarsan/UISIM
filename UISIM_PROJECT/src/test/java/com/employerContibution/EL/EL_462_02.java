package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

public class EL_462_02 extends TestBase{

	@Test(priority=1, description = "EL.462.02 - Verify CSR can search PEO and update PEO conversion 'PEO Group to PEO Exempt'",groups = {"Regression"})
	public void EL_462_02() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest("EL.462.02 - Verify CSR can search PEO and update PEO conversion 'PEO Group to PEO Exempt' ");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Manage PEO");
		commonFuntions.clickMenu("Manage PEO");sleep();
		commonFuntions.screenShot("Search for PEO", "Pass", "Search for PEO");
		PEOPage.advancedSearch.click();sleep();
		commonFuntions.clickButtonContains("search");
		Thread.sleep(2000);
		commonFuntions.errorContent("At least one field must be filled for search");
		commonFuntions.screenShot("error message", "Pass", "Search for PEO - error message");
		Thread.sleep(2000);
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),5);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButtonContains("search");sleep();
		commonFuntions.errorContent("Invalid Federal Employer Identification Number (FEIN)");
		commonFuntions.screenShot("error message", "Pass", "Search PEO - Invalid FEIN Number");
		driver.navigate().refresh();Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.clickButtonContains("search");sleep();
		commonFuntions.errorContent("Invalid Employer Registration Number");
		commonFuntions.screenShot("error message", "Pass", "Search PEO - Invalid ERN Number");
		driver.navigate().refresh();Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOGR'",
				"FEIN");
		String feinValue1 = databaseResults.get("FEIN");
		System.out.println("feinValue is: " + feinValue1);
		commonFuntions.enterTextboxContains("(FEIN)", feinValue1);
		commonFuntions.screenShot("enter peo name", "Pass", "Peo Name - record");
		commonFuntions.clickButtonContains("search");
		Thread.sleep(3000);
		commonFuntions.selectRadio("Select");
		commonFuntions.screenShot("select radio button", "Pass", "Selecting record..");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(3000);
		commonFuntions.selectDropdown("PEO Conversion", " PEO Group to PEO Exempt ");sleep();
		commonFuntions.screenShot("Manage Group PEO", "Pass", "Manage Group PEO");
		commonFuntions.clickButtonContains("  CONVERT ");
		Thread.sleep(2000);
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "No");
		try {
			commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAuto"+commonFuntions.createRandomInteger(10, 99));
			}
			catch(Exception e) {
				System.out.println("Type of Legal Entity - Other");
				
			}
		commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " Alabama ");
		commonFuntions.enterTextboxContains("State agency that issued it.", "test");
		commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
		String ernNumber=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),10);
		commonFuntions.enterTextboxContains("Registration Number ",ernNumber );
		commonFuntions.screenShot("General Information", "Pass", "General Information for PEO Exempt Registration");
		commonFuntions.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Unemployment Insurance Account Details", "Pass", "Unemployment Insurance Account Details");
		commonFuntions.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		try {
		commonFuntions.enterTextboxContains("Address Line 1 ", "Cooper square"+commonFuntions.createRandomInteger(10, 99));
		}
		catch(Exception e) {
			System.out.println("Mandatory address section is not filled.");
			
		}
		commonFuntions.screenShot("Address Information", "Pass", "Address Information");
		commonFuntions.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();	    
		}
		catch(Exception e) {
			System.out.println("Address pop up not showing");
		}
		commonFuntions.screenShot("Mailing Address", "Pass", "Mailing Address");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Application for Exemption Submission Instructions", "Pass", "Submission Instructions");
		commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		
		//uploading documents..
		commonFuntions.selectCheckbox("Authorization to do business in NYS");
		commonFuntions.selectLink("Authorization to do business in NYS", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(2000);
		//commonFuntions.clickButtonContains("Upload");
		//Thread.sleep(10000);
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		Thread.sleep(3000);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(3000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("PEO Details", "Pass", "PEO Details Review Screen");
		commonFuntions.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
		commonFuntions.screenShot("Declaration", "Pass", "Declaration for PEO Exempt");
		commonFuntions.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Statement Of Acknowledgement", "Pass", "Statement Of Acknowledgement");
		commonFuntions.clickButtonContains("Accept & Submit ");
		Thread.sleep(2000);
		commonFuntions.selectRadio("Approved");
		commonFuntions.screenShot("PEO Registration Approval", "Pass", "PEO Registration Approval");
		commonFuntions.clickButtonContains("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Conversion Confirmation", "Pass", "Conversion Confirmation");
		commonFuntions.clickButton("Home");
		Thread.sleep(2000);
		
		
		//test case is blocked at step 
		
		//View correspondance--->>Inquiry>>Contribution Inquiry>>View Correspondance
		/*commonFuntions.clickMenu("Menu");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.screenShot("Menu", "Pass", "Contribution Inquiry");
		commonFuntions.ScrollMenu("View Correspondence");
		commonFuntions.clickMenu("View Correspondence");
		commonFuntions.screenShot("Contribution Inquiry", "Pass", "View Correspondence");
		Thread.sleep(2000);*/
		
		//PEO Inquiry Information
		/*commonFuntions.clickMenu("Menu");
		commonFuntions.clickMenu("Inquiry");
		commonFuntions.clickMenu("Professional Employer Organization (PEO)");
		commonFuntions.clickMenu("Inquiry PEO Information");
		commonFuntions.screenShot("Inquiry", "Pass", "Inquiry PEO Information");
		sleep();
		PEOPage.advancedSearch.click();sleep();
		
		commonFuntions.enterTextboxContains("PEO Name", "ExemptCSR26");
		commonFuntions.clickButtonContains("search");*/
		
		
	

	}

}
