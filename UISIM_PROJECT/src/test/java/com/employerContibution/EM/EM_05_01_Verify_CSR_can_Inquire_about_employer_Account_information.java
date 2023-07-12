package com.employerContibution.EM;



	import java.util.Map;

	import org.openqa.selenium.support.PageFactory;
	import org.testng.annotations.Test;

	import com.ui.base.TestBase;
	import com.ui.pages.EmployerRegisterPage;
	import com.ui.pages.PEOPage;
	import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.screenShot;

import stepDefinitions.commonStepDefinitions;

	public class EM_05_01_Verify_CSR_can_Inquire_about_employer_Account_information extends TestBase {

		@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can enter ERN and rate year and manually update desired information with select override reasons 'Administrative Decision,", groups = {
				COMMON_CONSTANT.REGRESSION })
		public void TC_ERM_48_001() throws Exception {
			
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

			test = report.createTest("EM_05_01_Verify_CSR_can_Inquire_about_employer_Account_information");

			commonStepDefinitions commonFunction = new commonStepDefinitions();
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
			
			  
			  Map<String, String> databaseEanResult =
			  commonFunction.database_SelectQuerySingleColumn(
			  "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea) AND RATE_YEAR = '2023')"
			  , "EAN"); 
			  String eanValue = databaseEanResult.get("EAN");
			  System.out.println("The EAN is " + eanValue);
			  
			  
			  Map<String, String> databaseRateYear =
			  commonFunction.database_SelectQuerySingleColumn(
			  "SELECT RATE_YEAR FROM T_EMPLOYER_RATE ter WHERE EMPLOYER_ACCOUNT_ID IN (SELECT EMPLOYER_ACCOUNT_ID FROM T_EMPLOYER_ACCOUNT tea WHERE EAN = '" +eanValue+ "')"
			  , "RATE_YEAR");
			  String rateYr = databaseRateYear.get("RATE_YEAR");
			  System.out.println("The Rate Year is " + rateYr);
			  
			  
			 
			// --- Login ---
			commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			sleep(2000);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

			// ---Menu Click---
			commonFunction.clickMenu("Menu");
			//commonFunction.clickButtonContains("Home");
			
			commonFunction.clickMenu("Inquiry");
			commonFunction.clickMenu("Contribution Inquiry");
			sleep(2000);
			commonFunction.screenShot("ERM4", "Pass", "Inquiry Employer Account Path");
			commonFunction.clickMenu("Inquiry Employer Account");
			sleep(2000);
			commonFunction.screenShot("ERM5", "Pass", "Inquiry Employer Account Page");
			commonFunction.enterTextboxContains("Employer Registration Number", "");
			commonFunction.clickButton("Continue ");
			commonFunction.errorContent("Either Employer Registration Number(ERN) or FEIN must be entered");
			commonFunction.screenShot("page1", "pass", "errror 1 validated");
            
            sleep(2000);
			commonFunction.enterTextboxContains("Employer Registration Number", "0000269");
			commonFunction.clickButton("Continue ");
			commonFunction.errorContent("The Employer Registration Number (ERN) provided does not exist in the system.");
			commonFunction.screenShot("page2", "pass", "errror 2 validated");
			commonFunction.clearTextboxContains("Employer Registration Number");
			sleep(2000);
			commonFunction.enterTextboxContains(" FEIN ", "6788774646");
			commonFunction.clickButton("Continue ");
			commonFunction.errorContent("Invalid ERN and FEIN Combination");
			commonFunction.screenShot("page2", "pass", "errror 2 validated");
			commonFunction.clearTextboxContains(" FEIN ");
			driver.navigate().refresh();
	
			sleep(2000);
			commonFunction.enterTextboxContains("Employer Registration Number", eanValue);
			commonFunction.screenShot("page3", "pass", "No errror  validated");
			sleep(5000);
			commonFunction.clickButton("Continue ");
			sleep(2000);
			commonFunction.screenShot("SRG-051", "Pass", "Inquiry Employer Account Information page is displayed");
			String originalWindow = driver.getWindowHandle();
			sleep(2000);
			PEOPage.pastYear.click();
			sleep(2000);
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("CBK-017", "Pass", "Benefit Charge Transaction Inquiry page is displayed");
			driver.close();
			driver.switchTo().window(originalWindow);
			PEOPage.rateYear.click();
			sleep(10000);
			commonFunction.screenShot("ERM-013", "PASS", "Current Rating Account Status History pge is displayed");
			commonFunction.clickButtonContains("Previous "); 
			
			sleep(2000);
			PEOPage.accountHistory.click();
			sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SRG-053", "Pass", "Employer Account Profile Changes page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
//			commonFunction.driver.close();
//			commonFunction.driver.switchTo();
//			commonFunction.driver.ge
			
			
			PEOPage.bankAccountsInquiry.click();
			
			sleep(4000);

			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			//driver.switchTo().newWindow("https://uiservices-sit.labor.ny.gov/UIMContributionsWeb/uinteract/welcome/welcomepage");
			commonFunction.screenShot("TWR-269", "Pass", "BankAccountsInquiry page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.viewCorrespondence.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("DMS-001", "Pass", "ViewCorrespondence page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.addressDetails.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-486", "Pass", "AddressDetails page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.ownershipDetails.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-702", "Pass", "OwnershipDetails page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
		/*
		 * PEOPage.jointAccountDetails.click(); sleep(4000);
		 * 
		 * for (String windowHandle : driver.getWindowHandles()) {
		 * if(!originalWindow.contentEquals(windowHandle)) {
		 * driver.switchTo().window(windowHandle); break; } }
		 * commonFunction.screenShot("SREG-493", "Pass",
		 * "JointAccountDetails page is displayed"); sleep(2000); driver.close();
		 * driver.switchTo().window(originalWindow);
		 */
			
			PEOPage.inquiryPEOInformation.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("IPI", "Pass", "InquiryPEOInformation page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.currentBalance.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("TWR-280", "Pass", "CurrentBalance page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.accountMaintenance.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-030", "Pass", "AccountMaintenance page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.penaltyDetails.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("FIP-002", "Pass", "PenaltyDetails page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.flagDetails.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-544", "Pass", "FlagDetails page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.commentsHistory.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("TWR-250", "Pass", "CommentsHistory page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.pOATPRDetails.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-537", "Pass", "POA/TPRDetails page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);


			PEOPage.annualRateCalculation.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("INQ-200", "Pass", "AnnualRateCalculation page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.businessPrincipalActivityDetails.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-402", "Pass", "BusinessPrincipalActivityDetails page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.crossReferences.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("SREG-435", "Pass", "CrossReferences page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			PEOPage.assignmentHistory.click();
sleep(4000);
			
			for (String windowHandle : driver.getWindowHandles()) {
				if(!originalWindow.contentEquals(windowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
				}
				}
			commonFunction.screenShot("AUD-811", "Pass", "AssignmentHistory page is displayed");
			sleep(2000);
			driver.close();
			driver.switchTo().window(originalWindow);
			
			
	}

}
