package com.wecare.DoctorTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.objectRepository.adminRepo.AdminAddDoctorPage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorDashboardPage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorLoginPage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.objectRepository.patientRepo.PatientDashboardPage;
import com.wecare.generic.objectRepository.patientRepo.PatientLoginPage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;


public class UpdateProfile_Test extends BaseClass{
	
	@Test(groups = {"smokeTest"})
	public void UpdateProfile() throws InterruptedException, EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ActionsUtility aUtil = new ActionsUtility();
		ExcelUtility eUtil = new ExcelUtility();
		
		//1. HMS Doctor login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getDoctorLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("DoctorCredentials", 0, 0));
		
		//2. Login as Doctor
		DoctorLoginPage dlp = new DoctorLoginPage(driver);
		dlp.loginApplication(eUtil.getDataFromExcel("DoctorCredentials", 1, 0), eUtil.getDataFromExcel("DoctorCredentials", 2, 0));
		
		//3. Navigate to Profile page
		DoctorDashboardPage ddp = new DoctorDashboardPage(driver);
		ddp.getProfileDropdown().click();
		ddp.getMyProfileButton().click();
		
		//4. Update the details and submit
		AdminAddDoctorPage aadp = new AdminAddDoctorPage(driver);
		aadp.getClinicAddressTextarea().clear();
		aadp.getClinicAddressTextarea().sendKeys(eUtil.getDataFromExcel("UpdateDoctorInfo", 0, 0));
		Thread.sleep(1000);
		aadp.getFeesTextfield().clear();
		aadp.getFeesTextfield().sendKeys(eUtil.getDataFromExcel("UpdateDoctorInfo", 1, 0));
		Thread.sleep(1000);
		aadp.getContactNoTextfield().clear();
		aadp.getContactNoTextfield().sendKeys(eUtil.getDataFromExcel("UpdateDoctorInfo", 2, 0));
		Thread.sleep(1000);
		aadp.getSubmitButton().click();
		wUtil.switchToAlertandAccept(driver);;
		
		//5. Verify the updated info
		ddp.getDashboardModule().click();
		ddp.getProfileDropdown().click();
		ddp.getMyProfileButton().click();
		aUtil.scrollByAmount(driver, 0, 600);
		Thread.sleep(3000);
		Assert.assertEquals(aadp.getClinicAddressTextarea().getText(), eUtil.getDataFromExcel("UpdateDoctorInfo", 0, 0));
		Assert.assertEquals(aadp.getFeesTextfield().getAttribute("value"), eUtil.getDataFromExcel("UpdateDoctorInfo", 1, 0));
		Assert.assertEquals(aadp.getContactNoTextfield().getAttribute("value"), eUtil.getDataFromExcel("UpdateDoctorInfo", 2, 0));
		
		//6. Logout from Doctor
		aUtil.clickOnTarget(driver, ddp.getProfileDropdown());
		aUtil.clickOnTarget(driver, ddp.getLogoutButton());
		
	}

}
