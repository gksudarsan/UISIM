package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_01_006_CSR_Submit_EmpReg_Business_LimitedPartnership extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Partnership' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_01_006() throws Exception {
	
		test = report.createTest("EE.01.006 : Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Limited Partnership' and work items will be created for CSR to review.");
		
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		/*
		//--- GET Query
		
		// ERN that cannot be found on DOL, and is different than ERN that matches FEIN on DTF:
		Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_DOL_DTF tedd WHERE ERN NOT IN (SELECT ERN FROM T_EMPLOYER_DOL_DTF tedd2 LEFT JOIN T_EMPLOYER_ACCOUNT tea ON tea.FEIN = tedd2.FEIN WHERE tea.EAN != tedd2.ERN) AND ERN IS NOT NULL",
				"ERN");
		String eanValue = databaseEanResult.get("ERN");
		System.out.println("The ERN is " + eanValue);
		
		//Legal name match in DOL
		Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IS NOT NULL AND LENGTH(ENTITY_NAME) <=200 ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);
*/
		
		//FEIN
		String fein="010104123";
		System.out.println(fein);
		
		//EAN
				String ean="2572896";
				System.out.println(ean);
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// ---Menu Click---
		commonFunction.waitForLoadingIconToDisappear();
		empRegPage.menu.click();
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		sleep();
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		// commonFunction.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep(2000);
		
		
		// --- SREG-001 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched the Employer Register(SREG-001) page");
		commonFunction.ScrollMenu("Suffix");
		commonFunction.screenShot("EE01006", "Pass", "Data from SREG-001 page");
		sleep(2000);
		commonFunction.ScrollMenu("Suppress Correspondence?");
		commonFunction.screenShot("EE01006", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
	
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", fein); //897397325
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Limited Partnership ");
		sleep();
		commonFunction.screenShot("EE01006", "Pass", "Entered the details in SREG-025");
		commonFunction.enterTextboxContains("Employer Registration Number", "2572896"); //4543352
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFunction.screenShot("EE01006", "Pass", "Entered the details and clicked on continue button");
		commonFunction.clickButton("Continue ");
		sleep(3000);
		
		// --- SREG-003 ---
		String legalname="INNER BEAUTY ";
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched Employer Entity Information(SREG-003) page");
//		empRegPage.legalNameTextBox.sendKeys(legalName); //TEST REVIEW TASK -- FREDERICK DUNBAR & HELEN MANGANO
		empRegPage.legalNameTextBox.sendKeys(legalname);
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "S Corp");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Business Email Address", "randomBusinessMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-003 page");
		commonFunction.enterPastDate("Enter date of first operations in New York State", 360);
		commonFunction.enterPastDate("What is the date of the first payroll which you withheld ", 270);
		commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-003 page");
		empRegPage.quaterDropDown.click();
		sleep(1500);
		empRegPage.firstCalender_Quater_Value.click();
		empRegPage.yearDropDown.click();
		sleep(1500);
		empRegPage.yearValue_2022.click();
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		commonFunction.enterTextboxContains("Total number of covered employees", "10");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-008 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-008 page");
		commonFunction.enterTextboxContains("Number of employees at this location", "10");
		commonFunction.selectDropdown("Principal Business Activity at this location in New York State", " Manufacturing ");
		empRegPage.principalProducts_SREG008.sendKeys("testProd");
		empRegPage.principalRawMaterial_SREG008.sendKeys("codes");
		commonFunction.screenShot("EE01006", "Pass", "Enter the details on SREG-008 and click continue");
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
			empRegPage.uspsBusiAddress1InnerRadio.click();
		} catch (Exception exception) {
			empRegPage.uspsBusiAddress1OuterRadio.click();
		}
		
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-004 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
//				commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
		empRegPage.uspsBmadAddressText.sendKeys("joker nagar");
		empRegPage.uspsBmadCityText.sendKeys("India");
		empRegPage.uspsBmadZipText.sendKeys("10003");
		empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Successfully entered details in SREG-004 page");
		
		sleep(2000);
		
		commonFunction.clickButton("Continue ");
		try {
			empRegPage.uspsBusiAddress1InnerRadio.click();
		} catch (Exception exception) {
			empRegPage.uspsBusiAddress1OuterRadio.click();
		}
		commonFunction.clickButton("Continue ");
		// SREG-521
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		empRegPage.tradeNameId_SREG011.sendKeys("TEST EM JAA user dfe".toUpperCase()); //Cooking Inc
		empRegPage.address1_SREG011.sendKeys("Affinia Manhattan Hotel");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10013");
		commonFunction.enterPastDate("Acquisition Date", 90);
		commonFunction.enterTextboxContains("Notification date of Transfer", "04012023");
		sleep(3000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 012
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successful launch to (Change in Legal Entity)SREG-012 page");
		commonFunction.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		commonFunction.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "510032773"); //321326774
		commonFunction.enterTextboxContains("Prior Employer Registration Number", "8418781");
		commonFunction.enterTextboxContains("Date of Legal Entity change", "05172023");
		commonFunction.enterTextboxContains("Date of Notification", "04182023");
		sleep(2000);
		commonFunction.screenShot("EE01006", "Pass", "Details entered in to SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		// SREG-006
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successful launch to SREG-006 page");
		commonFunction.selectRadioQuestions("Type of Partner/Owner", "Individual");
		String ssnValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(ssnValue);
		commonFunction.enterTextboxContains("SSN", ssnValue);
		commonFunction.enterTextboxContains("First Name", "Antonio");
		commonFunction.enterTextboxContains("Last Name", "Gonzalez");
		commonFunction.enterTextboxContains("Address Line 1 ", "IndiaGate");
		commonFunction.enterTextboxContains("City ", "Ukren");
		commonFunction.selectDropdown("State", " ukren ");
		commonFunction.enterTextboxContains("Zip Code", "87685552");
		
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-006");
		commonFunction.clickButton("Continue ");
		/// it should be not come
		sleep();
		/*try {
			empRegPage.uspsAddress1InnerRadio.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-006");
		empRegPage.continueButton_popUp.click();
		*/
		
		// --- SREG 005 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-005");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EE01006", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-800 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		sleep(40000);
		commonFunction.screenShot("EE01006", "Pass", "Successfully launched to SREG-013 page");
		commonFunction.clickButton("Home ");

		commonFunction.waitForLoadingIconToDisappear();
		// run query to update user ID to work item
		// 1. SELECT * FROM T_WFA_PROCESS_DETAIL twpd WHERE PROCESS_NAME LIKE 'DOL%' ORDER BY UPDATED_TS desc
		// 2. SELECT * FROM LROUIM.T_WFA_WORK_ITEM_DETAIL WHERE PROCESS_DETAIL_ID ='101042'
		Thread.sleep(5000);
	        
		
		//----Work item----
		
		/*-------------- Work Item 1  --------------*/
		sleep(3000);
		commonFunction.waitForLoadingIconToDisappear();
		peoPage.queue.click();
		commonFunction.waitForLoadingIconToDisappear();
		
		/*--------------WF-001   --------------*/
		sleep(3000);
		commonFunction.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
		sleep(3000);
		commonFunction.clearTextboxContains("Employer Registration Number");
		commonFunction.enterTextboxContains("Work Item Description Free Text","DOL");
		sleep(3000);
	    commonFunction.screenShot("Work Item Description Free Text","Pass","Search for Task");
	    sleep(3000);
	    commonFunction.clickButtonContains(" Search ");
	    sleep(3000);
	    commonFunction.ScrollMenu("DOL DTF Discrepancy");
	    sleep(3000);
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - DOL DTF Discrepancy");
	    sleep(3000);
	    commonFunction.clickOnLink("DOL DTF Discrepancy");
		
	   
	 // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item ");
		// --- EEWI-005 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to DOL/DTF Discrepency Task(EEWI-005) page");
	    commonFunction.selectDropdownEquals("Account Status", " Liable ");
	    commonFunction.selectRadioQuestions("Suppress Correspondence?", "No ");
	    empRegPage.comment.sendKeys("Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE01006", "Pass", "Entered data in EEWI-005 page");
	    commonFunction.clickButtonContains("Submit ");
	    
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    
	    commonFunction.waitForLoadingIconToDisappear();
		// run query to update user ID to work item
		Thread.sleep(5000);
		
		
		//------WI -2
		peoPage.queue.click();
	    Thread.sleep(15000);
	    commonFunction.screenShot("wiSearch","Pass","Update Query: UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = 'ndsbb3' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN = '260525898' ORDER BY UPDATED_TS desc)");
	    commonFunction.enterTextboxContains("FEIN","260525898");
	    commonFunction.screenShot("wiSearch","Pass","Searched with Work Item Description Free Text");
	    commonFunction.clickButtonContains("Search");
	    sleep(3000);
	    commonFunction.ScrollMenu("DOL DTF Discrepancy");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - 'Normalize address'");
	    sleep();
	    commonFunction.clickOnLink("DOL DTF Discrepancy");
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    commonFunction.clickButtonContains("Open Work Item ");
	    // --- EEWI-001 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Normalize address Task(EEWI-001) page");
	    empRegPage.comment.sendKeys("Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE01006", "Pass", "Entered data in (EEWI-001) page");
	    commonFunction.clickButtonContains("Submit ");
	 // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    
	    //------WIem -3
	    peoPage.queue.click();
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("wiSearch","Pass","Work item ");
	    commonFunction.enterTextboxContains("FEIN","010085930");
	    commonFunction.screenShot("wiSearch","Pass","Searched with Work Item Description Free Text");
	    commonFunction.clickButtonContains("Search");
	    sleep(3000);
	    commonFunction.ScrollMenu("Resolve Incomplete Data");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - Resolve Incomplete Data");
	    sleep();
	    commonFunction.clickOnLink("Resolve Incomplete Data");
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item ");
		// --- EEWI-012 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Resolve incomplete data transfer Task(EEWI-012) page");
	    // commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "010085930"); //897397325
		empRegPage.predecessorErn.sendKeys( "3540934");
	    sleep();
		//commonFunction.enterTextboxContains("Employer Registration Number", "3540934");
	    empRegPage.predecessorFein.sendKeys("010085930");
	    commonFunction.selectDropdownEquals("Decision", " Continue with Transfer ");
	    empRegPage.comment.sendKeys("test Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE01006", "Pass", "Entered data in EEWI-012 page");
	    commonFunction.clickButtonContains("Submit ");
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	    //wi 4 not genrated
	    
	  //------WorkItem -4
	    peoPage.queue.click();
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("wiSearch","Pass","Work item ");
	    commonFunction.enterTextboxContains("Work Item Description Free Text","create");
	    commonFunction.screenShot("wiSearch","Pass","Searched with Work Item Description Free Text");
	    commonFunction.clickButtonContains("Search");
	    sleep(3000);
	    commonFunction.ScrollMenu("Create Letter Task");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - Verify Transfer Failed Rules");
	    sleep();
	    commonFunction.clickOnLink("Create Letter Task");
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item ");
		// --- EEWI-015 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Create Transfer Letter Task", "Pass", "Successful launch to (EEWI-015) page");
	    empRegPage.commentId.sendKeys("test Ok");
	    sleep(2000);
	    commonFunction.clickButtonContains("Submit ");
		 // --- SUC-002 ---
		commonFunction.waitForLoadingIconToDisappear();
		commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
		sleep();
		commonFunction.clickButtonContains("Home ");
        sleep();
        commonFunction.waitForLoadingIconToDisappear();
	    
	    
	  //------WorkIem -5
        peoPage.queue.click();
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("wiSearch","Pass","Work item ");
	    commonFunction.enterTextboxContains("FEIN","986432189");
	    commonFunction.screenShot("wiSearch","Pass","Searched with Work Item Description Free Text");
	    commonFunction.clickButtonContains("Search");
	    sleep(3000);
	    commonFunction.ScrollMenu("Verify Transfer Failed Rules");
	    sleep();
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - Verify Transfer Failed Rules");
	    sleep();
	    commonFunction.clickOnLink("Verify Transfer Failed Rules");
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item ");
		// --- EEWI-014 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Validate Total/Partial Transfer Failed Rules Task", "Pass", "Successful launch to Verify Transfer Failed Rules Task(EEWI-014) page");
	    commonFunction.selectDropdown("Account Status", " Liable ");
	    sleep(2000);
	    commonFunction.selectDropdownEquals("Decision", " No Transfer ");
	    commonFunction.selectCheckbox("Transfer Business Rules");
	    empRegPage.comment.sendKeys("test Ok");
	    sleep(2000);
	    commonFunction.clickOnLinkAnchorTag("Task History (Click here to view details)");
	    commonFunction.screenShot("Validate Total/Partial Transfer Failed Rules Task", "Pass", "Entered data in EEWI-014 page");
	    commonFunction.clickButtonContains("Submit ");
	 // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
	    sleep();
	    commonFunction.clickButtonContains("Home ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01006", "Fail", "Expected Create letter task WI not created");
	    
		System.out.println("Fail :(");
	}

}
