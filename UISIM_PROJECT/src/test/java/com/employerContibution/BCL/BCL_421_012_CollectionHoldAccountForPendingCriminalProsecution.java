package com.employerContibution.BCL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;

import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_421_012_CollectionHoldAccountForPendingCriminalProsecution extends TestBase {

	@Test
	public void BCL_421_012() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		// PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		// LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage bclPage = new BclPage(driver);
		test = report.createTest(
				"BCL.421.012-Verify CSR can add a collection hold on the account with reason for hold is Pending Criminal Prosecution");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		cf.ScrollMenu("Contribution Collection");
		cf.clickMenu("Contribution Collection");
		sleep();
		cf.screenShot("MaintainCollectionHold", "Pass", "Navigating to Maintain Collection Hold");
		cf.clickMenu("Maintain Collection Hold");
		sleep();

		/*------Maintain Collection Hold (COL - 527)-----*/

		cf.screenShot("MaintainCollectionHold1", "Pass", "Maintain Collection Hold");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD "
						+ "ttech ON ttech.EMPLOYER_ACCOUNT_ID = "
						+ "tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
				"EAN");
		String eanValue = databaseResults.get("EAN");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("MaintainCollectionHold2", "Pass", "Maintain Collection Hold with ERN");
		sleep();
		cf.clickOnLinkAnchorTag("Add Collection Hold");
		sleep(3000);

		/*---------Add Collection Hold (COL 528)-------*/

		cf.screenShot("AddCollectionHold", "Pass", "Add Collection Hold");
		cf.enterFutureDate("Hold Start Date", 10);
		cf.selectDropdown("Reason For Hold", " Pending Criminal Prosecution ");
		bclPage.otherReason.sendKeys("test");
		cf.screenShot("AddCollectionHold1", "Pass", "Add Collection Hold with Details");
		cf.clickButtonContains("Continue ");
		cf.waitForLoadingIconToDisappear();

		/*-------Add Collection Hold Verification (COL - 529)------*/

		cf.screenShot("AddCollectionHoldVerification", "Pass", "Add Collection Hold Verification");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();

		/*-----Add Collection Hold Confirmation (SUC 002)------*/

		cf.screenShot("AddCollectionHoldConfirmation", "Pass", "Add Collection Hold Confirmation");
		cf.clickButtonContains("Home ");
		sleep(3000);
		cf.screenShot("homePage", "Pass", "Home Page");
	}

}
