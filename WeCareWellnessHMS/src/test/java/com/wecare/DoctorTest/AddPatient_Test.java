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
import com.wecare.generic.objectRepository.doctorRepo.AddPatientpage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorDashboardPage;
import com.wecare.generic.objectRepository.doctorRepo.DoctorLoginPage;
import com.wecare.generic.objectRepository.doctorRepo.ManagePatientsPage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.webdriverUtility.JavaUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class AddPatient_Test extends BaseClass {

	@Test(groups = {"systemTest"})
	public void addPatient() throws EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ExcelUtility eUtil = new ExcelUtility();
		JavaUtility jUtil = new JavaUtility();

		// 1. HMS Doctor login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getDoctorLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("DoctorCredentials", 0, 0));

		// 2. Login as Doctor
		DoctorLoginPage dlp = new DoctorLoginPage(driver);
		dlp.loginApplication(eUtil.getDataFromExcel("DoctorCredentials", 1, 0), eUtil.getDataFromExcel("DoctorCredentials", 2, 0));
		
		//3. Navigate to Add patient module 
		DoctorDashboardPage ddp = new DoctorDashboardPage(driver);
		ddp.getPatientsModule().click();
		ddp.getAddpatientModule().click();
		
		//4. Enter patient details and add
		AddPatientpage adp  =new AddPatientpage(driver);
		adp.getPatientNameTextfield().sendKeys(eUtil.getDataFromExcel("AddPatientInfo", 0, 0));
		adp.getPatientContactNoTextfield().sendKeys(eUtil.getDataFromExcel("AddPatientInfo", 1, 0));
		adp.getPatientEmailTextfield().sendKeys(jUtil.getRandomNumber()+eUtil.getDataFromExcel("AddPatientInfo", 2, 0));
		adp.getGenderFemaleRadioButton().click();
		adp.getPatientAddressTextarea().sendKeys(eUtil.getDataFromExcel("AddPatientInfo", 4, 0));
		adp.getPatientAgeTextfield().sendKeys(eUtil.getDataFromExcel("AddPatientInfo", 5, 0));
		adp.getPatientMedicalHistoryTextfield().sendKeys(eUtil.getDataFromExcel("AddPatientInfo", 6, 0));
		adp.getAddPatientButton().click();  
		
		//5. Navigate to Manage Patient module
		ddp.getPatientsModule().click();
		ddp.getManagePatientModule().click();
		
		//6. Verify whether the patient is added or not
		ManagePatientsPage mpp = new ManagePatientsPage(driver);
		List<WebElement> details = mpp.getPatientHistory();
		for(WebElement detail : details) {
			System.out.println(detail.getText());
			if(detail.getText().contains(eUtil.getDataFromExcel("AddPatientInfo", 0, 0))) {
				Assert.assertTrue(detail.getText().contains(eUtil.getDataFromExcel("AddPatientInfo", 0, 0)));
			}else if(detail.getText().contains(eUtil.getDataFromExcel("AddPatientInfo", 1, 0))) {
				Assert.assertTrue(detail.getText().contains(eUtil.getDataFromExcel("AddPatientInfo", 1, 0)));
			}
		}
		
		//7. Logout from Doctor
		ddp.getProfileDropdown().click();
		ddp.getLogoutButton().click();
		
	}

}
