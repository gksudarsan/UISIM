package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_001 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_10_001() throws Exception {

		test = report.createTest("Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		// GET query
		// FEIN in DOL & not in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("The FIEN is " + feinValue);
		// EAN in DOL & DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("HomePage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		sleep(2000);
		
		// --- SREG-001 ---
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Middle Initial", "S");
		commonFunction.enterTextboxContains("Last Name", "Rodriguez");
		commonFunction.selectDropdown("Suffix", " Sr. ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister2", "Pass", "Details entered on SREG-001 page");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address", "randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
		sleep(2000);
		commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); //897397325
		commonFunction.selectDropdown("Type of Legal Entity", " City ");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //4543352
		sleep(2000);
		commonFunction.screenShot("EmpRegister4", "Pass", "Details entered and click on CONTINUE button");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-003 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("ColorEseence122");
		//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		//commonFunction.enterTextboxContains("Other commonly known", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		sleep(2000);
		commonFunction.screenShot("EmpRegister6", "Pass", "Details entered in SREG-003 page");
		commonFunction.enterTextboxContains("date of the first payroll", "10/12/2021");
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "745");
		commonFunction.enterTextboxContains("Date covered employment", "05/07/2022");
		sleep(2000);
		
		commonFunction.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"Yes ");
		sleep();
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Acme Corp");
		commonFunction.enterTextboxContains("Address Line 1 ", "29 W 35th");
		commonFunction.enterTextboxContains("Address Line 2 ", "St 9th floor");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10001");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("EmpRegister8", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.clickButton("Continue ");
		
		//step 8 failed at MC
		sleep();
		commonFunction.screenShot("EmpRegister7", "Fail", "Failed in MC");
		;
	}

}
