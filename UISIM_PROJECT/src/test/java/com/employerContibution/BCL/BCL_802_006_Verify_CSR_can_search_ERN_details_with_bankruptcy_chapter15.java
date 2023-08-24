package com.employerContibution.BCL;

import java.util.Map;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EM_005;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_006_Verify_CSR_can_search_ERN_details_with_bankruptcy_chapter15 extends TestBase {
	@Test
	public void BCL_802_006() throws Exception {
		test = report.createTest(
				"BCL.802.006 - Verify CSR  can search ERN details with legal name and add bankruptcy details for the Employer/responsible party with bankruptcy chapter 15");

		commonStepDefinitions cf = new commonStepDefinitions();
		EM_005 em005 = new EM_005(driver);

		// Query
		Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn("SELECT DISTINCT \r\n"
				+ "            acc.EAN as selectedEan, acc.FEIN as fein, acc.ENTITY_NAME  as employerEntityName,\r\n"
				+ "            partner.SSN as selectedSsn,    partner.EMPLOYER_PARTNER_ID     as PARTNERID        \r\n"
				+ "            FROM T_REGULAR_EMPLOYER ms \r\n"
				+ "            inner join T_EMPLOYER_ACCOUNT acc  on ms.EMPLOYER_ACCOUNT_ID = acc.EMPLOYER_ACCOUNT_ID \r\n"
				+ "            left join T_BUSINESS_LOCATION bislocation on bislocation.EMPLOYER_ID = ms.EMPLOYER_ID and bislocation.PRIMARY_BUSINESS_ACTIVITY=1\r\n"
				+ "            join T_EMPLOYER_PARTNER partner on partner.EMPLOYER_ACCOUNT_ID = acc.EMPLOYER_ACCOUNT_ID\r\n"
				+ "            WHERE acc.EAN IN (SELECT EAN FROM T_EMPLOYER_ACCOUNT tea)  ", "SELECTEDEAN");
		String eanValue = databaseEanResult.get("SELECTEDEAN");
		System.out.println("The EAN is " + eanValue);

		// -----Login
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");

		// -----Menu
		cf.clickMenu("menu");
		sleep(2000);
		cf.screenShot("MenuPage", "Pass", "Launched to Menu");
		cf.ScrollMenu("Employer Collection");
		cf.clickMenu("Employer Collection");
		sleep(1000);
		cf.clickMenu("Bankruptcy");
		cf.clickMenu("Enter Bankruptcy");
		cf.screenShot("Enter Bankruptcy", "Pass", "Launched to COL-468");
		cf.enterTextboxContains("a.Employer Registration Number", eanValue);
		cf.clickButtonContains(" Search ");
		sleep(2000);
		em005.selectToggle.click();
		sleep(3000);
		cf.clickButtonContains("Continue ");
		cf.screenShot("Enter Bankruptcy Details", "Pass", "Launched to COL-469");
		cf.enterTextboxContains("1.  Bankruptcy Case Number", "241978423");
		cf.selectRadioQuestions("2 Is this a Jointly Administered Claim?", "Yes");
		cf.enterTextboxContains("3 Jointly Administered Bankruptcy Case Number", "143091");
		cf.enterTextboxContains("4. Petition File Date", "8/7/2023");
		sleep();
		cf.selectDropdown("5. Bankruptcy Chapter", "Chapter 15");
		cf.selectRadioQuestions("6.  Is this an Asset Case", "Yes ");
		cf.enterTextboxContains("7. Deadline for Filing Proof of Claim", "8/7/2023");
		sleep();
		cf.selectDropdown("8. Bankruptcy State", " Alaska ");
		sleep(3000);
		cf.selectDropdown("9. Bankruptcy District", " Eastern ");
		cf.selectDropdown("10. Proceedings", "Bankruptcy");
		cf.selectDropdown("11. Trustee", "Trustee description");
		sleep(2000);
		cf.selectDropdown("12. Attorney", " Attorney description ");
		cf.selectDropdown("13. Receiver", "Receiver description");
		cf.selectDropdown("14. Executor/Executrix", "Executor description");
		cf.enterTextboxContains("15 Bankruptcy Address", "Bharat");
		sleep();
		cf.clickButtonContains("Continue ");

		test.info("Bankruptcy Details Verification");
		cf.screenShot("Bankruptcy Details Verification", "Pass", "Launched to COL-470");
		cf.clickButtonContains("Submit ");

		test.info(" Bankruptcy Details Confirmation");
		cf.screenShot(" Bankruptcy Details Confirmation", "Pass", "Launched to SUC-002");
		/*
		 * cf.clickOnLinkAnchorTag("https://www.nysb.uscourts.gov"); sleep();
		 * cf.screenShot("United States Bankruptcy Court Doc", "Pass",
		 * "Launched to Southern Doc");
		 */
		System.out.println("Worked");
	}
}
