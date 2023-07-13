package com.ui.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

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


import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_503 extends TestBase{

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_503(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[@id='SREG-503']")
	public WebElement screenIdText;
	
	@FindBy(how = How.XPATH, using = "//label[@for='soldBusinessId_Yes-input']//span[@class='mat-radio-inner-circle']")
	public WebElement selectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='successorEanId']")
	public WebElement successorEan;
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Length of this response must be at least 7 characters.']")
	public WebElement invalidSuccessorEan;
	
	@FindBy(how = How.XPATH, using = "//li[text()='Employer Registration Number is invalid.']")
	public WebElement eanNotValid;
	
	
	public Boolean validateScreenIdText() {
		selectRadioButton.click();
		Boolean flag = screenIdText.isDisplayed();
		return flag;
	}
	
	@FindBy(how = How.XPATH, using = "//label[@for='soldBusinessId_Yes-input']//span[@class='mat-radio-inner-circle']")
	public WebElement haveYouSoldYourRadio;
	
	@FindBy(how = How.XPATH, using = "//input[@id='successorEanId']")
	public WebElement successorInput;
	
	@FindBy(how = How.XPATH, using = "//input[@id='successorTelePhoneId']")
	public WebElement successorTelePhoneInput;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Search']")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Search']")
	public WebElement saerchButton;
	
	@FindBy(how = How.XPATH, using = "//label[@for='sellAllPartofBusinessId_P-input']//span[@class='mat-radio-outer-circle']")
	public WebElement partRadioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='effectiveDateOfTransferId']")
	public WebElement effectiveDateTransfer;
	
	@FindBy(how = How.XPATH, using = "//label[@for='suppressPtId_Yes-input']//span[@class='mat-radio-outer-circle']")
	public WebElement yesRadio;
	
	@FindBy(how = How.XPATH, using = "//label[@for='suppressPtPercentageId_No-input']//span[@class='mat-radio-outer-circle']")
	public WebElement eAANoRadio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Source']/../../div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement sourceDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceValue;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Source Type']/../../div/mat-form-field/div/div/div/following-sibling::div/following-sibling::div/mat-select")
	public WebElement sourceTypeDropDown;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Correspondence/Email ']")
	public WebElement sourceTypeValue;
	
	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Continue']")
	public WebElement continueButton;
	
	
	public void fillFormDetails() throws Exception {
		sleep(2000);
		stepDef.waitForLoadingIconToDisappear();
		stepDef.screenShot("Form", "Pass", "Form in blank state");
		haveYouSoldYourRadio.click();
		successorInput.sendKeys(prop.getProperty("successor"));
		saerchButton.click();
		Thread.sleep(3000);
		partRadioButton.click();
		effectiveDateTransfer.sendKeys(prop.getProperty("Date_EM_310_02"));
		sourceDropDown.click();
		stepDef.screenShot("Form1", "Pass", "Entering data");
		Thread.sleep(3000);
		stepDef.ScrollMenu(" Correspondence/Email ");
		try {
			stepDef.waitForElementClicable(sourceValue);
		}catch(ElementClickInterceptedException e) {
			stepDef.safeJavaScriptClick(sourceValue);
		}
		System.out.println("Selected drop down 1");
		Thread.sleep(2000);
		sourceTypeDropDown.click();
		stepDef.ScrollMenu(" Correspondence/Email ");
		stepDef.screenShot("Form2", "Pass", "Entering data");
		Thread.sleep(2000);
		try {
			stepDef.waitForElementClicable(sourceTypeValue);
		}catch(ElementClickInterceptedException e) {
			stepDef.safeJavaScriptClick(sourceTypeValue);
		}
		
		System.out.println("Selected drop down 2");
		stepDef.screenShot("Form3", "Pass", "Entered data");
		continueButton.click();
		Thread.sleep(5000);
	}
	
	
	public Boolean validateEanMessageText() throws InterruptedException {
		successorEan.sendKeys("6789");
		Thread.sleep(2000);
		Boolean flag = invalidSuccessorEan.isDisplayed();
		return flag;
	}
	
	public Boolean validateEanMessageText2() throws InterruptedException {
		successorEan.sendKeys("12345678");
		Thread.sleep(2000);
		Boolean flag = eanNotValid.isDisplayed();
		return flag;
	}
	
	public Map<String, String> database_SelectQuery_Successor(String query) throws SQLException, IOException {

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
            results.put("legalName", rs.getString("SUCCESSOR_NAME"));
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
