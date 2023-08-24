package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class FIS_009 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public FIS_009(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectCheckboxfis009(String xpathParameter) {

		By element = By.xpath("//mat-checkbox//label//span[contains(.,'" + xpathParameter + "')]//preceding::*[@class='mat-checkbox-inner-container'][1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			stepDef.safeJavaScriptClick(ele);
		} catch (final Exception e) {
		}
	}
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-button//label//span[contains(text(),'Yes ')])[1]")
	public WebElement pfp4_IA13RadioBtn;
	
	@FindBy(how = How.XPATH, using = "(//mat-radio-button//label//span[contains(text(),'Yes ')])[2]")
	public WebElement pfp4_findingRequiredRadioBtn;
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Resolution Details']")
	public WebElement commentfieldResolution;
	
	
	
	

}
