package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_462_01 extends TestBase{

	@Test()
	public void EL_462_01_Indi_Exempt() throws Exception {

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOGR'", "FEIN");
		String FEIN = databaseResults.get("FEIN");
		System.out.println("feinNumber is" + FEIN);

		test = report.createTest("EL.462.01 - Verify CSR can search PEO and update PEO conversion 'PEO Group to PEO Individual' ");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		cf.screenShot("Menu", "Pass", "Manage PEO");
		cf.clickMenu("Manage PEO");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		cf.enterTextboxContains("(FEIN)", FEIN);
		cf.screenShot("file1", "Pass", "Searching with FEIN ");
		cf.clickButtonContains("search");
		Thread.sleep(2000);
		cf.selectRadioWithFeinValue(FEIN);
		cf.clickButton("Continue ");
		Thread.sleep(4000);
		cf.selectDropdown("PEO Conversion", " PEO Group to PEO Individual ");
		cf.screenShot("DropDownValue", "Pass", "Selecting the dropdown value PEO Individual to PEO Exempt ");
		cf.clickButtonContains(" CONVERT ");
		cf.screenShot("", "Pass", "");
		/*try {
		cf.selectDropdown("States in which the PEO is licensed or registered as a PEO", " Alaska ");
		cf.enterTextboxContains("State agency that issued it.","homeagency");
		cf.selectRadio("Registration Number");
		cf.enterTextboxContains("Registration Number","2738383838");
		}
		catch (Exception e)
		{
			System.out.println("click on save and continue....");
		}*/
		cf.screenShot("GeneralInformation", "Pass", "General Information");
		cf.clickButtonContains("Save & Continue");
		sleep();
		 try {
	    	 PEOPage.peoRadioButton.click();
	    	 cf.screenShot("UnemploymentInsuranceAccountDetails", "Pass", "Unemployment Insurance Account Details");
		     //cf.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		     }
		     catch(Exception e) {}
		cf.screenShot("UnemploymentInsuranceAccountDetails", "Pass", "Unemployment Insurance Account Details");
		cf.clickButtonContains("Save & Continue");
		sleep();
		cf.screenShot("AddressInformation", "Pass", "Address Information");
		cf.clickButtonContains("Save & Continue");
		sleep(2000);
		try {
		PEOPage.uspsAddress.click();
	     PEOPage.currentAdditionalAddress.click();
	     cf.screenShot("UspsAddress2","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();
	     sleep(2000);}
		catch(Exception e) {}
		cf.screenShot("VerifyCurrentAdditionalAddress", "Pass", "VerifyCurrentAdditionalAddress");
		cf.clickButtonContains("Continue");
		sleep();
		cf.screenShot("MailingAddress", "Pass", "MailingAddress");
		cf.clickButtonContains("Save & Continue");
		sleep();
		cf.screenShot("prioraddress", "Pass", "prioraddress");
		cf.clickButtonContains("Save & Continue");
		sleep();
		cf.screenShot("VerifypriorAddress", "Pass", "VerifypriorAddress");
		try {cf.clickButtonContains("Yes");
		sleep(2000);}
		catch(Exception e) {}
		cf.clickButtonContains("Continue");
		sleep();
		try {cf.clickButtonContains("Yes");}
		catch(Exception e) {}
		cf.screenShot("ownershipInformation", "Pass", "ownershipInformation");
		cf.clickButtonContains("Save & Continue");
		sleep(2000);
		cf.screenShot("VerifyownershipInfo", "Pass", "VerifyownershipInfo");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("priorOwner", "Pass", "priorOwner");
		cf.clickButtonContains("Save & Continue");
		sleep(2000);
		try {PEOPage.uspsAddress.click();
	     cf.screenShot("UspsAddress3","Pass","UspsAddress");
	     PEOPage.UspsContinueButton.click();	
	     sleep(2000);}
		catch(Exception e) {}
		cf.screenShot("Verifypriorownership", "Pass", "Verifypriorownership");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("Submission", "Pass", "Submission");
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("uploadDocs", "Pass", "uploadDocuments");
		cf.clickButtonContains("Save & Continue");
		sleep(2000);
		cf.clickButtonContains("Choose File");
		Thread.sleep(2000);
		cf.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		cf.clickButtonContains("Continue");
		Thread.sleep(2000);
		cf.screenShot("verifyClient","Pass","Verify Client List");
		cf.clickButtonContains("Continue");
		Thread.sleep(2000);
		
Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ORGANIZATION_TYPE='SPRI' AND COMPANY_TYPE='PRI' ORDER BY UPDATED_TS DESC","FEIN");
		
		String feinNumber=databaseResults1.get("FEIN");
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)",feinNumber);
		System.out.println(feinNumber);
		Thread.sleep(2000);
		//commonFuntions.clickButtonContains(" Search ");
		cf.clickButton(" Search ");
		cf.selectRadio("Select");
		cf.screenShot("PEO Member", "PASS", "Search For PEO Member");
		cf.clickButtonContains("Continue ");
		Thread.sleep(2000);
		cf.enterTextbox("PEO Member Name","Test_auto"+cf.createRandomInteger(1000,9999));
		
		//Random fein is not working - only existing fein is working from database
		//commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinNumber);
		cf.selectRadioQuestions("Does this PEO member already have an Unemployment Insurance Account?", "No");
		cf.enterTextboxContains("Address Line 1","ownerAddressLine1"+cf.createRandomInteger(1000,9999));
		cf.enterTextboxContains("Address Line 2","ownerAddressLine2"+cf.createRandomInteger(1000,9999));
		cf.enterTextboxContains("City","NewYork");
		cf.selectDropdown("State", "New York");
		cf.enterTextboxContains("Zip Code","13430");
		cf.selectDropdown("Country", "United States");
		cf.enterTextboxContains("Phone Number",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
		cf.enterTextboxContains("Business Email Address","autoTest"+Long.toString(cf.createRandomInteger(10000,99999))+"@gmail.com");
		Thread.sleep(2000);
		//commonFuntions.selectRadioQuestions("List the current address of each additional address the PEO Member maintains in New York", "Same As Physical Address");
		cf.selectRadio("Same As Physical Address");
		cf.screenShot("PEO member information", "Pass", "PEO Member Information");
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		try {
			PEOPage.uspsSuggestedAddress.click();
			PEOPage.mailingAddress.click();
			cf.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(3000);
		}
		catch(Exception e) {
			
		}
		
		cf.screenShot("peoDetails","Pass","Peo conversion");
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		cf.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+cf.createRandomInteger(10000,99999));
		cf.screenShot("Declaration","Pass","Declaration");
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(2000);
		cf.screenShot("Acknowledgement","Pass","Statement Of Acknowledgement");
		cf.clickButtonContains("Accept & Submit");
		Thread.sleep(2000);
		cf.screenShot("Completion","Pass","Register/Renew Confirmation");
		cf.selectRadio("Approved");
		cf.screenShot("Completion","Pass","conversion indi to exempt");
		cf.clickButtonContains("submit");sleep();
		cf.screenShot("Completion","Pass","Register/Renew Confirmation");
		cf.clickButtonContains("home ");
		em.Inquery(FEIN);
		
		test.log(Status.PASS, "Clicked on Home button");




	}
}
