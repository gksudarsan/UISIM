package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_074;
import com.ui.pages.SREG_EM_mod;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_321_002_CSR_Verify_CSR_is_able_to_Delete_Corporate_Officer_Owner_Details extends TestBase{

	
	@Test(priority = 1, description = "Verify Employer is able to Delete Corporate Officer/Owner Details", groups = { "Regression" })
	public void EM_321_002()throws Exception {
		test=report.createTest("Verify Employer is able to Delete Corporate Officer/Owner Details");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
		HomePage home = new HomePage(driver);
		SREG_EM_mod sreg = new SREG_EM_mod(driver);
		
		//------Login
		CommFun.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT WHERE ORGANIZATION_TYPE = 'CORP' AND EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_PARTNER tep GROUP BY EMPLOYER_ACCOUNT_ID HAVING COUNT(EMPLOYER_ACCOUNT_ID) > 1)","EAN");
		 
		String ErnNum=databaseResults.get("EAN");
		//CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		System.out.println(ErnNum);
		sleep(2000);
	
		//---Menu Click---
		
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu page");
		CommFun.clickMenu("Menu");
		sleep(2000);
		CommFun.ScrollMenu("Account Maintenance");
		home.accountMaintenance.click();
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu selected");
		home.MaintainBusinessOwnership.click();
		sleep(2000);
		
		//-----SREG 029
		CommFun.screenShot("Maintain Bussiness ownership Details ERN ", "Pass", "Successfully landed on SREG 029");
		sleep(2000);
		CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		sleep(2000);
		CommFun.screenShot("Maintain Bussiness ownership Details ERN ", "Pass", "Entered details on SREG 029");
		sleep(2000);
		CommFun.clickButton("Continue ");
		sleep(2000);
	
		
		//------SREG 702
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Successfully landed on SREG 702");
		sleep(2000);
		sreg.actionDeleteCorprate.click();
		sleep(2000);
		CommFun.screenShot("Pop up", "Pass", "Successfully landed on popup");
		sleep(2000);
		CommFun.clickButton(" Yes ");
		sleep(2000);
		CommFun.screenShot("Corporate Officer/Owner", "Pass", "Deleted members from table on SREG 702");
		sleep(2000);
        CommFun.screenShot("Completed Testcase", "Pass", "Successfully landed on home page test completed  ");
		
	    //-------Completed by Palak
	    
		
		
		
		
	    
	    
	    
		 
		}
}
