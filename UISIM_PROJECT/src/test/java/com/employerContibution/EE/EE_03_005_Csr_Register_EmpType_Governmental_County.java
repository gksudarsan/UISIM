package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_03_005_Csr_Register_EmpType_Governmental_County extends TestBase {

	@Test
	public void EE_03_005() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		SREG_084 sreg084 = new SREG_084(driver);
		
		test = report.createTest(
				"EE.03.003 -Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'Village' and work items will be created for CSR to review.\r\n"
						+ "");
		/*
		 * Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN  IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
		 * , "FEIN"); String FEIN = databaseResults.get("FEIN");
		 * System.out.println("FEIN NUMBER = " +FEIN);
		 * 
		 * Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ACCOUNT_STATUS ='SUSPN' AND EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
		 * , "EAN"); String EAN = databaseResults1.get("EAN");
		 * System.out.println("EAN NUMBER = " +EAN);
		 */
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("menu");
		// homePage.menu.click();
		cf.ScrollMenu("Employer Registration");
		cf.clickMenu("Employer Registration");
		sleep(2000);
		cf.screenShot("Employer Registration", "Pass", "Register Employer");
		cf.clickMenu("Register Employer");
		sleep(3000);
		cf.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		cf.clickButtonContains("Continue ");
		sleep(3000);
		cf.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 Page");
		cf.selectDropdown("Employer Type", " Governmental ");
		sleep();

		String feinValue = "010042354"; // Exist in DOl & DTF
		String EAN = "2280502";

		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		cf.selectDropdown("Type of Legal Entity", " County ");
		cf.enterTextboxContains("Employer Registration Number", EAN);
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		cf.selectDropdown("Source Type", " NYS-100G ");
		cf.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		sleep(3000);
		cf.clickButtonContains("Continue ");
		cf.screenShot("EmpRegister3", "Pass", "SREG-003 Screen is displayed");
		sleep(3000);
		/*----------------SREG-003----------------*/
		empPage.legalNameTextBox.sendKeys("AKRON KNITTING MILLS INC");
		// cf.enterTextboxContains("Other commonly known", "TESTING");
		cf.enterTextboxContains(" Business Phone Number  ", Long.toString(cf.createRandomInteger(10000000, 99999999))
				+ Long.toString(cf.createRandomInteger(10, 99)));

		cf.enterTextboxContains("Estimated or approximate number of individuals working in covered employment", "56");

		cf.enterTextbox("Date covered employment began?", "12/1/2022");
		cf.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"No ");

		cf.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Contributory");

		cf.clickButtonContains("Continue ");

		sleep();
		// Step-10
		cf.screenShot("EmpRegister5", "Pass", "SREG-008 Page is displayed");
		cf.enterTextboxContains("Address Line 1 ", "123state");
		cf.enterTextboxContains("City ", "albany");
		// commonFuntions.selectDropdown("Country", " United States ");
		// commonFuntions.enterTextboxContains("State", " New York ");
		cf.enterTextboxContains("Zip Code", "12012");
		cf.selectDropdown("County", " Albany ");
		cf.clickButtonContains("Continue ");

		// Step-11
		try {
			sreg008.firstradiobuttonVerifyAddPopup.click();
			sleep(2000);
			empPage.continueButton_popUp.click();
			sleep(2000);
			cf.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}

		// Step-12
		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-007 and click continue");
		sleep(4000);
		cf.clickButtonContains("Continue ");

		// Step-13
		sleep(4000);
		cf.screenShot("EmpRegister8", "Pass", "Navigated on SREG-004 and enter Both the address ");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		cf.enterTextboxContains("Attention", "Test"); // GRAYSON BEAIRSTO
		sreg004.addresslinelist.get(0).sendKeys("123state"); // 7
		sreg004.citylist.get(0).sendKeys("albany");
		sreg004.zipCodelist.get(0).sendKeys("12012");
		
		cf.selectRadioQuestions("Location of Books and Records", "Other");   
		sreg004.careOfFieldlist.get(0).sendKeys("knowledge is power vfbgb");
		sreg004.addresslinelist.get(1).sendKeys("123state");
		sreg004.citylist.get(1).sendKeys("albany");
		sreg004.zipCodelist.get(1).sendKeys("12012");
		sreg004.listOfFirstname.get(0).sendKeys("FN");
		sreg004.listOfLastName.get(0).sendKeys("LN");

		cf.clickButtonContains("Continue ");

		// Step-14
		try {
			cf.selectRadioQuestions("Agad Address", "123");
			cf.selectRadioQuestions("bmad Address", "123");
			cf.selectRadioQuestions("lbra Address", "123");
			cf.selectRadioQuestions("npca Address", "123");
			Thread.sleep(2000);
			sreg004.popUpContinueButton.click();
		} catch (Exception e) {
			System.out.println("pop up not appeared");
		}
/////////////////////////////////////////////////

		sleep(4000);
		cf.screenShot("EmpRegister11", "Pass", "Navigated on SREG-521 page");
		cf.clickButtonContains("Continue ");

		sleep(4000);
		// Step-15
		cf.screenShot("EmpRegister12", "Pass", "Navigated on SREG-683 page and upload document");
		cf.clickButtonContains("Continue ");

		sleep(10000);
		// Step-16
		cf.screenShot("EmpRegister13", "Pass", "Navigated on SREG-800 and Accept");
		cf.clickButton("Continue ");

		// Step-17
		cf.screenShot("EmpRegister14", "Pass", "Navigated on SREG-043 and Accept");
		cf.selectCheckbox("I accept");
		sleep();
		cf.clickButtonContains("Submit ");
		sleep(15000);

		// Step-17
		cf.screenShot("EmpRegister15", "Pass", "Navigated to SREG-521 page and click on exit");
		cf.clickButtonContains("Home ");
		Thread.sleep(2000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");
		cf.screenShot("Homepage", "Pass", "Homapage page displayed");

		///////////////////////////////////
		cf.screenShot("EmpRegister16", "Pass", "Navigated to Home Page and click on My-Q");
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		cf.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		// Step-20
		cf.enterTextboxContains("FEIN", feinValue);
		sleep();
		cf.clickButtonContains(" Search ");
		sleep(4000);
		// cf.clickOnLink("Review Employer Type");
		//sreg084.reviewemployertypelink.click();  
		sleep(2000);
		cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		sleep(5000);
		cf.clickButtonContains("Open Work Item ");

		sleep(3000);
		cf.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
		cf.enterTextboxContains("Date Covered Employment began? ", "12/1/2022");
		sreg043.EEWI002CommentsField.sendKeys("testing work item");
		sleep(3000);
		cf.clickButtonContains("Submit ");

		sleep(3000);
		cf.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		cf.screenShot("Homepage", "Pass", "Homepage screen is visible");
		/*
		 * commonFuntions.
		 * database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" +
		 * COMMON_CONSTANT.CSR_USER_1 +
		 * "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
		 * + feinValue + "' ORDER BY UPDATED_TS desc)");
		 */
		
		test.info("CSR Navigate to Main Menu -> MyQ");
		Thread.sleep(5000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		cf.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");

		// Step 39 DOL DTF Task
		// commonFuntions.enterTextboxContains("FEIN", feinValue);
		// commonFuntions.clickButtonContains(" Search ");
		// Thread.sleep(2000);

		//sreg084.dolDtfTasklink.click();  
		// Step-26
		cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		cf.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);
		cf.screenShot("DOL-DTF Task Details", "Pass", "EEWI-005 screen is visible");

		// Step-41 EEWI-005 Screen

		// empPage.What_firstCalender_Quater.click();
		// empPage.firstCalender_Quater_Value_2.click();
		// empPage.whatFirstCalender_YearField.sendKeys("2023");
		cf.enterTextboxContains("Date Covered Employment began? ", "12/1/2022");
		Thread.sleep(1000);
		cf.selectDropdown("Account Status", " Liable ");
		sleep(2000);
		sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm");
		cf.clickButtonContains("Submit ");
		Thread.sleep(2000);
		cf.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		//
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		cf.screenShot("Homepage", "Pass", "Homepage screen is visible");

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"
				+ feinValue + "' ORDER BY UPDATED_TS desc)");

		Thread.sleep(2000);

		//Verify Agent Rep-Task
		Thread.sleep(2000);
		PEOPage.queue.click();
		Thread.sleep(3000);
		cf.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
		//
		cf.enterTextboxContains("FEIN", feinValue);
		cf.clickButtonContains(" Search ");
		Thread.sleep(2000);
		//
		//sreg084.verifyAgentRepTasklink.click();  
		//

		cf.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
		Thread.sleep(2000);

		cf.clickButtonContains("Open Work Item ");
		Thread.sleep(2000);

		// Step-32
		cf.clickButtonContains("Submit ");
		Thread.sleep(2000);
		cf.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");

		// Step-33
		Assert.assertTrue(suc002.screenIdText.isDisplayed());
		suc002.homeButton.click();
		Thread.sleep(5000);
		cf.screenShot("Homepage", "Pass", "Homepage screen is visible");

		// step-34
		cf.clickMenu("menu");
		cf.ScrollMenu("Inquiry");
		cf.clickMenu("Inquiry");
		sleep(2000);
		cf.ScrollMenu("Contribution Inquiry");
		cf.clickMenu("Contribution Inquiry");
		sleep(2000);
		cf.ScrollMenu("Inquiry Employer Account");
		cf.clickMenu("Inquiry Employer Account");
		sleep(2000);
		cf.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "SREG-050 screen is displayed");
		sleep(2000);

		// step-35
		cf.enterTextboxContains(" FEIN ", feinValue);
		sleep(2000);
		cf.clickButtonContains("Continue ");

		sleep(2000);
		cf.screenShot("Inquiry Employer Account Information", "Pass", "SREG-051 screen is displayed");

		// step-36
		cf.clickButtonContains("Previous ");
		cf.screenShot("Inquiry Employer Account Information", "Pass", "SREG-050 screen is displayed");

		// step-38
		cf.clickButtonContains("Home");
		cf.screenShot("Inquiry Employer Account Information", "Pass", "Home screen is displayed");
	}
}
