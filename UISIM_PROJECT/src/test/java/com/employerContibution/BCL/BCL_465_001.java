package com.employerContibution.BCL;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.COL_474;
import com.ui.pages.PEOPage;
import com.ui.pages.HomePage; 
import com.ui.pages.COL_521;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_465_001 extends TestBase {

	@Test
	public void BCL465_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		COL_474 col474 = new COL_474(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		HomePage hm = new HomePage(driver);
		COL_521 col521 = new COL_521(driver);

		test = report.createTest(
				"BCL.465.001. Verify CSR can review Delinquency Assignment work item and complete the assignment.");

		commonFuntions.login(COMMON_CONSTANT.Collections_Specialist_1_User.toUpperCase(), COMMON_CONSTANT.Collections_Specialist_1_pwd);

		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		test.info("Step: 3 -- ");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "WF-001 screen is visible");

		test.info("Step: 4 -- ");
		commonFuntions.enterTextboxContains("Work Item Description Free Text", "DELINQUENCY ASSIGNMENT TASK");
		sleep();
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Individual Work Queue", "Pass", "Work item is visible");

		test.info("Step: 5 -- ");
		hm.delinquencyAssignmentTask.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");

		commonFuntions.clickButtonContains("Open Work Item");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Delinquency Assignment", "Pass", "COL-521 screen is visible");

		test.info("Step: 6 -- ");
		commonFuntions.clickButtonContains(" Add Contact Attempt ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();

		test.info("Step: 7 -- ");
		commonFuntions.errorLabel(" Required");
		commonFuntions.screenShot("Delinquency Assignment", "Pass", "Required error screen is visible");
		commonFuntions.forceClearTextWithElement("Date of Contact");
		commonFuntions.selectRadioQuestions(" Escalate to District Office ", "Yes");
		
		test.info("Step: 8&9 -- ");
		int size = col521.statusdropdownList.size();
		
		for(int i=0; i<size;i++) {
			col521.statusdropdownList.get(i).click();
			sleep(2000);
			col521.blankOption.click();
		}
				
		commonFuntions.enterFutureDate("Date of Contact",7);
		commonFuntions.selectDropdown("Result of Contact", " Email ");
		commonFuntions.enterTextboxContains("Person Contacted", "Dev");
		col521.contactCommentsField.sendKeys("test contact comment");
		commonFuntions.selectRadioQuestions("Assign to Supervisor", "No ");
		//commonFuntions.selectRadioQuestions(" Escalate to District Office ", "Yes");
		col521.escalatetoDistrictOfficeYes.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		
		//Action section radio btn
		col521.selectRadioQuestionsInAction("Has it been 6 weeks since the delinquency was issued and/or assigned to you? ", "yes");
		col521.selectRadioQuestionsInAction("Have you made two calls to the telephone number(s) on file? ", "yes");
		col521.selectRadioQuestionsInAction("Did you verify that the telephone number is correct and/or request Accurint Search? ", "no");
		col521.selectRadioQuestionsInAction("Did you research if the Employer is still in business? ", "no");
		col521.selectRadioQuestionsInAction("Did you check Workers Compensastion, Licensing, Yelp. etc.? ", "yes");
		
		//
		commonFuntions.selectDropdown("Transfer Delinquency Assignment", " 1-Troy ");
		commonFuntions.selectRadioQuestions("Closing Type", "Completed");
		col521.commentsField.sendKeys("test comment");
		
		
		test.info("Step: 10 -- ");
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Delinquency Assignment", "Pass", "error is displayed");
		
		test.info("Step: 11 -- ");
		col521.selectRadioQuestionsInAction("Did you verify that the telephone number is correct and/or request Accurint Search? ", "yes");
		col521.selectRadioQuestionsInAction("Did you research if the Employer is still in business? ", "yes");
		//commonFuntions.selectRadioQuestions(" Escalate to District Office ", "No");
		//col521.escalatetoDistrictOfficeNo.click();
		sleep(2000);
		commonFuntions.clickButtonContains("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Delinquency Assignment updated.", "Pass", "SUC-002 page is displayed");
		
		test.info("Step: 12 -- ");
		commonFuntions.clickButtonContains("Home");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("HomePage", "Pass", "Home Page page is displayed");
		
	}

}
