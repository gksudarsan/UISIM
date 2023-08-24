package com.employerContribution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.COL_474;
import com.ui.pages.FIP_006;
import com.ui.pages.FIS_002;
import com.ui.pages.FIS_008;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class FI_169_07_002 extends TestBase {

	@Test
	public void FI169_07_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);
		COL_474 col474 = new COL_474(driver);
		FIP_006 fip006 = new FIP_006(driver);
		FIS_008 fis008 = new FIS_008(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);

		test = report.createTest(
				"FI.169.07.002- 'Verify TPR can submit a IA198.P Failure to File Protest form online, task 'Failure to File Penalty Protest' will create for CSR to review and close task with action taken");

		commonFuntions.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message - Enter ERN");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("Write Message - Enter ERN");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "SM-100 screen is displayed");

		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Write Message - Enter ERN", "0847711"); // 0847711 , 5454645 , 5454645
		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");

		test.info("Step: 5 -- ");
		commonFuntions.selectDropdown("Category", " Protest ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.selectDropdown("Subcategory", " How do I protest Failure to File Penalties? ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 with details filled screen is displayed");
		sleep(2000);
		commonFuntions.clickOnLinkAnchorTag("Protest Document for Failure to File Penalties");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		Set<String> allHandles = driver.getWindowHandles();
		Iterator<String> l1 = allHandles.iterator();
		String parent = l1.next();
		System.out.println(parent);
		String Child = l1.next();
		System.out.println(Child);
		driver.switchTo().window(Child);
		sleep(2000);
		commonFuntions.screenShot("Protest Document for Failure to File Penalties", "Pass",
				"FIS-008 screen is displayed");

		test.info("Step: 6 -- ");
		//
		Map<String, String> databaseResults1 = peopage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC;");

		String eanValue1 = databaseResults1.get("Ean");
		System.out.println("The EAN Value is:" + eanValue1);
		test.log(Status.INFO, "Ean::" + eanValue1);

		String nameValue1 = databaseResults1.get("legalName");
		System.out.println("The EAN Value is:" + nameValue1);
		test.log(Status.INFO, "Ean::" + nameValue1);
		//

		commonFuntions.enterTextboxContains("Employer Name", nameValue1);
		commonFuntions.enterTextboxContains("Address Line 1", "USA, California");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.selectDropdown("State", " Alabama ");
		commonFuntions.enterTextboxContains("Zip Code", "12345");
		commonFuntions.enterTextboxContains("ERN", eanValue1);

		fis008.iDoNotCommentField.sendKeys("Testing This Screen");
		fis008.otherReasonCommentField.sendKeys("Testing This Field");

		AddPage.browserLink.click();
		sleep(2000);
		commonFuntions.uploadDoc("TESTINGEL.docx");
		sleep(3000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "documnet is uploaded");

		commonFuntions.enterCurrentDate("Date"); // past date
		commonFuntions.selectCheckbox("I Accept");

		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Issue Submission Confirmation", "Pass", "SUC-002 screen is displayed");

		//
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		//

		
		//same ern everytime assigned to workitem
		String eanValue2 = "5667230";
		//
		commonFuntions.database_UpdateQuery(
				"UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
						+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
						+ eanValue2 + "' ORDER BY UPDATED_TS desc);");
		//

		test.info("Step: 8 -- ");
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);

		test.info("Step: 9 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue2);
		commonFuntions.screenShot("eanSearch", "Pass", "eanSearch");
		commonFuntions.clickButtonContains("Search");

		test.info("Step: 9 -- ");
		//commonFuntions.clickOnLink("Failure to File Penalty Protest");
		fis008.failureToFilePenaltyProtestWorkItem.click();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		commonFuntions.screenShot("Transfer Protest", "Pass", "PFP-002 screen is visible");

		test.info("Step: 10 -- ");
		commonFuntions.selectRadioQuestions("Close Task with No Action Taken", "Yes ");
		fis008.enterProtestCommentField.sendKeys("Testing This Field");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Task Confirmation", "Pass", "Message from Webpage popup is visible");

		test.info("Step: 11 -- ");
		commonFuntions.clickButtonContains(" Yes ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");

		test.info("Step: 12 -- ");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");

	}
}
