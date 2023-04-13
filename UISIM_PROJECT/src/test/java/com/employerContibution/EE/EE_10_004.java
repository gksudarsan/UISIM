package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_004 extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'School District' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_004() throws Exception {

		test = report.createTest(
				"Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");
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
		
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd) AND ENTITY_NAME IS NOT NULL ORDER BY UPDATED_TS DESC","ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");

		// ---Menu Click---
		commonFunction.clickMenu("Menu");
		// commonFuntions.clickMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");

		// --- SREG-001 ---
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Middle Initial", "S");
		commonFunction.enterTextboxContains("Last Name", "Rodriguez");
		commonFunction.selectDropdown("Suffix", " Sr. ");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address", "randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		commonFunction.screenShot("EmpRegister2", "Pass", "Details entered on SREG-001 page");
		commonFunction.clickButton("Continue ");		
		
		// --- SREG-025 ---
		commonFunction.screenShot("MenuPage", "Pass", "Details entered on SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); //897397325
		commonFunction.selectDropdown("Type of Legal Entity", " School District ");
		commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //4543352
		sleep(2000);
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();
		
		// --- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys(legalName); //ColorEseence122
		//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		//commonFunction.enterTextboxContains("Other commonly known", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains(" Business Fax Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("date of the first payroll", "12/16/2021");
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "453");
		commonFunction.enterTextboxContains("Date covered employment", "04/07/2022");
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-008 ---
		commonFunction.screenShot("EmpRegister6", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.screenShot("EmpRegister7", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.clickButton("Continue ");
		
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EmpRegister8", "Pass", "USPS Business address selection");
		empRegPage.continueButton_popUp.click();

		// --- SREG-007 ---
		commonFunction.screenShot("EmpRegister9", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		sleep(2000);
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---

		
		commonFunction.screenShot("EmpRegister10", "Fail", "Can't enter details in SREG-004 page");
		
	
		
	}
}
