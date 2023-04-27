// --------------------------Palak--------------------------

package com.employerContibution.EE;

import java.util.Map;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_03_006 extends TestBase {
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void EE_03_006() throws Exception {
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest(
				"EE.03.006 Verify CSR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");

		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLogonPage", "Pass", "Application login successfully");
		commonFunction.clickMenu("Menu");
		commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFunction.clickMenu("Register Employer");
		sleep(2000);
		// --------SREG 001------
		commonFunction.screenShot("EmployerRegister1", "pass", "Landed on the Employer Register page");
		commonFunction.clickButton("Continue ");
		sleep(3000);
		commonFunction.screenShot("EmployerRegister2", "pass", "Navigate to SREG 025 page");
		// --------SREG 025------
		commonFunction.selectDropdown("Employer Type", "Governmental");
		sleep(2000);
		Map<String, String> databaseResults = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC",
				"FEIN");
		String FEIN = databaseResults.get("FEIN");
		System.out.println("FEIN NUMBER = " + FEIN);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		commonFunction.selectDropdown("Type of Legal Entity", " Other ");
		commonFunction.enterTextboxContains("Employer Registration Number", "73812378");
		commonFunction.enterTextboxContains("If Other, provide the type of Legal Entity.", "union");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100G ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");

		sleep(2000);
		// ----------------SREG-003----------------
		commonFunction.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		// commonFunction.enterTextboxContains("Legal Name","United state");
		commonFunction.populateListbox("Legal Name", "United state");
		// commonFunction.legalNameTextBox.sendKeys("TCS CORPORATION");
		commonFunction.enterTextboxContains("Other commonly known", "TESTING");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "3564777565");
		commonFunction.enterTextboxContains(" Business Fax Number ", "9484735838");
		commonFunction.enterTextboxContains("What is the date of", "01/04/2023");
		// commonFunction.enterTextboxContains("Estimated or approximate number of
		// individuals working in covered employment", " 67 ");
		commonFunction.enterTextboxContains("Date covered employment began?", "10/05/2022");
		commonFunction.selectRadioQuestions(
				"Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?",
				"No ");
		commonFunction.selectRadioQuestions(
				"Choose the option you wish to use to discharge your Unemployment Insurance liability.",
				"Contributory");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// ----------------SREG-008 ----------------*/
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on SREG-008 page and click continue");

		commonFunction.enterTextboxContains("Address Line 1 ", "123 cart Paris");
		// commonFunction.enterTextbox("Address Line 2 ", "near post office");
		commonFunction.enterTextboxContains("City ", "albany");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("Zip Code", "45678");
		// commonFunction.selectDropdown("Country", " United States ");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		// ----------------SREG-007 ----------------*/
		sleep(2000);
		commonFunction.screenShot("EmpRegister6", "Pass", "Enter the details on SREG-007 page and click continue");
		commonFunction.clickButtonContains("Continue ");
		commonFunction.screenShot("EmpRegister7", "Pass",
				"Successfully launched Employer Contact Details(SREG-004) page");
		// ----------------SREG-004 ----------------*/
		sleep(2000);

		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empPage.uspsBmadAddressAttention.sendKeys("34 ,Besides bank of paris");
		empPage.uspsBmadAddressText.sendKeys("234 , Villa");
		empPage.uspsBmadCityText.sendKeys("Albany");
		empPage.uspsBmadZipText.sendKeys("67896");
		empPage.uspsBmadCounty.sendKeys("Albany");
		sleep(2000);
		commonFunction.screenShot("EmpRegister8", "Pass", "Entered details of BMAD on (SREG-004) page");

		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empPage.uspsLbraAddressText.sendKeys("Hotel Marriot");
		empPage.uspsLbraCityText.sendKeys("Albany");
		empPage.uspsLbraZipText.sendKeys("67854");
		empPage.uspsLbraCounty.sendKeys("Albany");
		sleep(2000);
		commonFunction.screenShot("EmpRegister9", "Pass", "Entered details of LBRA on (SREG-004) page");

		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		// Blocked after step 14 MC expected SREG 683 , getting SREG 521
		commonFunction.screenShot("EmpRegister10", "Fail", "Launched to (SREG 521) expected (SREG 683)");

		

	}
}
