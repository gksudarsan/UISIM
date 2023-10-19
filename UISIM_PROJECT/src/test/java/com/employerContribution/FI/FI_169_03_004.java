package com.employerContribution.FI;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_004 extends TestBase {

	@Test
	public void FI169_03_004() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		FIS_002 fis002 = new FIS_002(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peopage = new PEOPage(driver);

		test = report.createTest(
				"FI.169.03.004 - Verify Employer can submit an FI Issue when Issue Category - Audit ,Issue Subcategory - 'Audit Protest'  and system create task for CSR review");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_MA_ROLE.toUpperCase(), COMMON_CONSTANT.EMPLOYER_MA_ROLE_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		
		try {
			commonFuntions.clickButtonContains(" I agree with the Terms and Conditions ");
			test.log(Status.PASS, "Accepted 'Terms and Conditions for Businesses'");
		} catch(Exception exception) {
			test.log(Status.PASS, "Accepted 'Terms and Conditions for Businesses'");
		}

		test.info("Step: 3 -- ");
		commonFuntions.clickMenu("Menu");
		sleep(1000);
		commonFuntions.ScrollMenu("Secure Messaging");
		commonFuntions.clickMenu("Secure Messaging");
		sleep(2000);
		commonFuntions.ScrollMenu("Write Message");
		sleep(1000);
		commonFuntions.screenShot("Menu Bar", "Pass", "Write Message menu side bar is displayed");
		commonFuntions.clickMenu("Write Message");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");

		test.info("Step: 4 -- ");
		commonFuntions.selectDropdown("Category", " Protest ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.selectDropdown("Subcategory", " How do I protest Audit Findings? ");
		sleep(1000);
		commonFuntions.screenShot("Write Message", "Pass", "SM-101 with details filled screen is displayed");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.clickOnLinkAnchorTag("click here");
		commonFuntions.waitForLoadingIconToDisappear();
		Set<String> allHandles = driver.getWindowHandles();
		Iterator<String> l1 = allHandles.iterator();
		String parent = l1.next();
		System.out.println(parent);
		String Child = l1.next();
		System.out.println(Child);
		driver.switchTo().window(Child);
		sleep(2000);
		commonFuntions.screenShot("Submit Issue", "Pass", "FIS-002 screen is displayed");

		test.info("Step: 5 -- ");
		//
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		String eanvaluebefore = commonFuntions.retrieveValue("Employer Registration Number").trim();
		String eanvalue = eanvaluebefore.replace("-", "");
		System.out.println(eanvalue);
		//
		fis002.submittingIssueField.sendKeys("test comment");

		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		//
		fis002.quarter1.click();
		sleep(2000);
		fis002.quarterValue1.click();
		sleep(2000);
		fis002.year1.click();
		sleep(2000);
		fis002.yearValue1.click();
		//
		fis002.quarter2.click();
		sleep(2000);
		//fis002.quarterValue2.click();
		sleep(2000);
		fis002.year2.click();
		sleep(2000);
		fis002.yearValue2.click();
		//

		commonFuntions.enterTextboxContains("Amount Protesting ($)", "1000000");

		commonFuntions.selectCheckbox("Were Books and records previously provided?");
		commonFuntions.enterTextboxContains("Address Line 1",
				commonFuntions.createRandomInteger(10, 99) + "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "13429");

		commonFuntions.selectCheckbox("If 50% penalty was assessed are you protesting 50% Willful penalty?");
		commonFuntions.selectCheckbox("Is this protest a hearing request ?");

		commonFuntions.clickButtonContains("Continue ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Submit Issue Verification", "Pass", " screen is displayed");
		commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Issue Submission Confirmation", "Pass", "SUC-002 screen is displayed");

		test.info("Step: 6 -- ");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Home page", "Pass", "Home screen is displayed");

		//
		/*commonFuntions.database_UpdateQuery(
				"UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
						+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"
						+ eanvalue + "' ORDER BY UPDATED_TS desc);");*/
		//

		test.info("Step: 7 -- ");
		//commonFuntions.logoutAndLogin(COMMON_CONSTANT.LND_SPECIALIST.toUpperCase(), COMMON_CONSTANT.LND_SPECIALIST_PASSWORD);
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);

		test.info("Step: 8 -- ");
		commonFuntions.forceClearTextWithElement("Employer Registration Number");
		sleep(1000);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanvalue);
		commonFuntions.screenShot("ErnSearch", "Pass", "ernSearch");
		commonFuntions.clickButtonContains("Search");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("My Q", "Pass", "Work item screen is visible");

		test.info("Step: 9 -- ");
		//commonFuntions.clickOnLink("Audit Protest Task"); 
		fis002.auditProtestWorkItem.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		commonFuntions.clickButtonContains("Open Work Item ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Transfer Protest", "Pass", "PFP-050 screen is visible");

		test.info("Step: 10 -- ");
		commonFuntions.selectRadioQuestions("Do you want to reroute this task to another work basket/User?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to create a Field Audit Request?", "No ");
		commonFuntions.selectRadioQuestions("Do you want to add a Hold Action Flag on this account?", "No ");
		commonFuntions.selectRadioQuestions("Is this Employer Protesting 50% Fraud Penalty?", "Yes ");
		commonFuntions.selectRadioQuestions("Do you want to close this work item with no action taken?", "Yes ");
        fis002.PFP020CommentFeild.sendKeys("test com");
        commonFuntions.clickButtonContains("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(1000);
		commonFuntions.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");
		
		test.info("Step: 11 -- ");
		suc002.homeButton.click();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
		
		
		
		
	}

}
