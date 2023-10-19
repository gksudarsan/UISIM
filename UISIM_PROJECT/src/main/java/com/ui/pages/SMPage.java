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
	
	@FindBy(how = How.XPATH, using = "//*[.='Write Your Message Here.']//following::*[@class='angular-editor-textarea']")
    public WebElement write_SecureMessage;
	
	@FindBy(how = How.XPATH, using = "//*[@id='EMAIL-input']")
    public WebElement emailCheckBox;
	
	
}
