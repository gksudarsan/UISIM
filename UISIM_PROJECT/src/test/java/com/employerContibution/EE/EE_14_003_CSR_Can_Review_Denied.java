package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_14_003_CSR_Can_Review_Denied extends TestBase{
	
	@Test
	public void EE_14_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("Verify CSR is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100G sent to NYBE.");
		//-------------Login
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		

		//--- Menu Click ---
		commonFuntions.screenShot("Menu", "Pass", "Menu page");
		commonFuntions.clickMenu("menu");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep();
		commonFuntions.screenShot("Menu", "Pass", "Menu - Employer Registration - Register Employer");
		sleep();
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		//--- SREG-001 ---
		commonFuntions.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
		sleep();
		commonFuntions.clickButton("Continue ");
		
		
		
	    /*---------------SREG-025--------------*/
		commonFuntions.screenShot("General Information", "Pass", "Navigated to SREG-025 page and enter the details");
		sleep(2000);
		
		/*---------------DB_FEIN--------------
		Map<String, String> FeinOutput =  commonFuntions.database_SelectQuerySingleColumn("SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ", "FEIN");
		String feinValue = FeinOutput.get("FEIN");
		System.out.println("FEIN 1 : : " +feinValue);
		test.log(Status.INFO, "FEIN 1 : : " +feinValue);*/
		
		/*---------------FEIN--------------*/
		//commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(12, 12))), 11));
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		sleep(2000);
		commonFuntions.selectDropdown("Type of Legal Entity", " Business ");
		sleep(2000);
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		sleep(2000);
		commonFuntions.selectDropdown("Source Type", " NYS-100IT ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		sleep(2000);
		commonFuntions.screenShot("General Information", "Pass", "entered the details SREG-025 page ");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		/*---------------SREG-003--------------*/
		//---------------Legal Name--------------
		//SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT  IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName="Random Legal Name " + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		/*---------------Legal Name--------------*/
		
		empPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(100,999)));
		sleep(2000);
		//commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?",);
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax", 365);
		sleep(2000);
		commonFuntions.selectRadioQuestions("Are you a subdivision, subsidiary or business enterprise wholly", "Yes ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
		sleep(2000);
		commonFuntions.enterPastDate("Date covered employment began? ", 180);
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-008----------------*/
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entering the details");
		sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		sleep(2000);
		commonFuntions.enterTextboxContains("City ", "NY");
		sleep(2000);
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		sleep(2000);
		commonFuntions.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entered the details");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-007----------------*/
		
		sleep(4000);
		
		commonFuntions.screenShot("Business physical Address details", "Pass", "Navigated to SREG-007 page");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		

		/*-----------------SREG-004----------------*/
		
		sleep(3000);
		commonFuntions.screenShot("Emp contact details", "Pass", "Navigated to SREG-004 page and entering the details");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		commonFuntions.screenShot("Emp contact details", "Pass", "Selected Same as Same as Primary Business Physical Address for Notice of Potential Charges (LO400) Address");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-521----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-521 page");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
		sleep(4000);
		commonFuntions.selectLink("Supporting documents like 501(c)(3) Exemptions, Lessor contracts, ", "Browse");
		//commonFuntions.safeJavaScriptClick(empPage.browserLink);
		sleep(2000);
		commonFuntions.uploadDoc("Sample.docx");
		Thread.sleep(4000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		sleep(2000);
		commonFuntions.clickButtonContains("Continue");
		
		/*-----------------SREG-800----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-800 page");
		sleep(2000);
		test.log(Status.INFO, "Click on edit Employer Registration");
		sleep(2000);
		commonFuntions.safeJavaScriptClick(empPage.employer_Register_Edit_Button);
		
		/*-----------------SREG-001----------------*/
		
		
		commonFuntions.selectRadioQuestions("Suppress Correspondence?", "Yes ");
		sleep(2000);
	
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.clickButtonContains(" Yes ");

		//-----------------SREG-800----------------*/
		
commonFuntions.screenShot("Review Registration Details", "Pass", "Navigated to SREG-004 page and update the address");
sleep(2000);
commonFuntions.safeJavaScriptClick(empPage.Contact_Detail_Edit_Button);
sleep(2000);
//------SREG 004------------------------
sleep(2000);
commonFuntions.screenShot("Employer Contact Details", "Pass", "landed to SREG-004 page and update the address");
sleep(2000);
commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
sleep(2000);
commonFuntions.clickButton("Continue ");
//----------SREG 800
sleep(2000);
commonFuntions.safeJavaScriptClick(empPage.upload_Document_Edit_Button);
//----------SREG 638
sleep(3000);
commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
sleep(2000);
commonFuntions.selectLink("Supporting documents like 501(c)(3) Exemptions, Lessor contracts, ", "Browse");
sleep(6000);
commonFuntions.uploadDoc("Sample.docx");
Thread.sleep(4000);
commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
sleep(2000);
try {
	commonFuntions.clickButtonContains(" Yes ");}
	catch(Exception e){}
	sleep(2000);

commonFuntions.clickButtonContains("Continue");
sleep(10000);
//------SREG 800
sleep(2000);
		commonFuntions.safeJavaScriptClick(empPage.general_Info_Edit_Button);
//------SREG 025
		
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)",StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(12, 12))), 11));
		sleep(2000);
		commonFuntions.selectDropdown("Employer Type", " Business ");
		sleep(2000);
		try {
		commonFuntions.clickButtonContains(" Yes ");}
		catch(Exception e){}
		sleep(2000);
		//commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		//sleep(2000);
		commonFuntions.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		sleep(2000);
		//commonFuntions.selectDropdown("Source Type", " NYS-100IT ");
		//sleep(2000);
		commonFuntions.clickButton("Continue ");
		//-----SREG 003
		sleep(2000);
		String legalName1="Random Legal Name " + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		
		empPage.legalNameTextBox.sendKeys(legalName1);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFuntions.createRandomInteger(1000000,9999999))+Long.toString(commonFuntions.createRandomInteger(100,999)));
		sleep(2000);
		
		commonFuntions.enterPastDate("Enter date of first operations in New York State", 365);
		sleep(2000);
		commonFuntions.enterPastDate("What is the date of the first payroll", 270);
		sleep(2000);
		commonFuntions.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
		sleep(2000);
		//commonFuntions.enterPastDate("First calendar quarter and year you have paid", 180);
		//sleep(2000);
		commonFuntions.selectDropdown("Quarter ", " 4 ");
		sleep(2000);
		commonFuntions.selectDropdown("Year ", " 2022 ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		sleep(2000);
		commonFuntions.selectRadioQuestions("Are you registering to remit withholding tax only?", "No ");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		//-------SREG 008
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Navigated to SREG-008 page and entering the details");
		sleep(2000);
		commonFuntions.enterTextboxContains("Address Line 1 ", "8963PHP Road ");
		sleep(2000);
		commonFuntions.enterTextboxContains("City ", "Albany");
		sleep(2000);
		commonFuntions.enterTextboxContains("Zip Code", "87534");
		sleep(2000);
		commonFuntions.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", " entered the details");
		sleep(2000);
		commonFuntions.enterTextboxContains("Number of employees at this location", "15");
		sleep(2000);
		commonFuntions.selectDropdown("Principal Business Activity at this location in New York State", " Accommodation and Food Services ");
		sleep(2000);
		empPage.typeOfEstablishment_SREG008.sendKeys("Testing");
		sleep(2000);
		empPage.productSoldOrRendered_SREG008.sendKeys("Testing");
		sleep(2000);
		empPage.totalRevenue.sendKeys("400");
		sleep(2000);
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", " entered the details");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
 //-------SREG-007----------------*/

	
		try {
            try {
            	empPage.uspsBusinessAddress.click();
            } catch (Exception exception) {
            	empPage.uspsBusinessAddressInnerCircle.click();
            }
            commonFuntions.screenShot("EmpRegister10", "Pass", "USPS Business address selection on SREG-008");
            empPage.continueButton_popUp.click();
    } catch (Exception exception) {
    	commonFuntions.screenShot("EmpRegister10", "Pass", "USPS pop-up didnot come");
    }
		sleep(4000);
		commonFuntions.screenShot("Business Physical Address Details", "Pass", "Navigated to SREG-007 page");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		//-------SREG 011
		sleep(2000);
		commonFuntions.screenShot("Business Accusion ", "Pass", "Navigated to SREG-011 page");
		sleep(2000);
		commonFuntions.clickButton("Continue ");
		
		//-------SREG 012
				sleep(2000);
				commonFuntions.screenShot("Changes in Legal entity ", "Pass", "Navigated to SREG-012 page");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
		//------SREG -006	
				sleep(2000);
				commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", "Navigated to SREG-006 page");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Business Entity");
				sleep(2000);
				commonFuntions.enterTextboxContains("Entity Name", "Bussiness");
				sleep(2000);
				commonFuntions.selectDropdown("Title", " President ");
				sleep(2000);
				
				commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", "Navigated to SREG-006 page and entering the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("Address Line 1 ", "567HPKL");
				sleep(2000);
				commonFuntions.enterTextboxContains("City ", "NY");
				sleep(2000);
				commonFuntions.enterTextboxContains("Zip Code", "98602");
				sleep(2000);
				//commonFuntions.selectDropdown("County", " Albany ");
				//sleep(2000);
				commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", " SREG-006 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				try {
		            try {
		            	empPage.uspsBusinessAddress.click();
		            } catch (Exception exception) {
		            	empPage.uspsBusinessAddressInnerCircle.click();
		            }
		            commonFuntions.screenShot("EmpRegister", "Pass", "USPS Business address selection on SREG-006");
		            empPage.continueButton_popUp.click();
		    } catch (Exception exception) {
		    	commonFuntions.screenShot("EmpRegister", "Pass", "USPS pop-up didnot come");
		    }
				
			//------SREG 005
				
				commonFuntions.screenShot("Corporate Officer/Owner Details", "Pass", " SREG-005 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				sleep(2000);
				commonFuntions.clickButton("Continue ");
		
				
				//------SREG -006	
				sleep(2000);
				commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", "Navigated to SREG-006 page");
				sleep(2000);
				commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
				sleep(2000);
				commonFuntions.enterTextboxContains("Entity Name", "Private");
				sleep(2000);
				commonFuntions.selectDropdown("Title", " President ");
				sleep(2000);
				
				commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", "Navigated to SREG-006 page and entering the details");
				sleep(2000);
				commonFuntions.enterTextboxContains("First Name", "Sona");
				sleep(2000);
				commonFuntions.enterTextboxContains("Last Name", "Nema");
				sleep(2000);
				commonFuntions.enterTextboxContains("Address Line 1 ", "567HPKL");
				sleep(2000);
				commonFuntions.enterTextboxContains("City ", "Newyork");
				sleep(2000);
				commonFuntions.enterTextboxContains("Zip Code", "88602");
				sleep(2000);
				commonFuntions.screenShot("Add Corporate Officer/Owner", "Pass", " SREG-006 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
				
				try {
		            try {
		            	empPage.uspsBusinessAddress.click();
		            } catch (Exception exception) {
		            	empPage.uspsBusinessAddressInnerCircle.click();
		            }
		            commonFuntions.screenShot("EmpRegister", "Pass", "USPS Business address selection on SREG-006");
		            empPage.continueButton_popUp.click();
		    } catch (Exception exception) {
		    	commonFuntions.screenShot("EmpRegister", "Pass", "USPS pop-up didnot come");
		    }
				
			//------SREG 005
				
				commonFuntions.screenShot("Corporate Officer/Owner Details", "Pass", " SREG-005 page and entered the details");
				sleep(2000);
				commonFuntions.clickButton("Continue ");
						
				
				
				//------Completed by Palak
				
		
		
	
		
		
	}
}
