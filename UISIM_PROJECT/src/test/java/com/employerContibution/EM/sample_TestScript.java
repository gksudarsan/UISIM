package com.employerContibution.EM;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.base.TestBase;
import com.ui.pages.LoginPage;
import com.ui.pages.employerManagement;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

@Listeners(com.ui.utilities.ListenerTest.class)
public class sample_TestScript extends TestBase {

	@Test(priority=1, description = "Test sample",groups = {"Regression"})
		public void Testing123() throws Exception
		{
		test = report.createTest("Sample Script Testing");
	
			String EAN = prop.getProperty("EAN");
			commonStepDefinitions cf = new commonStepDefinitions();
			//employerManagement em =  new employerManagement();
			Map<String, String> databaseResults = cf.database_SelectQuerySingleColumn(
					"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE FEIN NOT IN "
					+ "(SELECT FEIN FROM T_EMPLOYER_DOL_DTF tedd) ORDER BY UPDATED_TS DESC"
					, "FEIN"); String FEIN = databaseResults.get("FEIN");
					System.out.println("FEIN NUMBER = " +FEIN);
				    cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
					cf.screenShot("ApplicationLogin","Pass","Login is successful");
					
					 cf.clickMenu("Menu"); 
					 cf.clickMenu("Account Maintenance");sleep();
					 
					  cf.clickMenu("Sale of Business");sleep();
					  cf.clickMenu("Inquiry Employer Account");sleep();
					 
					cf.screenShot("Menu","Pass","AccountMaintenance");
					//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
					//em.Inquery(EAN);

		
	}
}