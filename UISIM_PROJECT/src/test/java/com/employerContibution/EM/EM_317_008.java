package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_317_008 extends TestBase{

	@Test
	public void EM_317_008() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		EmployerRegisterPage EmpRegPage = PageFactory.initElements(driver, EmployerRegisterPage.class);
		test = 
				report.createTest("EM.317.008. Verify CSR is able to search and update FEIN and Source of FEIN update 'IA217' ");
		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		sleep();
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		sleep();
		cf.screenShot("NavigationToMaintainFEIN", "Pass", "Select Maintain FEIN");
		sleep();
		cf.clickMenu("Maintain FEIN");
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn("SELECT EAN,* FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "EAN");
		String eanValue = databaseResults.get("EAN"); 
		System.out.println("The EAN Value is:"+ eanValue);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		sleep(2000);
		test.log(Status.INFO, "EAN::"+eanValue);
		cf.screenShot("EnterERN", "Pass", "Maintain Federal Employer Identification Number (FEIN) - Enter ERN");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn("SELECT FEIN,* FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "FEIN");
		String feinValue = databaseResults1.get("FEIN"); 
		System.out.println("The FEIN Value is:"+ feinValue);
		cf.enterTextboxContains("New FEIN", feinValue);
		cf.enterTextboxContains("Please Re-enter FEIN", feinValue);
		test.log(Status.INFO, "EAN::"+feinValue);
		cf.selectDropdown("Suffix", "US");
		sleep();
		cf.selectDropdown("Confirm Suffix", "US");
		sleep();
		cf.selectDropdown("Source", " IA217 Notice of Change in Employer");
		sleep();
		cf.selectDropdown("Source Type", "IA217 – Notice of Change in Employer");
		sleep();
		EmpRegPage.commentBox_MyQ.sendKeys("Updated Fein");
		sleep(2000);
		AddPage.browserLink.click();
		sleep(3000);
		cf.uploadDoc("TESTINGEL.docx");
		Thread.sleep(4000);
		cf.screenShot("UpdatedFein", "Pass", "Update Federal Employer Identification Number (FEIN)");
		cf.clickButtonContains("Submit ");
		sleep(2000);
		try {
			cf.clickButtonContains("Yes");
		}
		catch(Exception e) {
			System.out.println("Confirm the fein entered");
		}
		sleep(5000);
		cf.screenShot("ConfirmationMessage", "Pass", "Maintain Federal Employer Identification Number (FEIN) Confirmation");
		cf.clickButtonContains("Home ");
		cf.waitForLoadingIconToDisappear();
				
	}
}
