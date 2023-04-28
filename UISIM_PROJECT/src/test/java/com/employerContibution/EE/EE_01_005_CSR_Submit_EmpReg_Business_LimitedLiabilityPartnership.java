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
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd) AND ENTITY_NAME IS NOT NULL ORDER BY UPDATED_TS DESC","ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// ---Menu Click---
		sleep();
		commonFunction.clickMenu("Menu");
		// commonFuntions.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
		
		// --- SREG-001 ---
		sleep(2000);
		commonFunction.ScrollMenu("Suffix");
		commonFunction.screenShot("EmpRegister2", "Pass", "Data from SREG-001 page");
		sleep(2000);
		commonFunction.ScrollMenu("Suppress Correspondence?");
		commonFunction.screenShot("EmpRegister3", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister3", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValueRandom); //897397325
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Partnership ");
		//commonFunction.enterTextboxContains("Employer Registration Number", eanValue); //4543352
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister4", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		//--- SREG-003 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys(legalName); //ColorEseence122
		//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Business Email Address", "randomBusinessMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EmpRegister6", "Pass", "Details entered in SREG-003 page");
		commonFunction.enterPastDate("Enter date of first operations in New York State", 450);
		commonFunction.enterPastDate("What is the date of the first payroll which you withheld ", 360);
		commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		commonFunction.selectRadioQuestions("Are you registering to remit withholding tax only?", "Yes ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister7", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		
		// --- SREG-008 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister8", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
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
		commonFunction.screenShot("EmpRegister11", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister12", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
//		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister13", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
//		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
//		empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
//		empRegPage.uspsLbraCityText.sendKeys("New York");
//		empRegPage.uspsLbraZipText.sendKeys("10001");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsLbraFirstNameText.sendKeys("Thomas");
		empRegPage.uspsLbraLastNameText.sendKeys("Shelby");
		sleep(2000);
		commonFunction.screenShot("EmpRegister14", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
//		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
//		empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
//		empRegPage.uspsNpcaCityText.sendKeys("New York");
//		empRegPage.uspsNpcaZipText.sendKeys("10003");
		empRegPage.uspsNpcaCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
//		sleep();
//		empRegPage.uspsNpcaFirstNameText.sendKeys("Sydney");
//		empRegPage.uspsNpcaLastNameText.sendKeys("Sheldon");
		sleep(2000);
		commonFunction.screenShot("EmpRegister15", "Pass", "Successfully entered details in SREG-004 page");
	
		commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
		
		sleep(2000);
		commonFunction.selectRadioQuestions("Agent (C/O) address", "Other");
		empRegPage.uspsAgadCareOf.sendKeys("Ctalonea B");
		empRegPage.uspsAgadAddressText.sendKeys("19th Street");
		empRegPage.uspsAgadCityText.sendKeys("New York");
		empRegPage.uspsAgadZipText.sendKeys("10045");
		empRegPage.uspsAgadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsAgadFirstNameText.sendKeys("Peaky");
		empRegPage.uspsAgadLastNameText.sendKeys("Blinder");
		sleep(2000);
		commonFunction.screenShot("EmpRegister16", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		empRegPage.uspsBmadAddressRadio.click();
		empRegPage.uspsLbraAddressRadio.click();
		empRegPage.uspsNpcaAddressRadio.click();
		commonFunction.screenShot("EmpRegister17", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		empRegPage.continueButton_popUp.click();
		
		// launching SREG-521 page, expected SREG - 011 as is MC(Step 14)
		sleep(3000);
		commonFunction.screenShot("EmpRegister15", "Fail", "System launched SREG - 521, expected SREG - 011");
		
		
	}

}
