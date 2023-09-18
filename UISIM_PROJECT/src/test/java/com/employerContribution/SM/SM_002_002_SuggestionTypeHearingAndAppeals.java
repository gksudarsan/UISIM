package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_002_002_SuggestionTypeHearingAndAppeals extends TestBase {

	@Test
	public void SM_002_002() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest(
				"SM.002.002-Verify that the system displays a suggestion, but the claimant will not be able to write and send a message. (Suggestion Type H)");
		cf.login(COMMON_CONSTANT.OTHER_USER_1.toUpperCase(), COMMON_CONSTANT.OTHER_USER_PASS_1);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		cf.clickMenu("Secure Messaging");
		sleep();
		cf.screenShot("NavigateToWriteMessage", "Pass", "Navigating to Write Message");
		cf.clickMenu("Write Message");
		sleep();

		// Write Message(SM-101).....
		//system does not allow the Claimant to write or send message.
		cf.screenShot("WriteMessage", "Pass", "Write Message");
		cf.selectDropdown("Message Language", " English ");
		sleep();
		cf.selectDropdown("Category", " Hearing and Appeals ");
		sleep(2000);
		cf.selectDropdown("Subcategory", " Other questions about hearings ");
		cf.enterTextboxContainsThirdBox("Write Your Message Here.", "I have requested for hearing..");
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("Sample");
		sleep(3000);
		cf.screenShot("WriteMessageScreenAfterEnteringDetails", "Pass", "Write Message Screen After Entering Details");
	}

}
