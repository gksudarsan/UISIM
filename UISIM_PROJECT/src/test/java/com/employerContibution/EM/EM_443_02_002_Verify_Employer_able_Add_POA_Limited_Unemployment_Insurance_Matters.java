package com.employerContibution.EM;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.AddressPage;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_443_02_002_Verify_Employer_able_Add_POA_Limited_Unemployment_Insurance_Matters extends TestBase {

	String EAN = prop.getProperty("EAN");

	@Test(priority = 1, description = "EM.443.02.002.Verify Employer is able Add POA/TPR association for designation type \"Limited Unemployment Insurance Matters\" and the system creates task for CSR reviews and approved.", groups = {
			"Regression" })
	public void EM_443_02_002() throws Exception {
		test = report.createTest(
				"EM.443.02.002.Verify Employer is able Add POA/TPR association for designation type \"Limited Unemployment Insurance Matters\" and the system creates task for CSR reviews and approved.");

		commonStepDefinitions cf = new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage addPage = new AddressPage(driver);
		HomePage home = new HomePage(driver);

		// login with employer account
		cf.login(COMMON_CONSTANT.EMP_USER_2.toUpperCase(), COMMON_CONSTANT.EMP_USER_2_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		home.menuCTA.click();
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		cf.screenShot("NavigateToAddPoa/TprAssociation", "Pass", "Navigate Add or Remove POA/TPR Association");
		cf.clickMenu("Add or Remove POA/TPR Association");
		cf.screenShot("AddorRemoveThirdPartyAssociationtoEmployer", "Pass",
				"Add or Remove Third Party Association to Employer:SREG-537");
		cf.selectDropdown("Designation Type", "Limited Unemployment Insurance Matters");
		cf.clickButtonContains("Search POA/TPR");
		cf.screenShot("SearchPOA/ThirdPartyRepresentative", "Pass", "Launched to SREG-040");
		cf.enterTextboxContains("POA/TPR Legal Name", "Test");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("ThirdPartyRepresentativeDetails", "Pass", "Launched to SREG-040");
		cf.selectRadio("Select");
		cf.screenShot("SelectRadioButtonForTprPoaRepresentative", "Pass", "Launched to SREG-040");
		cf.clickButtonContains("Continue ");
		sleep(2000);
		String Ern = addPage.getERN.getText().trim();
		Ern = Ern.replace("-", "");
		System.out.println("Selected ERN is:" + Ern);
		test.log(Status.INFO, "ERN::" + Ern);
		cf.selectRadio("Select");
		cf.enterCommentBoxContains("Add the Name");
		cf.selectCheckbox("Additional authorization");
		cf.screenShot("ThirdPartyAssociationtoEmployer", "Pass", "Launched to SREG-537");
		cf.clickButtonContains("Submit ");
		sleep(2000);
		cf.screenShot("EmployerConfirmation", "Pass", "Launched to SUC-002");
		cf.clickButtonContains("Home ");

		// login with CSR account

		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '" + COMMON_CONSTANT.CSR_USER_1
				+ "' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='" + Ern
				+ "' ORDER BY UPDATED_TS desc)");
		//cf.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(3000);
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Employer Registration Number", Ern);
		cf.screenShot("SerachWithLegalNameOfBusiness", "Pass", "Searching with Legal Name of Business");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("ReviewEmployerAgentChange", "Pass", "Review Employer Agent Change");
		cf.clickOnLinkAnchorTag("Review Employer Agent Change");
		sleep(2000);
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.screenShot("OpenWorkItem", "Pass", "Launched to WF-091");
		cf.selectDropdown("Select the action", "Approve");
		cf.enterCommentBoxContains("Approved");
		sleep();
		cf.screenShot("ApprovePOA/ThirdPartyAssociationtoEmployer", "Pass",
				"Launched to SREG-499");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("Associationconfirmation", "Pass", "Launched to SUC-002");
		cf.clickButtonContains("Home ");
		cf.screenShot("HomePage", "Pass", "HomePageScreen");

	}

}
