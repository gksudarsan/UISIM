package com.ui.pages;

import java.time.Duration;

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

		By element = By.xpath("//mat-checkbox//label[contains(.,'" + xpathParameter + "')]//preceding::*[@class='mdc-checkbox__native-control'][1]");
		//final WebDriverWait wait = new WebDriverWait(driver, 10);
		/*WebDriverWait not supported in latest version of selenium updated as below*/
		
		final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			//stepDef.safeJavaScriptClick(ele);
			ele.click();
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
