package com.employerContribution.FI;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.FIS_002;
import com.ui.pages.PEOPage;
import com.ui.pages.SUC_002;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


public class FI_169_05_004_Verify_TPR_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Transfer_Protest__and_system_create_task_for_CSR_review extends TestBase {

	@Test
	public void FI_169_05_004()throws Exception {
	test = report.createTest("FI_169_05_004_Verify_TPR_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Transfer_Protest__and_system_create_task_for_CSR_review");

	commonStepDefinitions commonFuntions = new commonStepDefinitions();
	EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
	PEOPage peopage = new PEOPage(driver);
	FIS_002 fis002 = new FIS_002(driver);
	SUC_002 suc002 = new SUC_002(driver);
	
    // -----Login
	commonFuntions.login(COMMON_CONSTANT.TPR_USER_1, COMMON_CONSTANT.TPR_USER_1_PASSWORD);
    sleep(2000);
    commonFuntions.waitForLoadingIconToDisappear();
    commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");

   // String ernNum = "5454645";
    
    Map<String, String> ERNOutput = commonFuntions.database_SelectQuerySingleColumn(
			"SELECT * FROM t_employer WHERE EMPLOYER_ID IN (\r\n"
					+ "SELECT EMPLOYER_ID FROM T_THIRD_PARTY_CDS_VENDOR_ASSOCIATION WHERE \r\n"
					+ "THIRD_PARTY_CDS_VENDOR_ID = (SELECT THIRD_PARTY_AGENT_ID FROM T_TPR_USER ttu WHERE USER_ID = 'tpruser@123') AND ASSOCIATION_STATUS = 'ACTIVE');",
			"EAN");
	String ernNum = ERNOutput.get("EAN");
	System.out.println(ernNum);
	test.log(Status.INFO, "Prior ERN : : " + ernNum);

	
    // -----Menu
    commonFuntions.clickMenu("Menu");
    sleep(1000);
	commonFuntions.ScrollMenu("Secure Messaging");
	commonFuntions.clickMenu("Secure Messaging");
	sleep(2000);
	commonFuntions.ScrollMenu("Write Message - Enter ERN");
	sleep(1000);
	commonFuntions.screenShot("Menu Bar", "Pass", "Write Message - Enter ERN menu side bar is displayed");
	commonFuntions.clickMenu("Write Message - Enter ERN");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	commonFuntions.screenShot("Write Message - Enter ERN", "Pass", "SM-101 screen is displayed");
	
	commonFuntions.enterTextboxContains("Write Message - Enter ERN", ernNum); // 0847711 , 5454645 , 5454645
	commonFuntions.clickButtonContains("Continue ");
	sleep(2000);
	commonFuntions.waitForLoadingIconToDisappear();
	commonFuntions.screenShot("Write Message", "Pass", "SM-101 screen is displayed");
	
	test.info("Step: 4 -- ");
	commonFuntions.selectDropdown("Category", " Protest ");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	commonFuntions.selectDropdown("Subcategory", " How do I protest a Transfer of Business? ");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	commonFuntions.screenShot("Write Message", "Pass", "SM-101 with details filled screen is displayed");
	sleep(2000);
	commonFuntions.clickOnLinkAnchorTag("click here");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(2000);
	Set<String> allHandles = driver.getWindowHandles();
	Iterator<String> l1 = allHandles.iterator();
	String parent = l1.next();
	System.out.println(parent);
	String Child = l1.next();
	System.out.println(Child);
	driver.switchTo().window(Child);
	sleep(2000);
	commonFuntions.screenShot("Submit Issue", "Pass", "FIS-002 screen is displayed");
	
	
	//
	Map<String, String> databaseResults1 = peopage.database_SelectQuery(
			"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC;");

	String eanValue1 = databaseResults1.get("Ean");
	System.out.println("The EAN Value is:" + eanValue1);
	test.log(Status.INFO, "Ean::" + eanValue1);
	
	String nameValue1 = databaseResults1.get("legalName");
	System.out.println("The EAN Value is:" + nameValue1);
	test.log(Status.INFO, "Ean::" + nameValue1);
	//
	
	//
	Map<String, String> databaseResults2 = peopage.database_SelectQuery3(
			"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC;");

	String eanValue2 = databaseResults2.get("Ean");
	System.out.println("The EAN Value is:" + eanValue2);
	test.log(Status.INFO, "Ean::" + eanValue2);
	
	String nameValue2 = databaseResults1.get("legalName");
	System.out.println("The EAN Value is:" + nameValue2);
	test.log(Status.INFO, "Ean::" + nameValue2);
	//
	test.info("Step: 5 -- ");
	//
	String eanvaluebefore = commonFuntions.retrieveValue("Employer Registration Number").trim();
	String eanvalue = eanvaluebefore.replace("-", "");
	//
	fis002.submittingIssueField.sendKeys("test comment");
	commonFuntions.enterCurrentDate("Effective Date of Transfer");
	commonFuntions.enterTextboxContains("Predecessor ERN", eanValue1);
	commonFuntions.enterTextboxContains("Predecessor Name", nameValue1);
	commonFuntions.enterTextboxContains("Successor ERN", eanValue2);
	commonFuntions.enterTextboxContains("Successor Name", nameValue2);
	commonFuntions.selectRadioQuestions("Is a contract provided?", "No ");
	
	commonFuntions.selectLink("Document", "Browse");
	sleep(2000);
	commonFuntions.uploadDoc("Sample.docx");
	sleep(2000);
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(2000);
	commonFuntions.screenShot("Submit Issue", "Pass", "FIS-002 screen is displayed");
	commonFuntions.clickButtonContains("Continue ");
	sleep(2000);
	commonFuntions.waitForLoadingIconToDisappear();
	commonFuntions.screenShot("Submit Issue Verification", "Pass", " screen is displayed");
	commonFuntions.clickButtonContains("Submit ");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	commonFuntions.screenShot("Issue Submission Confirmation", "Pass", "SUC-002 screen is displayed");
	
	test.info("Step: 6 -- ");
	commonFuntions.clickButtonContains("Home");
	sleep(2000);
	commonFuntions.waitForLoadingIconToDisappear();
	commonFuntions.screenShot("Home page", "Pass", "Home screen is displayed");
	
	
	test.info("Step: 7 -- ");
	commonFuntions.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
	
	//
	commonFuntions.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+eanvalue+"' ORDER BY UPDATED_TS desc);");
	//
	
	
	peopage.queue.click();
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	
	test.info("Step: 8 -- ");
	commonFuntions.forceClearTextWithElement("Employer Registration Number");
	sleep(1000);
	commonFuntions.enterTextboxContains("Employer Registration Number", eanvalue);
	commonFuntions.screenShot("EanSearch", "Pass", "EanSearch");
	commonFuntions.clickButtonContains("Search");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	
	test.info("Step: 9 -- ");
	commonFuntions.clickOnLink("Transfer Protest Task");
	commonFuntions.screenShot("Work Item Details", "Pass", "WF-091 screen is visible");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	
	commonFuntions.clickButtonContains("Open Work Item ");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	commonFuntions.screenShot("Transfer Protest", "Pass", "PFP-050 screen is visible");
	
	test.info("Step: 10 -- ");
	commonFuntions.selectRadioQuestions("Do you want to reroute this task to another work basket/User?", "No ");
	commonFuntions.selectRadioQuestions("Do you want to create a Field Audit Request task?", "No ");
	commonFuntions.selectRadioQuestions("Do you want to place a Hold Action Flag on this account?", "No ");
	fis002.commentsFieldpfp050.sendKeys("comment test");
	commonFuntions.clickButtonContains("Submit ");
	commonFuntions.waitForLoadingIconToDisappear();
	sleep(1000);
	commonFuntions.screenShot("Task Confirmation", "Pass", "SUC-002 screen is visible");
	
	test.info("Step: 11 -- ");
	suc002.homeButton.click();
	Thread.sleep(5000);
	commonFuntions.screenShot("Homepage", "Pass", "Homepage screen is visible");
	
	}

}
