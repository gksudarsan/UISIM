package com.ui.utilities;

import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.ui.base.TestBase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class FullPageScreenshot extends TestBase {

	@Test
	public void screenShot(String fileName, String status, String message) throws Exception {
		Date currentDate = new Date();
		String screenShotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		Screenshot fullPageScreenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(fullPageScreenshot.getImage(), "PNG", new File(".//screenshot"+screenShotFileName+".png"));
		File destinationFile = new File(".//screenshot"+screenShotFileName+".png");
		String absolutePath = destinationFile.getAbsolutePath();
		
		if (status.equalsIgnoreCase("Pass")) {
			test.log(Status.PASS, message);
		}else {
			test.log(Status.FAIL, message);
		}
		test.addScreenCaptureFromPath(absolutePath);
		
	}
}
