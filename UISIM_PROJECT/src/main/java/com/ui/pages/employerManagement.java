package com.ui.pages;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.ErrorHandler.UnknownServerException;

import com.ui.base.TestBase;
import com.ui.locators.claimsIntake;


public class employerManagement extends TestBase
{
	
	employerManagement em = new employerManagement();
	claimsIntake em1 = new claimsIntake();

	public void updateAddress(UnknownServerException driver, String snapshotPath, String SimpleClassname, String EAN) throws ClassNotFoundException, SQLException, InterruptedException, ParseException
	{		
		
		((WebDriver) driver).manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		System.out.println("updating address............");
		employerManagement ud =  new employerManagement();
        em.menuButton().click();
        em.AccountMaintenance().click();
        em.MaintainAddress().click();
        em.editButton().click();
        em.addressLine1().clear();
        em.addressLine1().sendKeys("abc");
        em.addressLine2().click();
        em.addressLine2().sendKeys("updated address2");
        em.city().sendKeys("");
        
  
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	
}