package com.wecare.generic.BaseTest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.wecare.generic.webdriverUtility.ActionsUtility;
import com.wecare.generic.webdriverUtility.WebDriverUtility;
import com.wecare.generic.databaseUtility.DatabaseUtility;
import com.wecare.generic.fileUtility.FileUtility;
import com.wecare.generic.objectRepository.adminRepo.AdminDashboardPage;
import com.wecare.generic.objectRepository.adminRepo.AdminLoginPage;

@Listeners(com.wecare.generic.listenerUtility.ListenerUtility.class)
public class BaseClass {
	
	public DatabaseUtility databaseUtil = new DatabaseUtility();
	public FileUtility fUtil = new FileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws SQLException {
		databaseUtil.getDBConnection();
	}
	
//	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException {																//String bname
		String BROWSER = System.getProperty("browser",fUtil.getDataFromProperties("browser"));
		String URL = System.getProperty("url",fUtil.getDataFromProperties("url"));
//		BROWSER = bname;
		
		if (BROWSER.contains("firefox")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);
		wUtil.maximizeBrowser(driver);
		wUtil.waitForPageToLoad(driver);
		sdriver = driver;
	}
	
//	@BeforeMethod(alwaysRun = true)
//	public void beforeMethod() throws IOException {
//		String USERNAME = System.getProperty("username",fUtil.getDataFromProperties("username"));
//		String PASSWORD = System.getProperty("password",fUtil.getDataFromProperties("password"));
//		AdminLoginPage lp = new AdminLoginPage(driver);
//		lp.loginApplication(USERNAME, PASSWORD);
//	}
//	
//	@AfterMethod(alwaysRun = true)
//	public void afterMethod() {
//		AdminDashboardPage dp = new AdminDashboardPage(driver);
//		ActionsUtility aUtil = new ActionsUtility();
//		aUtil.clickOnTarget(driver, dp.getProfileDropdown());
//		aUtil.clickOnTarget(driver, dp.getLogoutButton());
//	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws SQLException {
		databaseUtil.closeDBConnection();
	}

}
