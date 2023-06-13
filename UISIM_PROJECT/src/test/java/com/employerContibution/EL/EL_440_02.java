//---------------------------------------ANKAN DAS---------------------------------------
package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class EL_440_02 extends TestBase {
	@Test(priority = 1, description = "EL.440.02 - Verify CSR can search PEO and update PEO conversion ' PEO Individual'  to PEO Group' ", groups = {COMMON_CONSTANT.REGRESSION })
	public void Test_EL_440_02() throws Exception {
		test = report.createTest("EL.440.02 - Verify CSR can search PEO and update PEO conversion ' PEO Individual'  to PEO Group' ");

		commonStepDefinitions commonFunctions = new commonStepDefinitions();
		PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
		
		Map<String, String> databaseResults = commonFunctions.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOIR' AND FEIN IS NOT NULL AND LENGTH(FEIN)=9 ORDER BY UPDATED_TS DESC",
				"FEIN");
		String feinValue = databaseResults.get("FEIN");
		System.out.println("feinValue is " + feinValue);

		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		commonFunctions.login(COMMON_CONSTANT.CSR_USER_2, COMMON_CONSTANT.CSR_USER_2_PASSWORD);
		commonFunctions.screenShot("ApplicationLogin", "Pass", COMMON_CONSTANT.LOGIN_SUCCESS);

		commonFunctions.clickMenu("Menu");
		commonFunctions.ScrollMenu("Professional Employer Organization (PEO)");
		peoPage.menuPeo.click();
		commonFunctions.screenShot("Menu", "Pass", "Manage PEO");
		driver.findElement(
				By.xpath(".//span[@id='ProfessionalEmployerOrganization(PEO)']//following::*[.='Manage PEO'][1]"))
				.click();
		sleep();

		commonFunctions.clickOnLinkAnchorTag(" ADVANCED SEARCH");
		commonFunctions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		//commonFunctions.enterTextboxContains("PEO Name", "ABC");
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Searched for PEO with FEIN");
		sleep();

		commonFunctions.clickButtonContains("Search");
		commonFunctions.screenShot("PeoSearchPage", "Pass", "Searched PEO List");
		
		commonFunctions.selectRadioInTable(feinValue, 1, 1, "Search for PEO");
		sleep();
		commonFunctions.clickButtonContains("Continue");
		commonFunctions.screenShot("ManageExemptPeoPage", "Pass", "Group Exempt PEO Details");
		
		//---MPEO-003---
		
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",
							// driver.findElement(By.xpath("//mat-label[contains(.,'PEO Conversion')]")));		 		
		peoPage.selectionPeoDropdown.click();
		peoPage.conversionPeoDropdown.click();
		commonFunctions.screenShot("ManageIndividualPeoPage", "Pass", "Select drop down");
		commonFunctions.clickButtonContains("  CONVERT ");
		sleep(2000);
		commonFunctions.screenShot("GeneralInformationPeoPage", "Pass", "General Information");
		
		//---PEO-002---
		commonFunctions.screenShot("GeneralInformationPeoPage", "Pass", "Save &amp; Continue button clicked from General Information page");
		commonFunctions.clickButtonContains("Save & Continue ");
		commonFunctions.screenShot("UnemploymentInsuranceAccountPage", "Pass", "Unemployment Insurance Account Details");
		
		
		//---EAS-001---
		try {
	    	 peoPage.peoRadioButton.click();
		     commonFunctions.selectRadioInTable(feinValue,1, 1,"Unemployment Insurance Account Details");
		     }
		catch(Exception e) {}
		commonFunctions.clickButtonContains("Save & Continue ");
		commonFunctions.screenShot("UnemploymentInsuranceAccountPage", "Pass", "Unemployment Insurance Account Details");
		sleep();
		
		/*------------- PEO_003 ------------------*/
	     
		sleep();
		commonFunctions.screenShot("ManagePeo7", "Pass", "Navigated to Address Information page");
		peoPage.addressLine1.clear();
		peoPage.addressLine2.clear();
		peoPage.addressLine1.sendKeys("addressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		peoPage.addressLine2.sendKeys("addressLine2"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		
		peoPage.listCurrentAddressLine1.sendKeys("listCurrentAddressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		peoPage.listCurrentAddressLine2.sendKeys("listCurrentAddressLine2"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		peoPage.listCurrentAddressCity.sendKeys("NewYork");
		peoPage.listCurrentAddressZip.sendKeys("13420");
		commonFunctions.screenShot("PrimaryAddress", "Pass", "List the Current address");
		commonFunctions.clickButtonContains("Save & Continue ");
		
		try {
			peoPage.uspsAddress.click();
			sleep();
			peoPage.currentAdditionalAddress.click();
			sleep();
			peoPage.UspsContinueButton.click();
		} catch(Exception e) { }
		
		/* -------------- PEO-005 -----------------*/
		sleep();
		commonFunctions.screenShot("ManagePeo8", "Pass", "Verify Contact Details pop-up");
		commonFunctions.clickButton("Continue ");
		
		try {
			peoPage.uspsAddress.click();
			sleep();
			peoPage.currentAdditionalAddress.click();
			sleep();
			peoPage.UspsContinueButton.click();
		} catch(Exception e) { }
		sleep();
		commonFunctions.screenShot("verifyCurrentAddress", "Pass", "Verify current Address");
		commonFunctions.clickButton("Continue ");
		
		/* -------------- PEO-004 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo9", "Pass", "Navigated to Mailing Address(PEO-004) page");
		peoPage.listCurrentAddressLine1.clear();
		peoPage.listCurrentAddressLine2.clear();
		peoPage.listCurrentAddressLine1.sendKeys("listCurrentAddressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		peoPage.listCurrentAddressLine2.sendKeys("listCurrentAddressLine2"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		
		commonFunctions.clickButtonContains("Save & Continue ");
		try {
			peoPage.uspsSuggestedAddress.click();
			peoPage.UspsContinueButton.click();
		} catch(Exception e) {}
		
		
		
		/* -------------- PEO-006 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo10", "Pass", "Navigated to Prior Address(es) in New York(PEO-006) page");
		peoPage.addressLine1.sendKeys("addressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		peoPage.addressLine2.sendKeys("addressLine1"+StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),4));
		commonFunctions.enterTextboxContains("City ", "NY City");
		commonFunctions.enterTextboxContains("Zip Code", "59544");
		commonFunctions.enterTextboxContains("other name(s)", "Tester1");
		commonFunctions.enterTextboxContains("Predecessor(s) Name", "Teser2");
		commonFunctions.enterTextboxContains("Successor(s) Name", "Tester3");
		sleep();
		commonFunctions.clickButtonContains("Save & Continue ");
		
		/* -------------- PEO-007 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo11", "Pass", "Navigated to Verify Prior Address(es) in New York(PEO-007) page");
		commonFunctions.clickButton("Continue ");
		
		/* -------------- PEO-008 -----------------*/
		sleep();
		commonFunctions.screenShot("ManagePeo12", "Pass", "Navigated to Ownership Information(PEO-008) page");
		commonFunctions.enterTextboxContains("Entity or Person", "Test1");
		commonFunctions.enterTextboxContains("Ownership Percentage", "55	");
		commonFunctions.enterTextboxContains("Address Line 1 ", "Test Address");
		commonFunctions.enterTextboxContains("Address Line 2 ", "Test Address 2");
		commonFunctions.enterTextboxContains("City ", "Test City");
		commonFunctions.enterTextboxContains("Zip Code", "65655");
		commonFunctions.clickButtonContains("Save & Continue ");
		
		/* -------------- PEO-009 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo13", "Pass", "Navigated to Verify Ownership Information(PEO-009) page");
		commonFunctions.clickButton("Continue ");
		
		/* -------------- PEO-010 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo14", "Pass", "Navigated to Prior Ownership Information(PEO-010) page");
		commonFunctions.enterTextboxContains("Entity or Person", "Test1");
		commonFunctions.enterTextboxContains("Ownership Percentage", "55	");
		commonFunctions.enterTextboxContains("Address Line 1 ", "Test Address");
		commonFunctions.enterTextboxContains("Address Line 2 ", "Test Address 2");
		commonFunctions.enterTextboxContains("City ", "Test City");
		commonFunctions.enterTextboxContains("Zip Code", "65655");
		commonFunctions.clickButtonContains("Save & Continue ");
		

		/* -------------- PEO-011 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo15", "Pass", "Navigated to Verify Prior Ownership Information(PEO-011) page");
		commonFunctions.clickButton("Continue ");
		
		
		/* -------------- PEO-014 -----------------*/
		sleep();
		commonFunctions.screenShot("ManagePeo16", "Pass", "Navigated to Submission Instructions and Responsibilities(PEO-014) page");
		commonFunctions.clickButton("Continue ");
		
		/* -------------- SREG-06 -----------------*/
		sleep();
		commonFunctions.screenShot("ManagePeo17", "Pass", "Navigated to SREG-06 document upload page");
		commonFunctions.clickButtonContains("Save & Continue ");
		
		
		/* -------------- PEO-015 -----------------*/
		
		sleep();
		commonFunctions.screenShot("ManagePeo18", "Pass", "Navigated to Upload Client List(PEO-015) page");
		commonFunctions.clickButtonContains("Choose File");
		Thread.sleep(3000);
		commonFunctions.uploadDoc("PEO Client List template_TestData2");
		commonFunctions.clickButton("Continue ");
		
		/* -------------- LEAS-012 -----------------*/
		sleep();
		commonFunctions.screenShot("ManagePeo19", "Pass", "Navigated to LEAS-012 page");
		commonFunctions.screenShot("SuccessPage", "Pass", "Successful page reached");
		commonFunctions.clickButton("Continue ");
		sleep();
		
		
	}
}
