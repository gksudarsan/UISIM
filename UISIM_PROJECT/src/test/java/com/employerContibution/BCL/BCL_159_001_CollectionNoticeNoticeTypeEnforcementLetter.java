package com.employerContibution.BCL;



import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.BclPage;

import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class BCL_159_001_CollectionNoticeNoticeTypeEnforcementLetter extends TestBase {

	@Test
	public void BCL_159_001() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		//PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		//LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		BclPage bclPage = new BclPage(driver);
		test = report
				.createTest("BCL.159.001-Verify CSR can submit collection notice for single quarter and notice type is Enforcement Letter");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
        cf.ScrollMenu("Employer Collection");
        cf.clickMenu("Employer Collection");sleep();
        cf.clickMenu("Collections");sleep();
        cf.screenShot("GenerateCollectionNoticeEnterERN", "Pass", "Navigating to Generate Collection Notice");
        cf.clickMenu("Generate Collection Notice - Enter ERN");sleep();
        
        /*-----Generate Collection Notice – Enter ERN  (COL - 548)-----*/
        
        cf.screenShot("GenerateCollectionNoticeEnterERN1", "Pass", "Generate Collection Notice - Enter ERN");
        cf.clickButtonContains("Continue ");sleep(2000);
        cf.screenShot("GenerateCollectionNoticeEnterERN2", "Pass", "RequiredError");
        cf.errorLabel("Required");
        cf.enterTextboxContains("Employer Registration Number", "1111111");
        cf.clickButtonContains("Continue ");sleep(2000);
        cf.screenShot("GenerateCollectionNoticeEnterERN3", "Pass", "The Employer Registration Number is invalid.");
        cf.errorContent("The Employer Registration Number is invalid.");
        cf.enterTextboxContains("Employer Registration Number", "1023453");
        cf.clickButtonContains("Continue ");cf.waitForLoadingIconToDisappear();
        cf.screenShot("GenerateCollectionNoticeEnterERN4", "Pass", "Collection Notice cannot be issued as there is no debt on the account.");sleep();
        cf.errorContent("Collection Notice cannot be issued as there is no debt on the account.");
        cf.enterTextboxContains("Employer Registration Number", "0464364");
        cf.clickButtonContains("Continue ");sleep();cf.waitForLoadingIconToDisappear();
        
        /*---------Generate Collection Notice (COL - 549)-------*/
        
        cf.screenShot("GenerateCollectionNotice", "Pass", "Generate Collection Notice");
        cf.clickButtonContains("Continue ");sleep();cf.waitForLoadingIconToDisappear();
        cf.screenShot("GenerateCollectionNotice1", "Pass", "At least one selection must be made.");
        cf.errorContent("At least one selection must be made.");
        cf.selectCheckbox("Select");
        cf.forceClearText(bclPage.AddressLine1);
        cf.clickButtonContains("Continue ");cf.waitForLoadingIconToDisappear();
        cf.screenShot("GenerateCollectionNotice2", "Pass", "Address Line 1 is required");
        cf.errorContent("Address Line 1 is required");
        bclPage.AddressLine1.sendKeys("49967");
        cf.clickButtonContains("Continue ");sleep(3000);
        
        /*-------Issue Collection Notice (COL - 567)------*/
        
        cf.screenShot("IssueCollectionNotice", "Pass", "Issue Collection Notice");
        cf.enterCurrentDate("Notice Date");sleep();
        cf.selectDropdown("Notice Type", " Enforcement Letter ");
        cf.clickButtonContains("Continue ");sleep(2000);
        cf.screenShot("IssueCollectionNotice1", "Pass", "Select one of the records to proceed.");
        cf.errorContent("Select one of the records to proceed.");
        cf.selectCheckbox("Select");
        cf.forceClearText(bclPage.noticeDate);
        cf.clickButtonContains("Continue ");sleep(2000);
        cf.screenShot("IssueCollectionNotice2", "Pass", "RequiredErrorForNoticeDate");
        cf.enterCurrentDate("Notice Date");sleep();
        cf.selectDropdown("Notice Type", "--SELECT--");
        cf.clickButtonContains("Continue ");sleep(2000);
        cf.screenShot("IssueCollectionNotice3", "Pass", "RequiredErrorForNoticeType");
        cf.selectDropdown("Notice Type", " Enforcement Letter ");
        cf.clickButtonContains("Continue ");sleep(2000);
        
        /*---- Issue Collection Notice – Verification  (COL - 569)-----*/
        
        cf.screenShot("IssueCollectionNoticeVerification", "Pass", "Issue Collection Notice - Verification");
        cf.clickButtonContains("Submit ");cf.waitForLoadingIconToDisappear();
        
        /*---- Issue Collection Notice – Confirmation (SUC - 002)----*/
        
        cf.screenShot("IssueCollectionNoticeConfirmation", "Pass", "Issue Collection Notice - Confirmation");
        cf.clickButtonContains("Home ");sleep(2000);
        cf.screenShot("homePage", "Pass", "Home Page");
	}

}
