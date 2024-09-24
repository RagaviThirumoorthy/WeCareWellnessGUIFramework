package com.wecare.DoctorTest;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.objectRepository.doctorRepo.AppointmentHistorypage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorDashboardPage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorLoginPage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.objectRepository.patientRepo.PatientDashboardPage;
import com.wecare.generic.objectRepository.patientRepo.PatientLoginPage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class CancelAppointment_Test extends BaseClass {
	
	@Test(groups = {"integrationTest"})
	public void cancelAppointment() throws InterruptedException, EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ActionsUtility aUtil = new ActionsUtility();
		ExcelUtility eUtil = new ExcelUtility();
		String appTime = "2024-10-22 / 5:30 PM";
		
		//1. HMS Doctor login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getDoctorLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("DoctorCredentials", 0, 0));
		
		//2. Login as Doctor
		DoctorLoginPage dlp = new DoctorLoginPage(driver);
		dlp.loginApplication(eUtil.getDataFromExcel("DoctorCredentials", 1, 0), eUtil.getDataFromExcel("DoctorCredentials", 2, 0));
		
		//3. Navigate to Appointment History page and cancel it
		DoctorDashboardPage ddp = new DoctorDashboardPage(driver);
		ddp.getAppointmentHistoryModule().click();
		AppointmentHistorypage ahp = new AppointmentHistorypage(driver);
		driver.findElement(By.xpath("//table[@id='sample-table-1']//tr/td[contains(text(),'"+appTime+"')]/../td[8]")).click();
		Thread.sleep(2000);
		wUtil.switchToAlertandAccept(driver);
		
		//4. Verify whether it is cancelled or not
		String currentStatus = driver.findElement(By.xpath("//table[@id='sample-table-1']//tr/td[contains(text(),'"+appTime+"')]/../td[7]")).getText();
		String action = driver.findElement(By.xpath("//table[@id='sample-table-1']//tr/td[contains(text(),'"+appTime+"')]/../td[8]")).getText();
		Assert.assertTrue(currentStatus.contains("Cancel by you"));
		Assert.assertTrue(action.contains("Canceled"));
		
		//5. Logout from Doctor
		aUtil.clickOnTarget(driver, ddp.getProfileDropdown());
		aUtil.clickOnTarget(driver, ddp.getLogoutButton());		
		
		//6. Login as Patient
		hms.getLoginMenu().click();
		hms.getPatientLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("PatientCredentials", 0, 0));
		PatientLoginPage plp = new PatientLoginPage(driver);
		plp.loginApplication(eUtil.getDataFromExcel("PatientCredentials", 1, 0), eUtil.getDataFromExcel("PatientCredentials", 2, 0));
		
		//7. Navigate to Appointment history and verify whether it is cancelled or not
		PatientDashboardPage pdp = new PatientDashboardPage(driver);
		pdp.getAppointmentHistoryModule().click();
		String currentStatus1 = driver.findElement(By.xpath("//table[@id='sample-table-1']//tr/td[contains(text(),'"+appTime+"')]/../td[7]")).getText();
		String action1 = driver.findElement(By.xpath("//table[@id='sample-table-1']//tr/td[contains(text(),'"+appTime+"')]/../td[8]")).getText();
		Assert.assertTrue(currentStatus1.contains("Cancel by Doctor"));
		Assert.assertTrue(action1.contains("Canceled"));
		
		//8. Logout from Patient
		aUtil.clickOnTarget(driver, pdp.getProfileDropdown());
		aUtil.clickOnTarget(driver, pdp.getLogoutButton());	
	}

}
