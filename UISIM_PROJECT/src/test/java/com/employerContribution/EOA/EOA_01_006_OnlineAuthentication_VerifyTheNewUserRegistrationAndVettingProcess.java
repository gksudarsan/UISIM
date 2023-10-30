package com.employerContribution.EOA;

import java.util.Map;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EOAPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EOA_01_006_OnlineAuthentication_VerifyTheNewUserRegistrationAndVettingProcess extends TestBase {
	
	@Test
	public void EOA_01_006_OnlineAuthentication() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EOAPage eoa = new EOAPage(driver);
		test = report.createTest(
				"EOA.01.006 - Online Authentication_Verify the New User registration and vetting process (Employer who is not registered for UI)");
		cf.login(COMMON_CONSTANT.APPEALS_USER1.toUpperCase(), COMMON_CONSTANT.APPEALS_USER_PASSWORD1);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();cf.waitForLoadingIconToDisappear();
		eoa.radioButton_EOA_Employer.click();sleep();
		cf.screenShot("UIservicesAccountCreation", "Pass", "UI services account creation");
		cf.clickButtonContains("Continue ");
		
		//To fetch FEN from database 
		Map<String, String> databaseResults_fein = cf.database_SelectQuerySingleColumn(
				"SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a \r\n" + 
				" JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID  \r\n" + 
				" JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID \r\n" + 
				" JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID \r\n" + 
				" JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID \r\n" + 
				" WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN","FEIN");
		String feinValue = databaseResults_fein.get("FEIN");
		System.out.println("Fein Number is :: " + feinValue);
		test.log(Status.INFO, "FEIN::" + feinValue);
		cf.enterTextboxContains(" Federal Employer Identification Number (FEIN) ", "298294170");
		
		//To fetch Entity Name from database
		Map<String, String> databaseResults_entityName = cf.database_SelectQuerySingleColumn(
				"SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a \r\n" + 
				" JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID  \r\n" + 
				" JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID \r\n" + 
				" JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID \r\n" + 
				" JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID \r\n" + 
				" WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN","ENTITY_NAME");
		String entityName = databaseResults_entityName.get("ENTITY_NAME");
		System.out.println("Entity Name is :: " + entityName);
		test.log(Status.INFO, "EntityName::" + entityName);
		cf.enterTextboxContains("Legal Name", "NEW CAFE BURTIS LLP");
		
		//To fetch NYBE application number from database
		Map<String, String> databaseResults_NYBEApllicationNumber = cf.database_SelectQuerySingleColumn(
				"SELECT DISTINCT a.EAN, a.ENTITY_NAME,a.FEIN  ,n.NYBE_AGENCY_SPEC_APP_ID FROM T_EMPLOYER_ACCOUNT a \r\n" + 
				" JOIN T_EMPLOYER_NYBE n ON n.EMP_ACCT_ID = a.EMPLOYER_ACCOUNT_ID  \r\n" + 
				" JOIN T_REGULAR_EMPLOYER b ON a.EMPLOYER_ACCOUNT_ID = b.EMPLOYER_ACCOUNT_ID \r\n" + 
				" JOIN T_EMPLOYER c ON b.EMPLOYER_ID = c.EMPLOYER_ID \r\n" + 
				" JOIN T_EMPLOYER_CONTACT d ON c.EMPLOYER_ID = d.EMPLOYER_ID \r\n" + 
				" WHERE a.ACCOUNT_STATUS = 'LIAB' AND a.REGISTRATION_STATUS = 'C' AND a.EAN IS NOT NULL AND a.EAN <> '' AND a.EAN = c.EAN","NYBE_AGENCY_SPEC_APP_ID");
		String NybeAppNumber = databaseResults_NYBEApllicationNumber.get("NYBE_AGENCY_SPEC_APP_ID");
		System.out.println("NYBE Application Number is :: " + NybeAppNumber);
		test.log(Status.INFO, "NYBEAPPLICATIONNUMBER::" + NybeAppNumber);
		cf.enterTextboxContains("New York Business Express (NYBE) UI Application Number", "DOL100-2023-000358");
		cf.screenShot("UIServicesEmployerAccountCreation", "Pass", "UI Services Employer Account Creation");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//SIDES E-Response Auto-enrollment
		eoa.selectCheckBox_AutoEnrollment.click();
		cf.screenShot("SIDESE-ResponseAuto-enrollment", "Pass", "SIDES E-Response Auto-enrollment");
		cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//SIDES E-Response Auto-enrollment Confirmation
		cf.screenShot("SIDESE-ResponseAuto-enrollmentConfirmation", "Pass", "SIDES E-Response Auto-enrollment Confirmation");
		cf.clickButtonContains("Continue ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//Contact Information
		cf.enterTextboxContains("First Name", "test");
		cf.enterTextboxContains("Last Name", "com");
		cf.enterTextboxContains("E-mail Address","autoTest"+Long.toString(cf.createRandomInteger(10000,99999))+"@gmail.com");
		cf.enterTextboxContains(" Contact Number ",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
	    cf.selectCheckbox("Same as Contact Number");
	    cf.screenShot("ContactInformation", "Pass", "Contact Information");
	    cf.clickButtonContains("Submit ");
		sleep();cf.waitForLoadingIconToDisappear();
		
		//UI Services Account Creation Confirmation
		 cf.screenShot("UIServicesAccountCreationConfirmation", "Pass", "UI Services Account Creation Confirmation");
		
	}
}
