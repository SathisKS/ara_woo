/* ******************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute Fraud modules for Direct Debit SEPA
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 ********************************************************************************/

package com.nn.SpecialPaymentFeatures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class DirectDebitSEPA extends TestInputData {
	TestInputData Data = new TestInputData();

	// Method used to execute 'PIN by callback' with empty phone number validation
	@Test
	public void phoneNumberEmptySEPA() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("PIN by callback: Phone number field empty validation for Direct Debit SEPA");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Sepa_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Enabling fraud module PIN by Callback
			Actions action = new Actions(driver);
			WebElement SelectFraudModule = element.Sepa_Enable_Fraud_Prevention_Selectbox;
			String FraudModuleValue = element.Sepa_Enable_Fraud_Prevention_Selectbox.getText();
			if (FraudModuleValue.equals("None")) {
				action.click(SelectFraudModule).sendKeys("PIN by callback", Keys.DOWN, Keys.ENTER).build().perform();
			} else if (FraudModuleValue.equals("PIN by SMS")) {
				action.click(SelectFraudModule).sendKeys("PIN by callback", Keys.UP, Keys.ENTER).build().perform();
			}
			Thread.sleep(3000);
			System.out.println("Enabled fraud module: " + FraudModuleValue);
			if (FraudModuleValue.equals("PIN by callback")) {
				test.log(Status.INFO, "PIN by callback is enabled");
				element.Sepa_Payment_Save_Changes.click();
				Thread.sleep(3000);
				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				Utility.wooCommerceCheckOutProcess();
				// Read the data from excel sheet
				Map<String, String> UserData = new HashMap<String, String>();
				UserData = Data.ExcelReader_PaymentMethods();
				// Check whether payment method is displayed in checkout page
				try {
					if (element.Sepa_Label.isDisplayed()) {
						if (element.Sepa_Radio_button.isDisplayed()) {
							element.Sepa_Radio_button.click();
						}
					}
				} catch (Exception e) {
					System.out.println("Error: Direct Debit SEPA payment was not available in the checkout page");
					test.log(Status.ERROR, "Error: Direct Debit SEPA payment was not available in the checkout page");
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				// Checking whether Telephone number field is displaying in checkout page
				try {
					if (element.Sepa_Telephone_Number_Label.isDisplayed()) {
						element.Sepa_Telephone_Number_Textbox.clear();
						element.Place_Order.click();
						String ValidationErrorMessage = element.Woocommerce_Checkout_Error.getText();
						// Verify the error message
						if (ValidationErrorMessage.equals("Please enter your telephone number")) {
							System.out.println("TC PASSED: Telephone number field not allows the empty data");
							test.log(Status.PASS, "TC PASSED: Telephone number field not allows the empty data");
						} else {
							System.out.println("TC FAILED: Telephone number field allows the empty data");
							test.log(Status.FAIL, "TC FAILED: Telephone number field allows the empty data");
						}
					}
				} catch (Exception e) {
					System.out.println(
							"Error: Either Telephone number field was not displayed or there was an error in displaying the error message");
					test.log(Status.ERROR,
							"Error: Either Telephone number field was not displayed or there was an error in displaying the error message");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'phonenumberEmptySEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'phonenumberEmptySEPA' method");
		}
	}

	// Method used to execute 'PIN by callback' with empty phone number validation
	@Test
	public void callbackPinNumberEmptySEPA() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("PIN by callback: PIN number field empty validation for Direct Debit SEPA");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Sepa_Payment_Display.click();
			// Checking payment method enabled or disabled
			if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Sepa_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Checking Guarantee payment enabled or disabled
			if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
				element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
				Thread.sleep(3000);
			}
			// Enabling fraud module PIN by Callback
			Actions action = new Actions(driver);
			WebElement SelectFraudModule = element.Sepa_Enable_Fraud_Prevention_Selectbox;
			String FraudModuleValue = element.Sepa_Enable_Fraud_Prevention_Selectbox.getText();
			if (FraudModuleValue.equals("None")) {
				action.click(SelectFraudModule).sendKeys("PIN by callback", Keys.DOWN, Keys.ENTER).build().perform();
			} else if (FraudModuleValue.equals("PIN by SMS")) {
				action.click(SelectFraudModule).sendKeys("PIN by callback", Keys.UP, Keys.ENTER).build().perform();
			}
			Thread.sleep(3000);
			System.out.println("Enabled fraud module: " + FraudModuleValue);
			if (FraudModuleValue.equals("PIN by callback")) {
				test.log(Status.INFO, "PIN by callback is enabled");
				element.Sepa_Payment_Save_Changes.click();
				Utility.wooCommerceBackEndLogOut();
				Thread.sleep(3000);
				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				Utility.wooCommerceFrontEndLogin();
				Utility.wooCommerceCheckOutProcess();
				Thread.sleep(3000);
				// Read the data from excel sheet
				Map<String, String> UserData = new HashMap<String, String>();
				UserData = Data.ExcelReader_PaymentMethods();
				// Check whether payment method is displayed in checkout page
				try {
					if (element.Sepa_Label.isDisplayed()) {
						if (element.Sepa_Radio_button.isDisplayed()) {
							element.Sepa_Radio_button.click();
						}
					}
				} catch (Exception e) {
					System.out.println("Error: Direct Debit SEPA payment was not available in the checkout page");
					test.log(Status.ERROR, "Error: Direct Debit SEPA payment was not available in the checkout page");
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
				// Checking whether Telephone number field is displaying in checkout page
				try {
					if (element.Sepa_Telephone_Number_Label.isDisplayed()) {
						element.Place_Order.click();
						Thread.sleep(12000);
						// Verify the PIN generation message and checking whether Transaction PIN field
						// is displaying in checkout page
						if (element.Sepa_Transaction_Pin_Number_Textbox.isDisplayed()) {
							System.out.println("Transaction PIN field was displayed in checkout page");
							test.log(Status.INFO, "Transaction PIN field was displayed in checkout page");
							Thread.sleep(10000);
							element.Sepa_Transaction_Pin_Number_Textbox.clear();
							element.Place_Order.click();
							Thread.sleep(6000);
							String PinErrorMessage = element.Woocommerce_Checkout_Error.getText();
							if (PinErrorMessage.equals("Enter your PIN")) {
								System.out.println(
										"TC PASSED: Transaction PIN did not allow the empty data and the proper error message was displayed");
								test.log(Status.PASS,
										"TC PASSED:  Transaction PIN did not allow the empty data and the proper error message was displayed");
							} else {
								System.out.println(
										"TC FAILED: Transaction PIN allows empty data or not displays the proper error message");
								test.log(Status.FAIL,
										"TC FAILED: Transaction PIN allows empty data or not displays the proper error message");
							}
						} else {
							System.out.println("Transaction PIN field was not displayed in checkout page");
							test.log(Status.INFO, "Transaction PIN field was not displayed in checkout page");
						}
					}
				} catch (Exception e) {
					System.out.println(
							"Error: Either Telephone number field was not displayed or there was an error in generating PIN number");
					test.log(Status.ERROR,
							"Error: Either Telephone number field was not displayed or there was an error in generating PIN number");
				}
				// Logout from the shop
				Utility.wooCommerceFrontEndLogOut();
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'callbackPinNumberEmptySEPA' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'callbackPinNumberEmptySEPA' method");
		}
	}
	
	// Method used to execute 'PIN by callback' with wrong PIN limit
		@Test
		public void callbackWrongPinNumberWithMoreThanMaxLimitSEPA() throws Exception {
			//try {
				// Launch the browser and load the default URL
				Utility.initConfiguration();
				Thread.sleep(3000);
				// Login to back end
				Utility.wooCommerceBackEndLogin();
				Thread.sleep(3000);
				ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
				// Title for HTML report
				test = extend.createTest("PIN by callback: Wrong PIN number with more than maximum limit for Direct Debit SEPA");
				// Steps
				Actions actions = new Actions(driver);
				actions.moveToElement(element.WooCommerce).perform();
				Thread.sleep(3000);
				element.WooCommerce_Settings.click();
				element.Payment_Tab.click();
				Thread.sleep(2000);
				element.Sepa_Payment_Display.click();
				// Checking payment method enabled or disabled
				if (!element.Sepa_Enable_Payment_Method_Checkbox.isSelected()) {
					element.Sepa_Enable_Payment_Method_Checkbox.click();
					Thread.sleep(3000);
				}
				// Checking Guarantee payment enabled or disabled
				if (element.Sepa_Enable_Payment_Guarantee_CheckBox.isSelected()) {
					element.Sepa_Enable_Payment_Guarantee_CheckBox.click();
					Thread.sleep(3000);
				}
				// Enabling fraud module PIN by Callback
				Actions action = new Actions(driver);
				WebElement SelectFraudModule = element.Sepa_Enable_Fraud_Prevention_Selectbox;
				String FraudModuleValue = element.Sepa_Enable_Fraud_Prevention_Selectbox.getText();
				if (FraudModuleValue.equals("None")) {
					action.click(SelectFraudModule).sendKeys("PIN by callback", Keys.DOWN, Keys.ENTER).build().perform();
				} else if (FraudModuleValue.equals("PIN by SMS")) {
					action.click(SelectFraudModule).sendKeys("PIN by callback", Keys.UP, Keys.ENTER).build().perform();
				}
				Thread.sleep(3000);
				System.out.println("Enabled fraud module: " + FraudModuleValue);
				if (FraudModuleValue.equals("PIN by callback")) {
					test.log(Status.INFO, "PIN by callback is enabled");
					element.Sepa_Payment_Save_Changes.click();
					Utility.wooCommerceBackEndLogOut();
					Thread.sleep(3000);
					driver.navigate().to(Constant.shopfrontendurl);
					Thread.sleep(3000);
					Utility.wooCommerceFrontEndLogin();
					Utility.wooCommerceCheckOutProcess();
					Thread.sleep(3000);
					// Read the data from excel sheet
					Map<String, String> UserData = new HashMap<String, String>();
					UserData = Data.ExcelReader_PaymentMethods();
					// Check whether payment method is displayed in checkout page
					try {
						if (element.Sepa_Label.isDisplayed()) {
							if (element.Sepa_Radio_button.isDisplayed()) {
								element.Sepa_Radio_button.click();
							}
						}
					} catch (Exception e) {
						System.out.println("Error: Direct Debit SEPA payment was not available in the checkout page");
						test.log(Status.ERROR, "Error: Direct Debit SEPA payment was not available in the checkout page");
					}
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					element.Sepa_Iban_TextBox.sendKeys(UserData.get("SEPAIBAN"));
					// Checking whether Telephone number field is displaying in checkout page
					//try {
						if (element.Sepa_Telephone_Number_Label.isDisplayed()) {
							element.Place_Order.click();
							Thread.sleep(12000);
							// Verify the PIN generation message and checking whether Transaction PIN field
							// is displaying in checkout page
							if (element.Sepa_Transaction_Pin_Number_Textbox.isDisplayed()) {
								System.out.println("Transaction PIN field was displayed in checkout page");
								test.log(Status.INFO, "Transaction PIN field was displayed in checkout page");
								Thread.sleep(10000);
								element.Sepa_Transaction_Pin_Number_Textbox.clear();
								Thread.sleep(4000);
								element.Sepa_Transaction_Pin_Number_Textbox.sendKeys("1234");
								element.Place_Order.click();
								Thread.sleep(4000);
								element.Sepa_Transaction_Pin_Number_Textbox.sendKeys("1234");
								element.Place_Order.click();
								Thread.sleep(4000);
								element.Sepa_Transaction_Pin_Number_Textbox.sendKeys("1234");
								element.Place_Order.click();
								Thread.sleep(4000);
								element.Sepa_Transaction_Pin_Number_Textbox.sendKeys("1234");
								element.Place_Order.click();								
								Thread.sleep(4000);
								// Check whether payment is hidden in checkout page
							//	try {
									if (!element.Sepa_Label.isDisplayed()) {
										System.out.println(
												"TC PASSED: Direct Debit SEPA payment was not displayed in the checkout page");
										test.log(Status.PASS,
												"TC PASSED:  Direct Debit SEPA payment was not displayed in the checkout page");
									}
									
								//} catch (Exception e) {
								//	System.out.println("TC FAILED: Direct Debit SEPA payment was displayed in the checkout page");
								//	test.log(Status.FAIL, "TC FAILED: Direct Debit SEPA payment was displayed in the checkout page");
								//}																
							} else {
								System.out.println("Transaction PIN field was not displayed in checkout page");
								test.log(Status.INFO, "Transaction PIN field was not displayed in checkout page");
							}
						}
					//} catch (Exception e) {
					//	System.out.println(
						//		"Error: Either Telephone number field was not displayed or there was an error in generating PIN number");
						//test.log(Status.ERROR,
								//"Error: Either Telephone number field was not displayed or there was an error in generating PIN number");
					//}
					// Logout from the shop
					Utility.wooCommerceFrontEndLogOut();
				}
				// Close browser
				Utility.closeBrowser();
			//} catch (Exception e) {
			//	System.out.println("ERROR: Unexpected error from 'callbackWrongPinNumberWithMoreThanMaxLimitSEPA' method");
			//	test.log(Status.ERROR, "ERROR: Unexpected error from 'callbackWrongPinNumberWithMoreThanMaxLimitSEPA' method");
			//}
		}	
}