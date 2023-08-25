package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.BclPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_011 extends TestBase {

	@Test(priority = 1, description = "FI.169.03.011- Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Unsatisfied Judgment'", groups = {
			"Regression" })
	public void FI_169_03_011() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI.169.03.011- Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Unsatisfied Judgment'");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.EMPLOYER_USER_8, COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFunctions.clickMenu("menu");
		commonFunctions.screenShot("Menu", "Pass", "ClickMenu");
		commonFunctions.ScrollMenu("Secure Messaging");
		commonFunctions.clickMenu("Secure Messaging");
		commonFunctions.ScrollMenu("Write Message");
		commonFunctions.clickMenu("Write Message");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SM-101---
		commonFunctions.screenShot("Write Message", "Pass", "Write Message (SM-101)screen launched");
		commonFunctions.selectDropdown("Category", " Protest ");
		sleep(1000);
		commonFunctions.selectDropdown("Subcategory", " How do I protest (an) Unsatisfied Judgment(s)? ");
		sleep(1000);
		commonFunctions.clickOnLinkAnchorTag("Click Here");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.switchTab();
		commonFunctions.waitForLoadingIconToDisappear();

		// ----FIS-002---
		commonFunctions.screenShot("Submit Issue", "Pass", "Successfully launched to FIS_002 page");
		commonFunctions.enterTextbox("Warrant/Judgment balance due ($)", "213");
		BCL.Reason_reasonBasisUnsatisfiedJudgProtest.sendKeys("for testing");
		commonFunctions.selectLink("Document", "Browse");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.uploadDoc("Sample.docx");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Submit Issue", "Pass", "Details entered on (FIS-002)screen");
		commonFunctions.clickButtonContains("Continue ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---Submit Issue Verification---
		commonFunctions.screenShot("Submit Issue Verification", "Pass", "Submit Issue Verification screen launched");
		commonFunctions.clickButtonContains("Submit ");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---SUC-002---
		commonFunctions.screenShot("Issue Submission - Confirmation", "Pass",
				"Issue Submission - Confirmation (SUC-002)screen launched");
		commonFunctions.clickButtonContains("Home ");
		sleep(2000);

		// ---HOME---
		commonFunctions.screenShot("Home", "Pass", "Home screen launched");

		// Done
	}

}
