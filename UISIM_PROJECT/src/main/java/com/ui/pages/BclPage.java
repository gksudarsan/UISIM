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
	 
	
	
	
	
	
	
	
	
	
}
