package com.employerContribution.FI;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIpage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_119A_07_01_Verify_CSRcaninquireon_FraudTiptasks extends TestBase {

	@Test
	public void FI_119A_07_01() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"FI.119A.07.01-Verify CSR can inquire on Fraud Tip tasks");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();
		sleep();
		cf.clickMenu("Inquiry");
		sleep();
		cf.screenShot("NavigateToInquiry", "Pass", "Navigating to Inquiry Fraud Tips");
		cf.clickMenu("Inquiry Fraud Tips");
		sleep(2000);
		cf.screenShot("InquiryFraudTipTasks", "Pass", "Inquiry Fraud Tip Tasks");
		
		//Inquiry Fraud Tips 
		cf.enterTextboxContains("Investigation", "69");
		cf.clickButtonContains("Search");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.clickButtonContains("Continue");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("errorMessages", "Pass", "A Selection is Required");
		cf.errorContent("A selection is required.");
		
		//By clicking on search option
		cf.clickButtonContains("Search");
		sleep();cf.waitForLoadingIconToDisappear();
		fiPage.selectRadioButton.click();sleep();
		cf.screenShot("selectionMustbeRequired", "Pass", "Radio Button is selected");
		cf.clickButtonContains("Continue");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Inquiry Fraud Tip Details
		cf.screenShot("InquiryFraudTipDetails", "Pass", "Inquiry Fraud Tip Details");
		//cf.clickOnLinkAnchorTag("           View Fraud Tip Questionnaire PDF       ");
		fiPage.link.click();
		sleep(2000);
		cf.clickButtonContains(" Home ");
	
	}

}
