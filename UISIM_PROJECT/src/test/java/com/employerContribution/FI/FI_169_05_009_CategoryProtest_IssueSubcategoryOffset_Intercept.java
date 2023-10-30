package com.employerContribution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.FIpage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class FI_169_05_009_CategoryProtest_IssueSubcategoryOffset_Intercept extends TestBase {

	@Test
	public void FI_169_05_009() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		FIpage fiPage = new FIpage(driver);
		test = report.createTest(
				"FI.169.05.009-Verify TPR can submit an FI Issue when Issue Category - Protest, Issue Subcategory - 'Offset_Intercept' and system create task for CSR review ");
		cf.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();cf.waitForLoadingIconToDisappear();
		try {
		cf.clickButtonContains(" I agree with the Terms and Conditions ");
		sleep();cf.waitForLoadingIconToDisappear();
		}catch(Exception e) {
		}
		AddPage.menu.click();
		sleep();
		cf.clickMenu("Secure Messaging");
		sleep();
		cf.screenShot("NavigateToWriteMessage", "Pass", "Navigating to Write Message");
		cf.clickMenu("Write Message - Enter ERN");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("WriteMessageEnterErn", "Pass", "Write Message Enter Ern");
		
		// Query
	    Map<String, String> databaseEanResult = cf.database_SelectQuerySingleColumn(
	            "SELECT * FROM t_employer WHERE EMPLOYER_ID IN (\r\n" + 
	            "SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE \r\n" + 
	            "THIRD_PARTY_CDS_VENDOR_ID = '299'\r\n" + 
	            ");",
	            "EAN");
	    String eanValue = databaseEanResult.get("EAN");
	    System.out.println("The EAN is " + eanValue);
	    cf.enterTextboxContains("Employer Registration Number", eanValue);
	    cf.screenShot("enteredErnValue", "Pass", "Valid Ern Entered");
	    cf.clickButtonContains("Continue ");
	    sleep();cf.waitForLoadingIconToDisappear();
	    
	    //Write Message
	    cf.screenShot("writeMessage", "Pass", "Write Message");
		cf.selectDropdown("Category", " Protest ");
		sleep();cf.waitForLoadingIconToDisappear();
		fiPage.subCategoryDropdown.click();sleep(2000);
		fiPage.subCategoryValue2.click();sleep(2000);
		//cf.selectDropdown("Subcategory", " How do I protest an Offset/Intercept (SWOP/TOP)? ");
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
		sleep();cf.waitForLoadingIconToDisappear();
		
		cf.screenShot("SubmitIssue", "Pass", "Submit Issue");
		String ernValue = cf.retrieveValue("Employer Registration Number").trim();
		ernValue = ernValue.replace("-", "");
		System.out.println("Selected ERN is:" + ernValue);
		test.log(Status.INFO, "ERN::" + ernValue);

		// Submit Issue
		cf.enterTextboxContains("Offset/Intercept Source Name", "AutoTest");
		cf.enterCurrentDate("Offset/Intercept Source Date");
		cf.enterTextboxContains("Offset/Intercept Amount ($)", "1000");
		fiPage.offsetInterceptProtestReason.sendKeys("testing");
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("Sample");
		sleep(3000);
		cf.screenShot("SubmitIssueDetailsEntered", "Pass", "Submit Issue Details Entered");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("SubmitIssueVerification", "Pass", "Submit Issue Verification");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("IssueSubmissionConfirmation", "Pass", "Issue Submission Confirmation");
		cf.clickButtonContains("Home ");sleep(5000);
		cf.screenShot("homePage", "Pass", "Home Page");

	}

}
