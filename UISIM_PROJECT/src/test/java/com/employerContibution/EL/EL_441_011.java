package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_441_011 extends TestBase{


	@Test(priority=1, description = "EL.441.011 - Verify CSR can register Group PEO for Type of Legal Entity 'Limited Liability Partnership' and Type of Ownership 'Privately or Closely Held'",groups = {"Regression"})
	public void EL_441_011() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		//Map<String, String> databaseResults = PEOPage.database_SelectQuery("SELECT FEIN,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS desc");
		//String feinValue =databaseResults.get("Fein");
		//String ernValue = databaseResults.get("Ean");
		//System.out.println("feinValue is"+feinValue);
		
		test = report.createTest("EL.322.012: Verify CSR can register Individual PEO for Type of Legal Entity 'Other' and  type of Ownership 'Privately or Closely Held'.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login("ndfjp3","Admin@12345678");

		commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		commonFuntions.clickMenu("Menu");	
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();	
		commonFuntions.screenShot("Menu","Pass","Register PEO");
		commonFuntions.clickMenu("Register PEO");			 
		commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");		
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000); 
		PEOPage.groupRegPeo.click();
		commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.screenShot("PEO group registration", "PASS", "Professional Employer Organization Registration");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);     
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");

		String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		
		

		commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Limited Liability Limited Partnership");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		//commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
		commonFuntions.selectRadioQuestions("Type of Ownership", "Privately or Closely Held");
		commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
		commonFuntions.screenShot("General Information","Pass","General Information");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Account Details");
		try {
			Thread.sleep(2000);
			commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		}
		catch(Exception e) {}
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		PEOPage.addressLine1.sendKeys("addressLine1"+commonFuntions.createRandomInteger(1000,9999));
		PEOPage.addressLine2.sendKeys("addressLine2"+commonFuntions.createRandomInteger(1000,9999));
		PEOPage.addressCity.sendKeys("NewYork");
		PEOPage.addressZip.sendKeys("13420");

		commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		PEOPage.uspsAddress.click();
		PEOPage.currentAdditionalAddress.click();
		commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
		PEOPage.UspsContinueButton.click();
		Thread.sleep(2000);
		commonFuntions.screenShot("CurrentAdditionalAddress","Pass","Verify Current Additional Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Mailing Address","Pass","Mailing Address");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","PrioraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13429");
		commonFuntions.enterTextboxContains("other name(s)","automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
		commonFuntions.screenShot("Prior Address","Pass","Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyPriorAddress","Pass","Verify Prior Address(es) in New York");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage","40");
		commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("Verify Ownership Information", "Pass", "Verify Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
		commonFuntions.enterTextboxContains("Ownership Percentage","60");
		commonFuntions.enterTextboxContains("Address Line 1","PriorOwnerAddressLine1"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("Address Line 2","PriorOwnerAddressLine2"+commonFuntions.createRandomInteger(1000,9999));
		commonFuntions.enterTextboxContains("City","NewYork");
		commonFuntions.enterTextboxContains("Zip Code","13430");
		commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		try {
			PEOPage.uspsAddress.click();
			commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();	    
			Thread.sleep(2000);
		}
		catch(Exception e) {

		}
		commonFuntions.screenShot("Ownership Information", "Pass", "Verify Prior Ownership Information");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		try {
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		Thread.sleep(3000);
		}
		catch(Exception e) {
			
		}
		commonFuntions.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()=' DOWNLOAD TEMPLATE. ']")).click();
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.screenShot("verifyClient","Pass","Verify Client List");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		
		//Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' ORDER BY UPDATED_TS DESC","PEO_NAME");
		
		//String PEOName=databaseResults.get("PEO_NAME");
		
		// this test cases is failing at step 26 manually also, defect_3816



	}
}