package com.employerContibution.EE;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_084;
import com.ui.pages.SUC_002;
import com.ui.pages.HomePage;
import com.ui.pages.SREG_004;
import com.ui.pages.SREG_008;
import com.ui.pages.SREG_043;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EE_07_001_EmployerType extends TestBase{

	@Test
	public void EE_07_001() throws Exception
	{
		commonStepDefinitions commonFuntions= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		SREG_084 sreg084 = new SREG_084(driver);
		HomePage homePage = new HomePage(driver); 
		SREG_008 sreg008 = new SREG_008(driver);
		SREG_043 sreg043 = new SREG_043(driver);
		SUC_002 suc002 = new SUC_002(driver);
		PEOPage peoPage= new PEOPage(driver);
		SREG_004 sreg004 = new SREG_004(driver);
		EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
		
		//String feinValue = "979465465";
		
		
				
		test = 
				report.createTest("EE.07.001- Verify employer can submit employer registration for employer type 'Governmental' and legal entity type 'City' and work items will be created for CSR to review.");
		commonFuntions.login(COMMON_CONSTANT.EMP_USER_1.toUpperCase(), COMMON_CONSTANT.EMP_USER_1_PASSWORD);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		commonFuntions.clickMenu("menu");
		//homePage.menu.click();
		commonFuntions.ScrollMenu("Employer Registration");
		commonFuntions.clickMenu("Employer Registration");
		sleep(2000);
		commonFuntions.screenShot("Employer Registration", "Pass", "Register Employer");
		commonFuntions.clickMenu("Register Employer");
		sleep(2000);
		//Step -4 : SREG-001
		
		Map<String, String> databaseResults = peoPage.database_SelectQuery(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EMPLOYER_TYPE = 'GOVT' AND EAN IS NOT NULL;");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		String legalName = databaseResults.get("legalName");
		System.out.println("feinValue " + feinValue);
		System.out.println("ernValue " + eanValue);
		System.out.println("legalName " + legalName);
		
		
		//clear all fields
		
		commonFuntions.forceClearTextWithElement("First Name");
		commonFuntions.forceClearTextWithElement("Last Name");
		commonFuntions.forceClearTextWithElement("Job Title");
		commonFuntions.forceClearTextWithElement("Contact Telephone Number");
		commonFuntions.forceClearTextWithElement("Email Address");
	
		//
		
		commonFuntions.clickButtonContains("Continue");
		AddPage.requiredError_empReg(" Required");
		commonFuntions.screenShot("Employer Regitration", "Pass", "Employer Regitration - Error Message");
		commonFuntions.enterTextboxContains("First Name", "Deva");
		commonFuntions.enterTextboxContains("Last Name", "Tc");
		commonFuntions.enterTextboxContains("Job Title", "tester");
		commonFuntions.enterTextboxContains("Contact Telephone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("Email Address","autoTest"+Long.toString(commonFuntions.createRandomInteger(10000,99999))+"@gmail.com");
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		
		test.info("Step: 6-- ");
		//SREG-025
		//
		//commonFuntions.forceClearTextWithElement("Federal Employer Identification Number (FEIN)");
		//commonFuntions.forceClearTextWithElement("Employer Registration Number");
		//
		Thread.sleep(3000);
		commonFuntions.clickButtonContains("Continue ");
		//AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("Employer Registration", "Pass", "Employer Registration:SREG-001_Error Message");
		sleep();
		
		//SREG-025
		test.info("Step: 7-- ");
		commonFuntions.selectDropdown("Employer Type", "Governmental");
		sleep();
		//String feinValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),9);
	//	System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.selectDropdown("Type of Legal Entity", "City");
		commonFuntions.enterTextboxContains("Employer Registration Number", eanValue);
		//String ernValue=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		//System.out.println(ernValue);
		//commonFuntions.enterTextboxContains("Employer Registration Number", ernValue);
		commonFuntions.screenShot("General Information", "Pass", "General Information:SREG-025");
		commonFuntions.clickButtonContains("Continue");
		sleep(2000);
		
		//SREG-003
		//
		commonFuntions.forceClearText(AddPage.legalNameTextBox);
		//
		commonFuntions.clickButtonContains("Continue");
		//AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("General Information", "Pass", "General Information:SREG-003_Error Message");
		sleep();
		AddPage.legalNameTextBox.sendKeys(legalName);
		commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "1/1/1799");
		commonFuntions.enterTextboxContains("Estimated or approximate number", "360");
		commonFuntions.enterTextboxContains("Date covered employment began?", "1/1/1799");
		commonFuntions.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?", "Yes");
		commonFuntions.clickButtonContains("Continue");
		//AddPage.requiredError_empReg(" Required");
		commonFuntions.screenShot("Employer Entity Information", "Pass", "Employer Entity Information:SREG-003_Error Message");
		sleep();
		commonFuntions.enterTextboxContains("If Yes, enter Legal Name", "TestAutomation");
		
//		commonFuntions.enterTextboxContains("Address Line 1", commonFuntions.createRandomInteger(10, 99)+ "Cooper Square");
//		commonFuntions.enterTextboxContains("City", "New York");
//		commonFuntions.selectDropdown("State", "New York");
		
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("Invalid date Payroll", "Pass", "Date Payroll Withheld is invalid");
		commonFuntions.errorLabel(" Date Payroll Withheld is invalid");
		commonFuntions.errorLabel(" Date Covered Employment Began is invalid");
		sleep();
		WebElement payrollDate = driver.findElement(By.xpath("//input[@id='firstPayrollDtId']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='';", payrollDate);
		commonFuntions.enterTextboxContains("What is the date of the first payroll", "7/1/2022");
		sleep();
		WebElement empDate = driver.findElement(By.xpath("//input[@id='covEmploymentBeginDtId']"));
		js.executeScript("arguments[0].value='';", empDate);
		commonFuntions.enterTextboxContains("Date covered employment began?", "7/1/2022");
		commonFuntions.screenShot("No Error Message", "Pass", "Employer Entity Information  (SREG-003)");
		commonFuntions.clickButtonContains("Continue");
		Thread.sleep(5000);
		
		////If already address existed (Business Physical Address Details)
		try {
			sreg008.businesssreg007.isDisplayed();
			commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
			sreg008.EditBtnsreg007.click();
		} catch(Exception exception) {
			sleep(2000);
			System.out.println("SREG-007 screen is not displayed");
		}
		
		sleep(2000);
		
		////
		
		commonFuntions.screenShot("Expected : SREG-008", "Pass", "SREG-008 Page is displayed");
		
		test.info("Step: 12-- SREG-008");
		commonFuntions.clickButtonContains("Continue");
		sleep();
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Required error message is displayed");
		
		test.info("Step: 13-- SREG-008");
		commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 99th");
		//commonFuntions.enterTextboxContains("Address Line 2 ", "St 9th floor");
		commonFuntions.enterTextboxContains("City ", "gw");
		commonFuntions.selectDropdown("Country", " United States ");
		commonFuntions.enterTextboxContains("State", "--SELECT--");
		commonFuntions.selectDropdown("County", " Albany ");
		commonFuntions.clickButton("Continue ");
		//AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Add Primary Business Physical Address:SREG-008_Error Message");
		sleep();
		
		test.info("Step: 14-- SREG-008");
		commonFuntions.enterTextboxContains("State", " New York ");
		commonFuntions.enterTextboxContains("Zip Code", "12210");
		commonFuntions.selectDropdown("County", "--SELECT--");
		commonFuntions.clickButton("Continue ");
		//AddPage.requiredError_empReg("Required");
		commonFuntions.screenShot("Add Primary Business Physical Address", "Pass", "Add Primary Business Physical Address:SREG-008_Error Message");
		sleep();
		
		test.info("Step: 15-- SREG-008");
		commonFuntions.clickButton(" Finish Later ");
		Thread.sleep(2000);
		commonFuntions.clickButtonContains(" Yes ");
		Thread.sleep(5000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		commonFuntions.screenShot("Homepage", "Pass", "Add Primary Business Physical Address:Wel-10000_page is displayed");
		
		test.info("Step: 16-- Navigate to Home Page>Employer Registration> Incomplete Registration");
		Thread.sleep(5000);
		//homePage.menu.click();
		commonFuntions.clickMenu("menu");
		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.clickMenu("Incomplete Registration");
		Thread.sleep(2000);
		commonFuntions.screenShot("Search for Finish Later Applications", "Pass", "SREG-101 : Page is displayed");
		
		test.info("Step: 17&18-- SREG_101");
		commonFuntions.enterTextboxContains("FEIN", "121121121");
		commonFuntions.clickButton(" Search ");
		commonFuntions.screenShot("Search for Finish Later Applications", "Pass", "No records found");
		sleep(4000);
		
		test.info("Step: 19 --  Enter details on \"Search for Finish Later Applications\" screen");
		commonFuntions.forceClearText(sreg084.feinField);
		sleep(2000);
		commonFuntions.enterTextboxContains("FEIN", feinValue); 
		commonFuntions.clickButton(" Search ");
		sleep(2000);
		commonFuntions.screenShot("Search for Finish Later Applications", "Pass", "table is visible");
		
		test.info("Step: 20-- click on legal name");
		sleep(2000);
		//sreg008.listFirstItemSreg101.click();
		sreg008.Sreg101Results(legalName);
		sleep(5000);
		commonFuntions.screenShot("registration", "Pass", "SREG-001 screen is displayed");
		
		test.info("Step: 21 --SREG-001");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("General Information", "Pass", "SREG-025 screen is displayed");
		
		test.info("Step: 22 --SREG-025");
		commonFuntions.clickButton("Continue ");
		sleep(2000);
		commonFuntions.screenShot("Employer Entity Information", "Pass", "SREG-003 screen is displayed");
		
		test.info("Step: 23 --SREG-003");
		//commonFuntions.enterTextboxContains("Business Phone Number",Long.toString(commonFuntions.createRandomInteger(10000000,99999999))+Long.toString(commonFuntions.createRandomInteger(10,99)));
		//commonFuntions.enterTextboxContains("What is the date of the first payroll", "7/1/2022");
		//commonFuntions.enterTextboxContains("Estimated or approximate number", "360");
		//commonFuntions.enterTextboxContains("Date covered employment began?", "7/1/2022");
		//commonFuntions.selectRadioQuestions("Is your entity a legally established component or subdivision of another entity, which is responsible for the unemployment insurance liability of this entity?", "Yes");
		//sleep();
		//commonFuntions.enterTextboxContains("If Yes, enter Legal Name", "TestAutomation");
		commonFuntions.clickButtonContains("Continue");
		
	////If already address existed (Business Physical Address Details)
			try {
				sreg008.businesssreg007.isDisplayed();
				commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
				sreg008.EditBtnsreg007.click();
			} catch(Exception exception) {
				sleep(2000);
			}
			
			sleep(2000);
			////
			commonFuntions.screenShot("Expected : SREG-008", "Pass", "SREG-008 Page is displayed");
			
			test.info("Step: 24&25 --SREG-008");
			commonFuntions.enterTextboxContains("Address Line 1 ", "29 W 35th");
			commonFuntions.enterTextboxContains("City ", "NY");
			commonFuntions.selectDropdown("Country", " United States ");
			commonFuntions.enterTextboxContains("State", " New York ");
			commonFuntions.enterTextboxContains("Zip Code", "12210");
			commonFuntions.selectDropdown("County", " Albany ");
			sleep(1000);
			commonFuntions.clickButton("Continue ");
			sleep(2000);
			
		
			test.info("Step: 26");
			try {
				sreg008.firstradiobuttonVerifyAddPopup.click();
				sleep(2000);
				empRegPage.continueButton_popUp.click();
				sleep(2000);
				commonFuntions.screenShot("Business Physical Address Details", "Pass", "SREG-007 screen is displayed");
			} catch (Exception e) {
				System.out.println("pop up not appeared");
			}
			commonFuntions.clickButton("Continue ");
			sleep(2000);
			commonFuntions.screenShot("Employer Contact Details", "Pass", "SREG-004 screen is displayed");
			
			test.info("Step: 27&28");
			commonFuntions.selectRadioQuestions("Business Mailing Address", "Same as Primary Business Physical Address");
			commonFuntions.selectRadioQuestions("Location of Books and Records",
					"Same as Primary Business Physical Address");
			sreg004.listOfFirstname.get(0).sendKeys("deva");
			sreg004.listOfLastName.get(0).sendKeys("tcs");
			commonFuntions.selectRadioQuestions("Notice of Potential Charges (LO400) Address",
					"Same as Primary Business Physical Address");
			sreg004.listOfFirstname.get(1).sendKeys("dev");
			sreg004.listOfLastName.get(1).sendKeys("test");
			
			commonFuntions.clickButton("Continue ");
			sleep(2000);

			try {
				commonFuntions.selectRadioQuestions("bmad Address", "29");
				commonFuntions.selectRadioQuestions("lbra Address", "29");
				commonFuntions.selectRadioQuestions("npca Address", "29");
				Thread.sleep(2000);
				sreg004.popUpContinueButton.click();
			} catch (Exception e) {
				System.out.println("pop up not appeared");
			}
			commonFuntions.selectRadioQuestions("Do you want all of your mail directed to your Agent – C/O ?",
					"No ");
			commonFuntions.clickButton("Continue ");
			sleep(2000);
			commonFuntions.screenShot("Employer Verify Contact Details", "Pass", "SREG-521 screen is displayed");
			
			commonFuntions.clickButton("Continue ");
			sleep(2000);
			commonFuntions.screenShot("Upload Documents", "Pass", "SREG-683 screen is displayed");
			
			/*test.info("Step: 29&30&31");
			sreg084.browseLink.click();
			Thread.sleep(2000);
			commonFuntions.uploadDoc("Sample.docx");
			Thread.sleep(2000);
			commonFuntions.screenShot("Upload Documents", "Pass",
					"Upload Document Section");
			Assert.assertTrue(sreg084.uploadDocSec.getText().contains("Uploaded Documents"));
			*/
			test.info("Step: 32");
			sleep(2000);
			commonFuntions.clickButton("Continue ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Review Registration Details", "Pass", "SREG-800 page displayed");
			
			test.info("Step: 33");
			commonFuntions.clickButton("Continue ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Statement of Acknowledgement", "Pass", "SREG-043 page displayed");
			
			test.info("Step: 34");
			//sreg043.submitterCommentsField.sendKeys("test dev co");
			commonFuntions.selectCheckbox("I accept");
			commonFuntions.clickButton("Submit ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Employer Registration Confirmation", "Pass", "SREG-013 page displayed");
			
			test.info("Step: 35");
			commonFuntions.clickButton("Home ");
			Thread.sleep(2000);
			commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
			commonFuntions.screenShot("Homepage", "Pass", "Homapage page displayed");
			
			test.info("Step: 36 -- Login as a CSR and Navigates to Main Menu -> MyQ");
			//commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
			commonFuntions.screenShot("Business Acquisition", "Pass", "logged In");
			test.info("CSR Navigate to Main Menu -> MyQ");
			Thread.sleep(5000);
			PEOPage.queue.click();
			Thread.sleep(3000);
			commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
			
			test.info("Step: 37 -- ");
			commonFuntions.enterTextboxContains("FEIN", feinValue);
			commonFuntions.clickButtonContains(" Search ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
			
			test.info("Step: 38 -- ");
			Thread.sleep(2000);
			sreg084.reviewemployertypelink.click();
			commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
			Thread.sleep(2000);
			commonFuntions.clickButtonContains("Open Work Item ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
			Thread.sleep(2000);
			
			test.info("Step: 39 -- ");
			test.info("Step: 40 -- ");
			test.info("Step: 41 -- ");
			
			test.info("Step: 42 -- ");
			commonFuntions.enterTextboxContains("Date Covered Employment began? ", "05/07/2022");
			sleep(2000);
			sreg043.EEWI002CommentsField.sendKeys("Review em comment");
			sleep(2000);
			commonFuntions.clickButtonContains("Submit ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
			
			test.info("Step: 43 -- ");
			Assert.assertTrue(suc002.screenIdText.isDisplayed());
			Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
			suc002.homeButton.click();
			Thread.sleep(5000);
			commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
			
			commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
			Thread.sleep(2000);
			
			test.info("Step: 44 -- ");
			Thread.sleep(2000);
			PEOPage.queue.click();
			Thread.sleep(3000);
			commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
			
			test.info("Step: 45 -- ");
			//commonFunction.enterTextboxContains("FEIN", feinValuemanual);
			//commonFunction.clickButtonContains(" Search ");
			//Thread.sleep(2000);
			
			test.info("Step: 46 -- DOL-DTF");
			//click manually on work item "REVIEW POTENTIAL DUPLICATES"
			Thread.sleep(2000);
			//sreg084.dolDTFlink.click();
			commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
			Thread.sleep(2000);
			commonFuntions.clickButtonContains("Open Work Item ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
			Thread.sleep(2000);
			
			test.info("Step: 47 -- ");
			commonFuntions.enterTextboxContains("Date Covered Employment began? ", "05/07/2022");
			Thread.sleep(1000);
			commonFuntions.selectRadioQuestions("Financing Method", "Contributory");
			Thread.sleep(1000);
			commonFuntions.selectDropdown("Account Status", " Liable ");
			sleep(2000);
			sreg043.EEWI002CommentsField.sendKeys("Dol DTF Cm ");
			commonFuntions.clickButtonContains("Submit ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
			
			test.info("Step: 48 -- ");
			Assert.assertTrue(suc002.screenIdText.isDisplayed());
			//Assert.assertTrue(suc002.reviewEmployeerTypeSuccessmsg.isDisplayed());
			suc002.homeButton.click();
			Thread.sleep(5000);
			commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
			commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
			Thread.sleep(2000);
			
			test.info("Step: 49 -- ");
			Thread.sleep(2000);
			PEOPage.queue.click();
			Thread.sleep(3000);
			commonFuntions.screenShot("Business Acquisition", "Pass", "WF-001 screen is visible");
			
			test.info("Step: 50 -- ");
			commonFuntions.enterTextboxContains("FEIN", feinValue);
			commonFuntions.clickButtonContains(" Search ");
			Thread.sleep(2000);
			
			test.info("Step: 51 -- ");
			commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
			Thread.sleep(2000);
			test.info("Step: 52 -- ");
			commonFuntions.clickButtonContains("Open Work Item ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Review Employer Type Task Details", "Pass", "EEWI-002 screen is visible");
			Thread.sleep(2000);
			commonFuntions.clickButtonContains("Submit ");
			Thread.sleep(2000);
			commonFuntions.screenShot("Work Item Completed.", "Pass", "SUC-002 screen is visible");
			
			test.info("Step: 53 -- ");
			Thread.sleep(2000);
			Assert.assertTrue(suc002.screenIdText.isDisplayed());
			Assert.assertTrue(suc002.reviewCommentsTypeSuccessmsg.isDisplayed());
			suc002.homeButton.click();
			Thread.sleep(5000);
			commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
			
			test.info("Step: 54 -- ");
			commonFuntions.clickMenu("menu");
			commonFuntions.ScrollMenu("Inquiry");
			commonFuntions.clickMenu("Inquiry");
			sleep(2000);
			commonFuntions.ScrollMenu("Contribution Inquiry");
			commonFuntions.clickMenu("Contribution Inquiry");
			sleep(2000);
			commonFuntions.ScrollMenu("Inquiry Employer Account");
			commonFuntions.clickMenu("Inquiry Employer Account");
			sleep(2000);
			commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "SREG-050 screen is displayed");
			sleep(2000);
			
			test.info("Step: 55 -- ");
			commonFuntions.enterTextboxContains("FEIN", feinValue);
			sleep(2000);
			commonFuntions.clickButtonContains("Continue ");
			sleep(2000);
			
			test.info("Step: 56 -- ");
			Thread.sleep(5000);
			commonFuntions.screenShot("Inquiry Employer Account Information", "Pass", "SREG-051 screen is displayed");
			
			test.info("Step: 57 -- ");
			Thread.sleep(1000);
			commonFuntions.clickButtonContains("Previous ");
			Thread.sleep(3000);
			commonFuntions.screenShot("Inquiry Employer Account - Enter ERN", "Pass", "SREG-050 screen is displayed");
	
			test.info("Step: 58 -- ");
			commonFuntions.clickButtonContains("Home ");
			Thread.sleep(3000);
			driver.navigate().refresh();
			Thread.sleep(5000);
			commonFuntions.screenShot("Joint Employment/Management Agreement Arrangement Confirmation", "Pass",
					"Homepage is displayed");
	
	}
	
}
