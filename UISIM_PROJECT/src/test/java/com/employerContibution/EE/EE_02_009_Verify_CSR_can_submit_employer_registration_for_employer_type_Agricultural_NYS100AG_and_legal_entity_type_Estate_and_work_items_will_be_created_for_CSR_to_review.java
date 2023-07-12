package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_02_009_Verify_CSR_can_submit_employer_registration_for_employer_type_Agricultural_NYS100AG_and_legal_entity_type_Estate_and_work_items_will_be_created_for_CSR_to_review extends TestBase{

	@Test()
	public void EE_02_009_Verify_CSR_can_submit_employer_registration_for_employer_type_Agricultural_NYS100AG_and_legal_entity_type_Estate_and_work_items_will_be_created_for_CSR_to_review() throws Exception {

		commonStepDefinitions commonFunction = new commonStepDefinitions();	
		 
		
		 
		//String EntityName = prop.getProperty("Entity");
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);


		test = report.createTest("EE_02_009_Verify_CSR_can_submit_employer_registration_for_employer_type_Agricultural_NYS100AG_and_legal_entity_type_Estate_and_work_items_will_be_created_for_CSR_to_review extends TestBase");

		test.log(Status.INFO, "Logging to the application");
		
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		
		commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");		
		commonFunction.clickMenu("Menu"); sleep();
		commonFunction.ScrollMenu("Employer Registration");sleep();
		commonFunction.screenShot("Menu", "Pass", "Employer Registration");
		commonFunction.clickMenu("Employer Registration");sleep(2000);
		commonFunction.screenShot("Menu", "Pass", "Employer Registration");
		commonFunction.clickMenu("Register Employer"); sleep(3000);
		commonFunction.clickButtonContains("Continue"); sleep(2000);

		commonFunction.selectDropdown("Employer Type", " Agricultural ");
		commonFunction.enterTextboxContains("(FEIN)", "99-9941203"); 
		commonFunction.screenShot("file1","Pass", "Searching with FEIN "); 
		commonFunction.selectDropdown("*Type of Legal Entity"," Estate "); 
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
		commonFunction.selectDropdown("Source Type", " NYS-100AG ");sleep(2000);
		commonFunction.screenShot("Menu", "Pass", "Employer Registration");
		commonFunction.clickButtonContains("Continue");sleep(2000);

		commonFunction.screenShot("Menu", "Pass", "Employer Registration");
		commonFunction.populateListbox("Legal Name", "FIVE SEVENTY SEVEN");sleep(2000);
		commonFunction.clickButtonContains("Continue");sleep();

		commonFunction.enterTextboxContains("Address Line 1","7th Street 40 E 7th St");sleep(2000);
		commonFunction.enterTextboxContains("Address Line 2","");
		commonFunction.enterTextboxContains("City","New York");sleep(2000);
		commonFunction.enterTextboxContains("Zip Code","10003");sleep(2000);

		//commonFunction.safeJavaScriptClick(empPage.uspsAddressRadio);
		sleep();
		commonFunction.screenShot("SREG-003", "Pass", "Employer Registration");

		commonFunction.clickButtonContains("Continue");sleep(2000);

		//commonFunction.clickButtonContains("Continue");
		commonFunction.screenShot("SREG-007", "Pass", "Employer Registration");
		commonFunction.clickButtonContains("Continue ");sleep();sleep(2000);

		commonFunction.selectRadio("Same as Primary Business Physical Address");sleep();

		commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		//empPage.addressLine1_Form2.sendKeys("street32");
		sleep();
		//empPage.city_Form2.sendKeys("Albany");
		sleep();
		//empPage.zipCode_Form2.sendKeys("45678");
		sleep();
		//empPage.countyDropDown_Form2.click();
		//empPage.countyValue_Form2.click();

		commonFunction.enterTextboxContains("First Name", "tghrth");
		commonFunction.enterTextboxContains("Last Name", "bdfhdhfh");
		commonFunction.enterTextboxContains(" Telephone Number ", "5544435678");

		//commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");

		//eml.selectradio_locationofbooks().click();sleep(2000);
		//eml.selectradio_noticeofpotentialcharges().click();sleep(2000);
		commonFunction.clickButtonContains("Continue ");sleep(2000);
		/*//commonFunction.selectLink("Documents to be uploaded or drag and drop files here", "Browse");
	     sleep(2000);
	     commonFunction.uploadDoc("Sample.docx");
	     sleep(2000);*/
		commonFunction.clickButtonContains("Continue ");sleep(2000);
		//commonFunction.clickButtonContains("Continue ");

		commonFunction.screenShot("Bussiness Aquisition", "Pass", "Bussiness Aquisition(SREG-011)");
		//commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "No ");
		;
		commonFunction.screenShot(" business ac", "Pass", " business ac");
		commonFunction.clickButtonContains("Continue ");	
		sleep(2000);
		commonFunction.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-012");
		commonFunction.selectRadioQuestions("Have you changed legal entity?", "No ");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.screenShot("SREG-006", "Pass", "Add executor page is displayed");
		commonFunction.enterTextboxContains("First Name", "Test");
		commonFunction.enterTextboxContains("Last Name", "AutoTest");
		commonFunction.enterTextboxContains("Address Line 1", "Ave"+ commonFunction.createRandomInteger(10,99));
		commonFunction.enterTextboxContains("City","NY");
		commonFunction.enterTextboxContains("Zip Code","13429");
		commonFunction.screenShot("Add Corporate Officer/Owner", "Pass", "Add Corporate Officer/Owner(SREG-006)");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		try {
			PEOPage.uspsAdd.click();
			commonFunction.screenShot("UspsAddress","Pass","UspsAddress");
			PEOPage.UspsContinueButton.click();
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("usps pop up dispalyed");
		}
		sleep(2000);
		commonFunction.screenShot("SREG-005", "Pass", "add executor confirm");
		commonFunction.clickButtonContains("Continue ");
		sleep(2000);
		commonFunction.selectLink("Supporting documents like", "Browse");
		sleep(3000);
		//D:\AutomationFiles\Sample.docx
		commonFunction.uploadDoc("Sample.docx");
	     sleep(2000);
		commonFunction.screenShot("Upload Documents", "Pass", "Upload Documents(SREG-683)");
		commonFunction.clickButtonContains("Continue");
		sleep(5000);
		commonFunction.screenShot("Review Registration Details", "Pass", "Review Registration Details(SREG-800)");
		commonFunction.clickButtonContains("Continue");
		sleep(3000);
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		commonFunction.clickButtonContains("Submit");
		sleep(2000);
		commonFunction.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		sleep(20000);
		commonFunction.clickButtonContains("Home");

		//Assigning user to WI Review emp type..................
		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='99-9941203' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

		//Resolving 1 WI................
		//PEOPage.queue.click(); Thread.sleep(15000);
		//commonFunction.enterTextboxContains("FEIN",FEIN);
		commonFunction.screenShot("FeinSearch","Pass","feinSearch");
		//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
		commonFunction.screenShot("review employer type","Pass","emp type");
		//commonFunction.clickOnLink("Review Employer Type");

		Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		commonFunction.screenShot("EEWI-002","Pass","review emp type  ");

		commonFunction.selectDropdown("*Account Status ", "Liable");
		commonFunction.enterTextboxContains("Comment", "registration in process");
		commonFunction.clickButtonContains("Submit "); Thread.sleep(2000);
		commonFunction.screenShot("GeneralInfo","Pass","General Information");
		commonFunction.clickButtonContains("Home");


		commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='99-9941203' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

		//Resolving 2ND WI ................
		PEOPage.queue.click(); Thread.sleep(15000);
		//commonFunction.enterTextboxContains("FEIN","99-9950123");
		commonFunction.screenShot("FeinSearch","Pass","feinSearch");
		//commonFunction.clickButtonContains("Search"); Thread.sleep(2000);
		commonFunction.screenShot("DOL DTF Discrepancy","Pass","emp type");
		//commonFunction.clickOnLink("DOL DTF Discrepancy");

		Thread.sleep(2000); commonFunction.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		commonFunction.screenShot("EEWI-005","Pass","DOL DTF ");
		commonFunction.selectDropdown("Quarter", "1");sleep();
		commonFunction.selectDropdown("Year", "2023");sleep();
		commonFunction.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		commonFunction.selectDropdown("*Account Status ", "Liable");
		commonFunction.enterTextboxContains("Comment", "registration in process");
		//commonFunction.clickButtonContains("Submit"); Thread.sleep(2000);
		commonFunction.screenShot("GeneralInfo","Pass","General Information");
		commonFunction.clickButtonContains("Home");

		//Verify Registered employer in Inquery page 	...........
	




	}
}