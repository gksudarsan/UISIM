package com.ui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.ui.base.TestBase;

import stepDefinitions.commonStepDefinitions;

public class SREG_043 extends TestBase{
	
public WebDriver driver;
	
	commonStepDefinitions stepDef = new commonStepDefinitions();
	
	public SREG_043(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Submitter Comments may be entered below.']")
	public WebElement submitterCommentsField;
	
	@FindBy(how = How.XPATH, using = "//textarea[contains(@id,'comment')]")
	public WebElement EEWI002CommentsField;
	
	@FindBy(how = How.XPATH, using = "//input[@aria-label='Comment']")
	public WebElement EEWI014CommentFeild;
	
	
	@FindBy(how = How.XPATH, using = "//textarea[@aria-label='Comment']")
	public WebElement EEWI012CommentFeild;
	
	@FindBy(how = How.XPATH, using = "//a//u[text()='Edit']")
	public List<WebElement> EEWI011EditButtonList;
	
	@FindBy(how = How.XPATH, using = "//button[@id='EEWI-011access.normalize']")
	public WebElement EEWI011NormalizeBtn;
	
	
	
	

}
