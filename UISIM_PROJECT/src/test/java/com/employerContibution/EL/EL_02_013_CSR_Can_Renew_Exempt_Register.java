package com.employerContibution.EL;

/* Need a refined query to run the testcase */

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_02_013_CSR_Can_Renew_Exempt_Register extends TestBase {

	@Test
	public void EL_02_013() throws Exception {

		test = report.createTest("EL.02.013 : Verify CSR  can renewal PEO Exempt registration.");
		
		//test.log(Status.INFO, "Script developed by Abhinab");
		test.log(Status.INFO, "Script updated by Das, Ankan.");
		
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions commonFuntions = new commonStepDefinitions();

		//GET method
//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
//				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' AND COMPANY_TYPE = 'PRI' AND FEIN IS NOT FALSE ORDER BY ORGANIZATION_TYPE DESC",
//				"FEIN");
		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
//				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' AND FEIN IS NOT NULL  ORDER BY ORGANIZATION_TYPE DESC",
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE TYPE_OF_REQUEST='PEOER' AND FEIN IS NOT NULL AND CREATED_BY IN ('LEGACY') ORDER BY ORGANIZATION_TYPE DESC;",
				"FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("feinValue is" + feinValue);
		/*Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn(
//				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOER' AND FEIN IS NOT NULL  ORDER BY ORGANIZATION_TYPE DESC",
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE TYPE_OF_REQUEST='PEOER' AND FEIN IS NOT NULL AND CREATED_BY IN ('LEGACY') ORDER BY ORGANIZATION_TYPE DESC;",
				"EMPLOYER_REGISTRATION_NUMBER");
		String ernValue = databaseResults.get("EMPLOYER_REGISTRATION_NUMBER");
		System.out.println("ernValue is " + ernValue);*/
		
		if ((feinValue == null) || feinValue.isEmpty())
		{
			System.out.println("FEIN value is null");
//			System.out.println("ERN value is null");
		} else {
			test.log(Status.PASS, "DB Connected successfully & fetched FEIN generated is " + feinValue + ".");
//			test.log(Status.PASS, "DB Connected successfully & fetched ERN generated is " + ernValue + ".");
		}
		
			
		// --- Login ---
		commonFuntions.login(COMMON_CONSTANT.PEO_SPECIALIST.toUpperCase(), COMMON_CONSTANT.PEO_SPECIALIST_PASSWORD);
		test.log(Status.PASS, "Login with Peo Specialist Role is successful");
		
		// ---Menu Click---
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		peoPage.menu.click();
		sleep(2000);
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		peoPage.menuPeo.click();
		commonFuntions.ScrollMenu("Renew PEO");
		sleep();
		commonFuntions.screenShot("MenuPage", "Pass", "Navigate to Menu -> Professional Employer Organization(PEO) -> Renew PEO");
		commonFuntions.clickMenu("Renew PEO");
		
		// --- MPEO-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Search for PEO(MPEO-001) page");
		commonFuntions.enterTextboxContains("PEO Name", "test");
		sleep();
		commonFuntions.screenShot("EL02013", "Pass", "Entered PEO Name as 'test'");
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "No data present with this Peo Name");
		
		commonFuntions.enterTextboxContains("PEO Name", "");
		commonFuntions.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Clicked on 'Advanced Search' hyperlink");
		
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", "");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		
//		commonFuntions.enterTextboxContains("Employer Registration Number", "");
//		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Entered ERN to search");
		commonFuntions.clickButtonContains(" Search ");
		sleep(5000);
		commonFuntions.selectRadioInTable("", 1, 1, "");
		sleep();
		commonFuntions.screenShot("EL02013", "Pass", "Selected required radio button.");
		commonFuntions.clickButton("Continue ");
		
		//--- for advanced search ---
//		PEOPage.advancedSearch.click();
//		Thread.sleep(4000);
//		commonFuntions.enterTextboxContains("(FEIN)", feinValue);
//		commonFuntions.screenShot("file1", "Pass", "Searching with FEIN ");
//		commonFuntions.clickButtonContains("search");
//		Thread.sleep(4000);
//		commonFuntions.selectRadioInTable("298106328", 1, 1, "");
//		sleep();
//		commonFuntions.screenShot("file1", "Pass", "Selected radio for FEIN ");
//		commonFuntions.clickButton("Continue ");
		
		// --- RPEOE-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Renew PEO Registration(RPEOE-001) page");
		try {
			commonFuntions.enterTextboxContains("Additional names", "");
			commonFuntions.enterTextboxContains("Additional names", "exempttest");
		} catch(Exception exception) { 
			commonFuntions.enterTextboxContains("Additional names if any under which the PEO currently conducts business", "");
			commonFuntions.enterTextboxContains("Additional names if any under which the PEO currently conducts business", "exempttest");
		}
		
//		try {
//			commonFuntions.clickOnLinkAnchorTag(" + ADD ANOTHER NAME ");
//			peoPage.additionalNamesId.clear();
//			peoPage.additionalNamesId.sendKeys("testpeouser");
//		} catch(Exception exception) {
//			exception.printStackTrace();
//		}
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Additional name added to RPEOE-001 page");
		commonFuntions.clickButton("Continue ");
		
//		commonFuntions.waitForLoadingIconToDisappear();
//		commonFuntions.screenShot("file2", "Pass", "Navigating to next page");
//		commonFuntions.clickButton("Continue ");
//		Thread.sleep(4000);
		
		// --- PEO-002 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to General Information for PEO Exempt Registration(PEO-002) page");
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?", "No ");
//		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
//		commonFuntions.enterTextboxContains(" Federal Employer Identification Number (FEIN) ", "");
//		commonFuntions.enterTextboxContains(" Federal Employer Identification Number (FEIN) ", "127434898");
		commonFuntions.enterTextboxContains("Fiscal Year Start Date", "");
		commonFuntions.enterPastDate("Fiscal Year Start Date", 13);
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Updated the Fiscal Year Start Date in PEO-002 page");
		
		//Out-Of-State PEO registration information
		commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " California ");
		commonFuntions.enterTextboxContains("State agency that issued it.", "exempt");
		commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
		commonFuntions.enterTextboxContains("Registration Number ", "4367572193");
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Data entered to Out-Of-State PEO registration information PEO-002 page");
		
		commonFuntions.clickButton("Save & Continue ");
		
		
		// --- EAS-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Unemployment Insurance Account Details(EAS-001) page");
		commonFuntions.clickButton("Save & Continue ");
		
//		commonFuntions.screenShot("file3", "Pass", "Navigated to EAS-001 page");
//		commonFuntions.clickButton("Save & Continue ");
//		Thread.sleep(4000);

		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}

		
		// --- PEO-003 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Address Information(PEO-003) page");
		
		peoPage.addressLine1.clear();
		sleep();
		peoPage.addressLine1.sendKeys("20 Cooper Square");
		peoPage.addressLine2.clear();
		peoPage.addressZip.clear();
		sleep();
		peoPage.addressZip.sendKeys("10003");
		sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Edited data in PEO-003 page");
		commonFuntions.clickButton("Save & Continue ");
		
//		commonFuntions.screenShot("file4", "Pass", "Navigating to PEO-003  page");
//		peoPage.addressLine1.clear();
//		Thread.sleep(4000);
//		peoPage.addressLine1.sendKeys("Test Address Data");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("file5", "Pass", "Entered the address lane 1");
//		commonFuntions.clickButton("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("file6", "Pass", "Pop up if displayed");
		try {
			peoPage.uspsAddress.click();
			sleep(2000);
			commonFuntions.screenShot("EL02013", "Pass", "Selected USPS address radio from Verify Contact Details");
			commonFuntions.clickButton("Continue ");
		} catch (Exception e) {
			test.log(Status.PASS, "Verify Contact Details - Pop up not displayed");
			System.out.println("Pop up not displayed");
		}

		// --- PEO-005 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Verify Current Additional Address(es) in New York");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Verify Current Additional Address(es) in New York (PEO-005) page");
			commonFuntions.clickButton("Continue ");
		}
		 catch(Exception exception) {
			test.log(Status.PASS, "PEO-005 page not reached"); 
		 }
		
//		Thread.sleep(4000);
//		PEOPage.addressLine1.sendKeys("Test Address Data");
//		Thread.sleep(4000);
//		commonFuntions.clickButton("Continue ");
//		commonFuntions.clickButton("Save & Continue ");
		
		// --- PEOE-004 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Exemption Submission Instructions(PEOE-004) page");
		commonFuntions.clickButton("Save & Continue ");
		
//		Thread.sleep(3000);
//		commonFuntions.clickButton("Continue ");
		
		// --- PEO-006 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Prior Address(es) in New York");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Prior Address(es) in New York(PEO-006) page");
			commonFuntions.clickButton("Save & Continue ");
		}
		 catch(Exception exception) {
			System.out.println("PEO-006 page not reached");
//			test.log(Status.PASS, "PEO-006 page not reached"); 
		 }
		
		// --- PEO-007 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Verify Prior Address(es) in New York");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Verify Prior Address(es) in New York(PEO-007) page");
			commonFuntions.clickButton("Continue ");
		}
		 catch(Exception exception) {
			System.out.println("PEO-007 page not reached");
//			test.log(Status.PASS, "PEO-007 page not reached"); 
		 }
		
		// --- PEO-008 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Ownership Information - privately or closely held company");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Ownership Information - privately or closely held company(PEO-008) page");
			commonFuntions.clickButton("Save & Continue ");
			try {
				peoPage.uspsAddress.click();
				sleep(2000);
				commonFuntions.screenShot("EL02013", "Pass", "Selected USPS address radio from Verify Contact Details");
				commonFuntions.clickButton("Continue ");
	
			} catch (Exception e) {
				test.log(Status.PASS, "Verify Contact Details - Pop up not displayed");
				System.out.println("Pop up not displayed");
			}
		}
		 catch(Exception exception) {
			System.out.println("PEO-008 page not reached");
//					test.log(Status.PASS, "PEO-007 page not reached"); 
		 }
		
		// --- PEO-009 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Verify Ownership Information");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Verify Ownership Information(PEO-009) page");
			commonFuntions.clickButton("Continue ");
		}
		 catch(Exception exception) {
			System.out.println("PEO-009 page not reached");
//					test.log(Status.PASS, "PEO-007 page not reached"); 
		 }
		
		// --- PEO-010 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Prior Ownership Information");
			peoPage.addressLine1.clear();
			peoPage.addressLine1.sendKeys("Metrotech Center");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Prior Ownership Information(PEO-010) page");
			commonFuntions.clickButton("Save & Continue ");
			try {
				peoPage.uspsAddress.click();
				sleep(2000);
				commonFuntions.screenShot("EL02013", "Pass", "Selected USPS address radio from Verify Contact Details");
				commonFuntions.clickButton("Continue ");
			} catch (Exception e) {
				test.log(Status.PASS, "Verify Contact Details - Pop up not displayed");
				System.out.println("Pop up not displayed");
			}
		}
		 catch(Exception exception) {
			System.out.println("PEO-010 page not reached");
		 }
		
		// --- PEO-011 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Verify Prior Ownership Information");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Verify Prior Ownership Information(PEO-011) page");
			commonFuntions.clickButton("Continue ");
		}
		 catch(Exception exception) {
			System.out.println("PEO-011 page not reached");
		 }
		
		// --- PEO-012 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Ownership Information - Publicly Traded Company");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Ownership Information - Publicly Traded Company(PEO-012) page");
			peoPage.addressLine1.sendKeys("47 W 13th St");
			commonFuntions.clickButton("Save & Continue ");
			try {
				peoPage.uspsAddress.click();
				sleep(2000);
				commonFuntions.screenShot("EL02013", "Pass", "Selected USPS address radio from Verify Contact Details");
				commonFuntions.clickButton("Continue ");
	
			} catch (Exception e) {
				test.log(Status.PASS, "Verify Contact Details - Pop up not displayed");
				System.out.println("Pop up not displayed");
			}
			
		}
		 catch(Exception exception) {
			System.out.println("PEO-012 page not reached");
//			test.log(Status.PASS, "PEO-012 page not reached"); 
		 }
		
		// --- PEO-013 ---
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			commonFuntions.Label("Verify Public Ownership Information");
			commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Verify Public Ownership Information(PEO-013) page");
			commonFuntions.clickButton("Continue ");
		}
		 catch(Exception exception) {
			System.out.println("PEO-013 page not reached");
		 }
		
		// --- PEO-014 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Submission Instructions and Responsibilities(PEO-014) page");
		commonFuntions.clickButton("Continue ");
		
		
		// --- SREG-006 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Upload Documents(SREG-006) page");
		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS");
		Thread.sleep(2000);
		commonFuntions.selectLink("Authorization to do business in NYS from the NYS", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(2000);
		commonFuntions.screenShot("EL02013", "Pass", "Successfully Uploaded document in SREG-006 page");
		
//		Thread.sleep(3000);
//		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS");
//		Thread.sleep(4000);
//		commonFuntions.selectLink("Authorization to do business in NYS from the NYS", "Browse");
//		Thread.sleep(4000);
//		commonFuntions.uploadDoc("Sample.docx");
//		Thread.sleep(4000);
		
		try {
			commonFuntions.screenShot("EL02013", "Pass", "Click Yes on Upload pop-up");
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			commonFuntions.screenShot("EL02013", "Pass", "Click on Upload button");
			test.log(Status.PASS, "Upload Pop up not displayed");
			System.out.println("Pop up not displayed");
		}
		commonFuntions.clickButtonContains(" Upload ");
		Thread.sleep(10000);
		commonFuntions.clickButton("Save & Continue ");
		
		// --- PEO-015 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Upload Client List(PEO-015 ) page");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(3000);
		commonFuntions.uploadDoc("PEOClientListTemplate.xls");
		Thread.sleep(3000);
		commonFuntions.screenShot("EL02013", "Pass", "Successfully uploaded Client List in PEO-015 page");
		commonFuntions.clickButton("Continue ");
		
		// --- LEAS-012 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Verify Client List(LEAS-012) page");
		commonFuntions.clickButton("Continue ");
		
//		Thread.sleep(3000);
//		commonFuntions.clickButton("Continue ");
		
		// --- PEOR-001 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to PEO Details Review screen(PEOR-001) page");
		commonFuntions.clickButton("Save & Continue ");
		
//		Thread.sleep(3000);
//		commonFuntions.clickButton("Save & Continue ");
		
		// --- PEO-016 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Declaration(PEO-016) page");
		commonFuntions.clickButton("Save & Continue ");
		
//		Thread.sleep(3000);
//		commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
//		Thread.sleep(3000);
//		commonFuntions.clickButton("Save & Continue ");
		
		// --- PEO-017 ---
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EL02013", "Pass", "Successfully launched to Statement Of Acknowledgement(PEO-017) page");
		commonFuntions.clickButton("Accept & Submit ");
		
//		Thread.sleep(3000);
//		commonFuntions.screenShot("Final", "Pass", "Click Accep & Submit");
//		commonFuntions.clickButton("Accept & Submit ");
		
		commonFuntions.Label("System Failure");
		
		commonFuntions.screenShot("EL02013", "Fail", "Unable to submit due to System Failure");
		
		
		
		Thread.sleep(3000);
		commonFuntions.clickButtonContains("Home ");
		Thread.sleep(5000);
		peoPage.queue.click();
		Thread.sleep(15000);
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.screenShot("ErnSearch", "Pass", "ERN enetered in Search");
		commonFuntions.clickButtonContains("Search");
		Thread.sleep(4000);
		commonFuntions.screenShot("Review Peo", "Pass", "Review Peo");
		commonFuntions.clickOnLink("Review PEO");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Open Work Item");
		Thread.sleep(4000);
		commonFuntions.screenShot("Review", "Pass", "Review Peo Registration");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(4000);
		commonFuntions.selectDropdown("States in which the PEO is licensed or registered as a PEO", " California ");
		commonFuntions.enterTextbox("State agency that issued it.", "New York");
		commonFuntions.selectRadioQuestions("Provide Information", "Registration Number");
		commonFuntions.enterTextbox("Registration Number ", "3458767985");
		long number = commonFuntions.createRandomInteger(10000, 99999);
		
		String eanValue = "12" + Long.toString(number);
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		try {
			peoPage.uspsAddress.click();
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(3000);
		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS");
		Thread.sleep(4000);
		commonFuntions.selectLink("Authorization to do business in NYS from the NYS", "Browse");
		Thread.sleep(4000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		commonFuntions.clickButtonContains("Upload");
		Thread.sleep(10000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(10000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(4000);
		commonFuntions.enterTextboxContains("Enter name of Officer,", "Test_Data");
		Thread.sleep(3000);
		commonFuntions.clickButton("Save & Continue ");
		Thread.sleep(3000);
		commonFuntions.clickButton("Continue ");
		Thread.sleep(4000);

		commonFuntions.selectRadio("Approved");
		commonFuntions.screenShot("Approval", "Pass", "ApprovalPage");
		commonFuntions.clickButtonContains("Submit");
		Thread.sleep(4000);
		commonFuntions.screenShot("Success", "Pass", "SuccessPage");
	}
}
