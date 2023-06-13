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
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		cf.screenShot("NavigateToMaintainAccountStatus", "Pass", "Maintain Account Status:Enter ERN");
		cf.clickMenu("Maintain Account Status");
		cf.screenShot("MaintainAccountStatusEnterERN", "Pass", "Employer Registration Numbe:SREG-434");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("MaintainAccountStatusEnterERN_ErrorMessage", "Pass", "Required Error-Employer Registration Numbe:SREG-434");
		cf.errorLabel("Required");
		cf.enterTextboxContains("Employer Registration Number", "1111111");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		cf.screenShot("ERNDoesNotExitInTheSystem", "Pass", "Invalid Employer Registration Number. No ERN matched.");
		cf.errorContent("Invalid Employer Registration Number. No ERN matched.");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN  AND FEIN IS NOT NULL AND ACCOUNT_STATUS='FUTR' AND REGISTRATION_STATUS='C'",
				"EAN");
		String eanNumber = databaseResults.get("EAN");
		System.out.println("The EAN is:" +eanNumber);
		test.log(Status.INFO, "ERN::"+eanNumber);
		cf.enterTextboxContains("Employer Registration Number",eanNumber);
		cf.clickButtonContains("Continue");
		sleep(2000);
		cf.screenShot("AccountUpdateStatus", "Pass", "Update Account Status");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("ErrorRequired_AccountUpdateStatus", "Pass", "Update Account Status Error Required:SREG-435");
		sleep();
		cf.selectDropdown("Status of Employer Account", " Liable ");
		sreg435.enterComments.sendKeys("entered comments");
		sleep();
		sreg435.sourceDropdown.click();
		sleep();
		sreg435.sourceDropdownValue.click();
		sleep();
		sreg435.selectSourceTypeDropdown.click();
		sleep();
		sreg435.selectSourceTypeDropdownValue.click();
		sleep(2000);
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ConfirmationPage", "Pass", "Confirmation Page:SUC-002");
		cf.clickButtonContains("Home");
	}

}
