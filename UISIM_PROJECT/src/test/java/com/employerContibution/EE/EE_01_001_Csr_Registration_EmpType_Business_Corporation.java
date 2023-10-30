package com.employerContibution.EE;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;
import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_001_Csr_Registration_EmpType_Business_Corporation extends TestBase {

	@Test()
	public void EE_01_001_csr_registration() throws Exception {

		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		test = report.createTest(
				"EE_01_001 - Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Corporation (All types)' and work items will be created for CSR to review");
		commonFunction.login(COMMON_CONSTANT.CSR_USER_9.toUpperCase(), COMMON_CONSTANT.CSR_USER_9_PASSWORD);
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickMenu("Menu");
		sleep(10000);
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Register Employer");
		commonFunction.clickMenu("Register Employer");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-001 ---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-001 page");
		commonFunction.clickButton("Continue ");
        sleep();commonFunction.waitForLoadingIconToDisappear();
        
		// --- SREG-025 ---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-025 page");
		commonFunction.errorLabel(" Required ");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-025 ---
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "815318333");
		commonFunction.selectDropdown("Type of Legal Entity", " Corporation (All Types, includes Sub-Chapter S) ");
		commonFunction.enterTextboxContains("Employer Registration Number", "5426933");
		commonFunction.selectDropdown("Source Type", "--SELECT--");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmployerRegister", "Pass", "Source Type Required Message pop up");
		commonFunction.errorLabel(" Required ");
		
		// --- SREG-025 ---
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "Yes ");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmployerRegister1", "Pass", "prior ERN Required Message pop up");
		commonFunction.errorLabel("Required");
		
		// --- SREG-025 ---
		commonFunction.enterTextboxContains("Enter the prior Employer Registration Number.", "5426933");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister3", "Pass",
				"Message 'Previous NYS Employer Registration Number cannot be the same as the Employer Registration Number submitted on this application' on SREG-025 page");
		commonFunction.errorLabel(
				" Previous NYS Employer Registration Number cannot be the same as the Employer Registration Number submitted on this application");

		// --- SREG-025 ---
		commonFunction.selectRadioQuestions("Is this a Re-issue of Prior Employer Registration Number?", "No ");
		commonFunction.screenShot("EmpRegister2", "Pass", "Navigated to General Information(SREG-025) Page");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered on SREG-025 page");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-003 ---
		commonFunction.screenShot("EmpRegister4", "Pass", "Launched Employer Entity Information(SREG-003) page");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-003 page");

		// --- SREG-003 ---
		empRegPage.legalNameTextBox.sendKeys("Laksh Private Limited");
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFunction.enterTextboxContains(" Business Fax Number ", "3621231111");
		commonFunction.enterTextboxContains("Business Email Address", "shubhanshisahu@labor.ny.gov");
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "1/1/1799");
		commonFunction.enterTextboxContains(
				"What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from",
				"1/1/1799");
		commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
		commonFunction.selectDropdown("Year ", " 2022 ");
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?",
				"No ");
		commonFunction.enterTextboxContains("Total number of covered employees", "450");
		commonFunction.selectRadioQuestions("Are you registering to remit withholding tax only?", "No ");
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered and clicked on CONTINUE button");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.errorLabel(" Date of first operations is invalid");
		sleep();
		commonFunction.errorLabel(" Date Payroll Withheld is invalid");
		sleep();

		// --- SREG-003 ---
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "7/1/2023");
		commonFunction.enterTextboxContains(
				"What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from",
				"7/1/2023");
		commonFunction.selectDropdown("Quarter ", " 1 ");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information");

		// --- SREG-003 ---
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "7/1/2023");
		commonFunction.enterTextboxContains(
				"What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from",
				"7/1/2023");
		commonFunction.selectDropdown("Quarter ", " 1 ");
		sleep();
		commonFunction.selectDropdown("Year ", " 2021 ");
		sleep();
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmployerEntityInformation1", "Pass", "Employer Entity Information1");

		// --- SREG-003 ---
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "10/1/2018");
		commonFunction.enterTextboxContains(
				"What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from",
				"10/1/2023");
		commonFunction.selectDropdown("Quarter ", " 1 ");
		sleep();
		commonFunction.selectDropdown("Year ", " 2019 ");
		sleep();
		commonFunction.clickButton("Continue ");
		sleep(2000);
		try {
			commonFunction.screenShot("verifyDatePopUp", "Pass", "Verify date pop up");
			commonFunction.clickButtonContains(" No ");
			sleep(2000);
		} catch (Exception exception) {
		}
		commonFunction.clickButton("Continue ");
		sleep(2000);
		try {
			commonFunction.screenShot("verifyDatePopUp1", "Pass", "Verify date pop up1");
			commonFunction.clickButtonContains(" Yes ");
		} catch (Exception exception) {
		}
         sleep();commonFunction.waitForLoadingIconToDisappear();
         
		//SREG-008
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");		
		commonFunction.clickButtonContains("Previous ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01003", "Pass", "Sucessfully launched to SREG-003 page");
		commonFunction.enterTextboxContains("Enter date of first operations in New York State", "10/1/2022");
		commonFunction.enterTextboxContains(
				"What is the date of the first payroll which you withheld (or will withhold) NYS Income Tax from",
				"10/1/2022");
		commonFunction.selectDropdown("Quarter ", " 4 ");
		sleep();
		commonFunction.selectDropdown("Year ", " 2023 ");
		sleep();
		commonFunction.screenShot("EE01003", "Pass", "Message for fill details to SREG-003 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		commonFunction.waitForLoadingIconToDisappear();
		//step 23 - 30 is missing
		//SREG-008
		commonFunction.screenShot("EE01008", "Pass", "Do Not Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		
		//SREG-004
		commonFunction.screenShot("EE01004", "Pass", " Do Not Enter the details on SREG-004 and click continue");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01004", "Pass", "Required Error After Clicking Continue Button");
		commonFunction.clickButtonContains("Previous ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01008", "Pass", "Click Previous Button on SREG-004 to SREG-008");
		
		//SREG-008
		/*--1--*/
		commonFunction.enterTextboxContains("Address Line 2 ", "67 Cooper Sqaure");sleep();
		commonFunction.selectDropdown("Country", "--SELECT--");sleep();
		commonFunction.selectDropdown("Principal Business Activity", " Manufacturing ");sleep();
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01008ErrorMessage", "Pass", "Enter the details on SREG-008 and click continue");
		
		/*--2--*/
		commonFunction.enterTextboxContains("Address Line 1 ", "94 Cooper Sqaure");
		commonFunction.enterTextboxContains("Address Line 2 ", "S");
		commonFunction.selectDropdown("Country", " United States ");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("County", " Albany ");sleep();
		commonFunction.enterTextboxContains("Number of employees at this location", "100");
		commonFunction.selectDropdown("Principal Business Activity", " Other ");sleep();
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01008ErrorMessage2", "Pass", "Enter the details on SREG-008 and click continue");
		try {
			AddPage.uspsAddress.click();
			commonFunction.screenShot("verifypopUpAddress", "Pass", "Verify Pop Up Address");
			AddPage.continueButton_popUp.click();
		} 
		catch (Exception exception) {
		}sleep(2000);
		
		/*--3--*/
		commonFunction.enterTextboxContains("Address Line 1 ", "94 Cooper Sqaure");
		commonFunction.enterTextboxContains("Address Line 2 ", "");
		commonFunction.selectDropdown("Country", " Canada ");
		commonFunction.selectDropdown("State", " Alberta ");
		commonFunction.enterTextboxContains("City ", "NY");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("County", " Albany ");sleep();
		commonFunction.enterTextboxContains("Number of employees at this location", "100");
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01008ErrorMessage3", "Pass", "Enter the details on SREG-008 and click continue");
		try {
			AddPage.uspsAddress.click();
			commonFunction.screenShot("verifypopUpAddress", "Pass", "Verify Pop Up Address");
			AddPage.continueButton_popUp.click();
		} 
		catch (Exception exception) {
		}sleep(2000);
		
		/*--4--*/
		commonFunction.enterTextboxContains("Address Line 1 ", "94 Cooper Sqaure");
		commonFunction.selectDropdown("Country", " United States ");
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.enterTextboxContains("City ", "NY");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("County", " Albany ");sleep();
		commonFunction.enterTextboxContains("Number of employees at this location", "100");sleep();
		commonFunction.selectDropdown("Principal Business Activity", " Manufacturing ");sleep();
		empRegPage.Principal_Raw_Materials_Used.sendKeys("Testing");
	    empRegPage.Principal_Product_Sold_or_Service_Rendered.sendKeys("Testing");
		commonFunction.enterTextboxContains("Percent of Total Sales Value", "50");
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		try {
			AddPage.uspsAddress.click();
			commonFunction.screenShot("verifypopUpAddress", "Pass", "Verify Pop Up Address");
			AddPage.continueButton_popUp.click();
		} 
		catch (Exception exception) {
		}sleep(2000);

		// --- SREG-007 ---
		commonFunction.screenShot("EE01008", "Warning",
				"Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLinkAnchorTag(" Add Another Business Location ");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// ---SREG-008---(step 38)
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");sleep();
		commonFunction.enterTextboxContains("City ", "vatican city");sleep();
		commonFunction.enterTextboxContains("Zip Code", "10011");sleep();
		commonFunction.selectDropdown("Country", " Egypt ");sleep();
		commonFunction.selectDropdown("County", " Albany ");sleep();
		commonFunction.selectDropdown("Principal Business Activity",
				" Manufacturing ");sleep();
	    empRegPage.Principal_Product_Sold_or_Service_Rendered.sendKeys("Testing");
	    empRegPage.Principal_Raw_Materials_Used.sendKeys("Testing");
		commonFunction.enterTextboxContains("Percent of Total Sales Value", "90");
		commonFunction.screenShot("EE01008", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		sleep(2000);commonFunction.waitForLoadingIconToDisappear();
		try {
			AddPage.uspsAddress.click();
			commonFunction.screenShot("verifypopUpAddress", "Pass", "Verify Pop Up Address");
			AddPage.continueButton_popUp.click();
		} 
		catch (Exception exception) {
		}sleep(2000);
		
		// --- SREG-007 ---
		commonFunction.screenShot("EE01008", "Warning",
				"Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLinkAnchorTag("Edit");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// ---SREG-008---
		commonFunction.screenShot("EE01008", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-007 ---
		commonFunction.screenShot("EE01008", "Warning",
				"Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLinkAnchorTag("Delete");sleep();
		commonFunction.screenShot("EE01008", "Warning",
				"Pop up launch for Confirmation!Do you want to delete this location/address details?");
		commonFunction.clickButton(" No ");
		sleep(2000);
		commonFunction.screenShot("EE01008", "Warning",
				"Successful launch to Business Physical Address Details(SREG-007) page");
		commonFunction.clickOnLinkAnchorTag("Delete");
		commonFunction.screenShot("EE01008", "Warning",
				"Pop up launch for Confirmation!Do you want to delete this location/address details?");
		commonFunction.clickButton(" Yes ");
		sleep(2000);
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();

		// --- SREG-004 ---
		commonFunction.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		commonFunction.screenShot("EE01008", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.clickButton("Continue ");
		sleep();commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister3", "Pass", "Message enter Required field  on SREG-003 page");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);commonFunction.waitForLoadingIconToDisappear();
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		commonFunction.enterTextbox("First Name", "abc");
		commonFunction.enterTextbox("Last Name", "abc");
		commonFunction.enterTextbox(" Telephone Number ", "8269375089");
		sleep(2000);commonFunction.waitForLoadingIconToDisappear();
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		commonFunction.enterTextbox("First Name", "abc");
		commonFunction.enterTextbox("Last Name", "abc");
		commonFunction.enterTextbox(" Telephone Number ", "8269375089");
		commonFunction.screenShot("EE01008", "Pass", "Successfully entered details in SREG-004 page");
		sleep(2000);commonFunction.waitForLoadingIconToDisappear();
		commonFunction.clickButton("Continue ");
		sleep(2000);commonFunction.waitForLoadingIconToDisappear();

		try {
			empRegPage.bmad_Address.click();
		} catch (Exception exception) {
			empRegPage.bmad_AddressInnerCircle.click();
		}
		try {
			empRegPage.lbra_Address.click();
		} catch (Exception exception) {
			empRegPage.lbra_AddressInnerCircle.click();
		}
		try {
			empRegPage.npca_Address.click();
		} catch (Exception exception) {
			empRegPage.npca_AddressInnerCircle.click();
		}
		commonFunction.screenShot("EE01008", "Pass", "USPS Business address selection on SREG-004");
		empRegPage.continueButton_popUp.click();
		sleep(2000);

		// SREG-011 expected, SREG-521 coming.

		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");

		// --- SREG-011 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-011 page");
		commonFunction.clickButton("Continue ");

		// --- SREG-713 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-713 page");
		commonFunction.clickButton("Continue ");

		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");
		commonFunction.clickButton("Continue ");

		// --- SREG 683 ---
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		commonFunction.clickButton("Previous ");

		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");
		commonFunction.selectRadio("Individual");
		commonFunction.clickButton("Continue ");
		sleep();
		commonFunction.screenShot("EE01008", "Warning", "Message for enter Required field SREG-006 page");
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("First Name", "XYZ");
		commonFunction.enterTextboxContains("Last Name", "xyz");
		commonFunction.clickButton("Continue ");

		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickOnLink("Add Member/Managing Member Details");

		// --- SREG-006 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-006 page");
		commonFunction.selectRadio("Individual");
		commonFunction.enterTextboxContains("First Name", "XYZ");
		commonFunction.enterTextboxContains("Last Name", "xyz");
		commonFunction.clickButton("Continue ");

		// --- SREG-005 ---
		commonFunction.screenShot("EE01008", "Warning", "Launched to  SREG-005 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG 683 ---
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(
				" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.",
				"Browse");
		sleep(2000);
		commonFunction.uploadDoc("Sample.docx");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Previous ");
		sleep(10000);

		// --- SREG-800 ---
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		sleep(2000);

		// --- SREG-043 ---
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");

		// --- SREG-013 ---
	}
}