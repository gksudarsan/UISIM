package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_11_004_TPR_UserType extends TestBase{

	@Test
	public void EE_11_004() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		
		test = 
				report.createTest("EE.11.004:Verify TPR can submit employer registration for employer type 'Non-Profit' and legal entity type 'Joint Venture' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		AddPage.menu.click();sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");sleep();
		
		/*---Employer Registration---SREG-001*/
		commonFuntions.enterRandomString("First Name");
		commonFuntions.enterRandomString("Last Name");
		commonFuntions.enterTextboxContains("Job Title", "tprusertest");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("EmployerRegistrationDetailsPage", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		
		/*---General Information---SREG-025*/
		commonFuntions.selectDropdown("Employer Type", "Non-Profit");
		//		Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_BY DESC", 
		//				"FEIN");
		//		String feinValue = databaseResults.get("FEIN"); 
		String feinValue = prop.getProperty("FeinPresentInDolDtf");
		System.out.println("The FEIN Value is:"+ feinValue);
		test.log(Status.INFO, "Fein::" + feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "Joint Venture");
		//		Map<String, String> databaseResults1 = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_BY DESC", 
		//				"EAN");
		//		String eanValue = databaseResults1.get("EAN");
		String ernValue = prop.getProperty("ErnPresentInDolDtf");
		test.log(Status.INFO, "Ern::" + ernValue);
		System.out.println("The ERN Value is:"+ ernValue);
		commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("GeneralInformationPage", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		
		/*---Employer Entity Information---SREG-003*/
		commonFuntions.enterRandomStringLegalName("Legal Name");
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Business Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.enterPastDate("Enter date of first operations", 547);
		commonFuntions.selectDropdown("Quarter", "1");
		commonFuntions.selectDropdown("Year", "2022");
		commonFuntions.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No");
		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for nonprofit employment, do you wish to elect voluntary coverage?", "Yes");
		commonFuntions.selectRadioQuestions("Does this organization have, or have they applied for, a Nonprofit 501 (c)(3) exemption with the Internal Revenue Service?", "Yes");
		commonFuntions.screenShot("EmployerEntityInformation", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();

		/*---Add Primary Business Physical Address---SREG-008*/
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City", "New York");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"12");
		commonFuntions.selectDropdown("County", "Albany");
		commonFuntions.enterTextboxContains("Number of employees at this location", "50");
		commonFuntions.enterRandomString("Name of Government Agency");
		commonFuntions.screenShot("AddPrimaryBusinessPhysicalAddress", "Pass", "Add Primary Business Physical Address:SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			 PEOPage.uspsAdd.click();sleep();
			 AddPage.continueButton_popUp.click();	
		    }
		catch (Exception e) {
			System.out.println("Add Primary Business Physical Address Pop Up");
		}
		sleep(2000);
		
		/*---Business Physical Address Details---SREG-007*/
		commonFuntions.screenShot("BusinessPhysicalAddressDetails", "Pass", "Bussiness Physical Address Details:SREG-007");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		
		/*---Employer Contact Details---SREG-004*/
		commonFuntions.selectRadioQuestions("Business Mailing Address", "Other");
		AddPage.addressLine1_Form1.sendKeys(commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		AddPage.city_Form1.sendKeys("NY");
		AddPage.zipCode_Form1.sendKeys("39894");
		AddPage.countyDropDown_Form1.click();
		AddPage.countyValue1.click();sleep();
		commonFuntions.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		AddPage.firstName_locationOfBooksAndrecords.sendKeys("DJ");
		AddPage.lastName_locationOfBooksAndrecords.sendKeys("AJ");
		commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		AddPage.addressLine1_Form3.sendKeys(commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		AddPage.city_Form3.sendKeys("NEW YORK");
		AddPage.zipCode_Form3.sendKeys("54465");
		AddPage.countyDropDown_Form3.click();
		AddPage.countyValue3.click();sleep();
		AddPage.firstName_noticeOfPotentialCharges.sendKeys("James");
		AddPage.lastName_noticeOfPotentialCharges.sendKeys("Ueor");
		commonFuntions.screenShot("EmployerContactDetails", "Pass", "Employer Contact Details:SREG-004");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			AddPage.adderessRadioButton1.click();
			sleep();
			AddPage.adderessRadioButton2.click();
			sleep();
			AddPage.adderessRadioButton3.click();
			sleep();
			commonFuntions.screenShot("VerifyAddressPageDetails", "Pass", "Verify Address PopUp");
			commonFuntions.safeJavaScriptClick(AddPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Th popup addres details populated");
		}
		sleep(2000);
		commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();

		/*---Employer Verify Contact Details---SREG-521*/
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();

		/*---Business Acquisition---SREG-011*/
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition:SREG-011");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();

		/*---Add Corporate Officer/Owner---SREG-006*/
		commonFuntions.selectRadioQuestions("Type of Corporate Officer/Owner", "Business Entity");
		commonFuntions.enterRandomString("Entity Name");
		String FEIN = StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
		System.out.println("The FEIN Value is:"+ FEIN);
		commonFuntions.enterTextboxContains("Federal Identification Number (FEIN)", FEIN);
		commonFuntions.selectDropdown("Title", "Vice President");
		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
		commonFuntions.enterTextboxContains("City", "NEW YORK");
		commonFuntions.enterTextboxContains("Zip Code", commonFuntions.createRandomInteger(100, 999)+"01");
		commonFuntions.enterTextboxContains("Contact Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.screenShot("AddCorporateOfficer", "Pass", "Add Corporate Officer/Owner: SREG-006");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			 PEOPage.enteredAddress.click();sleep();
			 AddPage.continueButton_popUp.click();
			
		}catch (Exception e) {
			System.out.println("Add Corporate Officer/Owner pop up is displyed");
		}
		sleep(2000);
		
		/*---Corporate Officer/Owner Details---SREG-005*/
		commonFuntions.screenShot("AddCorporateOfficerDetails", "Pass", "Add Corporate Officer/Owner Details: SREG-005");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();

		/*---Upload Documents---SREG-683*/
		AddPage.browserLink.click();
		sleep(3000);
		commonFuntions.uploadDoc("Sample");
	    sleep(4000);
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButtonContains("Continue");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.selectCheckbox("I accept");
		commonFuntions.screenShot("StatementOfAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButtonContains("Submit");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass",
				"Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButtonContains("Home");
        sleep(60000);
        
		//commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(5000);
		
		//Resolving WI Review employer type................
		PEOPage.queue.click(); 
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.enterTextboxContains("FEIN",feinValue);
		commonFuntions.screenShot("FeinSearch","Pass","FeinSearch");
		commonFuntions.clickButtonContains("Search");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("IndividualWorkQueueReviewWorkItem","Pass","Individual Work Queue Review");
		sleep(2000);
		commonFuntions.clickOnLinkAnchorTag("Review Employer Type");
		sleep();commonFuntions.waitForLoadingIconToDisappear(); 
		commonFuntions.screenShot("WorkItemDetails","Pass","Work Item Details");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewEmployerTypeTaskDetails","Pass","Review Employer Type Task Details");
		PEOPage.selectQuarter.sendKeys("4");
		sleep();
		PEOPage.selectYear.sendKeys("2023");
		sleep();
		AddPage.commentField.sendKeys("closing");
		commonFuntions.clickButtonContains("Submit"); 
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ReviewWorkItemCompleted","Pass","Review Workitem Completed");
		commonFuntions.clickButtonContains("Home");


	}
}
