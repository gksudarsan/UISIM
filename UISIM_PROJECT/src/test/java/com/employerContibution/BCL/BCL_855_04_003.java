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

public class BCL_855_04_003 extends TestBase {
	@Test
	public void BCL_855_04_003() throws Exception
	{
		
		 test = report.createTest("BCL.855.04.003 - Verify CSR can search ERN and put single NPRs for an SSN on hold or release with status is 'Certified' and reason is Hearing Requested'");
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
		 	 Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE REGISTRATION_STATUS = 'C'  and EAN = '0464364'","EAN");
				
				String eanNumber=databaseResults.get("EAN");//0464364
			commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("updateNPRReferral","Pass","Update NPR referral To treasury");					
			commonFuntions.selectActionTableParameterizedId(eanNumber, 1, 1, "Update NPR Referral to Treasury – Enter ERN/SSN/FEIN","dataTableId","checkBox","");	
			commonFuntions.screenShot("updateNPRReferral12","Pass","Update NPR referral To treasury");					
			commonFuntions.clickButtonContains("Continue");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			BclPage.updateReferralCheckBox.click();
			BclPage.updateReferralStatus.click();
			sleep();
			BclPage.updateReferralStatusCertifiedValue.click();
			sleep();
			BclPage.updateReferralReason.click();
			sleep();
			BclPage.updateReferralReasonHearingRequestedValue.click();
			sleep();
			commonFuntions.clickButtonContains("Update");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			commonFuntions.screenShot("updateNPRReferral5","Pass","Update NPR referral To treasury");
			commonFuntions.Label("Changes made to NPR have been saved/updated successfully.");
			
		 
		 
		 			
	}

}
