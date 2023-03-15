package com.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PEOPage {
	public WebDriver driver;

	public PEOPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[.='PEO Individual Registration']//preceding::span[@class='mat-radio-inner-circle'][1]")
	public WebElement individualPeo;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ProfessionalEmployerOrganization(PEO)'][1]")
	public WebElement menuPeo;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_address1']")
	public WebElement addressLine1;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_address2']")
	public WebElement addressLine2;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_city']")
	public WebElement addressCity;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_zip']")
	public WebElement addressZip;
	
	@FindBy(how = How.XPATH, using = "//*[.='Primary Physical Address']//following::*[@class='mat-radio-outer-circle'][2]")
	public WebElement uspsAddress;
	
	@FindBy(how = How.XPATH, using = "//*[.='Current Additional Address']//following::*[@class='mat-radio-outer-circle'][1]")
	public WebElement currentAdditionalAddress;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CIN-999access.continue']")
	public WebElement UspsContinueButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='address0_attention']")
	public WebElement attentionCareOf;
	
	@FindBy(how = How.XPATH, using = "//a[text()=' + ADD PEO MEMBER ']")
	public WebElement addPeoMember;
	
	
	
}
