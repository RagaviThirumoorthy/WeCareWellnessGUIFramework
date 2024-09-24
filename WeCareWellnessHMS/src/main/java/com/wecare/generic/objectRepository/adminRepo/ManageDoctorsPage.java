package com.wecare.generic.objectRepository.adminRepo;

/**
 * @author RAGAVI
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.wecare.generic.BaseTest.BaseClass;
import com.wecare.generic.fileUtility.ExcelUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;

public class ManageDoctorsPage{
	
	ExcelUtility eUtil = new ExcelUtility();
	
	public ManageDoctorsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
//	@FindBy(xpath = "//tbody/tr/td[contains(text(),'"+eUtil.getDataFromExcel("DoctorInfo", 1, 0)+"')]/following-sibling::td/div/a[@tooltip='Edit']")
//	private WebElement editIcon;
	
	@FindBy(xpath = "//p[contains(text(),'data deleted !!')]")
	private WebElement deleteSuccessMessage;
	

	public WebElement getDeleteSuccessMessage() {
		return deleteSuccessMessage;
	}
	

	
}
