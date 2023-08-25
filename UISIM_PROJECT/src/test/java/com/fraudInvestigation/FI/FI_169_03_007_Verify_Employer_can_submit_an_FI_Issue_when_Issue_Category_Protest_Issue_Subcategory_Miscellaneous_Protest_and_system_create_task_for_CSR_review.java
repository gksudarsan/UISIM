package com.fraudInvestigation.FI;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


public class FI_169_03_007_Verify_Employer_can_submit_an_FI_Issue_when_Issue_Category_Protest_Issue_Subcategory_Miscellaneous_Protest_and_system_create_task_for_CSR_review extends TestBase {

	@Test
	public void FI_445_006()throws Exception {
	test = report.createTest("FI_445_006_Verify_CSR_can_view_the_BCP_penalty_details_in_order_to_determine_if_it_is_cancelled_when_cancel_code_is_Data_Processing_Error");

	commonStepDefinitions commonFunction = new commonStepDefinitions();
	EmployerRegisterPage FI_445_006 = new EmployerRegisterPage(driver);
	PEOPage peoPage = PageFactory.initElements(driver, PEOPage.class);
	
	 // Query
    Map<String, String> databaseEanResult = commonFunction.database_SelectQuerySingleColumn(
            "SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_TX_EMPLOYER_COLLECTION_HOLD ttech ON ttech.EMPLOYER_ACCOUNT_ID = tea.EMPLOYER_ACCOUNT_ID WHERE ACCOUNT_STATUS = 'ACTV'",
            "EAN");
    String eanValue = databaseEanResult.get("EAN");
    System.out.println("The EAN is " + eanValue);
    
	commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);

    sleep(2000);
    commonFunction.waitForLoadingIconToDisappear();
    commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");

    // -----Menu
    commonFunction.clickMenu("menu");
    sleep(2000);
    commonFunction.screenShot("MenuPage", "Pass", "Launched to Menu");
    commonFunction.ScrollMenu("Penalty");
    commonFunction.clickMenu("Penalty");
    commonFunction.clickMenu("Penalty Menu");
    sleep(5000);
    commonFunction.screenShot("FIP-001", "Pass", "Penality menupage is displayed");
	commonFunction.enterTextboxContains("Employer Registration Number", "04-63826");
	commonFunction.screenShot("FIP-001", "Pass", "Penality menupage is displayed");
	commonFunction.clickButton("Continue ");
	sleep(4000);
	
	commonFunction.screenShot("FIP-002", "Pass", "Select Penalty is displayed");
//	peoPage.selectRadiobutton1.click();
	commonFunction.screenShot("FIP-002", "Pass", "Select Penalty is displayed");
	commonFunction.clickButton("Continue ");
	sleep(5000);
	
	commonFunction.screenShot("FIP-005", "Pass", "Benefit Claim Penalty Summary page is displayed");
	//need to enter deatils
	//peoPage.selectRadiobutton2.click();
	commonFunction.screenShot("FIP-005", "Pass", "Benefit Claim Penalty Summary page is displayed");
	commonFunction.clickButton("Continue ");
	sleep(5000);
	
	commonFunction.screenShot("FIP-006", "Pass", "Benefit Claim Penalty Details page is displayed");
	sleep(3000);
	commonFunction.clickButton("Home");
	sleep(3000);
	commonFunction.screenShot("Home", "Pass", "HOME page is dislayed");
}

}
