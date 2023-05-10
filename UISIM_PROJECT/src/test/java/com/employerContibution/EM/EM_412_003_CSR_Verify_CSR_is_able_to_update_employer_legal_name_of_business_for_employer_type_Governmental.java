package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_412_003_CSR_Verify_CSR_is_able_to_update_employer_legal_name_of_business_for_employer_type_Governmental extends TestBase {
	@Test(priority = 1, description = "Verify CSR is able to update employer legal name of business for employer type 'Governmental'.", groups = { "Regression" })
	public void EM_412_002()throws Exception {
		test=report.createTest("Verify CSR is able to update employer legal name of business for employer type 'Governmental'");
		commonStepDefinitions CommFun = new commonStepDefinitions();
		LoginPage LogPage = new LoginPage(driver);
		HomePage home = new HomePage(driver);
		EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
		
		//------Login
		CommFun.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
	
		Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '8%' ORDER BY UPDATED_TS","EAN");
		 
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
		home.empAccMaintenance.click();
		sleep(2000);
		CommFun.screenShot("Menu", "Pass", "Menu selected");
		home.maintainAccounts.click();
		sleep(2000);
		
		//------SREG 027
		CommFun.screenShot("Employer Account Maintenance – Enter ERN", "Pass", "Successfully landed on SREG 027");
		CommFun.enterTextboxContains("Employer Registration Number",ErnNum);
		
		sleep(2000);
		CommFun.screenShot("Employer Account Maintenance – Enter ERN", "Pass", "Entered information on SREG 027");
		CommFun.clickButton("Continue ");
		sleep(2000);
		//-----SREG 030
				CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SREG 030");
				sleep(2000);
				employerRegisterPage.legalEntityNameId.clear();
		        employerRegisterPage.legalEntityNameId.sendKeys("Albany Medical");
		        sleep(4000);
		        employerRegisterPage.commentId.click();
		        sleep(2000);
		        employerRegisterPage.commentId.sendKeys("Updating legal bussiness name");
		        sleep(2000);
				CommFun.selectLink("Please select a file to upload that provides proof of name change.", "Browse");
				sleep(2000);
				CommFun.uploadDoc("Sample.docx");
			    sleep(2000);
				CommFun.screenShot("Modify Employer Account Details", "Pass", "Entered details on SREG 030");
				CommFun.enterTextboxContains(" Business Phone Number ", "6787889995");
				CommFun.selectDropdown("Employer Type", " Governmental Employer ");
				CommFun.selectDropdownEquals("Source", " Miscellaneous ");
			    CommFun.selectDropdown("Source Type", " Labor Standards ");
			    sleep(2000);
			    CommFun.screenShot("Modify Employer Account Details", "Pass", "ENtered details on SREG 030");
			    CommFun.clickButton("Submit ");
			    //----SUC 002
			    sleep(2000);
			    CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
			    sleep(2000);
			    CommFun.clickButtonContains("Home ");
			    CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
			    
				
}}
