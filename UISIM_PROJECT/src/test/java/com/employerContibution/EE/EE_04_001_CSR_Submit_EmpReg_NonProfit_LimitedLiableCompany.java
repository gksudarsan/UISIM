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

public class EE_04_001_CSR_Submit_EmpReg_NonProfit_LimitedLiableCompany extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Limited Liability Company (All Types)' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_04_001() throws Exception{
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Limited Liability Company (All Types)' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//--- GET Query
		
		// FEIN in DOL & in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN) = 9 ORDER BY UPDATED_TS",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("The FEIN is " + feinValue);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
			
		// ---Menu Click---
		sleep();
		commonFunction.clickMenu("Menu");
		sleep();
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Launched the Employer Register(SREG-001) page");
		
		// --- SREG-001 ---
		sleep(2000);
		commonFunction.ScrollMenu("Suffix");
		commonFunction.screenShot("EE04001", "Pass", "Data from SREG-001 page");
		sleep(2000);
		commonFunction.ScrollMenu("Suppress Correspondence?");
		commonFunction.screenShot("EE04001", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Non-Profit ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue); //897397325
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Limited Liability Company (All Types) ");
		//commonFunction.enterTextboxContains("Employer Registration Number", "2897453"); //4543352
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EE04001", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		// --- SREG-003 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Launched Employer Entity Information(SREG-003) page");
		sleep(2000);
		commonFunction.clickButton("Continue ");
		commonFunction.errorLabel(" Required");
		commonFunction.screenShot("EE04001", "Pass", "Required field in SREG-003 page");
		
		empRegPage.legalNameTextBox.sendKeys("ColorEseence122"); //ColorEseence122
		//commonFunction.enterTextboxContains("Other name under which you operate", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterPastDate("Enter date of first operations in New York State", 180);
		empRegPage.firstCalender_Quater.click();
		empRegPage.firstCalender_Quater_Value.click();
		empRegPage.firstCalender_Year.click();
		empRegPage.firstCalender_Year_Value_2022.click();
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Details entered in SREG-003 page");
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "Yes ");
		commonFunction.enterTextboxContains("Explain services that are performed by people you do not consider to be your employee(s).","10");
		empRegPage.What_firstCalender_Quater.click();
		empRegPage.firstCalender_Quater_Value_2.click();
		empRegPage.What_firstCalender_Year.click();
		empRegPage.firstCalender_Year_Value_2023.click();
		commonFunction.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for nonprofit employment, do you wish to elect voluntary coverage?", "Yes ");
		commonFunction.selectRadioQuestions("Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?", "Yes ");
		commonFunction.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Reimbursable");
		commonFunction.selectRadioQuestions("If Reimbursable, has a copy of the 501c3 exemption documentation been provided?", "No ");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Legal Name Error warning");
		empRegPage.legalNameTextBox.clear();
		empRegPage.legalNameTextBox.sendKeys("DAVID EFFRON LLC");
		commonFunction.screenShot("EE04001", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-008 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.enterTextboxContains("Number of employees at this location", "10");
		commonFunction.enterTextboxContains("Name of Government Agency from which you receive funds", "NYSDOL");
		commonFunction.screenShot("EE04001", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLinkAnchorTag(" Add Another Business Location ");
		
		// --- SREG-008 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully launched Additional Business Physical Location(s)(SREG-008) page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Data entered in SREG-008 page");
		commonFunction.enterTextboxContains("Number of employees at this location", "10");
		commonFunction.enterTextboxContains("Name of Government Agency from which you receive funds", "NYSDOL");
		commonFunction.enterTextboxContains("Program Name", "Test");
		commonFunction.selectDropdownEquals("Principle purpose for which you are organized and operate.", " Other ");
		commonFunction.enterTextboxContains("If Other, provide details", "Test");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Data entered in SREG-008 page and clicked Continue");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---			
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		//commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully entered details in SREG-004 page");
		
		try {
		//commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
		empRegPage.uspsLbraCityText.sendKeys("New York");
		empRegPage.uspsLbraZipText.sendKeys("10001");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully entered details in SREG-004 page");
		}catch(Exception exception) {}
		
		//commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Mailing");
		//empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
		//empRegPage.uspsNpcaCityText.sendKeys("New York");
		//empRegPage.uspsNpcaZipText.sendKeys("10003");
		//empRegPage.uspsNpcaCounty.click();
		//commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully entered details in SREG-004 page");
		
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
		commonFunction.screenShot("EE04001", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		try {
		empRegPage.continueButton_popUp.click();
		} catch(Exception exception) {}
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {}
		
		// SREG-011 expected, SREG-521 coming. 
		sleep(2000);
		commonFunction.screenShot("EE04001", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0449897");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "115339997");
		empRegPage.tradeNameId_SREG011.sendKeys("WILLIAM JEFFERSON CLINTON");
		empRegPage.address1_SREG011.sendKeys("Affinia Manhattan Hotel");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10013");
		commonFunction.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		commonFunction.enterTextboxContains("Acquisition Date", "03/31/2023");
		commonFunction.enterPastDate("Notification date of Transfer", 0);
		sleep(3000);
		commonFunction.screenShot("EE04001", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
				
		// SREG - 012
		sleep(3000);
		commonFunction.screenShot("EE04001", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		// SREG-006
		commonFunction.screenShot("EE04001", "Pass", "Successful launch to SREG-006 page");
		commonFunction.selectRadioQuestions("Type of Member/Managing Member", "Business Entity");
		commonFunction.enterTextboxContains("Entity Name", "Test");
		commonFunction.enterTextboxContains("Federal Identification Number (FEIN)", "897387426");
		commonFunction.selectDropdownEquals("Title", " Member ");
		commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("Zip Code", "10012");
		
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-006");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-006");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG 005 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "Click on 'Add Member/Managing Member Details' link on SREG-005");
		commonFunction.clickOnLink("Add Member/Managing Member Details");
		
		commonFunction.selectRadioQuestions("Type of Member/Managing Member", "Individual");
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(ssnValue);
		commonFunction.enterTextboxContains("SSN", ssnValue);
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Last Name", "Gonzalez");
		commonFunction.enterTextboxContains("Address Line 1 ", "345 E 24th St");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("Zip Code", "10012023");
		
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-006");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-006");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG 005 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-005");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		sleep(2000);
		commonFunction.screenShot("EE04001", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EE04001", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");

		// --- SREG-800 ---
		sleep(4000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		sleep(2000);
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE04001", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		sleep(10000);
		commonFunction.screenShot("EE04001", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Exit ");
		
		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1 + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='" + feinValue + "' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
	     
	    peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("FEIN",feinValue);
	    commonFunction.screenShot("FeinSearch","Pass","FEIN Search");
	    commonFunction.clickButtonContains("Search");
	    
	    sleep(3000);
	    commonFunction.clickOnLink("Review Employer Type");
	    
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item");
	    
	    // ---  EEWI-002 ---
	    sleep(3000);
	    empRegPage.firstCalender_Quater.click();
		empRegPage.firstCalender_Quater_Value.click();
		empRegPage.firstCalender_Year.click();
		empRegPage.firstCalender_Year_Value_2022.click();
	    empRegPage.commentId_EEWI002.sendKeys("Tested Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE04001", "Pass", "Successfully launched to EEWI-002 page");
	    commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    sleep(3000);
	    commonFunction.screenShot("EE04001", "Pass", "Successfully launched to SUC-002 page");
		commonFunction.clickButton("Home ");
		
		
		Thread.sleep(2000);
	     
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("FEIN",feinValue);
	    commonFunction.screenShot("FeinSearch","Pass","FEIN Search");
	    commonFunction.clickButtonContains("Search");
	    
	    
	    // scripted till step 47,DOL DTF WI not created
	    commonFunction.screenShot("EE04001","Fail","DOL DTF Discripency WI Not created");
	    
	}

}
