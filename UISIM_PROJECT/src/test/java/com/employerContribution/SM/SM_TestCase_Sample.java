package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIpage;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_TestCase_Sample extends TestBase {

	@Test
	public void SM_TestCase() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIpage fiPage = new FIpage(driver);
		SMPage sm = new SMPage(driver);
		test = report.createTest(
				"Select Category (For example Collections)\r\n" + 
				"Select Sub Category (For example Other)\r\n" + 
				"Write Message in the editor\r\n" + 
				"Click Send\r\n" + 
				"Confirmation Screen should appear.");
		cf.login(COMMON_CONSTANT.EMPLOYER_USER_4.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(5000);
		cf.waitForLoadingIconToDisappear();
		try {
			cf.clickButtonContains(" I agree with the Terms and Conditions ");
			sleep();
			cf.waitForLoadingIconToDisappear();
		} catch (Exception e) {
		}
		AddPage.menu.click();
		sleep(10000);
		cf.clickMenu("Secure Messaging");
		cf.screenShot("NavigateToWriteMessage", "Pass", "Navigating to Write Message");
		cf.clickMenu("Write Message");
		sleep(7000);
		
		// Write Message(SM-101).....
		cf.screenShot("WriteMessage", "Pass", "Write Message");
		cf.selectDropdown("Category", " Collection Notices ");
		sleep();cf.waitForLoadingIconToDisappear();
		fiPage.subCategoryDropdown.click();sleep(4000);
		fiPage.otherDropdownValue.click();
		sleep(2000);
		sm.write_SecureMessage.sendKeys("I have requested for collection notice");
		sleep(3000);
		cf.screenShot("WriteMessageScreenAfterEnteringDetails", "Pass", "Write Message Screen After Entering Details");
		cf.clickButtonContains("Send ");
		sleep(7000);
		
		//Secure Message Confirmation(SUC-002)
		cf.screenShot("secureMessageConfirmation", "Pass", "Secure Message Confirmation");
		
	}

}
