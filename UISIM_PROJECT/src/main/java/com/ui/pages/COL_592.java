package com.ui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class COL_592 extends TestBase{

commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public COL_592(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//mat-select[contains(@id,'dataTableId_release')]")
	public List<WebElement> dropDownSelectionList;
	
	@FindBy(how = How.XPATH, using = "//mat-option//span[text()=' Yes']")
	public WebElement selectYesFromDropdown;
	
	@FindBy(how = How.XPATH, using = "//textarea[@id='comments']")
	public WebElement col595commentfield;

	@FindBy(how = How.XPATH, using = "//mat-select[contains(@id,'dataTableId_fixed')]")
	public List<WebElement> dropDowndataTableIdFixedListCOL591;
	
}