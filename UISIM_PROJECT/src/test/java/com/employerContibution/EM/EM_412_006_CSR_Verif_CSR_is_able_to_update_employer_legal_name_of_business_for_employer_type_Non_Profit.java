package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_412_006_CSR_Verif_CSR_is_able_to_update_employer_legal_name_of_business_for_employer_type_Non_Profit extends TestBase {

	
	
		@Test(priority = 1, description = "Verify CSR is able to update employer legal name of business for employer type 'Non-Profit'.", groups = { "Regression" })
		public void EM_412_006()throws Exception {
			test=report.createTest("Verify CSR is able to update employer legal name of business for employer type 'Non-Profit'");
			commonStepDefinitions CommFun = new commonStepDefinitions();
			EmployerRegisterPage employerRegisterPage = new EmployerRegisterPage(driver);
			HomePage home = new HomePage(driver);
			//------Login
			CommFun.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			CommFun.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
			//Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN LIKE '8%' ORDER BY UPDATED_TS","EAN");
			Map<String, String> databaseResults = CommFun.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL","EAN");
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
	        
	        employerRegisterPage.legalEntityNameId.sendKeys("Super Market Inc");
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
			
		    CommFun.selectDropdown("Employer Type", " Non-Profit ");
		    sleep(2000);
		    CommFun.selectDropdownEquals("Type of Legal Entity", " Limited Liability Company (All Types) ");
		    sleep(2000);
			CommFun.selectDropdownEquals("Source", " Miscellaneous ");
			sleep(2000);
		    CommFun.selectDropdown("Source Type", " Other Local Agency ");
		    sleep(2000);
		    CommFun.screenShot("Modify Employer Account Details", "Pass", "Entered details on SREG 030");
		    sleep(2000);
		    CommFun.clickButton("Submit ");
		    sleep(2000);
		   
		   // CommFun.screenShot("Modify Employer Account Details", "Pass", "ENtered details on SREG 030");
		   // CommFun.clickButton("Submit ");
		    //----SUC 002
		    sleep(2000);
		    CommFun.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
		    sleep(2000);
		    CommFun.Label("The Account Information has been succesfully saved");
		    CommFun.clickButtonContains("Home ");
		    CommFun.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		
		  //---re-executed completed by Palak
		
		}
}
