package com.ui.pages;

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

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class COL_474 extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public COL_474(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//ul//li[contains(text(),'Enter only one of the following: Bankruptcy Case Number')]")
	public WebElement ERNOROwnerSSNORFEINErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//ul//li[text()='No records found.']")
	public WebElement noRecordFound;
	
	@FindBy(how = How.XPATH, using = "//ul//li[text()='Select a record to track the Bankruptcy.']")
	public WebElement selectARecordErrorMsg;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-button[contains(@class,'mat-radio-button')])[1]")
	public WebElement selectFirstRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ContributionCollectionBankruptcy' and text()='Bankruptcy']")
	public WebElement bankruptcynavBtn;
	
	
	
	
	
	
	
	public Map<String, String> database_SelectQuery(String query) throws SQLException, IOException {

		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java" + "\\com\\ui\\configuration\\config.properties");
		prop.load(ip);
		System.out.println(query);
		Map<String, String> results = new HashMap<String, String>();
		try {// Load the IBM Data Server Driver for JDBC and SQLJ with DriverManager
			Class.forName("com.ibm.db2.jcc.DB2Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		int i = 0;
		String url = "jdbc:db2://100.96.3.201:55000/NYUISTDB:currentSchema=LROUIM;sslConnection=true;";
		String user = prop.getProperty("databaseUserId");
		String password = "Tata@1234";
		Connection con = (Connection) DriverManager.getConnection(url, user, password);
		System.out.println("Connected Successfully");

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			i = i + 3;
			if (i > 4) {
				results.put("Fein", rs.getString("FEIN"));
				results.put("Ean", rs.getString("EAN"));
				results.put("CaseNumber", rs.getString("CASE_NUMBER"));
				break;
			}
		}

		ResultSet rs1 = stmt
				.executeQuery("SELECT * FROM T_TX_PEO_ACCOUNT ttpa WHERE FEIN='" + results.get("Fein") + "'");
		if (rs1.next() == false) {
			System.out.println("ResultSet in empty");
		} else {
			long feinValue = Long.valueOf(results.get("Fein")).longValue() + 1;
			String ernValue = StringUtils.left(results.get("Ean"), 5)
					+ String.valueOf((long) (Math.random() * Math.pow(10, 10)));

			String updateQuery = "UPDATE T_TX_PEO_ACCOUNT SET FEIN = '" + feinValue
					+ "' AND EMPLOYER_REGISTRATION_NUMBER = '" + ernValue + "' WHERE FEIN='"
					+ Long.valueOf(results.get("Fein")).longValue() + "'";
			System.out.println(updateQuery);
			PreparedStatement p = null;
			// Statement stmt=con.createStatement();
			// stmt.executeQuery(query);
			p = con.prepareStatement(updateQuery);
			p.execute();
		}

		con.close();
		return results;
	}
	
}


