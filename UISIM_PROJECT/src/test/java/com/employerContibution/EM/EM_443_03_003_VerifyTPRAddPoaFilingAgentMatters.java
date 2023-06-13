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
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_537;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;


@Listeners(com.ui.utilities.ListenerTest.class)
public class EM_443_03_003_VerifyTPRAddPoaFilingAgentMatters extends TestBase{

	@Test
	public void EM_443_03_003() throws Exception
	{
		commonStepDefinitions cf= new commonStepDefinitions();
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
		AddressPage AddPage = PageFactory.initElements(driver, AddressPage.class);
		//SREG_537 sreg537 = new SREG_537(driver);
		
		//login with TPR account **********************
		
		test = report.createTest("EM.443.03.003:Verify TPR is able to enter ERN and Add POA/TPR association for designation type \"Filing Agent Matters\"and the system creates task for CSR reviews and approved.");
		cf.login(COMMON_CONSTANT.TPR_USER_1.toUpperCase(), COMMON_CONSTANT.TPR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");
		cf.clickMenu("Menu");
		cf.ScrollMenu("Account Maintenance");
		cf.clickMenu("Account Maintenance");
		cf.ScrollMenu("Employer Account Maintenance");
		cf.clickMenu("Employer Account Maintenance");
		cf.screenShot("NavigateToAddPoa/TprAssociation", "Pass", "Navigate Add or Remove POA/TPR Association");
		cf.clickMenu("Add or Remove POA/TPR Association");
		sleep();
		cf.screenShot("AddRemovePOA/TPRAssociationEnterERN", "Pass", "Add or Remove POA/TPR Association – Enter ERN:SREG-430");
		sleep();
		Map<String, String> databaseResults = 
				cf.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE EAN IS NOT NULL ORDER BY UPDATED_TS DESC", "EAN");
		String eanValue = databaseResults.get("EAN"); 
		System.out.println("The EAN Value is:"+ eanValue);
		cf.enterTextboxContains("Employer Registration Number", eanValue);
		test.log(Status.INFO, "ERN::"+eanValue);
		cf.screenShot("EnteredERN", "Pass", "Entered:SREG-430");
		cf.clickButtonContains("Continue ");
		sleep();
		cf.selectDropdown("Designation Type", " Filing Agent Matters ");
		AddPage.commentField.sendKeys("enter comments");
		cf.selectCheckbox("Additional authorization");
		sleep();
		cf.ScrollMenu("Add or Remove Third Party Association to Employer");
		String Ern = AddPage.getERN.getText().trim();
		Ern=Ern.replace("-", "");
		System.out.println("Selected ERN is:" +Ern);
		test.log(Status.INFO, "ERN::" +Ern);
		cf.screenShot("AddRemoveThirdPartyAssociationtoEmployer", "Pass", "Add or Remove Third Party Association to Employer:SREG-537");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("ThirdPartyRepresentativeAssociationEmployerConfirmation", "Pass", "Third Party Representative Association to Employer Confirmation:SUC-002");
		cf.clickButtonContains("Home");
		
		//login with CSR account *************************
		
		cf.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE EAN='"+Ern+"' ORDER BY UPDATED_TS desc)");
		cf.LogoutAndLoginIfOktaPageDisplayed(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		sleep(3000);
		PEOPage.queue.click();
		cf.waitForLoadingIconToDisappear();
		cf.enterTextboxContains("Employer Registration Number", Ern);
		cf.screenShot("SearchWithLegalNameOfBusiness","Pass","Searching with Legal Name of Business");
		cf.clickButtonContains("Search");
		sleep(2000);
		cf.screenShot("ReviewEmployerAgentChange","Pass","Review Employer Agent Change");
		cf.clickOnLink("Review Employer Agent Change");
		sleep(2000);
		cf.clickButtonContains("Open Work Item");
		sleep(2000);
		cf.ScrollMenu("Search");sleep();
		cf.screenShot("OpenWorkItem","Pass","Open work item:WF-091");
		cf.selectDropdown("Select the action", " Approve ");
		AddPage.commentField.sendKeys("Approved");
		cf.screenShot("ApprovePOA/ThirdPartyAssociationtoEmployer","Pass","Approve POA/Third Party Association to Employer:SREG-499");
		cf.clickButtonContains("Submit");
		sleep(2000);
		cf.screenShot("Associationconfirmation","Pass","Association confirmation:SUC-002");
		cf.clickButtonContains("Home ");
		sleep(2000);
		cf.screenShot("HomePage", "Pass", "HomePageScreen");
	}
}
