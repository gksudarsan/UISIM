package com.employerContibution.EM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EM_005;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import org.testng.annotations.Listeners;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_412_02_001_Verify_Employer_is_able_to_update_legal_name_of_business_information_and_the_system_create_task_for_CSR_review_and_Approve
		extends TestBase {
	@Test(priority = 1, description = "Test sample", groups = { "Regression" })
	public void EM_412_02_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"EM_412_02_001_Verify_Employer_is_able_to_update_legal_name_of_business_information_and_the_system_create_task_for_CSR_review_and_Approve");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);

		commonFunction.login(COMMON_CONSTANT.EMPLOYER_MA_ROLE.toUpperCase(), COMMON_CONSTANT.EMPLOYER_MA_ROLE_PASSWORD);
		sleep(2000);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Account Maintenance");
		commonFunction.clickMenu("Account Maintenance");
		commonFunction.ScrollMenu("Employer Account Maintenance");
		commonFunction.clickMenu("Employer Account Maintenance");
		commonFunction.screenShot("SREG-030", "Pass", "Modify Employer Account Details page is displayed");
		empRegPage.legalNameOfBusinessTextBoxEEWI_005.sendKeys("ST MARYS HOSPITAL FOR CHILDREN CORP");
		commonFunction.populateListbox("Comment", "Test Automation");
		commonFuntions.selectLink("Please select a file to upload that provides proof of name change.", "Browse");
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(2000);
		String eanValue = AddPage.getERN.getText().trim();
		eanValue = eanValue.replace("-", "");
		System.out.println("Selected ERN is:" +eanValue);
		test.log(Status.INFO, "ERN::" +eanValue);
		commonFunction.screenShot("SREG-030", "Pass", "Modify Employer Account Details entered");
		commonFunction.clickButtonContains("Submit ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.Label("This Request in under review");
		commonFunction.screenShot("SUC-002", "Pass", "Employer Account Maintenance Confirmation page is displayed");
		commonFunction.clickButtonContains("Home ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Home", "Pass", "Home page is displayed");
		
		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
				+ COMMON_CONSTANT.LND_SPECIALIST
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='" + eanValue +"' ORDER BY UPDATED_TS desc)");
		Thread.sleep(4000);
		
		
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.LND_SPECIALIST.toUpperCase(), COMMON_CONSTANT.LND_SPECIALIST_PASSWORD);
		peoPage.queue.click();
		commonFunction.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFunction.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
		commonFunction.screenShot("EAN Search", "Pass", "EAN Search");
		commonFunction.clickButtonContains("Search");
		
		commonFunction.clickOnLinkfirstItem("Third Party For Legal Business Entity Name Change");
		commonFunction.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);
		commonFunction.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		
		commonFunction.screenShot("EMWI-004", "Pass", "Update Legal Name Task page is displayed");
		commonFunction.selectDropdown("Select the Action", " Approve ");
		commonFunction.populateListbox("Comments", "Approved");
		commonFunction.screenShot("Filled Details", "Pass", "Launched to EMWI-004");
		commonFunction.clickButtonContains("Submit ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.Label("The Task details have been successfully saved.");
		commonFunction.screenShot("Task Confirmarion: SUC-002", "Pass", "Work Item Completed");
		commonFunction.clickButtonContains("Home ");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Home", "Pass", "Home page is displayed");
	}
}