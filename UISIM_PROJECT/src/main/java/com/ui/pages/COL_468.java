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

public class COL_468 extends TestBase{
	
commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public COL_468(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//span[@id='EmployerCollectionBankruptcy' and text()='Bankruptcy']")
	public WebElement employerbankruptcynavBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id ='ContributionCollectionMaintainCollectionHold' and text()='Maintain Collection Hold']")
	public WebElement maintainCollectionHoldBtn;
	
	@FindBy(how = How.XPATH, using = "//a[text()='https://www.nyeb.uscourts.gov']")
	public WebElement easternDistrictBankruptcyCourtLink;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ConfidentialSourceEmployerReferralRelease/Removal']")
	public WebElement ConfidentialSourceEmployerReferralReleaseLink;
	
	public void errorLabelInLi(String xpathParameter) {
		WebElement ele = driver.findElement(By.xpath("//ul//li[contains(text(),'" + xpathParameter + "')]"));
		Assert.assertTrue(ele.isDisplayed());
	}
	
}
