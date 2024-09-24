package com.wecare.AdminTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.objectRepository.adminRepo.AdminAddDoctorPage;
import com.wecare.generic.objectRepository.adminRepo.AdminDashboardPage;
import com.wecare.generic.objectRepository.adminRepo.AdminLoginPage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorLoginPage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.JavaUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class AddDoctor_Test extends BaseClass{
	
	@Test(groups = {"systemTest"})
	public void addDoctor() throws EncryptedDocumentException, IOException, InterruptedException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ExcelUtility eUtil = new ExcelUtility();
		JavaUtility jUtil = new JavaUtility();
		
		//1. HMS Admin login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getAdminLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("AdminCredentials", 0, 0));
		
		//2. Login as Admin
		AdminLoginPage alp = new AdminLoginPage(driver);
		alp.loginApplication(eUtil.getDataFromExcel("AdminCredentials", 1, 0), eUtil.getDataFromExcel("AdminCredentials", 2, 0));
		
		 //3. Navigate to Add Doctor module
		AdminDashboardPage adp = new AdminDashboardPage(driver);
		adp.getDoctorModule().click();
		adp.getAddDoctorModule().click();
		
		//4. Enter doctor details and submit
		AdminAddDoctorPage aadp = new AdminAddDoctorPage(driver);
		
		String specialization = eUtil.getDataFromExcel("AddDoctorInfo", 0, 0);
		String doctorName = eUtil.getDataFromExcel("AddDoctorInfo", 1, 0);
		String clinicAddress = eUtil.getDataFromExcel("AddDoctorInfo", 2, 0);
		String fees = eUtil.getDataFromExcel("AddDoctorInfo", 3, 0);
		String contactNo = eUtil.getDataFromExcel("AddDoctorInfo", 4, 0);
		String email = jUtil.getRandomNumber()+eUtil.getDataFromExcel("AddDoctorInfo", 5, 0);
		String password = eUtil.getDataFromExcel("AddDoctorInfo", 6, 0);
		String confirmPassword = eUtil.getDataFromExcel("AddDoctorInfo", 7, 0);
		
		aadp.addDoctor(specialization,doctorName,clinicAddress,fees,contactNo,email,password,confirmPassword);
		wUtil.switchToAlertandAccept(driver);
		Thread.sleep(2000);
		
		//5. Navigate to Manage Doctors module and verify
		String docName = driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+eUtil.getDataFromExcel("AddDoctorInfo", 1, 0)+"')]")).getText();
		System.out.println(docName);
		Assert.assertEquals(eUtil.getDataFromExcel("AddDoctorInfo", 1, 0),docName);
		Thread.sleep(2000);
		
		//6. Logout from Admin
		ActionsUtility aUtil = new ActionsUtility();
		aUtil.clickOnTarget(driver, adp.getProfileDropdown());
		aUtil.clickOnTarget(driver, adp.getLogoutButton());
		Thread.sleep(2000);
		
		//7. Login as Doctor
		hms.getLoginMenu().click();
		hms.getDoctorLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("DoctorCredentials", 0, 0));
		Thread.sleep(2000);
		DoctorLoginPage dlp = new DoctorLoginPage(driver);
		dlp.loginApplication(eUtil.getDataFromExcel("AddDoctorInfo", 5, 0), eUtil.getDataFromExcel("AddDoctorInfo", 6, 0));
		
		//8. Logout from Doctor
		aUtil.clickOnTarget(driver, adp.getProfileDropdown());
		aUtil.clickOnTarget(driver, adp.getLogoutButton());
		
	}

}
