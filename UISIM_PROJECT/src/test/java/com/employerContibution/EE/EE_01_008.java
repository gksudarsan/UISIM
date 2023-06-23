//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_008 extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Liability Company' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void Test_EE_01_008() throws Exception {
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Liability Company' and work items will be created for CSR to review.");
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//DB SELECT query
		//FEIN not in DOL-DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC","FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		//EAN not in DOL-DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC","EAN");
		String eanValue = databaseEanResult.get("EAN");

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
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-025 ---
		sleep();
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Business ");	
		//String feinValue = StringUtils.left(String.value Of((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)",Long.toString(commonFunction.createRandomInteger(10000000,99999999))+Long.toString(commonFunction.createRandomInteger(9,99)));
		//commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFunction.selectDropdown("Type of Legal Entity", " Limited Liability Company (All Types) ");
		System.out.println("The ERN is " + eanValue);
		commonFunction.enterTextboxContains("Employer Registration Number",Long.toString(commonFunction.createRandomInteger(10000000,99999999))+Long.toString(commonFunction.createRandomInteger(7,99)));
		//commonFunction.enterTextboxContains("Employer Registration Number",eanValue);
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100G ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		sleep();
		
		
		//--- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("LLC");
		commonFunction.enterTextboxContains("Other commonly known", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFunction.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFunction.safeJavaScriptClick(empRegPage.iSyourEntityQuestion_Yes);
		//commonFunction.safeJavaScriptClick(empRegPage.iSyourEntityQuestion_Yes);
		sleep();
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on Information page and click continue");
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Clothing");
		commonFunction.enterTextboxContains("Address Line 1 ", "60 Ave");
		commonFunction.enterTextboxContains("City ", "Albany");
		commonFunction.enterTextboxContains("Zip Code", "44673");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-008 ---
		sleep(2000);
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---
		sleep(2000);
		commonFunction.screenShot("EE01008", "Warning", "Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---
		sleep(2000);
		commonFunction.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		//commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01008", "Pass", "Successfully entered details in SREG-004 page");
		
		sleep(2000);
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
		empRegPage.uspsBmadAddressRadio.click();
		} catch(Exception exception) {}
		
		sleep(2000);
		commonFunction.screenShot("EE01008", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		try {
		empRegPage.continueButton_popUp.click();
		} catch(Exception exception) {}
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {}
		
		// SREG-011 expected, SREG-521 coming. 
		sleep(2000);
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-011 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-011 page");
		commonFunction.selectRadio("Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number","2243625");
		commonFunction.enterTextboxContains("Notification date of Transfer","02/03/2023");
		commonFunction.clickButton("Continue ");
		// --- SREG-012 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-012 page");
		commonFunction.clickButton("Continue ");
		// --- SREG-713 ---
				commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-713 page");
				commonFunction.selectRadioQuestions("Have you changed legal entity?", "No ");
				commonFunction.clickButton("Continue ");
		// --- SREG-006 ---
				commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
				commonFunction.selectRadio("Individual");
				commonFunction.enterTextboxContains("First Name","ABC");
				commonFunction.enterTextboxContains("Last Name","abc");
				commonFunction.clickButton("Continue ");
		// --- SREG-005 ---
				commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
				commonFunction.clickOnLink("Add Member/Managing Member Details");
		// --- SREG-006 ---
				commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");		
				commonFunction.selectRadio("Individual");
				commonFunction.enterTextboxContains("First Name","XYZ");
				commonFunction.enterTextboxContains("Last Name","xyz");
				commonFunction.clickButton("Continue ");
		// --- SREG-005 ---
				commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
				commonFunction.clickButton("Continue ");			
		// --- SREG 683 ---
				sleep(2000);
				commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
				sleep();
				commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
		 		sleep(2000);
		 		commonFunction.uploadDoc("Sample.docx");
		 		sleep(2000);
		 		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
				commonFunction.clickButton("Continue ");
		// --- SREG-800 ---
				sleep(10000);
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
				commonFunction.clickButton("Continue ");
	   // --- SREG-043 ---
				sleep(2000);
				commonFunction.selectCheckbox("I accept");
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
				commonFunction.clickButton("Submit ");
	   // --- SREG-013 ---
				sleep(10000);
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
				commonFunction.clickButton("Home ");
				commonFunction.clickButton(" Go to My Q ");
	   // --- WF-001 ---
				sleep(10000);
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
				commonFunction.enterTextboxContains("Work Item Description Free Text", "Dol");
				commonFunction.clickButton(" Search ");
			//	commonFunction.clickHyperlink("DOL DTF Discrepancy");
	  // --- WF-091 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
				commonFunction.clickButton("Open Work Item ");
      // --- EEWI-005 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-005 page");	
				commonFunction.selectDropdown("Account Status", " Liable ");
				commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
				empRegPage.commentBox_MyQ.sendKeys("for testing");
				commonFunction.clickButton("Submit ");
	 // --- SUC-002 ---
				commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
				commonFunction.clickButton("Home ");
				commonFunction.clickButton(" Go to My Q ");
	// --- WF-001 ---
				 sleep(10000);
		         commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
				 commonFunction.enterTextboxContains("Work Item Description Free Text", "review profile");
				 commonFunction.clickButton(" Search ");
			  //	commonFunction.clickHyperlink("review profile");
	// --- WF-091 ---
					commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-091 page");
					commonFunction.clickButton("Open Work Item ");
	 // --- EEWI-014 ---
					commonFunction.screenShot("EE01007", "Pass", "Successfully launched to EEWI-014 page");	
					commonFunction.selectDropdown("Employer Type", " Business ");
					commonFunction.selectDropdown("What is the first calendar quarter and year you employed 4 (four) or more persons on at least one day in each of 20 different weeks during a calendar year?"," 1 ");
					commonFunction.enterTextboxContains("What is the first calendar quarter and year you employed 4 (four) or more persons on at least one day in each of 20 different weeks during a calendar year?","2023");
					commonFunction.enterTextboxContains("Liability date – Qtr. Year", "6/14/2023");
					commonFunction.selectDropdown("Account Status", " Liable ");
					empRegPage.commentBox_MyQ.sendKeys("for testing");
					commonFunction.clickButton("Submit ");
		 //System failure at 34 step
	  // --- SUC-002 ---
					commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SUC-002 page");
					commonFunction.clickButton("Home ");
					commonFunction.clickButton(" Go to My Q ");
		// --- WF-001 ---
					 sleep(10000);
			         commonFunction.screenShot("EE01007", "Pass", "Successfully launched to WF-001 page");
					 commonFunction.enterTextboxContains("Work Item Description Free Text", "review profile");
					 commonFunction.clickButton(" Search ");
				  //	commonFunction.clickHyperlink("review profile");
		// --- WF-091 ---

					
	}

}
