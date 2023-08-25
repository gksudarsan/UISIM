package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_10_003_TPR_EmpRegGovernmental_LegalEntitiyVillage_CSR_WiReview extends TestBase{

	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Village' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION} )
	public void TC_EE_10_003() throws Exception {
		
		test = report.createTest("EE.10.003 : Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Village' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		// GET query
		// FEIN in DOL & not in DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.EAN = tedd.ERN WHERE tea.FEIN != tedd.FEIN",
				"FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		System.out.println("The FIEN is " + feinValue);
		
		// EAN in DOL & DTF
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IN (SELECT EAN FROM T_EMPLOYER_DOL_DTF tedd) AND EAN IS NOT NULL AND LENGTH(EAN)=7 ORDER BY UPDATED_TS DESC",
				"EAN");
		String eanValue = databaseEanResult.get("EAN");
		System.out.println("The EAN is " + eanValue);
		
		//Legal name not in DOL, multiple in DTF
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IN (SELECT LEGAL_NAME FROM T_EMPLOYER_DOL_DTF tedd GROUP BY LEGAL_NAME HAVING COUNT(*)>1 ) ORDER BY UPDATED_TS DESC","ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);
				
	    
	    // --- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		test.log(Status.PASS, "Login with TPR is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		empRegPage.menu.click();
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");		
		
		// --- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister1", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Middle Initial", "S");
		commonFunction.enterTextboxContains("Last Name", "Rodriguez");
		commonFunction.selectDropdown("Suffix", " Sr. ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister2", "Pass", "Details entered on SREG-001 page");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address", "randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EmpRegister3", "Pass", "Details entered on SREG-001 page");
		commonFunction.clickButton("Continue ");

		// --- SREG-025 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("MenuPage", "Pass", "Successful Launch to SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "351744007"); //45-9749876
		commonFunction.selectDropdown("Type of Legal Entity", " Village ");
		commonFunction.enterTextboxContains("Employer Registration Number", "2073530"); //48-91116
		sleep(2000);
		commonFunction.screenShot("EmpRegister4", "Pass", "Details entered and click on CONTINUE button");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-003 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister5", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("TRANSCOM MEDIA INC"); //TID WTH
		//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		commonFunction.enterTextboxContains("Other commonly known name of entity", "Test Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		sleep(2000);
		commonFunction.screenShot("EmpRegister6", "Pass", "Details entered in SREG-003 page");
		commonFunction.enterTextboxContains("date of the first payroll", "09/01/2022");
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "90");
		commonFunction.enterTextboxContains("Date covered employment", "05/07/2022");
		commonFunction.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Reimbursable");
		sleep(2000);
		commonFunction.screenShot("EmpRegister7", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			commonFunction.screenShot("EmpRegister7", "Pass", "Warning message on Continue");
			commonFunction.clickButton(" Yes ");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		// --- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister8", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister9", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}

		try {
			commonFunction.screenShot("EmpRegister10", "Pass", "USPS Business address selection on SREG-008");
			empRegPage.continueButton_popUp.click();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		// --- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister11", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");

		// --- SREG-004 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister12", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		commonFunction.enterTextboxContains("Care Of", "knowledge is power");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EmpRegister13", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		commonFunction.enterTextboxContains("Attention", "Test");
		empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
		empRegPage.uspsLbraCityText.sendKeys("New York");
		empRegPage.uspsLbraZipText.sendKeys("10001");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		empRegPage.uspsLbraFirstNameText.sendKeys("Thomas");
		empRegPage.uspsLbraLastNameText.sendKeys("Shelby");
		sleep(2000);
		commonFunction.screenShot("EmpRegister14", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
		empRegPage.uspsNpcaCityText.sendKeys("New York");
		empRegPage.uspsNpcaZipText.sendKeys("10003");
		empRegPage.uspsNpcaCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		empRegPage.uspsNpcaFirstNameText.sendKeys("Sydney");
		empRegPage.uspsNpcaLastNameText.sendKeys("Sheldon");
		sleep(2000);
		commonFunction.screenShot("EmpRegister15", "Pass", "Successfully entered details in SREG-004 page");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		empRegPage.uspsBmadAddressRadio.click();
		empRegPage.uspsNpcaAddressRadio.click();
		commonFunction.screenShot("EmpRegister15", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		empRegPage.continueButton_popUp.click();
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {
			sleep(2000);
		}
		
		// --- SREG-521 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister16", "Pass", "Successfully launched Employer Verify Contact Details(SREG-521) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister17", "Pass", "Successfully launched Upload Documents(SREG-683) page");
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EmpRegister17", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 800 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("EmpRegister18", "Pass", "Successfully launched Review Registration Details(SREG-800) page");
		commonFunction.clickButton("Continue ");
	
		// --- SREG-043 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EmpRegister19", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EmpRegister20", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Home ");
				
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		
		//commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1 + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='" + feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(3000);
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.screenShot("wiSearch","Pass","UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1.toUpperCase() + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='241926291' ORDER BY UPDATED_TS desc)");
		
	    commonFunction.enterTextboxContains("FEIN","241926291");
	    commonFunction.screenShot("wiSearch","Pass","Searched with FEIN");
	    commonFunction.clickButtonContains("Search");
	    
	    // ---------------------------- WI 1 - Review Employer Type ----------------------------
	    sleep(3000);
	    commonFunction.ScrollMenu("Review Employer Type");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Review Employer Type'");
	    sleep();
	    commonFunction.clickOnLink("Review Employer Type");
	    
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
	    
	    // --- EEWI-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Review Employer Type Task Details(EEWI-002) page");
	    commonFunction.enterCurrentDate("Date Covered Employment began? ");
	    empRegPage.commentId_EEWI002.sendKeys("Ok tested");
	    sleep(1500);
	    commonFunction.screenShot("EE10003", "Pass", "Entered details in EEWI-002 page");
	    commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    // ---------------------------- WI 2 - DOL DTF Discrepancy Task ----------------------------
	    
	    
		commonFunction.waitForLoadingIconToDisappear();
		// run query to update user ID to work item
		// 1. SELECT * FROM T_WFA_PROCESS_DETAIL twpd WHERE PROCESS_NAME LIKE 'DOL%' ORDER BY UPDATED_TS desc
		// 2. SELECT * FROM LROUIM.T_WFA_WORK_ITEM_DETAIL WHERE PROCESS_DETAIL_ID ='101042'
		Thread.sleep(5000);
	     
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.screenShot("wiSearch","Pass","Update Query to bind DOL DTF Descripancy WI: UPDATE T_WFA_WORK_ITEM_DETAIL SET USER_ID = 'ndsbb3' WHERE PROCESS_DETAIL_ID ='101042' AND WORK_ITEM_NAME = 'DOLDTFDiscrepancy'");
	    commonFunction.enterTextboxContains("Work Item Description Free Text","DOL");
	    commonFunction.screenShot("wiSearch","Pass","Searched with Work Item Description Free Text");
	    commonFunction.clickButtonContains("Search");
	    
	    // --- DOL DTF Discrepancy WI ---
	    
	    sleep(3000);
	    commonFunction.ScrollMenu("DOL DTF Discrepancy");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'DOL DTF Discrepancy'");
	    sleep();
	    //commonFunction.clickOnLink("DOL DTF Discrepancy");
		
	    // --- WF-091 ---
	    //commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
		
		// --- EEWI-005 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to DOL/DTF Discrepency Task(EEWI-005) page");
	    empRegPage.comment.sendKeys("Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE10003", "Pass", "Entered data in EEWI-005 page");
	   // commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    // ---------------------------- WI 3 - Verify Agent-Rep Task ----------------------------
	    
	    commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
	     
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.enterTextboxContains("FEIN","241926291");
	    commonFunction.screenShot("wiSearch","Pass","Searched with FEIN");
	    commonFunction.clickButtonContains("Search");
	    
	    // --- Verify Agent Rep Task WI ---
	    
	    sleep(3000);
	    commonFunction.ScrollMenu("Verify Agent Rep Task");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Verify Agent Rep Task'");
	    sleep();
	    commonFunction.clickOnLink("Verify Agent Rep Task");
		
	    // --- WF-091 ---
	    //commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
		
		// --- EEWI-012 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Verify Agent-Rep Task(EEWI-012) page");
	    commonFunction.selectDropdown("Account Status", " Suspended Out of Business ");
	    empRegPage.suspended_Quater.click();
	    sleep();
		empRegPage.firstCalender_Quater_Value_2.click();
		empRegPage.suspended_Year.click();
		sleep();
		empRegPage.firstCalender_Year_Value_2023.click();
	    empRegPage.comment.sendKeys("Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE10003", "Pass", "Entered data in EEWI-012 page");
	    commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10003", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    
	    // ------------------------------
	    
	    // --- Menu Click ---
 		commonFunction.waitForLoadingIconToDisappear();
 		empRegPage.menu.click();
 		commonFunction.ScrollMenu("Inquiry");
 		commonFunction.clickMenu("Inquiry");
 		commonFunction.clickMenu("Contribution Inquiry");
 		commonFunction.ScrollMenu("Inquiry Employer Account");
 		sleep();
 		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Inquiry -> Contribution Inquiry -> Inquiry Employer Account");
 		commonFunction.clickMenu("Inquiry Employer Account");		
 				
 		// --- SREG-050 ---
 		commonFunction.waitForLoadingIconToDisappear();
 		commonFunction.screenShot("EE10003", "Pass", "Launched the Inquiry Employer Account - Enter ERN(SREG-050) page"); 
 		commonFunction.enterTextboxContains("Employer Registration Number", "2501424"); //2501424
 		sleep();
 		commonFunction.screenShot("EE10003", "Pass", "Entered ERN in SREG-050 page");
 		commonFunction.clickButton("Continue ");
 		
 		// --- SREG-051 ---
 		commonFunction.waitForLoadingIconToDisappear();
 		commonFunction.screenShot("EE10003", "Pass", "Launched the Inquiry Employer Account Information(SREG-051) page");
 		commonFunction.clickButton("Previous ");
 		sleep(3000);
 		commonFunction.clickButton(" Home ");
 		
 		// --- WEL-10000 --- Home
 		commonFunction.waitForLoadingIconToDisappear();
 		commonFunction.screenShot("EE10003", "Pass", "Launched the Home(WEL-10000) page");
 		
 		test.log(Status.PASS, "TC EE.10.003 passed successfully");
 		
		System.out.println("Pass :)");
		
	}
}
