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

public class EE_08_004_Emp_Register_EmpType_NonProfit_JointVenture extends TestBase {

	String legalname= "EAST SIDE TRUCKING CO.";

	@Test
	public void EE_08_004() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		String feinValue1 =StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),8);
		String feinValue2 =  "3"  ;
		String feinValue = feinValue2 + feinValue1 ;  
		System.out.println("FEIN NUMBER = " +feinValue);
		System.out.println("Legal Name = " +legalname);
		
//		test.log(Status.INFO, "FEIN_2: : "+feinValue1);

		test = report.createTest(" EE.08.004 Verify employer can submit employer registration for employer type 'Non-Profit' and legal entity type 'Joint Venture' and work items will be created for CSR to review.");
		//Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
		//		"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "FEIN");
		//		String FEIN = databaseResults.get("FEIN");
		
		cf.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");sleep(2000);
		cf.sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("menu");
		cf.safeJavaScriptClick(empPage.employerRegisterMenu);
		cf.clickMenu("Register Employer");
		sleep(3000);
		
		// --- SREG-001 ---
       	cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-001 page");
		cf.enterTextboxContains("First Name", "sittest");
		cf.enterTextboxContains("Last Name", "lasttest");
		cf.selectDropdown("Suffix", " Sr. ");
		cf.enterTextboxContains("Job Title", "quality");
		cf.enterTextboxContains("Contact Telephone Number",Long.toString(cf.createRandomInteger(10000000,99999999))+Long.toString(cf.createRandomInteger(10,99)));
		cf.enterTextboxContains("Email Address","autoTest"+Long.toString(cf.createRandomInteger(10000,99999))+"@labor.ny.gov");
        sleep();
		cf.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
		cf.clickButton("Continue ");
		sleep(3000);

		// --- SREG-025 ---//
		cf.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		cf.selectDropdown("Employer Type", " Governmental ");
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)","132783409");
		cf.selectDropdown("Type of Legal Entity", " School District ");
		cf.enterTextboxContains("Employer Registration Number", "8006265");
		cf.selectDropdown("Source", " NYS-100 (paper) ");
		cf.selectDropdown("Source Type", " NYS-100G ");
		cf.screenShot("EmpRegister3", "Pass", "Details entered on SREG-025 page");
		cf.clickButton("Continue ");
		sleep(3000);
		/*----------------SREG-003----------------*/
		cf.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.clear();sleep();
		empPage.legalNameTextBox.sendKeys(legalname);
		//cf.enterTextboxContains("Other commonly known", "TESTING");
		cf.enterTextboxContains(" Business Phone Number  ", "3564777565");
		cf.enterTextboxContains(" Business Fax Number ", "9484735838");
		cf.enterTextboxContains("Enter date of first operations in New York State", "03032022");
		cf.selectDropdown("Quarter", "1");
		cf.selectDropdown("Year", "2023");
		cf.selectDropdown("What is the first calendar quarter and year you employed 4 (four) or more", "1");
		cf.selectDropdownSecondBox("What is the first calendar quarter and year you employed 4 (four) or more", "2023");
		cf.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for nonprofit employment, do you wish to elect voluntary coverage?", "No");
		cf.selectRadioQuestions("Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?", "No");

		//cf.safeJavaScriptClick(empPage.iSyourEntityQuestion_Yes);
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
		cf.selectDropdown("County", " New York ");
		cf.enterTextboxContains("Number of employees at this location", "20");
		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-007 and click continue");
		sleep(4000);
		cf.clickButton("Continue ");sleep(4000);

		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-007 and click continue");
		/*
		 * cf.selectRadioQuestions("Business Mailing Address", "Same"); sleep();
		 * empPage.addressLine1_Form1.sendKeys("Goddard Hall 80");
		 * empPage.city_Form1.sendKeys("New York");
		 * empPage.zipCode_Form1.sendKeys("10003");
		 * empPage.countyDropDown_Form1.click(); empPage.countyValue_Form1.click();
		 * 
		 * cf.selectRadioQuestions("Location of Books and Records", "Same");
		 * cf.screenShot("EmpRegister9", "Pass",
		 * "Selected other for - Location of Books and Records"); sleep(2000);
		 * empPage.addressLine1_Form2.sendKeys("40 Park View"); sleep();
		 * empPage.city_Form2.sendKeys("Albany"); sleep();
		 * empPage.zipCode_Form2.sendKeys("28287"); sleep();
		 * empPage.countyDropDown_Form2.click(); empPage.countyValue_Form2.click();
		 */
		cf.clickButton("Continue ");
		sleep(4000);
		cf.screenShot("EmpRegister7", "Pass", "Navigated on SREG-004 and click continue");
		
		cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
		sleep();
		cf.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		cf.enterTextboxContains("First Name", "loga");
		cf.enterTextboxContains("Last Name", "moris");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
		sleep();
		cf.enterTextboxContains("Contact Person for Notice of Potential Charges (LO400) Address", "loganaro");
		cf.enterTextboxContainsThirdBox("Contact Person for Notice of Potential Charges (LO400) Address", "morisaro");
		cf.screenShot("Employer Contact Details", "Pass", "Employer Contact Details:SREG-004");	
		sleep();
		cf.screenShot("EmpRegister10", "Pass", " displayed");
		sleep(4000);
		
		cf.clickButton("Continue ");

		sleep(4000);
		cf.screenShot("EmpRegister11", "Pass", "Navigated on SREG-521 page");
		cf.clickButton("Continue ");

		sleep(4000);
		cf.screenShot("EmpRegister12", "Pass", "Navigated SREG-011");
		
		cf.clickButton("Continue ");
		sleep(4000);
		cf.screenShot("EmpRegister12", "Pass", "Navigated SREG-006");
		cf.selectRadioQuestions("*Type of Corporate Officer/Owner", "Individual");
		cf.enterTextbox("SSN", "827263748");
		cf.enterTextboxContains("First Name", "Devid");
		cf.enterTextboxContains("Last Name", "Devidoare");
		cf.enterTextboxContains("Address Line 1 ", "23 Plaza");
		cf.screenShot("EmpRegister12", "Pass", "Navigated ");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34737");
		cf.selectDropdown("State", " New York ");
		cf.enterTextboxContains("Phone Number", "3647378839");
		cf.selectDropdown("Title", "President");
		cf.screenShot("EmpRegister12", "Pass", "Navigated SREG");
		cf.clickButton("Continue ");
		
	//	------------------------------
		
		
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
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		sleep(5000);
		cf.screenShot("EmpRegister16", "Pass", "Navigated to Home Page and click on My-Q");
		PEOPage.queue.click();
		sleep(25000);


		sleep();
		cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-001 page and open the work Item");
		cf.enterTextboxContains("FEIN", feinValue);
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
		test.log(Status.PASS, "Clicked on Home button");

	}
}
