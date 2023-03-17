package com.ui.pages;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;
import org.testng.Assert;

import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;
import com.ui.locators.employerManagementLocators;

import stepDefinitions.commonStepDefinitions;


public class employerManagement extends TestBase
{


	employerManagementLocators em = new employerManagementLocators();
	commonStepDefinitions cf= new commonStepDefinitions();
	public void updateAddress(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.editButtonPriBusinessPhyLoc().click();
		cf.screenShot("Menu","Pass","");
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("");
		em.select().click();
		em.selectCorres().click();
		em.select().click();
		em.selectCorres().click();
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");		
		String msg =   em.successMsgSuc002().getText();
		System.out.println(msg);
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");



















	}
	public void updateAddress() throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();


		cf.screenShot("Menu","Pass","");
		em.editButtonBusinessPhyLoc().click();
		cf.screenShot("Menu","Pass","");
		em.addressLine1().clear();
		em.addressLine1().sendKeys("abc");
		em.addressLine2().click();
		em.addressLine2().sendKeys("updated address2");
		cf.screenShot("Menu","Pass","");
		em.city().sendKeys("");
		em.select().click();
		em.selectCorres().click();
		em.select().click();
		em.selectCorres().click();
		cf.screenShot("Menu","Pass","");
		em.submitButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
		String msg =   em.successMsgSuc002().getText();
		Assert.assertEquals(msg, "Employer Address and Contact Person Details are saved successfully.");


	}

	public void Inquery(String EAN) throws Exception
	{		

		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		System.out.println("updating address............");

		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
	em.businessActivity().click();
		// em.menuButton().click();
		// em.AccountMaintenance().click();
		// em.MaintainAddress().click();
		em.EAN().sendKeys(EAN);
		cf.screenShot("Menu","Pass","");
		em.continueButton().click();
		cf.screenShot("Menu","Pass","");
	em.businessActivity().click();sleep();
	cf.screenShot("Menu","Pass","");
	
	
	}
	
	
	


}