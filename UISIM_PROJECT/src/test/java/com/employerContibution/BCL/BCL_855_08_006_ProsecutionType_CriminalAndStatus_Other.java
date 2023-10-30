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
public class BCL_855_08_006_ProsecutionType_CriminalAndStatus_Other extends TestBase {

	@Test
	public void BCL_855_08_006() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		BclPage bclPage = new BclPage(driver);
		test = report.createTest(
				"BCL_855_08_006-Verify CSR can search ERN details and add new prosecution for Prosecution Type is 'Criminal' and status is 'Other' with comments");
		cf.login(COMMON_CONSTANT.CollectionsSpecialistUser_3.toUpperCase(), COMMON_CONSTANT.CollectionsSpecialistUser_3_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		cf.ScrollMenu("Employer Collection");
		cf.clickMenu("Employer Collection");sleep();
		cf.screenShot("Prosecution", "Pass", "Navigating to Prosecution");
		cf.clickMenu("Prosecution");sleep();

		/*------ Prosecution (COL-593)------*/

		cf.screenShot("Prosecution1", "Pass", "Prosecution1");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PROSECUTION ttp JOIN T_EMPLOYER te ON ttp.EMPLOYER_ID = te.EMPLOYER_ID",
				"EAN");
		String eanValue = databaseResults.get("EAN");
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.screenShot("Prosecution2", "Pass", "Prosecution2");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		/*------List of Prosecutions (COL-594)------*/
		
		cf.screenShot("ListofProsecutions", "Pass", "List of Prosecutions");sleep();
		cf.clickButtonContains(" Add Prosecution ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("EnterProsecutionDetails", "Pass", "Enter Prosecution Details");
		
		/*-------Enter Prosecution Details (COL - 595)-------*/
		String firstName = cf.enterRandomString("a. First Name");sleep();
		cf.enterTextboxContains("c. Last Name", "testauto");sleep();
		cf.enterTextboxContains("3. Prosecution Case Number", cf.createRandomInteger(10, 99)+"23");
		cf.selectDropdown("4. Prosecution Type", " Criminal ");sleep();
		cf.enterFutureDate("5. Date Prosecution Proceedings Commenced",60);sleep();
		cf.enterTextboxContains("6. Assigned To", firstName);
		cf.selectDropdown("7. Status", " Other (add comments) ");sleep();
		bclPage.comments.sendKeys("ok");
		cf.screenShot("EnterProsecutionDetailsEntered", "Pass", "Enter Prosecution Details Entred");
		cf.clickButtonContains("Submit ");sleep();
		cf.waitForLoadingIconToDisappear();
		
		/*-------Prosecution Confirmation (SUC 002)------*/

		cf.screenShot("ProsecutionConfirmation", "Pass", "Prosecution Confirmation");
		cf.clickOnLinkAnchorTag(" Generate Correspondence ");
		sleep();cf.waitForLoadingIconToDisappear();

		/*------Select Correspondence (ACOR-001)-------*/

		cf.screenShot("SelectCorrespondence", "Pass", "Select Correspondence");
		cf.clickButtonContains("Home ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("homePage", "Pass", "Home Page");
		
		AddPage.menu.click();
		sleep();
		cf.ScrollMenu("Employer Collection");
		cf.clickMenu("Employer Collection");sleep();
		cf.screenShot("Prosecution1", "Pass", "Navigating to Prosecution 1");
		cf.clickMenu("Prosecution");sleep();
		
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("ListofProsecutions1", "Pass", "List of Prosecutions1");sleep();
	}

}
