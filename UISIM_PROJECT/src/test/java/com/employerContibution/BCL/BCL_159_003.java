package com.employerContibution.BCL;

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
public class BCL_159_003 extends TestBase {

	@Test(priority = 1, description = "BCL.159.003 -  Verify CSR can submit collection notice with select more than one address and single quarter with notice type is Field Office Notice", groups = {
			"Regression" })
	public void BCL_159_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		BclPage BCL = new BclPage(driver);

		Map<String, String> databaseEanResult = commonFuntions.database_SelectQuerySingleColumn("SELECT \r\n"
				+ "tbl.NOI_STATUS AS STATUS,\r\n" + "tbl.NOI_REASON AS REASON  ,emp.EAN   \r\n"
				+ "FROM T_EMPLOYER_ACCOUNT emp\r\n"
				+ "JOIN T_EMPLOYER_PARTNER empp ON  emp.EMPLOYER_ACCOUNT_ID=empp.EMPLOYER_ACCOUNT_ID\r\n"
				+ "JOIN T_TX_TAX_TOP_EXPORT_MASTER TBL ON TBL.EMPLOYER_PARTNER_ID = EMPP.EMPLOYER_PARTNER_ID\r\n"
				+ "WHERE tbl.NOI_STATUS IS NOT NULL --='HOLD'\r\n" + "--WHERE emp.EAN='0464364'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"BCL.159.003 -  Verify CSR can submit collection notice with select more than one address and single quarter with notice type is Field Office Notice");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		commonFuntions.clickMenu("menu");
		commonFuntions.screenShot("Menu", "Pass", "ClickMenu");
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		commonFuntions.ScrollMenu("Collections");
		commonFuntions.clickMenu("Collections");
		commonFuntions.ScrollMenu("Generate Collection Notice - Enter ERN");
		commonFuntions.clickMenu("Generate Collection Notice - Enter ERN");
		sleep();

		// ---COL-548---
		commonFuntions.screenShot("Generate Collection Notice - Enter ERN", "Pass",
				"Generate Collection Notice - Enter ERN (COL-548)screen launched");
		commonFuntions.clickButtonContains("Continue ");
		sleep();
		commonFuntions.screenShot("Generate Collection Notice - Enter ERN", "Pass", "Message 'Required' on COL-548");
		commonFuntions.enterTextboxContains("Employer Registration Number", "0463089");
		commonFuntions.screenShot("Generate Collection Notice - Enter ERN", "Pass", "ERN Entered");
		commonFuntions.clickButtonContains("Continue ");
		sleep(20000);

		// ---COL-549---
		commonFuntions.screenShot("Generate Collection Notice", "Pass",
				"Generate Collection Notice (COL-549)screen launched");
		commonFuntions.selectCheckbox("");
		commonFuntions.screenShot("Generate Collection Notice", "Pass", "Record selected on (COL-549)screen");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		// ---COL-567---
		commonFuntions.screenShot("Issue Collection Notice", "Pass",
				"Issue Collection Notice (COL-567)screen launched");
		commonFuntions.selectCheckbox("");
		commonFuntions.enterTextboxContains("Notice Date", "6/7/2023");
		commonFuntions.selectDropdown("Notice Type", " Enforcement Letter ");
		commonFuntions.screenShot("Issue Collection Notice", "Pass", "Issue selected on (COL-549)screen");
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);

		// ---COL-569---
		commonFuntions.screenShot("Issue Collection Notice - Verification", "Pass",
				"Issue Collection Notice - Verification (COL-569)screen launched");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);

		// ---SUC-002---
		commonFuntions.screenShot("Issue Collection Notice - Confirmation", "Pass",
				"Cancel Payment Plan Confirmation (SUC-002)screen launched");
		commonFuntions.clickButtonContains("Home ");
		sleep(2000);

		// ---HOME---
		commonFuntions.screenShot("Home", "Pass", "Home screen launched");
		sleep(2000);

		// Done
	}

}
