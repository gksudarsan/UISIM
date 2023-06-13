package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_434;
import com.ui.pages.SREG_435;
import com.ui.pages.SREG_541;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_414_001 extends TestBase {


	@Test
	public void EM_414_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		SREG_541 sreg541 = new SREG_541(driver);
		test = report.createTest("EM.414.001. - Verify CSR is able to process void transfer and reason of voiding transfer \"No Transfer Occurred\".");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.ScrollMenu("Void Transfer");
		commonFuntions.screenShot("NavigateToVoidTransfer", "Pass", "Navigating to void transfer");
		commonFuntions.clickMenu("Void Transfer");
		commonFuntions.screenShot("VoidTransfer", "Pass", "Void Transfer:SREG-541");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("VoidTransferErrorPage", "Pass", "Required Error Displayed");
		commonFuntions.errorLabel(" Required");
		sleep(2000);
		
//		String ERN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		
		commonFuntions.enterTextboxContains("Employer Registration Number", "1111111");
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("InvalidERN", "Pass", "Checkig with Invalid ERN");
		
		/*       -------------SREG-541-----------------*/
		
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID","EAN");
		String eanNumber = databaseResults.get("EAN");
		System.out.println("The EAN is:" +eanNumber);
		test.log(Status.INFO, "ERN::"+eanNumber);
		commonFuntions.enterTextboxContains("Employer Registration Number",eanNumber);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("VoidTransferERNRecord", "Pass", "Fecth the ERN record");
		commonFuntions.clickButtonContains("Void Transfer ");
		sleep(3000);
		commonFuntions.screenShot("SelectAtLeastOneRecord", "Pass", "Please select a record to proceed further");
		commonFuntions.errorContent("Please select a record to proceed further.");
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadio("Select");
		commonFuntions.clickButtonContains("Void Transfer ");
		sleep(3000);
		commonFuntions.ScrollMenu("Reason For Voiding Transfer");
		commonFuntions.screenShot("ErrorPageValidate", "Pass", "Error Page Validation");
		commonFuntions.errorLabel(" Required");
		sleep(2000);
		commonFuntions.clickButtonContains(" Search ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectRadio("Select");
		commonFuntions.selectDropdown("Reason For Voiding Transfer", " No Transfer Occurred ");
		sreg541.commentBox_voidTransfer.sendKeys("OK");
		commonFuntions.selectDropdown("Source", " Correspondence/Email ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " Correspondence/Email ");
		commonFuntions.clickButtonContains("Void Transfer ");
		sleep(2000);
		commonFuntions.screenShot("VoidTransferCompleteSUC002", "Pass", "Void Transfer Complete-SUC-002");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Account Maintenance");
		commonFuntions.clickMenu("Account Maintenance");
		commonFuntions.ScrollMenu("Employer Account Maintenance");
		commonFuntions.clickMenu("Employer Account Maintenance");
		commonFuntions.screenShot("NavigateToMaintainAccountStatus", "Pass", "Maintain Account Status:Enter ERN");
		commonFuntions.clickMenu("Maintain Account Status");
		commonFuntions.screenShot("MaintainAccountStatusEnterERN", "Pass", "Employer Registration Numbe:SREG-434");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanNumber);
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.screenShot("AccountUpdateStatus", "Pass", "Update Account Status");
		// test case ends here
	}

}
