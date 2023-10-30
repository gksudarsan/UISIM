package com.employerContribution.SM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.SMPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class SM_001_001_CSR_SendSecureMessageToClaimant extends TestBase {

	@Test()
	public void SM_001_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		SMPage sm = new SMPage(driver);

		test = report
				.createTest("SM.001.001-Verify that the system allows the CSR to send a secure message to a claimant.");
		cf.login(COMMON_CONSTANT.CollectionsSpecialistUser_1.toUpperCase(), COMMON_CONSTANT.CollectionsSpecialistUser_1_PASSWORD);
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
		cf.screenShot("writeMessage", "Pass", "Write Message");
		
		/*------- Write Message-------(SM-101)*/
		// 1st Validation
		cf.selectDropdown("Category", " Collection Notices ");sleep();
		cf.selectDropdown("Subcategory", " How do I file a Quarterly Combined Withholding, Wage Reporting and Unemployment Insurance Return (Form NYS-45)? ");sleep();
		sm.write_SecureMessage.sendKeys("testing testing.");
		cf.clickButtonContains("Send ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("recipientIsRequired", "Pass", "Recipient is Required");
		cf.errorLabel(" Recipient is required.");sleep();

		// 2nd Validation
		cf.enterTextboxContains("Recipient", "test automation");
		cf.selectDropdown("Category", " Collection Notices ");sleep();
		cf.selectDropdown("Subcategory", " Other ");sleep();
		cf.forceClearText(sm.subject);
		cf.clickButtonContains("Send ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("subjectIsRequired", "Pass", "Subject is Required");
		cf.errorLabel(" Subject is required.");sleep();

		// 3rd Validation
		sm.subject.sendKeys(
				"Many applications of randomization have caused several methods to exist for generating random data. "
				+ "Lottery games is one current application. Slot machine odds are another use of random number generators.");
		cf.clickButtonContains("Send ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("moreThanHundredCharacters", "Pass", "More Than Hundred Chracters");
		
		// 4th Validation
		cf.forceClearText(sm.subject);
		sm.subject.sendKeys("Other");sleep();
		cf.selectDropdown("Category", "--SELECT--");
		sleep(2000);
		cf.clickButtonContains("Send ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("categoryIsRequired", "Pass", "Category is Required");
		cf.errorLabel(" Category is required.");sleep();
		
		// 5th Validation
		cf.selectDropdown("Category", " Collection Notices ");sleep();
		cf.selectDropdown("Subcategory", "--SELECT--");sleep();
		cf.clickButtonContains("Send ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("subCategoryIsRequired", "Pass", "Sub Category is Required");
		cf.errorLabel(" Sub Category is required.");sleep();
		
		// 6th Validation
		cf.selectDropdown("Subcategory", " Other ");sleep();
		cf.forceClearText(sm.write_SecureMessage);
		cf.clickButtonContains("Send ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("messageIsRequired", "Pass", "Message Is Required");
		cf.errorContent("Message is required.");sleep();
		
		// 7th Validation
		sm.write_SecureMessage.sendKeys("Many applications of randomization have caused several methods to exist for generating random data. "
				+ "Lottery games is one current application. Slot machine odds are another use of random number generators.");
		cf.clickButtonContains("Send ");
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("MessagesCannotMoreThan10000Characters", "Pass", "Messages Cannot More Than 10000 Characters");
		
		// 8th Validation
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("Sample2");
	    sleep(4000);
	    cf.screenShot("fileSizeIsTooLarge", "Pass", "File Size is too large");
	    cf.clickButtonContains("OK");
	    sleep(2000);
	    
	    // 9th Validation 
	    AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("chrome");
	    sleep(4000);
	    cf.screenShot("fileFormatNotSupported", "Pass", "File Format Not Supported");
	    cf.clickButtonContains("OK");
	    sleep(2000);
	    
	    //Again navigating to write message...
	    cf.clickButtonContains(" Cancel ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("homePage", "Pass", "Home Page");
		AddPage.menu.click();
		sleep();
		cf.clickMenu("Secure Messaging");
		sleep();
		cf.clickMenu("Write Message");
		sleep();
		cf.clickButtonContains(" Search Recipient ");sleep(2000);
		
		/*------Search Recipient------(SM-102)*/
		 cf.screenShot("searchRecipient", "Pass", "Search Recipient");
		 cf.clickButtonContains(" Search ");sleep(2000);
		 cf.errorContent("This recipient does not have a UI Services account.");
		 cf.screenShot("recipientDoesNotHaveUiServiceAccount", "Pass", "This recipient does not have a UI Services account.");
		 
		 //To fetch claimant SSN record
		 cf.enterTextboxContains("Claimant SSN", "079677592");
		 cf.clickButtonContains(" Search ");
		 cf.waitForLoadingIconToDisappear();
		 sleep(2000);
		 cf.clickButtonContains("Continue ");
		 sleep();cf.waitForLoadingIconToDisappear();
		 cf.screenShot("rowMustBeSelectedToContinue", "Pass", "Row must be selected to continue");
		 cf.errorContent("A row must be selected to continue.");
		 sm.selectRadioButton_SM_102.click();
		 cf.clickButtonContains("Continue ");
		 sleep();cf.waitForLoadingIconToDisappear();
		 
		 /*------- Write Message-------(SM-101)*/
		 cf.screenShot("writeMessage_SM_101", "Pass", "Write Message SM_101");
		 cf.selectDropdown("Category", " Collection Notices ");sleep();
		 cf.selectDropdown("Subcategory", " Other ");sleep();
		 sm.write_SecureMessage.sendKeys("testing testing...");
		 AddPage.browserLink.click();
		 sleep(3000);
		 cf.uploadDoc("Sample");
		 sleep(4000);
		 //this test cases have still few changes need to be done in design doc, that is Search Canned Response Button will be post go live.
		
	    
	}

}
