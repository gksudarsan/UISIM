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

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Period From Quarter/Year ')]//following::mat-select[1]")
    public WebElement Quarter_start;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' Audit Period From Quarter/Year ')]//following::mat-select[2]")
    public WebElement Year_start;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' To Quarter/Year ')]//following::mat-select[1]")
    public WebElement Quarter_end;

    @FindBy(how = How.XPATH, using = "//mat-label[contains(.,' To Quarter/Year ')]//following::mat-select[2]")
    public WebElement Year_end;

    @FindBy(how = How.XPATH, using = "//span[text()=' 2 ']")
    public WebElement Value_Quarter_start; 

    @FindBy(how = How.XPATH, using = "//span[text()=' 3 ']")
    public WebElement Value_Quarter_end;

    @FindBy(how = How.XPATH, using = "//span[text()= ' 2022 ']")
    public WebElement Value_Year;
    
    @FindBy(how = How.XPATH, using = "//span[text()=' 1 ']")
    public WebElement Value_Quarter_1;
    
	@FindBy(how = How.XPATH, using = "//*[text()='Reason/basis for Unsatisfied Judgment Protest']//following::*[@id='reasonBasisUnsatisfiedJudgProtestId']")
	public WebElement reasonBasisUnsatisfiedJudgProtestId;
   
	//
    @FindBy(how = How.XPATH, using = "//*[@id='ownerAddress_address1']")
    public WebElement ownerAddress_address1;
    
    @FindBy(how = How.XPATH, using = "//*[@id='ownerAddress_city']")
    public WebElement ownerAddress_city;
	
    @FindBy(how = How.XPATH, using = "//*[@id='ownerAddress_zip']")
    public WebElement ownerAddress_zip;
    
    @FindBy(how = How.XPATH, using = "//*[@id='ownerPrimaryPhoneBean']")
    public WebElement ownerPrimaryPhoneBean;
    
    @FindBy(how = How.XPATH, using = "//*[@id='ownerSecondaryPhoneBean']")
    public WebElement ownerSecondaryPhoneBean;
    
    @FindBy(how = How.XPATH, using = "//*[@id='companyAddress_address1']")
    public WebElement companyAddress_address1;
    
    @FindBy(how = How.XPATH, using = "//*[@id='companyAddress_city']")
    public WebElement companyAddress_city;
	
    @FindBy(how = How.XPATH, using = "//*[@id='companyAddress_zip']")
    public WebElement companyAddress_zip;
    
    @FindBy(how = How.XPATH, using = "//*[@id='doingBusinessAs']")
    public WebElement doingBusinessAs;
    
    @FindBy(how = How.XPATH, using = "//*[@id='companyPhoneBean']")
    public WebElement companyPhoneBean;
    
    @FindBy(how = How.XPATH, using = "//*[@id='worksiteAddressId_address1']")
    public WebElement worksiteAddressId_address1;
    
    @FindBy(how = How.XPATH, using = "//*[@id='worksiteAddressId_city']")
    public WebElement worksiteAddressId_city;
    
    @FindBy(how = How.XPATH, using = "//*[@id='worksiteAddressId_zip']")
    public WebElement worksiteAddressId_zip;
    
    @FindBy(how = How.XPATH, using = "//*[@id='supervisorFirstName']")
    public WebElement supervisorFirstName;
    
    @FindBy(how = How.XPATH, using = "//*[@id='supervisorLastName']")
    public WebElement supervisorLastName;
    
    @FindBy(how = How.XPATH, using = "//*[@id='supervisorPrimaryPhoneBean']")
    public WebElement supervisorPrimaryPhoneBean;
    
    @FindBy(how = How.XPATH, using = "//*[@id='supervisorSecondaryPhoneBean']")
    public WebElement supervisorSecondaryPhoneBean;
    
    @FindBy(how = How.XPATH, using = "//*[@id='explainNotPayCorrectRateForOvertime']")
    public WebElement explainNotPayCorrectRateForOvertime;
    
    @FindBy(how = How.XPATH, using = "//*[@id='explainNotPayEmployeesAllHoursWorked']")
    public WebElement explainNotPayEmployeesAllHoursWorked;
    
    @FindBy(how = How.XPATH, using = "//*[@id='explainNotPayMinimumWage']")
    public WebElement explainNotPayMinimumWage;
    
    @FindBy(how = How.XPATH, using = "//*[@id='explainNotKeepProperTimeRecord']")
    public WebElement explainNotKeepProperTimeRecord;
    
    @FindBy(how = How.XPATH, using = "//*[@id='explainReceiveWageKickbacks']")
    public WebElement explainReceiveWageKickbacks;
    
    @FindBy(how = How.XPATH, using = "//*[@id='fraudInformation']")
    public WebElement fraudInformation;
    
    @FindBy(how = How.XPATH, using = "//*[@id='externalUserFirstName']")
    public WebElement externalUserFirstName;
    
    @FindBy(how = How.XPATH, using = "//*[@id='externalUserLastName']")
    public WebElement externalUserLastName;
    
    @FindBy(how = How.XPATH, using = "//*[@id='externalUserAddressId_address1']")
    public WebElement externalUserAddressId_address1;

    @FindBy(how = How.XPATH, using = "//*[@id='externalUserAddressId_city']")
    public WebElement externalUserAddressId_city;
    
    @FindBy(how = How.XPATH, using = "//*[@id='externalUserAddressId_zip']")
    public WebElement externalUserAddressId_zip;
    
    @FindBy(how = How.XPATH, using = "//*[@id='externalUserPrimaryPhoneNo']")
    public WebElement externalUserPrimaryPhoneNo;
    
    @FindBy(how = How.XPATH, using = "//*[@id='externalUserSecondaryPhoneNo']")
    public WebElement externalUserSecondaryPhoneNo;
    
    @FindBy(how = How.XPATH, using = "//*[@id='additionalComments']")
    public WebElement additionalComments;    
    
}
