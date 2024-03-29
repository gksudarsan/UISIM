package com.employerContibution.EL;



import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_322_02_001 extends TestBase
{
String EAN = "";

	
	@Test(priority=1, description = "EL.322.02.001: Verify CSR can search for a client and add to a PEO\r\n"
			+ "'.",groups = {"Regression"})
	public void EL_322_014() throws Exception
	{
		employerManagement em =  new employerManagement();
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		Map<String, String> databaseResults = PEOPage.database_SelectQuery("SELECT FEIN,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS desc");
		String feinValue =databaseResults.get("Fein");
//		String ernValue = databaseResults.get("Ean");
		System.out.println("feinValue is"+feinValue);
		 test = report.createTest("EL.322.02.001: Verify CSR can search for a client and add to a PEO\r\n"
		 		+ "");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 
		 
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("Menu");	
		 sleep();
		 commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		 sleep();
		 PEOPage.menuPeo.click();	
		 sleep();
		 commonFuntions.screenShot("Menu","Pass","Register PEO");
		 sleep();
		 commonFuntions.clickMenu("Register PEO");		
		 sleep();
		 commonFuntions.screenShot("PeoRegistration","Pass","PEO Registration - Contact Details");	
		 sleep();
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(5000);
	     PEOPage.individualPeo.click();	  
	     sleep(2000);
	     commonFuntions.enterTextbox("Name of Professional Employer Organization","Test_auto"+commonFuntions.createRandomInteger(1000,9999));
//	     commonFuntions.enterTextbox("Additional Names, if any, under which the PEO’s Conduct Business currently","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextbox("Additional name(s), if any, under which the PEO currently conducts business","auto_test"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(5000);
	     commonFuntions.screenShot("IndividualPeo","Pass","Professional Employer Organization Registration");	     
	     commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "Yes");
	     sleep();
	     
		// String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		// String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
	     String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
	     commonFuntions.enterTextboxContains("Employer Registration Number",ernValue);
	     commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
	     commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
	     commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
//	     commonFuntions.selectRadioQuestions("Type of Ownership", "Public Ownership");
	     commonFuntions.selectRadioQuestions("Type of Ownership", "Public");
	     commonFuntions.enterTextboxContains("Fiscal Year Start Dat","02/01/2023");
	     commonFuntions.screenShot("IndividualPeo","Pass","Professional Employer Organization Registration");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     commonFuntions.screenShot("UnemploymentInsurance","Pass","Unemployment Insurance Account Details");
	     try {
	    	 Thread.sleep(4000);
	     commonFuntions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
	     }
	     catch(Exception e) {}
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(5000);
	     PEOPage.addressLine1.sendKeys("addressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressLine2.sendKeys("addressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     PEOPage.addressCity.sendKeys("NewYork");
	     PEOPage.addressZip.sendKeys("13420");
	      
	     commonFuntions.enterTextboxContains("Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
	     commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(5000);
	     PEOPage.uspsAddress.click();
	     PEOPage.currentAdditionalAddress.click();
	     commonFuntions.screenShot("UspsAddress","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();
	    
	     Thread.sleep(5000);
	     commonFuntions.screenShot("CurrentAdditionalAddress","Pass","Verify Current Additional Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(5000);
	     commonFuntions.screenShot("Mailing Address","Pass","Mailing Address");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(5000);
	     commonFuntions.enterTextboxContains("Address Line 1","PrioraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","PrioraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13429");
	     commonFuntions.enterTextboxContains("other name(s)","automation");
	     commonFuntions.enterTextboxContains("Predecessor(s) Name","AutoPredecessor");
	     commonFuntions.enterTextboxContains("Successor(s) Name","AutoSuccessor");
	     commonFuntions.screenShot("Prior Address","Pass","Prior Address(es) in New York");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(5000);
	     commonFuntions.screenShot("verifyPriorAddress","Pass","Verify Prior Address(es) in New York");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(5000);
	     commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
	     commonFuntions.enterTextboxContains("Ownership Percentage","40");
	     commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
	     commonFuntions.enterTextboxContains("City","NewYork");
	     commonFuntions.enterTextboxContains("Zip Code","13430");
	     commonFuntions.screenShot("OwnershipInformation","Pass","Ownership Information - privately or closely held company");
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     try {
	    	 PEOPage.uspsAddress.click();
	    	 sleep(2000);
	    	 commonFuntions.safeJavaScriptClick(PEOPage.UspsContinueButton);
	     }catch(Exception e) {
	    	 System.out.println("Pop up not displayed");
	     }
	     sleep(4000);
//	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
//	     commonFuntions.clickButtonContains("Continue");
//	     Thread.sleep(5000);
//	     commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
//	     sleep();
//	     commonFuntions.enterTextboxContains("Ownership Percentage","60");
//	     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
//	     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
//	     commonFuntions.enterTextboxContains("City","NewYork");
//	     commonFuntions.enterTextboxContains("Zip Code","13430");
//	     commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
//	     commonFuntions.clickButtonContains("Save & Continue");
//	     Thread.sleep(4000);
//	     PEOPage.uspsAddress.click();
//	     commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
//	     PEOPage.UspsContinueButton.click();	    
//	     Thread.sleep(4000);
	     commonFuntions.screenShot("verifyOnwershipInfo","Pass","Verify Ownership Information");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(4000);
	     commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
	     commonFuntions.clickButtonContains("Continue");
	     Thread.sleep(4000);	     
	     commonFuntions.selectCheckbox("Proof of NYS Workers");
	     commonFuntions.selectLink("Proof of NYS Workers", "Browse");
	     Thread.sleep(4000);
	     commonFuntions.uploadDoc("Sample.docx");
	     Thread.sleep(4000);
	     commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
	     commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
	     Thread.sleep(4000);
	     commonFuntions.uploadDoc("Sample.docx");
	     Thread.sleep(4000);
	     commonFuntions.selectCheckbox("Proof of $75k net worth or bond or a letter of credit for $75k");
	     commonFuntions.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
	     Thread.sleep(4000);
	     commonFuntions.uploadDoc("Sample.docx");
	     Thread.sleep(4000);
	     commonFuntions.clickButtonContains("Upload");
	     Thread.sleep(4000);
	     commonFuntions.clickButtonContains("Save & Continue");
	     Thread.sleep(4000);
	     //adding client information manually......................
	     em.addClientDatailsManually(EAN);
	     
	
	     
	}

}