package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_600 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_600(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'PTIN')]//following::input[1]")
	public WebElement ptinField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Trade Name or Doing Business As')]//following::input[1]")
	public WebElement tradeNameField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Address Line 1 ')]//following::input[1]")
	public WebElement addressLineField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Mailing Address ')]//following::input[1]")
	public WebElement mailingAddField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'City ')]//following::input[1]")
	public WebElement CityField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Zip Code')]//following::input[1]")
	public WebElement zipCodeField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Contact Number ')]//following::input[1]")
	public WebElement contactNoField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'E-mail Address')]//following::input[1]")
	public WebElement emailAddField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Fax Number ')]//following::input[1]")
	public WebElement FaxNoField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'TPR ID')]//following::input[1]")
	public WebElement tprIdField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'SIDES BROKER Start Date')]//following::input[1]")
	public WebElement sidesBrokerStartDateField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'SIDES BROKER End Date')]//following::input[1]")
	public WebElement sidesBrokerEndDateField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Comments')]//following::input[1]")
	public WebElement commentsField;
	
	@FindBy(how = How.XPATH, using = "//li[.='Either PTIN OR FEIN is required.'][1]")
	public WebElement ptinErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//li[.='Attention/Careof cannot contain ATTN, DBA, C/0, c/o'][1]")
	public WebElement careOfErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'only Alphabets, Numbers')]")
	public WebElement alphabetsErrorMessage;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='commentId']")
	public WebElement commentAreaField;
	
	
	
	

	
}
