package com.wecare.PatientTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.objectRepository.patientRepo.PatientDashboardPage;
import com.wecare.generic.objectRepository.patientRepo.PatientLoginPage;
import com.wecare.generic.objectRepository.patientRepo.PatientRegistrationPage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.JavaUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class CreateAccount_Test extends BaseClass {
	
	@Test(groups = {"systemTest"})
	public void createAccount() throws InterruptedException, EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ActionsUtility aUtil = new ActionsUtility();
		ExcelUtility eUtil = new ExcelUtility();
		JavaUtility jUtil = new JavaUtility();
		
		//1. HMS Patient login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getPatientLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("PatientCredentials", 0, 0));
		
		//2. Register as Patient
		PatientLoginPage plp = new PatientLoginPage(driver);
		plp.getCreateAccountButton().click();
		Assert.assertEquals(driver.getTitle(), "User Registration");
		
		PatientRegistrationPage prp = new PatientRegistrationPage(driver);
		prp.getFullNameTextfield().sendKeys(eUtil.getDataFromExcel("PatientRegistrationInfo", 0, 0));
		prp.getAddressTextfield().sendKeys(eUtil.getDataFromExcel("PatientRegistrationInfo", 1, 0));
		prp.getCityTextfield().sendKeys(eUtil.getDataFromExcel("PatientRegistrationInfo", 2, 0));
		prp.getFemaleRadioIcon().click();
		prp.getEmailTextfield().sendKeys(jUtil.getRandomNumber()+eUtil.getDataFromExcel("PatientRegistrationInfo", 4, 0));
		prp.getPasswordTextfield().sendKeys(eUtil.getDataFromExcel("PatientRegistrationInfo", 5, 0));
		prp.getConfirmPasswordTextfield().sendKeys(eUtil.getDataFromExcel("PatientRegistrationInfo", 6, 0));
		aUtil.scrollByAmount(driver, 0, 500);
		prp.getSubmitButton().click();
		wUtil.switchToAlertandAccept(driver);
		Thread.sleep(2000);
		
		//3. Login as patient
		aUtil.scrollByAmount(driver, 0, 500);
		prp.getLoginButton().click();
		plp.loginApplication(eUtil.getDataFromExcel("PatientRegistrationInfo", 4, 0), eUtil.getDataFromExcel("PatientRegistrationInfo", 5, 0));
		
		//4. Verify Login
		Assert.assertTrue(driver.getTitle().contains("Dashboard"));
		Thread.sleep(2000);
		
		//5. Logout from patient acc
		PatientDashboardPage pdp = new PatientDashboardPage(driver);
		aUtil.clickOnTarget(driver, pdp.getProfileDropdown());
		aUtil.clickOnTarget(driver, pdp.getLogoutButton());
		
		
	}

}
