package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.internal.Systematiser;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_322_014 extends TestBase {

	@Test(priority = 1, description = "EL.322.014: Verify CSR can register Individual PEO for Type of Legal Entity 'Corporation' and type of Ownership 'Public Ownership'", groups = {
			"Regression" })
	public void EL_322_014() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

		// Map<String, String> databaseResults = PEOPage.database_SelectQuery("SELECT
		// FEIN,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT
		// NULL ORDER BY UPDATED_TS desc");
		// String feinValue =databaseResults.get("Fein");
		// String ernValue = databaseResults.get("Ean");
		// System.out.println("feinValue is"+feinValue);
		test = report.createTest(
				"EL.322.014: Verify CSR can register Individual PEO for Type of Legal Entity 'Corporation' and type of Ownership 'Public Ownership'");
        //------Login
		//commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.login(COMMON_CONSTANT.Role_Peo.toUpperCase(), COMMON_CONSTANT.Role_Peo_Pass);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		//------Menu PEO
		commonFuntions.clickMenu("menu");
		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
		commonFuntions.clickMenu("Register PEO");
		commonFuntions.waitForLoadingIconToDisappear();
		//-------PEO-019
		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details  PEO-019 ");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		//------- PEO-001
		commonFuntions.screenShot("Professional Employer Organization Registration", "Pass",
				"Professional Employer Organization Registration PEO-001 ");
		PEOPage.individualPeo.click();
		commonFuntions.enterTextbox("Name of Professional Employer Organization",
				"Test_auto" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Additional Name(s)",
				"auto_test" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.screenShot("IndividualPeo", "Pass", "Professional Employer Organization Registration PEO 001");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
        //--------PEO -002
		commonFuntions.screenShot("General Information", "Pass", "General Information PEO -002");
		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
				"Yes");
		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("FEIN:" + feinValue);
		System.out.println("EAn:" + ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
		commonFuntions.selectRadioQuestions("Type of Ownership", "Public");
		commonFuntions.enterTextboxContains("Fiscal Year Start Dat", "02/01/2023");
		commonFuntions.screenShot("General Information", "Pass", "General Information PEO -002");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
        //-------EAS 001
		commonFuntions.screenShot("UnemploymentInsurance", "Pass", "Unemployment Insurance Account Details EAS 001");
		try {
			PEOPage.peoRadioButton.click();
			Thread.sleep(2000);
			commonFuntions.selectRadioInTable(feinValue, 1, 1, "Unemployment Insurance Account Details");
		} catch (Exception e) {
		}
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		//------PEO 003
		commonFuntions.screenShot("Address Information", "Pass", "Landed on Address Information PEO -003");
		Thread.sleep(2000);
		PEOPage.addressLine1.sendKeys("addressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		PEOPage.addressLine2.sendKeys("addressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		PEOPage.addressCity.sendKeys("NewYork");
		PEOPage.addressZip.sendKeys("10003");
		commonFuntions.enterTextboxContains("Phone Number",
				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
		commonFuntions.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			sleep(2000);
			PEOPage.uspsAddress.click();
			PEOPage.currentAdditionalAddress.click();
			commonFuntions.screenShot("UspsAddress", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
		} catch (Exception e) {
		}
		// -----PEO-005
		Thread.sleep(2000);
		commonFuntions.screenShot("CurrentAdditionalAddress", "Pass",
				"Verify Current Additional Address(es) in New York PEO 005");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ------PEO-004
	
		commonFuntions.screenShot("Mailing Address", "Pass", "Mailing Address PEO-004");
		
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ----PEO-006
		commonFuntions.screenShot("Prior Address(es) in New York", "Pass", "Prior Address(es) in New York PEO-006");
		//commonFuntions.enterTextboxContains("Address Line 1",
				//"PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		//commonFuntions.enterTextboxContains("Address Line 2",
				//"PrioraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 1","36 East 8th Street");
		commonFuntions.enterTextboxContains("Address Line 2","36 East 8th Street");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.enterTextboxContains("Zip Code", "10003");
		commonFuntions.enterTextboxContains("other name(s)", "automation");
		commonFuntions.enterTextboxContains("Predecessor(s) Name", "AutoPredecessor");
		commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
		commonFuntions.screenShot("Prior Address", "Pass", "Prior Address(es) in New York PEO -006 ");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----PEO-007
		commonFuntions.screenShot("verifyPriorAddress", "Pass", "Verify Prior Address(es) in New York PEO-007 ");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----PEO-012
		commonFuntions.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company PEO 012");
		commonFuntions.enterTextboxContains("Entity or Person", "Automation_entity");
		commonFuntions.enterTextboxContains("Ownership Percentage", "60");
		commonFuntions.enterTextboxContains("Address Line 1",
				"owneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("Address Line 2",
				"owneraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
		commonFuntions.enterTextboxContains("City", "NewYork");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.screenShot("OwnershipInformation", "Pass",
				"Ownership Information - privately or closely held company PEO 012");
		commonFuntions.clickButtonContains("Save & Continue ");

		try {
			commonFuntions.waitForLoadingIconToDisappear();
			PEOPage.uspsAddress.click();
			commonFuntions.screenShot("UspsAddress1", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
			sleep(2000);
		} catch (Exception e) {
		}
		/*
		 * commonFuntions.screenShot("verifyOnwershipInfo",
		 * "Pass","Verify Ownership Information");
		 * commonFuntions.clickButtonContains("Continue "); Thread.sleep(2000);
		 * commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
		 * commonFuntions.enterTextboxContains("Ownership Percentage","60");
		 * commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+
		 * commonFuntions.createRandomInteger(1000,9999));
		 * commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+
		 * commonFuntions.createRandomInteger(1000,9999));
		 * commonFuntions.enterTextboxContains("City","NewYork");
		 * commonFuntions.enterTextboxContains("Zip Code","13430");
		 * commonFuntions.screenShot("PriorOwnershipInformation",
		 * "Pass","PriorOwnership Information - privately or closely held company");
		 * commonFuntions.clickButtonContains("Save & Continue "); Thread.sleep(2000);
		 * PEOPage.uspsAddress.click();
		 * commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
		 * PEOPage.UspsContinueButton.click(); Thread.sleep(2000);
		 */
		// -----PEO-013
		commonFuntions.screenShot("verifyOnwershipInfo", "Pass", "Verify Ownership Information PEO-013");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----PEO-014
		
		commonFuntions.screenShot("submissonInstructions", "Pass",
				"Submission Instructions and Responsibilities PEO-014");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----SREG-006
		
		commonFuntions.screenShot("upload document", "Pass", "upload document SREG-006");
		commonFuntions.selectCheckbox("Proof of NYS Workers");
		commonFuntions.selectLink("Proof of NYS Workers", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
		commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.selectCheckbox("Affirmation from an independent CPA that the PEO is up to date on remittance of federal and state payroll tax withholdings through the last completed financial quarter");
		commonFuntions.selectLink("Affirmation from an independent CPA that the PEO is up to date on remittance of federal and state payroll tax withholdings through the last completed financial quarter", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.selectCheckbox("A blank client contract incorporating the requirements of Article 31 of the New York Labor Law.");
		commonFuntions.selectLink("A blank client contract incorporating the requirements of Article 31 of the New York Labor Law.", "Browse");
		Thread.sleep(2000);
		
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.selectCheckbox("Sample of a written notice to worksite employees notifying them of the nature of the relationship between the employer and the PEO");
		commonFuntions.selectLink("Sample of a written notice to worksite employees notifying them of the nature of the relationship between the employer and the PEO", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		commonFuntions.waitForLoadingIconToDisappear();
		
		commonFuntions.selectCheckbox("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)");
		commonFuntions.selectLink("Authorization to do business in NYS from the NYS Secretary of State (Applicable only for initial registrations)", "Browse");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		commonFuntions.clickButtonContains("Upload");
		commonFuntions.waitForLoadingIconToDisappear();
		
		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("upload document", "Pass", "uploaded document on SREG-006");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ----PEO-015
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "Pass", "Upload Client List on PEO-015");
		commonFuntions.clickButtonContains("Choose File");
		Thread.sleep(2000);
		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(2000);
		commonFuntions.screenShot("Upload Client List", "Pass", "Upload Client List on PEO-015");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----LEAS-012
		commonFuntions.screenShot("verifyClient", "Pass", "Verify Client List on LEAS-012");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// --------PEOR-001
		commonFuntions.screenShot("peoDetails", "Pass", "Peo Details Review on PEOR-001");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -------PEO-016
		commonFuntions.screenShot("Declaration", "Pass", "Declaration on PEO-016");
		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member",
				"TestAutomation" + commonFuntions.createRandomInteger(10000, 99999));
		commonFuntions.screenShot("Declaration", "Pass", "Declaration");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -------PEO-017
		commonFuntions.screenShot("Acknowledgement", "Pass", "Statement Of Acknowledgement on PEO-017");
		commonFuntions.clickButtonContains("Accept & Submit");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----SUC-002
		commonFuntions.screenShot("Completion", "Pass", "Register/Renew Confirmation on SUC-002");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ---Workitem
		// commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
	
		sleep(6000);
		// ---LoginLogout
		//commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.waitForLoadingIconToDisappear();

		PEOPage.queue.click();

		
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		 //commonFuntions.enterTextboxContains("FEIN","684800878");
		//commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep(2000);
		commonFuntions.screenShot("ReviewPeo", "Pass", "Review Peo");
		commonFuntions.clickOnLink("Review PEO");
		commonFuntions.waitForLoadingIconToDisappear();

		commonFuntions.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		// ---CPEO-001
		commonFuntions.screenShot("Review", "Pass", "Review Peo Registration on CPEO-001");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ---PEO-002
		// commonFuntions.enterTextboxContains("Provide the type of Legal Entity",
		// "TestAutomation");
		commonFuntions.screenShot("GeneralInfo", "Pass", "General Information on PEO-002");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			PEOPage.peoRadioButton.click();
			commonFuntions.selectRadioInTable(feinValue, 1, 1, "Unemployment Insurance Account Details");
			// commonFuntions.selectRadioInTable("684800878",1, 1,"Unemployment Insurance
			// Account Details");

		} catch (Exception e) {
		}
		// -----EAS-001
		commonFuntions.screenShot("Insurance", "Pass", "UnemploymentInsuranceAccountDetails on EAS-001");

		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -------PEO-003
		commonFuntions.screenShot("AddressInfo", "Pass", "Address Information on PEO-003");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		try {
			PEOPage.uspsAddress.click();
			PEOPage.currentAdditionalAddress.click();
			commonFuntions.screenShot("UspsAddress2", "Pass", "UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(2000);
		} catch (Exception e) {}
		// -------PEO-005
		commonFuntions.screenShot("VerifyCurrentAdd", "Pass", "Verify Current Additional Address on PEO-005");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		try{
		// ------PEO-004
		commonFuntions.screenShot("MailingAddress", "Pass", "Mailing Address on PEO-004");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		}catch(Exception e) {}
		Thread.sleep(2000);
		//----PEO 006
		//commonFuntions.clickButtonContains("Save & Continue ");}
		Thread.sleep(2000);
		// -------PEO-007
		commonFuntions.screenShot("VerifyPriorAdd", "Pass", "Verify Prior Address on PEO-007");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		/*
		 * commonFuntions.enterTextboxContains("Entity or Person","Automation_entity");
		 * commonFuntions.enterTextboxContains("Ownership Percentage","40");
		 * commonFuntions.enterTextboxContains("Address Line 1","owneraddressLine1"+
		 * commonFuntions.createRandomInteger(1000,9999));
		 * commonFuntions.enterTextboxContains("Address Line 2","owneraddressLine2"+
		 * commonFuntions.createRandomInteger(1000,9999));
		 * commonFuntions.enterTextboxContains("City","NewYork");
		 * commonFuntions.enterTextboxContains("Zip Code","13430");
		 * commonFuntions.screenShot("OwnershipInformation2",
		 * "Pass","Ownership Information - privately or closely held company");
		 * 
		 * commonFuntions.clickButtonContains("Save & Continue ");
		 */
		Thread.sleep(2000);
		// -----PEO-013
		commonFuntions.screenShot("VerifyOwnerInfo", "Pass", "Verify Owner Information on PEO-013");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		/*
		 * commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+
		 * commonFuntions.createRandomInteger(1000,9999));
		 * commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+
		 * commonFuntions.createRandomInteger(1000,9999));
		 * commonFuntions.screenShot("PriorOwner","Pass","Prior Owner Information");
		 * commonFuntions.clickButtonContains("Save & Continue "); Thread.sleep(2000);
		 * PEOPage.uspsAddress.click();
		 * commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
		 * PEOPage.UspsContinueButton.click(); Thread.sleep(2000);
		 */
		// ------PEO-014
		commonFuntions.screenShot("VerifyPrioerOwner", "Pass", "Verify Prior Ownership Information on PEO-014");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Submission", "Pass", "Submission");
		// commonFuntions.clickButtonContains("Continue ");
		Thread.sleep(2000);
		// ---SREG-006
		commonFuntions.screenShot("UploadDocs", "Pass", "Upload Documents on SREG-006");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ----LEAS-012
		commonFuntions.screenShot("VerifyClient", "Pass", "Verify Client List on LEAS-012");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ---PEOR-001
		commonFuntions.screenShot("PeoDetails", "Pass", "Peo Details Review on PEOR-001");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ---PEO-016
		// commonFuntions.enterTextboxContains("Enter name of Officer, Partner,
		// Proprietor or
		// Member","TestAutomation"+commonFuntions.createRandomInteger(10000,99999));
		commonFuntions.screenShot("Declaration2", "Pass", "Declaration on PEO-016");
		commonFuntions.clickButtonContains("Save & Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----PEO-017
		commonFuntions.screenShot("StatementAckn", "Pass", "Statment of Acknowledgment on PEO-017");
		commonFuntions.clickButtonContains("Continue ");
		commonFuntions.waitForLoadingIconToDisappear();
		// ------RPEO-003
		// commonFuntions.selectRadio("Approved");
		PEOPage.ApprovePeo.click();
		commonFuntions.screenShot("Approval", "Pass", "ApprovalPage on RPEO-003");
		commonFuntions.clickButtonContains("Submit");
		commonFuntions.waitForLoadingIconToDisappear();
		// -----SUC-002
		commonFuntions.screenShot("Confirmation", "Pass", " Confirmation on SUC-002");
		commonFuntions.clickButtonContains("Home ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("Success", "Pass", "SuccessPage");
		commonFuntions.screenShot("TC:EL_322_014", "Pass", "Testcase Execution completed  ");

//		     
////		     
////		     
////		     
////		    /* 
////		     
////		Map<String, String> databaseResults = PEOPage.database_SelectQuery(
////				"SELECT FEIN,EAN FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL AND FEIN IS NOT NULL ORDER BY UPDATED_TS desc");
//////		String feinValue = databaseResults.get("Fein");
//////		String ernValue = databaseResults.get("Ean");
////		System.out.println("feinValue is" + feinValue);
////		test = report.createTest(
////				"EL.322.014: Verify CSR can register Individual PEO for Type of Legal Entity 'Corporation' and type of Ownership 'Public Ownership'");
////		
////
////		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
////		sleep(2000);
////		commonFuntions.waitForLoadingIconToDisappear();
////		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
////		commonFuntions.clickMenu("Menu");
////		commonFuntions.ScrollMenu("Professional Employer Organization (PEO)");
////		PEOPage.menuPeo.click();
////		commonFuntions.screenShot("Menu", "Pass", "Register PEO");
////		commonFuntions.clickMenu("Register PEO");
////		commonFuntions.screenShot("PeoRegistration", "Pass", "PEO Registration - Contact Details");
////		commonFuntions.clickButtonContains("Continue ");
////		Thread.sleep(2000);
////		PEOPage.individualPeo.click();
////		commonFuntions.enterTextbox("Name of Professional Employer Organization",
////				"Test_auto" + commonFuntions.createRandomInteger(1000, 9999));
////		sleep(2000);
////		commonFuntions.enterTextbox("Additional name(s), if any, under which the PEO currently conducts business",
////				"auto_test" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(2000);
////		commonFuntions.screenShot("IndividualPeo", "Pass", "Professional Employer Organization Registration");
////		commonFuntions.selectRadioQuestions("Do you currently have a New York State Unemployment Insurance Account?",
////				"Yes");
////
////		// String ernValue=StringUtils.left( String.valueOf((long)
////		// (Math.random()*Math.pow(10,10))),7);
////		// String feinValue=StringUtils.left( String.valueOf((long)
////		// (Math.random()*Math.pow(10,10))),9);
//////		String ernValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
////		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
////		commonFuntions.selectDropdown("Type of Legal Entity", "Corporation");
////		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
////		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
////		commonFuntions.selectRadioQuestions("Type of Ownership", "Public");
////		commonFuntions.enterTextboxContains("Fiscal Year Start Dat", "02/01/2023");
////		commonFuntions.screenShot("IndividualPeo", "Pass", "Professional Employer Organization Registration");
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(2000);
////		commonFuntions.screenShot("UnemploymentInsurance", "Pass", "Unemployment Insurance Account Details");
////		try {
////			Thread.sleep(2000);
////			commonFuntions.selectRadioInTable(feinValue, 1, 1, "Unemployment Insurance Account Details");
////		} catch (Exception e) {
////		}
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(4000);
////		PEOPage.addressLine1.sendKeys("addressLine1" + commonFuntions.createRandomInteger(1000, 9999));
////		PEOPage.addressLine2.sendKeys("addressLine2" + commonFuntions.createRandomInteger(1000, 9999));
////		PEOPage.addressCity.sendKeys("NewYork");
////		PEOPage.addressZip.sendKeys("13420");
////
////		commonFuntions.enterTextboxContains("Phone Number",
////				Long.toString(commonFuntions.createRandomInteger(10000000, 99999999))
////						+ Long.toString(commonFuntions.createRandomInteger(10, 99)));
////		commonFuntions.enterTextboxContains("Business Email Address",
////				"autoTest" + Long.toString(commonFuntions.createRandomInteger(10000, 99999)) + "@gmail.com");
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(2000);
////		PEOPage.uspsAddress.click();
////		PEOPage.currentAdditionalAddress.click();
////		commonFuntions.screenShot("UspsAddress", "Pass", "UspsAddress");
////		PEOPage.UspsContinueButton.click();
////		/*----------------PEO-005---------------*/
////		     /*
////		Thread.sleep(2000);
////		commonFuntions.screenShot("CurrentAdditionalAddress", "Pass",
////				"Verify Current Additional Address(es) in New York");
////		commonFuntions.clickButtonContains("Continue ");
////		Thread.sleep(4000);
////		/*----------------PEO-004---------------*/
////		//commonFuntions.screenShot("Mailing Address", "Pass", "Mailing Address");
////		//commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(4000);
////		/*----------------PEO-006---------------*/
////		/*
////		commonFuntions.enterTextboxContains("Address Line 1",
////				"PrioraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.enterTextboxContains("Address Line 2",
////				"PrioraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.enterTextboxContains("City", "NewYork");
////		commonFuntions.enterTextboxContains("Zip Code", "13429");
////		commonFuntions.enterTextboxContains("other name(s)", "automation");
////		commonFuntions.enterTextboxContains("Predecessor(s) Name", "AutoPredecessor");
////		commonFuntions.enterTextboxContains("Successor(s) Name", "AutoSuccessor");
////		commonFuntions.screenShot("Prior Address", "Pass", "Prior Address(es) in New York");
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(4000);
////		commonFuntions.screenShot("verifyPriorAddress", "Pass", "Verify Prior Address(es) in New York");
////		commonFuntions.clickButtonContains("Continue ");
////		Thread.sleep(4000);
////		commonFuntions.enterTextboxContains("Entity or Person", "Automation_entity");
////		commonFuntions.enterTextboxContains("Ownership Percentage", "40");
////		commonFuntions.enterTextboxContains("Address Line 1",
////				"owneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.enterTextboxContains("Address Line 2",
////				"owneraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.enterTextboxContains("City", "NewYork");
////		commonFuntions.enterTextboxContains("Zip Code", "13430");
////		commonFuntions.screenShot("OwnershipInformation", "Pass",
////				"Ownership Information - privately or closely held company");
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(4000);
////		try {
////			PEOPage.uspsAddress.click();
////			sleep(2000);
////			commonFuntions.safeJavaScriptClick(PEOPage.UspsContinueButton);
////		} catch (Exception e) {
////			System.out.println("Pop up not displayed");
////		}
////		sleep(4000);
////		commonFuntions.screenShot("verifyOnwershipInfo", "Pass", "Verify Ownership Information");
////		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		*/
////	     commonFuntions.enterTextboxContains("Entity or Person","Automation_Pentity");
////	     commonFuntions.enterTextboxContains("Ownership Percentage","60");
////	     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
////	     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
////	     commonFuntions.enterTextboxContains("City","NewYork");
////	     commonFuntions.enterTextboxContains("Zip Code","13430");
////	     commonFuntions.screenShot("PriorOwnershipInformation","Pass","PriorOwnership Information - privately or closely held company");
////	     commonFuntions.clickButtonContains("Save & Continue ");
////	     Thread.sleep(4000);
////	     PEOPage.uspsAddress.click();
////	     commonFuntions.screenShot("UspsAddress1","Pass","UspsAddress");
////	     PEOPage.UspsContinueButton.click();	    
////	     Thread.sleep(4000);
//		commonFuntions.screenShot("verifyOnwershipInfo", "Pass", "Verify Ownership Information");
//		commonFuntions.clickButtonContains("Continue ");
////	     Thread.sleep(4000);
////	     commonFuntions.screenShot("submissonInstructions","Pass","Submission Instructions and Responsibilities");
////	     commonFuntions.clickButtonContains("Continue ");
////	     Thread.sleep(4000);	     
////	     commonFuntions.selectCheckbox("Proof of NYS Workers");
////	     commonFuntions.selectLink("Proof of NYS Workers", "Browse");
////	     Thread.sleep(4000);
////	     commonFuntions.uploadDoc("Sample.docx");
////	     Thread.sleep(4000);
////	     commonFuntions.selectCheckbox("Proof of NYS Disability Insurance Coverage");
////	     commonFuntions.selectLink("Proof of NYS Disability Insurance Coverage", "Browse");
////	     Thread.sleep(4000);
////	     commonFuntions.uploadDoc("Sample.docx");
////	     Thread.sleep(4000);
////	     commonFuntions.selectCheckbox("Proof of $75k net worth or bond or a letter of credit for $75k");
////	     commonFuntions.selectLink("Proof of $75k net worth or bond or a letter of credit for $75k", "Browse");
//		Thread.sleep(4000);
//		commonFuntions.uploadDoc("Sample.docx");
//		Thread.sleep(4000);
//		commonFuntions.clickButtonContains("Upload");
//		Thread.sleep(4000);
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.clickButtonContains("Choose File");
//		Thread.sleep(4000);
//		commonFuntions.uploadDoc("PEO Client List template_TestData2.xls");
//		Thread.sleep(4000);
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("verifyClient", "Pass", "Verify Client List");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("peoDetails", "Pass", "Peo Details Review");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member",
//				"TestAutomation" + commonFuntions.createRandomInteger(10000, 99999));
//		commonFuntions.screenShot("Declaration", "Pass", "Declaration");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("Acknowledgement", "Pass", "Statement Of Acknowledgement");
//		commonFuntions.clickButtonContains("Accept & Submit");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("Completion", "Pass", "Register/Renew Confirmation");
//
//		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"
//				+ COMMON_CONSTANT.CSR_USER_1
//				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
//				+ feinValue + "' ORDER BY UPDATED_TS desc)");
//		Thread.sleep(4000);
//
//		PEOPage.queue.click();
//		Thread.sleep(15000);
//		commonFuntions.enterTextboxContains("FEIN", feinValue);
//		commonFuntions.screenShot("FeinSearch", "Pass", "feinSearch");
//		commonFuntions.clickButtonContains("Search");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("Review Peo", "Pass", "Review Peo");
//		commonFuntions.clickOnLink("Review PEO");
//		Thread.sleep(4000);
//		commonFuntions.clickButtonContains("Open Work Item");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("Review", "Pass", "Review Peo Registration");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		commonFuntions.enterTextboxContains("Provide the type of Legal Entity", "TestAutomation");
//		commonFuntions.screenShot("GeneralInfo", "Pass", "General Information");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		try {
//			commonFuntions.selectRadioInTable(feinValue, 1, 1, "Unemployment Insurance Account Details");
//		} catch (Exception e) {
//
//		}
//		commonFuntions.screenShot("Insurance", "Pass", "UnemploymentInsuranceAccountDetails");
//
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		/*-----------------PEO-003------------*/
//		commonFuntions.screenShot("AddressInfo", "Pass", "Address Information");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		PEOPage.uspsAddress.click();
//		PEOPage.currentAdditionalAddress.click();
//		commonFuntions.screenShot("UspsAddress2", "Pass", "UspsAddress");
//		PEOPage.UspsContinueButton.click();
//		Thread.sleep(4000);
//		/*-----------------PEO-005------------*/
//		commonFuntions.screenShot("VerifyCurrentAdd", "Pass", "Verify Current Additional Address");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		/*-----------------PEO-004------------*/
//		commonFuntions.screenShot("MailingAddress", "Pass", "Mailing Address");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		/*-----------------PEO-007------------*/
//		commonFuntions.screenShot("VerifyPriorAdd", "Pass", "Verify Prior Address");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		/*-----------------PEO-013------------*/
////		commonFuntions.enterTextboxContains("Entity 	or Person", "Automation_entity");
////		commonFuntions.enterTextboxContains("Ownership Percentage", "40");
////		commonFuntions.enterTextboxContains("Address Line 1",
////				"owneraddressLine1" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.enterTextboxContains("Address Line 2",
////				"owneraddressLine2" + commonFuntions.createRandomInteger(1000, 9999));
////		commonFuntions.enterTextboxContains("City", "NewYork");
////		commonFuntions.enterTextboxContains("Zip Code", "13430");
////		commonFuntions.screenShot("OwnershipInformation2", "Pass",
////				"Ownership Information - privately or closely held company");
////
////		commonFuntions.clickButtonContains("Save & Continue ");
////		Thread.sleep(4000);
////		commonFuntions.screenShot("VerifyOwnerInfo", "Pass", "Verify Owner Information");
////		commonFuntions.clickButtonContains("Continue ");
//////		     Thread.sleep(4000);
//////		     commonFuntions.enterTextboxContains("Address Line 1","PowneraddressLine1"+commonFuntions.createRandomInteger(1000,9999));
//////		     commonFuntions.enterTextboxContains("Address Line 2","PowneraddressLine2"+commonFuntions.createRandomInteger(1000,9999));
//////		     commonFuntions.screenShot("PriorOwner","Pass","Prior Owner Information");
//////		     commonFuntions.clickButtonContains("Save & Continue ");
//////		     Thread.sleep(4000);
//////		     PEOPage.uspsAddress.click();
//////		     commonFuntions.screenShot("UspsAddress3","Pass","UspsAddress");
//////		     PEOPage.UspsContinueButton.click();	
//		/*
//		Thread.sleep(4000);
//		commonFuntions.screenShot("VerifyPrioerOwner", "Pass", "Verify Prior Ownership Information");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("Submission", "Pass", "Submission");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("UploadDocs", "Pass", "Upload Documents");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("VerifyClient", "Pass", "Verify Client List");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("PeoDetails", "Pass", "Peo Details Review");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member",
//				"TestAutomation" + commonFuntions.createRandomInteger(10000, 99999));
//		commonFuntions.screenShot("Declaration2", "Pass", "Declaration");
//		commonFuntions.clickButtonContains("Save & Continue ");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("StatementAckn", "Pass", "Statment of Acknowledgment");
//		commonFuntions.clickButtonContains("Continue ");
//		Thread.sleep(4000);
//
//		commonFuntions.selectRadio("Approved");
//		commonFuntions.screenShot("Approval", "Pass", "ApprovalPage");
//		commonFuntions.clickButtonContains("Submit");
//		Thread.sleep(4000);
//		commonFuntions.screenShot("Success", "Pass", "SuccessPage");
//*/
//	}
	}
}