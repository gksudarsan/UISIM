package com.ui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_084 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_084(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'only Alphabets')]")
	public WebElement cityErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//mat-error[contains(text(),'Zip Code')]")
	public WebElement zipCodeErrorMsg;
	
	@FindBy(how = How.XPATH, using = "//li[contains(text(),'A1B')]")
	public WebElement PostalCodeErrorMsg;

	@FindBy(how = How.XPATH, using = "//mat-label[@class='upload-doc']")
	public WebElement uploadDocSec;
	
	@FindBy(how = How.XPATH, using = "//a[@id='addadditionaladdressesId']")
	public WebElement addAddtionalAddress;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(@class,'help')]//span")
	public WebElement helpButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@id,'previous')]")
	public WebElement previousButton;
	
	@FindBy(how = How.XPATH, using = "//button[contains(@id,'continue')]")
	public WebElement continueButton;

	@FindBy(how = How.XPATH, using = "//a[text()=' Add Another Related Business ']")
	public WebElement addAnoterlink;

	@FindBy(how = How.XPATH, using = "//button[contains(@id,'cancel')]")
	public WebElement cancelButton;
	
	
	@FindBy(how = How.XPATH, using = "//button[contains(@id,'submit')]")
	public WebElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'mat-table-main')]")
	public WebElement tableHeadingList;

	@FindBy(how = How.XPATH, using = "(//mat-cell//u[text()='Edit'])[1]")
	public WebElement editActionBtn;
	
	@FindBy(how = How.XPATH, using = "//mat-cell//u[text()='Delete']")
	public WebElement deleteActionBtn;

	@FindBy(how = How.XPATH, using = "(//mat-cell//u[text()='Add'])[1]")
	public WebElement addActionBtn;

	@FindBy(how = How.XPATH, using = "(//*[@class='mat-radio-container'])[1]/following::span//mat-label[text()='Set up from Power of Attorney - IA900 form']")
	public WebElement setUpPOARadioBtn;
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Browse']")
	public WebElement browseLink;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'City')]//following::input[1]")
	public WebElement cityField;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Zip Code')]//following::input[1]")
	public WebElement zipCodeField;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-button[contains(@id,'dataTable')])[1]")
	public WebElement selectradioBtn1;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'FEIN')]//following::input[1]")
	public WebElement feinField;
	
	//WF-001 page
	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'Review Employer Type')])[1]")
	public WebElement reviewemployertypelink;

	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'Verify Agent Rep Task')])[1]")
	public WebElement verifyAgentRepTasklink;
	
	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'DOL DTF Discrepancy')])[1]")
	public WebElement dolDtfTasklink;
	
	
	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'Review Comments')])[1]")
	public WebElement reviewCommentslink;

	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'Verify Transfer Failed Rules')])[1]")
	public WebElement verifyTransferlink;

	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'DOL DTF Discrepancy')])[1]")
	public WebElement dolDTFlink;

	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[text()='Delete'])[1]")
	public WebElement deleteLinkFirst;

	@FindBy(how = How.XPATH, using = "(//mat-cell//a//u[contains(text(),'Verify Transfer Failed Rules')])[1]")
	public WebElement verifyTransferpelink;

}
