//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_13_005 extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Governmental'.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EE_13_005() throws Exception {
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Governmental'");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//DB SELECT query
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS DESC","FEIN");
		String feinValue = databaseFeinResult.get("FEIN");

				
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.clickMenu("Menu");
		//commonFuntions.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Employer Registration");
		empRegPage.employerRegisterMenu.click();
		//commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFunction.clickMenu("Register Employer");
		sleep();
		
		//--- SREG-001 ---
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.selectRadioQuestions("Short Form Registration", "Yes ");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-025 ---
		sleep();
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Non-Profit ");		
		//String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectDropdown("Type of Legal Entity", " Limited Liability Company (All Types) ");
		commonFunction.selectDropdown("Source", " IA602 ");
		commonFunction.selectDropdown("Source Type", " Coverage Exception ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister2", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();
		
		//--- SREG-003 ---
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Mandatory fields Warning message displaying");
		
		empRegPage.legalNameTextBox.sendKeys("XYZ Corp LLC");
		commonFunction.enterTextboxContains("Other name under which you operate", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		//commonFunction.enterTextboxContains("Ext", "003");
		
//		commonFunction.screenShot("EmpRegister4", "Pass", "Warning Message on clicking Continue");
//		commonFunction.clickButton("Continue ");
//		
//		commonFunction.enterTextboxContains("Other name under which you operate", "");
//		commonFunction.enterTextboxContains("Other name under which you operate", "p");
		
		
		// to script from step 8 MC
		
		sleep(2000);
		commonFunction.screenShot("EmpRegister4", "Pass", "Warning Message on clicking Continue");
		empRegPage.firstCalender_Quater.click();
		empRegPage.firstCalender_Quater_Value_2.click();
		empRegPage.firstCalender_Year.click();
		sleep();
		empRegPage.firstCalender_Year_Value_2022.click();
		empRegPage.What_firstCalender_Quater.click();
		sleep();
		empRegPage.firstCalender_Quater_Value.click();
		empRegPage.What_firstCalender_Year.click();
		sleep();
		empRegPage.firstCalender_Year_Value_2022.click();
		commonFunction.selectRadioQuestions("Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?", "Yes ");
		commonFunction.screenShot("EmpRegister4", "Pass", "Details entered in SREG 003");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		commonFunction.screenShot("EmpRegister4", "Pass", "Clicked on Yes on pop-up window");
		try{
		commonFunction.clickButton(" Yes ");
		} catch(Exception exception) {}
		
		// --- SREG-004 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Successfully entered details in SREG-004 page");
		
		sleep(2000);
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
		empRegPage.uspsBmadAddressRadio.click();
		} catch(Exception exception) {}
		
		sleep(2000);
		commonFunction.screenShot("EmpRegister5", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		try {
		empRegPage.continueButton_popUp.click();
		} catch(Exception exception) {}
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {}
		
		// --- SREG-521 --- 
		sleep(2000);
		commonFunction.screenShot("EmpRegister6", "Pass", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		sleep(2000);
		commonFunction.screenShot("EmpRegister7", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0450428");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "161220353");
		empRegPage.tradeNameId_SREG011.sendKeys("FIRST UNITED MOTHODIST CHURCH");
		empRegPage.address1_SREG011.sendKeys("Affinia Manhattan Hotel");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10013");
		commonFunction.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
		commonFunction.enterPastDate("Acquisition Date", 360);
		commonFunction.enterPastDate("Notification date of Transfer", 10);
		sleep(3000);
		commonFunction.screenShot("EmpRegister7", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 012
		sleep(3000);
		commonFunction.screenShot("EmpRegister8", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister9", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EmpRegister9", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-800 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister10", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister11", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		sleep(10000);
		commonFunction.screenShot("EmpRegister12", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Exit ");
		
		
		////////
		commonFunction.clickMenu("Menu");
		//commonFuntions.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Inquiry");;
		commonFunction.clickMenu("Contribution Inquiry");
		commonFunction.screenShot("EmpRegister13", "Pass", "Navigated to Menu -> Inquiry -> Contribution Inquiry -> Inquiry Employer Account");
		commonFunction.clickMenu("Inquiry Employer Account");
		
		// --- SREG-050 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister14", "Pass", "Successfully launched to SREG-050 page");
		commonFunction.enterTextboxContains("Employer Registration Number", "8606720");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 051 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister15", "Pass", "Successfully launched to SREG-051 page");
		commonFunction.clickButton("Previous ");
		
		// --- SREG-050 ---
		sleep(2000);
		commonFunction.screenShot("EmpRegister16", "Pass", "Successfully launched to SREG-050 page on clicking Previous at SREG-051 page");
		commonFunction.clickButton(" Home ");
		
		commonFunction.screenShot("SuccessPage", "Pass", "Test Case Passed");
	}
}
