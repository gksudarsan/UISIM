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

public class COL_521 extends TestBase {

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public COL_521(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//textarea[@id='contactComments']")
	public WebElement contactCommentsField;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='comments']")
	public WebElement commentsField;
	
	@FindBy(how = How.XPATH, using = "//mat-select[contains(@id,'status')]//div[contains(@id,'mat-select-value')]")
	public List<WebElement> statusdropdownList;
	 
	@FindBy(how = How.XPATH, using = "//mat-option//span[text()=' Blank']")
	public WebElement blankOption;
	
	@FindBy(how = How.XPATH, using = "//label[text()=' Escalate to District Office ']/following::mat-radio-group//mat-radio-button//span[text()='Yes']")
	public WebElement escalatetoDistrictOfficeYes;
	
	@FindBy(how = How.XPATH, using = "//label[text()=' Escalate to District Office ']/following::mat-radio-group//mat-radio-button//span[text()='No']")
	public WebElement escalatetoDistrictOfficeNo;
	
	
	

	public void selectRadioQuestionsInAction(String xpathQuestions, String xpathParameter) {
		
		By element = By.xpath(
				"(//mat-label[text()='" + xpathQuestions + "']/following::mat-cell//mat-radio-button[@value='" + xpathParameter+ "']//label//span)[1]");
		final WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(element));
//			highLightWebElement(driver, ele);
			stepDef.safeJavaScriptClick(ele);
		} catch (final Exception e) {
		}
	}

}
