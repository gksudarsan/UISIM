package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class B_BAD_Page {

	public WebDriver driver;

	public B_BAD_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[@id='dataTableId_radio_0_1_radio_button-input']")
	public WebElement select_Claimant;
	
	@FindBy(how = How.XPATH, using = "//*[@id='residentialAddrIndId_DIFF-input']")
	public WebElement differentFromMailing;
	
	@FindBy(how = How.XPATH, using = "//*[@id='residentialAddrIndId_PHYS-input']")
	public WebElement sameAsMailingAdd;
	
	@FindBy(how = How.XPATH, using = "//*[@id='phoneYesNoId_Yes-input']")
	public WebElement selectYesForCellPhone;
	
	@FindBy(how = How.XPATH, using = "//*[@id='altPhoneYesNoId_Yes-input']")
	public WebElement selectYesForOtherPhone;
	
	@FindBy(how = How.XPATH, using = "//*[@id='interpreterId_Yes-input']")
	public WebElement selectYesForInterpreter;

}
