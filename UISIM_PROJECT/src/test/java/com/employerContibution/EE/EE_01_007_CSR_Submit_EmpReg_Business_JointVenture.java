package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_01_007_CSR_Submit_EmpReg_Business_JointVenture extends TestBase{
	
	@Test(priority = COMMON_CONSTANT.PRIORITY_1, description = "Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Joint Venture' and work items will be created for CSR to review.", groups = {COMMON_CONSTANT.REGRESSION})
	public void TC_EE_01_007() throws Exception {
		
		test = report.createTest("Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Joint Venture' and work items will be created for CSR to review.");
	
		commonStepDefinitions commonFunction = new commonStepDefinitions();
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		//Legal name match in DOL
		/*Map<String, String> databaseEntityNameResult = commonFunction.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE ENTITY_NAME IS NOT NULL AND LENGTH(ENTITY_NAME) <=200 ORDER BY UPDATED_TS DESC",
				"ENTITY_NAME");
		String legalName = databaseEntityNameResult.get("ENTITY_NAME");
		System.out.println("The LegalName is " + legalName);
		
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);*/
		
		// --- Login ---
		commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
		
		// ---Menu Click---
		sleep(2000);
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Employer Registration");
		commonFunction.clickMenu("Employer Registration");
		commonFunction.screenShot("MenuPage", "Pass", "Navigate to Menu -> Employer Registration -> Register Employer");
		commonFunction.clickMenu("Register Employer");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched the Employer Register(SREG-001) page");
		
		// --- SREG-001 ---
		sleep(2000);
		
		commonFunction.screenShot("EE01007", "Pass", "Data from SREG-001 page");
		sleep(2000);
		/*
		commonFunction.enterTextboxContains("First Name", "AutoTest");
		commonFunction.enterTextboxContains("Last Name", "AutoSanjay");
		commonFunction.enterTextboxContains("Job Title", "AutomationEngineer");
		commonFunction.enterTextboxContains("Contact Telephone Number",Long.toString(commonFunction.createRandomInteger(10000000,99999999))+Long.toString(commonFunction.createRandomInteger(10,99)));
		commonFunction.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFunction.createRandomInteger(10000,99999))+"@gmail.com");
		commonFunction.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001");
		commonFunction.ScrollMenu("Suppress Correspondence?");*/
		commonFunction.screenShot("EE01007", "Pass", "Data from SREG-001 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-025 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched the Employer Register(SREG-025) page");
		commonFunction.selectDropdown("Employer Type", " Business ");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "981719556"); //897397325
		commonFunction.selectDropdownEquals("Type of Legal Entity", " Joint Venture - Internal User only ");
		sleep();
		commonFunction.enterTextboxContains("Employer Registration Number", "9830645"); //4543352
		commonFunction.selectDropdown("Source", " NYS-100 (paper) ");
		sleep();
		commonFunction.selectDropdown("Source Type", " NYS-100 ");
		sleep();
		commonFunction.clickButton("Continue ");
		commonFunction.screenShot("EE01007", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		// --- SREG-003 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched Employer Entity Information(SREG-003) page");
		empRegPage.legalNameTextBox.sendKeys("DAVID SINGER & ROBIN SINGERDAVID SINGER & ROBIN SINGER  "); //POWER SOLUTIONS EAST CORP
		//empRegPage.legalNameTextBox.sendKeys("B Legal Corp");
		commonFunction.enterTextboxContains("Trade Name or Doing Business As (DBA)", "test");
		commonFunction.enterTextboxContains(" Business Phone Number  ", Long.toString(commonFunction.createRandomInteger(10000000, 99999999)) + Long.toString(commonFunction.createRandomInteger(10, 99)));
		commonFunction.enterTextboxContains("Business Email Address", "randomBusinessMail" + Long.toString(commonFunction.createRandomInteger(1000, 9999)) + "@gmail.com");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-003 page");
		commonFunction.enterPastDate("Enter date of first operations in New York State", 360);
		commonFunction.enterPastDate("What is the date of the first payroll which you withheld ", 357);
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-003 page");
		
		commonFunction.selectRadioQuestions("Are you registering for Unemployment Insurance?", "Yes ");
		commonFunction.selectDropdown("Quarter", "4");
		sleep(2000);
     	commonFunction.selectDropdown("Year", "2022");
		commonFunction.selectRadioQuestions("Do persons work for you whom you do not consider to be your employees?", "No ");
		commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-003 page and click Continue");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-008 ---
		sleep(2000);
		/*
		commonFunction.screenShot("EE01007", "Pass", "Sucessfully launched to SREG-008 page");
		commonFunction.enterTextboxContains("Address Line 1 ", "13th Street");
		commonFunction.enterTextboxContains("City ", "New York");
		commonFunction.enterTextboxContains("Zip Code", "10011");
		sleep();
		commonFunction.selectDropdown("State", " New York ");
		commonFunction.selectDropdown("County", " Albany ");
		sleep(2000);
		commonFunction.enterTextboxContains("Number of employees at this location", "56");
		commonFunction.selectDropdown("Principal Business Activity at this location in New York State", " Food Services, Drinking & Accommodations ");
		empRegPage.typeOfEstablishment_SREG008.sendKeys("testProd");
		empRegPage.productSoldOrRendered_SREG008.sendKeys("codes");
		commonFunction.screenShot("EE01007", "Pass", "Enter the details on SREG-008 and click continue");
		*/
		commonFunction.clickButton("Continue ");
		
		sleep();
		/*
		try {
			empRegPage.uspsBusinessAddress.click();
		} catch (Exception exception) {
			empRegPage.uspsBusinessAddressInnerCircle.click();
		}
		
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-008");
		empRegPage.continueButton_popUp.click();
		
		// --- SREG-007 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Warning", "Successfully launched Business Physical Address Details(SREG-007) page");
		commonFunction.clickButton("Continue ");
	*/
		// --- SREG-004 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched Employer Contact Details(SREG-004) page");
		//commonFunction.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
	    commonFunction.selectRadioQuestions("Business Mailing Address", "Other");
	    empRegPage.uspsBmadAddressText.sendKeys("70 Washington Square South");
		empRegPage.uspsBmadCityText.sendKeys("New York");
	    empRegPage.uspsBmadZipText.sendKeys("10012");
	    empRegPage.uspsBmadCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully entered details in SREG-004 page");
		
//		commonFunction.selectRadioQuestions("Location of Books and Records", "Same as Mailing");
		commonFunction.selectRadioQuestions("Location of Books and Records", "Other");
		empRegPage.uspsLbraAddressText.sendKeys("29 Washington Pl,");
		empRegPage.uspsLbraCityText.sendKeys("New York");
		empRegPage.uspsLbraZipText.sendKeys("10003");
		empRegPage.uspsLbraCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsLbraFirstNameText.sendKeys("Brown");
		empRegPage.uspsLbraLastNameText.sendKeys("Bldg");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully entered details in SREG-004 page");
		
//		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Same as Location of Books and Records");
		commonFunction.selectRadioQuestions("Notice of Potential Charges (LO400) Address", "Other");
       empRegPage.uspsNpcaAddressText.sendKeys("36 East 8th Street");
	   empRegPage.uspsNpcaCityText.sendKeys("New York");
	   empRegPage.uspsNpcaZipText.sendKeys("10003");
	   empRegPage.uspsNpcaCounty.click();
		commonFunction.selectFromDropdown(" Albany ");
		sleep();
		empRegPage.uspsNpcaFirstNameText.sendKeys("Cantor");
		empRegPage.uspsNpcaLastNameText.sendKeys("Film");
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully entered details in SREG-004 page");
		
		sleep(2000);
		commonFunction.clickButton("Continue ");
		
		sleep();
		try {
		empRegPage.uspsBmadAddressRadio.click();
		} catch(Exception exception) {}
		
		try {
		empRegPage.uspsLbraAddressRadio.click();
		} catch(Exception exception) {}

		try{
			empRegPage.uspsNpcaAddressRadio.click();
		} catch(Exception exception) {}
		
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Click on appropriate USPS radio on SREG-004 page");
		try {
		empRegPage.continueButton_popUp.click();
		} catch(Exception exception) {}
		
		
		
		// SREG-521 expected, 
		sleep(2000);
		commonFunction.screenShot("EE01007", "Warning", "Launched to  SREG-521 page");
		commonFunction.clickButton("Continue ");
		
		// SREG - 011
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "Launched to  SREG-011 page");
		commonFunction.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
		commonFunction.enterTextboxContains("Employer Registration Number", "2485896");
		commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "240933037");
		empRegPage.tradeNameId_SREG011.sendKeys("Bobst Library");
		empRegPage.address1_SREG011.sendKeys("5 Washington Square S");
		empRegPage.city_SREG011.sendKeys("Albany");
		empRegPage.zip_SREG011.sendKeys("10012");
		commonFunction.enterPastDate("Acquisition Date", 10);
		commonFunction.enterPastDate("Notification date of Transfer", 11);
		commonFunction.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
		//commonFunction.enterTextboxContains("Notification date of Transfer", "04012023");
		sleep(3000);
		commonFunction.screenShot("EE01007", "Pass", "Details entered in SREG-011 page");
		commonFunction.clickButton("Continue ");
		
		
		//should be there popup
		
		// SREG - 012
		sleep(3000);
		commonFunction.screenShot("EE01007", "Pass", "Successful launch to (Business Acquisition Details)SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		sleep(2000);
		commonFunction.screenShot("EE01007", "Warning", "Expected screen name SREG-011 as on MC");
		sleep(3000);
		commonFunction.screenShot("EE01007", "Pass", "Successful launch to (Change in Legal Entity)SREG-012 page");
//		commonFunction.selectRadioQuestions("Have you changed legal entity?", "Yes ");
//		commonFunction.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", "321326774");
//		commonFunction.enterTextboxContains("Prior Employer Registration Number", "2897452");
//		commonFunction.enterTextboxContains("Date of Legal Entity change", "10012023");
//		commonFunction.enterTextboxContains("Date of Notification", "05152023");
//		commonFunction.screenShot("EE01007", "Pass", "Details entered in to SREG-012 page");
		commonFunction.clickButton("Continue ");
		
		// SREG-006
		sleep(3000);
		commonFunction.screenShot("EE01007", "Pass", "Successful launch to (Add Partnership Details)SREG-006 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG 683 ---
		sleep(2000);
		commonFunction.screenShot("EE01007", "Pass", "USPS Business address selection on SREG-683");
		sleep();
		commonFunction.selectLink(" Supporting documents like 501(c)(3) Exemptions, Lessor contracts, and Religious entity verification document, etc., can be uploaded.", "Browse");
 		sleep(2000);
 		commonFunction.uploadDoc("Sample.docx");
 		sleep(2000);
 		commonFunction.screenShot("EE01007", "Pass", "Sample document uploaded");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-800 ---
		sleep(10000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Continue ");
		
		// --- SREG-043 ---
		sleep(2000);
		commonFunction.selectCheckbox("I accept");
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-043 page");
		commonFunction.clickButton("Submit ");
		
		// --- SREG-013 ---
		sleep(10000);
		commonFunction.screenShot("EE01007", "Pass", "Successfully launched to SREG-800 page");
		commonFunction.clickButton("Home ");
		
		//commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='342452764' ORDER BY UPDATED_TS desc)");
		Thread.sleep(2000);
	     
		//--------WorkItems--------
		
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
	    
	 // --- EEWI-005---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("DOL/DTF Discrepency Task", "Pass", "Successful launch to EEWI-005 page");
	    sleep(2000);
	    
	    //commonFunction.selectDropdown("Account Status", " Liable ");
	    sleep(2000);
	    /*
	    commonFunction.enterTextbox("Date covered employment began? ", "09/05/2023");
		//commonFunction.enterPastDate("Date covered employment began? ", 270);
		 sleep(2000);
	    commonFunction.selectRadioQuestions("Financing Method", "Contributory");
	    sleep(2000);
	    */
	    empRegPage.comment.sendKeys("Test Reveiw");
	    sleep(2000);
	    commonFunction.screenShot("Dol DTF e Task Details","Pass","Details entered in to EEWI-005 page");
	    sleep(2000);
	    commonFunction.clickButtonContains("Submit ");
	    
	 // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
	    sleep();
		commonFunction.clickButtonContains("Home ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
		
		/*-------------- Work Item 2  --------------*/
		sleep(3000);
		commonFunction.waitForLoadingIconToDisappear();
		peoPage.queue.click();
		//commonFunction.waitForLoadingIconToDisappear();
		
		/*--------------WF-001   --------------*/
		sleep(3000);
		commonFunction.screenShot("Individual Work Queue","Pass","Clicked on Work Item - WF-001 ");
		sleep(3000);
		commonFunction.clearTextboxContains("Employer Registration Number");
		commonFunction.enterTextboxContains("FEIN","986432189");
		sleep(3000);
	    commonFunction.screenShot("FEINSearch","Pass","FEIN Search for Task");
	    sleep(3000);
	    commonFunction.clickButtonContains(" Search ");
	    sleep(3000);
	    commonFunction.ScrollMenu("Review Profile Data");
	    sleep(3000);
	    commonFunction.screenShot("WIClick","Pass","Clicked on Work Item - Review Profile Data");
	    sleep(3000);
	    commonFunction.clickOnLink("Review Profile Data");
	    
	    // --- WF-091 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    sleep(2000);
	    commonFunction.screenShot("Work Item Details", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item ");
	    
	 // --- EEWI-014---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Review Profile Data Task", "Pass", "Successful launch to EEWI-014 page");
	    sleep(2000);
	    //-----something need to change according tc
	   // commonFunction.selectDropdown("Account Status", " Future ");
	    sleep(2000);
	    empRegPage.commentid.sendKeys("Test Reveiw");
	    sleep(2000);
	    commonFunction.screenShot("Review Profile Data Task","Pass","Details entered in to EEWI-014 page");
	    sleep(2000);
	    commonFunction.clickButtonContains("Submit ");
	 // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
	    sleep();
		commonFunction.clickButtonContains("Home ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
		
		//------WorkItem -3
	    peoPage.queue.click();
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("wiSearch","Pass","Work item ");
	    commonFunction.enterTextboxContains("FEIN","986432189");
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
	    commonFunction.screenShot("EE01007", "Pass", "Successful launch to Work Item Details(WF-091) page");
	    sleep(3000);
	    commonFunction.clickButtonContains("Open Work Item ");
		// --- EEWI-012 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01007", "Pass", "Successful launch to Resolve incomplete data transfer Task(EEWI-012) page");
	    // commonFunction.enterTextboxContains("Federal Employer Identification Number (FEIN)", "010085930"); //897397325
		//empRegPage.predecessorErn.sendKeys( "3540934");
	    sleep();
		//commonFunction.enterTextboxContains("Employer Registration Number", "3540934");
	    //empRegPage.predecessorFein.sendKeys("010085930");
	    
	    //commonFunction.enterPastDate("Notification Date  ", 22);
	    commonFunction.selectDropdownEquals("Decision", " Continue with Transfer ");
	    empRegPage.comment.sendKeys("test Ok");
	    sleep(2000);
	    commonFunction.screenShot("EE01007", "Pass", "Entered data in EEWI-012 page");
	    commonFunction.clickButtonContains("Submit ");
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("EE01007", "Pass", "Successful launch to Work Item Completed(SUC-002) page");
	    commonFunction.clickButtonContains("Home ");
	    
	  //------WorkItem -4
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
		// --- EEWI-013 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.selectDropdown("Account Status", " Liable ");
	    sleep(2000);
	    commonFunction.screenShot("Verify Predecessor Data", "Pass", "Successful launch to EEWI-013) page");
	    empRegPage.predecessorFein.sendKeys("986432189");
	    commonFunction.selectDropdownEquals("Decision", " Continue with Transfer ");
	    //commonFunction.selectCheckbox("Transfer Business Rules");
	    empRegPage.comment.sendKeys("test Ok");
	    sleep(2000);
	    commonFunction.screenShot("Verify Predecessor Data", "Pass", "Entered data in EEWI-013 page");
	    commonFunction.clickButtonContains("Submit ");
	    // --- SUC-002 ---
	    commonFunction.waitForLoadingIconToDisappear();
	    commonFunction.screenShot("Work Item Completed.", "Pass", "Successful launch (SUC-002) page");
	    sleep();
	    commonFunction.clickButtonContains("Home ");
		sleep();
		commonFunction.waitForLoadingIconToDisappear();
	    
		//------WorkItem -5
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
		
		//------WorkItem -6
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
        
      //--------Menu----
		
		commonFunction.clickMenu("menu");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Inquiry");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Contribution Inquiry");
		sleep(2000);
		commonFunction.screenShot("Selecting Menu", "Pass", "Successfully selected menu & navigate to next page");
		commonFunction.ScrollMenu("Inquiry");
		commonFunction.clickMenu("Inquiry Employer Account");
		
		//----------SREG 050
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully landed on SREG 050 page");
		commonFunction.enterTextboxContains(" FEIN ", "161719556");  //
		//commonFunction.enterTextboxContains("Employer Registration Number", "6070139");
		sleep(2000);
		commonFunction.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "Successfully entered deatils and  click on continue");
		commonFunction.clickButton("Continue ");
		sleep(2000);
		
		//----------SREG 051
		commonFunction.screenShot("Inquiry Employer Account Information", "Pass", "Successfully landed on SREG 051 page");
		sleep(2000);
		commonFunction.screenShot("TC:EE_01_007", "Failed", "Blocked after dol dtf 1st  WorkItem , Resolve data task not coming	");
	}

}
