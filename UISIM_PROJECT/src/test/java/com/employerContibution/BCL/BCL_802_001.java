package com.employerContibution.BCL;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.COL_468;
import com.ui.pages.COL_474;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class BCL_802_001 extends TestBase {
	@Test
	public void BCL802_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_468 col468 = new COL_468(driver);
		COL_474 col474 = new COL_474(driver);

		test = report.createTest(
				"BCL.802.05.001 - Verify CSR can Search ERN with select bankruptcy case status 'Active' and add Bankruptcy Case Activity for activity type incoming and activity name 'Telephone Call'");

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

		test.info("Step: 4 -- ");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("Enter only one of the following 1");
		// Assert.assertTrue(col468.empcol468enterOnlyoneerrormsg.isDisplayed());
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");

		test.info("Step: 5 -- ");
		commonFuntions.enterTextboxContains("a.Employer Registration Number", "1111111");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("The entry for the Employer Registration Number is invalid.");
		// Assert.assertTrue(col468.ernIsInvalidempcol468.isDisplayed());
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		commonFuntions.forceClearTextWithElement("a.Employer Registration Number");
		sleep(1000);

		test.info("Step: 6 -- ");
		commonFuntions.enterTextboxContains("b.Owner SSN", "56&-98$-98%3");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("The entry for the SSN is invalid.");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		commonFuntions.forceClearTextWithElement("b.Owner SSN");
		sleep(1000);

		test.info("Step: 7 -- ");
		commonFuntions.enterTextboxContains("a.Employer Registration Number", "0463861");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("No records found.");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		commonFuntions.forceClearTextWithElement("a.Employer Registration Number");
		sleep(1000);

		test.info("Step: 8 -- ");
		commonFuntions.enterTextboxContains("c.Employer FEIN", "#45-5&98");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("The entry for the Federal Identification Number (FEIN) is invalid.");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		commonFuntions.forceClearTextWithElement("c.Employer FEIN");
		sleep(1000);

		test.info("Step: 9 -- ");
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

		commonFuntions.enterTextboxContains("a.Employer Registration Number", eanValue);
		commonFuntions.enterTextboxContains("c.Employer FEIN", feinValue);

		Map<String, String> ssnOutput = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT OWNER_TYPE,* FROM T_Employer_partner WHERE FIRST_NAME ='FN' AND LAST_NAME='LN';", "SSN");
		String ssn = ssnOutput.get("SSN");
		System.out.println(ssn);

		commonFuntions.enterTextboxContains("b.Owner SSN", ssn);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("Enter only one of the following 1. a. Employer Registration Number or b");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		commonFuntions.forceClearTextWithElement("a.Employer Registration Number");
		sleep(1000);
		commonFuntions.forceClearTextWithElement("b.Owner SSN");
		sleep(1000);
		commonFuntions.forceClearTextWithElement("c.Employer FEIN");
		sleep(1000);

		test.info("Step: 10 -- ");
		commonFuntions.enterTextboxContains("a.Employer Registration Number", eanValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Result table is displayed");

		test.info("Step: 11 -- ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("Select an account ERN to enter the bankruptcy details.");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		sleep(1000);
		commonFuntions.forceClearTextWithElement("a.Employer Registration Number");
		sleep(1000);

		test.info("Step: 12 -- ");
		Map<String, String> databaseResults1 = col474.database_SelectQuery(
				"SELECT * FROM T_TX_BANKRUPTCY ttb JOIN T_EMPLOYER_ACCOUNT tea ON ttb.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE STATUS ='ACTV';");

		String eanValue2 = databaseResults1.get("Ean");
		System.out.println("The EAN Value is:" + eanValue2);
		test.log(Status.INFO, "Ean::" + eanValue2);

		commonFuntions.enterTextboxContains("a.Employer Registration Number", eanValue2);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col468.errorLabelInLi("Bankruptcy details for the selected ERN already exists.");
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Error Message is displayed");
		commonFuntions.forceClearTextWithElement("a.Employer Registration Number");

		Map<String, String> employerEntityName = commonFuntions.database_SelectQuerySingleColumn("SELECT DISTINCT \r\n"
				+ "            acc.EAN as selectedEan, acc.FEIN as fein, acc.ENTITY_NAME  as employerEntityName,\r\n"
				+ "            partner.SSN as selectedSsn,    partner.EMPLOYER_PARTNER_ID     as PARTNERID        \r\n"
				+ "            FROM T_REGULAR_EMPLOYER ms \r\n"
				+ "            inner join T_EMPLOYER_ACCOUNT acc  on ms.EMPLOYER_ACCOUNT_ID = acc.EMPLOYER_ACCOUNT_ID \r\n"
				+ "            left join T_BUSINESS_LOCATION bislocation on bislocation.EMPLOYER_ID = ms.EMPLOYER_ID and bislocation.PRIMARY_BUSINESS_ACTIVITY=1\r\n"
				+ "            join T_EMPLOYER_PARTNER partner on partner.EMPLOYER_ACCOUNT_ID = acc.EMPLOYER_ACCOUNT_ID\r\n"
				+ "            WHERE acc.ENTITY_NAME IN (SELECT ENTITY_NAME FROM T_EMPLOYER_ACCOUNT tea);",
				"EMPLOYERENTITYNAME");

		String employerEntityNameValue = employerEntityName.get("EMPLOYERENTITYNAME");
		System.out.println("The selectedEAN Value is:" + employerEntityNameValue);
		test.log(Status.INFO, "Ean::" + employerEntityNameValue);

		test.info("Step: 13 -- ");
		commonFuntions.enterTextboxContains("d. Legal Name of Business", employerEntityNameValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		col474.selectFirstRadioBtn.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "COL-469 screen is displayed");

		test.info("Step: 14 -- ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "Required Error is displayed");

		test.info("Step: 15 -- ");
		commonFuntions.enterTextboxContains("1.  Bankruptcy Case Number", caseNumber);
		commonFuntions.selectRadioQuestions("2 Is this a Jointly Administered Claim?", "Yes ");
		commonFuntions.enterTextboxContains("4. Petition File Date", "20/6/2023");
		commonFuntions.errorLabel(" Please enter a valid date in MM/DD/YYYY format");
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "Date Error is displayed");
		commonFuntions.selectDropdown("5. Bankruptcy Chapter", " Chapter 7 ");
		commonFuntions.enterTextboxContains("7. Deadline for Filing Proof of Claim", "6/30/2023");
		commonFuntions.selectDropdown("8. Bankruptcy State", " Alabama ");
		commonFuntions.selectDropdown("9. Bankruptcy District", " Eastern ");
		commonFuntions.selectDropdown("10. Proceedings", " Bankruptcy ");
		commonFuntions.selectDropdown("11. Trustee", " Trustee description ");
		commonFuntions.selectDropdown("12. Attorney", " Attorney description ");

		test.info("Step: 16 -- ");
		sleep(1000);
		commonFuntions.forceClearTextWithElement("4. Petition File Date");
		sleep(1000);
		commonFuntions.enterTextboxContains("4. Petition File Date", "6/20/2023");
		commonFuntions.enterTextboxContains("3 Jointly Administered Bankruptcy Case Number", "9652");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		// commonFuntions.waitForLoadingIconToDisappear();
		// commonFuntions.errorLabel(" If response to question 5. is 'Chapter Seven'
		// then response to question 6. is mandatory.");
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "Required Error is displayed");

		test.info("Step: 17 -- ");
		sleep(1000);
		commonFuntions.forceClearTextWithElement("4. Petition File Date");
		sleep(1000);
		commonFuntions.selectRadioQuestions("6.  Is this an Asset Case", "Yes ");
		commonFuntions.enterFutureDate("4. Petition File Date", 720);
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.errorLabel(" Petition File Date cannot be future date.");
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "Required Error is displayed");

		test.info("Step: 18 -- ");
		sleep(1000);
		commonFuntions.forceClearTextWithElement("4. Petition File Date");
		sleep(1000);
		commonFuntions.enterTextboxContains("4. Petition File Date", "6/20/2023");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy Details", "Pass", "COL-470 screen is displayed");

		test.info("Step: 19 -- ");
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Bankruptcy Details Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 20 -- ");
		col468.easternDistrictBankruptcyCourtLink.click();
		sleep(5000);
		Set<String> allHandles = driver.getWindowHandles();
		Iterator<String> l1 = allHandles.iterator();
		String parent = l1.next();
		System.out.println(parent);
		String Child = l1.next();
		System.out.println(Child);
		driver.switchTo().window(Child);
		sleep(2000);
		commonFuntions.screenShot("eastern District Bankruptcy Court Link", "Pass",
				"eastern District Bankruptcy Court window is displayed");
		sleep(2000);
		driver.switchTo().window(parent);

		test.info("Step: 21 -- ");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Enter Bankruptcy", "Pass", "Home Page screen is displayed");

	}
}
