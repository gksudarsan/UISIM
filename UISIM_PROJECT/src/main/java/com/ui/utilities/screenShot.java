package com.ui.utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class screenShot

{
	
	@Test 
	public WebDriver getScreenShot(WebDriver driver, String snapshotPath,String className)
	{
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Date date =new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
		String time =sdf.format(date);
		try
		{
			FileUtils.copyFile(srcFile, new File(snapshotPath+"//"+className+"_"+time+".png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}		
		return driver;				
	}

	public void getScreenshotFromPDF(WebDriver driver, String snapshotPath, String className, String path) throws MalformedURLException, IOException
	{
		/** @author mjape */
		try
		{
			if((new File(path)).exists())
				System.out.println("File found...");
			else
				System.out.println("File not found...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("File path :"+path);
		driver.get("file:///"+path);
		String url = driver.getCurrentUrl();
		BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
		PDDocument d = PDDocument.load(in);
		PDFRenderer pr = new PDFRenderer(d);
		for(int i = 0 ; i < d.getNumberOfPages(); i++)
		{
			BufferedImage b = pr.renderImageWithDPI(i, 300, ImageType.RGB);
			File o = new File(snapshotPath+"//"+className+"_"+new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss").format(new Date())+".png");
			ImageIO.write(b, "jpg", o);
		}
		in.close();
		try
		{
			if((new File(path)).delete())
				System.out.println(new File(path).getName() + " is deleted!");
			else
				System.out.println(new File(path).getName() + " is not deleted!");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
/*
        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

            //Move image file to new destination

                File DestFile=new File(fileWithPath);

                //Copy file at destination

                FileUtils.copyFile(SrcFile, DestFile);
                return DestFile.getAbsolutePath();
                */
                File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
        	    String encodedBase64 = null;
        	    FileInputStream fileInputStreamReader = null;
        	    try {
        	        fileInputStreamReader = new FileInputStream(scrFile);
        	        byte[] bytes = new byte[(int)scrFile.length()];
        	        fileInputStreamReader.read(bytes);
        	        encodedBase64 = new String(Base64.encodeBase64(bytes));
        	    } catch (FileNotFoundException e) {
        	        e.printStackTrace();
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    }
        	    return "data:image/png;base64,"+encodedBase64;
        	

    }


}