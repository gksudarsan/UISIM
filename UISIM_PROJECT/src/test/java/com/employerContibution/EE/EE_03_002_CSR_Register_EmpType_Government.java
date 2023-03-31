package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_03_002_CSR_Register_EmpType_Government extends TestBase {

	@Test
	public void EE_03_002() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("EL.440.04- Verify CSR can update PEO Status 'Withdrawn' for PEO Individual Information");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
//		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister2", "Pass", "Navigated to __ Page");
		commonFuntions.selectDropdown("Employer Type", " Governmental ");
		sleep();
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", " Town ");
		commonFuntions.selectDropdown("Source", " NYS-100 (paper) ");
		commonFuntions.selectDropdown("Source Type", " NYS-100G ");
		sleep(3000);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		/*----------------SREG-003----------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("XYZ Corp");
		commonFuntions.enterTextboxContains("Other commonly known", "New Corp");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFuntions.safeJavaScriptClick(empPage.iSyourEntityQuestion_Yes);
		sleep();
		commonFuntions.screenShot("EmpRegister5", "Pass", "Enter the details on SREG-003 page and click continue");
		commonFuntions.enterTextboxContains("If Yes, enter Legal Name of Entity", "Clothing");
		commonFuntions.enterTextboxContains("Address Line 1 ", "60 Ave");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "44673");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");

		/*----------------SREG-008----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated on SREG-008 page and entering the address");
		commonFuntions.enterTextboxContains("Address Line 1 ", "23 Plaza");
		commonFuntions.enterTextboxContains("City ", "Albany");
		commonFuntions.enterTextboxContains("Zip Code", "34737");
		commonFuntions.clickButton("Continue ");

		/*----------------SREG-007----------------*/
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated on SREG-007 and click continue");
		sleep(4000);
		commonFuntions.clickButton("Continue ");

		/*----------------SREG-004----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister8", "Pass", "Navigated on SREG-008 and enter Both the address ");
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		sleep();
		empPage.addressLine1_Form1.sendKeys("50 Ave");
		empPage.city_Form1.sendKeys("Albany");
		empPage.zipCode_Form1.sendKeys("74747");
		empPage.countyDropDown_Form1.click();
		empPage.countyValue_Form1.click();

		commonFuntions.selectRadioQuestions("Location of Books and Records", "Other");
		commonFuntions.screenShot("EmpRegister9", "Pass", "Selected other for - Location of Books and Records");
		sleep(4000);
		empPage.addressLine1_Form2.sendKeys("40 Park View");
		sleep();
		empPage.city_Form2.sendKeys("Albany");
		sleep();
		empPage.zipCode_Form2.sendKeys("28287");
		sleep();
		empPage.countyDropDown_Form2.click();
		empPage.countyValue_Form2.click();
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio);
			sleep();
			commonFuntions.screenShot("EmpRegister10", "Pass", "Pop Up displayed");
			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
		

		/*----------------SREG-521----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated on SREG-521 page");
		commonFuntions.clickButton("Continue ");

		/*----------------SREG-683----------------*/
		sleep(4000);
		commonFuntions.screenShot("EmpRegister12", "Pass", "Navigated on SREG-683 page and upload document");
		empPage.browserLink.click();
		Thread.sleep(3000);
		commonFuntions.uploadDoc("Sample.docx");
		sleep(3000);
		commonFuntions.clickButton("Continue ");

		/*----------------SREG-800----------------*/
		sleep(10000);
		commonFuntions.screenShot("EmpRegister13", "Pass", "Navigated on SREG-800 and Accept");
		commonFuntions.clickButton("Continue ");
		commonFuntions.selectCheckbox("I accept");
		sleep();
		commonFuntions.screenShot("EmpRegister14", "Pass", "Click on submit button");
		commonFuntions.clickButtonContains("Submit ");
		sleep(15000);

		/*----------------SREG-521----------------*/
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to SREG-521 page and click on exit");
		commonFuntions.clickButtonContains("Exit ");

		/*----------------Home Page----------------*/
		sleep(4000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to Home Page and click on My-Q");
		PEOPage.queue.click();
		sleep(20000);

		/*----------------WF-001----------------*/
		sleep();
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to WF-001 page and open the work Item");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		sleep();
		commonFuntions.clickButtonContains(" Search ");
		sleep();
		commonFuntions.clickOnLink("Review Employer Type");
		sleep(5000);
		commonFuntions.clickButtonContains("Open Work Item ");

		/*----------------EEWI-002----------------*/
		sleep(3000);
		commonFuntions.screenShot("EmpRegister118", "Pass",
				"Navigated to EEWI page by opening the Work Item and add a comment and click submit");
		commonFuntions.enterTextboxContains("Comment", "Employer Tupe Gov");
		commonFuntions.clickButtonContains("Submit ");

		/*----------------SUC-002----------------*/

		sleep(3000);
		commonFuntions.clickButtonContains("Home ");

	}
}
