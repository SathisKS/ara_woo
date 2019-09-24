/* *************************************************************************
 * Project Name: Seamless Payment Form
 * Description:  This file contain the configuration methods
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ***************************************************************************/

package com.nn.TestConfiguration;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestData.TestInputData;
import com.nn.TestReport.ExtendReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.text.ParseException;

	public class Utility extends ExtendReport {
		public static String  finalscreenshotpath ;
		public static WebDriver driver;
		public static Logger log = Logger.getLogger("devpinoyLogger");
		public static String browser;

	/*
	 * *************************** 
	 * Initialization the driver
	 *****************************/
	public static void initConfiguration(String... args) {
		if (Constant.browser.equals("firefox_linux")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/Executables/geckodriver_linux/geckodriver");
			driver = new FirefoxDriver();
			log.debug("Lauching Firefox");
		} else if (Constant.browser.equals("chrome_linux")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/main/resources/Executables/chromedriver_linux/chromedriver");
			driver = new ChromeDriver();
			log.debug("Lauching Chrome");
		} else if (Constant.browser.equals("firefox_windows")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")
					+ "\\src\\main\\resources\\Executables\\geckodriver_windows\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.debug("Lauching Firefox");
		} else if (Constant.browser.equals("chrome_windows")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
					+ "\\src\\main\\resources\\Executables\\chromedriver_windows\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Lauching Chrome");
		}
		if (args.length != 0) {
			String arg = args[0];
			driver.get(arg);
		} else {
			driver.get(Constant.shopbackendurl);
		}
		driver.manage().window().maximize();
	}

	/*
	 * *****************************************************************************************************
	 * Screenshot Method Pass the argument to takeScreenshot() method For example: "Test"
	 *******************************************************************************************************/
	public static String takeScreenshot(String fileName) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss a");
		Date date = new Date();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String folderPath = System.getProperty("user.dir") + "/src/test/resources/Screenshots/" + fileName + "/";
		File folder = new File(folderPath);	
		if (!folder.exists()) {
			folder.mkdir();
			finalscreenshotpath = folderPath + fileName + " - " + dateFormat.format(date) + ".png";
			File finalDestination = new File(finalscreenshotpath);
			FileUtils.copyFile(scrFile,finalDestination);
		} else {
			finalscreenshotpath = folderPath + fileName + " - " + dateFormat.format(date) + ".png";
			File finalDestination = new File(finalscreenshotpath);
			FileUtils.copyFile(scrFile,finalDestination);
		}
		return finalscreenshotpath;
	}

	/*
	 * ****************** 
	 * Close browser
	 ********************/
	public static void closeBrowser() throws InterruptedException {
		driver.close();
	}

	/* ********************************************* 
	 * This method is used for date validation
	 ***********************************************/
	public static boolean isThisDOBValid(String dateToValidate, String dateFromat) {
		if (dateToValidate == null) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(dateToValidate); // if not valid, it will throw ParseException
			System.out.println(date);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/* **************************************************************
	* This method is used for wooCommerce Front End Login
	******************************************************************/
	static TestInputData ExcelData_shoplogin = new TestInputData();
	public static void wooCommerceFrontEndLogin() throws Exception {
	try {
	ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
	Map<String, String> login_data = new HashMap<String, String>();
	login_data = ExcelData_shoplogin.ExcelReader_FrontEnd();
	element.MyAccount_Menu.click();
	Thread.sleep(2000);
	try {
	if (element.FE_Login_Button.isDisplayed() == true) {
	element.FE_Login_Username.sendKeys(login_data.get("ShopFrontendLogin_username"));
	element.FE_Login_Password.sendKeys(login_data.get("ShopFrontendLogin_password"));
	element.FE_Login_Button.click();
	}
	} catch (Exception e) {
	System.out.println("User have already logged in into the shop system");
	}
	} catch (Exception e) {
	System.out.println("ERROR: Unexpected error from 'wooCommerceFrontEndLogin' method");
	}
	}
	
	/* ******************************************************************
	 * This method is used for wooCommerce checkout process
	 *********************************************************************/
	public static void wooCommerceCheckOutProcess() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			element.Shop_Menu.click();
				if (element.Shop_Menu_Product1.isDisplayed() == true) {
					element.Shop_Menu_Product1.click();
					Thread.sleep(4000);
					element.ViewCart.click();
					element.ViewCart_Proceed_To_Checkout.click();
				} else {
					element.Shop_Menu_Product2.click();
					Thread.sleep(4000);
					element.ViewCart.click();
					element.ViewCart_Proceed_To_Checkout.click();
				}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'wooCommerceCheckOutProcess' method");
		}
	}
	
	/* **********************************************************
	 * This method is used for wooCommerce cart clear
	 ************************************************************
	public static void wooCommerceCartClear() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			element.Cart_Menu.click();
			if (element.Cart_Clear.isDisplayed() == true) {
				System.out.println("cart menu displayed");
				element.Cart_Clear.click();
			} else {
				System.out.println("Sorry for the inconvenience product is not available in your shopping cart");
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'wooCommerceCartClear' method");
		}
	}
	*/
	/* ***********************************************************************************
	 * This method is used for wooCommerce cart clear verification after placing the order
	 *************************************************************************************/
	public static void CartVerficationAfterTheOrder() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			element.Cart_Menu.click();
			try {
				if (element.Cart_Clear.isDisplayed() == true) {
					System.out.println("TC FAILED: In your shopping cart product is available");
				}
			} catch (Exception e) {
				System.out.println("TC PASSED: Your shopping cart is empty");
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'CartVerficationAfterTheOrder' method");
		}
	}
	
	/* *********************************************************
	 * This method is used for wooCommerce cart clear
	 *************************************************************/
	public static void wooCommerceCartClear() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			element.Cart_Menu.click();
			if (element.Cart_Clear.isDisplayed() == true) {
				System.out.println("cart menu displayed");
				element.Cart_Clear.click();
			} else {
				System.out.println("Sorry for the inconvenience product is not available in your shopping cart");
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'wooCommerceCartClear' method");
		}
	}
	/* ************************************************************** 
	 * This method is used for wooCommerce Back End Login
	 ******************************************************************/
	public static void wooCommerceBackEndLogin() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			Map<String, String> login_data = new HashMap<String, String>();
			login_data = ExcelData_shoplogin.ExcelReader_BackEnd();
			try {
				if (element.Login_Button.isDisplayed() == true) {
					element.Username.sendKeys(login_data.get("ShopBackendLogin_username"));
					element.Password.sendKeys(login_data.get("ShopBackendLogin_password"));
					element.Login_Button.click();
				}
			} catch (Exception e) {
				System.out.println("User have already logged in into the shop system");
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'wooCommerceBackEndLogin' method");
		}
	}
	
	/* ****************************************************************
	* This method is used to read transaction details from card portal 
	*******************************************************************/

	public static void getTransactionDetailsFromCardPortal() {
	ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
	test.log(Status.INFO, "Payment details:\n");
	test.log(Status.INFO, "Payment name:\t" + element.Cardportal_Payment_Name.getText());
	test.log(Status.INFO, "Amount:\t" + element.GetAmountFromCardPortal.getText());
	test.log(Status.INFO, "Customer Email id:\t" + element.Cardportal_mail.getText());
	test.log(Status.INFO, "Booking date:\t" + element.Booking_Date.getText());
	test.log(Status.INFO, "Booking time:\t" + element.Booking_Time.getText());
	test.log(Status.INFO, "Status code:\t" + element.Status_Code.getText());
	test.log(Status.INFO, "Status description:\t" + element.Status_desc.getText());
	test.log(Status.INFO, "Projct URL:\t" + element.Project_url.getText());
	}
	
	/* ***************************************************************** 
	 * This method is used for wooCommerce Back End Logout
	 *********************************************************************/
	public static void wooCommerceBackEndLogOut() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			Actions actions = new Actions(driver);
			actions.moveToElement(element.Woocommerce_Shop_Admin_Image).perform();
			element.Woocommerce_LogOut.click();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'wooCommerceBackEndLogOut' method");
		}
	}
	
	/* ***************************************************************** 
	 * This method is used for wooCommerce Front End Logout
	 *********************************************************************/
	public static void wooCommerceFrontEndLogOut() throws Exception {
		try {
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			element.MyAccount_Menu.click();
			element.Shop_Logout.click();	
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'wooCommerceFrontEndLogOut' method");
		}
	}
}