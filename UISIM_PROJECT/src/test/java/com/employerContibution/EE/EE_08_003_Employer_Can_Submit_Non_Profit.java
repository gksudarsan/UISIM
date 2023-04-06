package com.employerContibution.EE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EE_08_003_Employer_Can_Submit_Non_Profit extends TestBase{
	@Test
	public void EE_08_003() throws Exception {
		commonStepDefinitions commonFuntions = new commonStepDefinitions();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report
				.createTest("EE.08.003 Verify employer can submit employer registration for employer type 'Non-Profit' and legal entity type 'Unincorporated Association' and work items will be created for CSR to review.");

		commonFuntions.login(COMMON_CONSTANT.EMPLOYER_USER_MANJU.toUpperCase(), COMMON_CONSTANT.EMPLOYER_PASS_MANJU);
		commonFuntions.screenShot("ApplicationLogin", "Pass", "Login is successful");
		commonFuntions.clickMenu("Menu");
//		commonFuntions.clickMenu("Employer Registration");
		commonFuntions.safeJavaScriptClick(empPage.employerRegisterMenu);
		commonFuntions.clickMenu("Register Employer");
		sleep(3000);
		commonFuntions.screenShot("EmpRegister1", "Pass", "Landed on the Employer Register page");
		commonFuntions.clickButton("Continue ");
		
		
		commonFuntions.selectDropdown("Employer Type", " Non-Profit ");
		commonFuntions.selectDropdown("Type of Legal Entity", " Unincorporated Association ");
		String feinValue = StringUtils.left(String.valueOf((long) (Math.random() * Math.pow(10, 10))), 9);
		System.out.println(feinValue);
		commonFuntions.enterTextboxContains("Federal Employer Identification Number (FEIN)", feinValue);
		commonFuntions.clickButton("Continue ");
		commonFuntions.screenShot("EmpRegister3", "Pass", "Entered the details and clicked on continue button");
		sleep(3000);
		
		/*----------------SREG-003----------------*/
		commonFuntions.screenShot("EmpRegister4", "Pass", "Navigated on SREG-003 page");
		empPage.legalNameTextBox.sendKeys("AAA INC");
		commonFuntions.enterTextboxContains(" Business Phone Number  ", "6732111111");
		commonFuntions.enterTextbox("Enter date of first operations in New York State", "02282021");
		
		sleep(3000);
		
		empPage.firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();
		empPage.firstCalender_Year.click();
		empPage.firstCalender_Year_Value.click();
		sleep();
//		commonFuntions.selectRadioQuestions("Do persons work for you whom you do", "No ");
		commonFuntions.safeJavaScriptClick(empPage.DO_Person_Work_radio);
		commonFuntions.screenShot("EmpRegister5", "Pass", "Entering the details");
		empPage.What_firstCalender_Quater.click();
		empPage.firstCalender_Quater_Value.click();
		empPage.What_firstCalender_Year.click();
		empPage.firstCalender_Year_Value.click();
		
		commonFuntions.safeJavaScriptClick(empPage.If_Not_Lible_Radio);
		commonFuntions.safeJavaScriptClick(empPage.DOes_Org_Have_Radio);
		commonFuntions.safeJavaScriptClick(empPage.Choose_Option_Radio);
		
//		commonFuntions.selectRadioQuestions("If you are not liable under the Unemployment", "No ");
//		commonFuntions.selectRadioQuestions("Does this organization have, or h", "No ");
//		commonFuntions.selectRadioQuestions("Choose the option you wish to", "Contributory");
		commonFuntions.clickButton("Continue ");
		sleep(3000);
		
	}
}
