//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_14_002 extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100N sent to NYBE.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EE_14_002() throws Exception{
		
		test = report.createTest("Verify CSR is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100N sent to NYBE.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		
		//--- Menu Click ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("Menu", "Pass", "Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		
		//--- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
		commonFunction.enterTextboxContains("First Name", "");
		commonFunction.enterTextboxContains("Last Name", "");
		commonFunction.enterTextboxContains("Job Title", "");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", "");
		commonFunction.enterTextboxContains("Email Address", "");
		
		commonFunction.enterTextboxContains("First Name", "prashant");
		commonFunction.enterTextboxContains("Last Name", "Kumar");
		commonFunction.enterTextboxContains("Job Title", "test");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", "5164377377");
		commonFunction.enterTextboxContains("Email Address", "prash@gmail.com");
		commonFunction.screenShot("EmployerRegistraionPage", "Pass", "Entered all details and click on Continue");
		
		commonFunction.clickButtonContains("Continue ");
		commonFunction.screenShot("GeneralInformationPage", "Pass", "Launched to General Information(SREG-025) page");

		//--- SREG-025 ---
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "");
		commonFunction.enterTextboxContains("Employer Registration Number", "");
		
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "437289770");
		commonFunction.selectDropdown("Type of Legal Entity", " City ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0550599");
		commonFunction.screenShot("GeneralInformationPage", "Pass", "Entered all details and click on ");
		
		commonFunction.clickButtonContains("Continue ");
		commonFunction.screenShot("GeneralInformationPage", "Pass", "Launched to Employer Entity Information(SREG-003) page");
		
		//--- SREG-003 ---
		
		empRegPage.legalNameTextBox.sendKeys("XYZ Corp");
		commonFunction.enterTextboxContains("Other commonly known", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFunction.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFunction.enterTextboxContains("date of the first payroll","04/06/2022");
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals","360");
		commonFunction.enterTextboxContains("Date covered employment","04/06/2022");
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on SREG-003 page and click continue");
		sleep();
		commonFunction.safeJavaScriptClick(empRegPage.iSyourEntityQuestion_Yes);
		commonFunction.enterTextboxContains("If Yes, enter Legal Name of Entity", "Clothing");
		commonFunction.enterTextboxContains("Address Line 1 ", "60 Ave");
		commonFunction.enterTextboxContains("City ", "Albany");
		commonFunction.enterTextboxContains("Zip Code", "44673");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.screenShot("EmpRegister5", "Pass", "Enter the details on SREG-003 page and click continue");
		commonFunction.clickButton("Continue ");

		// --- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();;
		commonFunction.screenShot("EE14002", "Pass", "Sucessfully launched  SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister9", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		
		try {
				try {
					empRegPage.uspsBusinessAddress.click();
				} catch (Exception exception) {
					empRegPage.uspsBusinessAddressInnerCircle.click();
				}

				commonFunction.screenShot("EmpRegister10", "Pass", "USPS Business address selection on SREG-008");
				empRegPage.continueButton_popUp.click();
		} catch (Exception exception) {
				commonFunction.screenShot("EmpRegister10", "Pass", "USPS pop-up didnot come");
		}
		
		// --- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE14002", "Pass", "Sucessfully launched SREG-007 page");
		
		
		
		commonFunction.screenShot("FailurePage", "Fail", "Expected page not reached");
		System.out.println("xxx");
	}

}
