package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_01_003_Csr_BusinessOther extends TestBase{

	@Test()
	public void EE_01_003_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		test = report.
				createTest("EE_01_003:Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'other' and work items will be created for CSR to review.");
		cf.login(COMMON_CONSTANT.CSR_USER_5.toUpperCase(), COMMON_CONSTANT.CSR_USER_5_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");sleep();
		cf.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		//cf.clickMenu("Menu"); sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); sleep();
		cf.clickButtonContains("Continue"); 
		sleep(3000);
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "FEIN"); 
		String FEIN = databaseResults.get("FEIN");
		System.out.println("FEIN NUMBER = " +FEIN);
		test.log(Status.INFO, "Fein::" + FEIN);
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC", "EAN"); 
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN NUMBER = " +EAN);
		test.log(Status.INFO, "Fein::" + EAN);

		/*----'General Information (SREG-025)----*/
		cf.screenShot("GeneralInfo","Pass", "Entering Details"); 
		cf.selectDropdown("Employer Type", " Business ");
		cf.enterTextboxContains("(FEIN)", FEIN); 
		cf.selectDropdown("*Type of Legal Entity","Other"); 
		cf.enterTextboxContains("If Other, provide the type of Legal Entity.", "abc test automation");
		cf.enterTextboxContains("Employer Registration Number", EAN);
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep();
		cf.selectDropdown("Source Type", " NYS-100 ");sleep();
		cf.screenShot("GeneralInfo1", "Pass", "General Information Details");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*------'Employer Entity Information (SREG-003)----*/
		cf.screenShot("EmployerEntityInfo", "Pass", "Entering Details Employer Entity Info");
		Map<String, String> databaseResults3 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME NOT IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd)","ENTITY_NAME");
		String legalName = databaseResults3.get("ENTITY_NAME");
		System.out.println("LegalName is: "+ legalName );
		AddPage.legalNameTextBox.sendKeys(legalName);
		cf.enterTextboxContains("Trade Name", "TestAutoCompany");
		cf.enterPastDate("Enter date of first operations in New York State", 730);
		cf.enterPastDate("What is the date of the first payroll", 730);
		cf.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes");
		cf.selectDropdown("Quarter", "2");sleep();
		cf.selectDropdown("Year", "2023");sleep();
		cf.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		cf.selectRadioQuestions("Are you registering to remit withholding tax only?", "Yes");
		cf.screenShot("EmployerEntityInfo1", "Pass", "Employer Entity Information");
		cf.clickButtonContains("Continue");
		sleep(3000);

		//clicking on finish later---entering data in incomplete registration
		cf.clickButtonContains(" Finish Later ");sleep();
		cf.screenShot("FinishLater", "Pass", "Finsh later pop up");
		cf.clickButtonContains(" Yes ");sleep(3000);
		cf.screenShot("HomePage", "Pass", "Back to home page");
		AddPage.menu.click();sleep();
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu1", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep();
		cf.screenShot("Menu2", "Pass", "Employer Registration");
		cf.clickMenu("Incomplete Registration"); sleep();
		cf.screenShot("SearchforFinishLaterApplications", "Pass", "Search with legal name");
		cf.enterTextboxContains("Legal Name of Business", legalName);
		cf.clickButtonContains(" Search ");sleep(2000);
		cf.screenShot("SearchResults", "Pass", "Search with legal name");
		cf.clickOnLinkAnchorTag(legalName);sleep(2000);
		cf.screenShot("EmpReg", "Pass", "EmpRegDetails");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.screenShot("GenInformationPage", "Pass", "General Information Details Page");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.screenShot("EEInformationPage", "Pass", "EE Information Details Page");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*----Add Primary Business Physical Address(SREG-008)-----*/
		cf.screenShot("AddPrimaryBussinessPhysicalAddress", "Pass", "Entering Deatail on Business Page");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99)+ "Cooper Square");
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","23263");
		cf.selectDropdown("County", "Albany");
		cf.selectDropdown("Principal Business Activity", "Manufacturing");sleep();
		AddPage.productsName.sendKeys("AutomationTesting");
		cf.enterTextboxContains("Percent of Total Sales Value", "50");
		AddPage.rawMaterialName.sendKeys("SteelManufacturingTest");
		cf.screenShot("AddPrimaryBussinessPhysicalAddressPage", "Pass", "Add Primary Bussiness Physical Address:SREG-008");
		cf.clickButtonContains("Continue");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep(3000);
		cf.screenShot("BusinessPhysicalAddressDetails", "Pass", "Business Physical Address Details:SREG-007");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*----Employer Contact Details(SREG-004)---*/
		cf.screenShot("EmpContactDeatails", "Pass", "Entering Details on Emp Contact Details");
		cf.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");sleep();
		cf.selectRadioQuestions("Location of Books and Records", "Other");
		driver.findElement(By.xpath("//input[@id='lbramailingAddressId_careOf']")).sendKeys("LEGACY");
		AddPage.addressLine1_Form2.sendKeys(cf.createRandomInteger(10, 99)+ "Cooper Square");
		AddPage.city_Form2.sendKeys("NY");
		AddPage.zipCode_Form2.sendKeys("45797");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("auto");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("motor");
		cf.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		driver.findElement(By.xpath("//input[@id='npcaAddressId_careOf']")).sendKeys("LEGACY");
		AddPage.addressLine1_Form3.sendKeys(cf.createRandomInteger(10, 99)+ "Cooper Square");
		AddPage.city_Form3.sendKeys("NY");
		AddPage.zipCode_Form3.sendKeys("54465");
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("bmw");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("motor");
		cf.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details (SREG-004)");
		cf.clickButtonContains("Continue");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress2);sleep();
			cf.safeJavaScriptClick(AddPage.uspsAddress3);sleep();
			cf.screenShot("VerifyAddress", "Pass", "Verify Address Pop-Up");
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		} catch (Exception e) {
			System.out.println("Employer Contact Details Addres Pop Up");
		}
		sleep(2000);
		cf.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*---"Business Acquisition (SREG-011)-----*/
		cf.screenShot("BussinessAquisition", "Pass", "Bussiness Aquisition(SREG-011)");
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		String BA_FEIN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println(BA_FEIN);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", BA_FEIN);
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99)+ "Cooper Square");
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","23263");
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		cf.enterTextboxContains("Acquisition Date", "1/1/2023");
		cf.enterTextboxContains("Notification date of Transfer", "4/1/2023");
		cf.screenShot("BusinessAcquisitionPages", "Pass", "Business Acquisition Details :SREG-011");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.screenShot("BusinessAcquisitionDetailsPages3", "Pass", "Business Acquisition Details :SREG-012");

		//Again adding business acquisition details 
		AddPage.clickOnLink(" Add Another Acquisition");sleep(2000);
		cf.screenShot("BussinessAquisitionOther", "Pass", "Bussiness Aquisition(SREG-011)");
		cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		String ERN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		System.out.println(ERN);
		cf.enterTextboxContains("Federal Employer Identification Number (FEIN)", BA_FEIN);
		cf.enterTextboxContains("Employer Registration Number", ERN);
		AddPage.legalNameOfBussiness.sendKeys("TestAuto");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10, 99)+ "Cooper Square");
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","23263");
		cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		cf.enterTextboxContains("Acquisition Date", "1/1/2023");
		cf.enterTextboxContains("Notification date of Transfer", "4/1/2023");
		cf.screenShot("BusinessAcquisitionPages1", "Pass", "Business Acquisition Details :SREG-011");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.screenShot("BusinessAcquisitionDetailsPages4", "Pass", "Business Acquisition Details :SREG-012");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*----Change in Legal Entity (SREG-012)--------*/
		cf.screenShot("ChangeinLegalEntity", "Pass", "Change in Legal Entity(SREG-012");
		cf.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		cf.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "241622287");
		cf.enterCurrentDate("Date of Notification");
		cf.screenShot("ChangeinLegalEntityDetails", "Pass", "Change in Legal Entity(SREG-012");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*----Add Corporate Officer/Owner (SREG-006)--------*/
		cf.selectRadioQuestions("Type of Corporate Officer/Owner", "Individual");
		cf.enterTextboxContains("First Name", "Test");
		cf.enterTextboxContains("Last Name", "AutoTest");
		cf.enterTextboxContains("Address Line 1", cf.createRandomInteger(10,99) +"Cooper Square");
		cf.enterTextboxContains("City","NY");
		cf.enterTextboxContains("Zip Code","13429");
		cf.screenShot("AddCorporateOfficer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
		cf.clickButtonContains("Continue");
		sleep(3000);
		try {
			cf.safeJavaScriptClick(AddPage.uspsAddress);
			cf.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch (Exception e) {
			System.out.println("USPS ADDRESS");
		}
		sleep();	
		cf.screenShot("CorporateOfficer/Owner Details", "Pass", "Corporate Officer/Owner Details(SREG-005))");
		cf.clickButtonContains("Continue");
		sleep(3000);

		/*-----Upload Documents -SREG-683------*/
		AddPage.browserLink.click();
		sleep(2000);
		cf.uploadDoc("TESTINGEL.docx");
		sleep(3000);
		cf.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		cf.clickButtonContains("Continue");
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.selectCheckbox("I accept");
		cf.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		cf.clickButtonContains("Submit");
		sleep(3000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation:SREG-013");
		cf.clickButtonContains("Home");
		sleep(5000);

		//Assigning user to DOL_DTF WI..................
		try {
		loginPage.okPopUpButton.click();
		sleep(2000);
		}catch(Exception e) {}
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_5+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)");

		//Resolving DOL_DTF WI................
		PEOPage.queue.click(); sleep();
		cf.waitForLoadingIconToDisappear();


	}
}