package com.wecare.PatientTest;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.objectRepository.doctorRepo.AppointmentHistorypage;
import com.wecare.generic.objectRepository.hmsRepo.HMSHomePage;
import com.wecare.generic.objectRepository.patientRepo.BookAppointmentPage;
import com.wecare.generic.objectRepository.patientRepo.PatientDashboardPage;
import com.wecare.generic.objectRepository.patientRepo.PatientLoginPage;
import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class BookAppointment_Test extends BaseClass{
	
	@Test(groups = {"smokeTest"})
	public void bookAppointment() throws InterruptedException, EncryptedDocumentException, IOException {
		
		WebDriverUtility wUtil = new WebDriverUtility();
		ActionsUtility aUtil = new ActionsUtility();
		ExcelUtility eUtil = new ExcelUtility();
		
		//1. HMS Patient login
		HMSHomePage hms = new HMSHomePage(driver);
		hms.getLoginMenu().click();
		hms.getPatientLoginButton().click();
		wUtil.switchToChildWindowWithTitle(driver, eUtil.getDataFromExcel("PatientCredentials", 0, 0));
		
		//2. Login as Patient
		PatientLoginPage plp = new PatientLoginPage(driver);
		plp.loginApplication(eUtil.getDataFromExcel("PatientCredentials", 1, 0), eUtil.getDataFromExcel("PatientCredentials", 2, 0));
		
		//3. Navigate to Book Appointment module and Book it
		PatientDashboardPage pdp = new PatientDashboardPage(driver);
		pdp.getBookAppointmentModule().click();
		BookAppointmentPage bap = new BookAppointmentPage(driver);
		Select sel1 = new Select(bap.getSpecializationDropdown());
		sel1.selectByValue(eUtil.getDataFromExcel("BookAppointmentInfo", 0, 0));
		Select sel2 = new Select(bap.getDoctorDropdown());
		sel2.selectByVisibleText(eUtil.getDataFromExcel("BookAppointmentInfo", 1, 0));
		aUtil.scrollByAmount(driver, 0, 600);
		Assert.assertTrue(bap.getConsultancyFeesValue().isDisplayed());
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DAY_OF_MONTH, 26);
		bap.getDatePicker().click();
		bap.getDateValue().click();
		bap.getForm().click();
		Thread.sleep(4000);
		bap.getTimePicker().click();
		bap.getTimePickerHour().sendKeys(eUtil.getDataFromExcel("BookAppointmentInfo", 2, 0));
		Thread.sleep(1000);
		bap.getTimePickerMinute().sendKeys(eUtil.getDataFromExcel("BookAppointmentInfo", 3, 0));
		Thread.sleep(1000);
		bap.getTimePickerMeridian().sendKeys(eUtil.getDataFromExcel("BookAppointmentInfo", 4, 0),Keys.ESCAPE);
		Thread.sleep(1000);
		bap.getSubmitButton().click();
		wUtil.switchToAlertandAccept(driver);
		
		//4. Navigate to Appointment History module and check
		pdp.getAppointmentHistoryModule().click();
		AppointmentHistorypage ahp = new AppointmentHistorypage(driver);
		List<WebElement> bookings = ahp.getBookingHistory();
		for(WebElement booking : bookings) {
			System.out.println(booking.getText());
		}
				
	}

}
