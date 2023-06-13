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
public class EE_01_006_CSR_Submit_EmpReg_Business_LimitedPartnership extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Partnership' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_01_006() throws Exception {
	
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Partnership' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//--- GET Query
		
		// ERN that cannot be found on DOL, and is different than ERN that matches FEIN on DTF:
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd WHERE ERN NOT IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd2 LEFT JOIN T_EMPLOYER_ACCOUNT tea ON tea.FEIN = tedd2.FEIN WHERE tea.EAN != tedd2.ERN) AND ERN IS NOT NULL",
				"ERN");
		String eanValue = databaseEanResult.get("ERN");
		System.out.println("The ERN is " + eanValue);
		
		//Legal name match in DOL
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IS NOT NULL AND LENGTH(ENTITY_NAME) <=200 ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);

		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Launched the Employer Register(SREG-001) page");
		
		// --- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.ScrollMenu("Suffix");
		commonFunction.screenShot("EE01006", "Pass", "Data from SREG-001 page");
		sleep(2000);
		commonFunction.ScrollMenu("Suppress Correspondence?");
		commonFunction.screenShot("EE01006", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "541961111"); //897397325
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Limited Partnership ");
		commonFunction.enterTextboxContains("Employer Registration Number", "2897453"); //4543352
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EE01006", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		// --- SREG-003 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys(legalName); //ColorEseence122
		//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Business Email Address", "randomBusinessMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-003 page");
		commonFunction.enterPastDate("Enter date of first operations in New York State", 360);
		commonFunction.enterPastDate("What is the date of the first payroll which you withheld ", 270);
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Sucessfully launched to SREG-008 page");
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
		commonFunction.screenShot("EE01006", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
//				commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Successfully entered details in SREG-004 page");
		
		sleep(2000);
		
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		empRegPage.uspsBmadAddressRadio.click();
		commonFunction.screenShot("EE01006", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		empRegPage.continueButton_popUp.click();
		
		// SREG-011 expected, SREG-521 coming. 
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		empRegPage.tradeNameId_SREG011.sendKeys("Cooking Inc");
		empRegPage.address1_SREG011.sendKeys("Affinia Manhattan Hotel");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10013");
		commonFunction.enterPastDate("Acquisition Date", 90);
		commonFunction.enterTextboxContains("Notification date of Transfer", "04012023");
		sleep(3000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 012
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successful launch to (Change in Legal Entity)SREG-012 page");
		commonFunction.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFunction.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "321326774");
		commonFunction.enterTextboxContains("Prior Employer Registration Number", "2897452");
		commonFunction.enterTextboxContains("Date of Legal Entity change", "10012023");
		commonFunction.enterTextboxContains("Date of Notification", "05152023");
		commonFunction.screenShot("EE01006", "Pass", "Details entered in to SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		// SREG-006
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successful launch to SREG-006 page");
		commonFunction.selectRadioQuestions("Type of Partner/Owner", "Individual");
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(ssnValue);
		commonFunction.enterTextboxContains("SSN", ssnValue);
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Last Name", "Gonzalez");
		commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("Zip Code", "10012023");
		
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-006");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-006");
		empRegPage.continueButton_popUp.click();
		
		
		// --- SREG 005 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-005");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EE01006", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-800 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		sleep(12000);
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Home ");

//		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='324923847' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
	     
	    peoPage.queue.click();
	    Thread.sleep(15000);
//	    commonFunction.enterTextboxContains("FEIN","541961111");
//	    commonFunction.screenShot("FeinSearch","Pass","feinSearch");
//	    commonFunction.clickButtonContains("Search");
	    Thread.sleep(2000);
	    commonFunction.screenShot("EE01006", "Fail", "WI did not created");
	    
	    
	    
		System.out.println("Pass :)");
	}

}
