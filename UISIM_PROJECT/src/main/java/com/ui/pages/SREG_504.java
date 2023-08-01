package com.ui.pages;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_504 extends TestBase {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_504(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(how = How.XPATH, using = "//h2[@id='titleId']")
	public WebElement pageNameText;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Successor ERN')]")
	public WebElement successorERNText;

	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='9300007']")
	public WebElement successorERNValue;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Predecessor ERN')]")
	public WebElement predecessorERNText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='9300010']")
	public WebElement predecessorERNValue;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Total or Partial')]")
	public WebElement totalOrPartialText;
	
	@FindBy(how = How.XPATH, using = "//mat-label[normalize-space()='Part']")
	public WebElement totalOrPartialValue;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Submit']")
	public WebElement submitButton;
	
	public void verifyPageName(String actualPageName) throws InterruptedException {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		String name = pageNameText.getText();
		Assert.assertEquals(name, actualPageName);
	}
	
	public void verifyFilterValues() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("Filters", "Pass", "Filter validation");
		Assert.assertTrue(successorERNText.isDisplayed());
		Assert.assertTrue(successorERNValue.isDisplayed());
		Assert.assertTrue(predecessorERNText.isDisplayed());
		Assert.assertTrue(predecessorERNValue.isDisplayed());
		Assert.assertTrue(totalOrPartialText.isDisplayed());
		Assert.assertTrue(totalOrPartialValue.isDisplayed());
		stepDef.screenShot("Filters", "PASS", "Filter validation");
	}
	
	public void clickSubmitButton() throws Exception{
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		submitButton.click();
		Thread.sleep(2000);
		stepDef.screenShot("Submit", "PASS", "Submit validation");
	}
	
	public Map<String, String> database_SelectQuery(String query) throws SQLException, IOException {
		
   	 Properties prop = new Properties();
   		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java"
   				+ "\\com\\ui\\configuration\\config.properties");
   		prop.load(ip);
		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");} 
		catch (ClassNotFoundException e) {e.printStackTrace();
		}
		int i=0;
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con=(Connection) DriverManager.getConnection( url, user, password);
		System.out.println("Connected Successfully");
		
		Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery(query);
		while(rs.next())
		{
			i=i+1;
			if(i>2) {
			results.put("Fein",rs.getString("SUCCESSOR_FEIN"));
			results.put("Ean",rs.getString("SUCCESSOR_ERN"));
			results.put("EntityName", rs.getString("SUCCESSOR_NAME"));
			break;
			}
		}
		
		ResultSet rs1 =stmt.executeQuery("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE FEIN='"+results.get("Fein")+"'");
		if (rs1.next() == false) {
		      System.out.println("ResultSet in empty");}
		      else
		      {long feinValue = Long.valueOf(results.get("Fein")).longValue()+1;
		      String ernValue=  StringUtils.left(results.get("Ean"),5)+ String.valueOf((long) (Math.random()*Math.pow(10,10)));
		    
		  String updateQuery=  "UPDATE T_TX_PEO_ACCOUNT SET FEIN = '"+feinValue+"' AND EMPLOYER_REGISTRATION_NUMBER = '"+ernValue+"' WHERE FEIN='"+Long.valueOf(results.get("Fein")).longValue()+"'";
		  System.out.println(updateQuery);
		  PreparedStatement p=null;
			//Statement stmt=con.createStatement();
			//stmt.executeQuery(query);
			p=con.prepareStatement(updateQuery);
			p.execute();   }
		    	  
		    
		con.close();
		return results;
		}
	
	
}
