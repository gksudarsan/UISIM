package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class ReturnAdjustmentDeterminationLocators extends TestBase {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public ReturnAdjustmentDeterminationLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId002_reason_0']")
	public WebElement dataTableId002;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId002_reason')]")
	public WebElement dataTableId002_containsReason;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Amount Less than $1.00']")
	public WebElement amtLess1Dollar;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Unable to locate Employer']")
	public WebElement unableToLocateEmployer;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId002_')]//preceding::*[@class='mat-checkbox-inner-container'][1]")
	public WebElement dataTableId002_checkbox;
	
	@FindBy(how = How.XPATH, using = "//span[text()=' Outside the Statute']")
	public WebElement outsideTheStatute;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId001_select_0_1_radio_button-')]//preceding::*[@class='mat-radio-outer-circle'][1]")
	public WebElement dataTableId001_outerRadio;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId001_select_0_1_radio_button-')]//preceding::*[@class='mat-radio-inner-circle'][1]")
	public WebElement dataTableId001_innerRadio;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Normal- Timely']//following::*[contains(@id, 'dataTableId123_writeOffAmt')]")
	public WebElement normalTimelyWriteOffAmt;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Normal- Timely']//following::*[contains(@id, 'dataTableId123_reEstablishedAmt')]")
	public WebElement normalTimelyReestablishedAmt;
	
	@FindBy(how = How.XPATH, using = "//*[@id='updateReasonId']")
	public WebElement updateReasonId;
	
	@FindBy(how = How.XPATH, using = "//span[text()='Slop Over ']//following::*[@id='mat-checkbox-3']/preceding::span[contains(@class, 'mat-checkbox-inner-container')][1]")
	public WebElement slopOverCheckBox;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Wages Subject To Cotribution ($)')]//following::*[@id='undefined_null_0']")
	public WebElement wagesSubjectToCotributionTextBox0;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Wages Subject To Cotribution ($)')]//following::*[@id='undefined_null_1']")
	public WebElement wagesSubjectToCotributionTextBox1;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Normal Payment Amount($)')]//following::*[contains(@id,'undefined_')][3]")
	public WebElement normalPaymentAmtTextBox0;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Normal Payment Amount($)')]//following::*[contains(@id,'undefined_')][7]")
	public WebElement normalPaymentAmtTextBox1;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Comments']//following::*[contains(@id,'mat-input')]")
	public WebElement comment;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Comments']//following::*[contains(@id,'reasonForAdjustmentOtherId')]")
	public WebElement reasonForAdjustmentComment;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_radio_0_1_radio_button-input']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement Selct_Transfer_Payment_Overpayment;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='mat-mdc-checkbox-4-input']//following::*[@class='mdc-checkbox__native-control'][1]")
	public WebElement Selct_Due_Amount;
	
	@FindBy(how = How.XPATH, using = "//*[@id='undefined_null_0'][1]")
	public WebElement Enter_contribtion;
	
}
	
