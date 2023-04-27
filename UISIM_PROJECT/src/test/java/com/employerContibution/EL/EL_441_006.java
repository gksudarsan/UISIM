package com.employerContibution.EL;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_441_006 extends TestBase {
	@Test(priority = 1, description = "EL.441.007  - Verify PEO Admin can register PEO Group  for Type of Legal Entity 'Other' and Type of Ownership 'Public Ownership'." )
	public void Testing123() throws Exception{
		test = report.createTest("EL.441.007  - Verify PEO Admin can register PEO Group  for Type of Legal Entity 'Other' and Type of Ownership 'Public Ownership'.");
		commonStepDefinitions stepDef= new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		PEOPage peoPg = new PEOPage(driver);
		PEO_019_PEO_Registration_ContactDetails peoRegDetails = new PEO_019_PEO_Registration_ContactDetails(driver);
		PEO_001_ProfessionalEmployerOrganizationRegistration pemo = new PEO_001_ProfessionalEmployerOrganizationRegistration(driver);
		test.log(Status.INFO,"Logging to the application");
		stepDef.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.INFO, "Navigating to professional employer organization");
		home.navigateToPeoRegister();
		test.log(Status.PASS, "PEO Register");
		peoRegDetails.clickContinueButton();
		test.log(Status.INFO, "Clicking on the button");
		//pemo.selectRegDetails();
		//test.log(Status.INFO, "Entering the Details");
		//test.log(Status.PASS, "Entered the details");
		
		//SREG -001
		pemo.nameofProfessionalEmployerOrganization.sendKeys("PEO group EL other Admin ");
		stepDef.enterTextboxContains("Additional Names, if any, under which the PEO’s Conduct Business currently", "PEO group EL other Admin Co");
		//pemo.additionalNames.sendKeys("PEO group EL other Admin Co");
		pemo.saveAndContinue.click();
		
		//-------PEO 002
		sleep(2000);
		stepDef.screenShot("GenrelInformationPage","Pass"," Successfully landed on SREG 002");
		stepDef.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes ");
		   long number = stepDef.createRandomInteger(10000,99999);
		   String ernValue="12"+Long.toString(number);
		   String feinValue=Long.toString( stepDef.createRandomInteger(100000000,999999999));
		stepDef.enterTextboxContains("Employer Registration Number", ernValue);
		stepDef.selectDropdown("Type of Legal Entity", " Other ");
		stepDef.enterTextboxContains("Provide the type of Legal Entity", "Other cop");
		stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		stepDef.selectRadioQuestions("Type of Ownership", "Public");
		stepDef.enterTextboxContains("Fiscal Year Start Date", "02/02/2023");
		sleep(2000);
		stepDef.screenShot("GenrelInformationPage","Pass"," Entered details on SREG 002");
		stepDef.clickButtonContains("Save & Continue ");
		
		//------EAS-001
		sleep(2000);
		stepDef.screenShot("UnemploymentInsuranceAccountPage","Pass","successfully landed on EAS-001");
		sleep(2000);
		//driver.findElement(By.xpath("//span[@class='mat-radio-outer-circle']")).click();
		stepDef.clickButtonContains("Save & Continue ");
		sleep(2000);
		
		//-------PEO---003
		peoPg.attentionCareOf.sendKeys("Test");
		peoPg.addressLine1.sendKeys("addressLine1"+stepDef.createRandomInteger(1000,9999));
		peoPg.addressLine2.sendKeys("addressLine2"+stepDef.createRandomInteger(1000,9999));
		peoPg.addressCity.sendKeys("NewYork");
		peoPg.addressZip.sendKeys("13420");
		
		stepDef.enterTextboxContains("Phone Number",Long.toString(stepDef.createRandomInteger(10000000,99999999))+Long.toString(stepDef.createRandomInteger(10,99)));
		stepDef.enterTextboxContains("Business Email Address","autoTest"+Long.toString(stepDef.createRandomInteger(10000,99999))+"@gmail.com");
		stepDef.clickButtonContains("Save & Continue");
	    Thread.sleep(2000);
	    
	    peoPg.uspsAddress.click();
	    peoPg.currentAdditionalAddress.click();
	    stepDef.screenShot("UspsAddress","Pass","UspsAddress");
	    peoPg.UspsContinueButton.click();
	    Thread.sleep(2000);
		
		
		
		System.out.println("palak");
	}
}
