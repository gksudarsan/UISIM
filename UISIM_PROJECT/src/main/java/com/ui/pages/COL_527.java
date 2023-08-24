package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class COL_527 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public COL_527(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[text()='Add Collection Hold']")
	public WebElement addCollectionHoldLink;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='otherreason']")
	public WebElement otherReasonField;
	
	
	
	
}
