package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_04_003_CSR_Submit_EmpReg_NonProfit_UnincorpAssociation extends TestBase {

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Unincorporated Association' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_04_003() throws Exception{
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Unincorporated Association' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//--- GET Query
		
		// FEIN one match in DOL, not match in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("The FEIN is " + feinValue);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// ---Menu Click---
		sleep();
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04003", "Pass", "Launched the Employer Register(SREG-001) page");

		// --- SREG-001 ---
		sleep(2000);
		commonFunction.ScrollMenu("Suffix");
		commonFunction.screenShot("EE04003", "Pass", "Data from SREG-001 page");
		sleep(2000);
		commonFunction.ScrollMenu("Suppress Correspondence?");
		commonFunction.screenShot("EE04003", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		

		// --- SREG-025 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04003", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Non-Profit ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); //897397325
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Unincorporated Association ");
		//commonFunction.enterTextboxContains("Employer Registration Number", "2897453"); //4543352
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFunction.selectDropdown("Source Type", " NYS-100N ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EE04003", "Pass", "Entered the details and clicked on continue button");
		
		// --- SREG-003 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04002", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("Sugar L.L.C"); //ColorEseence122
		//commonFunction.enterTextboxContains("Other name under which you operate", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		//commonFunction.enterPastDate("Enter date of first operations in New York State", 365);
		empRegPage.firstCalender_Quater.click();
		empRegPage.firstCalender_Quater_Value_1.click();
		empRegPage.firstCalender_Year.click();
		empRegPage.firstCalender_Year_Value_2023.click();
		sleep(2000);
		commonFunction.screenShot("EE04002", "Pass", "Details entered in SREG-003 page");
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		empRegPage.What_firstCalender_Quater.click();
		empRegPage.firstCalender_Quater_Value_1.click();
		empRegPage.What_firstCalender_Year.click();
		empRegPage.firstCalender_Year_Value_2023.click();
		sleep(2000);
		commonFunction.screenShot("EE04002", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");		
		
		// --- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04002", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.enterTextboxContains("Number of employees at this location", "345");
		commonFunction.enterTextboxContains("Name of Government Agency from which you receive funds", "NYSDOL");
		commonFunction.screenShot("EE04002", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE04002", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();		
				
		// --- SREG-007 ---		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04002", "Pass", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---
		commonFunction.waitForLoadingIconToDisappear();
		
		commonFunction.screenShot("EE04002", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		//commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04002", "Pass", "Successfully entered details in SREG-004 page");
		
		try {
		//commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
		empRegPage.uspsLbraCityText.sendKeys("New York");
		empRegPage.uspsLbraZipText.sendKeys("10001");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04002", "Pass", "Successfully entered details in SREG-004 page");
		} catch(Exception exception) {}
		
		//commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
		empRegPage.uspsNpcaCityText.sendKeys("New York");
		empRegPage.uspsNpcaZipText.sendKeys("10003");
		empRegPage.uspsNpcaCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04002", "Pass", "Successfully entered details in SREG-004 page");
		
		sleep(2000);
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
		empRegPage.uspsBmadAddressRadio.click();
		} catch(Exception exception) {}
		
		try {
		empRegPage.uspsLbraAddressRadio.click();
		} catch(Exception exception) {}

		try{
			empRegPage.uspsNpcaAddressRadio.click();
		} catch(Exception exception) {}
		
		sleep(2000);
		commonFunction.screenShot("EE04002", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		try {
		empRegPage.continueButton_popUp.click();
		} catch(Exception exception) {}
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {}
		
		// SREG-011 expected, SREG-521 coming. 
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04002", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04002", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0000211");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "235324393");
		empRegPage.tradeNameId_SREG011.sendKeys("Lakme12 L.L.C");
		empRegPage.address1_SREG011.sendKeys("Affinia Manhattan Hotel");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10013");
		commonFunction.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFunction.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
		commonFunction.enterPastDate("Notification date of Transfer", 0);
		sleep(3000);
		commonFunction.screenShot("EE04002", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 012
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE04002", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		// SREG-006
//		commonFunction.screenShot("EE04002", "Pass", "Successful launch to SREG-006 page");
//		commonFunction.selectRadioQuestions("Type of Corporate Officer/Owner", "Business Entity");
//		commonFunction.enterTextboxContains("Entity Name", "Test Entity");
//		commonFunction.enterTextboxContains("Federal Identification Number (FEIN)", "246324903");
//		commonFunction.enterTextboxContains("Last Name", "Gonzalez");
//		commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
//		commonFunction.enterTextboxContains("City ", "New York");
//		commonFunction.selectDropdown("State", " New York ");
//		commonFunction.enterTextboxContains("Zip Code", "10012023");
//		
//		commonFunction.screenShot("EE04002", "Pass", "USPS Business address selection on SREG-006");
//		commonFunction.clickButton("Continue ");
//		
//		sleep();
//		try {
//			empRegPage.uspsBusinessAddress.click();
//		} catch (Exception exception) {
//			empRegPage.uspsBusinessAddressInnerCircle.click();
//		}
//		
//		commonFunction.screenShot("EE04002", "Pass", "USPS Business address selection on SREG-006");
//		empRegPage.continueButton_popUp.click();
		
		//Exec doc not available after this step.
		commonFunction.screenShot("EE04002", "Fail", "Step different in MC and Exec Doc.");
		
		System.out.println("fail");
	}
}
