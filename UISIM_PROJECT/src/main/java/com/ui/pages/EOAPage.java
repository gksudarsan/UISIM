package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class EOAPage extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public EOAPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='I am a Third Party Representative who needs to create an online account to represent my clients in Unemployment Insurance matters.']")
	public WebElement tprRepresentativeRadio;
	
	
	
	
	

}
