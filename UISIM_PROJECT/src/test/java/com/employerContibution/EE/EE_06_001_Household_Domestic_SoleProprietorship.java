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

public class EE_06_001_Household_Domestic_SoleProprietorship extends TestBase{

	@Test()
	public void EE_01_004_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();	/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		
		test = report.createTest("EE.06.001 Verify CSR can submit employer registration for employer type 'Household/Domestic' and legal entity type 'Sole Proprietorship (Individual)' and work items will be created for CSR to review.");
		
//		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
//				"SELECT FEIN FROM T_EMPLOYER_ACCOUNT tea  WHERE FEIN IN (SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd ) GROUP BY FEIN HAVING COUNT(*)>1 " , "FEIN"); 
//		String FEIN = databaseResults1.get("FEIN");
		
		String FEIN = "TF0656697";
		System.out.println("FEIN = " +FEIN);
		test.log(Status.INFO, "FEIN : : "+FEIN);
		//String EntityName = prop.getProperty("Entity");
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		
		
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");		
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
//		cf.clickMenu("Menu"); sleep();
		cf.safeJavaScriptClick(empPage.menuButtonHomepage);
		sleep(2000);
		cf.ScrollMenu("Employer Registration");sleep();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Employer Registration");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickMenu("Register Employer"); sleep(3000);
		cf.clickButtonContains("Continue"); sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.selectDropdown("Employer Type", " Household/Domestic ");
		cf.enterTextboxContains("(FEIN)", FEIN); 
		cf.screenShot("file1","Pass", "Searching with FEIN "); 
		cf.selectDropdown("*Type of Legal Entity"," Sole Proprietorship (Individual) "); 
		cf.selectDropdown("Source", " NYS-100 (paper) ");sleep(2000);
		cf.selectDropdown("Source Type", " NYS-100 ");sleep(2000);
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.clickButtonContains("Continue");sleep(4000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Menu", "Pass", "Employer Registration");
		cf.populateListbox("Legal Name", "hjdbfj");sleep(2000);
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		sleep();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value.click();
		
		cf.enterTextboxContains("Total number of persons employed in your home? ", "4");
		cf.selectRadioQuestions("Will you withhold New York State Income Tax from these employees?", "Yes ");
		
		cf.clickButtonContains("Continue");sleep();
		
		cf.waitForLoadingIconToDisappear();

		cf.enterTextboxContains("Address Line 1","20 Madison AVE");sleep(2000);
		cf.enterTextboxContains("City","Albany");sleep(2000);
		cf.enterTextboxContains("Zip Code","10003");sleep(2000);
		cf.selectDropdown("County", " Albany ");
		cf.clickButtonContains("Continue");sleep();
		cf.waitForLoadingIconToDisappear();
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		
		
		sleep();
		cf.screenShot("", "Pass", "Employer Registration");
		/*-----------------SREG-007---------------*/
		cf.clickButtonContains("Continue");sleep(2000);
		cf.waitForLoadingIconToDisappear();
		/*-----------------SREG-004---------------*/
		//cf.clickButtonContains("Continue");
		sleep(3000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("TPRRegister7", "Pass", "Navigated to SREG-004 page and entering the details");
		cf.selectRadioQuestions("Business Mailing Address", "Other");
		cf.enterTextboxContains("Address Line 1 ","325 Test Road");
		cf.selectDropdown("Country", " Afghanistan ");
		cf.enterTextboxContains("City ", "Test");
		cf.enterTextboxContains("Zip Code", "34276");
		sleep();
		cf.selectRadioQuestions("Location of Books and Records", "Same as Primary Business Physical Address");
		sleep();
		cf.clickButtonContains("Continue");
		sleep(4000);
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}
		catch(Exception e) {
			System.out.println("No Address Pop up");
		}
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*-----------------SREG-521---------------*/
		cf.clickButtonContains("Continue");	
		sleep(2000);
		//cf.screenShot("Change in Legal Entity", "Pass", "Change in Legal Entity(SREG-012");
		//cf.selectRadioQuestions("Have you changed legal entity?", "No ");
		//cf.clickButtonContains("Continue");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		/*----------------------SREG-713---------------*/
		
		String priorFein = "643523422";
		String priorERN = "9200009";
		cf.selectRadioQuestions("Have you changed legal entity?", "Yes ");
		sleep(2000);
		cf.enterTextboxContains(" Prior Federal Employer Identification Number (FEIN) ", priorFein);
		cf.enterTextboxContains("Prior Employer Registration Number", priorERN);
		cf.enterTextboxContains("Date of Legal Entity change", "09302022");
		cf.enterCurrentDate("Date of Notification");
		cf.clickButtonContains("Continue");	
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*----------------------SREG-006---------------*/
		cf.screenShot("TPRRegister19", "Pass", "Navigated to SREG-006 page");
		cf.enterTextboxContains("SSN", "453647568");
		cf.enterTextboxContains("First Name", "dsbjh");
		cf.enterTextboxContains("Last Name", "dfhsd");
		cf.selectDropdown("Title", " Household Employer ");
		
		cf.enterTextboxContains("Address Line 1 ", "222 Madison AVE");
		cf.enterTextboxContains("City ", "Albany");
		cf.enterTextboxContains("Zip Code", "34226");
		sleep();
		cf.clickButton("Continue ");
		sleep(4000);
		try {
			cf.safeJavaScriptClick(empPage.uspsCommonButton);
			sleep();
			cf.safeJavaScriptClick(empPage.continueButton_popUp);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		
		/*----------------------SREG-005---------------*/
		
		sleep(2000);
		cf.screenShot("add partner confirm", "Pass", "))");
		cf.clickButtonContains("Continue");
		/*----------------------SREG-683---------------*/
		
		sleep(2000);
		cf.selectLink("Supporting documents like", "Browse");
		sleep(3000);
		cf.uploadDoc("Sample");
		sleep(2000);
		cf.screenShot("Upload Documents", "Pass", "Upload Documents(SREG-683)");
		cf.clickButtonContains("Continue");
		/*----------------------SREG-800---------------*/
		sleep(5000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Review Registration Details", "Pass", "Review Registration Details(SREG-800)");
		cf.clickButtonContains("Continue");
		sleep(3000);
		cf.waitForLoadingIconToDisappear();
		/*----------------------SREG-043---------------*/
		cf.selectCheckbox("I accept");
		cf.screenShot("Statement of Acknowledgement", "Pass", "Statement of Acknowledgement(SREG-043)");
		sleep(2000);
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("Employer Registration Confirmation", "Pass", "Employer Registration Confirmation(SREG-013)");
		cf.clickButtonContains("Exit");
//		throw new Exception();

		//Assigning user to WI Review emp type..................
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

		//Resolving 1 WI................
		PEOPage.queue.click();
		sleep();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("FEIN",FEIN);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search"); Thread.sleep(2000);
		cf.screenShot("review employer type","Pass","emp type");
		cf.clickOnLink("Review Employer Type");

		Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("","Pass","review emp type  ");

		cf.selectDropdown("*Account Status ", "Liable");
		cf.enterTextboxContains("Comment", "registration in process");
		cf.clickButtonContains("Submit"); Thread.sleep(2000);
		cf.screenShot("GeneralInfo","Pass","General Information");
		cf.clickButtonContains("Home");


		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+FEIN+"' ORDER BY UPDATED_TS desc)"); Thread.sleep(2000);

		//Resolving 2ND WI ................
		PEOPage.queue.click(); Thread.sleep(15000);
		cf.enterTextboxContains("FEIN",FEIN);
		cf.screenShot("FeinSearch","Pass","feinSearch");
		cf.clickButtonContains("Search"); Thread.sleep(2000);
		cf.screenShot("DOL DTF Discrepancy","Pass","emp type");
		cf.clickOnLink("DOL DTF Discrepancy");

		Thread.sleep(2000); cf.clickButtonContains("Open Work Item");
		Thread.sleep(2000);
		cf.screenShot("","Pass","DOL DTF ");
		cf.selectDropdown("Quarter", "1");sleep();
		cf.selectDropdown("Year", "2023");sleep();
		cf.selectRadioQuestions("If you are not liable under the Unemployment Insurance law for agricultural employment, do you wish to elect voluntary coverage?", "Yes");
		cf.selectDropdown("*Account Status ", "Liable");
		cf.enterTextboxContains("Comment", "registration in process");
		cf.clickButtonContains("Submit"); Thread.sleep(2000);
		cf.screenShot("GeneralInfo","Pass","General Information");
		cf.clickButtonContains("Home");

		//Verify Registered employer in Inquery page 	...........
		em.Inquery_fein(FEIN);
		test.log(Status.PASS, "Clicked on Home button");


	}
}