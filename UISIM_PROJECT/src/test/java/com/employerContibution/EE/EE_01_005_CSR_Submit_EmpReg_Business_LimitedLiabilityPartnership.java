package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_005_CSR_Submit_EmpReg_Business_LimitedLiabilityPartnership extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Liability Partnership' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_01_005() throws Exception {
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Liability Partnership' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		// GET query
		// FEIN not in DOL & DTF
		/*
		 * Map<String, String> databaseFeinResult =
		 * commonFunction.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN) = 9 ORDER BY UPDATED_TS DESC"
		 * , "FEIN"); String feinValueFromDb = databaseFeinResult.get("FEIN");
		 * System.out.println("The DB FIEN is " + feinValueFromDb);
		 */

		String feinValueRandom = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		//String feinValueRandom = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The Random FIEN is " + feinValueRandom);
		
		/*
		 * String feinToUse = ""; try { if (feinValueRandom == feinValueFromDb) {
		 * //feinToUse = feinValueRandom; } } catch(Exception exception) {
		 * System.out.println("FEIN matching in DOL table"); }
		 */
		
		// EAN in DOL & DTF
		/*
		 * Map<String, String> databaseEanResult =
		 * commonFunction.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC"
		 * , "EAN"); String eanValue = databaseEanResult.get("EAN");
		 * System.out.println("The EAN is " + eanValue);
		 */
		
		//Legal name not in DOL, multiple in DTF
		  //Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd) AND ENTITY_NAME IS NOT NULL ORDER BY UPDATED_TS DESC","ENTITY_NAME");
		  //String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		   //System.out.println("The LegalName is " + legalName);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		commonFunction.waitForLoadingIconToDisappear();
		
		// ---Menu Click---
		sleep(2000);
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched the Employer Register(SREG-001) page");
		
		// --- SREG-001 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Data from SREG-001 page");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "652986349"); //202298634
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Limited Liability Partnership ");
		sleep(2000);
		commonFunction.enterTextboxContains("Employer Registration Number", "6594345"); //2070072
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(2000);
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep(2000);
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EE01007", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		// --- SREG-003 ---
				sleep(2000);
				commonFunction.screenShot("EE01007", "Pass", "Launched Employer Entity Information(SREG-003) page");
				empRegPage.legalNameTextBox.sendKeys("SCOTT MCBRIDE"); //MOCA PIZZA 
				//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
				commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "test");
				commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
				commonFunction.enterTextboxContains("Business Email Address", "randomBusinessMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
				sleep(2000);
				commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-003 page");
				commonFunction.enterPastDate("Enter date of first operations in New York State", 120);
				commonFunction.enterPastDate("What is the date of the first payroll which you withheld ", 150);
				sleep(2000);
				commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-003 page");
				
				commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
				commonFunction.selectDropdown("Quarter", "4");
				sleep(2000);
		     	commonFunction.selectDropdown("Year", "2022");
				commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
				commonFunction.selectRadioQuestions("Are you registering to remit withholding tax only?", "Yes ");
				commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-003 page and click Continue");
				commonFunction.clickButton("Continue ");
				
				// --- SREG-008 ---
		
		
		sleep(2000);
		commonFunction.screenShot("EmpRegister8", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "70 Washington Square South");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10012");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.selectDropdown("Principal Business Activity at this location in New York State", " Manufacturing ");
		empRegPage.principalProducts_SREG008.sendKeys("testProd");
		empRegPage.principalRawMaterial_SREG008.sendKeys("codes");
		commonFunction.screenShot("EmpRegister9", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		commonFunction.screenShot("EmpRegister10", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Warning", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
	
		// --- SREG-004 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		//commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
	    commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
	    empRegPage.uspsBmadAddressText.sendKeys("70 Washington Square South");
		empRegPage.uspsBmadCityText.sendKeys("New York");
	    empRegPage.uspsBmadZipText.sendKeys("10012");
	    empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully entered details in SREG-004 page");
		
//		commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsLbraAddressText.sendKeys("29 Washington Pl,");
		empRegPage.uspsLbraCityText.sendKeys("New York");
		empRegPage.uspsLbraZipText.sendKeys("10003");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsLbraFirstNameText.sendKeys("Brown");
		empRegPage.uspsLbraLastNameText.sendKeys("Bldg");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully entered details in SREG-004 page");
		
//		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
       empRegPage.uspsNpcaAddressText.sendKeys("36 East 8th Street");
	   empRegPage.uspsNpcaCityText.sendKeys("New York");
	   empRegPage.uspsNpcaZipText.sendKeys("10003");
	   empRegPage.uspsNpcaCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsNpcaFirstNameText.sendKeys("Cantor");
		empRegPage.uspsNpcaLastNameText.sendKeys("Film");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully entered details in SREG-004 page");
		
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
		commonFunction.screenShot("EE01007", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		try {
		empRegPage.continueButton_popUp.click();
		} catch(Exception exception) {}
		
		
		
		// SREG-521 expected, 
		sleep(2000);
		commonFunction.screenShot("EE01007", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", "2485896");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "240933037");
		empRegPage.tradeNameId_SREG011.sendKeys("upstobst Library");
		empRegPage.address1_SREG011.sendKeys("5 Washington Square S");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10012");
		commonFunction.enterPastDate("Acquisition Date", 150);
		commonFunction.enterPastDate("Notification date of Transfer", 130);
		commonFunction.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		//commonFunction.enterPastDate("Acquisition Date", 90);
		//commonFunction.enterTextboxContains("Notification date of Transfer", "07012023");
		sleep(3000);
		commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 012
				sleep(3000);
				commonFunction.screenShot("EE01007", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		
		
		
	}

}
