package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_10_005_TPR_EmpRegGovernmental_LegalEntitiyCounty_CSR_WiReview extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'County' and work items will be created for CSR to review.", groups = {
			COMMON_CONSTANT.REGRESSION })
	public void TC_EE_10_005() throws Exception {

		test = report.createTest(
				"EE.10.005 : Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'County' and work items will be created for CSR to review.");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);

		// GET query
		// FEIN in DOL & not in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		// EAN in DOL not in DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");

		// --- Menu Click ---
		commonFunction.waitForLoadingIconToDisappear();
		empRegPage.menu.click();
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		

		// --- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
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
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("MenuPage", "Pass", "Successful Launch to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		//System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "241766198");
		commonFunction.selectDropdown("Type of Legal Entity", " County ");
		commonFunction.enterTextboxContains("Employer Registration Number", "5675215");
		//System.out.println("The EAN is " + eanValue);
		sleep(2000);
		commonFunction.screenShot("EmpRegister4", "Pass", "Details entered and click on CONTINUE button");
		commonFunction.clickButton("Continue ");

		// --- SREG-003 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister5", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("DAVID COWEN");
		//commonFunction.enterTextboxContains("Other commonly known", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains(" Business Fax Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterPastDate("date of the first payroll", 180);
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "347");
		commonFunction.enterPastDate("Date covered employment", 90);
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Entered details on Employer Entity Information page");
		commonFunction.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?", "Yes ");
		sleep();
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "ASTRO");
		commonFunction.enterTextboxContains("Address Line 1 ", "55 East 10th Street");
		//commonFunction.enterTextboxContains("Address Line 2 ", "St 9th floor");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10003");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Contributory");
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
		commonFunction.screenShot("EmpRegister7", "Pass", "Warning message on Continue");
		commonFunction.clickButton(" Yes ");
		} catch(Exception exception) {
			
		}

		// --- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister8", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "371 7th Ave");
		commonFunction.enterTextboxContains("City ", "NewYork");
		commonFunction.enterTextboxContains("Zip Code", "10001");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister9", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");

		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}

		try {
			sleep();
			commonFunction.screenShot("EmpRegister10", "Pass", "USPS Business address selection on SREG-008");
			empRegPage.continueButton_popUp.click();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		// --- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister11", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");

		// --- SREG-004 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister12", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Location of Books and Records",
				"Same as Primary Business Physical Address");
		empRegPage.enterContactPersonFirstName("Contact Person for Location of Books and Records Address", "Joseph");
		empRegPage.enterContactPersonMiddleName("Contact Person for Location of Books and Records Address", "M");
		empRegPage.enterContactPersonLastName("Contact Person for Location of Books and Records Address", "Morgan");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enetered Details of Contact Person for Location of Books and Records address in SREG-004 page");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
				"Same as Primary Business Physical Address");
		empRegPage.enterContactPersonFirstName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Jonathan");
		empRegPage.enterContactPersonMiddleName("Contact Person for Notice of Potential Charges (LO400) Address", "S");
		empRegPage.enterContactPersonLastName("Contact Person for Notice of Potential Charges (LO400) Address",
				"Cybel");
		commonFunction.screenShot("EmpRegister5", "Pass",
				"Enetered Details of Contact Person for Notice of Potential Charges (LO400) address in SREG-004 page");
		commonFunction.clickButton("Continue ");

		empRegPage.uspsBmadAddress.click();
		empRegPage.uspsLbraAddress.click();
		empRegPage.uspsNpcaAddress.click();
		commonFunction.screenShot("EmpRegister5", "Pass", "Entered USPS address in SREG-004 page");
		commonFunction.clickButton("Continue ");

		// --- SREG 683 Screen expected ---

		commonFunction.screenShot("FailurePage", "Fail", "Launched to SREG-521 page");
		System.out.println("XXX");

	}

}