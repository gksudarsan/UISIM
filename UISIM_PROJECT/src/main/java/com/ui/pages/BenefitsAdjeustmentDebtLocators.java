package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

public class BenefitsAdjeustmentDebtLocators extends TestBase{

	public WebDriver driver;

	public BenefitsAdjeustmentDebtLocators(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[text()='Menu'][@class='menu-label']")
	public WebElement menu;

	@FindBy(how = How.XPATH, using = "//*[.=' OK '][@class='mat-button-wrapper']")
	public WebElement okPopUpButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='remarksId']")
	public WebElement remarksId;
	
}
