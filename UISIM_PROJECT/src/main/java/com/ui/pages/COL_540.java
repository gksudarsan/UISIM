package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class COL_540 extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public COL_540(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[@id='ContributionCollectionBankruptcy' and text()='Bankruptcy']")
	public WebElement contributionCollectionBankruptcybtn;
	


}
