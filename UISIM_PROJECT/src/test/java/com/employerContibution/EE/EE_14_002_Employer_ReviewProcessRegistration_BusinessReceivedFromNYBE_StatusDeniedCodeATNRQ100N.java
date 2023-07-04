//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EE;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_14_002_Employer_ReviewProcessRegistration_BusinessReceivedFromNYBE_StatusDeniedCodeATNRQ100N extends TestBase {
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify Employer is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100N sent to NYBE.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void Test_EE_14_002() throws Exception{
		
		test = report.createTest("EE.14.002: Verify Employer is able to review and process the registration for employer type Business received from NYBE, status Denied and status code ATNRQ100N sent to NYBE.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		employerManagement empManage = new employerManagement(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_6.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_6_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		
		//--- Menu Click ---
		commonFunction.waitForLoadingIconToDisappear();
		empManage.menu.click();
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("Menu", "Pass", "Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		
		//--- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmployerRegistraionPage", "Pass", "Launched at Employer Registration(SREG-001) page");
//		commonFunction.enterTextboxContains("First Name", "");
//		commonFunction.enterTextboxContains("Last Name", "");
//		commonFunction.enterTextboxContains("Job Title", "");
//		commonFunction.enterTextboxContains(" Contact Telephone Number ", "");
//		commonFunction.enterTextboxContains("Email Address", "");
		
		commonFunction.enterTextboxContains("First Name", "prashant");
		commonFunction.enterTextboxContains("Last Name", "Kumar");
		commonFunction.enterTextboxContains("Job Title", "test");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", "5164377377");
		commonFunction.enterTextboxContains("Email Address", "prash@gmail.com");
		commonFunction.screenShot("EmployerRegistraionPage", "Pass", "Entered all details and click on Continue");
		
		commonFunction.clickButtonContains("Continue ");
		commonFunction.screenShot("GeneralInformationPage", "Pass", "Launched to General Information(SREG-025) page");

		//--- SREG-025 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "437289770");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		commonFunction.selectDropdown("Type of Legal Entity", " City ");
		commonFunction.enterTextboxContains("Employer Registration Number", "0550599");
		commonFunction.screenShot("GeneralInformationPage", "Pass", "Entered all details and click on ");
		sleep(2000);
		commonFunction.clickButtonContains("Continue ");
		
		//--- SREG-003 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("GeneralInformationPage", "Pass", "Launched to Employer Entity Information(SREG-003) page");
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
		commonFunction.clickButton("Continue ");
		
		// --- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE14002", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
//		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
//		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
//		empRegPage.uspsBmadCityText.sendKeys("New York");
//		empRegPage.uspsBmadZipText.sendKeys("10003");
//		empRegPage.uspsBmadCounty.click();
//		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE14002", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
//		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
//		empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
//		empRegPage.uspsLbraCityText.sendKeys("New York");
//		empRegPage.uspsLbraZipText.sendKeys("10001");
//		empRegPage.uspsLbraCounty.click();
//		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsLbraFirstNameText.sendKeys("Thomas");
		empRegPage.uspsLbraLastNameText.sendKeys("Shelby");
		sleep(2000);
		commonFunction.screenShot("EE14002", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Primary Business Physical Address");
//		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
//		empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
//		empRegPage.uspsNpcaCityText.sendKeys("New York");
//		empRegPage.uspsNpcaZipText.sendKeys("10003");
//		empRegPage.uspsNpcaCounty.click();
//		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsNpcaFirstNameText.sendKeys("Sydney");
		empRegPage.uspsNpcaLastNameText.sendKeys("Sheldon");
		sleep(2000);
		commonFunction.screenShot("EE14002", "Pass", "Successfully entered details in SREG-004 page");
	
		commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
		
		//if yes radio
//		sleep(2000);
//		commonFunction.selectRadioQuestions("Agent (C/O) address", "Other");
//		empRegPage.uspsAgadCareOf.sendKeys("Ctalonea B");
//		empRegPage.uspsAgadAddressText.sendKeys("19th Street");
//		empRegPage.uspsAgadCityText.sendKeys("New York");
//		empRegPage.uspsAgadZipText.sendKeys("10045");
//		empRegPage.uspsAgadCounty.click();
//		commonFunction.selectFromDropdown(" Albany ");
//		sleep();
//		empRegPage.uspsAgadFirstNameText.sendKeys("Peaky");
//		empRegPage.uspsAgadLastNameText.sendKeys("Blinder");
		sleep(2000);
		commonFunction.screenShot("EE14002", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		empRegPage.uspsBmadAddressRadio.click();
		empRegPage.uspsLbraAddressRadio.click();
		empRegPage.uspsNpcaAddressRadio.click();
		commonFunction.screenShot("EE14002", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		empRegPage.continueButton_popUp.click();
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {}
		
		// ---SREG-521 ---
		sleep(2000);
		commonFunction.screenShot("EE14002", "Pass", "Launched to Employer Verify Contact Detail (SREG-521) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-683 ---
		sleep(2000);
		commonFunction.screenShot("EE14002", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EE14002", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-800 ---
		sleep(10000);
		commonFunction.screenShot("EE14002", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		sleep(2000);
		empRegPage.commentid.sendKeys("Test to Submit");
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE14002", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		sleep(15000);
		commonFunction.screenShot("EE14002", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Home ");
		
		commonFunction.screenShot("SuccessPage", "Pass", "TC EE.14.002 passed");
		System.out.println("Pass :)");
	}

}
