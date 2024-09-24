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
import com.wecare.generic.objectRepository.adminRepo.AdminEditDoctorPage;
import com.wecare.generic.objectRepository.adminRepo.AdminLoginPage;
import com.wecare.generic.objectRepository.adminRepo.ManageDoctorsPage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.JavaUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class ManageDoctors_Test extends BaseClass {

	@Test(groups = {"smokeTest"})
	public void manageDoctors() throws InterruptedException, EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ExcelUtility eUtil = new ExcelUtility();
		JavaUtility jUtil = new JavaUtility();
		ActionsUtility aUtil = new ActionsUtility();

		// 1. HMS Admin Login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getAdminLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("AdminCredentials", 0, 0));

		// 2. Login as Admin
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
		
		//6. Update Doctor details in Manage Doctor page
		driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+eUtil.getDataFromExcel("AddDoctorInfo", 1, 0)+"')]/following-sibling::td/div/a[@tooltip='Edit']")).click();
		AdminEditDoctorPage aedp = new AdminEditDoctorPage(driver);
		aedp.getContactNoTextfield().clear();
		aedp.getContactNoTextfield().sendKeys(eUtil.getDataFromExcel("AddDoctorInfo", 9, 0));
		aedp.getFeesTextfield().clear();
		aedp.getFeesTextfield().sendKeys(eUtil.getDataFromExcel("AddDoctorInfo", 10, 0));
		aedp.getSubmitButton().click();
		
		//7. Verify the updated info
		Assert.assertTrue(aedp.getUpdateSuccessMessage().isDisplayed()) ;
		
		//8. Remove the doctor in Manage Doctor page
		adp.getDoctorModule().click();
		adp.getManageDoctorsModule().click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tbody/tr/td[contains(text(),'"+eUtil.getDataFromExcel("AddDoctorInfo", 1, 0)+"')]/following-sibling::td/div/a[@tooltip='Remove']")).click();
		wUtil.switchToAlertandAccept(driver);
		ManageDoctorsPage mdp = new ManageDoctorsPage(driver);
		Assert.assertTrue(mdp.getDeleteSuccessMessage().isDisplayed());
		
		//9. Logout from Admin
		aUtil.clickOnTarget(driver, adp.getProfileDropdown());
		aUtil.clickOnTarget(driver, adp.getLogoutButton());	
		
		
		
	}

}
