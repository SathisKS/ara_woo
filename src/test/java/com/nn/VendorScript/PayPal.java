/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for PayPal
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class PayPal extends TestInputData {
	static TestInputData Data = new TestInputData();

	// Method used to execute 'PAYPAL' payment pending status
	@Test
	public void paypalPending() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'PAYPAL' payment pending status");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Paypal_Payment_Display.click();
			String PayPalOrderCompletionStatus = element.Paypal_Order_Completion_Status_Selectbox.getText();
			if (!element.Paypal_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Paypal_Enable_Payment_Method_Checkbox.click();
			}
			Thread.sleep(3000);
			// Enable Capture
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Paypal_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Paypal_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Paypal_Label.isDisplayed()) {
				if (element.Paypal_Radio_Button.isDisplayed() == true) {
					element.Paypal_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(15000);
				WebDriverWait waitforPaypal_Email = new WebDriverWait(driver, 20);
				waitforPaypal_Email.until(ExpectedConditions.visibilityOf(element.Paypal_Email));
				element.Paypal_Email.sendKeys(UserData.get("PayPalUserName"));
				Thread.sleep(1000);
				int pwdtextwidth = element.Paypal_Password.getSize().getWidth();
				if (pwdtextwidth != 0) {
					element.Paypal_Password.sendKeys(UserData.get("PayPalPassword"));
				} else {
					element.PayPal_NextButton.click();
					Thread.sleep(3000);
					element.Paypal_Password.sendKeys(UserData.get("PayPalPassword"));
				}
				element.Paypal_Login_Button.click();
				Thread.sleep(10000);
				element.PayPal_Continue_button.click();
				element.Paypal_Confirm_Button.click();
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("TC PASSED: Order placed successfully using PayPal");
					test.log(Status.INFO, "TC PASSED: Order placed successfully using PayPal");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the tid is in pending status (90)
					if (tid_status == 90) {
						System.out.println("Transaction is in pending status");
						test.log(Status.INFO, "Transaction is in pending status");
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
						selectpaymenttype.selectByVisibleText("PAYPAL");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
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
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {
							// Navigate to shop back end
							driver.navigate().to(Constant.shopbackendurl);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(3000);
							element.WooCommerce_Orders.click();
							String BEOrderStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
							// Verify the callback execution message is updated in the order note
							if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
								System.out.println(
										"TC PASSED: PAYPAL execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: PAYPAL execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: PAYPAL execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC PASSED: PAYPAL execution and back end order note message text was not matched successfully");
							}
							// Verify order completion status is updated in shop back end after the execution
							System.out.println("PayPal order completion status: " + PayPalOrderCompletionStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (PayPalOrderCompletionStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: PayPal order completion status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: PayPal order completion status is updated successfully in shop back end");
							} else {
								System.out.println(
										"TC FAILED: PayPal order completion status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: PayPal order completion status is not updated in shop back end");
							}
							// Verify order completion status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (PayPalOrderCompletionStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: PayPal order completion status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: PayPal order completion status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: PayPal order completion status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: PayPal order completion status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("Trasaction is not in pending status");
						test.log(Status.INFO, "Trasaction is not in pending status");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using PayPal");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using PayPal");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'paypalPending' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'paypalPending' method");
		}
	}

	// Method used to execute 'PAYPAL_BOOKBACK'
	@Test
	public void paypalBookback() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'PAYPAL_BOOKBACK'");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Paypal_Payment_Display.click();
			if (!element.Paypal_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Paypal_Enable_Payment_Method_Checkbox.click();
			}
			// Enable Capture
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Paypal_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Capture", Keys.UP, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			element.Paypal_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Paypal_Label.isDisplayed()) {
				if (element.Paypal_Radio_Button.isDisplayed() == true) {
					element.Paypal_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(15000);
				WebDriverWait waitforPaypal_Email = new WebDriverWait(driver, 20);
				waitforPaypal_Email.until(ExpectedConditions.visibilityOf(element.Paypal_Email));
				element.Paypal_Email.sendKeys(UserData.get("PayPalUserName"));
				Thread.sleep(1000);
				int pwdtextwidth = element.Paypal_Password.getSize().getWidth();
				if (pwdtextwidth != 0) {
					element.Paypal_Password.sendKeys(UserData.get("PayPalPassword"));
				} else {
					element.PayPal_NextButton.click();
					Thread.sleep(3000);
					element.Paypal_Password.sendKeys(UserData.get("PayPalPassword"));
				}
				element.Paypal_Login_Button.click();
				Thread.sleep(10000);
				element.PayPal_Continue_button.click();
				element.Paypal_Confirm_Button.click();
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using PayPal");
					test.log(Status.INFO, "Order placed successfully using PayPal");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the tid is in paid status (100)
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
						selectpaymenttype.selectByVisibleText("PAYPAL_BOOKBACK");
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
							// Navigate to shop back end
							driver.navigate().to(Constant.shopbackendurl);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(3000);
							element.WooCommerce_Orders.click();
							// String BEOrderStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
							// Verify the callback execution message is updated in the order note
							if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
								System.out.println(
										"TC PASSED: PAYPAL_BOOKBACK execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: PAYPAL_BOOKBACK execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: PAYPAL_BOOKBACK execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC PASSED: PAYPAL_BOOKBACK execution and back end order note message text was not matched successfully");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("Payment has not been made");
						test.log(Status.INFO, "Payment has not been made");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using PayPal");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using PayPal");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'paypalPending' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'paypalPending' method");
		}
	}

	// Method used to execute 'PAYPAL' onhold to confirm status
	@Test
	public void paypalOnholdToConfirm() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'PAYPAL' onhold to confirm status");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Paypal_Payment_Display.click();
			String PayPalOrderCompletionStatus = element.Paypal_Order_Completion_Status_Selectbox.getText();
			if (!element.Paypal_Enable_Payment_Method_Checkbox.isSelected()) {
				element.Paypal_Enable_Payment_Method_Checkbox.click();
				Thread.sleep(3000);
			}
			// On-hold enabled
			Actions action = new Actions(driver);
			Thread.sleep(3000);
			WebElement onhold = element.Paypal_Onhold_Payment_Action_Selectbox;
			Thread.sleep(5000);
			action.click(onhold).sendKeys("Authorize", Keys.DOWN, Keys.ENTER).build().perform();
			Thread.sleep(2000);
			// Read the order completion status
			Thread.sleep(3000);
			element.Paypal_Payment_Save_Changes.click();
			Thread.sleep(3000);
			driver.navigate().to(Constant.shopfrontendurl);
			Thread.sleep(3000);
			Utility.wooCommerceCheckOutProcess();
			Thread.sleep(4000);
			// Read the data from excel sheet
			Map<String, String> UserData = new HashMap<String, String>();
			UserData = Data.ExcelReader_PaymentMethods();
			// Check whether payment method is displayed in checkout page
			if (element.Paypal_Label.isDisplayed()) {
				if (element.Paypal_Radio_Button.isDisplayed() == true) {
					element.Paypal_Radio_Button.click();
				}
				Thread.sleep(2000);
				element.Place_Order.click();
				Thread.sleep(15000);
				WebDriverWait waitforPaypal_Email = new WebDriverWait(driver, 20);
				waitforPaypal_Email.until(ExpectedConditions.visibilityOf(element.Paypal_Email));
				element.Paypal_Email.sendKeys(UserData.get("PayPalUserName"));
				Thread.sleep(1000);

				int pwdtextwidth = element.Paypal_Password.getSize().getWidth();
				if (pwdtextwidth != 0) {
					element.Paypal_Password.sendKeys(UserData.get("PayPalPassword"));
				} else {
					element.PayPal_NextButton.click();
					Thread.sleep(10000);
					element.Paypal_Password.sendKeys(UserData.get("PayPalPassword"));
				}
				element.Paypal_Login_Button.click();
				Thread.sleep(15000);
				element.PayPal_Continue_button.click();
				Thread.sleep(15000);
				element.Paypal_Confirm_Button.click();
				driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);
				// After order placed successfully verify Thank you message displayed
				String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
				if (thankyoumessage.equals("Thank you. Your order has been received.")) {
					System.out.println("Order placed successfully using PayPal");
					test.log(Status.INFO, "Order placed successfully using PayPal");
					Thread.sleep(3000);
					// Get the amount from order success page front end
					String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]", "");
					// Get the TID from order success page front end
					String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
					System.out.println("Order amount:" + totalOrderAmount);
					System.out.println("TID:" + TID);
					// Go to card portal and check the tid_status
					driver.navigate().to(Constant.novalnetcardportalurl);
					Thread.sleep(5000);
					element.Cardportal_TID_Textbox.sendKeys(TID);
					element.Cardportal_Submit.click();
					Thread.sleep(3000);
					String tid_status_value = element.Status_Code.getText();
					int tid_status = Integer.parseInt(tid_status_value);
					// Check whether the tid is On-hold (Tid = 85)
					if (tid_status == 85) {
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
						selectpaymenttype.selectByVisibleText("PAYPAL");
						element.Test_Mode.clear();
						element.Test_Mode.sendKeys("1");
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
						System.out.println("Callback execution message: " + callback_message_updated);
						if (callback_message.contains("Novalnet Callback Script executed successfully for the TID:")) {
							// Navigate to shop back end
							driver.navigate().to(Constant.shopbackendurl);
							actions.moveToElement(element.WooCommerce).perform();
							Thread.sleep(3000);
							element.WooCommerce_Orders.click();
							String BEOrderStatus = element.Backend_Order_Status.getText();
							element.Backend_Order_Number.click();
							String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
							System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
							// Verify the callback execution message is updated in the order note
							if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
								System.out.println(
										"TC PASSED: PAYPAL execution message and back end order note message text was matched successfully");
								test.log(Status.PASS,
										"TC PASSED: PAYPAL execution message and back end order note message text was matched successfully");
							} else {
								System.out.println(
										"TC FAILED: PAYPAL execution message and back end order note message text was not matched successfully");
								test.log(Status.FAIL,
										"TC PASSED: PAYPAL execution and back end order note message text was not matched successfully");
							}
							// Verify order completion status is updated in shop back end after the execution
							System.out.println("PayPal order completion status: " + PayPalOrderCompletionStatus);
							System.out.println("Back end order status: " + BEOrderStatus);
							if (PayPalOrderCompletionStatus.equals(BEOrderStatus)) {
								System.out.println(
										"TC PASSED: PayPal order completion status is updated successfully in shop back end");
								test.log(Status.PASS,
										"TC PASSED: PayPal order completion status is updated successfully in shop back end");
							} else {
								System.out.println(
										"TC FAILED: PayPal order completion status is not updated in shop back end");
								test.log(Status.FAIL,
										"TC FAILED: PayPal order completion status is not updated in shop back end");
							}
							// Verify order completion status is updated in front after the execution
							driver.navigate().to(Constant.shopfrontendurl);
							Thread.sleep(1000);
							element.MyAccount_Menu.click();
							element.MyAccount_Orders.click();
							String FEOrderStatus = element.Frontend_Order_Status.getText();
							System.out.println("Front end order status: " + FEOrderStatus);
							if (PayPalOrderCompletionStatus.equals(FEOrderStatus)) {
								System.out.println(
										"TC PASSED: PayPal order completion status is updated successfully in shop front end");
								test.log(Status.PASS,
										"TC PASSED: PayPal order completion status is updated successfully in shop front end");
							} else {
								System.out.println(
										"TC FAILED: PayPal order completion status is not updated in shop front end");
								test.log(Status.FAIL,
										"TC FAILED: PayPal order completion status is not updated in shop front end");
							}
						} else {
							System.out.println("ERROR: Callback response message is displayed wrongly");
							test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
						}
					} else {
						System.out.println("Trasaction is not in pending status");
						test.log(Status.INFO, "Trasaction is not in pending status");
					}
				} else {
					System.out.println("TC FAILED: Order was not placed successfully using PayPal");
					test.log(Status.FAIL, "TC FAILED: Order was not placed successfully using PayPal");
				}
			}
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'paypalOnholdToConfirm' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'paypalOnholdToConfirm' method");
		}
	}
}