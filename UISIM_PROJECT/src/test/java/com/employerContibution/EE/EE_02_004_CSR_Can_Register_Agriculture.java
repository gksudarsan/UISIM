package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;
import com.ui.utilities.ConstantData;

import stepDefinitions.commonStepDefinitions;

public class EE_02_004_CSR_Can_Register_Agriculture extends TestBase{
	
	@Test
	public void EE_02_004() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage address = new AddressPage(driver);
		String feinValue1 =StringUtils.left( String.valueOf((long)(Math.random()*Math.pow(10,10))),5);
		  String feinvalue2 = "9999" ; 
		  String feinValue = feinvalue2 + feinValue1 ; System.out.println("FEIN NUMBER = "+feinValue);

		
		test = report
				.createTest("EE.02.004 -Verify CSR can submit employer registration for employer type 'Agricultural' and legal entity type 'Guardianship' and work items will be created for CSR to review.");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
cf.waitForLoadingIconToDisappear();
		
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");		
		cf.safeJavaScriptClick(empPage.menuButtonHomepage);
		sleep(2000);
		cf.clickMenu("Employer Registration");
		sleep();
		cf.clickMenu("Register Employer");
		sleep(3000);
		cf.clickButtonContains("Continue"); sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		
		/*------------------SREG-025----------------*/

		cf.selectDropdown("Employer Type", " Business ");
		cf.enterTextboxContains("(FEIN)", feinValue); 
		String ERN  = "000231902";
		cf.enterTextboxContains("Employer Registration Number", ERN);
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," Guardianship  Internal User Only "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
		cf.selectDropdown("Source Type", " NYS-100AG ");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*------------------SREG-003----------------*/
		String legalName = "NORTON LAW GROUP PLLC";
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.populateListbox("Legal Name", legalName);sleep(2000);
		cf.enterPastDate("Enter date of first operations in New York State", 366);
		cf.safeJavaScriptClick(empPage.firstCalender_Quater);
		cf.safeJavaScriptClick(empPage.firstCalender_Quater_Value_2);
		cf.safeJavaScriptClick(empPage.firstCalender_Year);
		cf.safeJavaScriptClick(empPage.firstCalender_Year_employed_4_value_2023);
		cf.enterDateOfCurrentQuaterFirstMonth("What is the date of the first payroll which you withheld (or will withhold) NYS");
		cf.enterTextboxContains("Total number of covered employees", "23");
		
		
		cf.clickButtonContains("Continue");sleep();
		cf.clickButton("Continue ");
		
		/*-----------------SREG-008----------------*/

		sleep(4000);
		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-008 page and entering the address");
		cf.enterTextboxContains("Address Line 1 ", "1 River RD");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "23432");
		cf.selectDropdown("County", " Albany ");
		cf.enterTextboxContains("Number of employees at this location", "45");
		cf.selectDropdown("Indicate your principal activity or farm production that produces the greatest gros", " Hog and Pig Farming ");
//		empPage.individualPrinciple.click();
//		empPage.individualPrinciple_value_other.click();
//		cf.selectDropdown("Indicate your principal activity or", " Other ");
//		PEOPage.otherDetails_New1.sendKeys("other1");
//		PEOPage.otherDetails1_New2.sendKeys("other2");
		cf.enterTextbox("Specify Type", "dshvfhs sdf ");
		
		
		cf.clickButton("Continue ");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-007----------------*/
		
		sleep(3000);
		cf.screenShot("EmpRegister8", "Pass", "Navigated to SREG-007 page");
		empPage.addAnotherBusinessLink.click();
		/*-----------------SREG-008----------------*/
		sleep(4000);
		cf.screenShot("EmpRegister9", "Pass", "Navigated on SREG-008 page and entering the address");
		cf.enterTextboxContains("Address Line 1 ", "20 cooper square");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "24986");
		cf.selectDropdown("County", " Allegany ");
//		cf.safeJavaScriptClick(empPage.individualPrinciple);
//		cf.safeJavaScriptClick(empPage.individualPrinciple_value_other);
//		cf.enterTextboxContains("Number of employees at this location", "23");
		cf.selectDropdown("Indicate your principal activity or farm production that produces the greatest gros", " Aquaculture/Other Animal Production ");
		cf.enterTextbox("Specify Type", "dshvfhs sdf d ");
//		cf.selectDropdown("Indicate your principal activity or", " Other ");
//		PEOPage.otherDetails_New1.sendKeys("other3");
//		PEOPage.otherDetails1_New2.sendKeys("other4");
		cf.clickButton("Continue ");
		try {
			cf.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-007----------------*/
		
		
		sleep(4000);
		cf.clickButton("Continue ");
		cf.screenShot("EmpRegister10", "Pass", "Navigated to SREG-007 page");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		empPage.addressLine1_Form1.sendKeys("20 cooper square");
		empPage.city_Form1.sendKeys("NY");
		empPage.zipCode_Form1.sendKeys("32424");
		empPage.countyDropDown_Form1_SREG_004.click();
		empPage.countyValue_Form1.click();
		
		cf.selectRadioQuestions("Location of Books and Records", "Other");
		
//		empPage.addresLine1_Form2_SREG_004.sendKeys("20 cooper");
//		cf.enterTextboxContains("Care Of", careOf);
		empPage.location_Of_Book_AddresLine1.sendKeys("2 River RD");
		empPage.location_Of_Book_City.sendKeys("NY");
		empPage.location_Of_Book_ZipCode.sendKeys("45643");
		cf.safeJavaScriptClick(empPage.location_Of_Book_County);
		sleep();
		cf.safeJavaScriptClick(empPage.countyDropDown_Form1_SREG_004);
		
		cf.enterTextboxContains("First Name", "Abhinav");
		cf.enterTextboxContains("Last Name", "jj");
		
		
		
		cf.clickButton("Continue ");
		
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.uspsCommonButton2);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-521----------------*/
		sleep(4000);
		cf.screenShot("EmpRegister11", "Pass", "Navigated to SREG-521 page");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-011----------------*/
		sleep(4000);
		cf.screenShot("EmpRegister12", "Pass", "Navigated to SREG-011 page");
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		/*-----------------Find Predecessor----------------*/
		
		/*-----------------Find Predecessor----------------*/
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		String ERN2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
//		System.out.println(ERN);
		System.out.println(feinValue);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		cf.enterTextboxContains("Employer Registration Number", ERN2);
		cf.enterTextboxContains("Legal Name of Business", "Other Enterprisea");
		
		cf.enterTextboxContains("Address Line 1 ", "20 cooper square new");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "24986");
//		cf.selectDropdown("County", " Allegancy ");
		cf.screenShot("EmpRegister13", "Pass", "Entering the form Details");
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
//		cf.enterTextboxContains("Acquisition Date", "04012023");
		cf.enterDateOfCurrentQuaterFirstMonth("Acquisition Date");
//		cf.enterTextboxContains("Notification date of Transfer", "04022023");
		cf.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Notification date of Transfer");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-012----------------*/
		sleep(4000);
		cf.screenShot("EmpRegister14", "Pass", "Navigated to SREG-012 page and click on continue");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-012----------------*/
		
		sleep(3000);
		cf.screenShot("EmpRegister15", "Pass", "Entering the form Details");
		cf.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		String feinValue3 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		String ERN3 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 7);
		cf.enterTextboxContains("Prior Federal Employer Identification Number (FEIN)", feinValue3);
		cf.enterTextboxContains("Prior Employer Registration Number", ERN3);
//		cf.enterTextboxContains("Date of Legal Entity change", "04032023");
		cf.enterDateOfCurrentQuaterFirstMonth("Date of Legal Entity change");
//		cf.enterTextboxContains("Date of Notification", "04042023");
		cf.enterDateOfCurrentQuaterFirstMonthPlusOneDay("Date of Notification");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-006----------------*/
		sleep(3000);
		cf.screenShot("EmpRegister16", "Pass", "Navigated to SREG-006 page and entering the form details");
		cf.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		
		cf.enterTextboxContains("SSN", "453758964");
		cf.enterTextboxContains("First Name", "Abhi");
		cf.enterTextboxContains("Last Name", "Jan");
		cf.selectDropdown("Title", " Officer ");
		
		cf.enterTextboxContains("Address Line 1 ", "4 River Rd");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "24944");
		cf.clickButton("Continue ");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-005----------------*/
		sleep(4000);
		cf.screenShot("EmpRegister17", "Pass", "Navigated to SREG-005 page");
		cf.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
		sleep(3000);
		cf.screenShot("EmpRegister18", "Pass", "Navigated to SREG-683 page and uploading the document");
		empPage.browserLink.click();
		sleep();
		cf.uploadDoc("Sample");
		sleep(3000);
		cf.clickButton("Continue ");
		/*-----------------SREG-800----------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister19", "Pass", "Navigated to SREG-800 page");
		cf.clickButton("Continue ");
		/*-----------------SREG-043----------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		cf.selectCheckbox("I accept");
		cf.clickButton("Submit ");

		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		/*-----------------SREG-013----------------*/
		cf.screenShot("EmpRegister21", "Pass", "Navigated to SREG-013 success page and click on exit");
		cf.clickButton("Exit ");
		
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		
		/*-----------------SREG-013----------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister22", "Pass", "Navigated to Home Page");
		PEOPage.queue.click();
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		sleep();
		cf.enterTextboxContains("FEIN", feinValue);
		cf.clickButton(" Search ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		empPage.review_employer_My_Q.click();
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.clickButton("Open Work Item ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		empPage.commentBox_MyQ.sendKeys("Random Q");
		cf.clickButton("Submit ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		/*----------------SUC-002----------------*/
		cf.validateNextPageNumber("SUC-002");
		
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister22", "Pass", "Navigated to Home Page");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.clickOnLinkAnchorTag("DOL DTF Discrepancy");
		sleep(4000);
		cf.clickButton("Open Work Item ");
		sleep(2000);
		/*----------------EEWI-005----------------*/
		String dolReason = empPage.dolFailedReasonText.getText();
		String dtfReason = empPage.dtfFailedReasonText.getText();
		
		Assert.assertEquals(ConstantData.EE_02_004_DOL_FAILED_REASON, dolReason);
		Assert.assertEquals(ConstantData.EE_02_004_DTF_FAILED_REASON, dtfReason);
		
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", "115341428");
		
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value_2022.click();
		
		cf.selectDropdown("Account Status", " Liable ");
		cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes ");
		
		empPage.commentBox_MyQ.sendKeys("sdfsfs");
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
