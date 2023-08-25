package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

public class FraudInvestigationLocators extends TestBase {
	
	public WebDriver webDriver;
	
	public FraudInvestigationLocators(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId')]//preceding::span[@class='mat-radio-container'][1]")
	public WebElement dataTableIdRadio;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement noRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement noRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reasonExplanation']")
	public WebElement reasonExplanation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
	public WebElement commentsId;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'mat-checkbox')]")
	public WebElement matCheckboxId;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement yesRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement yesRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//mat-icon[.='task']")
	public WebElement queue;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Contribution Collection'][1]//following::*[text()='Bankruptcy'][1]")
	public WebElement bankruptcyMenuLocator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='remarks']")
	public WebElement remarks;

	@FindBy(how = How.XPATH, using = "//*[@id='apprSelected']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement approveRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='apprSelected']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement approveRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='comments']")
	public WebElement comments;
	
	@FindBy(how = How.XPATH, using = "//*[@id='bankArtryId']")
	public WebElement bankArtryId;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Remarks/Reasons for submitting Issue']//following::*[@id='remarksId']")
	public WebElement remarksTextarea;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'textarea')]")
    public WebElement Entertextarea;

    @FindBy(how = How.XPATH, using = "//*[text()='Comments']//following::*[@id='commentsId']")
    public WebElement Entercomments;

    @FindBy(how = How.XPATH, using = "//*[@id='commentsId']")
    public WebElement Entercomments_497_002;

    @FindBy(how = How.XPATH, using = "//*[@id='remarksId']//following::*[@id='remarksId']")
    public WebElement EnterRemarks;

    @FindBy(how = How.XPATH, using = "//*[text()='Resolution Details']//following::*[@id='bankArtryId']")
    public WebElement TextareaFIP010;

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
}
