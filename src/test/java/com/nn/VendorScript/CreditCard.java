/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Credit Card
 * Author:       Novalnet AG
 * Author URI:   https://www.novalnet.de
 * Version:      1.0.0
 * Copyright:	 Novalnet AG
 * License:      GNU General Public License
 **************************************************************************/

package com.nn.VendorScript;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class CreditCard extends TestInputData {
	TestInputData Data = new TestInputData();

	// Method used to execute 'CREDITCARD_BOOKBACK'
	@Test
	public void creditCardBookback() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Credit Card 'CREDITCARD_BOOKBACK'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Creditcard_Payment_Display.click();
			if (!element.Creditcard_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Creditcard_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Disable 3D Secure, if enabled
			Actions action1 = new Actions(driver);
			Thread.sleep(3000);
			WebElement cc3D = element.Creditcard_Enable_3d_Secure_Selectbox;
			Thread.sleep(5000);
			action1.click(cc3D).sendKeys("No", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			// Enable Capture
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Creditcard_Pending_Payment_Status_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Creditcard_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Creditcard_Title.isDisplayed() == true) {
				if (element.Creditcard_Radio_Button.isDisplayed()) {
					element.Creditcard_Radio_Button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Switch to Credit Card iframe to enter the values in Credit Card form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.sendKeys(UserData.get("CreditcardCardHolder"));
				element.Credit_Number_TextBox.sendKeys(UserData.get("CreditcardCardNumber"));
				element.Expiry_Date_TextBox.sendKeys(UserData.get("CreditcardExpiryDate"));
				element.CVC_TextBox.sendKeys(UserData.get("CreditcardCVC_CVV"));
				// Switch to default window
				driver.switchTo().defaultContent();
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Credit Card");
					test.log(Status.INFO, "Order placed successfully using Credit Card");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the amount is paid (status code = 100)
					if (tid_status == 100) {
						// Go to callback execution site and enter vendor script URL
						driver.navigate().to(Constant.vendorscripturl);
						Thread.sleep(4000);
						element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
						// Enter required parameter
						element.Vendor_Id.clear();
						element.Vendor_Id.sendKeys("4");
						element.Vendor_Auth_Code.clear();
						element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
						element.Product_Id.clear();
						element.Product_Id.sendKeys("14");
						element.Tariff_Id.clear();
						element.Tariff_Id.sendKeys("30");
						element.Payment_Type_Edit_Button.click();
						Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
						selectpaymenttype.selectByVisibleText("CREDITCARD_BOOKBACK");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Tid_Payment.sendKeys(TID);
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys("13245678945612345");
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message.contains(
								"Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: CREDITCARD_BOOKBACK execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: CREDITCARD_BOOKBACK execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: CREDITCARD_BOOKBACK execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: CREDITCARD_BOOKBACK execution message and back end order note message text was not matched");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("TC FAILED: Payment has not been done for the order placed");
						test.log(Status.FAIL, "TC FAILED: Payment has not been done for the order placed");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Credit Card");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Credit Card");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'creditCardBookback' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'creditCardBookback' method");
		}
	}

	// Method used to execute 'CREDITCARD_CHARGEBACK'
	@Test
	public void creditCardChargeBack() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Credit Card 'CREDITCARD_CHARGEBACK'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Creditcard_Payment_Display.click();
			if (!element.Creditcard_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Creditcard_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Disable 3D Secure, if enabled
			Actions action1 = new Actions(driver);
			Thread.sleep(3000);
			WebElement cc3D = element.Creditcard_Enable_3d_Secure_Selectbox;
			Thread.sleep(5000);
			action1.click(cc3D).sendKeys("No", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			// Enable Capture
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Creditcard_Pending_Payment_Status_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Creditcard_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Creditcard_Title.isDisplayed() == true) {
				if (element.Creditcard_Radio_Button.isDisplayed()) {
					element.Creditcard_Radio_Button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Switch to Credit Card iframe to enter the values in Credit Card form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.sendKeys(UserData.get("CreditcardCardHolder"));
				element.Credit_Number_TextBox.sendKeys(UserData.get("CreditcardCardNumber"));
				element.Expiry_Date_TextBox.sendKeys(UserData.get("CreditcardExpiryDate"));
				element.CVC_TextBox.sendKeys(UserData.get("CreditcardCVC_CVV"));
				// Switch to default window
				driver.switchTo().defaultContent();
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Credit Card");
					test.log(Status.INFO, "Order placed successfully using Credit Card");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					// Enter required parameter
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("CREDITCARD_CHARGEBACK");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String chargeback_message = element.callback_message.getText();
					String chargeback_message_updated = chargeback_message.substring(9,
							chargeback_message.length());
					if (chargeback_message
							.contains("Novalnet callback received. Chargeback executed successfully for the TID")) {
						Thread.sleep(2000);
						driver.navigate().to(Constant.shopbackendurl);
						Thread.sleep(1000);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(1500);
						element.WooCommerce_Orders.click();
						element.Backend_Order_Number.click();
						String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Callback execution message: " + chargeback_message_updated);
						System.out.println("Back end order note: " + BEOrderNotesMessage);
						if (chargeback_message_updated.equals(BEOrderNotesMessage)) {
							System.out.println(
									"TC PASSED: CREDITCARD_CHARGEBACK execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: CREDITCARD_CHARGEBACK execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: CREDITCARD_CHARGEBACK execution message and back end order note message text was not matched");
							test.log(Status.FAIL,
									"TC FAILED: CREDITCARD_CHARGEBACK execution message and back end order note message text was not matched");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Credit Card");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Credit Card");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'creditCardChargeBack' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'creditCardChargeBack' method");
		}
	}

	// Method used to execute 'CREDIT_ENTRY_CREDITCARD'
	@Test
	public void creditEntryCreditCard() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Credit Card 'CREDIT_ENTRY_CREDITCARD'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Creditcard_Payment_Display.click();
			if (!element.Creditcard_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Creditcard_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Disable 3D Secure, if enabled
			Actions action1 = new Actions(driver);
			Thread.sleep(3000);
			WebElement cc3D = element.Creditcard_Enable_3d_Secure_Selectbox;
			Thread.sleep(5000);
			action1.click(cc3D).sendKeys("No", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			// Enable Capture
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Creditcard_Pending_Payment_Status_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Creditcard_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Creditcard_Title.isDisplayed() == true) {
				if (element.Creditcard_Radio_Button.isDisplayed()) {
					element.Creditcard_Radio_Button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Switch to Credit Card iframe to enter the values in Credit Card form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.sendKeys(UserData.get("CreditcardCardHolder"));
				element.Credit_Number_TextBox.sendKeys(UserData.get("CreditcardCardNumber"));
				element.Expiry_Date_TextBox.sendKeys(UserData.get("CreditcardExpiryDate"));
				element.CVC_TextBox.sendKeys(UserData.get("CreditcardCVC_CVV"));
				// Switch to default window
				driver.switchTo().defaultContent();
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Credit Card");
					test.log(Status.INFO, "Order placed successfully using Credit Card");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to callback execution site and enter vendor script URL
					driver.navigate().to(Constant.vendorscripturl);
					Thread.sleep(4000);
					element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
					// Enter required parameter
					element.Vendor_Id.clear();
					element.Vendor_Id.sendKeys("4");
					element.Vendor_Auth_Code.clear();
					element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
					element.Product_Id.clear();
					element.Product_Id.sendKeys("14");
					element.Tariff_Id.clear();
					element.Tariff_Id.sendKeys("30");
					element.Payment_Type_Edit_Button.click();
					Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
					selectpaymenttype.selectByVisibleText("CREDIT_ENTRY_CREDITCARD");
					element.Test_Mode.clear();
					element.Test_Mode.sendKeys("1");
					element.Tid_Payment.clear();
					element.Tid_Payment.sendKeys(TID);
					element.Amount.clear();
					element.Amount.sendKeys(totalOrderAmount);
					element.Currency.clear();
					element.Currency.sendKeys("EUR");
					element.Status.clear();
					element.Status.sendKeys("100");
					element.Tid_Status.clear();
					element.Tid_Status.sendKeys("100");
					element.Tid.clear();
					element.Tid.sendKeys("13245678945612345");
					element.Execute_Button.click();
					String creditEntryCreditCard_message = element.callback_message.getText();
					String creditEntryCreditCard_message_updated = creditEntryCreditCard_message.substring(9,
							creditEntryCreditCard_message.length());
					if (creditEntryCreditCard_message
							.contains("Novalnet Callback Script executed successfully for the TID:")) {
						Thread.sleep(2000);
						driver.navigate().to(Constant.shopbackendurl);
						Thread.sleep(1000);
						actions.moveToElement(element.WooCommerce).perform();
						Thread.sleep(1500);
						element.WooCommerce_Orders.click();
						element.Backend_Order_Number.click();
						String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
						System.out.println("Callback execution message: " + creditEntryCreditCard_message_updated);
						System.out.println("Back end order note: " + BEOrderNotesMessage);
						if (creditEntryCreditCard_message_updated.equals(BEOrderNotesMessage)) {
							System.out.println(
									"TC PASSED: CREDIT_ENTRY_CREDITCARD execution message and back end order note message text was matched successfully");
							test.log(Status.PASS,
									"TC PASSED: CREDIT_ENTRY_CREDITCARD execution message and back end order note message text was matched successfully");
						} else {
							System.out.println(
									"TC FAILED: CREDIT_ENTRY_CREDITCARD execution message and back end order note message text was not matched");
							test.log(Status.FAIL,
									"TC FAILED: CREDIT_ENTRY_CREDITCARD execution message and back end order note message text was not matched");
						}
					} else {
						System.out.println("ERROR: Callback response message is displayed wrongly");
						test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Credit Card");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Credit Card");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'creditEntryCreditCard' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'creditEntryCreditCard' method");
		}
	}

	// Method used to execute 'CREDITCARD' onhold to confirm
	@Test
	public void creditCardOnholdToComfirm() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'CREDITCARD' onhold to confirm");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Creditcard_Payment_Display.click();
			if (!element.Creditcard_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Creditcard_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Read the order completion status
			String OrderCompletionStatus = element.Creditcard_Order_Completion_Status_Selectbox.getText();
			Thread.sleep(3000);
			// Disable 3D Secure, if enabled
			Actions action1 = new Actions(driver);
			Thread.sleep(3000);
			WebElement cc3D = element.Creditcard_Enable_3d_Secure_Selectbox;
			Thread.sleep(5000);
			action1.click(cc3D).sendKeys("No", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			// On-hold enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Creditcard_Pending_Payment_Status_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Creditcard_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Creditcard_Title.isDisplayed() == true) {
				if (element.Creditcard_Radio_Button.isDisplayed()) {
					element.Creditcard_Radio_Button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Switch to Credit Card iframe to enter the values in Credit Card form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.sendKeys(UserData.get("CreditcardCardHolder"));
				element.Credit_Number_TextBox.sendKeys(UserData.get("CreditcardCardNumber"));
				element.Expiry_Date_TextBox.sendKeys(UserData.get("CreditcardExpiryDate"));
				element.CVC_TextBox.sendKeys(UserData.get("CreditcardCVC_CVV"));
				// Switch to default window
				driver.switchTo().defaultContent();
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Credit Card");
					test.log(Status.INFO, "Order placed successfully using Credit Card");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the Tid is 98 (status code = 98)
					if (tid_status == 98) {
						// Go to callback execution site and enter vendor script URL
						driver.navigate().to(Constant.vendorscripturl);
						Thread.sleep(4000);
						element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
						// Enter required parameter
						element.Vendor_Id.clear();
						element.Vendor_Id.sendKeys("4");
						element.Vendor_Auth_Code.clear();
						element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
						element.Product_Id.clear();
						element.Product_Id.sendKeys("14");
						element.Tariff_Id.clear();
						element.Tariff_Id.sendKeys("30");
						element.Payment_Type_Edit_Button.click();
						Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
						selectpaymenttype.selectByVisibleText("CREDITCARD");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys(TID);
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						if (callback_message.contains("Novalnet Callback Script executed successfully for the TID: ")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							String BEOrderStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							// Verify order completion status is updated in shop back end after the execution
							System.out.println("CreditCard order completion status: " + OrderCompletionStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (OrderCompletionStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: Order completion status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: Order completion status is updated successfully in shop back end");
							} else {
								System.out.println(
										"TC FAILED: Order completion status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: Order completion status is not updated in shop back end");
							}
							// Verify order completion status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (OrderCompletionStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: Order completion status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: Order completion status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: Order completion status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: Order completion status is not updated in shop front end");
							}
							System.out.println("Callback execution message: " + callback_message_updated);
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: CREDITCARD execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: CREDITCARD execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: CREDITCARD execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: CREDITCARD execution message and back end order note message text was not matched");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("TC FAILED: Transaction is already confirmed or not in pending status");
						test.log(Status.FAIL, "TC FAILED: Transaction is already confirmed or not in pending status");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Credit Card");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Credit Card");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'creditCardOnholdToComfirm' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'creditCardOnholdToComfirm' method");
		}
	}
	
	// Method used to execute 'DEBT_COLLECTION_CREDITCARD'
	@Test
	public void creditCardDebtCollection() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for Credit Card 'DEBT_COLLECTION_CREDITCARD'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Creditcard_Payment_Display.click();
			if (!element.Creditcard_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Creditcard_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// Disable 3D Secure, if enabled
			Actions action1 = new Actions(driver);
			Thread.sleep(3000);
			WebElement cc3D = element.Creditcard_Enable_3d_Secure_Selectbox;
			Thread.sleep(5000);
			action1.click(cc3D).sendKeys("No", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			// On-hold enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Creditcard_Pending_Payment_Status_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Creditcard_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Creditcard_Title.isDisplayed() == true) {
				if (element.Creditcard_Radio_Button.isDisplayed()) {
					element.Creditcard_Radio_Button.click();
				}
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				// Switch to CreditCard iframe to enter the values in Creditcard form
				driver.switchTo().frame("novalnet_cc_iframe");
				element.Credit_Holder_Name_TextBox.sendKeys(UserData.get("CreditcardCardHolder"));
				element.Credit_Number_TextBox.sendKeys(UserData.get("CreditcardCardNumber"));
				element.Expiry_Date_TextBox.sendKeys(UserData.get("CreditcardExpiryDate"));
				element.CVC_TextBox.sendKeys(UserData.get("CreditcardCVC_CVV"));
				// Switch to default window
				driver.switchTo().defaultContent();
				element.Place_Order.click();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using Credit Card");
					test.log(Status.INFO, "Order placed successfully using Credit Card");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the amount is paid (status code = 100)
					if (tid_status == 100) {
						// Go to callback execution site and enter vendor script URL
						driver.navigate().to(Constant.vendorscripturl);
						Thread.sleep(4000);
						element.Vendor_Script_Url.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
						// Enter required parameter
						element.Vendor_Id.clear();
						element.Vendor_Id.sendKeys("4");
						element.Vendor_Auth_Code.clear();
						element.Vendor_Auth_Code.sendKeys("JyEtHUjjbHNJwVztW6JrafIMHQvici");
						element.Product_Id.clear();
						element.Product_Id.sendKeys("14");
						element.Tariff_Id.clear();
						element.Tariff_Id.sendKeys("30");
						element.Payment_Type_Edit_Button.click();
						Select selectpaymenttype = new Select(element.Payment_Type_Selectbox);
						selectpaymenttype.selectByVisibleText("DEBT_COLLECTION_CREDITCARD");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
						element.Tid_Payment.clear();
						element.Tid_Payment.sendKeys(TID);
						element.Amount.clear();
						element.Amount.sendKeys(totalOrderAmount);
						element.Currency.clear();
						element.Currency.sendKeys("EUR");
						element.Status.clear();
						element.Status.sendKeys("100");
						element.Tid_Status.clear();
						element.Tid_Status.sendKeys("100");
						element.Tid.clear();
						element.Tid.sendKeys("13245678945612345");
						element.Execute_Button.click();
						String callback_message = element.callback_message.getText();
						String callback_message_updated = callback_message.substring(9, callback_message.length());
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {
							Thread.sleep(2000);
							driver.navigate().to(Constant.shopbackendurl);
							Thread.sleep(1000);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(1500);
							element.WooCommerce_Orders.click();
							element.Backend_Order_Number.click();
							String BEOrderNotesMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotesMessage);
							if (callback_message_updated.equals(BEOrderNotesMessage)) {
								System.out.println(
										"TC PASSED: DEBT_COLLECTION_CREDITCARD execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: DEBT_COLLECTION_CREDITCARD execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: DEBT_COLLECTION_CREDITCARD execution message and back end order note message text was not matched");
								test.log(Status.FAIL,
										"TC FAILED: DEBT_COLLECTION_CREDITCARD execution message and back end order note message text was not matched");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("TC FAILED: Payment has not been done for the order placed");
						test.log(Status.FAIL, "TC FAILED: Payment has not been done for the order placed");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using Credit Card");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using Credit Card");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'creditCardDebtCollection' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'creditCardDebtCollection' method");
		}
	}
}