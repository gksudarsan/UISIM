package com.employerContibution.EM;


		
		
		import java.util.Map;

		import org.testng.annotations.Test;

		import com.aventstack.extentreports.Status;
		import com.ui.base.TestBase;
		import com.ui.pages.EM_005;
		import com.ui.pages.EmployerRegisterPage;
		import com.ui.pages.HomePage;
		import com.ui.pages.SREG_027;
		import com.ui.pages.SREG_030;
		import com.ui.pages.SREG_503;
		import com.ui.pages.SREG_504;
		import com.ui.pages.SUC_002;
		import com.ui.utilities.COMMON_CONSTANT;

		import net.bytebuddy.description.annotation.AnnotationList.Empty;
		import stepDefinitions.commonStepDefinitions;

		public class EM_310_07_002_Verify_Employer_is_unable_to_report_a_change_in_Legal_entity_when_the_answer_to_Have_you_changed_legal_entity_is_No extends TestBase{


			@Test(priority = 1, description = "Test sample", groups = { "Regression" })
			public void Testing123() throws Exception {
				
				test = report.createTest("Verify_Employer_is_unable_to_report_a_change_in_Legal_entity_when_the_answer_to_Have_you_changed_legal_entity_is_No");
				
				commonStepDefinitions commonFunction = new commonStepDefinitions();
				EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
				HomePage home = new HomePage(driver);
				SREG_030 sreg030 = new SREG_030(driver);
				SREG_027 sreg27 = new SREG_027(driver);
				SUC_002 suc002 = new SUC_002(driver);
				 
				
				test.log(Status.INFO, "Logging to the application");
				//stepDef.login(prop.getProperty("EMPLOYER_USER_4"), prop.getProperty("CSR_Pass"));
				//stepDef.login(COMMON_CONSTANT.EMPLOYER_USER_4, COMMON_CONSTANT.EMPLOYER_USER_4_PASSWORD);

				commonFunction.login(COMMON_CONSTANT.EMPLOYER_USER_1.toUpperCase(), COMMON_CONSTANT.EMPLOYER_USER_1_PASSWORD);
sleep(2000);
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
				commonFunction.clickMenu("No ");
				commonFunction.screenShot("SREG-012", "Pass", "Change in Legal Entity page is displayed and option NO selected");
				commonFunction.clickButton("Continue ");
				sleep(5000);
				commonFunction.screenShot("Home", "Pass", "Home page is displayed");
				
			}

		}
