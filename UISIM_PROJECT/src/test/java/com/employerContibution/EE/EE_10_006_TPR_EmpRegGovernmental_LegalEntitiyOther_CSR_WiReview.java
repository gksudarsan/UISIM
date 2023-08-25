package com.employerContibution.EE;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_10_006_TPR_EmpRegGovernmental_LegalEntitiyOther_CSR_WiReview extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_10_006() throws Exception {
		
		test = report.createTest("EE.10.006 : Verify TPR can submit employer registration for employer type 'Governmental' and legal entity type 'Other' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		employerManagement empManage = new employerManagement(driver);
		
		//GET query
		//FEIN both in DOL & DTF
		Map<String, String> databaseFeinResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS DESC","FEIN");
		String feinValue = databaseFeinResult.get("FEIN");
		//EAN in DOL not in DTF
		Map<String, String> databaseErnResult = commonFunction.database_SelectQuerySingleColumn("SELECT * FROM LROUIM.T_EMPLOYER_ACCOUNT tea JOIN LROUIM.T_EMPLOYER_DOL_DTF tedd ON tea.FEIN = tedd.FEIN WHERE tea.EAN != tedd.ERN","ERN");
		String ernValue = databaseErnResult.get("ERN");
		
		
		
		//--- Login ---
		commonFunction.login(COMMON_CONSTANT.TPR_USER_3.toUpperCase(), COMMON_CONSTANT.TPR_USER_3_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		//---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		empManage.menu.click();
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.ScrollMenu("Register Employer");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		
		//--- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Middle Initial", "S");
		commonFunction.enterTextboxContains("Last Name", "Rodriguez");
		commonFunction.selectDropdown("Suffix", " Sr. ");
		commonFunction.enterTextboxContains("Job Title", "Tester");
		commonFunction.enterTextboxContains(" Contact Telephone Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Ext", Long.toString(commonFunction.createRandomInteger(100, 999)));
		commonFunction.enterTextboxContains("Email Address", "randomMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EE10006", "Pass", "Details entered on SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-025 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Details entered on SREG-025 page");
		commonFunction.selectDropdown("Employer Type", " Governmental ");
		//System.out.println("The FIEN is " + feinValue);
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "808705906");
		commonFunction.selectDropdown("Type of Legal Entity", " Other ");
		commonFunction.enterTextboxContains("If Other, provide", "hachiku");
		commonFunction.enterTextboxContains("Employer Registration Number", "5675215");
		//System.out.println("The EAN is " + eanValue);
		sleep();
		commonFunction.screenShot("EE10006", "Pass", "Details entered and clicked on CONTINUE button");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-003 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("AMEN INC".toUpperCase()); 
		//commonFunction.enterTextboxContains("Other commonly known", "New Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains(" Business Fax Number ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterPastDate("date of the first payroll", 90);
		commonFunction.enterTextboxContains("Estimated or approximate number of individuals", "325");
		commonFunction.enterDateOfCurrentQuaterFirstMonth("Date covered employment");
		commonFunction.selectRadioQuestions("Choose the option you wish to use to discharge your Unemployment Insurance liability.", "Reimbursable");
		sleep();
		commonFunction.screenShot("EE10006", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			commonFunction.screenShot("EE10006", "Pass", "Warning message on Continue");
			commonFunction.clickButton(" Yes ");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		//--- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.screenShot("EE10006", "Pass", "Enter the details on Employer Entity Information page and click continue");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street ");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		commonFunction.selectDropdown("County", " Albany ");
		commonFunction.clickButton("Continue ");
		
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}

		try {
			sleep();
			commonFunction.screenShot("EE10006", "Pass", "USPS Business address selection on SREG-008");
			empRegPage.continueButton_popUp.click();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		
		//--- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		//--- SREG-004 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("721 Broadway");
		empRegPage.uspsBmadCityText.sendKeys("New York");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE10006", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsLbraAddressText.sendKeys("Affinia Manhattan Hotel");
		empRegPage.uspsLbraCityText.sendKeys("New York");
		empRegPage.uspsLbraZipText.sendKeys("10001");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		empRegPage.uspsLbraFirstNameText.sendKeys("Thomas");
		empRegPage.uspsLbraLastNameText.sendKeys("Shelby");
		sleep(2000);
		commonFunction.screenShot("EE10006", "Pass", "Successfully entered details in SREG-004 page");
		
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
		empRegPage.uspsNpcaAddressText.sendKeys("55 East 10th Street");
		empRegPage.uspsNpcaCityText.sendKeys("New York");
		empRegPage.uspsNpcaZipText.sendKeys("10003");
		empRegPage.uspsNpcaCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		empRegPage.uspsNpcaFirstNameText.sendKeys("Sydney");
		empRegPage.uspsNpcaLastNameText.sendKeys("Sheldon");
		sleep(2000);
		commonFunction.screenShot("EE10006", "Pass", "Successfully entered details in SREG-004 page");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		empRegPage.uspsBmadAddressRadio.click();
		sleep();
		empRegPage.uspsNpcaAddressRadio.click();
		sleep(1500);
		commonFunction.screenShot("EE10006", "Pass", "Entered USPS address in SREG-004 page");
		empRegPage.continueButton_popUp.click();
		
		try {
			commonFunction.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?", "No ");
			commonFunction.clickButton("Continue ");
		} catch(Exception exception) {
			exception.printStackTrace();
		}
		
		// --- SREG-521 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched Employer Verify Contact Details(SREG-521) page");
		commonFunction.clickButton("Continue ");
		
		//--- SREG 683 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched Upload Documents(SREG-683) page");
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EE10006", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 800 ---
		commonFunction.waitForLoadingIconToDisappear();
		sleep(2000);
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched Review Registration Details(SREG-800) page");
		commonFunction.clickButton("Continue ");
	
		// --- SREG-043 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched to SREG-043 page");
		empRegPage.commentid.sendKeys("Ok to submit");
		commonFunction.selectCheckbox("I accept");
		sleep(1500);
		commonFunction.screenShot("EE10006", "Pass", "Entered comment &amp; accepted T&amp;C in SREG-043 page");		
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE10006", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Home ");
		
		commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1, COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		test.log(Status.PASS, "Login with CSR is successful");
		
		commonFunction.waitForLoadingIconToDisappear();
		Thread.sleep(5000);
		
		//commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1 + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='" + feinValue + "' ORDER BY UPDATED_TS desc)");
		sleep(3000);
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.screenShot("wiSearch","Pass","UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1.toUpperCase() + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='808705906' ORDER BY UPDATED_TS desc)");
		
	    commonFunction.enterTextboxContains("FEIN","808705906");
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
	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
	    
	    // --- EEWI-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Review Employer Type Task Details(EEWI-002) page");
	    commonFunction.enterCurrentDate("Date Covered Employment began? ");
	    empRegPage.commentId_EEWI002.sendKeys("Ok tested");
	    sleep(1500);
	    commonFunction.screenShot("EE10006", "Pass", "Entered details in EEWI-002 page");
	    commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
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
 	    commonFunction.clickOnLink("DOL DTF Discrepancy");
 		
 	    // --- WF-091 ---
 	    commonFunction.waitForLoadingIconToDisappear();
 	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Work Item Details(WF-091) page");
 	    commonFunction.clickButtonContains("Open Work Item ");
 		
 		// --- EEWI-005 ---
 	    commonFunction.waitForLoadingIconToDisappear();
 	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to DOL/DTF Discrepency Task(EEWI-005) page");
 	    commonFunction.selectDropdown("Account Status", " Liable ");
 	    empRegPage.comment.sendKeys("Ok");
 	    sleep(2000);
 	    commonFunction.screenShot("EE10006", "Pass", "Entered data in EEWI-005 page");
 	    commonFunction.clickButtonContains("Submit ");
 	    
 	    // --- SUC-002 ---
 	    commonFunction.waitForLoadingIconToDisappear();
 	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
 	    commonFunction.clickButtonContains("Home ");
	    
 	    
 	    sleep(3000);
		peoPage.queue.click();
	    Thread.sleep(15000);
	    //commonFunction.screenShot("wiSearch","Pass","UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1.toUpperCase() + "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='808705906' ORDER BY UPDATED_TS desc)");
		
	    commonFunction.enterTextboxContains("FEIN","808705906");
	    commonFunction.screenShot("wiSearch","Pass","Searched with FEIN");
	    commonFunction.clickButtonContains("Search");
	    
    	// ---------------------------- WI 3 - Review Comments Task ----------------------------
	    
 	    sleep(3000);
	    commonFunction.ScrollMenu("Review Comments");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Review Comments'");
	    sleep();
	    commonFunction.clickOnLink("Review Comments");
	    
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
	    
	    // --- EEWI-008 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Review Comments Task Details(EEWI-008) page");
	    commonFunction.selectRadioQuestions("Financing Method", "Reimbursable");
	    commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
	    empRegPage.commentId.sendKeys("Ok");
 	    sleep(2000);
 	    commonFunction.screenShot("EE10006", "Pass", "Entered data in EEWI-008 page");
 	    commonFunction.clickButtonContains("Submit ");
 	    
 	    // --- SUC-002 ---
 	    commonFunction.waitForLoadingIconToDisappear();
 	    commonFunction.screenShot("EE10006", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
 	    commonFunction.clickButtonContains("Home ");
 	    
 	    
 	    // ------------------------------
 	    // WI 4. Review Profile Data Task created. No mention in MC step. :)
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
 		commonFunction.screenShot("EE10006", "Pass", "Launched the Inquiry Employer Account - Enter ERN(SREG-050) page"); 
 		commonFunction.enterTextboxContains("Employer Registration Number", "5675215"); //2501424
 		sleep();
 		commonFunction.screenShot("EE10006", "Pass", "Entered ERN in SREG-050 page");
 		commonFunction.clickButton("Continue ");
 		
 		// --- SREG-051 ---
 		commonFunction.waitForLoadingIconToDisappear();
 		commonFunction.screenShot("EE10006", "Pass", "Launched the Inquiry Employer Account Information(SREG-051) page");
 		commonFunction.clickButton("Previous ");
 		sleep(3000);
 		commonFunction.clickButton(" Home ");
 		
 		// --- WEL-10000 --- Home
 		commonFunction.waitForLoadingIconToDisappear();
 		commonFunction.screenShot("EE10006", "Pass", "Launched the Home(WEL-10000) page");
 		
 		test.log(Status.PASS, "TC EE.10.006 passed successfully");
 		
		System.out.println("Pass :)");
		
	}

}
