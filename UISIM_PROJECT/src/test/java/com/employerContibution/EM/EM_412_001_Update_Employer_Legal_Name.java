package com.employerContibution.EM;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_027;
import com.ui.pages.SREG_030;
import com.ui.pages.SREG_503;
import com.ui.pages.SREG_504;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import net.bytebuddy.description.annotation.AnnotationList.Empty;
import stepDefinitions.commonStepDefinitions;

public class EM_412_001_Update_Employer_Legal_Name extends TestBase{


	@Test(priority = 1, description = "Test sample", groups = { "Regression" })
	public void Testing123() throws Exception {
		
		test = report.createTest("EM.412.001- Verify CSR is able to update employer legal name of business for employer type 'Business'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		HomePage home = new HomePage(driver);
		SREG_030 sreg030 = new SREG_030(driver);
		SREG_027 sreg27 = new SREG_027(driver);
		SUC_002 suc002 = new SUC_002(driver);
		
		
		test.log(Status.INFO, "Logging to the application");
<<<<<<< HEAD
		stepDef.login(prop.getProperty("CSR_UserID"), prop.getProperty("CSR_Pass"));
		test.log(Status.PASS, "Logged in to the application");
		test.log(Status.INFO, "Navigating to Maintain Accounts");
		home.navigateToMaintainAccounts();
		test.log(Status.PASS, "Navigated to Maintain Accounts");
		test.log(Status.INFO, "Entering ERN number");
		Map<String, String> ernOutput = stepDef.database_SelectQuerySingleColumn("SELECT ACCOUNT_STATUS,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS='SUSN'", "EAN");
		String ernValue = ernOutput.get("EAN");
//		String ernValue = "9923100";
		System.out.println("ERN : : "+ernValue);
		test.log(Status.INFO, "ERN : : "+ ernValue);
		test.log(Status.INFO, "Validating page title");
		sreg030.validatePageTitle();
		test.log(Status.PASS, "Validated page title");
		sreg27.enterERNNumber(ernValue);
		test.log(Status.PASS, "Entered the ERN number successfully");
		test.log(Status.INFO, "Entering form data and validating pre populated");
		sreg030.fillFormAndValidate();
		test.log(Status.PASS, "Entered form data and validated pre populated");
		sreg030.clickSubmit();
		test.log(Status.PASS, "Submit button clicked");
		suc002.validateEmployerAccountMSG();
		test.log(Status.PASS, "Success message validated");
		test.log(Status.PASS, "Test case completed successfully");
=======
		//stepDef.login(prop.getProperty("EMPLOYER_USER_4"), prop.getProperty("CSR_Pass"));
		//stepDef.login(COMMON_CONSTANT.EMPLOYER_USER_4, COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);

		commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_4.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
>>>>>>> refs/heads/master_27April
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		sleep();
		commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Employer Account Maintenance");
		
		empRegPage.employerAccountMaintanceMenu.click();
		//commonFunction.clickMenu("AccountMaintenanceEmployerAccountMaintenance");
		
		//00-00387
		
		// --- SREG-030 ---
		sleep(2000);
		commonFunction.screenShot("SREG30", "Pass", "Successful launch to Modify Employer Account Details");
		commonFunction.selectLink("Please select a file to upload that provides proof of name change.", "Browse");
	     sleep(2000);
	     commonFunction.uploadDoc("Sample.docx");
		commonFunction.clickButton("Submit ");
		
		// --- SUC - 002 ---
		sleep(2000);
		commonFunction.screenShot("SUC002", "Pass", "Successful uploaded sample file");
		commonFunction.clickButtonContains("Home");
		
		sleep(2000);
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		
//		commonFunction.selectTableWithoutId("Ledger", 5, 2, "Employer Account Rate Transaction History ")
//		sleep(2000);
		commonFunction.screenShot("SUC--3", "Pass", "Work Item Details page is displayed");
		/*
		 * test.log(Status.PASS, "Logged in to the application"); test.log(Status.INFO,
		 * "Navigating to Maintain Accounts"); home.navigateToMaintainAccounts();
		 * test.log(Status.PASS, "Navigated to Maintain Accounts");
		 * test.log(Status.INFO, "Entering ERN number"); sreg27.enterERNNumber();
		 * test.log(Status.PASS, "Entered the ERN number successfully");
		 * test.log(Status.INFO, "Validating page title"); sreg030.validatePageTitle();
		 * test.log(Status.PASS, "Validated page title"); test.log(Status.INFO,
		 * "Entering form data and validating pre populated");
		 * sreg030.fillFormAndValidate(); test.log(Status.PASS,
		 * "Entered form data and validated pre populated"); sreg030.clickSubmit();
		 * test.log(Status.PASS, "Submit button clicked");
		 * suc002.validateEmployerAccountMSG(); test.log(Status.PASS,
		 * "Success message validated"); test.log(Status.PASS,
		 * "Test case completed successfully");
		 */
	}
}
