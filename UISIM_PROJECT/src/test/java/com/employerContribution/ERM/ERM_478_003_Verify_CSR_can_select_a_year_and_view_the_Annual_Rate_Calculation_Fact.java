package com.employerContribution.ERM;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

	import stepDefinitions.commonStepDefinitions;
public class ERM_478_003_Verify_CSR_can_select_a_year_and_view_the_Annual_Rate_Calculation_Fact extends TestBase{

	@Test
	public void ERM_478_003_Verify_CSR_can_select_a_year_and_view_the_Annual_Rate_Calculation_Fact() throws Exception {


	commonStepDefinitions commonFunction = new commonStepDefinitions();	
	 
	employerManagement em =  new employerManagement();
	EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
	PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
	test = report.createTest("EE_03_003_Verify_CSR_can_submit_employer_registration_for_employer_type_Governmental_and_legal_entity_type_Village_and_work_items_will_be_created_for_CSR_to_review ");

	test.log(Status.INFO, "Logging to the application");
	
	commonFunction.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
	
	commonFunction.screenShot("ApplicationLogin", "Pass", "Login is successful");		
	commonFunction.clickMenu("menu"); 
	//commonFunction.ScrollMenu("Employer Registration");sleep();
	commonFunction.ScrollMenu("Annual UI Rate");
	commonFunction.clickMenu("Annual UI Rate");
	commonFunction.screenShot("Menu", "Pass", "Navigate to Annual UI Rate");
	sleep(3500);
	commonFunction.clickMenu("View Rate Factors");
    sleep(4000);
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page is displayed");
   commonFunction.selectDropdown("Select the rate year to view the Rate Calculation Details.", " 2023 ");
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page updated with details");
   commonFunction.clickButtonContains("Continue ");
   sleep(2500);
   commonFunction.screenShot("INQ-571", "Pass", "Factors for Annual Rate Calculation page is displayed");
   commonFunction.clickButtonContains("Previous ");
   
   sleep(4000);
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page is displayed");
   commonFunction.selectDropdown("Select the rate year to view the Rate Calculation Details.", " 2022 ");
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page updated with details");
   commonFunction.clickButtonContains("Continue ");
   sleep(2500);
   commonFunction.screenShot("INQ-571", "Pass", "Factors for Annual Rate Calculation page is displayed");
   commonFunction.clickButtonContains("Previous ");
   
   sleep(4000);
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page is displayed");
   commonFunction.selectDropdown("Select the rate year to view the Rate Calculation Details.", " 2021 ");
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page updated with details");
   commonFunction.clickButtonContains("Continue ");
   sleep(2500);
   commonFunction.screenShot("INQ-571", "Pass", "Factors for Annual Rate Calculation page is displayed");
   commonFunction.clickButtonContains("Previous ");

   sleep(4000);
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page is displayed");
   commonFunction.selectDropdown("Select the rate year to view the Rate Calculation Details.", " 2020 ");
   commonFunction.screenShot("INQ-204", "Pass", "Annual Rate Calculation Details page updated with details");
   commonFunction.clickButtonContains("Continue ");
   sleep(2500);
   commonFunction.screenShot("INQ-571", "Pass", "Factors for Annual Rate Calculation page is displayed");
   //commonFunction.clickButtonContains("Previous ");
   
   sleep(5000);
   commonFunction.clickButtonContains(" Home ");
   sleep(4000);
   commonFunction.screenShot("Home", "Pass", "Home page is displayed");

}}
