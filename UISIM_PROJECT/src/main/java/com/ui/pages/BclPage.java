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
	
	@FindBy(how = How.XPATH, using = "//*[@id='flagsOnAccount'][1]/mat-row[1]/mat-cell[1]//u[1]")
	public WebElement nprNoticeDate;
	
	@FindBy(how = How.XPATH, using = "//*[@id='flagsOnAccount'][1]/mat-row[1]/mat-cell[3]//u[1]")
	public WebElement viewGenerateLetter;
	
	@FindBy(how = How.XPATH, using = "//*[.=' Update All ']//following::*[@type='checkbox'][1]")
	public WebElement updateAllCheckBox;
	
	
	
	
	
}
