package com.employerContibution.EL;

/* Need a refined query to run the testcase */

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_02_013_CSR_Can_Renew_Exempt_Register extends TestBase {

	@Test
	public void EL_02_013() throws Exception {

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' AND COMPANY_TYPE = 'PRI' AND FEIN IS NOT FALSE ORDER BY ORGANIZATION_TYPE DESC",
				"FEIN");
		String feinValue = databaseResults.get("Fein");
		System.out.println("feinValue is" + feinValue);

		test = report.createTest("EL.02.013 .Verify CSR  can renewal PEO Exempt registration.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Renew PEO");
		Thread.sleep(2000);
//		 commonFuntions.enterTextbox("PEO Name", peoName);
//		 commonFuntions.clickOnLink(" ADVANCED SEARCH");
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("(FEIN)", feinValue);
		commonFuntions.screenShot("file1", "Pass", "Searching with FEIN ");
		commonFuntions.clickButtonContains("search");
		Thread.sleep(4000);
		commonFuntions.selectRadioWithFeinValue(feinValue);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(4000);
		commonFuntions.screenShot("file2", "Pass", "Navigating to next page");
		commonFuntions.clickButton("Continue ");
		Thread.sleep(2000);

		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(2000);
		/* EAS-001 */
		commonFuntions.screenShot("file3", "Pass", "Navigated to EAS-001 page");
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(2000);

		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		Thread.sleep(2000);
		/* PEO - 003 */
		commonFuntions.screenShot("file4", "Pass", "Navigating to PEO-003  page");
		PEOPage.addressLine1.clear();
		Thread.sleep(2000);
		PEOPage.addressLine1.sendKeys("Test Address Data");
		Thread.sleep(2000);
		commonFuntions.screenShot("file5", "Pass", "Entered the address lane 1");
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(4000);
		commonFuntions.screenShot("file6", "Pass", "Pop up if displayed");
		try {
			PEOPage.uspsAddress.click();
			commonFuntions.clickButton("Continue ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}

		Thread.sleep(2000);
//		 PEOPage.addressLine1.sendKeys("Test Address Data");
//		 Thread.sleep(2000);
//		 commonFuntions.clickButton("Continue ");
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS");
		Thread.sleep(2000);
		commonFuntions.selectLink("Authorization to do business in NYS from the NYS", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		commonFuntions.clickButtonContains("Upload");
		Thread.sleep(10000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(3000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.screenShot("Final", "Pass", "Click Accep & Submit");
		commonFuntions.clickButton("Accept & Submit ");
		Thread.sleep(3000);
		commonFuntions.clickButtonContains("Home ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(15000);
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review Peo", "Pass", "Review Peo");
		commonFuntions.clickOnLink("Review PEO");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		commonFuntions.screenShot("Review", "Pass", "Review Peo Registration");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(2000);
		commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " California ");
		commonFuntions.enterTextbox("State agency that issued it.", "New York");
		commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
		commonFuntions.enterTextbox("Registration Number ", "3458767985");
		long number = commonFuntions.createRandomInteger(10000, 99999);
		String ernValue = "12" + Long.toString(number);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		try {
			PEOPage.uspsAddress.click();
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS");
		Thread.sleep(2000);
		commonFuntions.selectLink("Authorization to do business in NYS from the NYS", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		commonFuntions.clickButtonContains("Upload");
		Thread.sleep(10000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(10000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(4000);
		commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(2000);

		commonFuntions.selectRadio("Approved");
		commonFuntions.screenShot("Approval", "Pass", "ApprovalPage");
		commonFuntions.clickButtonContains("Submit");
		Thread.sleep(2000);
		commonFuntions.screenShot("Success", "Pass", "SuccessPage");
	}
}
