package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BclPage {
	public WebDriver driver;

	public BclPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_radio_0_1_radio_button']//following::*[@class='mat-radio-container'][1]")
	public WebElement selectRadioButton;	
	
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_radio_0_1_radio_button-input']")
	public WebElement pendingReferralSelectRadioButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='flagsOnAccount'][1]/mat-row[1]/mat-cell[1]//u[1]")
	public WebElement nprNoticeDate;
	
	@FindBy(how = How.XPATH, using = "//*[@id='flagsOnAccount'][1]/mat-row[1]/mat-cell[3]//u[1]")
	public WebElement viewGenerateLetter;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Update All')]//following::*[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin'][1]")
	public WebElement updateAllCheckBox;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Update Referral')]//following::*[@class='mat-checkbox-inner-container mat-checkbox-inner-container-no-side-margin'][1]")
	public WebElement updateReferralCheckBox;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Update Referral')]//following::mat-select[1]")
	public WebElement updateReferralStatus;
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Update Referral')]//following::mat-select[2]")
	public WebElement updateReferralReason;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Hold'][1]")
	public WebElement updateReferralStatusValue;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Hold Action and/or Another Flag on Account'][1]")
	public WebElement updateReferralReasonValue;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId')]//preceding::span[@class='mat-radio-container'][1]")
	public WebElement dataTableIdRadio;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement noRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='noSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement noRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-outer-circle'][1]")
	public WebElement yesRadioOutCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yesSelected-input']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement yesRadioInCircle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='reasonExplanation']")
	public WebElement reasonExplanation;
	
	@FindBy(how = How.XPATH, using = "//*[contains(@id, 'dataTableId_select_0')]//preceding::*[@class='mat-checkbox-inner-container'][1]")
	public WebElement dataTableId_select_0_checkbox;
	
	@FindBy(how = How.XPATH, using = "//*[.='If Other, explain in detail']//following::*[@id='otherreason']")
	public WebElement otherreason_MaintainCollectionHold;

	@FindBy(how = How.XPATH, using = "//*[.='Comments']//following::*[@id='comment']")
	public WebElement comments_CancelPaymentPlan;

	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_radio_0_1_radio_button']//following::*[@class='mat-radio-input'][1]")
	public WebElement select_Review_UpdateBankruptcyCaseActivity;
	
	@FindBy(how = How.XPATH, using = "//*[text()='Treasury Offset Program'][1]//following::*[text()='Bankruptcy'][1]")
	public WebElement clickMenu_Bankruptcy;

	
	@FindBy(how = How.XPATH, using = "//span[.=' Order to Show Cause Filed'][1]")
	public WebElement updateReferralReasonOrderShowValue;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Voided'][1]")
	public WebElement updateReferralStatusVoidedValue;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Employer Appeal'][1]")
	public WebElement updateReferralReasonEmployerAppealValue;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Pending Income Execution'][1]")
	public WebElement updateReferralReasonPendingIncomeValue;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Certified'][1]")
	public WebElement updateReferralStatusCertifiedValue;
	
	@FindBy(how = How.XPATH, using = "//span[.=' Hearing Requested'][1]")
	public WebElement updateReferralReasonHearingRequestedValue;
	 
	@FindBy(how = How.XPATH, using = "//span[.=' Generated'][1]")
	public WebElement updateReferralStatusGeneratedValue;
	
	

	
	
	
	
	
}