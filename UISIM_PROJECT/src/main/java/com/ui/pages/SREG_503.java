package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class SREG_503 {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_503(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-error[text()='Required']")
	public WebElement requiredText;
	
	
}
