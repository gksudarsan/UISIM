package com.employerContibution.BCL;

import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.COL_468;
import com.ui.pages.COL_474;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_005
		extends TestBase {

	@Test
	public void BCL802_005() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_468 col468 = new COL_468(driver);
		COL_474 col474 = new COL_474(driver);

		test = report.createTest(
				"BCL.802.005 - Verify CSR  can search FEIN details with legal name and add bankruptcy details for the Employer/responsible party with bankruptcy chapter 12");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Employer Collection");
		commonFuntions.clickMenu("Employer Collection");
		sleep(2000);
		// commonFuntions.ScrollMenu("Bankruptcy");
		// commonFuntions.clickMenu("Bankruptcy");
		col468.employerbankruptcynavBtn.click();
		sleep(1000);
		commonFuntions.ScrollMenu("Enter Bankruptcy");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Enter Bankruptcy");
		commonFuntions.clickMenu("Enter Bankruptcy");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "COL-468 screen is displayed");

		Map<String, String> databaseResults = col474.database_SelectQuery(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID");

		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String caseNumber = databaseResults.get("CaseNumber");
		System.out.println("The EAN Value is:" + feinValue);
		test.log(Status.INFO, "Ean::" + feinValue);
		System.out.println("The EAN Value is:" + eanValue);
		test.log(Status.INFO, "Ean::" + eanValue);
		System.out.println("The EAN Value is:" + caseNumber);
		test.log(Status.INFO, "Ean::" + caseNumber);

		Map<String, String> selectedEAN = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT DISTINCT \r\n"
				+ "            acc.EAN as selectedEan, acc.FEIN as fein, acc.ENTITY_NAME  as employerEntityName,\r\n"
				+ "            partner.SSN as selectedSsn,    partner.EMPLOYER_PARTNER_ID     as PARTNERID        \r\n"
				+ "            FROM T_REGULAR_EMPLOYER ms \r\n"
				+ "            inner join T_EMPLOYER_ACCOUNT acc  on ms.EMPLOYER_ACCOUNT_ID = acc.EMPLOYER_ACCOUNT_ID \r\n"
				+ "            left join T_BUSINESS_LOCATION bislocation on bislocation.EMPLOYER_ID = ms.EMPLOYER_ID and bislocation.PRIMARY_BUSINESS_ACTIVITY=1\r\n"
				+ "            join T_EMPLOYER_PARTNER partner on partner.EMPLOYER_ACCOUNT_ID = acc.EMPLOYER_ACCOUNT_ID\r\n"
				+ "            WHERE acc.ENTITY_NAME IN (SELECT ENTITY_NAME FROM T_EMPLOYER_ACCOUNT tea);",
				"SELECTEDEAN");

		String selectedEANValue = selectedEAN.get("SELECTEDEAN");
		System.out.println("The selectedEAN Value is:" + selectedEANValue);
		test.log(Status.INFO, "Ean::" + selectedEANValue);

		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("a.Employer Registration Number", selectedEANValue);
		// commonFuntions.enterTextboxContains("d. Legal Name of Business", "PSALM 19
		// MINISTRIES INC");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 5 -- ");
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "COL-469 screen is displayed");

		test.info("Step: 6 -- ");
		commonFuntions.enterTextboxContains("1.  Bankruptcy Case Number", caseNumber);
		commonFuntions.selectRadioQuestions("2 Is this a Jointly Administered Claim?", "Yes ");
		commonFuntions.enterTextboxContains("3 Jointly Administered Bankruptcy Case Number", "9652");
		commonFuntions.enterTextboxContains("4. Petition File Date", "6/20/2023");
		commonFuntions.selectDropdown("5. Bankruptcy Chapter", " Chapter 12 ");
		commonFuntions.selectRadioQuestions("6.  Is this an Asset Case", "Yes ");
		commonFuntions.enterTextboxContains("7. Deadline for Filing Proof of Claim", "6/30/2023");
		commonFuntions.selectDropdown("8. Bankruptcy State", " Alabama ");
		commonFuntions.selectDropdown("9. Bankruptcy District", " Eastern ");
		commonFuntions.selectDropdown("10. Proceedings", " Bankruptcy ");
		commonFuntions.selectDropdown("11. Trustee", " Trustee description ");
		commonFuntions.selectDropdown("12. Attorney", " Attorney description ");

		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "COL-470 screen is displayed");

		test.info("Step: 7 -- ");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Bankruptcy Details Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 8 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Home Page screen is displayed");

	}

}
