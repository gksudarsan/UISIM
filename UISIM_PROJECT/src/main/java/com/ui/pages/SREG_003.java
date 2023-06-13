package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_003 extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_003(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'Other commonly known name of entity')]//following::input[1]")
	public WebElement othercommonNameField;

	@FindBy(how = How.XPATH, using = "//mat-label[contains(.,'If Yes, enter Legal Name of Entity')]//following::input[1]")
	public WebElement legalEntitIfField;
	
	//SREG-101
	@FindBy(how = How.XPATH, using = "(//mat-row//mat-cell[@data-label=\"Legal Name of Business\"]//a)[1]")
	public WebElement legalNameofBusinessFirst;
	
	@FindBy(how = How.XPATH, using = "(//mat-row//mat-cell[@data-label=\"Legal Name of Business\"]//a//u[text()='COLORESEENCE122'])[1]")
	public WebElement lnobWithTextFirst;
	
	
}
