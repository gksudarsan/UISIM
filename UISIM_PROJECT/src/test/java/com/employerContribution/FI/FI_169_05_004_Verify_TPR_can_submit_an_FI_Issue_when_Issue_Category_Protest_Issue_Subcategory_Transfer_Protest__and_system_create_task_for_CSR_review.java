package com.employerContribution.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


public class FI_169_05_004_Verify_TPR_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Transfer_Protest__and_system_create_task_for_CSR_review extends TestBase {

	@Test
	public void FI_169_05_004()throws Exception {
	test = report.createTest("FI_169_05_004_Verify_TPR_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Transfer_Protest__and_system_create_task_for_CSR_review");

	commonStepDefinitions commonFunction = new commonStepDefinitions();
	EmployerRegisterPage FI_169_05_004 = new EmployerRegisterPage(driver);
	PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
	
	 // Query
    Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
            "SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
            "EAN");
    String eanValue = databaseEanResult.get("EAN");
    System.out.println("The EAN is " + eanValue);

    // -----Login
    commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_8.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_8_PASSWORD);
    sleep(2000);
    commonFunction.waitForLoadingIconToDisappear();
    commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");

    // -----Menu
    commonFunction.clickMenu("menu");
    sleep(2000);
    commonFunction.screenShot("MenuPage", "Pass", "Launched to Menu");
    commonFunction.ScrollMenu("Secure Messaging");
    commonFunction.clickMenu("Secure Messaging");
    sleep(1000);
    commonFunction.clickMenu("Write Message");
    commonFunction.screenShot("Write Message", "Pass", "Launched to SM-101");
    commonFunction.selectDropdown("Category", " Protest ");
    commonFunction.selectDropdown("Subcategory", " How do I protest Failure to File Penalties? ");
    commonFunction.clickOnLinkAnchorTag("Protest Document for Failure to File Penalties");
    
    // FIS-008
    commonFunction.screenShot("Protest Document for Failure to File Penalties", "Pass", "Launched to FIS-008");
    commonFunction.enterTextboxContains("Employer Name", "Sam Hunt");
    commonFunction.enterTextboxContains("Address Line 1", "Brittany Hall 55 East");
    commonFunction.enterTextboxContains("Address Line 2", "Street 5");
    commonFunction.enterTextboxContains("City", "Albany");
    commonFunction.selectDropdown("State", " New York ");
    commonFunction.enterTextboxContains("Zip Code", "21356");
    commonFunction.enterTextboxContains("ERN", "68-35087");
    commonFunction.enterTextboxContains(" FEIN ", "26-1484819");
    commonFunction.enterTextboxContains("Assessment ID", "124323412111");
   // commonFunction.selectCheckboxFIS008("Section I", 3);
	
	}

}
