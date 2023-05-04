package com.employerContibution.EL;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.PEO_001_ProfessionalEmployerOrganizationRegistration;
import com.ui.pages.PEO_019_PEO_Registration_ContactDetails;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_441_007 extends TestBase{
	@Test(priority = 1, description = "EL.441.007  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Public Ownership'", groups = { "Regression" })
	public void Testing123() throws Exception {
		test = report.createTest("EL.441.007  - Verify CSR can register PEO Group  for Type of Legal Entity 'Corporation' and Type of Ownership 'Public Ownership'");
		commonStepDefinitions stepDef = new commonStepDefinitions();
		HomePage home = new HomePage(driver);
		 PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		PEOPage peoPg = new PEOPage(driver);
		PEO_019_PEO_Registration_ContactDetails peoRegDetails = new PEO_019_PEO_Registration_ContactDetails(driver);
		PEO_001_ProfessionalEmployerOrganizationRegistration pemo = new PEO_001_ProfessionalEmployerOrganizationRegistration(driver);
		test.log(Status.INFO, "Logging to the application");
		stepDef.login(prop.getProperty("CSR_UserID") , prop.getProperty("CSR_Pass"));
		test.log(Status.INFO, "Navigating to professional employer organization");
		home.navigateToPeoRegister();
		test.log(Status.PASS, "PEO Register");
		peoRegDetails.clickContinueButton();
		test.log(Status.INFO, "Clicking on the button");
		 sleep(2000);
		 PEOPage.groupRegPeo.click();
		 stepDef.enterTextbox("Name of Professional Employer Organization","Test_auto"+stepDef.createRandomInteger(1000,9999));
		 stepDef.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+stepDef.createRandomInteger(1000,9999));
		 stepDef.screenShot("peor", "Pass", "Professional Employer Organization Registration");
		 stepDef.clickButtonContains("Save & Continue");
	     sleep(2000);
	    
		test.log(Status.INFO, "Entering the Details");
		test.log(Status.PASS, "Entered the details");
		stepDef.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
		   long number = stepDef.createRandomInteger(10000,99999);
		   String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
			 String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		stepDef.enterTextboxContains("Employer Registration Number", ernValue);
		stepDef.selectDropdown("Type of Legal Entity", " Corporation ");
		stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		stepDef.selectRadioQuestions("Type of Ownership", "Public");
		stepDef.enterTextboxContains("Fiscal Year Start Date", "02/01/2023");
		stepDef.screenShot("IndividualPeo","Pass","Professional Employer Organization Registration");
		stepDef.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		stepDef.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Account Details");
		try {
			 PEOPage.peoRadioButton.click();
			stepDef.selectRadioInTable(ernValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}
		stepDef.screenShot("Unemployment Insurance", "PASS", "Unemployment Insurance Account Details");
	     
		stepDef.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
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
	    stepDef.screenShot("CurrentAdditionalAddress","Pass","Verify Current Additional Address(es) in New York");
	    stepDef.clickButtonContains("Continue");
	    Thread.sleep(2000);
	    stepDef.screenShot("Mailing Address","Pass","Mailing Address");
	    stepDef.clickButtonContains("Save & Continue ");
	    Thread.sleep(2000);
	    stepDef.enterTextboxContains("Address Line 1","PrioraddressLine1"+stepDef.createRandomInteger(1000,9999));
	    stepDef.enterTextboxContains("Address Line 2","PrioraddressLine2"+stepDef.createRandomInteger(1000,9999));
	    stepDef.enterTextboxContains("City", "NewYork");
	    stepDef.enterTextboxContains("Zip Code","13429");
	    stepDef.enterTextboxContains("other name(s)","automation");
	    stepDef.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
	    stepDef.enterTextboxContains("Successor(s) Name","AutoSuccessor");
	    stepDef.screenShot("Prior Address","Pass","Prior Address(es) in New York");
	    stepDef.clickButtonContains("Save & Continue");
	    Thread.sleep(2000);
	    stepDef.screenShot("verifyPriorAddress","Pass","Verify Prior Address(es) in New York");
	    stepDef.clickButtonContains("Continue");
	    Thread.sleep(2000);
	    stepDef.enterTextboxContains("Entity or Person","Automation_entity");
	    stepDef.enterTextboxContains("Ownership Percentage","50");
	    stepDef.enterTextboxContains("Address Line 1","owneraddressLine1"+stepDef.createRandomInteger(1000,9999));
	    stepDef.enterTextboxContains("Address Line 2","owneraddressLine2"+stepDef.createRandomInteger(1000,9999));
	    stepDef.enterTextboxContains("City","NewYork");
	    stepDef.enterTextboxContains("Zip Code","13430");
	    stepDef.screenShot("Ownership Information - privately or closely held company", "PASS", "Ownership Information - privately or closely held company");
		stepDef.clickButtonContains("Save & Continue ");
		Thread.sleep(2000);
		try {
		     PEOPage.uspsSuggestedAddress.click();
		     stepDef.screenShot("VerifyContactDetails","Pass","UspsAddress");
		     PEOPage.UspsContinueButton.click();
		     sleep(2000);
		     }
		     catch(Exception e) {}
		     
		stepDef.screenShot("Verify Ownership Information", "PASS", "Verify Ownership Information");
		stepDef.clickButtonContains("Continue ");
		Thread.sleep(2000);
	
		//Prior Ownership Information
		stepDef.enterTextboxContains("Entity or Person", "Automation_entity");
		stepDef.enterTextboxContains("Ownership Percentage", "40");
		stepDef.enterTextboxContains("Address Line 1", "ownershipAddressLine1"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("Address Line 2", "ownershipAddressLine2"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("City","NewYork");
	    stepDef.enterTextboxContains("Zip Code","13430"); 	
	    stepDef.screenShot("Prior Ownership Information", "PASS", "Prior Ownership Information");
	    stepDef.clickButtonContains("Save & Continue ");
	    peoPg.uspsAddress.click();
	    stepDef.screenShot("UspsAddress","Pass","UspsAddress");
	    peoPg.UspsContinueButton.click();
	    Thread.sleep(2000);
	    stepDef.screenShot("Verify Prior Ownership Information", "PASS", "Verify Prior Ownership Information");
	    stepDef.clickButtonContains("Continue ");
	    stepDef.screenShot("Submission Instructions and Responsibilities", "PASS", "Submission Instructions and Responsibilities");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    
	    //upload files
	    stepDef.selectCheckbox("Proof of NYS Workers");
	    stepDef.selectLink("Proof of NYS Workers", "Browse");
		 sleep(2000);
		 stepDef.uploadDoc("Sample.docx");
		 sleep(4000);
		 stepDef.clickButtonContains("Upload");
		 sleep(2000);
		 stepDef.screenShot("Upload Documents", "PASS", "Upload Documents");
		 stepDef.clickButtonContains("Save & Continue");
		 sleep(2000);
		 stepDef.clickButtonContains("Choose File");
		 sleep(2000);
		 stepDef.uploadDoc("PEO Client List template_TestData2.xls");
		 sleep(2000);
		 stepDef.screenShot("Upload Client List", "PASS", "Upload Client List");
		 stepDef.clickButtonContains("Continue");    stepDef.screenShot("Verify Client List", "PASS", "Verify Client List");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    //stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)", "123456789");
	    stepDef.clickButtonContains(" Search ");
	    Thread.sleep(2000);
        peoPg.addPeoMember.click();
        Thread.sleep(2000);
        stepDef.enterTextboxContains("PEO Member Name", "test321");
        stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)", "401101502");
        stepDef.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?", "Yes ");
        stepDef.enterTextboxContains("Employer Registration Number", "1691721");
        stepDef.enterTextboxContains("Address Line 1", "primaryAddressLine1"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("Address Line 2", "primaryAddressLine2"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("City","NewYork");
		stepDef.selectDropdown("State", " New York ");
	    stepDef.enterTextboxContains("Zip Code","13430");
	    stepDef.enterTextboxContains("Phone Number",Long.toString(stepDef.createRandomInteger(10000000,99999999))+Long.toString(stepDef.createRandomInteger(10,99)));
		stepDef.enterTextboxContains("Business Email Address","autoTest"+Long.toString(stepDef.createRandomInteger(10000,99999))+"@gmail.com");
		Thread.sleep(2000);
		stepDef.selectRadio("Different");
		stepDef.enterTextboxContains("Address Line 1", "peoMembersAddressLine1"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("Address Line 2", "peoMembersAddressLine2"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("City","NewYork");
		stepDef.selectDropdown("State", " New York ");
	    stepDef.enterTextboxContains("Zip Code","13430");
	    stepDef.screenShot("PEO Member Information", "PASS", "PEO Member Information");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Save & Continue ");
	    peoPg.uspsAddress.click();
	    peoPg.currentAdditionalAddress.click();
	    stepDef.screenShot("UspsAddress","Pass","UspsAddress");
	    peoPg.UspsContinueButton.click();
	    Thread.sleep(2000);
	    stepDef.screenShot("Verify Current Additional Address(es) in New York", "PASS", "Verify Current Additional Address(es) in New York");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Continue ");
	    stepDef.screenShot("Prior Address(es) in New York", "PASS", "Prior Address(es) in New York");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Save & Continue ");
	    stepDef.screenShot("Verify Prior Address(es) in New York", "PASS", "Verify Prior Address(es) in New York");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Choose File");
		sleep(2000);
		stepDef.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(4000);
		stepDef.clickButtonContains("Continue");
		sleep(2000);
		
	    Thread.sleep(2000);
	    stepDef.screenShot("Upload Client List", "PASS", "Upload Client List");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    stepDef.screenShot("Verify Client List", "PASS", "Verify Client List");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    stepDef.screenShot("List of Members of PEO Group", "PASS", "List of Members of PEO Group");
	    driver.findElement(By.xpath("//a[@class='static-link']")).click();
	    Thread.sleep(2000);
	    
	    //adding again peo members
	    stepDef.clickButtonContains(" Search ");
	    Thread.sleep(2000);
        peoPg.addPeoMember.click();
        stepDef.enterTextboxContains("PEO Member Name", "test321");
        stepDef.enterTextboxContains("Federal Employer Identification Number (FEIN)", "401101502");
        stepDef.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?", "Yes ");
        stepDef.enterTextboxContains("Employer Registration Number", "1691721");
        stepDef.enterTextboxContains("Address Line 1", "primaryAddressLine1"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("Address Line 2", "primaryAddressLine2"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("City","NewYork");
		stepDef.selectDropdown("State", " New York ");
	    stepDef.enterTextboxContains("Zip Code","13430");
	    stepDef.enterTextboxContains("Phone Number",Long.toString(stepDef.createRandomInteger(10000000,99999999))+Long.toString(stepDef.createRandomInteger(10,99)));
		stepDef.enterTextboxContains("Business Email Address","autoTest"+Long.toString(stepDef.createRandomInteger(10000,99999))+"@gmail.com");
		Thread.sleep(2000);
		stepDef.selectRadio("Different");
		stepDef.enterTextboxContains("Address Line 1", "peoMembersAddressLine1"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("Address Line 2", "peoMembersAddressLine2"+stepDef.createRandomInteger(1000,9999));
		stepDef.enterTextboxContains("City","NewYork");
		stepDef.selectDropdown("State", " New York ");
		stepDef.selectDropdown("Country", " United States ");
	    stepDef.enterTextboxContains("Zip Code","13430");
	    stepDef.screenShot("PEO Member Information", "PASS", "PEO Member Information");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Save & Continue ");
	    peoPg.uspsAddress.click();
	    peoPg.currentAdditionalAddress.click();
	    stepDef.screenShot("UspsAddress","Pass","UspsAddress");
	    peoPg.UspsContinueButton.click();
	    Thread.sleep(2000);
	    stepDef.screenShot("Verify Current Additional Address(es) in New York", "PASS", "Verify Current Additional Address(es) in New York");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Continue ");
	    stepDef.screenShot("Prior Address(es) in New York", "PASS", "Prior Address(es) in New York");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Save & Continue ");
	    stepDef.screenShot("Verify Prior Address(es) in New York", "PASS", "Verify Prior Address(es) in New York");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Continue ");
	    sleep(2000);
	    stepDef.clickButtonContains("Choose File");
		sleep(2000);
		stepDef.uploadDoc("PEO Client List template_TestData2.xls");
		sleep(4000);
		stepDef.clickButtonContains("Continue");
		sleep(2000);
		
	    Thread.sleep(2000);
	    stepDef.screenShot("Upload Client List", "PASS", "Upload Client List");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    stepDef.screenShot("Verify Client List", "PASS", "Verify Client List");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    stepDef.screenShot("List of Members of PEO Group", "PASS", "List of Members of PEO Group");
	    stepDef.clickButtonContains("Continue ");
	    Thread.sleep(2000);
	    stepDef.screenShot("PEO Details Review screen", "PASS", "PEO Details Review screen");
	    stepDef.clickButtonContains("Save & Continue ");
	    Thread.sleep(2000);
	    stepDef.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member", "Testing");
	    stepDef.screenShot("Declaration", "PASS", "Declaration");
	    stepDef.clickButtonContains("Save & Continue ");
	    Thread.sleep(2000);
	    stepDef.screenShot("Group Guaranty and Statement of Acknowledgment", "PASS", "Group Guaranty and Statement of Acknowledgment");
	    stepDef.clickButtonContains("Accept & Submit ");
	    Thread.sleep(2000);
	    stepDef.screenShot("Completion", "PASS", "Register/Renew Confirmation");
	    Thread.sleep(2000);
	    stepDef.clickButtonContains("Home");
	    
	    //datebase querry
	    stepDef.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='648901228')");
	           
	}

}
	
