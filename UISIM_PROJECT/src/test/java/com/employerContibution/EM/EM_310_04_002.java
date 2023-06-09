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
	import com.ui.pages.HomePage;
	import com.ui.pages.PEOPage;
	import com.ui.pages.employerManagement;
	import com.ui.utilities.COMMON_CONSTANT;
	
	import stepDefinitions.commonStepDefinitions;
	
	public class EM_310_04_002 extends TestBase{
	
		@Test()
		public void EM_310_04_002() throws Exception {

commonStepDefinitions cf = new commonStepDefinitions();	/*
 * String feinValue1 =StringUtils.left( String.valueOf((long)
 * (Math.random()*Math.pow(10,10))),5); String feinValue2 = "9999" ; String
 * feinValue = feinValue2 + feinValue1 ; System.out.println("FEIN NUMBER = "
 * +feinValue);
 */

EmployerRegisterPage empRegPage = new EmployerRegisterPage(driver);
employerManagement em =  new employerManagement();
HomePage homePage = new HomePage(driver);
PEOPage PEOPage = PageFactory.initElements(driver, PEOPage.class);
test = report.createTest("EM_310_04_002");

//-----Login
cf.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
cf.screenShot("ApplicationLogin", "Pass", "Login is successful");	

			//-----DB query
			Map<String, String> databaseResults1 = cf.database_SelectQuerySingleColumn(
					"SELECT * FROM T_EMPLOYER_ACCOUNT tea WHERE  ACCOUNT_STATUS = 'SUSB'" , "EAN"); 
			String EAN = databaseResults1.get("EAN");
			System.out.println("EAN_NAME  = " +EAN);
			
			
			//-----Menu
			sleep(2000);
			cf.screenShot("Menu", "Pass", "Menu page");
			cf.clickMenu("Menu");	
			cf.ScrollMenu("Account Maintenance");
			cf.clickMenu("Account Maintenance");
			sleep();
			cf.ScrollMenu("Business Acquisition");
			cf.clickMenu("Business Acquisition");
			sleep(2000);
			cf.screenShot("Menu", "Pass", "Menu selected");
			
			//driver.findElement(By.xpath("//button[@class='mat-focus-indicator mat-raised-button mat-button-base mat-primary']")); Thread.sleep(2000);
			
			//----EM-011
			sleep(2000);
			cf.screenShot("Business Acquisition – Enter ERN", "Pass", "Launched to  acquired the business page");
			cf.enterTextbox("Employer Registration Number", EAN);
			sleep(2000);
			cf.screenShot("Business Acquisition – Enter ERN", "Pass", "Entered data on EM-011");
			sleep(2000);
			cf.clickButton("Continue ");
			
			
			//------SREG -011
			sleep(2000);
			cf.screenShot("Business Acquisition ", "Pass", "Successfully landed on SREG -011");
			sleep(2000);
			cf.selectRadioQuestions("Have you acquired the business of another employer liable for New York State Unemployment Insurance?", "Yes ");
					sleep(2000);
					
					empRegPage.eanBeanId.sendKeys(EAN);
					sleep(2000);
			cf.clickButton(" Search ");
			sleep(2000);
		
			cf.screenShot("Business Acquisition", "Pass", "Details entered in SREG-011 page");
			//homePage.commentBox.sendKeys("Test logic");
			//cf.enterTextboxContains("Legal Name of Business", "hags gsg");
			//empRegPage.tradeNameId_SREG011.sendKeys("Cooking Inc");
			
			/*empRegPage.address1_SREG011.sendKeys("Clark Residence Hall Poly");
			empRegPage.city_SREG011.sendKeys("Brooklyn");
			empRegPage.zip_SREG011.sendKeys("11201");*/
			sleep(2000);
			cf.selectRadioQuestions("Did you acquire all or part of the business?", "PART");
			//cf.selectRadioQuestions("Did you acquire all or part of the business?", "ALL");
			sleep(2000);
			cf.enterPastDate("Acquisition Date", 5);
			
			//cf.enterTextboxContains("Acquisition Date", "4/4/2023");
			//cf.enterTextboxContains("Notification date of Transfer", "4/4/2023");
			
			cf.enterPastDate("Notification date of Transfer", 25);
			sleep(3000);
			cf.screenShot("Business Acquisition", "Pass", "Details entered in SREG-011 page");
			cf.selectDropdown("Source", "Correspondence/Email");
			Thread.sleep(2000);
			cf.selectDropdown("Source Type", "Correspondence/Email");
			sleep(3000);
			cf.screenShot("Business Acquisition", "Pass", "Details entered in SREG-011 page");
			Thread.sleep(2000);
			cf.clickButton("Continue ");
			//---pop up
			try {
			cf.clickButtonContains(" Yes ");
			}
			catch (Exception e)
			{
				System.out.println("pop up not appeared");
			}
			// SREG - 012
			sleep(2000);
			cf.screenShot("Business Acquisition ", "Pass", "Successfully landed on SREG -012");
			sleep(2000);
			cf.clickButton("Submit ");
			sleep(2000);
			cf.screenShot("Home", "Pass", "Successfully launched to Home");
			
			//execute by pg

	}
}