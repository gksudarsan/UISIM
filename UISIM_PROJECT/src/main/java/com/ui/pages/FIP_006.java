package com.ui.pages;

import java.util.List;

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

public class FIP_006 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public FIP_006(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void selectRadioWithValue(String xpathParameter) {
		By element = By.xpath("//mat-radio-button//label//span[contains(text(),'" + xpathParameter + "')]");

		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			stepDef.safeJavaScriptClick(ele);
		} catch (final Exception e) {
		}
}
	
	
	
	public void selectDropdownWithTagP(String xpathParameter, String value) {
		By element = By.xpath("//p[contains(.,'" + xpathParameter + "')]//following::mat-select[1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
			stepDef.safeJavaScriptClick(ele);
			driver.findElement(By.xpath("//*[contains(.,'" + value + "')][@class='mat-option-text']")).click();
		} catch (final Exception e) {
		}

	}
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Resolution Details']")
	public WebElement resolutionDetailsCommentField;
	
	
	@FindBy(how = How.XPATH, using = "(//u[text()='Review Benefit Claim Penalty Protest'])[1]")
	public WebElement reviewBenefitClaimPenaltyProtestWorkItem;


}
