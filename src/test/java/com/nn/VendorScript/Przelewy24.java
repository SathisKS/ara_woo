/* ************************************************************************
 * Project Name: WooCommerce TestSuite
 * Description:  This file is used to execute vendor script for Przelewy24
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nn.Repository.ElementLocators;
import com.nn.TestConfiguration.Constant;
import com.nn.TestConfiguration.Utility;
import com.nn.TestData.TestInputData;

public class Przelewy24 extends TestInputData {
	static TestInputData Data = new TestInputData();
	
	// Method used to execute 'PRZELEWY24' payment pending status
	@Test
	public void przelewy24Pending() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'PRZELEWY24' payment pending status");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Przelewy24_Payment_Display.click();
			String Przelewy24OrderCompletionStatus = element.Przelewy24_Order_Completion_Status_Selectbox.getText();
				if (!element.Przelewy24_Enable_Payment_Method_Checkbox.isSelected()) {
					element.Przelewy24_Enable_Payment_Method_Checkbox.click();
				}
				Thread.sleep(3000);
				element.Przelewy24_Payment_Save_Changes.click();
				Thread.sleep(3000);
				element.Woocommerce_General_tab.click();
				Thread.sleep(3000);
				Actions currency = new Actions(driver);
				currency.click(element.General_Currency).sendKeys("Polish złoty (zł)", Keys.DOWN, Keys.ENTER).build()
						.perform();
				Thread.sleep(3000);
				element.General_Save_Changes_Button.click();
				Thread.sleep(3000);
				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				// Utility.wooCommerceFrontEndLogin();
				Utility.wooCommerceCheckOutProcess();
				Thread.sleep(4000);
				Map<String, String> UserData = new HashMap<String, String>();
				UserData = Data.ExcelReader_PaymentMethods();
					// Check whether payment method is displayed in checkout page
					if (element.Przelewy24_Label.isDisplayed()) {
						if (element.Przelewy24_Radio_Button.isDisplayed() == true) {
							element.Przelewy24_Radio_Button.click();
						}
						Thread.sleep(2000);
						element.Place_Order.click();
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						element.Przelewy24_Choose_PaymentMethod.click();
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_Login.clear();
						element.Przelewy24_Login.sendKeys(UserData.get("Przelewy24UserName"));
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_Password.clear();
						element.Przelewy24_Password.sendKeys(UserData.get("Przelewy24Password"));
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_SandBox_Login_button.click();
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_Pay_button.click();
						driver.manage().timeouts().implicitlyWait(12000, TimeUnit.MILLISECONDS);
						// After order placed successfully verify Thank you message displayed
						String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
						if (thankyoumessage.equals("Thank you. Your order has been received.")) {
							System.out.println("Order placed successfully using Przelewy24");
							test.log(Status.INFO, "Order placed successfully using Przelewy24");
							Thread.sleep(3000);
							// Get the amount from order success page front end
							String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]",
									"");
							// Get the TID from order success page front end
							String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
							String TID_new = TID.substring(2, TID.length());
							System.out.println("Order amount:" + totalOrderAmount);
							System.out.println("TID:" + TID_new);
							Thread.sleep(2000);
							// Go to card portal and check the tid_status
							driver.navigate().to(Constant.novalnetcardportalurl);
							Thread.sleep(5000);
							element.Cardportal_TID_Textbox.sendKeys(TID_new);
							element.Cardportal_Submit.click();
							Thread.sleep(3000);
							String tid_status_value = element.Status_Code.getText();
							int tid_status = Integer.parseInt(tid_status_value);
							// Check whether the tid is in pending status (86)
							if (tid_status == 86) {
								// Go to callback execution site and enter vendor script URL
								driver.navigate().to(Constant.vendorscripturl);
								Thread.sleep(4000);
								element.Vendor_Script_Url
										.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
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
								selectpaymenttype.selectByVisibleText("PRZELEWY24");
								element.Test_Mode.clear();
								element.Test_Mode.sendKeys("1");
								element.Amount.clear();
								element.Amount.sendKeys(totalOrderAmount);
								element.Currency.clear();
								element.Currency.sendKeys("PLN");
								element.Status.clear();
								element.Status.sendKeys("100");
								element.Tid_Status.clear();
								element.Tid_Status.sendKeys("100");
								element.Tid.clear();
								element.Tid.sendKeys(TID_new);
								element.Execute_Button.click();
								String callback_message = element.callback_message.getText();
								String callback_message_updated = callback_message.substring(9,
										callback_message.length());
								System.out.println("Callback execution message: " + callback_message_updated);
								if (callback_message
										.contains("Novalnet Callback Script executed successfully for the TID:")) {
									// Change currency as euro
									// Go to shop back end and front end and check the order status update
									driver.navigate().to(Constant.shopbackendurl);
									Thread.sleep(5000);
									Actions action = new Actions(driver);
									action.moveToElement(element.WooCommerce).perform();
									Thread.sleep(3000);
									element.WooCommerce_Settings.click();
									Thread.sleep(3000);
									element.Woocommerce_General_tab.click();
									Thread.sleep(3000);
									Actions eurocurrency = new Actions(driver);
									eurocurrency.click(element.General_Currency)
											.sendKeys("Euro (€)", Keys.DOWN, Keys.ENTER).build().perform();
									Thread.sleep(3000);
									element.General_Save_Changes_Button.click();
									Thread.sleep(3000);
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
												"TC PASSED: PRZELEWY24 execution message and back end order note message text was matched successfully");
										test.log(Status.PASS,
												"TC PASSED: PRZELEWY24 execution message and back end order note message text was matched successfully");
									} else {
										System.out.println(
												"TC FAILED: PRZELEWY24 execution message and back end order note message text was not matched successfully");
										test.log(Status.FAIL,
												"TC PASSED: PRZELEWY24 execution and back end order note message text was not matched successfully");
									}
									// Verify order completion status is updated in shop back end after the execution
									System.out.println(
											"Przelewy24 order completion status: " + Przelewy24OrderCompletionStatus);
									System.out.println("Back end order status: " + BEOrderStatus);
									if (Przelewy24OrderCompletionStatus.equals(BEOrderStatus)) {
										System.out.println(
												"TC PASSED: Przelewy24 order completion status is updated successfully in shop back end");
										test.log(Status.PASS,
												"TC PASSED: Przelewy24 order completion status is updated successfully in shop back end");
									} else {
										System.out.println(
												"TC FAILED: Przelewy24 order completion status is not updated in shop back end");
										test.log(Status.FAIL,
												"TC FAILED: Przelewy24 order completion status is not updated in shop back end");
									}
									driver.navigate().to(Constant.shopfrontendurl);
									Thread.sleep(1000);
									element.MyAccount_Menu.click();
									element.MyAccount_Orders.click();
									String FEOrdersStatus = element.Frontend_Order_Status.getText();
									System.out.println("Front end order status: " + FEOrdersStatus);
									// Verify order completion status is updated in shop front end after the execution
									if (Przelewy24OrderCompletionStatus.equals(FEOrdersStatus)) {
										System.out.println(
												"TC PASSED: Przelewy24 order completion status is updated successfully in shop front end");
										test.log(Status.PASS,
												"TC PASSED: Przelewy24 order completion status is updated successfully in shop front end");
									} else {
										System.out.println(
												"TC FAILED: Przelewy24 order completion status is not updated in shop front end");
										test.log(Status.FAIL,
												"TC FAILED: Przelewy24 order completion status is not updated in shop front end");
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
							System.out.println("ERROR: Order was not placed successfully using Przelewy24");
							test.log(Status.ERROR, "ERROR:  Order was not placed successfully using Przelewy24");
						}
					}				
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'przelewy24Pending' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'przelewy24Pending' method");
		}
	}

	// Method used to execute 'PRZELEWY24_REFUND'
	@Test
	public void przelewy24Refund() throws Exception {
		try {
			// Launch the browser and load the default URL
			Utility.initConfiguration();
			Thread.sleep(3000);
			// Login to back end and check payment method is enabled or disabled
			Utility.wooCommerceBackEndLogin();
			Thread.sleep(3000);
			ElementLocators element = PageFactory.initElements(driver, ElementLocators.class);
			// Title for HTML report
			test = extend.createTest("Vendor script execution for 'PRZELEWY24_REFUND' refund");
			// Steps
			Actions actions = new Actions(driver);
			actions.moveToElement(element.WooCommerce).perform();
			Thread.sleep(3000);
			element.WooCommerce_Settings.click();
			element.Payment_Tab.click();
			Thread.sleep(2000);
			element.Przelewy24_Payment_Display.click();
				if (!element.Przelewy24_Enable_Payment_Method_Checkbox.isSelected()) {
					element.Przelewy24_Enable_Payment_Method_Checkbox.click();
				}
				Thread.sleep(3000);
				element.Przelewy24_Payment_Save_Changes.click();
				Thread.sleep(3000);
				element.Woocommerce_General_tab.click();
				Thread.sleep(3000);
				Actions currency = new Actions(driver);
				currency.click(element.General_Currency).sendKeys("Polish złoty (zł)", Keys.DOWN, Keys.ENTER).build()
						.perform();
				Thread.sleep(3000);
				element.General_Save_Changes_Button.click();
				Thread.sleep(3000);
				driver.navigate().to(Constant.shopfrontendurl);
				Thread.sleep(3000);
				// Utility.wooCommerceFrontEndLogin();
				Utility.wooCommerceCheckOutProcess();
				Thread.sleep(4000);
				Map<String, String> UserData = new HashMap<String, String>();
				UserData = Data.ExcelReader_PaymentMethods();
					// Check whether payment method is displayed in checkout page
					if (element.Przelewy24_Label.isDisplayed()) {
						if (element.Przelewy24_Radio_Button.isDisplayed() == true) {
							element.Przelewy24_Radio_Button.click();
						}
						Thread.sleep(2000);
						element.Place_Order.click();
						driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						element.Przelewy24_Choose_PaymentMethod.click();
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_Login.clear();
						element.Przelewy24_Login.sendKeys(UserData.get("Przelewy24UserName"));
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_Password.clear();
						element.Przelewy24_Password.sendKeys(UserData.get("Przelewy24Password"));
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_SandBox_Login_button.click();
						driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
						element.Przelewy24_Pay_button.click();
						driver.manage().timeouts().implicitlyWait(12000, TimeUnit.MILLISECONDS);
						// After order placed successfully verify Thank you message displayed
						String thankyoumessage = element.FE_Thank_You_Page_Text.getText();
						if (thankyoumessage.equals("Thank you. Your order has been received.")) {
							System.out.println("Order placed successfully using Przelewy24");
							test.log(Status.INFO, "Order placed successfully using Przelewy24");
							Thread.sleep(3000);
							// Get the amount from order success page front end
							String totalOrderAmount = element.OrderDetails_TotalAmount.getText().replaceAll("[^0-9]",
									"");
							// Get the TID from order success page front end
							String TID = element.OrderDetails_Note_TID.getText().replaceAll("[^0-9]", "");
							String TID_new = TID.substring(2, TID.length());
							System.out.println("Order amount:" + totalOrderAmount);
							System.out.println("TID:" + TID_new);
							Thread.sleep(2000);													
								// Go to callback execution site and enter vendor script URL
								driver.navigate().to(Constant.vendorscripturl);
								Thread.sleep(4000);
								element.Vendor_Script_Url
										.sendKeys((Constant.shopfrontendurl) + "?wc-api=novalnet_callback");
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
								selectpaymenttype.selectByVisibleText("PRZELEWY24_REFUND");
								element.Test_Mode.clear();
								element.Test_Mode.sendKeys("1");
								element.Tid_Payment.clear();
								element.Tid_Payment.sendKeys(TID_new);
								element.Amount.clear();
								element.Amount.sendKeys(totalOrderAmount);
								element.Currency.clear();
								element.Currency.sendKeys("PLN");
								element.Status.clear();
								element.Status.sendKeys("100");
								element.Tid_Status.clear();
								element.Tid_Status.sendKeys("100");
								element.Tid.clear();
								element.Tid.sendKeys("13245678945612345");
								element.Execute_Button.click();
								String callback_message = element.callback_message.getText();
								String callback_message_updated = callback_message.substring(9,
										callback_message.length());
								System.out.println("Callback execution message: " + callback_message_updated);
								if (callback_message.contains(
										"Novalnet callback received. Refund/Bookback executed successfully for the TID:")) {									
									// Change currency as euro
									// Go to shop back end and front end and check the order status update
									driver.navigate().to(Constant.shopbackendurl);
									Thread.sleep(5000);
									Actions action = new Actions(driver);
									action.moveToElement(element.WooCommerce).perform();
									Thread.sleep(3000);
									element.WooCommerce_Settings.click();
									Thread.sleep(3000);
									element.Woocommerce_General_tab.click();
									Thread.sleep(3000);
									Actions eurocurrency = new Actions(driver);
									eurocurrency.click(element.General_Currency)
											.sendKeys("Euro (€)", Keys.DOWN, Keys.ENTER).build().perform();
									Thread.sleep(3000);
									element.General_Save_Changes_Button.click();
									Thread.sleep(3000);
									actions.moveToElement(element.WooCommerce).perform();
									Thread.sleep(3000);
									element.WooCommerce_Orders.click();
									element.Backend_Order_Number.click();
									String BEOrderNotes_CallBackMessage = element.BE_OrderNotes_Message.getText();
									System.out.println("Back end order note: " + BEOrderNotes_CallBackMessage);
									// Verify the callback execution message is updated in the order note
									if (BEOrderNotes_CallBackMessage.equalsIgnoreCase(callback_message_updated)) {
										System.out.println(
												"TC PASSED: PRZELEWY24_REFUND execution message and back end order note message text was matched successfully");
										test.log(Status.PASS,
												"TC PASSED: PRZELEWY24_REFUND execution message and back end order note message text was matched successfully");
									} else {
										System.out.println(
												"TC FAILED: PRZELEWY24_REFUND execution message and back end order note message text was not matched successfully");
										test.log(Status.FAIL,
												"TC PASSED: PRZELEWY24_REFUND execution and back end order note message text was not matched successfully");
									}

								} else {
									System.out.println("ERROR: Callback response message is displayed wrongly");
									test.log(Status.ERROR, "ERROR: Callback response message is displayed wrongly");
								}
						} else {
							System.out.println("ERROR: Order was not placed successfully using Przelewy24");
							test.log(Status.ERROR, "ERROR:  Order was not placed successfully using Przelewy24");
						}
					}				
			// Close browser
			Utility.closeBrowser();
		} catch (Exception e) {
			System.out.println("ERROR: Unexpected error from 'przelewy24Refund' method");
			test.log(Status.ERROR, "ERROR: Unexpected error from 'przelewy24Refund' method");
		}
	}
}