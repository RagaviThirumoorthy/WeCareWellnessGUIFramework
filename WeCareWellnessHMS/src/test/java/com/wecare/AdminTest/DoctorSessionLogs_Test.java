package com.wecare.AdminTest;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.objectRepository.adminRepo.AdminDashboardPage;
import com.wecare.generic.objectRepository.adminRepo.AdminLoginPage;
import com.wecare.generic.objectRepository.adminRepo.DoctorSessionLogsPage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorLoginPage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.JavaUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class DoctorSessionLogs_Test extends BaseClass{
	
	@Test(groups = {"integrationTest"})
	public void doctorSessionLogs() throws InterruptedException, EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ExcelUtility eUtil = new ExcelUtility();
		ActionsUtility aUtil = new ActionsUtility();
		JavaUtility jUtil = new JavaUtility();
		
		//1. HMS Doctor login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getDoctorLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("DoctorCredentials", 0, 0));
		
		//2. Login as Doctor
		DoctorLoginPage dlp = new DoctorLoginPage(driver);
		dlp.loginApplication(eUtil.getDataFromExcel("DoctorCredentials", 1, 0), eUtil.getDataFromExcel("DoctorCredentials", 2, 0));
		Thread.sleep(5000);
		
		//3. Logout from Doctor
		AdminDashboardPage adp = new AdminDashboardPage(driver);
		aUtil.clickOnTarget(driver, adp.getProfileDropdown());
		aUtil.clickOnTarget(driver, adp.getLogoutButton());
		
		//4. Login as Admin
		hms.getLoginMenu().click();
		hms.getAdminLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("AdminCredentials", 0, 0));
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.loginApplication(eUtil.getDataFromExcel("AdminCredentials", 1, 0), eUtil.getDataFromExcel("AdminCredentials", 2, 0));
		
		//5. Navigate to Session Logs
		adp.getDoctorSessionLogs().click();
		DoctorSessionLogsPage dslp = new DoctorSessionLogsPage(driver);
		List<WebElement> logs = dslp.getLastEntryLogs();
		for(WebElement log : logs) {
			System.out.println(log.getText());
		}
		
		//6. Verification of logs
		Assert.assertEquals(logs.get(2).getText(), eUtil.getDataFromExcel("DoctorCredentials", 1, 0));
//		Assert.assertEquals(logs.get(3).getText(), "152.58.250.160");
		Assert.assertEquals(logs.get(6).getText(), "Success");
//		Assert.assertEquals(logs.get(5).getText(), jUtil.getCurrentDateTime());
		
		
	}

}
