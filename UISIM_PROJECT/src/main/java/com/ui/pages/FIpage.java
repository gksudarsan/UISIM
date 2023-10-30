package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FIpage {

	public WebDriver driver;

	public FIpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'textarea')]")
	public WebElement Entertextarea;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Comments']//following::*[@id='commentsId']")
	public WebElement Entercomments;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
	public WebElement Entercomments_497_002;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement ClickMenu;
		
	@FindBy(how = How.XPATH, using = "//*[@id='remarksId']//following::*[@id='remarksId']")
	public WebElement EnterRemarks;
		
	@FindBy(how = How.XPATH, using = "//*[text()='Resolution Details']//following::*[@id='bankArtryId']")
	public WebElement Textarea;
		
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year Start ')]//following::mat-select[1]")
    public WebElement Quarter_start;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year Start ')]//following::mat-select[2]")
    public WebElement Year_start;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year End ')]//following::mat-select[1]")
    public WebElement Quarter_end;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Quarter/Year End ')]//following::mat-select[2]")
    public WebElement Year_end;

    @FindBy(how = How.XPATH, using = "//span[text()=' 2 ']")
    public WebElement Value_Quarter_start;

    @FindBy(how = How.XPATH, using = "//span[text()=' 3 ']")
    public WebElement Value_Quarter_end;

    @FindBy(how = How.XPATH, using = "//span[text()= ' 2022 ']")
    public WebElement Value_Year;
  //F&I
  	@FindBy(how = How.XPATH, using = "//*[.='Remarks/Reasons for submitting Issue (must not exceed 2000 characters)']//following::*[@id='reasons']")
  	public WebElement Reasons_AuditProtest;
  	
  	@FindBy(how = How.XPATH, using = "//*[.='Comments']//following::*[@id='commentsId']")
  	public WebElement comment_AuditProtestTask;

  	@FindBy(how = How.XPATH, using = "//*[.='Resolution Details']//following::*[@id='bankArtryId']")
  	public WebElement Resolution_Details;
  	
  	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_radio_0_1_radio_button']//preceding::*[@class='mdc-radio__inner-circle'][1]")
  	public WebElement select_Penalty;
  		
  	@FindBy(how = How.XPATH, using = "//*[.='Resolution Details']//following::*[@id='resolutionDetailsId']")
  	public WebElement ResolutionDetails_FraudPenaltySummary;
  	
  	@FindBy(how = How.XPATH, using = "//*[.='Resolution Details']//following::*[@id='commentsId']")
  	public WebElement ResolutionDetails_ReviewPenaltyAbatementRequest;
  	
  	@FindBy(how = How.XPATH, using = ".//*[@id='sustainCode-input']//preceding::*[@class='mat-radio-inner-circle'][1]")
  	public WebElement select_sustainCode;

  	@FindBy(how = How.XPATH, using = ".//*[@id='cancelCode-input']//preceding::*[@class='mat-radio-inner-circle'][1]")
  	public WebElement select_cancelCode;
  	
  	@FindBy(how = How.XPATH, using = "//*[.='Reason/basis for Unsatisfied Judgment Protest']//following::*[@id='reasonBasisUnsatisfiedJudgProtestId']")
  	public WebElement Reason_reasonBasisUnsatisfiedJudgProtest;

  	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Select')]//preceding::*[@class='mdc-checkbox__native-control'][1]")
  	public WebElement selectcheckbox_SearchAssociateDocuments;

	@FindBy(how = How.XPATH, using = "//*[.='Reason/basis for Wage Garnishment Protest (must not exceed 2000 characters)']//following::*[@id='reasons']")
	public WebElement Reason_WageGarnishmentProtest;	

//EOA
	
	@FindBy(how = How.XPATH, using = "//div[@class='mdc-checkbox__ripple']//preceding::*[contains(@id, 'employerbenefits')]")
			public WebElement employerbenefits;	
}
