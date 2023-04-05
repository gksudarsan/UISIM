package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCorporatePage {
	public WebDriver driver;

	public AddCorporatePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='successLinkId']")
	public WebElement successLink;
	
	@FindBy(how = How.XPATH, using = "//*[.='Business as Member/Managing Member ']//following::input[1]")
	public WebElement businessEntityFilter;

}
