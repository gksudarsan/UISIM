package com.employerContibution.EE;

import java.awt.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_05_001_CSR_Register_Indian_Tribe_Housing extends TestBase {
	
	@Test
	public void EE_05_001() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"EE.05.001 - Verify CSR can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Housing' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(4000);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
//		sleep(3000);
//		commonFuntions.validateNextPageNumber("SREG-001");
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		sleep();
		commonFuntions.screenShot("EmpRegister11", "Pass", "Entered the details and click on continue button");
		commonFuntions.clickButton("Continue ");
//		commonFuntions.validateNextPageNumber("SREG-025");
		
		/**************** SREG-025 ************************/
		
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		commonFuntions.selectDropdown("Employer Type", " Indian Tribe ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Housing Authority ");
		String feinValue =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("FEIN : : "+feinValue);
		test.log(Status.INFO, "FEIN : : "+ feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
//		sleep(3000);
//		commonFuntions.enterTextbox("Employer Registration Number", "0710282");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100IT ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		commonFuntions.clickButton("Continue ");
//		sleep(3000);
//		commonFuntions.validateNextPageNumber("SREG-003");
		
		/*-----------------SREG-003----------------*/
		
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister4", "Pass", "Required text displayed if user do not enter Legal Name");
		empPage.legalNameTextBox.sendKeys("sadjh sdf sdh  LLC");
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld", 109500 );
	
		commonFuntions.clickButton("Continue ");
		sleep();
		String  error = empPage.date_Payroll_Error_Message.getText();
		System.out.println(error);
		Assert.assertEquals(error , "Date Payroll Withheld is invalid");
		sleep();
		WebElement firstPayroll =  driver.findElement(By.xpath("//mat-label[contains(.,'What is the date of the first payroll')]//following::input[1]"));
		commonFuntions.forceClearText(firstPayroll);
		sleep();
		commonFuntions.enterPastDate("What is the date of the first payroll which you withheld", 180 );
		commonFuntions.enterTextboxContains("Estimated or approximate number of individuals", "760");
		commonFuntions.enterPastDate("Date covered employment began? ", 365);
		commonFuntions.clickButton("Continue ");
//		sleep(3000);
//		commonFuntions.validateNextPageNumber("SREG-008");
		
		/*-----------------SREG-008----------------*/
		commonFuntions.screenShot("EmpRegister5", "Pass", "Navigated to SREG-008 page and entering the details");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "10002");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		try {
			sleep(2000);
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_Cooper);
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		} catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		/*-----------------SREG-007----------------*/
		
//		sleep(4000);
		commonFuntions.validateNextPageNumber("SREG-007");
		
		commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated to SREG-007 page");
		commonFuntions.clickButton("Continue ");
		
		
		/*-----------------SREG-004----------------*/
		
//		sleep(3000);
//		commonFuntions.validateNextPageNumber(" SREG-004");
		commonFuntions.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
//		sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
//		sleep();
		commonFuntions.screenShot("TPRRegister8", "Pass", "Selected Same as Mailing for Location of Books and Records");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
//		sleep();
		commonFuntions.screenShot("TPRRegister9", "Pass", "Selected Same as Location of Books and Records for Notice of Potential Charges (LO400) Address");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-521----------------*/
//		sleep(3000);
		commonFuntions.validateNextPageNumber("SREG-521");
		commonFuntions.screenShot("EmpRegister10", "Pass", "Navigated to SREG-521 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-683----------------*/
//		sleep(3000);
		commonFuntions.validateNextPageNumber("SREG-683");
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-683 page and uploading the document");
		commonFuntions.safeJavaScriptClick(empPage.browserLink);
		commonFuntions.uploadDoc("Sample");
		sleep(4000);
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-800----------------*/
//		sleep(3000);
		commonFuntions.waitForLoadingIconToDisappear();
//		commonFuntions.validateNextPageNumber("SREG-800");
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated to SREG-800 page");
		commonFuntions.clickButton("Continue ");
		
		/*-----------------SREG-043----------------*/
//		sleep(3000);
		commonFuntions.validateNextPageNumber(" SREG-043");
		commonFuntions.screenShot("EmpRegister13", "Pass", "Navigated to SREG-043 page and click on I accept and submit");
		commonFuntions.selectCheckbox("I accept");
//		sleep();
		commonFuntions.clickButton("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		
		/*-----------------SREG-013----------------*/

		commonFuntions.screenShot("EmpRegister14", "Pass", "Navigated to SREG-013 page and click on exit");
		commonFuntions.clickButton("Exit ");
		/*-----------------Home Page----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(20000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
//		commonFuntions.clickOnLink("Review Employer Type");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.review_employer_My_Q);
//		sleep(4000);
		/*-----------------WF-091----------------*/
		commonFuntions.validateNextPageNumber("WF-091");
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		commonFuntions.clickButton("Open Work Item ");
//		sleep(4000);
		commonFuntions.waitForLoadingIconToDisappear();
		/*-----------------EEWI-002----------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		commonFuntions.selectDropdown("Account Status", " Erroneous ");
		commonFuntions.selectDropdown("Reason for Erroneous", " Stop Processing ");
		commonFuntions.enterCurrentDate("Date Covered Employment began? ");
		empPage.commentBox_MyQ.sendKeys("Random Queue");
		commonFuntions.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		commonFuntions.waitForLoadingIconToDisappear();
//		sleep(10000);
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SUC-002 page and click on Home");
		commonFuntions.clickButton("Home ");
		
	}
}
