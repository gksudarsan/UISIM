package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PEOPage {
	public WebDriver driver;

	public PEOPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[.='PEO Individual Registration']//preceding::span[@class='mat-radio-container'][1]")
	public WebElement individualPeo;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ProfessionalEmployerOrganization(PEO)'][1]")
	public WebElement menuPeo;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'PEO Conversion')]//following::mat-select[1]")
	public WebElement selectionPeoDropdown;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'mat-option mat-focus-indicator')][4]")
	public WebElement conversionPeoDropdown;
	
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_address1']")
	public WebElement addressLine1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_address2']")
	public WebElement addressLine2;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_city']")
	public WebElement addressCity;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_zip']")
	public WebElement addressZip;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_address1']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressLine1ErrorLabel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_address2']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressLine2ErrorLabel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_city']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressCityErrorLabel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_zip']//following::mat-error[contains(.,'Required')][1]")
	public WebElement addressZipErrorLabel;
	
	
	@FindBy(how = How.XPATH, using = "//*[.='Primary Physical Address']//following::*[@class='mat-radio-container'][2]")
	public WebElement uspsAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='Current Additional Address']//following::*[@class='mat-radio-container'][1]")
	public WebElement currentAdditionalAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CIN-999access.continue']")
	public WebElement UspsContinueButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_attention']")
	public WebElement attentionCareOf;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' + ADD PEO MEMBER ']")
	public WebElement addPeoMember;
	
	@FindBy(how = How.XPATH, using = "//mat-icon[.='task']")
	public WebElement queue;
	
	@FindBy(how = How.XPATH, using = "//*[.='PEO Group Registration']//preceding::span[@class='mat-radio-container'][1]")
	public WebElement groupRegPeo;
	

	@FindBy(how = How.XPATH, using = "//*[.='USPS Suggested Address']//following::*[@class='mat-radio-container'][1]")
	public WebElement uspsSuggestedAddress;
	
	@FindBy(xpath = "//*[.='PEO Exempt Registration']//preceding::span[@class='mat-radio-container'][1]")
	public WebElement peoExemptRegisterRadio;
	
	@FindBy(how = How.XPATH, using ="//a[text()=' ADVANCED SEARCH']")
	public WebElement advancedSearch;
	
	@FindBy(how = How.XPATH, using ="//strong[text()='Client List']")
	public WebElement clientListHeading;
	
	@FindBy(how = How.XPATH, using ="//span[text()='Federal Employer Identification Number']/../following-sibling::mat-label")
	public WebElement feinValue;
	
	@FindBy(how = How.XPATH, using = "//*[.='Mailing Address']//following::*[@class='mat-radio-container'][1]")
	public WebElement mailingAddress;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'ADD PEO MEMBER')][1]")
	public WebElement peoAddMember;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_selectedPeoId_0_1_radio_button']//following::*[@class='mat-radio-container'][1]")
	public WebElement peoRenewRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'PEO Registration')][1]")
	public WebElement peoRegister;
	
	@FindBy(how = How.XPATH, using = "//*[@class='mat-radio-container'][1]")
	public WebElement peoRadioButton;
	
	@FindBy(how = How.XPATH, using ="//strong[text()='Browse']")
	public WebElement browserLinkManagePEOPage;
	
	@FindBy(how = How.XPATH, using ="//mat-label[text()='Uploaded Documents']")
	public WebElement uploadeDocManagePEOPage;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' Add Additional Address ']")
	public WebElement clickOnAdditionalLink;
	
	@FindBy(how = How.XPATH, using = "//span[text()='PEO Status']/../following-sibling::mat-label")
	public WebElement withdrawnMessage;
	
	@FindBy(how = How.XPATH, using = "//li[text()='PEO Status Updated Successfully']")
	public WebElement updatePeoMessage;
	
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()-1]")
	public WebElement otherDetails;
	
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()]")
	public WebElement otherDetails1;
	
	@FindBy(how = How.XPATH, using = "//*[.='business Address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement uspsAdd;
	
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()-1]")
	public WebElement otherDetails_New1;
	
	@FindBy(how = How.XPATH, using = "(//input[@type='text'])[last()]")
	public WebElement otherDetails1_New2;
	
	
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
			results.put("Fein",rs.getString("FEIN"));
			results.put("Ean",rs.getString("EAN"));
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
     
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_address1']")
	public WebElement listCurrentAddressLine1;
	 
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_address2']")
	public WebElement listCurrentAddressLine2;
	
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_city']")
	public WebElement listCurrentAddressCity;
	
	@FindBy(how = How.XPATH, using = "//*[@id='additionalAddressId0_zip']")
	public WebElement listCurrentAddressZip;
	
	@FindBy(how = How.XPATH, using = "//*[@id='access.peo.verify.adddress.uspsaddress-input'][@name='mat-radio-group-34']")
	public WebElement CurrentUspsAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@id='access.peo.verify.adddress.mailingaddress-input']")
	public WebElement mailingUspsAddress;
     
	@FindBy(how = How.XPATH, using = "//*[.=' Address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement enteredAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CIN-999access.continue']")
	public WebElement popContinueButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='access.peo.verify.adddres.physicaladdress-input'][@name='mat-radio-group-28']")
	public WebElement PrimaryPhysicalAddress;
	
	
}
