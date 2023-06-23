package com.employerContibution.EL;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EL_440_01 extends TestBase{

	@Test()
	public void EL_440_01_Indi_Exempt() throws Exception {

		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		commonStepDefinitions cf = new commonStepDefinitions();
		employerManagement em =  new employerManagement();
		Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE ACCOUNT_STATUS='ISSD' AND TYPE_OF_REQUEST='PEOIR'", "FEIN");
		String FEIN = databaseResults.get("FEIN");
		System.out.println("feinNumber is" + FEIN);

		test = report.createTest("EL.440.01 - Verify CSR can search PEO and update PEO conversion  PEO Individual' to 'PEO Exempt'");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		sleep();
		cf.ScrollMenu("Professional Employer Organization (PEO)");
		PEOPage.menuPeo.click();
		sleep();
		cf.screenShot("Menu", "Pass", "Manage PEO");
		cf.clickMenu("Manage PEO");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(4000);
		cf.enterTextboxContains("(FEIN)", FEIN);
		cf.screenShot("file1", "Pass", "Searching with FEIN ");
		cf.clickButtonContains("search");
		Thread.sleep(4000);
		cf.selectRadioWithFeinValue(FEIN);
		cf.clickButton("Continue ");
		Thread.sleep(4000);
		cf.selectDropdown("PEO Conversion", " PEO Individual to PEO Exempt ");
		cf.screenShot("DropDownValue", "Pass", "Selecting the dropdown value PEO Individual to PEO Exempt ");
		cf.clickButtonContains(" CONVERT ");
		cf.screenShot("", "Pass", "");
		cf.selectDropdown("States in which the PEO is licensed or registered as a PEO", " Alaska ");
		sleep();
//		cf.enterTextboxContains("State agency that issued it.","homeagency");
		cf.enterTextbox("State agency that issued it.", "New York");
		sleep(2000);
		cf.selectRadioQuestions("Provide Information", "Registration Number");
		sleep(2000);
		String ernValueNew=StringUtils.left( String.valueOf((long) (Math.random()*Math.pow(10,10))),7);
		cf.enterTextbox("Registration Number ", ernValueNew);
		sleep(4000);
		WebElement saveCont = driver.findElement(By.xpath("//span[text()='Save & Continue ']"));
//		cf.safeJavaScriptClick(saveCont);
		saveCont.click();
		System.out.println("Clicked on Continue button");
		sleep();
		/*---------EAS-001----------------*/
		cf.clickButton("Save & Continue ");
		/*---------PEO-003----------------*/
		
		cf.screenShot("Address2", "Pass", "Entering address 1&2");
		cf.enterTextboxContains("Address Line 1 ", "Fake Address" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("Address Line 2 ", "Fake Address 2" + cf.createRandomInteger(1000, 9999));
		cf.enterTextboxContains("City ","New York" );
		cf.enterTextboxContains("Zip Code", "12343");
		cf.enterTextboxContains("Phone Number",
				Long.toString(cf.createRandomInteger(10000000, 99999999))
						+ Long.toString(cf.createRandomInteger(10, 99)));
		cf.enterTextboxContains("Business Email Address",
				"autoTest" + Long.toString(cf.createRandomInteger(10000, 99999)) + "@gmail.com");
		sleep(2000);
		cf.clickButtonContains("Save & Continue");
		sleep(4000);
		try {
			WebElement radio = driver.findElement(By.xpath("//strong[text()='USPS Suggested Address']/../following-sibling::div/mat-radio-button/label/span/span[@class='mat-radio-outer-circle']"));
			cf.safeJavaScriptClick(radio);
			sleep();
			WebElement continuE = driver.findElement(By.xpath("//span[text()='Continue ']"));
			cf.safeJavaScriptClick(continuE);
		}catch(Exception e) {
			System.out.println("Pop up not displayed");
		}
		Thread.sleep(4000);
		
		/*---------PEO-004----------------*/
		Thread.sleep(4000);
		cf.clickButton("Save & Continue ");
		
		Thread.sleep(4000);
		/*---------PEOE-004----------------*/
		
		cf.clickButton("Continue ");
		Thread.sleep(4000);
		/*---------SREG-006----------------*/
		Thread.sleep(4000);
		cf.clickButton("Save & Continue ");
		
		/*---------PEO-015----------------*/
		Thread.sleep(4000);
		sleep();

		cf.clickButtonContains("Choose File");
		Thread.sleep(4000);
		cf.uploadDoc("PEO Client List template_TestData2.xls");
		Thread.sleep(4000);
		cf.clickButtonContains("Continue");
		Thread.sleep(4000);
		cf.screenShot("verifyClient","Pass","Verify Client List");
		cf.clickButtonContains("Continue");
		Thread.sleep(4000);
		cf.screenShot("peoDetails","Pass","Peo conversion");
		String peoID = 	PEOPage.peoIDText.getText();
		test.log(Status.INFO, "PEO ID : : "+peoID);
		System.out.println("PEO ID = "+peoID);
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(4000);
		cf.enterTextboxContains("Enter name of Officer, Partner, Proprietor or Member","TestAutomation"+cf.createRandomInteger(10000,99999));
		cf.screenShot("Declaration","Pass","Declaration");
		cf.clickButtonContains("Save & Continue");
		Thread.sleep(4000);
		cf.screenShot("Acknowledgement","Pass","Statement Of Acknowledgement");
		cf.clickButtonContains("Accept & Submit");
		Thread.sleep(4000);
		cf.screenShot("Completion","Pass","Register/Renew Confirmation");
		cf.selectRadio("Approved");
		cf.screenShot("Completion","Pass","conversion indi to exempt");
		cf.clickButtonContains("Submit ");sleep();
		cf.screenShot("Completion","Pass","Register/Renew Confirmation");
		cf.clickButton("Home ");
//		em.Inquery(FEIN);
		sleep(2000);
		cf.waitForLoadingIconToDisappear();
		cf.clickMenu("Menu");
		cf.clickMenu("Inquiry");
		sleep();
		cf.clickMenu("Professional Employer Organization (PEO)");
		sleep();
		cf.clickMenu("Inquiry PEO Information");
		Thread.sleep(3000);
		PEOPage.advancedSearch.click();
		Thread.sleep(4000);
		cf.enterTextboxContains("PEO ID", peoID);
		sleep();
		cf.clickButtonContains("search");
		cf.selectRadio("Select");
		sleep();
		cf.clickButton("Continue ");
		sleep(4000);
		cf.screenShot("ashjah", "Pass", "Inquiry");
		
		
		test.log(Status.PASS, "Clicked on Home button");




	}
}
