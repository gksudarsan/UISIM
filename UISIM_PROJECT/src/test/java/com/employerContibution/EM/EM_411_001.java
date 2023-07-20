package com.employerContibution.EM;

import java.util.Map;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.SREG_434;
import com.ui.pages.SREG_435;
import com.ui.pages.SREG_541;
import com.ui.pages.SUC_002;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;
@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_411_001 extends TestBase{

	@Test
	public void EM_411_001() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		SREG_435 sreg435 = new SREG_435(driver);
		test = report.createTest("EM.411.001:Verify CSR is able to update account status of employer account 'Liable' ");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		HomePage home = new HomePage(driver);
		employerManagement empManage = new employerManagement(driver);
		SUC_002 suc = new SUC_002(driver);
		
		//----DB-----
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN  AND FEIN IS NOT NULL AND ACCOUNT_STATUS='FUTR' AND REGISTRATION_STATUS='C'",
				"EAN");
		String eanNumber = databaseResults.get("EAN");
		System.out.println("The EAN is:" +eanNumber);
		
		//----Login----
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.waitForLoadingIconToDisappear();
		
		//---Menu Click---
		
		cf.clickMenu("menu");
		sleep(2000);
		cf.screenShot("Menu", "Pass", "Menu page");
		sleep();
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		home.empAccMaintenance.click();
		sleep();
		cf.screenShot("Menu", "Pass", "Menu selected");
		home.maintainAccStatus.click();
		cf.waitForLoadingIconToDisappear();
		
		//----SREG 434
		cf.screenShot("MaintainAccountStatusEnterERN", "Pass", "Employer Registration Numbe:SREG-434");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("MaintainAccountStatusEnterERN_ErrorMessage", "Pass", "Required Error-Employer Registration Numbe:SREG-434");
		cf.errorLabel("Required");
		cf.enterTextboxContains("Employer Registration Number", "1111111");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("ERNDoesNotExitInTheSystem", "Pass", "Invalid Employer Registration Number. No ERN matched.");
		//cf.errorContent("Invalid Employer Registration Number. No ERN matched.");
		test.log(Status.INFO, "ERN::"+eanNumber);
		cf.enterTextboxContains("Employer Registration Number",eanNumber);
		sleep(2000);
		cf.screenShot("Valid ERN entered ", "Pass", "Successfully enterd ERN on - Enter ERN(SREG-434) page");
		cf.clickButtonContains("Continue");
		cf.waitForLoadingIconToDisappear();
		
		//-------SREG-435-------
		cf.screenShot("AccountUpdateStatus", "Pass", "Update Account Status");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("ErrorRequired_AccountUpdateStatus", "Pass", "Update Account Status Error Required:SREG-435");
		sleep();
		cf.selectDropdown("Status of Employer Account", " Liable ");
		sleep(2000);
		cf.screenShot("Update Account Status ", "Pass", "Successfully enterd info on SREG-435 page");
		sleep(2000);
		empManage.suspensionDateQtrDropdown_SREG435.click();
		sleep();
		cf.selectFromDropdown(" 4 ");
		sleep();
		empManage.suspensionDateYearDropdown_SREG435.click(); 
		sleep();
		cf.selectFromDropdown(" 2023 ");
		sleep();
		cf.screenShot("Update Account Status ", "Pass", "Successfully enterd info on SREG-435 page");
		sleep(2000);
		sreg435.enterComments.sendKeys("Testing ");
		sleep(2000);
		cf.screenShot("Update Account Status ", "Pass", "Successfully enterd info on SREG-435 page");
		sleep(2000);
		cf.ScrollMenu("Send a Closure Letter (IA31.6)?");
		sreg435.radioButton.click();
		sleep(2000);
		empManage.sourceId_SREG435.click(); 
		sleep();
		empManage.IA602_SREG435.click(); 
		sleep(2000);
		empManage.sourceTypeId_SREG435.click();
		sleep(); 
		empManage.CashWages_SREG435.click();
		sleep(); 
		cf.screenShot("Update Account Status ", "Pass", "Successfully enterd complete info on SREG-435 page");
		sleep(2000);
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
		
		//----SUC 002
		sleep(2000);
		cf.screenShot("Modify Employer Account Details", "Pass", "Successfully landed on SUC 002");
		sleep(2000);
		suc.successMessagePatialText.isDisplayed();
		sleep(2000);
		cf.clickButtonContains("Home ");
		sleep(4000);
		cf.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
			
				//---Executed & completed by Palak											 
	}

}
