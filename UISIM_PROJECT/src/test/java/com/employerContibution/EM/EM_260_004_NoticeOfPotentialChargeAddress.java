package com.employerContibution.EM;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;
import com.ui.locators.employerManagementLocators;
import com.ui.pages.EmployerRegisterPage;
import com.ui.pages.PEOPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class EM_260_004_NoticeOfPotentialChargeAddress extends TestBase{

	@Test()
	public void EE_01_004_csr_registration() throws Exception {

		commonStepDefinitions cf = new commonStepDefinitions();	/*
		 * String feinValue1 =StringUtils.left( String.valueOf((long)
		 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
		 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
		 * +feinValue);
		 */
		Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
				"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'" , "EAN"); 
		String EAN = databaseResults1.get("EAN");
		System.out.println("EAN_NAME  = " +EAN);
		
		employerManagement em =  new employerManagement();
		EmployerRegisterPage empPage = new EmployerRegisterPage(driver);
		PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);

		test = report.createTest("EE_01_004- Verify CSR can submit employer registration for employer type 'Business' and legal entity type 'Guardianship' and work items will be created for CSR to review.");

		cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		cf.screenShot("ApplicationLogin", "Pass", "Login is successful");	
			cf.clickMenu("Menu");	
			cf.clickMenu("Account Maintenance");sleep();
			cf.clickMenu("Maintain Address");sleep();
			cf.screenShot("Menu","Pass","AccountMaintenance");
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			em.updateAddressNoticeofPotentialCharge(EAN);

		
	}
}