package com.employerContibution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_03_010_CategoryProtest_IssueSubcategoryOffsetIntercept extends TestBase {

	@Test
	public void FI_169_03_010() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIPage fiPage = new FIPage(driver);
		test = report.createTest(
				"FI.169.03.010-Verify Employer can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Offset_Intercept");
		cf.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
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
		cf.screenShot("WriteMessage", "Pass", "Write Message");
		cf.selectDropdown("Category", " Protest ");
		sleep(2000);
		cf.selectDropdown("Subcategory", " How do I protest an Offset/Intercept (SWOP/TOP)? ");
		sleep(2000);
		cf.screenShot("WriteMessage1", "Pass", "Write Message1");
		cf.clickOnLinkAnchorTag("click here");
		sleep();
		cf.waitForLoadingIconToDisappear();

		/*---- Submit Issue ----*/

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowId = it.next();
		String childWindowId = it.next();
		driver.switchTo().window(childWindowId);
		sleep();
		cf.screenShot("SubmitIssue", "Pass", "Submit Issue");
		String eanValue = cf.retrieveValue("Employer Registration Number").trim();
		eanValue = eanValue.replace("-", "");
		System.out.println("Selected ERN is:" + eanValue);
		test.log(Status.INFO, "ERN::" + eanValue);

		// Submit Issue
		cf.enterTextboxContains("Offset/Intercept Source Name", "AutoTest");
		cf.enterCurrentDate("Offset/Intercept Source Date");
		cf.enterTextboxContains("Offset/Intercept Amount ($)", "1000");
		fiPage.offsetInterceptProtestReason.sendKeys("testing");
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("TESTINGEL");
		sleep(3000);
		cf.screenShot("SubmitIssueDetailsEntered", "Pass", "Submit Issue Details Entered");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("SubmitIssueVerification", "Pass", "Submit Issue Verification");
		cf.clickButtonContains("Submit ");
		cf.waitForLoadingIconToDisappear();sleep();
		cf.screenShot("IssueSubmissionConfirmation", "Pass", "Issue Submission Confirmation");
		cf.clickButtonContains("Home ");sleep(5000);
		cf.screenShot("homePage", "Pass", "Home Page");

	}

}
