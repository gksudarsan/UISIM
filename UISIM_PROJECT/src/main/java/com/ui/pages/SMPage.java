package com.ui.pages;

 

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.How;

import org.openqa.selenium.support.PageFactory;

 

public class SMPage {

 

	public WebDriver driver;

 

	public SMPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}


	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;


	@FindBy(how = How.XPATH, using = "//*[.='Write Your Message Here.']//following::*[@class='angular-editor-textarea']")
    public WebElement write_SecureMessage;


	@FindBy(how = How.XPATH, using = "//*[@id='EMAIL-input']")
    public WebElement emailCheckBox;

	@FindBy(how = How.XPATH, using = "//*[@mattooltip='My Q']")
    public WebElement queue;
	
	@FindBy(how = How.XPATH, using = "//*[@id='recipientTypeId_1-input']")
    public WebElement selectEmployer;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ViewMessageViewMessage']")
    public WebElement clickViewMessage;

	//@FindBy(how = How.XPATH, using = "//*[.='Message']//following::*[@class='angular-editor-textarea'][1]")
    //public WebElement Message;
	//
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_select_0_1_radio_button-input'][1]")
	public WebElement datatabRadio1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_select_0_1_radio_button-input']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement dataTableIdRadio1;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_select_1_1_radio_button-input']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement dataTableIdRadio2;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='dataTableId_select_5_1_radio_button-input']//following::*[@class='mdc-radio__native-control'][1]")
	public WebElement dataTableIdRadio4;
	
	
	@FindBy(how = How.XPATH, using = "//mat-select//div[@id='mat-select-value-3']")
    public WebElement fraudValueDropdown;

    @FindBy(how = How.XPATH, using = "//span[text()=' Fraud ']")
    public WebElement fraudValue;

    @FindBy(how = How.XPATH, using = "//input[@id='recipientTypeId_2-input']")
    public WebElement appealCaseRadioButton;
    
    @FindBy(how = How.XPATH, using = "//input[@id='recipientTypeId_1-input']")
    public WebElement appealCaseRadioButton1;

    @FindBy(how = How.XPATH, using = "//input[@id='sendNotificationVia_1-input']")
    public WebElement notificationViaEmail;
    
    @FindBy(how = How.XPATH, using = "//input[@id='EMAIL-input']")
    public WebElement checkboxEmail;
    
    @FindBy(how = How.XPATH, using = "//div[@class='angular-editor-textarea']")
    public WebElement enterMessage;



}

