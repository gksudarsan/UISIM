package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;
@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_09_002_Employer_Can_Submit_Indian_Tribe extends TestBase {

	@Test
	public void EE_09_002() throws Exception {
		commonStepDefinitions cf = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		test = report.createTest(
				"EE.09.002 Verify employer can submit employer registration for employer type 'Indian Tribe' and legal entity type 'Business' and work items will be created for CSR to review.");

		cf.login(COMMON_CONSTANT.EMPLOYER_USER_9.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_9_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		//cf.safeJavaScriptClick(empPage.menuButtonHomepage);
		cf.clickMenu("Menu");
		sleep();
		cf.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep();
		cf.clickMenu("Register Employer");
		sleep(3000);
		cf.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		cf.enterTextboxContains("First Name", "Tom");
		cf.enterTextboxContains("Last Name", "Willam");
		cf.enterTextboxContains("Job Title", "Tester");
		cf.enterTextboxContains("Email Address", "test@Test.com");
		cf.enterTextboxContains(" Contact Telephone Number ", "9898876765");
		sleep();
		cf.clickButton("Continue ");

		/*---------------SREG-025--------------*/
		sleep();cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister2", "Pass", "Navigated to SREG-025 page and enter the details");
		cf.selectDropdown("Employer Type", " Indian Tribe ");
		sleep(2000);
		cf.selectDropdown("Type of Legal Entity", " Business ");
		/*---------------FEIN--------------*/
		// "SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd EXCEPT SELECT FEIN FROM
		// T_EMPLOYER_ACCOUNT tea"
		String FEIN = "134126708";
		System.out.println(FEIN);
		test.log(Status.INFO, "FEIN : : " + FEIN);
		/*---------------FEIN--------------*/
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", FEIN);
		cf.clickButton("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		/*---------------SREG-003--------------*/

		/*---------------Legal Name--------------*/
		// SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT IN (SELECT
		// LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)
		String legalName = "STINSON SERVICES LLC";
		/*---------------Legal Name--------------*/

		cf.screenShot("EmpRegister222", "Pass", "Navigated to SREG-003");
		cf.forceClearText(empPage.legalNameTextBox);
		empPage.legalNameTextBox.sendKeys(legalName);
		test.log(Status.INFO, "Legal Name  : : " + legalName);
		cf.enterTextboxContains(" Business Phone Number  ", "7687765665");
//		cf.enterTextboxContains("What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your Employee's pay?", "08012023");
		cf.enterPastDate(
				"What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from your",
				280);
		sleep();
		cf.selectRadioQuestions(
				"Are you a subdivision, subsidiary or business enterprise wholly owned by a federally recognized Indian Tribe?",
				"Yes ");
		sleep();
		cf.enterTextbox("Enter the name of the federally recognized Indian Tribe.", "Naruto Uzumaki ww");
		sleep();
//		cf.safeJavaScriptClick(empPage.Choose_Option_Reim_Radio);
		cf.selectRadioQuestions("Financing Method", "Reimbursable");
		sleep();
//		cf.selectRadioQuestions("Financing Method", "Reimbursable");
		cf.enterTextboxContains("Estimated or approximate number of individuals", "25");
//		cf.enterTextbox("Date covered employment began? ", "01012018");
		cf.enterPastDate("Date covered employment began? ", 1880);
		cf.clickButton("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();

		/*---------------SREG-008--------------*/
		try {
			sleep();
			cf.waitForLoadingIconToDisappear();
			cf.enterTextboxContains("Address Line 1 ", "4 River Rd");
			cf.enterTextboxContains("City ", "NY");
			cf.enterTextboxContains("Zip Code", "24944");
			cf.selectDropdown("County", " Albany ");
			cf.clickButton("Continue ");
			sleep(3000);
		}catch(Exception e) {
			cf.clickButton("Continue ");
		}
		sleep(2000);
		try {
			AddPage.uspsAddress.click();
			sleep();
			empPage.continueButton_popUp.click();
		}catch(Exception e ) {
			System.out.println("Pop up not displayed");
		}
		
		/*---------------SREG-007--------------*/
		cf.screenShot("EmpRegister223", "Pass", "Navigated to SREG-007");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.clickButton("Continue ");

		/*---------------SREG-004--------------*/
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister224", "Pass", "Navigated to SREG-004");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		cf.enterTextboxContains("Address Line 1 ", "1 Madion AVE");
		cf.enterTextboxContains("City ", "NY");
		cf.enterTextboxContains("Zip Code", "23432");
		cf.selectDropdown("County", " Albany ");
		cf.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		cf.enterTextboxContains("First Name", "Abhinav");
		cf.enterTextboxContains("Last Name", "jj");
		sleep();
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		cf.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
		sleep(2000);
		cf.selectRadioQuestions("Agent (C/O) address", "Other");
		sleep();
		cf.selectRadioQuestions("Agent (C/O) address", "Same as Notice of Potential Charges");
		sleep();
		cf.selectRadioQuestions("Agent (C/O) address", "Other");
		String careOf = "aadvhsbhdbsjh" + StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9)
				+ "";
		empPage.agentCOAddress_CareOf.sendKeys(careOf);
		sleep();
		cf.forceClearText(empPage.agent_CO_AddresLine1);
		sleep(3000);
		cf.forceClearText(empPage.agent_CO_City);
		sleep(3000);
		cf.forceClearText(empPage.agent_CO_ZipCode);
		sleep(3000);
		cf.forceClearText(empPage.agent_CO_City);
		sleep(4000);
		empPage.agent_CO_AddresLine1.sendKeys("50 Madison Ave");
		sleep(2000);
		empPage.agent_CO_City.sendKeys("Albany");
		empPage.agent_CO_ZipCode.sendKeys("45456");
//		cf.safeJavaScriptClick(empPage.agent_CO_County);
//		sleep(2000);
//		empPage.countyDropDown_Form1.click();
		empPage.agent_Co_First_Name.sendKeys("sdshd");
		empPage.agent_Co_Last_Name.sendKeys("gfshdj");

		cf.clickButton("Continue ");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister225", "Pass", "USPS Pop up");
		try {
			AddPage.uspsAddress4.click();
			sleep();
			AddPage.adderessRadioButton1.click();
			sleep();
		    empPage.continueButton_popUp.click();
		} catch (Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(3000);
		try {
			cf.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "Yes ");
		} catch (Exception e) {
			System.out.println("Error not displayed");
		}

		/*---------------SREG-521--------------*/

		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister226", "Pass", " Navigated to SREG-521");
		cf.clickButton("Continue ");

		/*---------------SREG-683--------------*/
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister227", "Pass", " Navigated to SREG-683");
		cf.clickButton("Continue ");

		/*---------------SREG-800--------------*/
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister228", "Pass", " Navigated to SREG-800");
		cf.clickButton("Continue ");
		sleep();
		cf.waitForLoadingIconToDisappear();
		//cf.clickButton("Continue ");
		/*-----------------SREG-043----------------*/
		sleep(2000);
		//cf.waitForLoadingIconToDisappear();
		
		cf.selectCheckbox("I accept");sleep();
		cf.screenShot("EmpRegister20", "Pass", "Navigated to SREG-043 page and accept the form and submit");
		cf.clickButton("Submit ");
		/*-----------------SREG-013----------------*/
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmpRegister229", "Pass", " Navigated to SREG-013 success screen");
		cf.validateNextPageNumber(" SREG-013");
		cf.screenShot("EmpRegister21", "Pass", "Navigated to SREG-013 sucess page");
		
		cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_9.toUpperCase(), COMMON_CONSTANT.CSR_USER_9_PASSWORD);
		sleep(10000);
		PEOPage.queue.click();
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		cf.enterTextbox("FEIN", FEIN);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep();
		empPage.review_employer_My_Q.click();
		sleep(3000);
		
		/*-----------------WF-091----------------*/
		
		cf.screenShot("EmpRegister17", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		cf.clickButton("Open Work Item ");
		sleep(3000);
		/*-----------------EEWl-002----------------*/
		cf.screenShot("EmpRegister18", "Pass", "Entering comment and click on submit");
		empPage.commentBox_MyQ.sendKeys("test QA ops");
		cf.enterCurrentDate("Date Covered Employment began? ");
		cf.clickButton("Submit ");
		
		/*-----------------SUC-002----------------*/
		cf.screenShot("EmpRegister19", "Pass", "Employer review work item submitted");
		cf.clickButton("Home ");
		
		/*-----------------Home Page----------------*/
		cf.waitForLoadingIconToDisappear();
		sleep(25000);
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");
		cf.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextbox("FEIN", FEIN);
		cf.clickButton(" Search ");
		cf.screenShot("EmpRegister16", "Pass", "Searched the FEIN and click on review employer type item");
		sleep(2000);
		empPage.obtain_bond_task_My_Q.click();
		sleep(3000);
		
		/*-----------------WF-091----------------*/
		
		cf.screenShot("EmpRegister122", "Pass", "Navigated to WF-091 page and click on Open Work Item");
		cf.clickButton("Open Work Item ");
		sleep(3000);
		

	}
}
