

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

public class BCL_855_03_001 extends TestBase {
	@Test
	public void BCL_855_03_001() throws Exception
	{
		
		 test = report.createTest("BCL.855.03.001 - Verify CSR can search for the list of mailed NPR letters for an ERN");
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
		 commonFuntions.clickMenu("Notice of Pending Referral List – Enter ERN/SSN/FEIN");
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
			commonFuntions.errorContent("No Records Found");
			sleep(2000);
			 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT  B.SSN AS ssn,\r\n"
			 		+ "                            COALESCE (B.FIRST_NAME, ' ') ||\r\n"
			 		+ "                    COALESCE (B.MIDDLE_INITIAL, ' ') ||\r\n"
			 		+ "                    COALESCE (B.LAST_NAME, ' ') ||\r\n"
			 		+ "                    COALESCE (B.SUFFIX, '') as ownerName,\r\n"
			 		+ "                            A.FEIN AS fein,\r\n"
			 		+ "                            A.EAN AS employerRegistrationNo,\r\n"
			 		+ "                            A.ENTITY_NAME AS legalNameOfBusiness\r\n"
			 		+ "FROM T_EMPLOYER_ACCOUNT A \r\n"
			 		+ "                            INNER JOIN T_EMPLOYER_PARTNER B \r\n"
			 		+ "ON A.EMPLOYER_ACCOUNT_ID=B.EMPLOYER_ACCOUNT_ID","employerRegistrationNo");
				
				String eanNumber=databaseResults.get("employerRegistrationNo");//0464364
			commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("NoticeOfPendingReferralList","Pass","Notice of pending referralList");					
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.screenShot("NoticeOfPendingReferralList1","Pass","Notice of pending referralList error");
			commonFuntions.errorContent("Please select a record to proceed.");
			try {
				BclPage.pendingReferralSelectRadioButton.click();
			}catch(Exception e) {
			BclPage.selectRadioButton.click();	}
			commonFuntions.screenShot("NoticeOfPendingReferralList2","Pass","Notice of pending referralList");					
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			BclPage.nprNoticeDate.click();
			sleep(2000);
			commonFuntions.screenShot("NoticeOfPendingReferralList4","Pass","Notice of pending referralList");					
			commonFuntions.clickButtonContains("Previous");
			sleep(2000);
			BclPage.viewGenerateLetter.click();
			sleep(2000);
			commonFuntions.screenShot("Confirmation","Pass","Notice of pending referralList confirmation");					
			
		 
		 
		 			
	}

}
