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

public class EE_07_005_Emp_Register_EmpType_Governmental_County extends TestBase {

	@Test
	public void EE_07_005() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest(" EE.07.005- Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'County' and work items will be created for CSR to review.");
		/*
		 * Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
		 * , "FEIN"); String FEIN = databaseResults.get("FEIN");
		 * System.out.println("FEIN NUMBER = " +FEIN);
		 * 
		 * Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
		 * "SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN NOT IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
		 * , "EAN"); String EAN = databaseResults1.get("EAN");
		 * System.out.println("EAN NUMBER = " +EAN);
		 */
		
		String feinValue1 =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),8);
		String feinValue2 =  "9"  ;
		String FEIN = feinValue2 + feinValue1 ;  
		System.out.println("FEIN NUMBER = " +FEIN);

		cf.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");

		cf.safeJavaScriptClick(empPage.employerRegisterMenu);
		cf.clickMenu("Register Employer");
		sleep(3000);
		cf.screenShot("EmpRegister1", "Pass", " Employer Register page");
		cf.clickButton("Continue ");
		sleep(3000);
		cf.screenShot("EmpRegister2", "Pass", "Emp Page");
		cf.selectDropdown("Employer Type", " Governmental ");
		sleep();

		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		cf.selectDropdown("Type of Legal Entity", " County ");
		//cf.enterTextbox("If Other, provide the type of Legal Entity.", "oshsh");
		cf.enterTextboxContains("Employer Registration Number", "");
		sleep(3000);
		cf.clickButton("Continue ");
		cf.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		/*----------------SREG-003----------------*/
		cf.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		
		empPage.legalNameTextBox.sendKeys("Entity Found");
		//cf.enterTextboxContains("Other commonly known", "HJGHHFH");
		cf.enterTextboxContains(" Business Phone Number  ", "3564777565");
		cf.enterTextboxContains(" Business Fax Number ", "9484735838");
		cf.enterTextboxContains("What is the date of the first payroll", "03032022");
		cf.enterTextboxContains("Estimated or approximate number of individuals", "20");
		cf.enterTextboxContains("Date covered employment began?", "03032022");
		cf.safeJavaScriptClick(empPage.iSyourEntityQuestion_No);
		sleep();
		cf.screenShot("EmpRegister5", "Pass", "Enter the details on SREG-003 page and click continue");
		//cf.enterTextboxContains("If Yes, enter Legal Name of Entity", "abc");
		//cf.enterTextboxContains("Address Line 1 ", "7th Street 40");
		//cf.enterTextboxContains("City ", "New York");
		//cf.enterTextboxContains("Zip Code", "44673");
		//cf.selectDropdown("County", " New York ");
		cf.clickButton("Continue ");
		
		sleep(4000);
		cf.screenShot("EmpRegister6", "Pass", "Navigated on SREG-008 page and entering the address");
		cf.enterTextboxContains("Address Line 1 ", "23 Plaza");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34737");
		cf.clickButton("Continue ");


		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-007 and click continue");
		sleep(4000);
		cf.clickButton("Continue ");


		sleep(4000);
		cf.screenShot("EmpRegister8", "Pass", "Navigated on SREG-008 and enter Both the address ");
		cf.selectRadioQuestions("Business Mailing Address", "Same");
		sleep();
		/*
		 * empPage.addressLine1_Form1.sendKeys("Goddard Hall 80");
		 * empPage.city_Form1.sendKeys("New York");
		 * empPage.zipCode_Form1.sendKeys("10003");
		 * empPage.countyDropDown_Form1.click(); empPage.countyValue_Form1.click();
		 */

		cf.selectRadioQuestions("Location of Books and Records", "Same");
		cf.screenShot("EmpRegister9", "Pass", "Selected other for - Location of Books and Records");
		sleep(2000);
		/*
		 * empPage.addressLine1_Form2.sendKeys("40 Park View"); sleep();
		 * empPage.city_Form2.sendKeys("Albany"); sleep();
		 * empPage.zipCode_Form2.sendKeys("28287"); sleep();
		 * empPage.countyDropDown_Form2.click(); empPage.countyValue_Form2.click();
		 */
		cf.clickButton("Continue ");
		sleep(4000);

		cf.safeJavaScriptClick(empPage.uspsAddressRadio);
		sleep();
		cf.screenShot("EmpRegister10", "Pass", "Pop Up displayed");
		cf.safeJavaScriptClick(empPage.continueButton_popUp);



		sleep(4000);
		cf.screenShot("EmpRegister11", "Pass", "Navigated on SREG-521 page");
		cf.clickButton("Continue ");


		sleep(4000);
		cf.screenShot("EmpRegister12", "Pass", "Navigated on SREG-683 page and upload document");
		empPage.browserLink.click();
		Thread.sleep(3000);
		cf.uploadDoc("Sample.docx");
		sleep(3000);
		cf.clickButton("Continue ");


		sleep(10000);
		cf.screenShot("EmpRegister13", "Pass", "Navigated on SREG-800 and Accept");
		cf.clickButton("Continue ");
		cf.selectCheckbox("I accept");
		sleep();
		cf.screenShot("EmpRegister14", "Pass", "Click on submit button");
		cf.clickButtonContains("Submit ");
		sleep(15000);

		cf.screenShot("EmpRegister15", "Pass", "Navigated to SREG-521 page and click on exit");
		cf.clickButtonContains("Exit ");

		sleep(4000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		cf.screenShot("EmpRegister16", "Pass", "Navigated to Home Page and click on My-Q");
		PEOPage.queue.click();
		sleep(25000);

		sleep();
		cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-001 page and open the work Item");
		cf.enterTextboxContains("FEIN", FEIN);
		sleep();
		cf.clickButtonContains(" Search ");
		sleep(4000);
		cf.clickOnLink("Review Employer Type");
		sleep(5000);
		cf.clickButtonContains("Open Work Item ");


		sleep(3000);
		cf.screenShot("EmpRegister18", "Pass",
				"Navigated to EEWI page by opening the Work Item and add a comment and click submit");
		empPage.commentBox_MyQ.sendKeys("Employer Tupe Gov");
		sleep(3000);
		cf.clickButtonContains("Submit ");


		sleep(3000);
		cf.screenShot("EmpRegister19", "Pass",
				"Sucessfully clicked on Submit button and navigated to SUC-002 Page");
		cf.clickButtonContains("Home ");

	}
}
