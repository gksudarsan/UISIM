package com.employerContibution.EM;


	import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

	import com.aventstack.extentreports.Status;
	import com.ui.base.TestBase;
	import com.ui.pages.EM_005;
	import com.ui.pages.EmployerRegisterPage;
	import com.ui.pages.HomePage;
import com.ui.pages.PEOPage;
import com.ui.pages.SREG_027;
	import com.ui.pages.SREG_030;
	import com.ui.pages.SREG_503;
	import com.ui.pages.SREG_504;
	import com.ui.pages.SUC_002;
	import com.ui.utilities.COMMON_CONSTANT;

	import net.bytebuddy.description.annotation.AnnotationList.Empty;
	import stepDefinitions.commonStepDefinitions;

	public class EM_310_07_008_Verify_Employer_is_able_to_report_a_change_in_Legal_entity_when_the_same_transfer_was_processed_previously_and_system_create_task_for_CSR_reviews extends TestBase{


		@Test(priority = 1, description = "Test sample", groups = { "Regression" })
		public void Testing123() throws Exception {
			
			commonStepDefinitions commonFuntions= new commonStepDefinitions();
			PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
			
			test = report.createTest("Verify CSR is able to update Legal entity type information for type of legal entity 'Limited Partnership' and employer type 'Business'");
			
			commonStepDefinitions commonFunction = new commonStepDefinitions();
			EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
			HomePage home = new HomePage(driver);
			SREG_030 sreg030 = new SREG_030(driver);
			SREG_027 sreg27 = new SREG_027(driver);
			SUC_002 suc002 = new SUC_002(driver);
			PEOPage peoPage = new PEOPage(driver);
			 
			
			test.log(Status.INFO, "Logging to the application");
			//stepDef.login(prop.getProperty("EMPLOYER_USER_4"), prop.getProperty("CSR_Pass"));
			//stepDef.login(COMMON_CONSTANT.EMPLOYER_USER_4, COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);

			commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_4.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);
			commonFunction.screenShot("ApplicationLoginPage", "Pass", "Login is successful");
			
			//---Menu Click---
			commonFunction.clickMenu("Menu");
			commonFunction.ScrollMenu("Account Maintenance");
			commonFunction.clickMenu("Account Maintenance");
			commonFunction.ScrollMenu("Change in Legal Entity");
			commonFunction.clickMenu("Change in Legal Entity");
			sleep(2000);
			commonFunction.screenShot("NavigationMenu", "Pass", "Navigated to Menu -> Account Maintenance -> Change in Legal Entity");
			sleep(3000);
			commonFunction.screenShot("SREG-012", "Pass", "Change in Legal Entity page is displayed");
			sleep(2000);
			commonFunction.clickMenu("Yes ");
			commonFunction.screenShot("SREG-012", "Pass", "Change in Legal Entity page is displayed and option NO selected");
			commonFunction.enterTextboxContains("Prior Federal Employer Identification Number (FEIN)", "665478978");
			commonFunction.enterTextboxContains("Prior Employer Registration Number", "8181816");
			//commonFunction.enterCurrentDate("Date of Legal Entity change");
			commonFunction.enterFutureDate("Date of Legal Entity change", 0);
			commonFunction.enterFutureDate("Date of Notification", 0);
			commonFunction.selectDropdown("Source", "IA602");
			commonFunction.selectDropdown("Source Type", "Phone Call");
			commonFunction.clickButton("Continue ");
			sleep(2000);
			commonFunction.screenShot("EM-007", "Pass", "Change in Legal Entity Details is displayed");
			commonFunction.clickButton("Submit ");
			sleep(1000);
			commonFunction.screenShot("SUC-002", "Pass", "Change in Legal Entity Confirmation page is displayed");
			commonFunction.clickButton("Home ");
			//commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		    
			commonFunction.database_UpdateQuery("UPDATE LROUIM.T_WFA_WORK_ITEM_DETAIL SET USER_ID = '"+COMMON_CONSTANT.CSR_USER_1+"' WHERE PROCESS_DETAIL_ID IN (SELECT PROCESS_DETAIL_ID FROM T_WFA_PROCESS_DETAIL WHERE FEIN='648901426' ORDER BY UPDATED_TS desc)");
		     sleep(2000);
		     commonFunction.logoutAndLogin(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		     sleep(2000);
			    PEOPage.queue.click();
			   sleep(2000);
		    //sleep(15000);
			    commonFunction.waitForLoadingIconToDisappear();
			    commonFunction.enterTextboxContains("FEIN","648901426");
			    sleep(5000);
			    commonFuntions.screenShot("FeinSearch","Pass","feinSearch");
			    commonFuntions.clickButtonContains("Search");
			     sleep(2000);
			    PEOPage.filter.click();
			    PEOPage.filter.sendKeys("VERIFY TRANSFER FAILED RULES      ");
			    sleep(2000);
			    commonFunction.screenShot("WF-001", "Pass", "WF-001 page is displayed");
			    commonFunction.clickHyperlink("Verify Transfer Failed Rules");
			    sleep(2000);
			    commonFunction.screenShot("WF-091", "Pass", "WF-091 page is displaye");
			    commonFunction.clickButton("Open Work Item ");
			    sleep(2000);
			    //commonFunction.screenShot(", status, message);
		}

	}
