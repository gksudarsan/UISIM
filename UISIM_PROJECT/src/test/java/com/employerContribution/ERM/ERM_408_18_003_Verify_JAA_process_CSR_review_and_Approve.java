package com.employerContribution.ERM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class ERM_408_18_003_Verify_JAA_process_CSR_review_and_Approve extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "ERM.408.18.003: Verify system calculated rate when JAA process for adding member of an joint account and the system create task for CSR review and Approve", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_ERM_408_18_003() throws Exception {

		test = report.createTest(
				"ERM.408.18.003: Verify system calculated rate when JAA process for adding member of an ̥ount and the system create task for CSR review and Approve");

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// --- GET Query

		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd WHERE ERN NOT IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd2 LEFT JOIN T_EMPLOYER_ACCOUNT tea ON tea.FEIN = tedd2.FEIN WHERE tea.EAN != tedd2.ERN) AND ERN IS NOT NULL",
				"ERN");
		String eanValue = databaseEanResult.get("ERN");
		System.out.println("The ERN is " + eanValue);

		// Legal name match in DOL
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IS NOT NULL AND LENGTH(ENTITY_NAME) <=200 ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		sleep();

	}

}
