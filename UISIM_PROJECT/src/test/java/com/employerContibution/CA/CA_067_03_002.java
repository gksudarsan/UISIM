//------------------------Palak---------------------------------------

package com.employerContibution.CA;

import java.util.Map;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ui.base.TestBase;
import com.ui.pages.CaPage;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.PEOPage;
import com.ui.utilities.COMMON_CONSTANT;

import stepDefinitions.commonStepDefinitions;

public class CA_067_03_002 extends TestBase {
	@Test
	public void CA_067_03_002() throws Exception
	{
		
		 test = report.createTest("CA.067.03.002 -- Verify CSR can add Deposit Details of  'Processing Vendor' for deposit type 'Paper IA'  and update interface status 'Reconciled'");
		 LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		 CaPage CaPage = PageFactory.initElements(driver, CaPage.class);
		 commonStepDefinitions commonFuntions= new commonStepDefinitions();
		 commonFuntions.login(COMMON_CONSTANT.CSR_USER_1.toUpperCase(), COMMON_CONSTANT.CSR_USER_1_PASSWORD);
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("ApplicationLogin","Pass","Login is successful");
		 commonFuntions.clickMenu("menu");
		 sleep(3000);
		 //commonFunti3ons.clickMenu("Inquiry");
		 //commonFuntions.clickMenu("Contribution Inquiry");	
		 
		 commonFuntions.ScrollMenu("Add Daily Deposits");
		 commonFuntions.screenShot("Menu","Pass","Add Daily Deposits");
		 commonFuntions.clickMenu("Add Daily Deposits");	
		 sleep(2000);
		 commonFuntions.waitForLoadingIconToDisappear();
		 commonFuntions.screenShot("AddDailyDeposit","Pass","Add Daily Deposit Screen");
		 //Map<String, String> databaseResults = commonFuntions.database_SelectQuerySingleColumn("SELECT * FROM T_EMPLOYER te WHERE EAN = '0463015' ORDER BY UPDATED_TS","EAN");
			//String ernNumber=databaseResults.get("EAN");
			commonFuntions.enterTextboxContains("Deposit Date", "06/08/2022");
			sleep(2000);
			commonFuntions.clickButtonContains("Search");
			sleep(2000);
			commonFuntions.waitForLoadingIconToDisappear();
			 commonFuntions.screenShot("AfterSearch","Pass","After Search");
			 commonFuntions.selectActionTableParameterizedId("Bulk File",1,1,"Deposit Details of DTF","id01","checkBox","");
			 commonFuntions.selectActionTableParameterizedId("Bulk File",7,1,"Deposit Details of DTF","id01","link","2");
			 sleep(2000);
			 commonFuntions.screenShot("Override1","Pass","Clicked on override without deposit date");
			 commonFuntions.errorContent("Enter the deposit amount for the selected type of deposit.");
			 commonFuntions.selectActionTableParameterizedId("Bulk File",1,1,"Deposit Details of DTF","id01","textBox","100");
			 
			 commonFuntions.selectActionTableParameterizedId("Bulk File",7,1,"Deposit Details of DTF","id01","link","2");
			 sleep(2000);
			 commonFuntions.screenShot("Override2","Pass","Clicked on override without comments");
			 
			 commonFuntions.errorContent("Reason for Override is mandatory.");
commonFuntions.selectActionTableParameterizedId("Bulk File",8,1,"Deposit Details of DTF","id01","listBox","overideComments");
commonFuntions.screenShot("updated","Pass","override Comments Updated");	
			 commonFuntions.selectActionTableParameterizedId("Bulk File",7,1,"Deposit Details of DTF","id01","link","2");
			 commonFuntions.screenShot("Override3","Pass","Clicked on override with comments");
			 //commonFuntions.screenShot("updated","Pass","Values Updated");	
			
	}

}
