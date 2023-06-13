package com.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import stepDefinitions.commonStepDefinitions;

public class SREG_537 {

	public WebDriver driver;

	commonStepDefinitions stepDef = new commonStepDefinitions();

	public SREG_537(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//mat-label[text()='Unemployment Insurance Administrative Proceedings and Court Appeals Matters']/ancestor::mat-cell/preceding-sibling::mat-cell/mat-radio-group/mat-radio-button//span/span[@class='mat-radio-outer-circle']")
	public WebElement UnemploymentInsuranceAdministrativeProceedingsAndCourtAppealsMatters_RadioButton;
	
	@FindBy(how = How.XPATH, using = "//mat-label[text()='Unemployment Insurance Employer Services and Collection Matters']/ancestor::mat-cell/preceding-sibling::mat-cell/mat-radio-group/mat-radio-button//span/span[@class='mat-radio-outer-circle']")
	public WebElement UnemploymentInsuranceEmployerServicesAndCollectionMatters_RadioButton;
	

}
