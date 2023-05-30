package com.employerContibution.EE;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_13_002_CSR_Can_Submit_Agriculture extends TestBase {

	@Test()
	public void EE_13_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest(
				"EE.13.002 - Verify CSR can submit employer registration for employer type 'Agricultural (NYS100AG)'Short Form flow");

		commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		sleep();
		commonFuntions.clickMenu("Create Short Form Resgistration Review Task");
		sleep(3000);
		
		/*-------------------EESR-001-------------------*/
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("FEIN : : "+feinValue);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Navigated to EESR-001 page and filling the form");
		commonFuntions.enterTextboxContains("Legal Name", "Abhinav J");
		commonFuntions.enterTextboxContains(" FEIN ", feinValue);
		commonFuntions.selectDropdown("Source", " IA602 ");
		empPage.browserLink.click();
		sleep();
		commonFuntions.uploadDoc("Sample");
		sleep();
		commonFuntions.screenShot("EmpRegister2", "Pass", "Form filled");
		commonFuntions.clickButton("Submit ");
		commonFuntions.waitForLoadingIconToDisappear();
		commonFuntions.clickButton("Home ");
		sleep(10000);
		/*-------------------Home Page-------------------*/
		commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='"+feinValue+"' ORDER BY UPDATED_TS desc)");
		commonFuntions.screenShot("EmpRegister15", "Pass", "Navigated to Home page and click on My-Q");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Click on My-Q");
		PEOPage.queue.click();
		commonFuntions.waitForLoadingIconToDisappear();
		sleep(3000);
		/*-------------------WF-001-------------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated to WF-001 page and search for WI");
		commonFuntions.enterTextbox("FEIN", feinValue);
		commonFuntions.clickButton(" Search ");
		sleep(6000);
		commonFuntions.screenShot("EmpRegister5", "Pass", "Short form register Review Work item");
		empPage.shor_Form_Registration_Review_Link.click();
		sleep(6000);
		
		/*-------------------WF-091-------------------*/
		commonFuntions.screenShot("EmpRegister6", "Pass", "Navigated to WF-091 page and open work item");
		commonFuntions.clickButton("Open Work Item ");
		sleep(3000);
		/*-------------------SREG-818-------------------*/
		commonFuntions.screenShot("EmpRegister7", "Pass", "Navigated to SREG-818 and click on short form Register Link");
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
		sleep(4000);
		/*-------------------SREG-025-------------------*/
		
		String feinValue2 = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println("FEIN2 : : "+feinValue2);
		commonFuntions.screenShot("EmpRegister9", "Pass", "Navigated to SREG-025 page and fill the form");
		commonFuntions.selectDropdown("Employer Type", " Agricultural ");
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue2);
		commonFuntions.selectDropdown("Type of Legal Entity", " Partnership ");
		commonFuntions.selectDropdown("Source", " IA602 ");
		commonFuntions.selectDropdown("Source Type", " Correspondence ");
		sleep();
		commonFuntions.screenShot("EmpRegister10", "Pass", "Form filled");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
		/*-------------------SREG-003-------------------*/
		commonFuntions.screenShot("EmpRegister11", "Pass", "Navigated to SREG-003 page and filling the form");
		empPage.legalNameTextBox.sendKeys("sdghs sdh s");
		commonFuntions.enterTextboxContains("Trade Name or Doing Business As (DBA)", "Third party");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "4536456787");
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value_1.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_employed_4_value_2023.click();
		
		commonFuntions.enterTextboxContains("Total number of covered employees", "29");
		sleep();
		commonFuntions.screenShot("EmpRegister12", "Pass", "Form filled");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-004-------------------*/
		commonFuntions.screenShot("EmpRegister13", "Pass", "Navigated to SREG-004 page and filling the form");
		commonFuntions.enterTextboxContains("Address Line 1 ", "Fake Address");
		commonFuntions.enterTextboxContains("City ", "NY");
		commonFuntions.enterTextboxContains("Zip Code", "23432");
		commonFuntions.selectDropdown("County", " Albany ");
		sleep();
		commonFuntions.screenShot("EmpRegister14", "Pass", "Form Filled");
		commonFuntions.clickButton("Continue ");
		
		
//		try {
//			commonFuntions.safeJavaScriptClick(empPage.uspsAddressRadio_20_square);
//			commonFuntions.screenShot("EmpRegister15", "Pass", "Address pop up");
//			sleep();
//			commonFuntions.safeJavaScriptClick(empPage.continueButton_popUp);
//		}catch(Exception e ) {
//			System.out.println("Pop up not displayed");
//		}
		sleep(4000);
		/*-------------------SREG-521-------------------*/
		commonFuntions.screenShot("EmpRegister16", "Pass", "Navigated to SREG-521");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		
		/*-------------------SREG-011-------------------*/
		commonFuntions.screenShot("EmpRegister17", "Pass", "Navigated to SREG-011");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-012-------------------*/
		commonFuntions.screenShot("EmpRegister18", "Pass", "Navigated to SREG-012");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-683-------------------*/
		commonFuntions.screenShot("EmpRegister19", "Pass", "Navigated to SREG-683");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-800-------------------*/
		commonFuntions.screenShot("EmpRegister20", "Pass", "Navigated to SREG-800");
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		/*-------------------SREG-043-------------------*/
		commonFuntions.screenShot("EmpRegister21", "Pass", "Navigated to SREG-043 page click on submit");
		commonFuntions.clickButton("Submit ");
		sleep(30000);
		/*-------------------SREG-013-------------------*/
		commonFuntions.screenShot("EmpRegister22", "Pass", "Navigated to SREG-013 page click on Exit");
		commonFuntions.clickButton("Exit ");
		sleep(10000);
		
		/*-------------------Home Page-------------------*/
		commonFuntions.screenShot("EmpRegister23", "Pass", "Navigated to Home Page page click on Inquiry Employer Account");
		commonFuntions.clickMenu("Menu");
//		commonFuntions.clickMenu("Inquiry");
		sleep();
		commonFuntions.safeJavaScriptClick(empPage.inquiry_dropDown_Menu);
		sleep();
//		commonFuntions.clickMenu("Contribution Inquiry");
		commonFuntions.safeJavaScriptClick(empPage.Contribution_dropDown_Menu);
		sleep();
		commonFuntions.screenShot("EmpRegister23", "Pass", "Clicking on Inquiry Employer Account");
		commonFuntions.clickMenu("Inquiry Employer Account");
		sleep(4000);
		
		/*-------------------SREG-050-------------------*/
		commonFuntions.screenShot("EmpRegister24", "Pass", "Navigated to SREG-050 page and validating the FEIN");
		commonFuntions.enterTextboxContains(" FEIN ", feinValue2);
		sleep();
		commonFuntions.clickButton("Continue ");
		sleep(4000);
		String fein = empPage.FEIN_Value_Text_SREG_051.getText();
		System.out.println("FEIN from SREG-051 : : "+fein);
		Assert.assertEquals(feinValue2, fein.replace("-", ""));
		commonFuntions.screenShot("EmpRegister25", "Pass", "Click on previous button");
		commonFuntions.clickButton("Previous ");
		sleep();
		commonFuntions.screenShot("EmpRegister26", "Pass", "Click on Home button");
		commonFuntions.clickButton(" Home ");
		
		
		
	}
}
