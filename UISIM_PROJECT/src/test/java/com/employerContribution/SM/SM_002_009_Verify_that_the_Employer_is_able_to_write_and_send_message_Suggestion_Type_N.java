package com.employerContribution.SM;

import java.util.Map;

import org.apache.commons.exec.LogOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_002_009_Verify_that_the_Employer_is_able_to_write_and_send_message_Suggestion_Type_N extends TestBase {

	@Test(priority = 1, description = "SM.002.009-Verify that the system does not display a suggestion and the third party representative (Employer) will be able to write and send a message.(Suggestion Type N)", groups = {
			"Regression" })
	public void SM_002_009() throws Exception {
		test = report.createTest(
				"SM.002.009-Verify that the system does not display a suggestion and the third party representative (Employer) will be able to write and send a message.(Suggestion Type N)");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage BCL = new BclPage(driver);
		
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM t_employer WHERE EMPLOYER_ID IN (SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE THIRD_PARTY_CDS_VENDOR_ID = '299');", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);


		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.TPR_USER_6, COMMON_CONSTANT.TPR_USER_6_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("Write Message - Enter ERN");
		commonFunctions.clickMenu("Write Message - Enter ERN");
		commonFunctions.screenShot("Menu", "Pass", "Write Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// --- SM-100 ---
		commonFunctions.screenShot("Write Message - Enter ERN", "Pass", "Successfully launched to SM-100 page");
		commonFunctions.enterTextboxContains("Employer Registration Number",eanValue );
		commonFunctions.screenShot("Write Message - Enter ERN", "Pass", "ERN Entered");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		// --- SM-101 ---
		commonFunctions.screenShot("Write Message", "Pass", "Successfully launched to SM-101 page");
		commonFunctions.selectDropdown("Category", " Audits ");
		sleep(2000);
		commonFunctions.selectDropdown("Subcategory", " Other ");
		sleep(2000);
		BCL.write_SecureMessage.sendKeys("This testcase is to verify TPR is able to send message 28/08/2023");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Write Message", "Pass", "Write Message Message Entered on SM-101 page");
		commonFunctions.clickButtonContains("Send ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Secure Message Confirmation", "Pass",
				"Secure Message Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");

		commonFunctions.logoutAndLogin(COMMON_CONSTANT.Collections_Specialist_1, COMMON_CONSTANT.Collections_Specialist_1_PASSWORD);

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");
		commonFunctions.clickButton(" Go to My Q ");

		// --- WF-001 ---
		commonFunctions.screenShot("Individual Work Queue", "Pass", "Successfully launched to WF-001 page");
		commonFunctions.enterTextboxContains("Work Item Description Free Text", "Audit Other");
		commonFunctions.clickButton(" Search ");
		
		commonFunctions.screenShot("Workitem audit is created", "Pass", "Successfully Created Workitem");
		// Done
	}

}
