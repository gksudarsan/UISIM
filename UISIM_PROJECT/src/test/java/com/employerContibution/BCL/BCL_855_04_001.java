//------------------------Palak---------------------------------------

package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_855_04_001 extends TestBase {
	@Test
	public void BCL_855_04_001() throws Exception
	{
		
		 test = report.createTest("BCL.855.03.001 - Verify CSR can search ERN and put single NPRs for an SSN on hold with status is 'Hold' and reason is 'Hold Action and/or Another Flag on Account'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 BclPage BclPage = PageFactory.initElements(driver, BclPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("menu");
		 sleep(2000);
		 commonFuntions.ScrollMenu("Employer Collection");
		 commonFuntions.clickMenu("Employer Collection");
		 sleep(1000);
		 commonFuntions.clickMenu("Confidential Source");
		 sleep(1000);
		 commonFuntions.screenShot("menu","Pass","Update NPR Referral to Treasury – Enter ERN/SSN/FEIN");
		 commonFuntions.clickMenu("Update NPR Referral to Treasury – Enter ERN/SSN/FEIN");
		 sleep(1000);
		 commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.screenShot("ErrorLabel1","Pass","A value must be provided for either SSN or ERN or FEIN.");
			commonFuntions.errorContent("A value must be provided for either SSN or ERN or FEIN.");
			sleep(2000);
			commonFuntions.enterTextboxContains("Employer Registration Number", "0000000");
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.screenShot("ErrorLabel2","Pass","The response to Employer Registration Number is an invalid Employer Registration Number");
			commonFuntions.errorLabelContains(" The response to Employer Registration Number is an invalid Employer Registration Number.","Employer Registration Number");
			sleep(2000);
			commonFuntions.enterTextboxContains("Employer Registration Number", "");
			commonFuntions.enterTextboxContains(" FEIN ", "000000000");	
			
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.screenShot("ErrorLabel3","Pass","A value must be provided for either SSN or ERN or FEIN");
			commonFuntions.errorLabelContains(" The response to FEIN is an invalid FEIN."," FEIN ");
			sleep(2000);
			commonFuntions.enterTextboxContains(" FEIN ", "");	
			commonFuntions.enterTextboxContains("Employer Registration Number", "2345678");
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("ErrorLabel4","Pass","No Records Found");
			commonFuntions.errorContent("No Records found");
			sleep(2000);
			 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE REGISTRATION_STATUS = 'C'  and EAN = '0464364'","EAN");
				
				String eanNumber=databaseResults.get("EAN");//0464364
			commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("updateNPRReferral","Pass","Update NPR referral To treasury");					
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.screenShot("updateNPRReferral1","Pass","Update NPR referral To treasury");
			commonFuntions.errorContent("Please select a record to proceed.");
			commonFuntions.selectActionTableParameterizedId(eanNumber, 1, 1, "Update NPR Referral to Treasury – Enter ERN/SSN/FEIN","dataTableId","checkBox","");	
			commonFuntions.screenShot("updateNPRReferral12","Pass","Update NPR referral To treasury");					
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.clickButtonContains("Update");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("updateNPRReferral3","Pass","Update NPR referral To treasury");
			commonFuntions.errorContent("Please select a record to proceed.");			
			BclPage.updateAllCheckBox.click();
			sleep(2000);
			commonFuntions.screenShot("updateNPRReferral4","Pass","Update NPR referral To treasury");					
			
			sleep(2000);
			commonFuntions.errorContent("Status and Reason should be selected for selected row(s).");
			BclPage.updateAllCheckBox.click();
			sleep(2000);
			BclPage.viewGenerateLetter.click();
			sleep(2000);
			commonFuntions.screenShot("Confirmation","Pass","Notice of pending referralList confirmation");					
			
		 
		 
		 			
	}

}
