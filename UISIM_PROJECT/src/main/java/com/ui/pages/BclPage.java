package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BclPage {

	
	public WebDriver driver;

	public BclPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='mat-input-7']")
	public WebElement followUpNote;
	
	@FindBy(how = How.XPATH, using = "//input[@id='mat-input-6']")
	public WebElement settlementAmount;

	@FindBy(how = How.XPATH, using = "//input[@id='name']")
	public WebElement nameFeild;
	
	@FindBy(how = How.XPATH, using = "//*[contains(.,'Select')][@class='mat-radio-label']//preceding::span[1][@class='mat-radio-outer-circle']")
	public WebElement radioButton;
	
	@FindBy(how = How.XPATH, using = "//input[@id='dataTableId_addressLine1_0']")
	public WebElement AddressLine1;
	
	@FindBy(how = How.XPATH, using = "//input[@id='dataTableId_city_0']")
	public WebElement city;
	
	@FindBy(how = How.XPATH, using = "//input[@id='dataTableId_zip_0']")
	public WebElement zipCode;
	
	@FindBy(how = How.XPATH, using = "//input[@id='noticeDateID']")
	public WebElement noticeDate; 
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='otherreason']")
	public WebElement otherReason;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='comments']")
	public WebElement comments;
}
