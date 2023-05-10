package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import stepDefinitions.commonStepDefinitions;

public class SREG_074 {

	public WebDriver driver;
	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_074(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//*[.='Individual as Partner ']//following::*[contains(@id ,'dataTable')][1]/mat-row[3]/mat-cell[10]//a[2]")
	public WebElement actionInactive;


	}

