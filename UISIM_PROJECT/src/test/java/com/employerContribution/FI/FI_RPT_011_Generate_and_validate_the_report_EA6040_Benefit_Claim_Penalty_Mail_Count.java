package com.employerContribution.FI;

import java.util.Map;

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
import com.ui.pages.FraudAndInvestigationPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_RPT_011_Generate_and_validate_the_report_EA6040_Benefit_Claim_Penalty_Mail_Count extends TestBase {

	@Test(priority = 1, description = "FI_RPT_11_Generate_and_validate_the_report_EA6040_Benefit_Claim_Penalty_Mail_Count", groups = {
			"Regression" })
	public void FI_RPT_11() throws Exception {
		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		
		AddressPage add =new AddressPage(driver);
        FraudAndInvestigationPage FI = new FraudAndInvestigationPage(driver);
		Map<String, String> databaseEanResult = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='LIAB' AND EAN LIKE '9%'", "EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println(eanValue);

		test = report.createTest(
				"FI_RPT_11_Generate_and_validate_the_report_EA6040_Benefit_Claim_Penalty_Mail_Count");

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();

		// --------Menu---------
				commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
				//commonFunctions.clickMenu("menu");
				add.menu.click();
				commonFunctions.ScrollMenu("Inquiry");
				commonFunctions.clickMenu("Inquiry");
				sleep(1000);
				commonFunctions.screenShot("menu", "Pass", "selected option");
		commonFunctions.ScrollMenu("Reports");
		commonFunctions.clickMenu("Reports");
		sleep(1000);
		commonFunctions.ScrollMenu("Generate Reports");
		commonFunctions.clickMenu("Generate Reports");
		commonFunctions.waitForLoadingIconToDisappear();

		// ---ODS-001---
		commonFunctions.screenShot("Generate Reports", "Pass", "Genrate Report ODS-001)screen launched");
		commonFunctions.selectDropdown("Report Name", " EA6040 - Benefit Claim Penalty Mail Count ");
		sleep(1000);
		commonFunctions.selectDropdown("Report Format", " PDF ");
		sleep(1000);
		//commonFunctions.enterPastDate("Start Date", 97);
		commonFunctions.enterTextbox("Start Date", "02/01/2023");
		sleep(1000);
		commonFunctions.enterTextbox("End Date", "08/01/2023");
		sleep(1000);
		commonFunctions.clickButtonContains(" Generate Report ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Generate Reportse", "Pass", "Write Message ODS-001)screen launched");
		//-----Home
		sleep(2000);
		commonFunctions.clickButtonContains("Home ");
		commonFunctions.waitForLoadingIconToDisappear();
		commonFunctions.screenShot("Home Page", "Pass", "Successfully landed on home page test completed  ");
		commonFunctions.waitForLoadingIconToDisappear();
		
		// --------Menu---------
		commonFunctions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		//commonFunctions.clickMenu("menu");
		add.menu.click();
		commonFunctions.ScrollMenu("Inquiry");
		commonFunctions.clickMenu("Inquiry");
		sleep(1000);
		commonFunctions.screenShot("menu", "Pass", "selected option");
commonFunctions.ScrollMenu("Reports");
commonFunctions.clickMenu("Reports");
sleep(1000);
commonFunctions.ScrollMenu("Search Reports");
commonFunctions.clickMenu("Search Reports");
commonFunctions.waitForLoadingIconToDisappear();

// ---ODS-002---
commonFunctions.screenShot("Search Reports", "Pass", "Write Message ODS-002 screen launched");
sleep(1000);
commonFunctions.selectDropdown("Report", " EA6040 - Benefit Claim Penalty Mail Count ");
sleep(1000);
commonFunctions.enterCurrentDate("Start Date");

//enterTextbox("End Date", "07/03/2023");
sleep(1000);
//commonFunctions.enterTextbox("End Date", "07/03/2023");
commonFunctions.enterCurrentDate("End Date");
sleep(1000);
commonFunctions.screenShot("Search Reports", "Pass", "Searching item on  ODS-002 ");
commonFunctions.clickButtonContains(" Search ");
commonFunctions.waitForLoadingIconToDisappear();
commonFunctions.screenShot("Search Reports", "Pass", "Report visible on  ODS-002 ");
commonFunctions.clickOnLink("Benefit Claim Penalty Mail Count");
commonFunctions.switchTab();
commonFunctions.verifyContentInPDf("BENEFIT CLAIM PENALTY MAIL COUNT");
commonFunctions.screenShot("Search Reports", "Pass", "Successsfully opened report");
//-------to be continue

				commonFunctions.screenShot("FI.RPT.011", "Pass", "FI.RPT.011 test completed  ");
	}

}
