package com.employerContibution.EE;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.createRandomString;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_13_001_CSR_UserType_Business extends TestBase{

	@Test
	public void EE_13_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		//LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		test = 
				report.createTest("EE.13.001:Verify CSR can submit employer registration for employer type 'Business'\r\n" + 
						"Short Form flow");
		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickMenu("Menu");sleep();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");sleep();
		commonFuntions.screenShot("EmployerRegistration", "Pass", "Create Short Form Resgistration Review Task:EESR-001");
		commonFuntions.clickMenu("Create Short Form Resgistration Review Task");
		sleep(3000);
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("FEIN : : "+feinValue);
		test.log(Status.INFO, "FEIN::"+feinValue);
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		String legalName = commonFuntions.enterRandomString("Legal Name");
		System.out.println(legalName);
		commonFuntions.selectDropdown("Source", " IA602 ");
		sleep();
		AddPage.browserLink.click();
		sleep(3000);
		commonFuntions.uploadDoc("TESTINGEL");
		sleep(3000);
		commonFuntions.screenShot("CreateShortFormRegistrationReviewTask", "Pass", "Create Short Form Registration Review Task Screen");
		commonFuntions.clickButtonContains("Submit ");
		sleep();
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("CreateShortFormRegistrationReviewTaskConfirmation", "Pass", "Create Short Form Registration Review Task Confirmation:SUC-002");
		commonFuntions.clickButtonContains("Home");
		sleep(3000);
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Click on My-Q");
		PEOPage.queue.click();
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated to WF-001 page and search for WI");
		commonFuntions.enterTextboxContains("FEIN", feinValue);
		commonFuntions.clickButtonContains(" Search ");
		sleep(2000);
		commonFuntions.screenShot("IndividualWorkQueue", "Pass", "Searched for work item");
		commonFuntions.clickOnLink("Short Form Registration Review");
		sleep(2000);
		commonFuntions.screenShot("WorkItemDetails", "Pass", "Work Item Details:WF-091");
		commonFuntions.clickButtonContains("Open Work Item");
		sleep();
		commonFuntions.screenShot("ShortFormRegistrationReviewTask", "Pass", "Short Form Registration Review Task:SREG-818");
		commonFuntions.safeJavaScriptClick(empPage.shor_Form_Registration_Link_SREG_818);
		sleep(4000);
		Set<String> handles =  driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowId = it.next();
		String childWindowId = it.next();
		driver.switchTo().window(childWindowId);
		sleep();
		/*-------------------SREG-001-------------------*/
		commonFuntions.screenShot("EmpRegister8", "Pass", "Navigated to SREG-001 page");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		/*-------------------SREG-025-------------------*/
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("FEIN2 : : "+feinValue2);
		commonFuntions.selectDropdown("Employer Type", " Business ");
		sleep();
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.selectDropdown("Type of Legal Entity", " Other ");
		sleep();
		commonFuntions.selectDropdown("Source", " IA602 ");
		sleep();
		commonFuntions.selectDropdown("Source Type", " Other ");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("requiredError", "Pass", "General Information:SREG-025_requiredError");
		commonFuntions.errorLabel(" Required ");
		sleep();
		commonFuntions.enterTextboxContains("If Other, provide the type of Legal Entity.", "Other");
		sleep();
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);

		/*----Employer Entity Information(SREG-003)-----*/

		String lname = commonFuntions.enterRandomStringLegalName("Legal Name");
		System.out.println(lname);
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", lname);
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "4536456787");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_employed_4_value_2023.click();
		commonFuntions.enterTextboxContains("Total number of covered employees", "50");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		commonFuntions.screenShot("TradeNameCannotBeSame", "Pass", "Employer Entity Information:SREG-003");
		commonFuntions.errorLabel(" Trade Name cannot be the same as Legal Name of business");
		sleep(2000);
		commonFuntions.forceClearText(AddPage.DBA);
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_employed_4_value_2023.click();
		sleep();commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.clickButtonContains(" Yes ");
		}catch(Exception e) {
			System.out.println("verify date");
		}

		/*------Employer Contact Details-----*/
		commonFuntions.enterTextboxContains("Address Line 1","243 Test Road");
		commonFuntions.enterTextboxContains("City","NY");
		commonFuntions.enterTextboxContains("Zip Code","10025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		try {
			commonFuntions.safeJavaScriptClick(AddPage.uspsAddress);
			sleep();
			AddPage.continueButton_popUp.click();
		}catch(Exception e) {
			System.out.println("Business Physical Address Details");
		}
		sleep();

		/*-------------------SREG-521-------------------*/
		commonFuntions.screenShot("EmployerVerifyContactDetails", "Pass", "Employer Verify Contact Details:SREG-521");
		commonFuntions.clickButton("Continue ");
		sleep(2000);

		/*-------------------SREG-011-------------------*/
		commonFuntions.screenShot("BusinessAcquisition", "Pass", "Business Acquisition:SREG-011");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		/*-------------------SREG-012-------------------*/
		commonFuntions.screenShot("ChangeInLegalEntity", "Pass", "Change in Legal Entity:SREG-012");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		/*-------------------SREG-683-------------------*/
		commonFuntions.screenShot("UploadDocuments", "Pass", "Upload Documents:SREG-683");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		/*-------------------SREG-800-------------------*/
		commonFuntions.screenShot("ReviewRegistrationDetails", "Pass", "Review Registration Details:SREG-800");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		/*-------------------SREG-043-------------------*/
		commonFuntions.screenShot("StatementofAcknowledgement", "Pass", "Statement of Acknowledgement:SREG-043");
		commonFuntions.clickButton("Submit ");
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		/*-------------------SREG-013-------------------*/
		commonFuntions.screenShot("EmployerRegistrationConfirmation", "Pass", "Employer Registration Confirmation:SREG-013");
		commonFuntions.clickButton("Exit ");
		sleep(3000);

		/*-------------------Home Page-------------------*/
//		commonFuntions.screenShot("EmpRegister23", "Pass", "Navigated to Home Page page click on Inquiry Employer Account");
//		commonFuntions.clickMenu("Menu");
//		sleep();
//		commonFuntions.clickMenu("Inquiry");
//		sleep();
//		commonFuntions.safeJavaScriptClick(empPage.inquiry_dropDown_Menu);
//		sleep();
//		commonFuntions.clickMenu("Contribution Inquiry");
//		commonFuntions.safeJavaScriptClick(empPage.Contribution_dropDown_Menu);
//		sleep();
//		commonFuntions.screenShot("EmpRegister23", "Pass", "Clicking on Inquiry Employer Account");
//		commonFuntions.clickMenu("Inquiry Employer Account");
//		sleep(4000);

		/*-------------------SREG-050-------------------*/
//		commonFuntions.screenShot("EmpRegister24", "Pass", "Navigated to SREG-050 page and validating the FEIN");
//		commonFuntions.enterTextboxContains(" FEIN ", feinValue2);
//		sleep();
//		commonFuntions.clickButton("Continue ");
//		sleep(4000);
//		String fein = empPage.FEIN_Value_Text_SREG_051.getText();
//		System.out.println("FEIN from SREG-051 : : "+fein);
//		Assert.assertEquals(feinValue2, fein.replace("-", ""));
//		commonFuntions.screenShot("EmpRegister25", "Pass", "Click on previous button");
//		commonFuntions.clickButton("Previous ");
//		sleep();
//		commonFuntions.screenShot("EmpRegister26", "Pass", "Click on Home button");
//		commonFuntions.clickButton(" Home ");



	}
}
