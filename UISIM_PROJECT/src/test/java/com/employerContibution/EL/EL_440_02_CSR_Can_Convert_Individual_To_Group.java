package com.employerContibution.EL;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;

import stepDefinitions.commonStepDefinitions;

public class EL_440_02_CSR_Can_Convert_Individual_To_Group extends TestBase {

	@Test
	public void EL_440_02() throws Exception {
		

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOIR' AND FEIN <> 'FALSE'",
				"FEIN");
		String feinValue = databaseResults.get("Fein");
		System.out.println("feinValue is" + feinValue);

		test = report.createTest("EL.02.013 .Verify CSR  can renewal PEO Exempt registration.");
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		commonFuntions.login("ndfjp3", "Admin@12345678");
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Manage PEO");
		commonFuntions.clickMenu("Manage PEO");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(2000);
		commonFuntions.enterTextboxContains("(FEIN)", feinValue);
		commonFuntions.screenShot("file1", "Pass", "Searching with FEIN ");
		commonFuntions.clickButtonContains("search");
		Thread.sleep(2000);
		commonFuntions.selectRadioWithFeinValue(feinValue);
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("ManagePeo", "Pass", "Navigated to manage Individual Peo screen");
		sleep();
		commonFuntions.ScrollMenu("Browse");
		commonFuntions.screenShot("ManagePeo2", "Pass", "Peo conversion drop down value before change");
		sleep();
		commonFuntions.selectDropdown("PEO Conversion", " PEO Individual to PEO Group ");
		sleep();
		commonFuntions.screenShot("ManagePeo3", "Pass", " Changed the Peo conversion drop down value to :  PEO Individual to PEO Group ");
		sleep();
		commonFuntions.clickButtonContains("  CONVERT ");
		sleep(4000);
		
		/* --------------PEO-002 -----------------*/
		commonFuntions.screenShot("ManagePeo4", "Pass", "Navigated to PEO-002 page");
		commonFuntions.selectRadio("Yes ");
		sleep();
		commonFuntions.selectDropdown("Type of Legal Entity", " Partnership ");
		sleep();
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(4000);
		/* -------------- EAS-001 -----------------*/
		commonFuntions.screenShot("ManagePeo5", "Pass", "Navigated to EAS-001 page");
		commonFuntions.clickButtonContains("Save & Continue ");
		sleep(4000);
		try {
			commonFuntions.screenShot("ManagePeo6", "Pass", "verifying EAS-001 page address pop up");
			commonFuntions.clickButtonContains(" Yes ");
		} catch(Exception e) {
			System.out.println("Pop not not Displayed");
		}
		
		/* -------------- PEO-003 -----------------*/
		sleep(4000);
		commonFuntions.screenShot("ManagePeo7", "Pass", "Navigated to PEO-003 page");
		PEOPage.addressLine1.clear();
		sleep();
		PEOPage.addressLine2.clear();
		sleep();
		PEOPage.addressLine1.sendKeys("Test input address");
		PEOPage.addressLine2.sendKeys("Test address 2");
		sleep();
		commonFuntions.clickButtonContains("Save & Continue ");
		
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.currentAdditionalAddress.click();
			sleep();
			PEOPage.UspsContinueButton.click();
		} catch(Exception e) {
			System.out.println("Pop not not Displayed");
		}
		
		/* -------------- PEO-005 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo8", "Pass", "Navigated to PEO-005 page");
		commonFuntions.clickButton("Continue ");
		
		/* -------------- PEO-004 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo9", "Pass", "Navigated to PEO-004 page");
		PEOPage.addressLine1.clear();
		sleep();
		PEOPage.addressLine1.sendKeys("Test address");
		sleep();
		PEOPage.addressLine2.clear();
		sleep();
		PEOPage.addressLine2.sendKeys("Test Address 2");
		
		try {
			PEOPage.uspsSuggestedAddress.click();
			sleep();
			PEOPage.UspsContinueButton.click();
		} catch(Exception e) {
			System.out.println("Pop not not Displayed");
		}
		
		/* -------------- PEO-006 -----------------*/
		
		sleep(6000);
		commonFuntions.screenShot("ManagePeo10", "Pass", "Navigated to PEO-006 page");
		PEOPage.addressLine1.sendKeys("Test Address");
		PEOPage.addressLine2.sendKeys("Test Address 2");
		commonFuntions.enterTextboxContains("City ", "Test City");
		commonFuntions.enterTextboxContains("Zip Code", "54544");
		commonFuntions.enterTextboxContains("other name(s)", "Test1");
		commonFuntions.enterTextboxContains("Predecessor(s) Name", "Test2");
		commonFuntions.enterTextboxContains("Successor(s) Name", "Test3");
		sleep();
		commonFuntions.clickButtonContains("Save & Continue ");
		
		/* -------------- PEO-007 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo11", "Pass", "Navigated to PEO-007 page");
		commonFuntions.clickButton("Continue ");
		
		/* -------------- PEO-008 -----------------*/
		sleep(4000);
		commonFuntions.screenShot("ManagePeo12", "Pass", "Navigated to PEO-008 page");
		commonFuntions.enterTextboxContains("Entity or Person", "Test1");
		commonFuntions.enterTextboxContains("Ownership Percentage", "55	");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Test Address");
		commonFuntions.enterTextboxContains("Address Line 2 ", "Test Address 2");
		commonFuntions.enterTextboxContains("City ", "Test City");
		commonFuntions.enterTextboxContains("Zip Code", "65655");
		commonFuntions.clickButtonContains("Save & Continue ");
		
		/* -------------- PEO-009 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo13", "Pass", "Navigated to PEO-009 page");
		commonFuntions.clickButton("Continue ");
		
		/* -------------- PEO-010 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo14", "Pass", "Navigated to PEO-010 page");
		commonFuntions.enterTextboxContains("Entity or Person", "Test1");
		commonFuntions.enterTextboxContains("Ownership Percentage", "55	");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Test Address");
		commonFuntions.enterTextboxContains("Address Line 2 ", "Test Address 2");
		commonFuntions.enterTextboxContains("City ", "Test City");
		commonFuntions.enterTextboxContains("Zip Code", "65655");
		commonFuntions.clickButtonContains("Save & Continue ");
		

		/* -------------- PEO-011 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo15", "Pass", "Navigated to PEO-011 page");
		commonFuntions.clickButton("Continue ");
		
		
		/* -------------- PEO-014 -----------------*/
		sleep(4000);
		commonFuntions.screenShot("ManagePeo16", "Pass", "Navigated to PEO-014 page");
		commonFuntions.clickButton("Continue ");
		
		/* -------------- SREG-06 -----------------*/
		sleep(4000);
		commonFuntions.screenShot("ManagePeo17", "Pass", "Navigated to SREG-06 document upload page");
		commonFuntions.clickButtonContains("Save & Continue ");
		
		
		/* -------------- SREG-06 -----------------*/
		
		sleep(4000);
		commonFuntions.screenShot("ManagePeo18", "Pass", "Navigated to PEO-015 document upload page");
		commonFuntions.clickButton("Choose File");
		Thread.sleep(3000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2");
		commonFuntions.clickButton("Continue ");
		
		/* -------------- LEAS-012 -----------------*/
		sleep(4000);
		commonFuntions.screenShot("ManagePeo19", "Pass", "Navigated to LEAS-012 page");
		commonFuntions.clickButton("Continue ");
		sleep(5000);
		
		
	}
}
